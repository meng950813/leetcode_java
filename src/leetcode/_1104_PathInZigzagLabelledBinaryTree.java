package leetcode;

/**
 * 1104. 二叉树寻路 [中等，数学]
 * 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。
 * 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
 * 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
 * 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
 *                  1
 *           /            \
 *          3                2
 *       /    \           /    \
 *      4      5        6       7
 *    /  \   /  \     /  \     / \
 *  15   14 13  12   11  10   9   8
 *
 * 示例 1：
 * 输入：label = 14
 * 输出：[1,3,4,14]
 * 
 * 示例 2：
 * 输入：label = 26
 * 输出：[1,2,6,10,26]
 * 
 * 提示：1 <= label <= 10^6
 */

import java.util.*;

public class _1104_PathInZigzagLabelledBinaryTree {
    public static void main(String[] args) {
        List<Integer> result = pathInZigZagTree(2);
        for (Integer item: result){
            System.out.print(item + "\t");
        }
    }

    /**
     * 分析：二叉树的数字连续，且满足完全二叉树性质
     * 若是普通完全二叉树，即数字从左到右增长,根节点为 1，父子节点满足 n, 2n, 2n + 1 的关系
     * 偶数行从右到左， 顺序与普通完全二叉树相反，但组成的数字完全相同即： 2,3 ==> 3,2
     * 每层的数字存在对称关系， 以第3层 4，5，6，7 为例， 最小值 2^(3-1) = 4, 最大值 2^3 - 1 = 7
     * 4 + 7 = 11, 5 + 6 = 11
     * 参照 示例 1
     * label = 14,
     * 对应普通完全二叉树，其父节点为 14 / 2  = 7
     * 在本树中 父节点为 4 = 11 - 7
     * 验证其他节点可得出规律：节点 n 的父节点为  m - (n/2)
     * 其中 m 为节点 n 上一层的 最大节点与最小节点之和
     * */

    /**
     * 解题思路：
     * 1. 通过 label 获取 当前节点所在层数 level， 若 n == 1 表示已到根节点，返回
     * 2. 基于 level 得出上一层 左右节点之和 sum = 2^(level-2) + 2^(level-1) -1
     * 3. sum - label / 2 得出label的父节点 p
     * 4. label = p, 重复步骤 1
     * */
    public static List<Integer> pathInZigZagTree(int label) {
        List<Integer> result = new ArrayList<>();
        int level = 1, max = 2, sum = 0;
        while (label >= max){
            max = max * 2;
            level++;
        }
        while (label >= 1){
            level--;
            result.add(0, label);
            sum = (int)(Math.pow(2,level - 1) + Math.pow(2, level) -1);
            label = sum - label / 2;
        }
        return result;
    }
}
