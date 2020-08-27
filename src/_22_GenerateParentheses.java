/**
 * 22. 括号生成 [中等, 回溯算法]
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例：
 * 输入：n = 3
 * 输出：[
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */

import java.util.*;

public class _22_GenerateParentheses {
    public static void main(String[] args) {
        List<String> result = generateParenthesis(0);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    /**
     * 解题思路：递归插入左右括号
     * 1. 
     * */
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();

        char[] container = new char[2 * n];
        recursion(result, container, n, 0, 0, 0);
        return result;
    }

    public static void recursion(List<String> result, char[] container, int n, int index, int left, int right) {
        if (index == n * 2) {
            result.add(String.valueOf(container));
        }
        if (left < n) {
            container[index] = '(';
            recursion(result, container, n, index + 1, left + 1, right);
        }
        if (left > right) {
            container[index] = ')';
            recursion(result, container, n, index + 1, left, right + 1);
        }
    }
}
