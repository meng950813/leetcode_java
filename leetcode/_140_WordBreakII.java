package leetcode;
import java.util.*;

/**
 * 140. 单词拆分 II [困难, 动态规划, 回溯算法]
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 *
 * 说明：
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 *
 * 示例 2：
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 * */
public class _140_WordBreakII {
    public static void main(String[] args) {
        _140_WordBreakII obj = new _140_WordBreakII();
        obj.wordBreak("SSS", new ArrayList<String>());
    }

    public List<String> wordBreak(String s, List<String> wordDict){
        Set<String> set = new HashSet<>();
        int maxLength = 0;
        for (String word : wordDict) {
            set.add(word);
            maxLength = Math.max(maxLength, word.length());
        }
        int length = s.length();
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;

        for (int i = 1; i <= length; i++) {
            for (int j = i; j >= 0 && j >= i - maxLength; j--) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        List<String> res = new ArrayList<>();
        if (dp[length]) {
            LinkedList<String> list = new LinkedList<>();
            dfs(s, length, set, dp, list, res);
        }

        return res;
    }

    private void dfs(String s, int end, Set<String> set, boolean[] dp, LinkedList<String> list, List<String> res) {
        if (end == 0) {
            StringBuilder sb = new StringBuilder();
            for (String word : list) {
                sb.append(word).append(" ");
            }
            sb.setLength(sb.length() - 1);
            res.add(sb.toString());
        }

        for (int i = 0; i < end; i++) {
            if (dp[i]) {
                String substring = s.substring(i, end);
                if (set.contains(substring)) {
                    list.addFirst(substring);
                    dfs(s, i, set, dp, list, res);
                    list.removeFirst();
                }
            }
        }
    }

}