package leetcode;

/**
 * 37. 解数独 [困难, 回溯算法]
 * 
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * 一个数独的解法需遵循如下规则：
 * 
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 * 
 * Note:
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 */

import java.util.*;

public class _37_SudokuSolver {
    public static void main(String[] args) {
        char[][] board = new char[][]{ 
                {'5','3','.','.','7','.','.','.','.'}, 
                {'6','.','.','1','9','5','.','.','.'}, 
                {'.','9','8','.','.','.','.','6','.'}, 
                {'8','.','.','.','6','.','.','.','3'}, 
                {'4','.','.','8','.','3','.','.','1'}, 
                {'7','.','.','.','2','.','.','.','6'}, 
                {'.','6','.','.','.','.','2','8','.'}, 
                {'.','.','.','4','1','9','.','.','5'}, 
                {'.','.','.','.','8','.','.','7','9'}
        };
        show(board);
        solveSudoku(board);
        show(board);
    }


    public static void show(char[][] board){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j< board[i].length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    /**
     * 解题思路： 使用回溯方法
     *  1. 从数组的左上方到右下方 遍历
     *  2. 根据当前行、列、及其所处的小正方形中已有的数据， 统计当前空位可填的数字
     *  3. 遍历这些数字，填入该位置， 继续向下遍历大数组
     *  4. 直到 数组被全部填充， 返回true; 或发现 无数字可填， 返回false, 回溯
     * */
    public static void solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return;
        }
        // 使用row 记录 数独中每一行出现的数字
        // col 记录 数独中 每一列出现的数字
        // box 的一行 表示数独中从左上到右下 每一个小正方形内出现的数字
        boolean[][] row = new boolean[9][9], col = new boolean[9][9], box = new boolean[9][9];
        // 初始化
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int k = (i / 3) * 3 + j / 3;
                    row[i][num] = col[num][j] = box[k][num] = true;
                }
            }
        }
        dfs(board, 0, row, col, box);
    }

    /**
     *
     * */
    public static boolean dfs(char[][] board, int count, boolean[][] rows, boolean[][] cols, boolean[][] box) {
        // 递归出口：数字全部填入
        if (count == 81) {
            return true;
        }
        // 计算当前位置的下标
        int i = count / 9, j = count % 9;
        // 已有数字， 填充下一个
        if (board[i][j] != '.') {
            return dfs(board, count + 1, rows, cols, box);
        }
        // 计算小方块的序号
        int k = (i / 3) * 3 + j / 3;
        // 针对当前位置 (i,j) 尝试填入 1-9
        for (int num = 0; num < 9; num++) {
            // 当前数已被使用
            if (rows[i][num] || cols[num][j] || box[k][num]) {
                continue;
            }
            // 否则， 填入当前数字
            rows[i][num] = cols[num][j] = box[k][num] = true;
            board[i][j] = (char) (num + '1');

            // 递归，若最终能完全填入，返回true
            if (dfs(board, count + 1, rows, cols, box)) {
                return true;
            }
            // 否则，表示不能在该位置填入当前数字，恢复修改前的状态
            rows[i][num] = cols[num][j] = box[k][num] = false;
            board[i][j] = '.';
        }
        return false;
    }
}
