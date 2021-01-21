package leetcode;

import java.util.List;

/**
 * 328. 奇偶链表 [中等]
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 *
 * 示例 2:
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 *
 * 说明:
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 */
public class _328_OddEvenLinkedList {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5};
        ListNode listNode = ListNode.generateListNode(nums);
        oddEvenList(listNode);
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null){
            return head;
        }
        ListNode oddHead = head, p = oddHead, evenHead = head.next, q = evenHead;
        head = q.next;
        while(head != null){
            p.next = head;
            p = p.next;
            head = head.next;
            p.next = null;
            if (head != null){
                q.next = head;
                q = q.next;
                head = head.next;
            }else {
                q.next = null;
            }
        }
//        print(oddHead);
//        print(evenHead);
        p.next = evenHead;
        print(oddHead);
        return oddHead;
    }

    public static ListNode oddEvenList2(ListNode head){
        if (head == null){
            return null;
        }
        ListNode odd = head, evenHead = head.next, even = evenHead;
        while(even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    public static void print(ListNode head){
        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }
}
