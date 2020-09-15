package leetcode;

import java.util.*;

/**
 * 202. 快乐数 [简单, 哈希表]
 * 
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为 1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 *
 * 示例：
 * 输入：19
 * 输出：true
 *
 * 解释：
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
public class _202_HappyNumber {
    public static void main(String[] args) {
        _202_HappyNumber obj = new _202_HappyNumber();
        System.out.println(obj.isHappy(2));
    }

    /**
     * 解题思路1： 哈希表记录已出现的数，若有重复，则陷入死循环
     * */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)){
            n = compute(n);
            set.add(n);
        }
        return 1 == n;
    }

    /**
     * 解题思路2：快慢指针
     *  若将每一步计算出的值看做链表上的节点，则 该题目可理解为 判断链表中是否存在值为1的节点或 环
     *  针对链表是否存在环的问题， 快慢指针是常用解法
     * */
    public boolean isHappy_2(int n) {
        int low = n, fast = compute(n);
        while (fast != 1 && fast != low){
            low = compute(low);
            fast = compute(compute(fast));
        }
        return fast == 1;
    }

    public int compute(int n){
        int temp = 0;
        while (n!=0){
            temp += Math.pow(n%10, 2);
            n /= 10;
        }
        return temp;
    }
}
