package leetcode; /**
 * 377. 组合总和Ⅳ [中等， 动态规划]
 * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
 *   
 * 示例:
 * nums = [1, 2, 3]
 * target = 4
 *   
 * 所有可能的组合为：
 *      (1, 1, 1, 1)
 *      (1, 1, 2)
 *      (1, 2, 1)
 *      (1, 3)
 *      (2, 1, 1)
 *      (2, 2)
 *      (3, 1)
 *   
 * 请注意，顺序不同的序列被视作不同的组合。 因此输出为 7。
 * 进阶：
 *  1. 如果给定的数组中含有负数会怎么样？
 *  2. 问题会产生什么变化？
 *  3. 我们需要在题目中添加什么限制来允许负数的出现？
 */

import java.util.*;
public class _377_CombinationSumIV {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        int target = 4;
        System.out.println(combinationSum4(nums, target));
    }

    /**
     * 解题思路：动态规划方法
     * 状态转移方程：dp[i]= dp[i - nums[0]] + dp[i - nums[1]] + dp[i - nums[2]] + ... （当 [] 里面的数 >= 0）
     * 特别注意：dp[0] = 1，表示刚好凑够一种选择，这个就成为 1 种组合方案
     * 再举一个具体的例子：nums=[1, 3, 4], target=7;
     * dp[7] = dp[6] + dp[4] + dp[3]
     * 即：7 的组合数可以由三部分组成，1 和 dp[6]，3 和 dp[4], 4 和dp[3];
     */
    public static int combinationSum4(int[] nums, int target) {
        int[] dp =new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i < target + 1; ++i){
            for (int num: nums){
                if (i >= num){
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}
