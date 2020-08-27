import java.util.ArrayList;

/**
 * 109. 有序链表转换二叉搜索树 [中等, 深度优先搜素]
 *
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *      0
 *    /   \
 *   -3    9
 *   /    /
 * -10   5
 */

public class _109_ConvertSortedList2BinarySearchTree {
    public static void main(String[] args) {
        _109_ConvertSortedList2BinarySearchTree obj = new _109_ConvertSortedList2BinarySearchTree();

    }
    /**
     * 解题思路：
     * 1. 将链表转为 有序数组
     * 2. 使用 二分法 遍历数组 构建二叉树
     * */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null){
            return null;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        ListNode p = head;
        while (p != null){
            list.add(p.val);
            p = p.next;
        }
        int length = list.size();
        return buildTree(list, 0, length - 1);
    }

    public TreeNode buildTree(ArrayList<Integer> list, int left, int right){
        if (left > right){
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = buildTree(list, left, mid-1);
        root.right = buildTree(list, mid +1, right);
        return root;

    }

}

class ListNode2 {
    int val;
    ListNode next;

    ListNode2() {}

    ListNode2(int val) {
        this.val = val;
    }

    ListNode2(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
