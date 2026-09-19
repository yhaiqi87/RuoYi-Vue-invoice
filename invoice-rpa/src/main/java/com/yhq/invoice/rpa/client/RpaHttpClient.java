package com.yhq.invoice.rpa.client;

import com.yhq.invoice.common.client.InvoiceHttpClient;
import com.yhq.invoice.common.dto.GetBatchPreCodeReq;
import com.yhq.invoice.common.dto.GetBatchPreCodeRes;
import com.yhq.invoice.common.dto.QueryQuotaRes;
import com.yhq.invoice.common.exception.InvoiceException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * RPA 渠道对外 HTTP 调用 Stub 实现：当前返回占位响应，后续替换为真实 RPA 流程客户端。
 * 仅对两个已落地的 demo 接口（批量预赋码、查询额度）返回示例数据，其余接口返回空响应占位。
 * 为与 leqi 区分，返回的示例数据带有 RPA 标识。
 */
@Component
public class RpaHttpClient implements InvoiceHttpClient {

    @Override
    public <T> T call(String apiCode, Object request, Class<T> responseType) {
        if (responseType.equals(GetBatchPreCodeRes.class)) {
            GetBatchPreCodeRes res = new GetBatchPreCodeRes();
            res.setReturncode("00");
            res.setReturnmsg("成功(RPA stub)");
            res.setFpqshm("9000000001");
            res.setFpzzhm("9000000500");
            if (request instanceof GetBatchPreCodeReq r && r.getLysl() != null) {
                res.setLysl(r.getLysl());
            }
            return responseType.cast(res);
        }
        if (responseType.equals(QueryQuotaRes.class)) {
            QueryQuotaRes res = new QueryQuotaRes();
            res.setReturncode("00");
            res.setReturnmsg("成功(RPA stub)");
            res.setZtsxbz("N");
            res.setBysxed(new BigDecimal("1000000.00"));
            res.setKysyed(new BigDecimal("800000.00"));
            res.setYxzed(new BigDecimal("200000.00"));
            res.setYxzwsyed(new BigDecimal("150000.00"));
            res.setSq("202609");
            return responseType.cast(res);
        }
        // 其余接口暂未实现，返回空响应占位
        try {
            return responseType.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new InvoiceException("stub 无法构造响应: " + responseType.getName(), e);
        }
    }
}
