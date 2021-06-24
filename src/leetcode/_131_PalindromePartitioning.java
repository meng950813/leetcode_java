package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *131. 分割回文串 [中等， 回溯算法]
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 * 输入:"aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 * */
public class _131_PalindromePartitioning {
    public static void main(String[] args) {
        _131_PalindromePartitioning obj = new _131_PalindromePartitioning();
        String s = "aab";
        obj.partition(s);
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), s, 0);
        for (List<String> list: res){
            System.out.println(list);
        }
       return res;
    }

    public void dfs(List<List<String>> res, List<String> one, String s, int start){
        int length = s.length();
        if (start == length){
            res.add(new ArrayList<>(one));
            return;
        }
        for (int i = start; i < length; i++){
            String left = s.substring(start, i + 1);
            if (isHuiWen(left)){
                one.add(left);
                dfs(res, one, s, i+1);
                one.remove(one.size() - 1);
            }
        }
    }



    /**
    * 双指针法判断字符串是否回文
    * */
    public boolean isHuiWen(String s){
        int i =0, j = s.length() - 1;
        while (i < j && s.charAt(i) == s.charAt(j)){
            i++;
            j--;
        }
        return i >= j;
    }
}
