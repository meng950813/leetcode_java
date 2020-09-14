/**
 * 637. 二叉树的层平均值 [简单, 树]
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 *
 * 示例 1：
 * 输入：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出：[3, 14.5, 11]
 * 解释：第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
 * 提示：节点值的范围在32位有符号整数范围内。
 * */
import java.util.*;
public class _637_AverageOfLevelsInBinaryTree {
    public static void main(String[] args) {

    }

    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null){
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        double sum;
        int i, size;
        TreeNode node = null;
        while (!queue.isEmpty()){
            sum = 0;
            for (i = 0, size = queue.size(); i < size; ++i){
                node = queue.poll();
                sum += node.val;

                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
            result.add(sum / size);
        }
        return result;
    }
}
