package leetcode;

/**
 * 3. 无重复字符的最长子串 [中等]
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是   "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke"   是一个子序列，不是子串。
 */
public class _3_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        int len = lengthOfLongestSubstring("bbtatlud");
        System.out.println(len);
    }

    /**
     * 解题思路：
     * 设置一个窗口，初始大小为 0，保证窗口内的元素都不重复
     * 遍历字符串
     * 检查下标为 i 的字符 在 [i-widow, i) 的窗口范围内是否存在
     * 若不存在：
     * window ++; 保存窗口最大长度；
     * 否则：
     * 找到相同元素在窗口内的下标，缩小窗口大小为 ： window - index;
     */
    public static int lengthOfLongestSubstring(String s) {
        int window = 0, index = -1, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            String sr = s.substring(i - window, i);
            char c = s.charAt(i);
            index = s.substring(i - window, i).indexOf(s.charAt(i));
            if (index == -1) {
                /* 下标为 i 的字符不在窗口内, 窗口 + 1 */
                window++;
                if (window > maxLen) {
                    maxLen = window;
                }
            } else {
                window -= index;
            }
        }
        return maxLen;
    }


}
