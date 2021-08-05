package leetcode;

/**
 * 15. 三数之和 [中等, 双指针，哈希表]
 *
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [ [-1, 0, 1],[-1, -1, 2] ]
 */

import java.util.*;
public class _15_3sum {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = threeSum(nums);
        for (List<Integer> item: result){
            System.out.println(item);
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;

        for (int first = 0; first < len; first++){
            if (nums[first] > 0){
                break;
            }
            // 去除重复情况
            if (first > 0 && nums[first] == nums[first - 1]){
                continue;
            }

            int target = -nums[first];
            int left = first + 1, right = len - 1, value;
            while (left < right){
                value = nums[left] + nums[right];
                if (value == target){
                    result.add(new ArrayList<>(Arrays.asList(nums[first], nums[left], nums[right])));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                }
                else if (value < target){
                    left++;
                }
                else{
                    right--;
                }
            }
        }
        return result;
    }
}
