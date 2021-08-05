package leetcode;

/**
 * 201. 数字范围按位与 [中等, 位运算]
 * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
 * 
 * 示例 1:
 * 输入: [5,7]
 * 输出: 4
 * 
 * 示例 2:
 * 输入: [0,1]
 * 输出: 0
 */
public class _201_BitwiseAndOfNumbersRange {
    public static void main(String[] args) {
        _201_BitwiseAndOfNumbersRange obj = new _201_BitwiseAndOfNumbersRange();
        int result = obj.rangeBitwiseAnd(2147483646, 2147483647);
        System.out.println(result);
    }

    /**
     * 解题思路1： 暴力运算
     * 1. 遍历 m,n 之间的数， 依次进行 与 运算，得出最终结果
     *  考虑到一般暴力运算超时，又因为 0& 任何数都是 0
     *  当 结果为 0 时, 即可返回 0
     * */
    public int rangeBitwiseAnd(int m, int n) {

        int result = m, i = m + 1;
        while(i<=n && i > 0 && result != 0){
            result &= i;
            i++;
        }
        return result;
    }

    /**
    * 解题思路2: 给定两个整数，找到它们对应的二进制字符串的公共前缀
     * 还有一个位移相关的算法叫做「Brian Kernighan 算法」，它用于清除二进制串中最右边的 1。
     *
     * Brian Kernighan 算法的关键在于我们每次对 number 和 number−1 之间进行按位与运算后，number 中最右边的 1 会被抹去变成 0。
     *
    * */
    public int rangeBitwiseAnd_2(int m, int n) {
        while (m < n) {
            // 抹去最右边的 1
            n = n & (n - 1);
        }
        return n;
    }
}
