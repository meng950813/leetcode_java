package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 763. 划分字母区间 [中等， 贪心算法，双指针]
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。
 * 返回一个表示每个字符串片段的长度的列表。
 * <p>
 * 示例 1：
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * <p>
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 * <p>
 * 提示：
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 */
public class _763_PartitionLabels {
    public static void main(String[] args) {
        String str = "1111211424555";
        _763_PartitionLabels obj = new _763_PartitionLabels();
        System.out.println(
                obj.partitionLabels(str)
        );
    }

    /**
     * 解题思路1：
     * 1. 使用 哈希表 保存每个元素最后出现的位置
     * 2. left = 0, 遍历字符串，获取该字符最后出现的位置， 记为 right
     * 3. 遍历 区间 [left, right] 之间的元素， right 变为 中间元素的最大位置
     * */
    public List<Integer> partitionLabels(String S) {
        int length = S.length();
        List<Integer> res = new ArrayList<>();
        if (length == 0){
            return res;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i= 0; i< length; i++){
            map.put(S.charAt(i), i);
        }
        int left = 0, right = 0, i = 0;
        while (left < length){
            right = map.get(S.charAt(left));
            for (i = left; i <= right; i++){
                right = Math.max(right, map.get(S.charAt(i)));
            }
            res.add(right - left + 1);
            left = right + 1;
        }

        return res;
    }

    /**
     * 解题思路2：主体思路与1相同，优化
     * 考虑到字符只包含 a-z, 使用26位数组代替 HashMap
     * */
    public List<Integer> partitionLabels2(String S) {
        int length = S.length();
        List<Integer> res = new ArrayList<>();
        if (length == 0){
            return res;
        }
        int[] map = new int[26];
        for (int i= 0; i< length; i++){
            map[S.charAt(i) - 'a'] = i;
        }

        int left = 0, right = 0;
        while (left < length){
            right = map[S.charAt(left) - 'a'];
            for (int i = left; i <= right; i++){
                right = Math.max(right, map[S.charAt(i) - 'a']);
            }
            res.add(right - left + 1);
            left = right + 1;
        }

        return res;
    }

}
