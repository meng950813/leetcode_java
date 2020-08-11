import java.util.Stack;

public class _32_LongestValidParentheses {
    public static void main(String[] args) {
        int result = longestValidParentheses(")()()((()()))");
        System.out.println(result);
    }


    /**
     * 使用动态规划思想
     * 创建 一个长度为 字符串长度的空数组dp，dp[i]记录 到第 i 位的最大有效长度，
     * 假设s = "()(()())" 最终 dp = [0, 2, 0, 0, 2, 0, 4, 0, 8]
     * 首先，若当前位置的两个字符能构成有效括号，如 "()" dp=[0, n, 0, ?], 则到当前位置的最大有效长度 = 截至到前两位的最大有效长度 + 2
     * 即  dp[i] = 2 + dp[i - 2] = 2+ n
     * 若 当前位置的两个字符构不成有效括号， 需要考虑 括号嵌套的 情况 "()(())"， 此时 dp=[0, n, 0, 0, 2, ?]
     * 此时需要先计算 嵌套括号内的有效长度， 再加上相邻的有效长度：
     *  ==> s[i] == ")" && s[i- dp[i-1] - 1] == "(" ==> dp[i] = 2 + dp[i-1] + n
     *
     * */
    public static int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int maxLen = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                continue;
            } else {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = i >= 2 ? dp[i - 2] + 2 : 2;
                } else {
                    int j = i - dp[i - 1] - 1;
                    if (j >= 0 && s.charAt(j) == '(') {
                        dp[i] = dp[i - 1] + 2;
                        if (j >= 1 && dp[j - 1] > 0) {
                            dp[i] += dp[j - 1];
                        }
                    }
                }
                maxLen = dp[i] > maxLen ? dp[i] : maxLen;
            }
        }
        return maxLen;
    }
}
