package leetcode; /**
 * 216. 组合总和 III [中等，回溯算法]
 *
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 *
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * */
import java.util.*;
public class _216_CombinationSumIII {
    public static void main(String[] args) {
        int n = 9, k = 3;
        List<List<Integer>> result = combinationSum3(k, n);
        for (List<Integer> item : result) {
            for (Integer i : item) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> valid = new ArrayList<>();
        dfs(result, valid, k, n, 1);
        return result;
    }

    public static void dfs(List<List<Integer>> result, List<Integer> valid, int k, int n, int start){
        // 递归出口
        if (k == 0 && n == 0){
            result.add(new ArrayList<>(valid));
            return;
        }
        // 剪枝：
        // 1. 若后续数字不足 k 个， 结束循环，可参考 题39
        // 2. 由于数字有序添加， 若当前数字 大于 n，则 后续不会有小于 n的数，可提前结束递归
        // 3. 由于此处的判断条件，递归出口处 可以 不判断 n < 0 的情况
        for (int i= start; i + k - 1 <= 9 && n >= i; i++){
            valid.add(i);
            dfs(result, valid, k - 1, n - i, i + 1);
            valid.remove(valid.size() - 1);
        }
    }
}
