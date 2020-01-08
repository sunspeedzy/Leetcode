package com.leetcode;

import java.util.HashMap;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 *
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSum {
    /**
     * 利用HashMap将数组元素值和其相应的索引组成key-value，每次装载时判断 target-当前key的差，
     * 是否已作为key存在于HashMap中，有则返回这两个key的value组成的结果数组，无则将当前key-value放入HashMap中
     * @param nums 给定的数组
     * @param target 给定的两个数组元素的加和目标值
     * @return 返回这两个加和等于目标值的数组元素的索引，升序排列
     */
    private static int[] twoSum(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums == null || nums.length < 2) {
            return res;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])) {
                res[0] = map.get(target-nums[i]);
                res[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = new int[] {2, 7, 11, 15};
        int target = 17;
        int[] res = twoSum(nums, target);
        System.out.println(res[0]+","+res[1]);
    }
}
