package leetcode;

/**
 * 46. 全排列 [中等，回溯算法]
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 *  [1,2,3],
 *  [1,3,2],
 *  [2,1,3],
 *  [2,3,1],
 *  [3,1,2],
 *  [3,2,1]
 * ]
 */
import java.util.*;
public class _46_Permutations {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> result = permute(nums);
        for (List<Integer> list: result){
            for (Integer item : list){
                System.out.print(item + ",");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if (0 == len){
            return result;
        }
        solution2(result, nums, len, 0);
        return result;
    }

    public static void solution2(List<List<Integer>> result, int[] nums, int len, int index){
        if (index == len){
            List<Integer> list = new ArrayList<>();
            for (int i= 0; i < len; i++){
                list.add(i);
            }
            result.add(list);
            return;
        }
        for (int i = index; i < len; i++){
            swap(nums, index, i);
            solution2(result, nums, len, index + 1);
            swap(nums, index, i);
        }
    }

    public static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 解题思路1：回溯法
     * */
    public static void dfs(List<List<Integer>> result, Deque<Integer> path, int[] nums, boolean[] used, int len, int count){
        if (count == len){
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++){
            if (!used[i]){
                used[i] = true;
                path.push(nums[i]);
                dfs(result, path, nums, used, len, count + 1);

                path.pop();
                used[i] = false;
            }
        }
    }
}
