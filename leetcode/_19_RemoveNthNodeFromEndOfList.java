package leetcode;

/**
 * 19. 删除链表的倒数第N个节点 [中等，双指针]
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * 说明：
 * 给定的 n 保证是有效的。
 *  
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 */
public class _19_RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5};
        int n = 5;
        ListNode head = ListNode.generateListNode(nums);
        System.out.println(removeNthFromEnd(head, n));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n){
        if (head == null || n < 0){
            return head;
        }
        ListNode pre = head, p = head;
        int count = 0;
        while (count <= n && p != null){
            p = p.next;
            count++;
        }
        if (p == null){
            if (count == n){
                return head.next;
            }
            return head;
        }
        while (p != null){
            pre = pre.next;
            p = p.next;
        }
        if (pre.next != null){
            pre.next = pre.next.next;
        }
        return head;
    }
}
