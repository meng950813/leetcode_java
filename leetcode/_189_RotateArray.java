package leetcode;

/**
 * 189. 旋转数组 [中等]
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 *
 * 示例 2:
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释: 
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 *
 * 说明:
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 */
public class _189_RotateArray {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        int k = 2;
        for (Integer num:nums){
            System.out.print(num + ", ");
        }
        System.out.println();
        _189_RotateArray obj = new _189_RotateArray();
        obj.rotate(nums, k);
        for (Integer num:nums){
            System.out.print(num + ", ");
        }
    }

    public void rotate(int[] nums, int k) {
       k %= nums.length;
       reverse(nums, 0, nums.length - 1);
       reverse(nums, 0, k - 1);
       reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int left, int right){
        int temp;
        while (left < right){
            temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
