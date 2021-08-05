package leetcode; /**
 * 40. 组合总和 II [中等，回溯算法]
 * <p>
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * <p>
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * <p>
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 */

import java.util.*;

public class _40_CombinationSumII {
    public static void main(String[] args) {
        int[] candidates = new int[]{10,1,2,2,7,6,1,5};
//        int[] candidates = new int[]{2, 5, 2, 1, 2};
        int target = 8;
        List<List<Integer>> result = combinationSum2(candidates, target);
        for (List<Integer> item : result) {
            for (Integer num : item) {
                System.out.print(num + ", ");
            }
            System.out.println();
        }
    }

    /**
     * 解题思路：利用回溯法计算所有可能性，题目要求 数字不可重用，组合不能重复，因此需要对同样值的数字进行去重
     * 1. 对数组排序，能减少递归次数，以及方便去重
     * 2.
     * */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 先对数组排序，
        Arrays.sort(candidates);
        List<List<Integer>> reuslt = new ArrayList<>();
        List<Integer> valid = new ArrayList<>();
        fun(reuslt, valid, candidates, target, 0);
        return reuslt;
    }

    public static void fun(List<List<Integer>> reuslt, List<Integer> valid, int[] candidates, int target, int start) {
        if (target == 0) {
            reuslt.add(new ArrayList<>(valid));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 利用数组有序的特性，减少递归次数
            if (target < candidates[i]) {
                break;
            }
            // 若当前位置与 前一位 数值相同，且 已运算过加入 candidates[i - 1] 的情况，则 candidates[i] 不再重复计算
            // 通过 i > start 可得出  candidates[i - 1] 在 valid　中，比如选了2，使得部分解为 [1,2]，下一选项也是 2，跳过它，因为选它，就也是 [1,2]。
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            valid.add(candidates[i]);
            fun(reuslt, valid, candidates, target - candidates[i], i + 1);
            valid.remove(valid.size() - 1);
        }
    }
}
