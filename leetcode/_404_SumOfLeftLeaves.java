package leetcode;

/**
 * 404. 左叶子之和 [简单]
 * 计算给定二叉树的所有左叶子之和。
 * 示例：
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 */
public class _404_SumOfLeftLeaves {
    public static void main(String[] args) {

    }

    /**
     * 解题思路： 前序遍历获得叶子节点， 通过使用 is_left 参数标记当前 叶子是否为左叶子节点
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return compute(root.left, true) + compute(root.right, false);
    }

    public int compute(TreeNode root, boolean is_left) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return is_left ? root.val : 0;
        }
        return compute(root.left, true) + compute(root.right, false);
    }

}
