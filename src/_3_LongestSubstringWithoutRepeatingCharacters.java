public class _3_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        int len = lengthOfLongestSubstring("bbtatlud");
        System.out.println(len);
    }

    public static int lengthOfLongestSubstring(String s) {
        /*
        * 设置一个窗口，初始大小为 0，保证窗口内的元素都不重复
        * 遍历字符串
        *   检查下标为 i 的字符 在 [i-widow, i) 的窗口范围内是否存在
        *   若不存在：
        *       window ++; 保存窗口最大长度；
        *   否则：
        *       找到相同元素在窗口内的下标，缩小窗口大小为 ： window - index;
        * */
        int window = 0, index = -1, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            String sr = s.substring(i - window, i);
            char c = s.charAt(i);
            index = s.substring(i - window, i).indexOf(s.charAt(i));
            if (index == -1){
                /* 下标为 i 的字符不在窗口内, 窗口 + 1 */
                window++;
                if (window > maxLen){
                    maxLen = window;
                }
            }
            else{
                window -= index;
            }
        }
        return maxLen;
    }


}
