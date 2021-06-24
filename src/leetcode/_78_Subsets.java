package src.leetcode;

/**
 * 78. 子集 [中等， 回溯算法]
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * */

import java.util.*;
public class _78_Subsets {
    public static void main(String[] args) {

    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = null;

        result.add(new ArrayList<>());
        int len = nums.length, i = 0, j = 0;
        for (i = 0; i < len; i++){
            path = new ArrayList<>();
            for (j = i; j < len; j++){
                path.add(nums[j]);
                result.add(new ArrayList<>(path));
            }
        }
        return result;
    }
}
