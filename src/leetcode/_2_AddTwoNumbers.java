package leetcode;

/**
 * 2. 两数相加 [中等]
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *  
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class _2_AddTwoNumbers {

    public static void main(String args[]) {
        int[] a = new int[]{4};
        int[] b = new int[]{8, 5};
        ListNode l1 = generateLink(a);
        ListNode l2 = generateLink(b);
        printList(addTwoNumbers(l1, l2));
    }

    /**
     * 解题思路：创建新链表保存结果，新链表包含两个指针： 头指针及移动指针 head
     * 1. 遍历 两个链表，直到全部为 null, 设置变量 ten = 0 用于保存两数之和的 十位
     *  1.1. 取链表中节点的值 t1, t2， 若有节点为 null，取值为 0
     *  1.2. 计算 t1, t2, ten 之和 sum，sum % 10 的结果创建新节点，作为 head 节点的后续； ten = sum / 10
     * 2. 若 ten > 0, 创建值为 ten 的节点，作为 head 的后续
     * 3. 返回 头指针.next
     * */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0), head = result;
        ListNode p1 = l1, p2 = l2;
        int ten = 0;
        while (p1 != null || p2 != null) {
            int t1 = p1 == null ? 0 : p1.val;
            int t2 = p2 == null ? 0 : p2.val;
            head.next = new ListNode((t1 + t2 + ten) % 10);
            ten = (t1 + t2 + ten) / 10;
            head = head.next;
            p1 = p1 == null ? null : p1.next;
            p2 = p2 == null ? null : p2.next;
        }
        if (ten > 0) {
            head.next = new ListNode(ten);
        }
        return result.next;
    }

    public static ListNode generateLink(int[] arr) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        for (int i = 0; i < arr.length; i++) {
            ListNode temp = new ListNode(arr[i]);
            p.next = temp;
            p = p.next;
        }
        return head.next;
    }

    public static void printList(ListNode list) {
        ListNode p = list;
        while (p != null) {
            System.out.print(p.val);
            p = p.next;
        }
    }
}