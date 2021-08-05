package leetcode;

/**
 * 968. 监控二叉树 [困难, 深度优先遍历， 动态规划]
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 * 计算监控树的所有节点所需的最小摄像头数量。
 *
 * 示例 1：
 *       0
 *      /
 *     1
 *    / \
 *   0   0
 *  
 * 输入：[0,0,null,0,0]
 * 输出：1
 * 解释：如图所示，一台摄像头足以监控所有节点。
 *
 * 示例 2：
 *            0
 *          /
 *         1
 *        /
 *       0
 *      /
 *     1
 *      \
 *       0
 * 输入：[0,0,null,0,null,0,null,null,0]
 * 输出：2
 * 解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
 */
public class _968_BinaryTreeCameras {
    public static void main(String[] args) {

    }
    public int result = 0;
    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (dfs(root) == 2) {
            result++;
        }
        return result;
    }

    /**
     * 0：节点安装了监视器
     * 1：节点没安装监视器但能被覆盖
     * 2：节点不能被覆盖，需要安装监视器
     */
    private int dfs(TreeNode root) {
        if (root == null){
            return 1;
        }
        int left = dfs(root.left), right = dfs(root.right);
        // 左右子树中有死角，需要安装监视器
        if (left == 2 || right == 2) {
            result++;
            return 0;
        }
        // 左右子树中安装监视器，可白嫖
        else if (left == 0 || right == 0){
            return 1;
        }
        // 没人罩着，需要安装
        else {
            return 2;
        }
    }
}
