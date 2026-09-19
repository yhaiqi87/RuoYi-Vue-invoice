package com.yhq.invoice.leqi.client;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yhq.invoice.common.dto.InvoiceResponse;
import com.yhq.invoice.common.enums.LeqiReturnCode;
import com.yhq.invoice.common.exception.InvoiceException;
import com.yhq.invoice.common.model.ChannelResponse;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * 乐企（LEQI）HTTP 调用工具：封装请求发送与响应包装。
 *
 * <p>严格按基础版文档「第五章 接口清单 / HTTP 请求调用说明」实现调用链路：
 * <ol>
 *   <li>业务报文 JSON 化；</li>
 *   <li>SM4(ECB) 加密请求报文（{@link SM4Util}，当前占位透传）；</li>
 *   <li>组装请求头 fwbm(服务编码)/nlbm(能力编码)/jrdwptbh/sydwptbh/ylbm/access_signature/sxcsbz；</li>
 *   <li>POST 能力开放平台，按 content-encoding 处理 gzip 响应；</li>
 *   <li>解析外层信封 KfptRes 与内层 ServerResponse，解密 data 并映射为业务响应 T；</li>
 *   <li>统一包装为 {@link ChannelResponse}：成功以业务报文 returncode/returnmsg 填充，
 *       失败以 Error 下的 code/Message 填充。</li>
 * </ol>
 *
 * <p>未知请求头(jrdwptbh/sydwptbh/ylbm/access_signature/sxcsbz)与真实密钥、网关地址待接入，
 * 当前先以空值/占位补全 HTTP 流程框架。
 */
@Component
public class LeqiHttpUtil {

    private static final Logger log = LoggerFactory.getLogger(LeqiHttpUtil.class);

    /**
     * 能力开放平台调用地址（文档示例）：路径参数为 能力编码/接口编码。
     */
//    private static final String URL = "https://lqpt.chinatax.gov.cn:8443/access/v2/invoke/{nlbm}/{fwbm}/";
    private static final String URL = "https://localhost:8443/access/v2/invoke/{}/{}/";

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /** 调用乐企接口，将原始业务响应包装为 {@link ChannelResponse}。 */
    public <T> ChannelResponse<T> call(String apiCode, Object request, Class<T> responseType) {
        return invoke(apiCode, request, responseType);
    }

    /**
     * 真实调用链路（按文档 HTTP 请求调用说明）。
     */
    private <T> ChannelResponse<T> invoke(String apiCode, Object request, Class<T> responseType) {
        try {
            // 1. 业务报文 JSON
            String body = objectMapper.writeValueAsString(request);
            // 2. 请求报文 SM4 加密（占位：当前透传）
            String lqId = "12345678";
            String nlbm = "888888";
            String encBody = SM4Util.encryptEcb(lqId, body);

            // 3. 组装请求头（未知项先空着）
            HttpHeaders headers = new HttpHeaders();
            headers.add("fwbm", apiCode);                 // 服务编码（接口编码）
            headers.add("nlbm", nlbm);                     // 能力编码（待按 CapabilityCode 注入）
            headers.add("jrdwptbh", "");                 // 接入单位平台编号
            headers.add("sydwptbh", "");                 // 使用单位平台编号
            headers.add("ylbm", "");                     // 用例编码
            headers.add("access_signature", "");         // 访问签名
            headers.add("sxcsbz", "");                   // 沙箱测试标志

            String fullUrl = StrUtil.format(URL, nlbm, apiCode);
            log.info("[乐企调用] fullUrl={}", fullUrl);
            log.info("[乐企调用] 请求头={}", headers);
            log.info("[乐企调用] 请求报文(明文)={}", body);
            log.info("[乐企调用] 请求报文(加密)={}", encBody);

            // 4. 发起请求
            HttpEntity<String> entity = new HttpEntity<>(encBody, headers);
            ResponseEntity<byte[]> responseEntity =
                    restTemplate.postForEntity(fullUrl, entity, byte[].class, "", apiCode);

            // 5. 处理 gzip / 非 gzip 响应
            String result = resolveBody(responseEntity);
            log.info("[乐企调用] 响应状态码={}, 响应报文={}", responseEntity.getStatusCode(), result);

            // 6. 解析外层信封 KfptRes
            KfptRes kfptRes = objectMapper.readValue(result, KfptRes.class);
            if (!"200".equals(kfptRes.getHttpStatusCode())) {
                log.warn("[乐企调用] 平台返回非成功状态 httpStatusCode={}", kfptRes.getHttpStatusCode());
                return ChannelResponse.of(kfptRes.getHttpStatusCode(), "乐企平台返回非成功状态", null);
            }

            // 7. 解析内层 ServerResponse
            ServerResponse serverResponse = objectMapper.readValue(kfptRes.getBody(), ServerResponse.class);
            ResponseContent content = serverResponse.getResponse();
            if (content != null && content.getError() != null) {
                // 失败：以 Error 下的 code / Message 填充
                ErrorInfo err = content.getError();
                log.warn("[乐企调用] 业务失败 errorCode={}, errorMessage={}", err.getCode(), err.getMessage());
                return ChannelResponse.of(err.getCode(), err.getMessage(), null);
            }

            // 8. 解密 data 并映射为业务响应 T
            String data = content != null ? content.getData() : null;
            String decData = SM4Util.decryptEcb(lqId, data);
            T bizData = objectMapper.readValue(decData, responseType);
            // 成功：以业务报文的 returncode / returnmsg 填充（默认取乐企成功码）
            String code = LeqiReturnCode.SUCCESS.getCode();
            String msg = LeqiReturnCode.SUCCESS.getDesc();
            if (bizData instanceof InvoiceResponse inv) {
                code = inv.getReturncode();
                msg = inv.getReturnmsg();
            }
            log.info("[乐企调用] 调用成功 returncode={}, returnmsg={}, 解密后业务报文={}", code, msg, decData);
            return ChannelResponse.of(code, msg, bizData);
        } catch (InvoiceException e) {
            throw e;
        } catch (Exception e) {
            throw new InvoiceException("乐企接口调用失败: " + apiCode, e);
        }
    }

    /** 按 content-encoding 解响应体：gzip 解压后读字符串，否则直接 UTF-8 解码。 */
    private String resolveBody(ResponseEntity<byte[]> responseEntity) throws IOException {
        List<String> encodings = responseEntity.getHeaders().getValuesAsList("content-encoding");
        if (encodings != null && encodings.contains("gzip") && responseEntity.getBody() != null) {
            try (GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(responseEntity.getBody()));
                 BufferedReader reader = new BufferedReader(new InputStreamReader(gzip, StandardCharsets.UTF_8));
                 StringWriter writer = new StringWriter()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                }
                return writer.toString();
            }
        }
        return new String(responseEntity.getBody(), StandardCharsets.UTF_8);
    }

    // ===== 乐企响应信封（文档：KfptRes / ServerResponse） =====

    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class KfptRes {
        private String httpStatusCode;
        private String body;

    }

    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ServerResponse {
        private ResponseContent response;

    }

    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ResponseContent {
        private ErrorInfo error;
        private String data;

    }

    /**
     * 业务失败明细：文档约定携带 code 与 Message。
     */
    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ErrorInfo {
        private String code;
        private String Message;

    }
}
