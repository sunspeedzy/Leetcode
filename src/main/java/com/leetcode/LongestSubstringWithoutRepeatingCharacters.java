package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestSubstringWithoutRepeatingCharacters {
    /**
     * 使用 HashMap 实现
     * i 用来遍历当前位置，j 用来指向当前子字符串的起始位置。
     * 遍历时，当发现HashMap中已存在当前位置 i 的字符时，将 j 挪到重复字符当前位置的下一个位置。
     * 遍历时，将当前位置的字符以及当前的位置放入HashMap，并将存储当前子串长度和当前结果的最大值作为新的结果。
     *
     * Time: O(n)
     * Space: O(n)
     *
     * @param s 输入字符串
     * @return 返回结果
     */
    private static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    /**
     * 使用 HashSet 实现
     * i 用来遍历当前位置，j 用来指向当前子字符串的起始字母的第一个位置
     * 遍历时，当HashSet中已存在当前位置 i 的字符时，从HashSet中删除位置 j 的字符，并将 j+1。
     *        判断这时位置 j 的字符是否和 位置 i 的字符相同，相同且 j < i 则再把 j++，
     *        使 j 指向当前新的子字符串的起始字母的位置
     * 遍历时，当HashSet中，将位置 i 的字符放入HashSet，
     *         并将存储当前子串长度和当前结果的最大值作为新的结果。
     *
     * Time: O(n)
     * Space: O(n)
     *
     * @param s 输入字符串
     * @return 返回结果
     */
    private static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        HashSet<Character> set = new HashSet<>();
        int res = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                set.remove(s.charAt(j++));
                if (s.charAt(j)==s.charAt(i) && j < i){
                    j++;
                }
            }
            set.add(s.charAt(i));
            res = Math.max(res, set.size());
        }
        return res;
    }

    public static void main(String[] args) {
//        String s = "abaabcbb";
//        String s = "pwwkewc";
//        String s = "bbbbb";
        String s = "pwwwkewc";
        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstring2(s));
    }
}
