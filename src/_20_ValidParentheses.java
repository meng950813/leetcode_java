import java.util.*;

/**
 * 20. 有效的括号 [简单]
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * 输入: "()"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 */
public class _20_ValidParentheses {
    public static void main(String[] args) {

    }

    /**
     * 解题思路： 利用 栈 结构
     * 1. 遍历字符串，
     * 1.1. 若 为括号左半边 ([{ 则入栈， 否则 取栈顶元素，判断栈顶元素与当前字符是否对应，
     * 1.2. 若 不对应则返回 false， 否则弹出栈顶元素
     * 2. 遍历结束，若栈空，返回 true, 否则返回 false
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    public static boolean isValid(String s) {
        Stack stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char cha = s.charAt(i);
            if (cha == '(' || cha == '[' || cha == '{') {
                stack.push(cha);
            } else {
                if (stack.empty() ||
                        ((char) stack.peek() == '(' && cha != ')') ||
                        ((char) stack.peek() == '[' && cha != ']') ||
                        ((char) stack.peek() == '{' && cha != '}')) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        if (stack.empty()) {
            return true;
        }
        return false;
    }

    /**
     * 优化算法： 相比于对操作数组，出栈和入栈的时间消耗更大， 因此使用字符数组实现栈的功能，提升执行效率
     */
    public static boolean isValid_2(String s) {
        int length = s.length();
        if (length == 0) {
            return true;
        }
        int index = 1;
        char[] stack = new char[length+1];
        char[] str = s.toCharArray();
        for (char c : str) {
            switch (c){
                case ')':{
                    if (stack[--index] != '(') return false;
                    break;
                }
                case ']':{
                    if (stack[--index] != '[') return false;
                    break;
                }
                case '}':{
                    if (stack[--index] != '{') return false;
                    break;
                }
                default:{
                    stack[index++] = c;
                }
            }
        }
        return index == 1;
    }
}
