package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. 从中序与后序遍历序列构造二叉树 [中等, DFS]
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * 注意: 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *      3
 *     / \
 *    9  20
 *      /  \
 *     15   7
 */
public class _106_ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static void main(String[] args) {
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        int[] postorder = new int[]{9, 15, 7, 20, 3};
        TreeNode root = buildTree(inorder, postorder);
        inorder(root);
        System.out.println();
        postorder(root);
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inorder_map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorder_map.put(inorder[i], i);
        }
        return DFS(inorder_map, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public static TreeNode DFS(Map<Integer, Integer> inorder_map, int in_start, int in_end, int[] postorder, int p_start, int p_end) {
        if (in_start > in_end || p_start > p_end) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[p_end]);
        int index = inorder_map.get(postorder[p_end]);
        int left_len = index - in_start - 1;
        int right_len = in_end - index;
        root.left = DFS(inorder_map, in_start, index - 1, postorder, p_start, p_start + left_len);
        root.right = DFS(inorder_map, index + 1, in_end,  postorder, p_end - right_len , p_end - 1);
        return root;
    }

    public static void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.val + ", ");
        inorder(root.right);
    }

    public static void postorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        inorder(root.right);
        System.out.print(root.val + ", ");
    }
}
