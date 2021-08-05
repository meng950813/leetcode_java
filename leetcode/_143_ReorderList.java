package leetcode;

import java.util.*;

/**
 * 143. 重排链表 [中等]
 * 给定一个单链表L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 
 * 示例1:
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 
 * 示例 2:
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 */
public class _143_ReorderList {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        ListNode list = ListNode.generateListNode(nums);
        reorderList(list);
    }

    /**
     * 解题思路1：
     * */
    public static void reorderList(ListNode head) {
        Deque<ListNode> deque = new ArrayDeque<>();
        ListNode p = head, q = head;
        while(p != null){
            deque.push(p);
            p = p.next;
        }
        p = deque.pop();
        while (q != null && p != q && p.next != q){
            p.next = q.next;
            q.next = p;
            q = p.next;
            p = deque.poll();
        }
        q.next = null;
    }
}
