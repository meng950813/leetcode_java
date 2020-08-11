/**
 * 31. 下一个排列 (中等)
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */

/**
 * 解题思路
 * 1. 从最后一位向前遍历， 若 后一位(i)比前一位(i-1)大：
 *      1.1 在区间 [i,array.length-1]内找到 大于 array[i]的最小值 array[j]，
 *      1.2 交换 array[i], array[j],
 *      1.3 结束遍历
 *    否则，遍历结束，i=0， 数组为降序数组，在[0, array.length-1]范围内执行步骤2
 * 2. 将区间 [i+1, array.length-1]范围内的数组按升序排序
 *      2.1 由步骤1可知， 区间范围内数组为 降序， 改为升序，从两端遍历，交换数值
 *
 * */
public class _31_NextPermutation {
    public static void main(String[] args) {
        int[] result = new int[]{1, 3, 2,1,0};
        for (int i =0; i< result.length;i++){
            System.out.print(result[i]+ "\t");
        }


        nextPermutation(result);

        for (int i =0; i< result.length;i++){
            System.out.print(result[i]+ "\t");
        }

    }

    public static void nextPermutation(int[] nums) {
        int i = nums.length - 1, j = 0;
        for (; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                j = i;
                while (i < nums.length && nums[j-1] < nums[i]) i++;
                swap(nums, j-1, i - 1);
                break;
            }
        }
        asc(nums, j);
    }

    public static void swap(int[] nums, int i, int j) {
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }

    public static void asc(int[] nums, int start){
        for (int i = start, j = nums.length - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }
}
