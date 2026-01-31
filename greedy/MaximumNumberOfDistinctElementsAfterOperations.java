/**
 * Problem:
 * <Maximum Number of Distinct Elements After Operations>
 *
 * Link:
 * <https://leetcode.com/problems/maximum-number-of-distinct-elements-after-operations/description/>
 *
 * Pattern:
 * <Greedy + Sorting + Intervals>
 *
 * Brute Force Intuition:
 * - Backtracking.
 * - For each index i:
 * - Try all the values from nums[i]-k to nums[i]+k.
 * - Build one possible modified array.
 * - Count the number of distinct elements.
 * - Track maximum.
 *
 * - Why it is inefficient?
 * - Same values are tested again and again.
 * - Heavy memory due to deep recursion.
 * - Every path is explored, no pruning.
 *
 * Time Complexity:
 * - O((2k + 1) ^ N)
 * Space Complexity:
 * - O(N) {Backtracking explores all the paths, it does not store all paths}
 *
 * Better Approach intuition:
 * - Sorting.
 * - Sort the array.
 * - For every element in the array:
 * - We need to add the numbers in the range -k to k.
 * - So, the actual range becomes nums[i]-k to nums[i]+k.
 * - Instead of exploring all the possible values in the above range:
 * - We always try to allocate the smallest unused values in that range.
 * - Track the used elements using a set.
 * - Everytime, we add a value to the set we count it.
 *
 * Time Complexity:
 * - O(n * k)
 * Space Complexity:
 * - O(n)
 *
 * Why this is still not optimal?
 * - Still slow for large n.
 *
 * Optimal Approach (Used below):
 * - Greedy + Sorting + Intervals.
 * - Sort the array so that the intervals are processed in increasing order.
 * - Track the next free value.
 * - Compute a valid interval:
 * - Each number brings a valid interval with it i.e left = num - k and right = num + k.
 * - Keep a variable assigned which takes the maximum out of nextAvailable and left.
 * - Don't reuse nextAvailable values.
 * - Don't violate the left interval.
 * - If we can fit a new value, count it.
 * - Move forward, nextAvailable = assigned + 1.
 * - This ensures that the future available values are strictly distinct.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Each element represents an interval.
 * - We want to place as many points as possible.
 * - Each point must be unique.
 * - Greedy rule: Always place the leftmost feasible point.
 */

package greedy;

import java.util.Arrays;

public class MaximumNumberOfDistinctElementsAfterOperations {

    public static void main(String[] args) {
        System.out.println(maxDistinctElements(new int[]{1,2,2,3,3,4}, 2));
    }

    public static int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        long nextAvailable = Long.MIN_VALUE;
        int count = 0;

        for(int num : nums){
            long left = num - k, right = num + k;

            long assigned = Math.max(nextAvailable, left);

            if(assigned <= right){
                nextAvailable = assigned + 1;
                count++;
            }
        }
        return count;
    }

}
