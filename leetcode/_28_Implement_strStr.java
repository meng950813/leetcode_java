package leetcode;

/**
 * 28. 实现 strStr() [简单, 字符串, 双指针]
 * <p>
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */
public class _28_Implement_strStr {
    public static void main(String[] args) {
        int result = strStr_1("hello", "ll");
        System.out.println(result);
    }


    /**
     * 解题思路1： 遍历 haystack， 从 下标 i 开始截取 needle 长度的子串， 与 needle 比较， 若相同，返回 i
     *      遍历结束 返回 -1
     * 时间复杂度 O((N - L) * L), 其中 N 为 haystack 字符串的长度，L 为 needle 字符串的长度。
     * 内循环中比较字符串的复杂度为 L，总共需要比较 (N - L) 次
     * 空间复杂度 O(1)
     */
    public static int strStr_1(String haystack, String needle) {
        int lenString = haystack.length(), lenSub = needle.length();
        for (int i = 0; i < lenString - lenSub + 1; i++) {
            if (haystack.substring(i, i + lenSub).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 思路2：
     */
    public static int strStr_2(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int idx_sub = 0, idx_str = 0;
        while (idx_str < haystack.length() && idx_sub < needle.length()) {
            if (haystack.charAt(idx_str) == needle.charAt(idx_sub)) {
                idx_str++;
                idx_sub++;
            } else {
                idx_str -= idx_sub - 1;
                idx_sub = 0;
            }
        }
        if (idx_sub == needle.length()) {
            return idx_str - idx_sub;
        }
        return -1;
    }
}
