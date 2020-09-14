/**
 * 94. 二叉树的中序遍历 [中等]
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * */

import java.util.*;
public class _94_BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        Integer[] nums = new Integer[]{1,null,2,3};
        TreeNode root = TreeNode.generateTree(nums);
        inorderTraversal(root);
    }


    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        // 递归方式
        // inorder(result, root);

        // 非递归方法
        inorder_without_recursion(result, root);
        for (Integer node : result){
            System.out.print(node + "\t");
        }
        System.out.println();
        return result;
    }

    /**
     * 非递归方式： 利用栈的相关特性，实现递归的效果
     * */
    public static void inorder_without_recursion(List<Integer> result, TreeNode root){
        Deque<TreeNode> deque = new LinkedList<>();
        while (root != null || !deque.isEmpty()){
            while (root != null){
                deque.push(root);
                root = root.left;
            }
            root = deque.pop();
            result.add(root.val);
            root = root.right;
        }

    }

    /**
     * 递归方式
     * */
    public static void inorder(List<Integer> result, TreeNode root){
        if(root == null){
            return;
        }
        inorder(result, root.left);
        result.add(root.val);
        inorder(result, root.right);
    }
}
