package leetcode; /**
 * 39. 组合总和 [中等， 回溯算法]
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 *   
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 *   
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 *   
 * 示例 2：
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 *   
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 */

import java.util.*;

public class _39_CombinationSum {
    public static void main(String[] args) {
        int[] input = new int[]{2, 5, 3};
        List<List<Integer>> result = combinationSum(input, 8);
        for (List<Integer> item : result) {
            for (Integer i : item) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }

    /**
     * 解题思路：
     * 1. 先对数组排序， 以减少回溯次数
     * 2. 回溯 + 剪枝
     * */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> valid = new ArrayList<>();
        dfs(result, valid, candidates, target, 0);
        return result;
    }

    public static void dfs(List<List<Integer>> result, List<Integer> valid, int[] candidates, int target, int begin) {
        if (target == 0) {
            result.add(new ArrayList<>(valid));
            return;
        }

        // 遍历可能的搜索起点
        for (int i = begin; i < candidates.length; i++) {
            // 基于数组有序，减少无效递归
            if (target < candidates[i]) {
                break;
            }
            // 加入当前数
            valid.add(candidates[i]);
            // 注意此处与 70 题的不同，由于数字可重复使用，因此下一轮搜索还是从当前节点开始
            dfs(result, valid, candidates, target - candidates[i], i);
            // 不选择当前数， 寻找其他可能性
            valid.remove(valid.size() - 1);
        }
    }
}
