package com.leetcode;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 *
 * 时间复杂度：O(log(min(n, m)))
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MedianOfTwoSortedArrays {
    /**
     * 时间复杂度出现log，就会使用二分查找
     *
     * 举例：
     * index：0  1  2  3    4   5
     *          L1  R1
     * nums1：3  5  8  9
     * nums2：1  2  7  10  11  12
     *             L2  R2
     *
     * 将 nums1 和 nums2 两个有序数组中的元素，整合为有序数组 nums3，再对 nums3 取中位数。
     * 那么可以对 nums1 和 nums2 分别从左右两个方向进行切分，使其选取出来的数字元素成为nums3的备选。
     * 如果nums3的长度为偶数，就把选取出来的数字求平均，nums3长度为奇数，则选取出来的数字就是中位数。
     * 所以num3中位数的位置，就和nums1、nums2切分后选取数字的位置和其数值有关，
     * 而根据从nums1和nums2选取出的数字进行比较，可以根据更短的有序数组选取数字的位置，调整更长的有序数组选取数字的位置。
     * 这样，再配合二分法查找就可以把时间复杂度控制在 O(log(min(nums1.size, nums2.size)))
     *
     * Time: O(log(min(n, m)))
     *
     * @param nums1 整型有序数组1
     * @param nums2 整型有序数组2
     * @return 返回两个数组结合的中位数
     */
    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 保证这个方法，只处理左短右长的情况，即nums1为短数组
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        // len 存储两个数组的长度和，即整合后的长度
        int len = nums1.length + nums2.length;
        int cut1 = 0;  // 对 nums1 进行切分的位置
        int cut2 = 0;  // 对 nums2 进行切分的位置
        int cutL = 0;  // nums1 左边切分的位置
        int cutR = nums1.length; // nums1 右边切分的位置
        while (cut1 <= nums1.length) {
            cut1 = (cutR - cutL) / 2 + cutL;  // 获取 nums1短数组 本轮靠近 num3 中位数的位置
            cut2 = len / 2 - cut1;  // 获取 nums2长数组 本轮靠近 num3 中位数的位置
            // 获取 nums1短数组 本轮左右位置的数字值
            double L1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            double R1 = (cut1 == 0) ? Integer.MAX_VALUE : (cut1 >= nums1.length ? Integer.MAX_VALUE : nums1[cut1]);
            // 获取 nums2长数组 本轮左右位置的数字值
            double L2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            double R2 = (cut2 == 0) ? Integer.MAX_VALUE : nums2[cut2];
            if (L1 > R2) {
                // 如果 nums1短数组的 左值大于 nums2长数组的右值，则 cutR 要左移一位
                cutR = cut1 - 1;
            } else if (R1 < L2) {
                // 如果 nums1短数组的 右值小于 nums2长数组的左值，则 cutL 要右移一位
                cutL = cut1 + 1;
            } else {
                if (len % 2 == 0) {
                    // nums3长度为偶数时，返回中间两个数字的平均数
                    L1 = Math.max(L1, L2);
                    R1 = Math.min(R1, R2);
                    return (L1 + R1) / 2;
                } else {
                    // nums3长度为偶数时，返回nums1和nums2的右值中最小的
                    R1 = Math.min(R1, R2);
                    return R1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[] nums1 = {3, 5, 8, 9, 15}; int[] nums2 = {1, 2, 7, 10, 11, 12};
      int[] nums1 = {10, 11, 12}; int[] nums2 = {1, 2, 3, 4, 5, 6};
//        int[] nums1 = {1, 2, 3}; int[] nums2 = {7, 8, 9, 10, 11, 12, 13, 14};

        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
