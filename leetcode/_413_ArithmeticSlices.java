package leetcode;

import java.util.Arrays;

/**
 * 413. 等差数列划分 [中等，动态规划]
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 * 子数组 是数组中的一个连续序列。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：3
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：0
 *  
 * 提示：
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 *
 * */
public class _413_ArithmeticSlices {

    public static void main(String[] args) {
        int[] nums = new int[5000];
        Arrays.fill(nums, 1);
        _413_ArithmeticSlices obj = new _413_ArithmeticSlices();
        assert obj.numberOfArithmeticSlices(nums) == 12492501;
    }

    /**
     * 解题思路1： 暴力法，双层遍历，最坏时间复杂度，O(n^2)
     * */
    public int numberOfArithmeticSlices(int[] nums) {
        int len = nums.length;
        int res = 0;
        for(int i = 2; i < len; i++){
            int distance = nums[i-1] - nums[i-2];
            for(int j = i; j < len; j++){
                if(distance == nums[j] - nums[j - 1]){
                    res++;
                }else{
                    break;
                }
            }
        }
        return res;
    }
}
