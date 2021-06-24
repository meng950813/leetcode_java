package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * 234. 回文链表 [简单]
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * */
public class _234_PalindromeLinkedList {
    public static void main(String[] args) {
        int[] nums = {-129,-129};
        ListNode list = ListNode.generateListNode(nums);
        System.out.println(isPalindrome2(list));
    }

    /**
     * 解题思路1： 节点入栈
     * */
    public static boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        Deque<ListNode> deque = new ArrayDeque<>();
        ListNode p = head;
        while(p != null){
            deque.push(p);
            p = p.next;
        }
        p = head;
        while (p != null){
            if (p.val != deque.pop().val){
                return false;
            }
            p = p.next;
        }
        return true;
    }

    /**
     * 取节点值，加入数组中，比较数组中的内容是否回文
     * */
    public static boolean isPalindrome2(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        ArrayList<Integer> list = new ArrayList<>();
        ListNode p = head;
        while (p != null){
            list.add(p.val);
            p = p.next;
        }
        for (int i = 0, j = list.size() - 1; i < j; ++i, --j){
            System.out.println(list.get(i));
            System.out.println(list.get(j));
//            if (list.get(i) != list.get(j)){
//                return false;
//            }

            if (!list.get(i).equals(list.get(j))) {
                return false;
            }
        }
        return true;
    }
}
