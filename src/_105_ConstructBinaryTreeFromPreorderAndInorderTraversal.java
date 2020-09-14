/**
 * 105. 从前序与中序遍历序列构造二叉树 [中等， 深度优先搜索]
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */

import java.util.*;

public class _105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
//        TreeNode root = buildTree()
    }

    /**
     * 解题思路：前序： 根节点在最前面， 中序： 跟节点在中间
     * 1. 通过前序遍历找到根节点， 在中序遍历中找到根结点；
     * 2. 利用中序遍历的根节点确定 左右子树的中序遍历结果及其对应节点数量left, right；
     * 3. 在前序遍历中，根节点之后 left 长度的为左子树的前序遍历结果， 再之后 right 长度的元素为右子树的前序遍历结果
     * 4. 通过递归求解左右子树
     * 做法： 为快速找到根节点在中序遍历中的位置，使用hashmap保存中序遍历下的节点值及下标
     * */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i) {
            map.put(inorder[i], i);
        }
        return fun(preorder, 0, preorder.length - 1, map, 0, inorder.length - 1);
    }

    public static TreeNode fun(int[] preorder, int p_start, int p_end, Map<Integer, Integer> inorder, int i_start, int i_end) {
        if (p_start > p_end) {
            return null;
        }
        // 初始化根节点
        TreeNode root = new TreeNode(preorder[p_start]);
        int index = inorder.get(preorder[p_start]);
        // 左子树元素数
        int left = index - i_start;
        // 添加左子树
        root.left = fun(preorder, p_start + 1, p_start + left, inorder, i_start, index - 1);
        // 添加右子树
        root.right = fun(preorder, p_start + left + 1, p_end, inorder, index + 1, i_end);
        return root;
    }
}
