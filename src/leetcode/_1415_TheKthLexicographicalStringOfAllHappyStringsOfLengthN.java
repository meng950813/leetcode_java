package leetcode;


import java.util.ArrayList;
import java.util.List;

public class _1415_TheKthLexicographicalStringOfAllHappyStringsOfLengthN {
    public static void main(String[] args) {
        _1415_TheKthLexicographicalStringOfAllHappyStringsOfLengthN obj = new _1415_TheKthLexicographicalStringOfAllHappyStringsOfLengthN();
        System.out.println(obj.getHappyString(1,3));
    }

    public String getHappyString(int n, int k) {
        List<String> arr = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        dfs(arr, buffer, n, k, '0');
        if (arr.size() < k){
            return "";
        }
        return arr.get(k - 1);
    }

    public void dfs(List<String> arr, StringBuffer path, int n, int k, char last){
        if (path.length() == n){
            arr.add(path.toString());
            return;
        }

        for (char i = 'a'; i < 'd'; i++){
            if (i == last){
                continue;
            }
            path.append(i);
            dfs(arr, path, n, k, i);
            if (arr.size() == k){
                return;
            }
            path.deleteCharAt(path.length() - 1);
        }
    }
}
