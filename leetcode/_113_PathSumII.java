package leetcode;

/**
 *113. 路径总和 II [中等，深度优先搜索]
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
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
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 * */
import java.util.*;
public class _113_PathSumII {
    public static void main(String[] args) {
        _113_PathSumII obj = new _113_PathSumII();
        Integer[] arr = {5,4,8,11,null,13,4,7,2,null,null,5,1};
        int sum = 22;
//        Integer[] arr = {-2,null,-3};
//        int sum = -5;
        TreeNode root = TreeNode.generateTree(arr);
        obj.pathSum(root, sum);
        for (List<Integer> path: obj.result){
            System.out.println(path);
        }
    }

    public List<List<Integer>> result = new ArrayList<>();
    public Stack<Integer> path = new Stack<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, sum);
        return result;
    }

    public void dfs(TreeNode root, int sum){
        if (root == null){
            return;
        }
//        path.add(root.val);
        path.push(root.val);
        if (root.val == sum && root.left == null && root.right == null){
            result.add(new ArrayList<>(path));
//            path.remove(path.size() - 1);
            path.pop();
            return;
        }
        dfs(root.left, sum - root.val);
        dfs(root.right, sum - root.val);
//        path.remove(path.size() - 1);
        path.pop();
    }
}
