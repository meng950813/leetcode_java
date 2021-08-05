package leetcode;

import java.util.*;

/**
 * 491. 递增子序列 [中等, 深度优先遍历]
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 *
 * 示例:
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 *
 * 说明:
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是[-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 */
public class _491_IncreasingSubsequences {

    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    private List<Integer> temp = new ArrayList<>();

    public static void main(String[] args) {
        _491_IncreasingSubsequences obj = new _491_IncreasingSubsequences();
        int[] nums = new int[]{4,6,7,4,1,7};
        List<List<Integer>> result = obj.findSubsequences(nums);
        for (List<Integer> one : result){
            for (int num: one){
                System.out.print(num + ",");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums == null) {
            return null;
        }
        dfs(0, Integer.MIN_VALUE, nums);
        return result;
    }

    /**
     * 解题思路：递归（深度遍历） + 剪枝
     * */
    private void dfs(int curIndex, int preValue, int[] nums) {
        if (curIndex >= nums.length) {  // 遍历结束
            if (temp.size() >= 2) {
                result.add(new ArrayList<>(temp));
            }
            return;
        }

        if (nums[curIndex] >= preValue) {   // 将当前元素加入，并向后遍历
            temp.add(nums[curIndex]);
            dfs(curIndex + 1, nums[curIndex], nums);
            temp.remove(temp.size() - 1);
        }
        if (nums[curIndex] != preValue) {   // 不遍历 重复元素
            dfs(curIndex + 1, preValue, nums);  // 将下一个元素加入，并向后遍历
        }
    }

}


