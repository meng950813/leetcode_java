package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 406. 根据身高重建队列 [中等， 贪心算法]
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 * 注意：
 * 总人数少于1100人。
 *
 * 示例
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class _406_QueueReconstructionByHeight {
    public static void main(String[] args) {

    }

    /**
     * 解题思路：
     * 1. 按照身高降序排序， 若身高相同，则按照 k 的升序排序
     * 2. 依次将每个人放入队列中，当放入第 i个人时：
     *   2.1 第 0 ~ i−1 已经在队列中被安排了位置，他们只要站在 i 前面，就会对 i产生影响，因为他们都比 i 高；
     *   2.2 而第 i+1 ~ n−1 还没有被放入队列中，并且他们无论站在哪里，对 i 都没有任何影响，因为他们都比 i 矮。
     * 3. 由于后面的人不会对 i 造成影响，可以采用「插空」的方法，依次给每一个人在当前的队列中选择一个插入的位置。
     *    也就是说，当放入 i 时，只需要将其插入队列中，使得他的前面恰好有 ki 个人即可。
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        /**
         * 排序，h 降序，k 升序
         */
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });

        List<int[]> ans = new ArrayList<int[]>();
        // 插入
        for (int[] person : people) {
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
