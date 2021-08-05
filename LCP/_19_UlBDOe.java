package LCP;

/**
 * LCP 19. 秋叶收藏集 [中等, 动态规划]
 * 小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves， 字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
 * 出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。
 * 
 * 示例 1：
 * 输入：leaves = "rrryyyrryyyrr"
 * 输出：2
 * 解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"
 * 
 * 示例 2：
 * 输入：leaves = "ryr"
 * 输出：0
 * 解释：已符合要求，不需要额外操作
 * 
 * 提示：
 * 3 <= leaves.length <= 10^5
 * leaves 中只包含字符 'r' 和字符 'y'
 */
public class _19_UlBDOe {
    public static void main(String[] args) {
        int res = minimumOperations("ryyryyyrryyyyyryyyrrryyyryryyyyryyrrryryyyryrryrrrryyyrrrrryryyrrrrryyyryyryrryryyryyyyryyrryrryryy");
        System.out.println(res);
    }

    /**
     *用 f[i][j] 表示对于第 0 片到第 ii 片叶子（记为 leaves[0..i]）进行调整操作，并且第 i 片叶子处于状态 j 时的最小操作次数。
     * 在推导状态转移方程时，我们可以分别对于每一种状态进行分析。
     * 当 j=0 时，我们需要将第 ii 片叶子变成红色，并且第 i-1 片叶子也只能处于 j=0 的状态（因为没有编号更小的状态了），因此有状态转移方程：
     * f[i][0]=f[i−1][0]+isYellow(i),其中 isYellow(i) 为示性函数，当第 i 片叶子为黄色时为 1，红色时为 0。
     *
     * 当 j=1 时，我们需要将第 i 片叶子变成黄色，而第 i-1 片叶子既可以处于 j=1 的状态，也可以处于 j=0 的状态，选择其中较小值，因此有状态转移方程：
     * f[i][1]=min{f[i−1][0],f[i−1][1]}+isRed(i), 其中 isRed(i) 为示性函数，当第 i 片叶子为红色时为 1，黄色时为 0。
     *
     * 当 j=2 时，我们需要将第 i 片叶子变成红色，而第 i-1 片叶子即可以处于 j=2 的状态，也可以处于 j=1 的状态
     * （注意这里不能处于 j=0 的状态，因为每一种状态包含的叶子数量必须至少为 1），我们选择其中较小值，因此有状态转移方程：
     * f[i][2]=min{f[i−1][1],f[i−1][2]}+isYellow(i)
     *
     * 最终的答案即为 f[n−1][2]，其中 n 是字符串 leaves 的长度，也就是树叶的总数。
     *
     * 细节
     * 因为每一种状态包含的叶子数量必须至少为 1，因此不仅需要特别注意 j=2 时的状态转移方程，还需要考虑每个状态本身是否是符合要求的。
     * 对于状态 f[i][j]而言，它包含了 leaves[0..i] 共 i+1 片叶子以及 j+1 个状态，因此 叶子的数量必须大于等于状态的数量，即满足 i≥j。
     * 这样一来，所有 i < j 的状态 f[i][j] 都是不符合要求的。观察上面的状态转移方程，我们在每一步转移时都是取最小值，
     * 因此我们可以将所有不符合要求的状态置为一个极大值（例如 n+1 或整数类型的上限等）。
     *
     * 同时需要注意的是，当 i=0 时，f[i][..] 会从 f[i-1][..] 转移而来，但后者是没有意义的，因此我们需要对 f[i][..] 进行特殊处理。
     * 由于当 i=0 时，j 也只能为 0，因此我们有：
     * f[0][0]=isYellow(0)
     * 作为唯一的边界条件。
     */
    public static int minimumOperations(String leaves) {
        int len = leaves.length();
        int[][] dp = new int[len][3];
        dp[0][0] = leaves.charAt(0) == 'r' ? 0 : 1;
        dp[0][1] = dp[0][2] = dp[1][2] = Integer.MAX_VALUE;

        int isRed, isYellow;
        for (int i = 1; i < len; i++) {
            isRed = leaves.charAt(i) == 'r' ? 1 : 0;
            isYellow = leaves.charAt(i) == 'y' ? 1 : 0;
            dp[i][0] = dp[i - 1][0] + isYellow;
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + isRed;
            if (i > 2) {
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + isYellow;
            }
        }
        return dp[len - 1][2];
    }


    /**
     * 解题思路：
     * 前i片时共有三种组合模式。
     * 1. r模式，即全为r。
     * 2. ry模式。
     * 3. ryr模式。
     * 
     * 定义变量 r, ry, ryr 表示加入第 i 片叶子变为对应模式的最少操作次数
     * 当第 i 片树叶为 r 时：
     * 1. r模式，可以直接组成 r 模式，无需修改，最少操作次数 r = r
     * 2. ry模式，将本片树叶修改为y，前i-1片是 r模式 或 ry模式 均可，取两者中较小的：ry = min(r, ry) + 1
     * 3. ryr模式，与前i-1片树叶的ry模式或者ryr模式直接组合即可，无需修改，取两者中较小的：ryr= min(ry, ryr)
     * 
     * 当第 i 片树叶为 y 时：
     * 1. r模式，r = r + 1
     * 2. ry模式，ry = min(r, ry)
     * 3. ryr模式，ryr = min(ry, ryr) + 1
     * 
     * 因此状态转移如下：
     * 如果第i片为r：
     * 1. r模式：r(i) = r(i-1)
     * 2. ry模式：ry(i) = min(r(i-1), ry(i-1)) + 1
     * 3. ryr模式：ryr(i) = min(ry(i-1), ryr(i-1))
     * 
     * 如果第i片为y：
     * 1. r模式：r(i) = r(i-1) + 1
     * 2. ry模式：ry(i) = min(r(i-1), ry(i-1))
     * 3. ryr模式：ryr(i) = min(ry(i-1), ryr(i-1)) + 1
     * 
     * 边界值：
     * 第0片为边界，如果第0片为r，则r = 0，否则为 1，
     * ry、ryr 的值与前 i-1 片叶子相关，因此全部初始化为：MAX_VALUE - 1
     * 注： 不初始化为 MAX_VALUE 是因为  MAX_VALUE + 1 => MIN_VALUE
     */
    public static int minimumOperations_2(String leaves) {
        int r = leaves.charAt(0) == 'r' ? 0 : 1, ry = Integer.MAX_VALUE - 1, ryr = Integer.MAX_VALUE - 1;
        // 临时变量，用于记录前 i -1 片叶子的最少操作数
        int last_ry, last_ryr;
        for (int i = 1, len = leaves.length(); i < len; i++) {
            last_ry = ry;
            last_ryr = ryr;
            if (leaves.charAt(i) == 'r') {
                ry = Math.min(r, last_ry) + 1;
                ryr = Math.min(last_ry, last_ryr);
            } else {
                // 此处 +1 可能导致整数越界变为最小负数， 因此初始化时, ry、ryr 为最大值 - 1
                ryr = Math.min(last_ry, last_ryr) + 1;
                ry = Math.min(r, last_ry);
                r++;
            }
        }
        return ryr;
    }
}
