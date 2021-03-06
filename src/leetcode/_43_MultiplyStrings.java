package leetcode;

/**
 * 43. 字符串相乘 [中等]
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *   
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *   
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *   
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */

public class _43_MultiplyStrings {
    public static void main(String[] args) {
        String result = multiply("12", "12");
        System.out.println(result);
    }

    /**
     * 解题思路： 暴力竖式计算
     */
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String result = "0";
        int num1_length = num1.length(), num2_length = num2.length();

        for (int i = num2_length - 1; i >= 0; i--) {
            StringBuffer buffer = new StringBuffer();
            int ten = 0;
            for (int j = num2_length - 1; j > i; j--) {
                buffer.append(0);
            }
            int n2 = num2.charAt(i) - '0';
            for (int j = num1_length - 1; j >= 0; j--) {
                int multip = (num1.charAt(j) - '0') * n2 + ten;
                buffer.append(multip % 10);
                ten = multip / 10;
            }
            if (ten != 0) {
                buffer.append(ten);
            }
            result = add(result, buffer.reverse().toString());
        }

        return result;
    }


    public static String add(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer result = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = x + y + add;
            result.append(sum % 10);
            add = sum / 10;
            i--;
            j--;
        }
        return result.reverse().toString();
    }
}
