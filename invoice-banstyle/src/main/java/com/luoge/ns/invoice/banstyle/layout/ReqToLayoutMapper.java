package com.luoge.ns.invoice.banstyle.layout;

import com.luoge.ns.invoice.banstyle.util.AmountToChinese;
import com.luoge.ns.invoice.common.dto.BaseUploadInvoiceReq;
import com.luoge.ns.invoice.common.dto.BaseUploadInvoiceReq.FpmxItem;
import com.luoge.ns.invoice.common.enums.Fppz;
import com.luoge.ns.invoice.common.enums.Lzfpbz;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 将乐企基础版「发票上传」请求 {@link BaseUploadInvoiceReq} 映射为渲染用的归一化数据 {@link InvoiceLayoutData}。
 * 负责所有「格式化 / 编码转换 / 展示规则」逻辑（规范 §1.2、§1.5、§1.6）。
 */
public final class ReqToLayoutMapper {

    private static final DateTimeFormatter SRC_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DST_FMT = DateTimeFormatter.ofPattern("yyyy 年 MM 月 dd 日");

    /**
     * 差额征税类型代码 → 左上角标签文字（规范 §1.5）。
     */
    private static final Map<String, String> CEZS_LABEL = Map.of(
            "01", "差额征税-差额开票",
            "02", "差额征税-全额开票");

    /**
     * 区域代码前两位 → 省份名（用于监制章下环；不完善时可扩展）。
     */
    private static final Map<String, String> QYDM_PROVINCE = buildProvinceMap();

    private ReqToLayoutMapper() {
    }

    public static InvoiceLayoutData map(BaseUploadInvoiceReq req) {
        InvoiceLayoutData d = new InvoiceLayoutData();

        // 票头：专票/普票
        String fppz = req.getFppz();
        if (Fppz.SPECIAL.getCode().equals(fppz)) {
            d.setInvoiceTitle("电子发票（增值税专用发票）");
        } else {
            d.setInvoiceTitle("电子发票（普通发票）");
        }

        d.setInvoiceNo(req.getFphm());
        d.setIssueDate(formatDate(req.getKprq()));
        boolean red = Lzfpbz.RED.getCode().equals(req.getLzfpbz());
        d.setRedLetter(red);
        d.setTaxBureauName(resolveTaxBureau(req.getQyDm()));

        // 差额征税标签
        String label = req.getCezslxDm() != null ? CEZS_LABEL.get(req.getCezslxDm()) : null;
        boolean diffDeduction = "差额征税-差额开票".equals(label);
        d.setLabelText(label);

        // 购销方
        d.setSellerName(req.getXsfmc());
        d.setSellerTaxId(req.getXsfnsrsbh());
        d.setBuyerName(req.getGmfmc());
        d.setBuyerTaxId(req.getGmfnsrsbh());

        d.setShowSellerContact("Y".equalsIgnoreCase(req.getSfzsxsfdzdh()));
        d.setShowSellerBank("Y".equalsIgnoreCase(req.getSfzsxsfyhzhbq()));
        d.setShowBuyerContact("Y".equalsIgnoreCase(req.getSfzsgmfdzdh()));
        d.setShowBuyerBank("Y".equalsIgnoreCase(req.getSfzsgmfyhzhbq()));
        d.setSellerAddr(req.getXsfdz());
        d.setSellerTel(req.getXsfdh());
        d.setSellerBank(req.getXsfkhh());
        d.setSellerAccount(req.getXsfzh());
        d.setBuyerAddr(req.getGmfdz());
        d.setBuyerTel(req.getGmfdh());
        d.setBuyerBank(req.getGmfkhh());
        d.setBuyerAccount(req.getGmfzh());

        // 明细
        if (req.getFpmxList() != null) {
            for (FpmxItem it : req.getFpmxList()) {
                d.getDetails().add(new InvoiceLayoutData.DetailRow(
                        it.getXmmc(),
                        it.getGgxh(),
                        it.getDw(),
                        it.getSl(),
                        it.getDj(),
                        money(it.getJe()),
                        formatTaxRate(it.getSlv(), it.getYhzcbs(), diffDeduction),
                        formatTax(it.getSe(), it.getYhzcbs())));
            }
        }

        // 合计
        d.setTotalAmount("¥" + money(req.getHjje()));
        d.setTotalTax("¥" + money(req.getHjse()));

        // 价税合计大写 / 小写
        String upper = AmountToChinese.toChinese(req.getJshj());
        d.setTotalAmountUpper(red ? "（负数）" + upper : upper);
        d.setTotalAmountLower("¥" + (req.getJshj() == null ? "" : req.getJshj()
                .setScale(2, RoundingMode.HALF_UP).toPlainString()));

        // 备注（§1.6）
        d.setRemark(buildRemark(req, red));

        d.setDrawer(req.getKpr());
        return d;
    }

    // ===== 格式化助手 =====

    static String formatDate(String kprq) {
        if (kprq == null || kprq.isBlank()) {
            return "";
        }
        try {
            return LocalDateTime.parse(kprq.trim(), SRC_FMT).format(DST_FMT);
        } catch (Exception e) {
            return kprq;
        }
    }

    static String money(BigDecimal v) {
        if (v == null) {
            return "";
        }
        return v.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

    /**
     * 税率/征收率展示（§1.5 特殊规则）。
     */
    static String formatTaxRate(BigDecimal slv, String yhzcbs, boolean diffDeduction) {
        if ("04".equals(yhzcbs)) {
            return "不征税";
        }
        if ("03".equals(yhzcbs)) {
            return "免税";
        }
        if (diffDeduction) {
            return "***";
        }
        if (slv == null) {
            return "";
        }
        if (slv.signum() == 0) {
            return "0%";
        }
        return slv.multiply(BigDecimal.valueOf(100)).stripTrailingZeros().toPlainString() + "%";
    }

    /**
     * 税额展示（§1.5 特殊规则）。
     */
    static String formatTax(BigDecimal se, String yhzcbs) {
        if ("04".equals(yhzcbs)) {
            return "";
        }
        if ("03".equals(yhzcbs)) {
            return "***";
        }
        return money(se);
    }

    static String resolveTaxBureau(String qyDm) {
        if (qyDm != null && qyDm.length() >= 2) {
            String prov = QYDM_PROVINCE.get(qyDm.substring(0, 2));
            if (prov != null) {
                return "国家税务总局" + prov + "税务局";
            }
        }
        return "国家税务总局";
    }

    // ===== 备注拼接（§1.6）=====

    private static String buildRemark(BaseUploadInvoiceReq req, boolean red) {
        List<String> inline = new ArrayList<>();   // 行内 token，用 ";" + 4空格 连接
        List<String> lines = new ArrayList<>();     // 独立成行的块

        if (red && req.getDylzfphm() != null) {
            inline.add("被红冲发票号码：" + req.getDylzfphm());
        }
        // 差额征税扣除额（中文"。"结尾）
        if (req.getCezslxDm() != null && req.getCekcList() != null && !req.getCekcList().isEmpty()) {
            BigDecimal kce = req.getCekcList().stream()
                    .map(c -> c.getBckcje() == null ? BigDecimal.ZERO : c.getBckcje())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            inline.add("扣除额：" + kce.setScale(2, RoundingMode.HALF_UP).toPlainString() + "元。");
        }
        if (req.getTdys() != null && !req.getTdys().isBlank()) {
            inline.add("特定要素：" + req.getTdys());
        }
        // 煤炭种类（合并去重，按首次出现顺序）
        if (req.getFpmxList() != null) {
            LinkedHashMap<String, Boolean> mt = new LinkedHashMap<>();
            for (FpmxItem it : req.getFpmxList()) {
                if (it.getMtzl() != null && !it.getMtzl().isBlank()) {
                    mt.putIfAbsent(it.getMtzl(), Boolean.TRUE);
                }
            }
            if (!mt.isEmpty()) {
                inline.add("煤炭种类：" + String.join("; ", mt.keySet()));
            }
        }

        // 购方 / 销方 地址电话银行账号（各自独立成行）
        String buyerLine = contactLine("购方", req.getGmfdz(), req.getGmfdh(),
                req.getGmfkhh(), req.getGmfzh(), req.getSfzsgmfdzdh(), req.getSfzsgmfyhzhbq());
        if (buyerLine != null) {
            lines.add(buyerLine);
        }
        String sellerLine = contactLine("销方", req.getXsfdz(), req.getXsfdh(),
                req.getXsfkhh(), req.getXsfzh(), req.getSfzsxsfdzdh(), req.getSfzsxsfyhzhbq());
        if (sellerLine != null) {
            lines.add(sellerLine);
        }

        // 附加要素
        if (req.getFjysList() != null) {
            for (var fj : req.getFjysList()) {
                if (fj.getFjysmc() != null) {
                    inline.add(fj.getFjysmc() + (fj.getFjysz() != null ? "：" + fj.getFjysz() : ""));
                }
            }
        }
        // 煤炭品质
        if (req.getMtpzxx() != null && !req.getMtpzxx().isBlank()) {
            inline.add("煤炭品质信息：" + req.getMtpzxx());
        }
        // 自定义备注
        if (req.getBz() != null && !req.getBz().isBlank()) {
            inline.add(req.getBz());
        }

        // 收款人 / 复核人（独立成行）
        List<String> pay = new ArrayList<>();
        if (req.getSkrxm() != null && !req.getSkrxm().isBlank()) {
            pay.add("收款人:" + req.getSkrxm() + ";");
        }
        if (req.getFhrxm() != null && !req.getFhrxm().isBlank()) {
            pay.add("复核人:" + req.getFhrxm() + ";");
        }
        if (!pay.isEmpty()) {
            lines.add(String.join(" ", pay));
        }

        // 支付渠道 / 交易单号（末两行，各自独立成行）
        if (req.getZfxxList() != null) {
            for (var zf : req.getZfxxList()) {
                if (zf.getZfqdDm() != null && !zf.getZfqdDm().isBlank()) {
                    lines.add("支付渠道:" + zf.getZfqdDm() + ";");
                }
                if (zf.getJydh() != null && !zf.getJydh().isBlank()) {
                    lines.add("交易单号:" + zf.getJydh() + ";");
                }
            }
        }

        String joinInline = String.join(";    ", inline); // ";" + 大 tab(4空格)
        List<String> all = new ArrayList<>();
        if (!joinInline.isBlank()) {
            all.add(joinInline);
        }
        all.addAll(lines);
        return String.join("\n", all);
    }

    private static String contactLine(String prefix, String addr, String tel,
                                      String bank, String account, String showContact, String showBank) {
        boolean sc = "Y".equalsIgnoreCase(showContact);
        boolean bankFlag = "Y".equalsIgnoreCase(showBank);
        if (!sc && !bankFlag) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (sc) {
            sb.append(prefix).append("地址:").append(nullToEmpty(addr))
                    .append("; 电话:").append(nullToEmpty(tel)).append(";");
        }
        if (bankFlag) {
            sb.append(prefix).append("开户银行:").append(nullToEmpty(bank))
                    .append("; 银行账号:").append(nullToEmpty(account)).append(";");
        }
        return sb.toString();
    }

    private static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }

    private static Map<String, String> buildProvinceMap() {
        Map<String, String> m = new LinkedHashMap<>();
        m.put("11", "北京市");
        m.put("12", "天津市");
        m.put("13", "河北省");
        m.put("14", "山西省");
        m.put("15", "内蒙古自治区");
        m.put("21", "辽宁省");
        m.put("22", "吉林省");
        m.put("23", "黑龙江省");
        m.put("31", "上海市");
        m.put("32", "江苏省");
        m.put("33", "浙江省");
        m.put("34", "安徽省");
        m.put("35", "福建省");
        m.put("36", "江西省");
        m.put("37", "山东省");
        m.put("41", "河南省");
        m.put("42", "湖北省");
        m.put("43", "湖南省");
        m.put("44", "广东省");
        m.put("45", "广西壮族自治区");
        m.put("46", "海南省");
        m.put("50", "重庆市");
        m.put("51", "四川省");
        m.put("52", "贵州省");
        m.put("53", "云南省");
        m.put("54", "西藏自治区");
        m.put("61", "陕西省");
        m.put("62", "甘肃省");
        m.put("63", "青海省");
        m.put("64", "宁夏回族自治区");
        m.put("65", "新疆维吾尔自治区");
        return m;
    }
}
