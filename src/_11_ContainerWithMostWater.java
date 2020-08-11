/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * [示例图](https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg)
 * <p>
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * <p>
 * 示例：
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 */

/**
 * 解题思路
 * i=0, j=array.length-1, result=0; 从两端开始遍历, 直到 i >= j ：
 * 1. 计算当前面积 a, result = max(result, a)
 * 2. 取 array[i], array[j] 中较小值， 对应坐标向中心靠拢， 直到 找到 更大的值， 执行步骤 1
 */
public class _11_ContainerWithMostWater {
    public static void main(String[] args) {
        int []height = new int[]{1,8,6,2,5,4,8,3,7};
        int res = maxArea(height);
        System.out.println(res);
    }

    public static int maxArea(int[] height) {
        int result = 0, i = 0, j = height.length - 1;
        while (i < j) {
            int min_height = Math.min(height[i], height[j]);
            result = Math.max(min_height * (j - i), result);

            while (i < j && height[i] <= min_height) i++;
            while (i < j && height[j] <= min_height) j--;
        }
        return result;
    }
}
