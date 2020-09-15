package leetcode;

/**
 * 415. 字符串相加 （简单）
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 注意：
 *
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 * */
public class _415_AddStrings {
    public static void main(String[] args) {
        String result = addStrings("98", "9");
        System.out.println(result);
    }
    public static String addStrings(String num1, String num2) {
        int ten = 0;
        int len1 = num1.length(), len2 = num2.length();
        int max_len = len1 > len2? len1 + 1: len2 + 1;
        char []result = new char[max_len];
        while(len1 > 0 && len2 > 0){
            ten = charSum(result, --max_len, ten, num1.charAt(--len1), num2.charAt(--len2));
        }

        while (len1 > 0){
            ten = charSum(result, --max_len, ten, num1.charAt(--len1),'0');
        }
        while (len2 > 0){
            ten = charSum(result, --max_len, ten, '0', num2.charAt(--len2));
        }

        if(ten == 1){
            result[--max_len] = '1';
        }else{
            result[max_len - 1] = '0';
        }

        return String.valueOf(result).substring(max_len);
    }

    public static int charSum(char[] result, int index, int ten, char num1, char num2){
        int sum = ten + (num1 - 48) + (num2 - 48);
        result[index] = (char)(sum % 10 + 48);
        return sum / 10;
    }
}
