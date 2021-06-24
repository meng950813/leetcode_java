package leetcode;

/**
 * 235. 二叉搜索树的最近公共祖先 [简单]
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 * 示例 1:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6 
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 *
 * 示例 2:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 *  
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 * */
public class _235_LowestCommonAncestorOfBinarySearchTree {
    public static void main(String[] args) {
        Integer[] tree = new Integer[]{6,2,8,0,4,7,9,null,null,3,5};
        TreeNode root = TreeNode.generateTree(tree);
        int p = 2;
        int q = 8;
        TreeNode res = lowestCommonAncestor(root, p, q);
        System.out.println(res.val);
    }

    /**
     * 从根节点开始遍历；
     * 1. 如果当前节点的值大于  p 和 q 的值，说明  p 和 q 应该在当前节点的左子树，因此将当前节点移动到它的左子节点；
     * 2. 如果当前节点的值小于  p 和 q 的值，说明  p 和 q 应该在当前节点的右子树，因此将当前节点移动到它的右子节点；
     * 3. 如果当前节点的值不满足上述两条要求，那么说明当前节点就是「分岔点」。此时， p 和 q 要么在当前节点的不同的子树中，要么其中一个就是当前节点。
     * */
    public static TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        TreeNode result = root;
        while (true){
            if (result.val > p && result.val > q){
                result = result.left;
            }
            else if (result.val < p && result.val < q){
                result = result.right;
            }
            else {
                break;
            }
        }
        return result;
    }
    
}
