package leetcode; /**
 * 51. N 皇后 [困难, 回溯算法]
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 示例：
 * 输入：4
 * 输出：[
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * <p>
 * 提示：
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 */

import java.util.*;

public class _51_NQueens {
    public static void main(String[] args) {
        List<List<String>> result = solveNQueens(4);
        for (List<String> item : result) {
            for (String str : item) {
                System.out.println(str);
            }
            System.out.println();
            System.out.println();
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        boolean[][] used = new boolean[n][n];
        List<List<String>> result = new ArrayList<>();
        dfs(result, used, n, 0);
        return result;
    }

    public static void addOne(List<List<String>> result, boolean[][] used, int n) {
        List<String> one = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuffer buffer = new StringBuffer();
            for (int j = 0; j < n; j++) {
                if (used[i][j]) {
                    buffer.append("Q");
                } else {
                    buffer.append(".");
                }
            }
            one.add(buffer.toString());
        }
        result.add(one);
    }

    public static void dfs(List<List<String>> result, boolean[][] used, int n, int row) {
        if (row == n) {
            addOne(result, used, n);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (valid(used, n, row, col)) {
                used[row][col] = true;
                dfs(result, used, n, row + 1);
                used[row][col] = false;
            }
        }
    }

    /**
     * 检测当前位置能否放置 棋子
     * */
    public static boolean valid(boolean[][] used, int n, int row, int col) {
        // 检测同一列是否已放置 棋子
        for (int i = row - 1; i >= 0; i--) {
            if (used[i][col]) return false;
        }
        // 检测左上方是否已放置 棋子
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (used[i][j]) return false;
        }
        // 检测右上方是否已放置 棋子
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (used[i][j]) return false;
        }
        return true;
    }
}
