package leetcode;

/**
 * 47. 全排列 II [中等, 回溯算法]
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 * 输入: [1,1,2]
 * 输出:
 * [
 *  [1,1,2],
 *  [1,2,1],
 *  [2,1,1]
 * ]
 */
import java.util.*;
public class _47_Permutations_II {
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2};
        List<List<Integer>> result = permuteUnique(nums);
        for (List<Integer> list: result){
            for (Integer item: list){
                System.out.print(item + ",");
            }
            System.out.println();
        }
    }


    /**
     * 解题思路：问题可以看作有 n 个排列成一行的空格，需要从左往右依次填入题目给定的 n 个数，每个数只能使用一次。
     *  从左往右每一个位置都依此尝试填入一个数，直到填满 n 个空格
     * 1. 数组排序，将相同的值聚集在一起
     * 2. 定义递归函数，参数 idx 表示当前填入第几个数字, idx 从0开始
     *  2.1 若 idx == n 表示全部填入，递归结束
     *  2.2 遍历整个数组，依次插入当前位置，同时使用等长的 boolean 数组 visited 记录对应数字是否使用过，防止重复
     *      2.2.1 若当前数字已被使用, 跳过该数字。具体包括两种情况：
     *          1. 数字在当前位置已被使用 ==> visited[i] = true
     *          2. 数字在其他位置已被使用， 由于数组已排序，可以通过 与前一个数字相同，且其已被使用 判断
     *           ==> i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == true
     *     2.2.2 数字未被使用：
     *          1. 将其填如 idx 位置 ==> path[idx] = nums[i]
     *          2. 标记其已被使用 ==> visited[i] = true
     *          3. 递归填入第 idx + 1 位
     *          4. 回溯： 清除 idx 填入的内容 ==> del path[idx]
     *          5. 回溯： 清除使用标记 ==> visited[i] = false
     * */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int len = nums.length;
        if (0 == len){
            return result;
        }
        boolean[] visited = new boolean[len];
        Arrays.sort(nums);
        dfs(result, path, nums, visited, len, 0);
        return result;
    }

    /**
     * 递归向第 idx 位插入数据
     * */
    public static void dfs(List<List<Integer>> result, List<Integer> path, int[] nums, boolean[] visited, int len, int idx){
        // 递归出口： len 个空位全部插入
        if (len == idx){
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++){
            // 这个判断条件保证了对于重复数的集合，一定是从左往右逐个填入的
            if (visited[i] || (i > 0 && nums[i] == nums[i-1] && visited[i - 1])){
                continue;
            }
            visited[i] = true;
            path.add(nums[i]);

            dfs(result, path, nums, visited, len, idx + 1);

            path.remove(idx);
            visited[i] = false;
        }
    }
}
