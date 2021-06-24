package src.leetcode;

public class ListNode {
    int val;
    ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;

        Object obj = new Object();
    }

    public static ListNode generateListNode(int[] nums){
        ListNode head = new ListNode(-1), p = head;
        for (int item: nums){
            p.next = new ListNode(item);
            p = p.next;
        }
        return head.next;
    }


    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        ListNode p = this;
        while (p != null){
            if (count > 0){
                buffer.append(",");
            }
            count++;
            buffer.append(p.val);
            p = p.next;
        }
        return buffer.toString();
    }
}

