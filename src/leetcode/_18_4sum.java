package leetcode;

/**
 * 18. 四数之和 [中等, 双指针]
 * 给定一个包含n 个整数的数组nums和一个目标值target，判断nums中是否存在四个元素 a，b，c和 d，使得a + b + c + d的值与target相等？找出所有满足条件且不重复的四元组。
 * 
 * 注意：
 * 答案中不可以包含重复的四元组。
 * 
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 *      [-1,  0, 0, 1],
 *      [-2, -1, 1, 2],
 *      [-2,  0, 0, 2]
 * ]
 */

import java.util.*;

public class _18_4sum {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 0, 0};
        int target = 0;
        for (List<Integer> item : fourSum(nums, target)) {
            System.out.println(item);
        }
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        int sum3, sum2;
        int first, second, third, fourth;
        for (first = 0; first < len - 3; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            sum3 = target - nums[first];
            if (nums[first + 1] + nums[first + 2] + nums[first + 3] > sum3) {
                break;
            }
            if (nums[len - 3] + nums[len - 2] + nums[len - 1] < sum3) {
                continue;
            }
            for (second = first + 1; second < len - 2; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                sum2 = sum3 - nums[second];
                if (nums[second + 1] + nums[second + 2] > sum2) {
                    break;
                }
                if (nums[len - 2] + nums[len - 1] < sum2) {
                    continue;
                }
                third = second + 1;
                fourth = len - 1;
                while (third < fourth) {
                    if ((nums[third] + nums[fourth]) == sum2) {
                        result.add(new ArrayList<>(Arrays.asList(nums[first], nums[second], nums[third], nums[fourth])));
                        third++;
                        fourth--;
                        while (third < fourth && nums[third] == nums[third - 1]) third++;
                        while (third < fourth && nums[fourth] == nums[fourth + 1]) fourth--;
                    } else if ((nums[third] + nums[fourth]) < sum2) {
                        third++;
                    } else {
                        fourth--;
                    }
                }
            }
        }

        return result;
    }
}
