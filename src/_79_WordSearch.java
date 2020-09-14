/**
 * 79. 单词搜索 [中等, 回溯算法]
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 * <p>
 * 提示：
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 */
public class _79_WordSearch {
    public static void main(String[] args) {
        char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCESEEEFS";
        System.out.println(exist(board, word));
    }

    public static boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        int len_x = board.length, len_y = board[0].length, words_len = words.length;
        boolean[][] visited = new boolean[len_x][len_y];
        boolean result = false;
        for (int i = 0; i < len_x; i++) {
            for (int j = 0; j < len_y; j++) {
                if (board[i][j] != words[0]) continue;
                result = check(board, visited, len_x, len_y, i, j, words, words_len, 0);
                if (result) {
                    return result;
                }
            }
        }
        return result;

    }

    public static boolean check(char[][] board, boolean[][] visited, int len_x, int len_y, int pos_x, int pos_y, char[] words, int word_len, int index) {
        if (index == word_len) {
            return true;
        }
        if (pos_x < 0 || pos_x == len_x || pos_y < 0 || pos_y == len_y) {
            return false;
        }
        if (visited[pos_x][pos_y] || board[pos_x][pos_y] != words[index]) {
            return false;
        }
        visited[pos_x][pos_y] = true;
        ++index;
        boolean result = check(board, visited, len_x, len_y, pos_x + 1, pos_y, words, word_len, index) ||
                check(board, visited, len_x, len_y, pos_x - 1, pos_y, words, word_len, index) ||
                check(board, visited, len_x, len_y, pos_x, pos_y + 1, words, word_len, index) ||
                check(board, visited, len_x, len_y, pos_x, pos_y - 1, words, word_len, index);
        visited[pos_x][pos_y] = false;
        return result;
    }
}
