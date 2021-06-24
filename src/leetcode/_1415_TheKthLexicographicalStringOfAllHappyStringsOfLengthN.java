package leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * 1415. 长度为 n 的开心字符串中字典序第 k 小的字符串 [中等， 回溯算法]
 * 一个 「开心字符串」定义为：
 *  
 * 仅包含小写字母 ['a', 'b', 'c'].
 * 对所有在 1 到 s.length - 1 之间的 i ，满足 s[i] != s[i + 1] （字符串的下标从 1 开始）。
 * 比方说，字符串 "abc"，"ac"，"b" 和 "abcbabcbcb" 都是开心字符串，但是 "aa"，"baa" 和 "ababbc" 都不是开心字符串。
 * 给你两个整数 n 和 k ，你需要将长度为 n 的所有开心字符串按字典序排序。
 * 请你返回排序后的第 k 个开心字符串，如果长度为 n 的开心字符串少于 k 个，那么请你返回 空字符串 。
 *  
 * 示例 1：
 * 输入：n = 1, k = 3
 * 输出："c"
 * 解释：列表 ["a", "b", "c"] 包含了所有长度为 1 的开心字符串。按照字典序排序后第三个字符串为 "c" 。
 *  
 * 示例 2：
 * 输入：n = 1, k = 4
 * 输出：""
 * 解释：长度为 1 的开心字符串只有 3 个。
 *  
 * 示例 3：
 * 输入：n = 3, k = 9
 * 输出："cab"
 * 解释：长度为 3 的开心字符串总共有 12 个 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"] 。第 9 个字符串为 "cab"
 *  
 * 示例 4：
 * 输入：n = 2, k = 7
 * 输出：""
 *  
 * 示例 5：
 * 输入：n = 10, k = 100
 * 输出："abacbabacb"
 *  
 * 提示：
 * 1 <= n <= 10
 * 1 <= k <= 100
 */

public class _1415_TheKthLexicographicalStringOfAllHappyStringsOfLengthN {
    public static void main(String[] args) {
        _1415_TheKthLexicographicalStringOfAllHappyStringsOfLengthN obj = new _1415_TheKthLexicographicalStringOfAllHappyStringsOfLengthN();
        System.out.println(obj.getHappyString(1, 3));
    }

    public String getHappyString(int n, int k) {
        List<String> arr = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        dfs(arr, buffer, n, k, '0');
        if (arr.size() < k) {
            return "";
        }
        return arr.get(k - 1);
    }

    public void dfs(List<String> arr, StringBuffer path, int n, int k, char last) {
        if (path.length() == n) {
            arr.add(path.toString());
            return;
        }

        for (char i = 'a'; i < 'd'; i++) {
            if (i == last) {
                continue;
            }
            path.append(i);
            dfs(arr, path, n, k, i);
            if (arr.size() == k) {
                return;
            }
            path.deleteCharAt(path.length() - 1);
        }
    }
}
