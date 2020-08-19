package 剑指offer;

import java.util.*;

/**
 * 剑指 Offer 03. 数组中重复的数字 [简单]
 *
 * 找出数组中重复的数字。
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 */
public class _03_lcof {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 0, 2, 5, 3};
        System.out.println(findRepeatNumber(nums));
    }

    /**
     * 解题思路： 使用 HashSet 集合
     * 时间复杂度 O(n), 空间复杂度 O(n)
     * */
    public static int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i =0; i< nums.length; i++){
            if (set.contains(nums[i])){
                return nums[i];
            }
            set.add(nums[i]);
        }
        return nums[0];
    }


    /**
     * 解题思路2：利用题目中 数组值 [0,n-1]的特性， 创建新数组，以 原数组值为下标，数字出现次数为值
     * 相较于 集合， 数组的存取时间更快
     * 时间复杂度 O(n), 空间复杂度 O(n)
     * */
    public static int findRepeatNumber_2(int[] nums) {
        int []count = new int[nums.length];
        for(int i =0;i < nums.length; i++){
            count[nums[i]]++;
            if (count[nums[i]] > 1){
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 解题思路3： 在思路2的基础上，遍历数组，将值交换到对应下标上，通过比较对应位置上是否已存在该值，判断 该值是否重复
     * 看似双重循环，实则 内层循环每次都将一个元素放入对应位置，所以整个循环中，内层循环最多执行n次
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * */
    public static int findRepeatNumber_3(int[] nums) {
        int temp;
        for(int i = 0 ; i < nums.length ; i++){
            while(nums[i] != i){
                if(nums[i] == nums[nums[i]]){
                    return nums[i];
                }
                temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }
}
