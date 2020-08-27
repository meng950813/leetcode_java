import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合 [中等, 回溯算法]
 */
public class _17_LetterCombinationsOfPhoneNumber {
    public static void main(String[] args) {
        _17_LetterCombinationsOfPhoneNumber obj = new _17_LetterCombinationsOfPhoneNumber();
        obj.letterCombinations("2345");
        for (String s : obj.result){
            System.out.println(s);
        }
    }

    List<String> result = new ArrayList<>();
    StringBuffer buffer = new StringBuffer();

    private char[][] dict = new char[][]{
            {'a','b','c'},
            {'d','e','f'},
            {'g','h','i'},
            {'j','k','l'},
            {'m','n','o'},
            {'p','q','r','s'},
            {'t','u','v'},
            {'w','x','y','z'}
    };

    public List<String> letterCombinations(String digits) {
        int str_len = digits.length();
        if (str_len > 0){
            dfs(str_len, 0, digits);
        }
        return result;
    }

    /**
     * 解题思路: 回溯法 ==> 本质上就是递归穷举
     *  1. 若字符串已全部遍历，将 buffer 添加到result中，递归结束
     *  2. 否则，获取当前字符，找到并遍历其对应的 字母
     *      2.1 将字母插入 buffer 中
     *      2.2 递归 下一个 数字字符
     *      2.3 回溯，将上一个字母移除
     *
     * @param str_len 字符串长度，用于判断递归出口
     * @param cur_idx 当前字符在字符串中的位置
     * @param digits 原始字符串
     */
    public void dfs(int str_len, int cur_idx, String digits){
        if (cur_idx >= str_len){
            result.add(buffer.toString());
            return;
        }
        int char_idx = digits.charAt(cur_idx)- '2';
        for (char cha : dict[char_idx]){
            buffer.append(cha);
            dfs(str_len, cur_idx+1, digits);
            buffer.deleteCharAt(buffer.length()-1);
        }
    }
}
