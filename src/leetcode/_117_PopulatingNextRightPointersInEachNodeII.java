package leetcode;

/**
 * 117. 填充每个节点的下一个右侧节点指针 II [中等， 深度优先搜索]
 *
 * 给定一个二叉树
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 * */
import java.util.*;
public class _117_PopulatingNextRightPointersInEachNodeII {
    public static void main(String[] args) {
        
    }

    /**
     * 层序遍历
     * */
    public static Node levelOrder(Node root){
        if (root == null){
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node pre = null, next = null;
            int size = queue.size();
            for (int i = 0; i < size; i++){
                next = queue.poll();
                if (next.left != null){
                    queue.offer(next.left);
                }
                if (next.right != null){
                    queue.offer(next.right);
                }
                if (i != 0){
                    pre.next = next;
                }
                pre = next;
            }
        }
        return root;
    }
}
