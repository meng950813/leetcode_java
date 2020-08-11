/**
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 *
 * 示例：
 *
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 * 返回 13
 * */

public class _378_Kth_SmallestElement_in_a_SortedMatrix {
    public static void main(String[] args) {
        int[][] test = new int[][]{{ 1,  5,  9}, {10, 11, 13}, {12, 13, 15}};
        int result = kthSmallest(test, 8);
        System.out.println(result);
    }

    public static int kthSmallest(int[][] matrix, int k) {
        /*
         * 解题思路：
         *  将二维数组变为一维数组，排序，取第k个值
         *  排序时想到，该问题已变为 一维数组下的第k小值，可以利用 快速排序的 特性，判断返回的位置与k的比较情况
         *  变成了分治法找第k小/大值，时间复杂度 O(nlogn), 空间复杂度 O(n^2)
         * */
        if (k == 1){
            return matrix[0][0];
        }
        int length = matrix.length;
        int [] array = new int[length * length];
        for(int i = 0; i < length; i++){
            for(int j =0; j< length; j++){
                array[length * i + j] = matrix[i][j];
            }
        }
        int position = 1;
        int start = 0, end = length * length - 1;
        while (start < end){
            position = partition(array, start, end);
            if (position == k-1){
                return array[position];
            }
            else if (position > k - 1){
                end = position - 1;
            }
            else {
                start = position + 1;
            }
        }
        return array[k-1];
    }

    public static int partition(int[] nums, int left, int right){
        int i = left, j = right;
        int standard = nums[left];
        while (i < j){
            while (nums[j] >= standard && i < j) j--;
            nums[i] = nums[j];
            while (nums[i] < standard && i < j) i++;
            nums[j] = nums[i];
        }

        nums[i] = standard;
        return i;
    }

}
