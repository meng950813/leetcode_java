package leetcode;

import java.util.Arrays;

/**
 * 611. 有效三角形的个数 [中等，贪心，数组，二分查找，排序]
 *
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 *
 * 示例 1:
 * 输入: [2,2,3,4]
 * 输出: 3
 * 解释:
 * 有效的组合是:
 *      2,3,4 (使用第一个 2)
 *      2,3,4 (使用第二个 2)
 *      2,2,3
 *
 * 注意:  数组长度不超过1000。
 * */
public class _611_ValidTriangleNumber {
    public static void main(String[] args) {
        _611_ValidTriangleNumber obj = new _611_ValidTriangleNumber();
        int[] nums = {2,2,3,4};
        int res = obj.triangleNumber(nums);
        System.out.println(res);
    }
    /**
     * 解题思路：首先对数组排序。
     * 固定最长的一条边，运用双指针扫描;
     * 如果 nums[l] + nums[r] > nums[i]，同时说明 nums[l + 1] + nums[r] > nums[i], ..., nums[r - 1] + nums[r] > nums[i]，满足的条件的有 r - l 种，r 左移进入下一轮。
     * 否则，如果 nums[l] + nums[r] <= nums[i]，l 右移进入下一轮。
     * 枚举结束后，总和就是答案。
     * */
    public int triangleNumber(int[] nums) {
        int res = 0;
        Arrays.sort(nums);
        for(int i = nums.length - 1; i >= 2; i--){
            int left = 0, right = i - 1;
            while (left < right){
                if(nums[left] + nums[right] > nums[i]){
                    res += right - left;
                    right--;
                }else {
                    left++;
                }
            }
        }
        return  res;
    }
}
