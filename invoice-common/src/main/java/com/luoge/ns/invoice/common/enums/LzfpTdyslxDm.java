package com.luoge.ns.invoice.common.enums;

/**
 * 蓝字发票特定要素类型代码（lzfpTdyslxDm）：取值范围 "01"~"18"、"20"（选填）。
 * 仅 "05" 在文档中有明确名称（不动产销售服务发票），其余仅文档枚举了编码范围。
 * 见 {@code ApplyRedConfirmReq#lzfpTdyslxDm}，依据基础版 V6.006 第 3.1.2.3.1 节合规性校验。
 */
public enum LzfpTdyslxDm {
    T01("01", "特定要素类型01"),
    T02("02", "特定要素类型02"),
    T03("03", "特定要素类型03"),
    T04("04", "特定要素类型04"),
    BUILDING_SALE("05", "不动产销售服务发票"),
    T06("06", "特定要素类型06"),
    T07("07", "特定要素类型07"),
    T08("08", "特定要素类型08"),
    T09("09", "特定要素类型09"),
    T10("10", "特定要素类型10"),
    T11("11", "特定要素类型11"),
    T12("12", "特定要素类型12"),
    T13("13", "特定要素类型13"),
    T14("14", "特定要素类型14"),
    T15("15", "特定要素类型15"),
    T16("16", "特定要素类型16"),
    T17("17", "特定要素类型17"),
    T18("18", "特定要素类型18"),
    T20("20", "特定要素类型20");

    private final String code;
    private final String desc;

    LzfpTdyslxDm(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
