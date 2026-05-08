/**
 * Problem:
 * <Median of Two Sorted Arrays>
 *
 * Link:
 * <https://leetcode.com/problems/median-of-two-sorted-arrays/>
 *
 * Pattern:
 * <Binary Search on Partition (NOT on answer)>
 *
 * -----------------------------------------------------
 *
 * Brute Force Intuition:
 * - Merge both sorted arrays into a single sorted array.
 * - Find the median from the merged array.
 *
 * - Why it is inefficient?
 * - Requires traversing both arrays completely.
 * - Uses extra space to store merged array.
 * - Does not satisfy O(log(m+n)) constraint.
 *
 * Time Complexity:
 * - O(m + n)
 *
 * Space Complexity:
 * - O(m + n)
 *
 * -----------------------------------------------------
 *
 * Better Approach Intuition:
 * - Instead of fully merging, simulate merge until median index.
 * - Keep track of only required elements.
 *
 * - Why it is still not optimal?
 * - Still requires O(m + n) time in worst case.
 * - Does not utilize binary search.
 *
 * Time Complexity:
 * - O(m + n)
 *
 * Space Complexity:
 * - O(1)
 *
 * -----------------------------------------------------
 *
 * Optimal Approach (Used Below):
 * - Use Binary Search on partition of smaller array.
 * - Divide both arrays such that:
 *
 *      left part size = (m + n + 1) / 2
 *
 * - Choose cut1 in nums1 → derive cut2 in nums2.
 *
 * - Ensure valid partition:
 *      max(left1) <= min(right2)
 *      max(left2) <= min(right1)
 *
 * - If valid:
 *      - If total length is odd:
 *            median = max(left1, left2)
 *      - If even:
 *            median = (max(left1, left2) + min(right1, right2)) / 2
 *
 * - Else adjust:
 *      - If left1 > right2 → move left (high = cut1 - 1)
 *      - If left2 > right1 → move right (low = cut1 + 1)
 *
 * -----------------------------------------------------
 *
 * Time Complexity:
 * - O(log(min(m, n)))
 *
 * Space Complexity:
 * - O(1)
 *
 * -----------------------------------------------------
 *
 * Why this is optimal?
 * - We are NOT searching in values or answer space.
 * - We are searching for a correct partition.
 * - Partition condition is monotonic:
 *      Too many elements from nums1 → move left
 *      Too few elements from nums1 → move right
 *
 * - Binary search efficiently finds this boundary.
 *
 * -----------------------------------------------------
 *
 * Notes:
 * - Always apply binary search on the smaller array.
 * - Handle edge cases using:
 *      - Integer.MIN_VALUE for left boundary
 *      - Integer.MAX_VALUE for right boundary
 *
 * - Median depends only on:
 *      max(left half) and min(right half)
 *
 * - Key formula:
 *      cut2 = (m + n + 1)/2 - cut1
 *
 * - This problem is NOT merge-based.
 *   It is partition-based.
 *
 * -----------------------------------------------------
 *
 * Mistake Log:
 * - Tried solving using merge → violates time constraint.
 * - Confused binary search on answer vs partition.
 * - Forgot boundary handling when cut = 0 or n.
 * - Applied binary search on larger array (less efficient).
 *
 */
package binary_search;

public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length, n = nums2.length, low = 0, high = m;

        while (low <= high) {
            int cut1 = low + (high - low) / 2;
            int cut2 = (m + n + 1) / 2 - cut1;

            int l1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int l2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];

            int r1 = (cut1 == m) ? Integer.MAX_VALUE : nums1[cut1];
            int r2 = (cut2 == n) ? Integer.MAX_VALUE : nums2[cut2];

            if (l1 <= r2 && l2 <= r1) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else {
                    return Math.max(l1, l2);
                }
            } else if (l1 > r2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }
        return 0.0;
    }
}
