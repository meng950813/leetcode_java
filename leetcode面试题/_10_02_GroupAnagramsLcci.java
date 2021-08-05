package leetcode面试题;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 面试题 10.02. 变位词组 [中等，字符串，哈希表，排序]
 *
 * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
 * 注意：本题相对原题稍作修改
 *
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * */
public class _10_02_GroupAnagramsLcci {
    public static void main(String[] args) {

    }

    /**
    * 解题思路： 统计字符串中的字符，并按a-z的顺序 将字符串统计为 a1b2c4...的格式
     * 并以其为 键 保存到 HashMap中
    * */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs){
            String key = countString(str);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        for (String key : map.keySet()){
            res.add(map.get(key));
        }
        return res;
    }

    public String countString(String str){
        // 将 “eat” , "tea" 转为 a1e1t1 格式
        int[] charNum = new int[26];
        for(char c: str.toCharArray()){
            charNum[(int)(c - 'a')]++;
        }
        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < 26; i++){
            if(charNum[i] > 0){
                buffer.append((char)('a' + i));
                buffer.append(charNum[i]);
            }
        }
        return buffer.toString();
    }
}
