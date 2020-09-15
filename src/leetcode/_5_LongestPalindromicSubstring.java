package leetcode;

import javax.swing.*;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * */
public class _5_LongestPalindromicSubstring {
    public static void main(String[] args) {
        String result = longestPalindrome("aabbvvbba");
        System.out.println(result);
    }

    public static String longestPalindrome(String s) {
        if (s.length() < 2){
            return s;
        }
        int maxLength = 0;
        String maxSubStr = null, p=null;

        for (int i = 1; i< s.length(); i++){
            p = onePalindromeLength(s, i,i);
            if (p.length() > maxLength){
                maxLength = p.length();
                maxSubStr = p;
            }
            p = onePalindromeLength(s, i-1, i);
            if (p.length() > maxLength){
                maxLength = p.length();
                maxSubStr = p;
            }
        }
        return maxSubStr;
    }

    /**
     * 给定两个位置，判断该位置的字符是否相同，若相同，指针分别向左和向右移动，扩大范围
     * 直到两字符不相同或 某一指针到头，返回当前子串
     * */
    public static String onePalindromeLength(String s, int left_index, int right_index){
        while (left_index >= 0 && right_index < s.length()){
            if (s.charAt(left_index) != s.charAt(right_index)){
                return s.substring(left_index + 1, right_index);
            }
            left_index--;
            right_index++;
        }
        return s.substring(left_index + 1, right_index);
    }
}
