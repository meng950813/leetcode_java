package leetcode;

import java.util.Arrays;

/**
 * 60. 第k个排列 [中等， 回溯法]
 * 给出集合  [1,2,3,…,n]，其所有元素共有  n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当  n = 3 时, 所有排列如下：
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定  n 和  k，返回第  k  个排列。
 *
 * 说明：
 * 给定 n  的范围是 [1, 9]。
 * 给定 k  的范围是[1,   n!]。
 *
 * 示例  1:
 * 输入: n = 3, k = 3
 * 输出: "213"
 *
 * 示例  2:
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class _60_PermutationSequence {

    public static void main(String[] args) {
        String result = getPermutation(5,10);
        System.out.println(result);
    }

    /**
     * 解题思路：
     * 1. 使用数组 arr[n] 记录 前 1! ~ （n-1）!的值
     * 2. 通过 num = k / m! + 1 确定 当前位置应该是 哪个值
     * 3. 使用 boolean 数组记录 哪些值已被使用， 若 num 已被使用， 则通过 num++ 找到最近（最小）的未被使用的值
     * */
    public static String getPermutation(int n, int k) {
        int[] used = new int[n+1];
        Arrays.fill(used,1);
        // 前 n-1 个数 的阶乘
        int[] jieCheng = new int[n];
        jieCheng[0] = 1;
        for (int i = 1; i < n; i++){
            jieCheng[i] = i * jieCheng[i-1];
        }

        k--;
        StringBuffer result = new StringBuffer();
//        for (int i = n - 1; i >= 0; i--) {
//            int order = k / jieCheng[i] + 1;
//            for (int j = 1; j <= n; ++j) {
//                order -= used[j];
//                if (order == 0) {
//                    result.append(j);
//                    used[j] = 0;
//                    break;
//                }
//            }
//            k %= jieCheng[i];
//        }

        for (int i = n-1; i >= 0; i--){
            result.append(getOne(used, jieCheng, n, i, k));
            k %= jieCheng[i];
        }
        return result.toString();
    }

    public static int getOne(int[] used, int[] jieCheng, int total, int n, int k){
        int order = k / jieCheng[n] + 1;
        for (int i =1; i <= total;i++){
            order -= used[i];
            if (order == 0){
                used[i] = 0;
                return i;
            }
        }
        return total;
    }
}