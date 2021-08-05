package LCP;
/**
 * LCP 07. 传递信息 [简单]
 *
 * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
 * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
 * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
 * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
 * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
 *
 * 示例 1：
 * 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
 * 输出：3
 * 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
 *
 * 示例 2：
 * 输入：n = 3, relation = [[0,2],[2,1]], k = 2
 * 输出：0
 * 解释：信息不能从小 A 处经过 2 轮传递到编号 2
 *
 * 限制：
 * 2 <= n <= 10
 * 1 <= k <= 5
 * 1 <= relation.length <= 90, 且 relation[i].length == 2
 * 0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
 * */

import javax.swing.plaf.SliderUI;
import java.util.*;
public class _07_ChuanDiXinXi {
    public static void main(String[] args) {
        int n = 5;
        int k = 3;
        int[][] relation = new int[][]{{0,2},{2,1},{3,4},{2,3},{1,4},{2,0},{0,4}};
        System.out.println(numWays(n, relation, k));
    }
    public static int numWays(int n, int[][] relation, int k) {
        // 解题思路1 ： 深度优先遍历
        // return Solution1(n, relation, k);

        // 解题思路2： 动态规划
//        return Solution2(n, relation, k);

        // 解题思路3： 动态规划 + 空间优化
        return Solution3(n, relation, k);
    }

    /**
     * 解题思路3： 动态规划 + 空间优化
     *  从思路2中看出，每次迭代只与 dp[i][] 和 dp[i-1][]相关，因此可以考虑将dp数组压缩为两个一维数组，减小空间复杂度
     * */
    public static int Solution3(int n, int[][] relaion, int k){
        int[] dp = new int[n];
        int[] tmp;
        dp[0] = 1;
        for (int i = 1; i <= k; i++){
            tmp = new int[n];
            for (int[] rel: relaion){
                tmp[rel[1]] += dp[rel[0]];
            }
            dp = tmp;
        }
        return dp[n-1];
    }
    /**
     * 解题思路2： 动态规划
     * 1. 定义二维数组 dp[k+1][n], dp[i][j] 表示 第i步 消息传递到 j 的路径数
     *      初始情况下， dp[0][0] = 1
     * 2. 若 下一步能到 j 的人是 x1,x2,..,xm
     *      则 dp[i+1][j] = dp[i][x1] + dp[i][x2]+ .. + dp[i][xm]
     * 最终结果位于
     *      dp[k][n-1]
     * */
    public static int Solution2(int n, int[][] relaion, int k){
        int[][] dp = new int[k+1][n];
        dp[0][0] = 1;
        for (int i = 1; i <= k; i++){
            for (int[] rel: relaion){
                dp[i][rel[1]] += dp[i-1][rel[0]];

            }
        }
        return dp[k][n-1];

    }

    /**
     * 解题思路1：深度优先遍历
     * 1. 通过构建hash数组记录每个人 下一跳 的组合
     * 2. 从 0 开始进行深度优先遍历，判断第 k 步是否为目标，若是则 可能的路径数 + 1， 否则 + 0
     * */
    public static int Solution1(int n, int[][] relation, int k){
        Map<Integer, List<Integer>> map = new HashMap<>();
        int source, target, len = relation.length;
        List<Integer> container = null;
        for(int i =0; i < len; i++){
            source = relation[i][0];
            target = relation[i][1];
            if (map.containsKey(source)){
                container = map.get(source);
            }
            else{
                container = new ArrayList<>();
            }
            container.add(target);
            map.put(source, container);
        }
        return dfs(map, 0, n - 1, k);
    }

    public static int dfs(Map<Integer, List<Integer>> map, int source, int target, int step){
        if (step == 0){
            if (target == source){
                return 1;
            }
            return 0;
        }
        if (!map.containsKey(source)){
            return 0;
        }
        int count = 0;
        for (Integer s : map.get(source)){
            count += dfs(map, s, target, step - 1);
        }
        return count;
    }
}
