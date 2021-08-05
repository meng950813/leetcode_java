package leetcode; /**
 * 93. 复原IP地址 (中等，回溯算法)
 * <p>
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 * <p>
 * 示例:
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 */

import java.util.ArrayList;
import java.util.List;

public class _93_Restore_IP_Addresses {

    final static int IP_SLICE_LEN = 4;
    List<String> result = new ArrayList<String>();
    int[] ip_part = new int[IP_SLICE_LEN];

    public List<String> restoreIpAddresses(String s) {
        dfs(s, 0, 0);
        return result;
    }

    /**
     * 深度遍历 => 回溯 + 剪枝
     */
    public void dfs(String ip_str, int start, int part_id) {
        /**
         * 遍历完整个字符串，判断是否分为4部分
         * */
        if (part_id == IP_SLICE_LEN) {
            if (start == ip_str.length()) {
                /**
                 * 获取一种划分结果
                 * */
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < IP_SLICE_LEN; i++) {
                    buffer.append(ip_part[i]);
                    if (i != IP_SLICE_LEN - 1) {
                        buffer.append(".");
                    }
                }
                result.add(buffer.toString());
            }
            return;
        }

        /**
         * 字符串遍历完，ip不为4段，错误结果，直接返回
         * */
        if (start == ip_str.length()) {
            return;
        }

        /**
         * 若当前片段，首位为 0，为避免 010 这种情况，该位只能为0
         * */
        if (ip_str.charAt(start) == '0') {
            ip_part[part_id] = 0;
            dfs(ip_str, start + 1, part_id + 1);
        }

        /**
         * 循环遍历所有可能
         * */
        int ip = 0;
        for (int i = start; i < ip_str.length(); i++) {
            ip = ip * 10 + (ip_str.charAt(i) - '0');
            if (ip > 255) {
                break;
            }
            ip_part[part_id] = ip;
            dfs(ip_str, i + 1, part_id + 1);
        }
    }


    public static void main(String[] args) {

    }
}
