package leetcode;

/**
 * 973. 最接近原点的 K 个点 [中等， 排序， 分治算法]
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 *
 * 示例 1：
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 *
 * 示例 2：
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 *
 * 提示：
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 */
import java.sql.Array;
import java.util.*;
public class _973_KCclosestPoints2Origin {
    public static void main(String[] args) {
        int[][] points = new int[][]{{68,97},{34,-84},{60,100},{2,31},{-27,-38},{-73,-74},{-55,-39},{62,91},{62,92},{-57,-67}};
//        int[][] points = new int[][]{{1,3}, {-2,-2}, {2,3}, {3,2}, {3,3}};
        int k = 3;
        _973_KCclosestPoints2Origin obj = new _973_KCclosestPoints2Origin();
        int[][] res = obj.kClosest2(points, k);
        for (int i = 0; i< res.length; i++){
            System.out.println(res[i][0] + ", " + res[i][1]);
        }
    }

    /**
     * 解题思路： 使用 treeMap 保存键值对 （距离，下标）
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosest(int[][] points, int K) {
        TreeMap<Integer, ArrayList<Integer>> treeMap = new TreeMap<>();
        ArrayList<Integer> indexList = null;
        int distance = 0;
        for (int i = 0; i < points.length; i++){
            distance = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if (treeMap.containsKey(distance)){
                indexList = treeMap.get(distance);
            }else{
                indexList = new ArrayList<>();
            }
            indexList.add(i);
            treeMap.put(distance, indexList);
        }
        int[][] res = new int[K][2];
        int i = 0;
        for (Integer dis : treeMap.keySet()){
            if (i >= K){
                break;
            }
            for (Integer index : treeMap.get(dis)){
                if (i < K){
                    res[i++] = points[index];
                }else{
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 解题思路2： 借鉴快速排序的思想
     */
    public int[][] kClosest2(int[][] points, int K){
        int left = 0, right = points.length - 1, position = -1;
        while (position != K - 1){
            position = partition(points, left, right);
            if (position < K - 1){
                left = position + 1;
            }else if (position > K - 1){
                right = position - 1;
            }
        }

        return Arrays.copyOfRange(points, 0, K);
    }

    /**
     * 将 指定数组 的 指定区间 进行 “一轮快排”
     * @param points 指定数组
     * @param left 左边界
     * @param right 右边界
     * @return 快排一轮后，当前元素所在位置
     */
    public int partition(int[][] points, int left, int right){
        int[] temp = points[left];
        int target = temp[0] * temp[0] +temp[1] * temp[1];
        while (left < right){
            while (left < right && (points[right][0] * points[right][0] + points[right][1] * points[right][1]) >= target) right--;
            swap(points, left, right);
            while (left < right && (points[left][0] * points[left][0] + points[left][1] * points[left][1]) <= target) left++;
            swap(points, left, right);
        }
        points[left] = temp;
        return left;
    }

    public void swap(int[][] points, int i, int j){
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
}
