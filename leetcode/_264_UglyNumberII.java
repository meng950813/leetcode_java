package leetcode;


/**
 * 264. 丑数 II [中等，哈希表，动态规划，数学，堆（优先队列）]
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 *
 * 示例 1：
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 *  
 * 提示：
 * 1 <= n <= 1690
 * */
public class _264_UglyNumberII {
    public static void main(String[] args) {
        _264_UglyNumberII obj = new _264_UglyNumberII();
        int n = 10;
        System.out.println(obj.nthUglyNumber(n));
    }
    /**
     * 解题思路： 动态规划
     * 定义数组 dp，其中 dp[i] 表示第 i 个丑数，第 n 个丑数即为 dp[n]。
     * 由于最小的丑数是 1，因此 dp[1]=1。如何得到其余的丑数呢？
     * 定义三个指针 p_2,p_3,p_5，表示下一个丑数是当前指针指向的丑数乘以对应的质因数。初始时，三个指针的值都是 1。
     *
     * 当 2 ≤ i ≤ n 时，令 dp[i]=min(dp[p_2]*2, dp[p_3]*3, dp[p_5]*5)，
     * 然后分别比较 dp[i] 和 dp[p_2] * 2, dp[p_3] * 3, dp[p_5] * 5是否相等，如果相等则将对应的指针加 1。
     * */
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for(int i = 2; i <= n; i++){
            int r2 = dp[p2] * 2, r3 = dp[p3] * 3, r5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(r2, r3), r5);
            if (dp[i] == r2){
                p2++;
            }
            if (dp[i] == r3){
                p3++;
            }
            if(dp[i] == r5){
                p5++;
            }
        }
        return  dp[n];
    }
}
