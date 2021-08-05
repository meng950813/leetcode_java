package leetcode面试题;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.12. 八皇后[困难， 回溯法]
 * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
 * 注意：本题相对原题做了扩展
 *
 * 示例:
 * 输入：4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [   ".Q..",  // 解法 1
 *      "...Q",
 *      "Q...",
 *      "..Q."],
 *
 *  [   "..Q.",  // 解法 2
 *      "Q...",
 *      "...Q",
 *      ".Q.."]
 * ]
 */
public class _08_12_EightQueensLcci {
    public static void main(String[] args) {
        _08_12_EightQueensLcci obj = new _08_12_EightQueensLcci();
        List<List<String>> res = obj.solveNQueens(4);
        for (List<String> list: res){
            for (String s: list){
                System.out.println(s);
            }
            System.out.println("*************");
        }
    }
    boolean[][] used = null;
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        used = new boolean[n][n];
        dfs(n, 0);
        return res;
    }

    public void dfs(int n, int x){
        if (x == n){
            addOneType(n);
            return;
        }
        for (int y = 0; y < n; y++){
            if (checkValid(x, y, n)){
                used[x][y] = true;
                dfs(n, x + 1);
                used[x][y] = false;
            }
        }
    }

    /**
     * 通过判断 正上方 和 斜上方 是否已有棋子 判断 (x, y) 位置能否放置棋子
     */
    public boolean checkValid(int x, int y, int n) {
        for (int i = 1; i <= x; ++i) {
            // 正上方
            if (used[x - i][y]) {
                return false;
            }
            // 左上方
            if (y >= i && used[x - i][y - i]) {
                return false;
            }
            // 右上方
            if (y + i < n && used[x - i][y + i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 写入本次可行的情况
     * */
    public void addOneType(int n) {
        List<String> one = new ArrayList<>();
        for (int i = 0; i <n; i++) {
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
        res.add(one);
    }
}
