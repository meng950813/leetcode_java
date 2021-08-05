package leetcode;

/**
 * 52. N皇后 II [困难， 回溯算法]
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 *  
 * 示例:
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 *  
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 *  
 * 提示：
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N-1 步，可进可退。（引用自 百度百科 - 皇后 ）
 */
public class _52_NQueensII {
    public static void main(String[] args) {
        _52_NQueensII obj = new _52_NQueensII();
        System.out.println(obj.totalNQueens(4));
    }

    int result = 0;

    public int totalNQueens(int n) {
        boolean[][] used = new boolean[n][n];
        dfs(used, n, 0);
        return result;
    }

    public void dfs(boolean[][] used, int n, int row) {
        if (row == n) {
            result++;
            return;
        }
        for (int j = 0; j < n; j++) {
            if (checkValid(used, n, row, j)){
                used[row][j] = true;
                dfs(used,n, row + 1);
                used[row][j] = false;
            }
        }
    }

    public boolean checkValid(boolean[][] used, int n, int x, int y) {
        for (int i = x - 1, j = 1; i >= 0; i--, j++) {
            // 正上方
            if (used[i][y]) {
                return false;
            }
            // 左上方
            if (y - j >= 0 && used[i][y-j]){
                return false;
            }
            // 右上方
            if (y + j <n && used[i][y+j]){
                return false;
            }
        }
        return true;
    }
}
