package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 402. 移掉K位数字 [中等， 贪心算法]
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *
 * 注意:
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 *
 * 示例 1 :
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 *
 * 示例 2 :
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 *
 * 示例 3 :
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 */
public class _402_RemoveKDigits {
    public static void main(String[] args) {
        String num = "10200";
        int k = 2;
        System.out.println(removeKdigits(num, k));
    }

    /**
     * 解题思路： 贪心算法， 比较前一位和后一位， 若后一位更小，舍去前一位
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits(String num, int k) {
        int length = num.length();
        if (k == length){
            return "0";
        }
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i< length; i++){
            char curChar = num.charAt(i);
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > curChar){
                k--;
                deque.pollLast();
            }
            deque.offerLast(curChar);
        }
        // 若 k > 0, 说明字符串为增序， 移除尾部字符串
        for (; k > 0; k--){
            deque.pollLast();
        }
        StringBuilder builder = new StringBuilder();
        boolean zeroFirst = true;
        char first;
        while (!deque.isEmpty()){
            first = deque.pollFirst();
            if (zeroFirst && first == '0'){
                continue;
            }
            zeroFirst = false;
            builder.append(first);
        }
        return builder.length() == 0? "0" : builder.toString();
    }
}
