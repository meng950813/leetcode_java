/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 *
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * */

import java.util.*;
public class _137_SingleNumber_II {
    public static void main(String[] args) {

    }

    public static int singleNumber(int[] nums){
        Map<Integer, Integer> map = new HashMap();
        int repeat_time = 0;
        for (int i = 0; i < nums.length; i++){
            repeat_time = map.containsKey(nums[i])? map.get(nums[i]) + 1: 1;
            map.put(nums[i], repeat_time);
        }
        for (int key : map.keySet()){
            if (map.get(key) == 1){
                return key;
            }
        }
        return nums[0];
    }
}
