package com.luoge.ns.invoice.banstyle.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金额转中文大写（人民币）。规范 §1.2.23 规定：负数发票在票面符号与价税合计大写之间填充带括号的
 * 「（负数）」字样，故本工具仅对金额绝对值转换（如「壹佰叁拾圆整」），负数前缀由调用方拼接。
 */
public final class AmountToChinese {

    private static final String[] NUM = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static final String[] UNIT = {"", "拾", "佰", "仟"};
    private static final String[] GUNIT = {"", "万", "亿", "兆"};

    private AmountToChinese() {
    }

    public static String toChinese(BigDecimal amount) {
        if (amount == null) {
            return "";
        }
        BigDecimal v = amount.setScale(2, RoundingMode.HALF_UP).abs();
        long totalFen = v.multiply(BigDecimal.valueOf(100)).longValue();
        long yuan = totalFen / 100;
        int jiao = (int) ((totalFen % 100) / 10);
        int fen = (int) (totalFen % 10);

        if (yuan == 0 && jiao == 0 && fen == 0) {
            return "零圆整";
        }

        StringBuilder sb = new StringBuilder();
        if (yuan > 0) {
            sb.append(integerToChinese(yuan)).append("圆");
        }
        if (jiao == 0 && fen == 0) {
            sb.append("整");
        } else {
            if (jiao == 0) {
                sb.append("零");
            } else {
                sb.append(NUM[jiao]).append("角");
            }
            if (fen > 0) {
                sb.append(NUM[fen]).append("分");
            }
        }
        return sb.toString();
    }

    private static String integerToChinese(long n) {
        if (n == 0) {
            return "零";
        }
        String s = Long.toString(n);
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        boolean hasContent = false;
        boolean zeroInGroup = false;
        boolean groupHasContent = false;
        for (int i = 0; i < len; i++) {
            int digit = s.charAt(i) - '0';
            int posFromRight = len - 1 - i;
            int unitPos = posFromRight % 4;
            int g = posFromRight / 4;
            if (digit == 0) {
                zeroInGroup = true;
            } else {
                if (zeroInGroup && hasContent) {
                    sb.append("零");
                }
                sb.append(NUM[digit]).append(UNIT[unitPos]);
                zeroInGroup = false;
                hasContent = true;
                groupHasContent = true;
            }
            if (unitPos == 0) {
                if (g > 0 && groupHasContent) {
                    sb.append(GUNIT[g]);
                }
                groupHasContent = false;
            }
        }
        return sb.toString();
    }
}
