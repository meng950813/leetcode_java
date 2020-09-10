/**
 * 77. 组合 [中等， 回溯算法]
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *  [2,4],
 *  [3,4],
 *  [2,3],
 *  [1,2],
 *  [1,3],
 *  [1,4],
 * ]
 */

import java.util.*;

public class _77_Combinations {
    public static void main(String[] args) {
        int n = 4, k = 2;
        List<List<Integer>> result = combine(n, k);
        for (List<Integer> item : result) {
            for (Integer i : item) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }

    /**
     * 解题思路：
     *  既然是树形问题上的 深度优先遍历，因此首先画出树形结构。例如输入：n = 4, k = 2，我们可以发现如下递归结构：
     *      1. 如果组合里有 1 ，那么需要在 [2, 3, 4] 里再找 1 个数；
     *      2. 如果组合里有 2 ，那么需要在 [3, 4] 里再找 1 数。注意：这里不能再考虑 1，因为包含 1 的组合，在第 1 种情况中已经包含。
     * */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> validRecord = new ArrayList<>();
        dfs(result, validRecord, 1, n, k);
        return result;
    }

    public static void dfs(List<List<Integer>> result, List<Integer> validRecord, int start, int n, int k) {
        // 递归终止条件是：还需要选择 0 个数
        if (k == 0) {
            result.add(new ArrayList<>(validRecord));
            return;
        }

        // 遍历可能的搜索起点
        // 搜索起点的上界 + 接下来要选择的元素个数 - 1 = n
        // 假设 n=k=4, 则 i = 2 时， 最多只会 2，3，4， 不符合要求，因此后续不需要运算
        for (int i = start; i + k - 1 <= n; i++) {
            // 添加当前数
            validRecord.add(i);
            // 下一轮搜索，设置的搜索起点加 1，因为组合数理不允许出现重复的元素
            dfs(result, validRecord, i + 1, n, k - 1);
            // 移除当前数， 表示 不选择当前数， 进而寻找其他可能性
            validRecord.remove(validRecord.size() - 1);
        }
    }
}
