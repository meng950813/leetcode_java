package leetcode;

/**
 * 112. 路径总和 [简单， 深度优先遍历]
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2
 * */
public class _112_PathSum {
    public static void main(String[] args) {

    }

    /**
     * 解题思路1： DFS 遍历，找到叶子节点， 如果此时 sum == 0, 说明有符合要求的路径
     * */
    public static boolean hasPathSum(TreeNode root, int sum) {
        /** 错误解法：没有判断 root 是否为叶子节点
         *   if(root == null){
         *      return sum == root.val;
         *   }
         *   return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
         *
         * */

        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
