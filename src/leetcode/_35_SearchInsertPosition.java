package leetcode;

/**
 * 35. 搜索插入位置 [简单, 数组]
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 *
 * 示例2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 *
 * 示例 3:
 * 输入: [1,3,5,6], 7
 * 输出: 4
 *
 * 示例 4:
 * 输入: [1,3,5,6], 0
 * 输出: 0
 * */
public class _35_SearchInsertPosition {
    public static void main(String[] args) {

    }

    /**
     * 解题思路1: 遍历，判断当前值 nums[i] 与 目标值的大小，
     *  若 nums[i] < target 由于数组有序，继续遍历
     *  若 nums[i] == target， 返回 i
     *  若 nums[i] > target, 表示该值不在数组中，但应当插入当前位置， 返回 i
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * */
    public int searchInsert(int[] nums, int target) {

        for(int i=0; i< nums.length; i++){
            if(nums[i] >= target){
                return i;
            }
        }
        return nums.length;
    }

    /**
     * 由于数组有序，可以使用分治法找到该位置
     * 分治法的核心在于确定边界值，
     * */
    public int searchInsert_2(int[] nums, int target) {
        int left = 0, right = nums.length - 1, middle=0; // [left, right]
        while (left <= right){
            middle = (left + right) / 2;
            if (nums[middle] > target){
                right = middle - 1;
            }
            else if (nums[middle] < target){
                left = middle + 1;
            }
            else {
                return middle;
            }
        }

        return right + 1;
    }
}
