package leetcode;

/**
 * 313. 超级丑数 [中等，哈希表，动态规划，数学，堆（优先队列）]
 * 超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
 * 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
 * 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
 *
 * 示例 1：
 * 输入：n = 12, primes = [2,7,13,19]
 * 输出：32
 * 解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
 *
 * 示例 2：
 * 输入：n = 1, primes = [2,3,5]
 * 输出：1
 * 解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
 *  
 * 提示：
 * 1 <= n <= 106
 * 1 <= primes.length <= 100
 * 2 <= primes[i] <= 1000
 * 题目数据 保证 primes[i] 是一个质数
 * primes 中的所有值都 互不相同 ，且按 递增顺序 排列
 * */
public class _313_SuperUglyNumber {
    public static void main(String[] args) {
        _313_SuperUglyNumber obj = new _313_SuperUglyNumber();
        int[] primes = {2,7,13,19};
        int n = 12;
        System.out.println(obj.nthSuperUglyNumber(n, primes));
    }

    /**
     * 解题思路： 动态规划
     * dp 思路和丑数题一样，就是把丑数题的3、5、7三指针变成N指针，用一个数组存储指针。
     * */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n];
        dp[0] = 1;
        int len = primes.length;
        int[] index = new int[len];

        for(int i = 1; i < n; i++){
            int minNum = Integer.MAX_VALUE;
            for (int j = 0; j < len; j++){
                minNum = Math.min(minNum, dp[index[j]] * primes[j]);
            }
            for (int j = 0; j < len; j++){
                if(minNum == dp[index[j]] * primes[j]){
                    index[j]++;
                }
            }
            dp[i] = minNum;
        }
        return dp[n - 1];
    }
}
