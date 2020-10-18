package leetcode;
/**
 * 1002. 查找常用字符 [简单]
 * 
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 *
 * 示例 2：
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 *
 * 提示：
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] 是小写字母
 */


import java.util.*;


public class _1002_FindCommonCharacters {
    public static void main(String[] args) {
        _1002_FindCommonCharacters obj = new _1002_FindCommonCharacters();
        String[] arr = new String[]{"bella", "label", "roller"};
        System.out.println(obj.commonChars(arr));
    }

    public List<String> commonChars(String[] A) {
//        return Solution1(A);
        return Solution2(A);
    }

    /**
     * 解题思路1： 暴力法：
     *  1. 将每个字符串 格式化为 HashMap<Character, Integer> 格式
     *  2. 遍历第一个字符串，记每个字符为 c
     *      2.1 遍历HashMap数组，判断当前 map 是否包含 字符 c
     *      2.2 若不包含，或 字符数量 小于 1， 结束遍历
     *      2.3 map[c]--
     * */
    public List<String> Solution1(String[] A){
        List<String> result = new ArrayList<>();
        int length = A.length;
        if (length == 0) {
            return result;
        }
        HashMap[] map = new HashMap[length];
        for (int i = 0; i < length; i++) {
            map[i] = new HashMap<Character, Integer>();
            gengerateMap(A[i], map[i]);
        }
        HashMap<Character, Integer> temp_map;
        for (int i = 0, size = A[0].length(); i < size; i++) {
            char c = A[0].charAt(i);
            int j = 0;
            for (; j < length; j++) {
                temp_map = map[j];
                if (!temp_map.containsKey(c) || temp_map.get(c) < 1) {
                    break;
                }
                temp_map.put(c, temp_map.get(c) - 1);
            }
            if (j == length) {
                result.add(String.valueOf(c));
            }
        }
        return result;
    }

    public void gengerateMap(String str, HashMap<Character, Integer> map) {
        for (int i = 0, length = str.length(); i < length; i++) {
            char c = str.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
    }

    /**
     * 解题思路2： 由于给定的都是小写字母，最多26个，可以通过定义 二维数组 代替 HashMap
     *
     * */

    public List<String> Solution2(String[] A){
        List<String> result = new ArrayList<>();
        int length = A.length;
        if (length == 0) {
            return result;
        }
        int[][] map = new int[length][26];
        int cha;
        for (int i =0; i< length; i++){
            for (int j = 0, size = A[i].length(); j< size; j++){
                cha = A[i].charAt(j) - 'a';
                map[i][cha] += 1;
            }
        }
        for (int i = 0; i < 26;  i++){
            int count = map[0][i], j = 1;
            while (count > 0 && j < length){
                count = Math.min(count, map[j][i]);
                j++;
            }

            while (count-- > 0){
                result.add(String.valueOf((char)('a' + i)));
            }
        }
        return result;
    }
}
