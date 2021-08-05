package leetcode;

/**
 * 357. 计算各个位数不同的数字个数 [中等， 回溯算法， 动态规划]
 * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10^n。
 *
 * 示例:
 * 输入: 2
 * 输出: 91
 * 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
 */
public class _357_CountNumbersWithUniqueDigits {
    public static void main(String[] args) {
        _357_CountNumbersWithUniqueDigits obj = new _357_CountNumbersWithUniqueDigits();
        int n = 2;
        System.out.println(obj.countNumbersWithUniqueDigits(n));
    }


    public int countNumbersWithUniqueDigits(int n) {
        // dfs(n, true);
        // // 回溯时未算上数字 0
        // return count + 1;

        return DP(n);
    }
    /**
     * 解题思路1： 回溯算法
     */

    int count = 0;
    boolean[] used = new boolean[10];

    public void dfs(int n, boolean isFirst) {
        if (n == 0){
            return;
        }
        int i = isFirst ? 1 : 0;
        for (; i < 10; i++){
            if (used[i] == false){
                used[i] = true;
                count++;
                dfs(n-1, false);
                used[i] = false;
            }
        }
    }

    /**
     * 解题思路2： 动态规划
     * 首先手动定义启动项，n = 0 的时候，return 1
     * 当 n = 1 的时候，也就是只有一位数，自然是有 0 - 9 一共10种选择， return 10
     * 当 n = 2 的时候，第一位有 1 - 9 一共9种选择，个位数就只有 0 - 9 再去掉前面一位一共 9 种选择， 然后再加上 n = 1 时候的总数， 也就是 return 9 * 9 + 10
     * 当 n = 3 的时候，第一位还是 1 - 9 一共 9 种， 第二位依然是 0 - 9 -1， 一共 9种， 第三位已经去掉了两位，就是 8 种, 所以最后return 9 * 9 * 8 + 9 * 9 + 10
     * n = 4, return 9 * 9 * 8 * 7 + 9 * 9 * 8 + 9 * 9 + 10
     * ...
     * 以此类推
     * */
    public int DP(int n){
      if ( n== 0){
          return 1;
      }
      if (n == 1){
          return 10;
      }
      //
      int sum = 9, uselessNumCount = 9;
      for (int i = 1; i < n; i++){
          sum *= uselessNumCount--;
      }
      return sum + DP(n - 1);
    }
}
