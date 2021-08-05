package leetcode;

/**
 * 327. 区间和的个数 [困难， 树状数组， 二分查找， 分治算法]
 * <p>
 * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * <p>
 * 说明:
 * 最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
 * <p>
 * 示例:
 * 输入: nums = [-2,5,-1], lower = -2, upper = 2,
 * 输出: 3
 * 解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
 */
public class _327_CountOfRangeSum {
    public static void main(String[] args) {
        _327_CountOfRangeSum obj = new _327_CountOfRangeSum();
        int[] nums = {-2147483647, 0, -2147483647, 2147483647};
        int lower = -564;
        int upper = 3864;

        System.out.println(obj.countRangeSum_1(nums, lower, upper));
    }

    /**
     * 解题思路1： 暴力算法: 使用一个二维数组记录各个区间之和，判断该区间之和是否符合范围
     *
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public int countRangeSum_1(int[] nums, int lower, int upper) {
        int result = 0, i, j;
        long sum = 0;
        for (i = 0; i < nums.length; i++) {
            sum = 0;
            for (j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= lower && sum <= upper) {
                    result++;
                }
            }
        }
        return result;
    }
}
