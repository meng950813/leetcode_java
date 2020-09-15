package leetcode; /**
 * 696. 计数二进制子串 [简单， 字符串]
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 * 重复出现的子串要计算它们出现的次数。
 * 示例 1 :
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 * <
 * 示例 2 :
 * 输入: "10101"
 * 输出: 4
 * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 * <p>
 * 注意：
 * s.length 在1到50,000之间。
 * s 只包含“0”或“1”字符。
 */

import java.util.*;

public class _696_CountBinarySubstrings {
    public static void main(String[] args) {
        int result = countBinarySubstrings_3("000111");
        System.out.println(result);
    }

    /**
     * 解题思路1： 找到 一对 “01”, 向两边扩展
     * 时间复杂度 O(n^2) 空间复杂度 O(1)
     */
    public static int countBinarySubstring_1(String s){
        int result = 0, left, right;
        for (int i = 0; i < s.length() - 1; i++) {
            char c_i = s.charAt(i), c_next = s.charAt(i+1);
            if (c_i != c_next){
                left = i;
                right = i+1;
                while (left >= 0 && right < s.length() && s.charAt(left) == c_i && s.charAt(right) == c_next) {
                    result++;
                    left--;
                    right++;
                }
            }
        }
        return result;
    }

    /**
     * 解题思路2： 相邻两类字符串能有多少种组合，取决于两类字符串长度的最小值 ，例如： 000 11 组合数为 2
     *  因此，只需要 将字符串 s 按照 0 和 1 的连续段分组，存在 counts 数组中，例如 s = 00111011，
     *  可以得到数组：counts={2,3,1,2}，相邻两个元素的最小值之和 即为答案
     *
     *  时间复杂度 O(n)
     *  空间复杂度 O(n)
     *  */
    public static int countBinarySubstrings_2(String s) {
        List<Integer> counts = new ArrayList<Integer>();
        int result = 0, index = 0, len = s.length();

        while (index < len) {
            char cha = s.charAt(index);
            int count = 0;
            while (index < len && s.charAt(index) == cha) {
                index++;
                count++;
            }
            counts.add(count);
        }

        for (int i = 1; i < counts.size(); i++){
            result += Math.min(counts.get(i-1), counts.get(i));
        }

        return result;
    }

    /**
     * 解题思路3： 简化思路2， 对于某一个位置 ii，其实我们只关心 i−1 位置的 counts 值是多少，
     * 所以可以用一个 last 变量来维护当前位置的前一个位置，这样可以省去一个 counts 数组的空间。
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * */
    public static int countBinarySubstrings_3(String s) {
        char [] c_arr = s.toCharArray();
        int index = 0;
        int result = 0;
        int lastSameCharCount = 0, sameCharCount = 0;
        while (index < c_arr.length) {
            char cha = c_arr[index];
            sameCharCount = 0;
            while (index < c_arr.length && c_arr[index] == cha) {
                index++;
                sameCharCount++;
            }
            result += Math.min(sameCharCount, lastSameCharCount);
            lastSameCharCount = sameCharCount;
        }
        return result;
    }
}
