package leetcode;

/**
 * 263. 丑数 [简单， 数学]
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 *
 * 示例 1：
 * 输入：n = 6
 * 输出：true
 * 解释：6 = 2 × 3
 *
 * 示例 2：
 * 输入：n = 8
 * 输出：true
 * 解释：8 = 2 × 2 × 2
 *
 * 示例 3：
 * 输入：n = 14
 * 输出：false
 * 解释：14 不是丑数，因为它包含了另外一个质因数 7 。
 *
 * 示例 4：
 * 输入：n = 1
 * 输出：true
 * 解释：1 通常被视为丑数。
 *
 * 提示：
 * -2^31 <= n <= 2^31 - 1
 * */
public class _263_UglyNumber {
    public static void main(String[] args) {

    }

    /**
     * 解题思路：显然 0和负数 不属于丑数
     * 按照定义，不断对2、3 或 5取余
     * */
    public boolean isUgly(int n) {
        while(n > 1){
            if(n % 2 == 0){
                n /= 2;
            }
            else if(n % 3 == 0){
                n /= 3;
            }
            else if(n % 5 == 0){
                n /= 5;
            }else{
                return false;
            }
        }
        return n == 1;
    }
}
