package leetcode;

/**
 * 925. 长按键入 [简单，双指针]
 * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 *
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 *
 * 示例 1：
 * 输入：name = "alex", typed = "aaleex"
 * 输出：true
 * 解释：'alex' 中的 'a' 和 'e' 被长按。
 *
 * 示例 2：
 * 输入：name = "saeed", typed = "ssaaedd"
 * 输出：false
 * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
 *
 * 示例 3：
 * 输入：name = "leelee", typed = "lleeelee"
 * 输出：true
 *
 * 示例 4：
 * 输入：name = "laiden", typed = "laiden"
 * 输出：true
 * 解释：长按名字中的字符并不是必要的。
 *
 * 提示：
 * name.length <= 1000
 * typed.length <= 1000
 * name 和 typed 的字符都是小写字母。
 * */
public class _925_LongPressedName {
    public static void main(String[] args) {
        String name = "pyplrz";
        String typed = "ppyypllr";
        System.out.println(isLongPressedName2(name, typed));
    }

    public static boolean isLongPressedName2(String name, String typed) {
        char[] arr_name = name.toCharArray();
        char[] arr_typed = typed.toCharArray();
        if (arr_name.length > arr_typed.length){
            return false;
        }
        int i = 0, j = 0;
        while (i < arr_name.length && j < arr_typed.length){
            if (arr_name[i] == arr_typed[j]){
                i++;
                j++;
            }
            else if (j > 0 && arr_typed[j] == arr_typed[j - 1]){
                j++;
            }
            else{
                return false;
            }
        }
        if (i < arr_name.length){
            return false;
        }
        while (j < arr_typed.length && arr_typed[j] == arr_typed[j - 1]){
            j++;
        }
        return j == arr_typed.length;
    }

    public static boolean isLongPressedName(String name, String typed) {

        int len_name = name.length(), len_typed = typed.length();
        if (len_name == 0 && len_typed == 0){
            return true;
        }
        if (len_name == 0 || len_typed == 0 || len_name > len_typed || name.charAt(0) != typed.charAt(0)){
            return false;
        }

        char p = name.charAt(0), pre = p;
        int index = 0;
        for (int i = 1; i < len_name; i++){
            pre = p;
            p = name.charAt(i);
            while (index < len_typed - 1){
                index++;
                if (typed.charAt(index) == p){
                    break;
                }
                else if (typed.charAt(index) == pre){
                    continue;
                }
                else {
                    return false;
                }
            }
            // typed 剩余字符长度 < name 剩余字符长度
            if (len_typed - index < len_name - i){
                return false;
            }
        }
        // len_typed > len_name
        while (index < len_typed && typed.charAt(index) == p){
            index++;
        }
        return index == len_typed ;
    }
}
