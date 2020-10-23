package leetcode;

import java.util.*;

/**
 * 347. 前 K 个高频元素  [中等， 哈希表]
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * <p>
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * 提示：
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 */
public class _347_TopKFrequentElements {
    public static void main(String[] args) {

    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        return sloution_1(map, k);
    }

    /**
     * 解题思路1： 暴力法： 哈希表存次数 => 哈希表内排序 => 取前 k 个
     */
    public static int[] sloution_1(Map<Integer, Integer> map, int k){
        int[] result = new int[k];
        List<Map.Entry<Integer, Integer>> infoIds = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());

        Collections.sort(infoIds, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                // 降序排列
                return o2.getValue() - o1.getValue();
            }
        });

        for (int i = 0; i < k; i++) {
            result[i] = infoIds.get(i).getKey();
        }
        return result;
    }
}
