/**
 * 130. 被围绕的区域 [中等, 深度遍历，广度遍历，并查集]
 * 
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 
 * 示例:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 
 * 运行你的函数后，矩阵变为：
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 
 * 解释:
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */

public class _130_SurroundedRegions {
    public static void main(String[] args) {
        char[][] board = new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        show(board);
        solve(board);
        show(board);
    }

    public static void show(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("**********");
    }

    /**
     * 解题思路： 边缘的 O 及与之相连的 O 不会被包裹，不会变， 因此可以标记出所有不变的 O, 将其他字符全置为 X
     * 1. 定义一个 boolean 型二维数组，用于存贮对应字符是否需要变化
     * 2. 遍历 board 数组的四边，若有字符为 O， 递归其 上下左右 四个元素
     * 2.1 判断下标是否越界 以及 该字符是否为 X 或 已被标记为 true， 若是则 返回
     * 2.2 否则 表示当前字符为 O 且与 边界 O 相连，其位置 置为 True, 表示不需改变
     *
     * 时间复杂度：O(n×m)，其中 n 和 m 分别为矩阵的行数和列数。广度优先搜索过程中，每一个点至多只会被标记一次。
     *
     * 空间复杂度：O(n×m)，其中 n 和 m 分别为矩阵的行数和列数。主要为广度优先搜索的队列的开销
     *
     */
    public static void solve(char[][] board) {
        if (board.length == 0 || (board.length < 3 || board[0].length < 3)) {
            return;
        }
        int row = board.length, col = board[0].length;
        // 初始化值为 false
        boolean[][] flag = new boolean[row][col];
        // 第一列 & 最后一列
        for (int i = 0; i < row; i++) {
            BFS(board, flag, i, 0);
            BFS(board, flag, i, col - 1);
        }
        // 第一行 & 最后一行
        for (int j = 0; j < col; j++) {
            BFS(board, flag, 0, j);
            BFS(board, flag, row - 1, j);
        }
        // 转变内部 O 为 X
        for (int i = 0;i< row;i++){
            for (int j=0; j<col;j++){
                if (flag[i][j] == false){
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static void BFS(char[][] board, boolean[][] flag, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] == 'X' || flag[i][j] == true) {
            return;
        }
        flag[i][j] = true;
        BFS(board, flag, i - 1, j);
        BFS(board, flag, i + 1, j);
        BFS(board, flag, i, j - 1);
        BFS(board, flag, i, j + 1);
    }
}
