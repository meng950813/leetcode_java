package leetcode; /**
 * 198. 打家劫舍 （简单）[动态规划]
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * *如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警*。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 *不触动警报装置的情况下* ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *  
 * 提示：
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */


/**
 * 解题思路
 * 动态规划思路：
 * 设偷到第 k 间房时的最大收益为 f(k)。 则 f(k) = max( f(k-1), f(k-2) + nums[k] )
 * <p>
 * 1. 若 nums为空，则返回 0， 若 nums 长度为 1 ， 则返回 nums[0]
 * 2. 创建新的一维数组 result, 长度为 nums.length， result[k] 表示偷到第 k 间房的最大收益
 * 3. 初始化数组，result[0] = nums[0], result[1] = max(nums[0], nums[1])
 * 4. 下标从 2 开始遍历数组，利用递推式 f(k) = max( f(k-1), f(k-2) + nums[k] ) 计算 result[i]
 * 5. 返回 result 数组的最后一个元素
 */
public class _198_HouseRobber {
    public static void main(String[] args) {

    }

    public static int rob(int[] nums) {
        int length = nums.length;
        if (length == 0) return 0;
        if (length == 1) return nums[0];

        int[] result = new int[length];

        result[0] = nums[0];
        result[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < length; i++) {
            result[i] = Math.max(result[i - 1], result[i - 2] + nums[i]);
        }
        return result[length - 1];
    }

    /**
     * 优化空间:
     * 考虑到计算 result[i] 时只使用 result[i-1] 与 result[i-2],
     * 可以尝试不使用数组，只用两个变量保存结果。
     */
    public static int rob_2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int front = nums[0];
        int result = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            int temp =  Math.max(result, front + nums[i]);
            front = result;
            result = temp;
        }
        return result;
    }
}
