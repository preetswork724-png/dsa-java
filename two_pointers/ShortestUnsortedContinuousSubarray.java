/**
 * Problem:
 * <Shortest Unsorted Continuous Subarray>
 *
 * Link:
 * <https://leetcode.com/problems/shortest-unsorted-continuous-subarray/description/>
 *
 * Pattern:
 * <Two Pointer>
 *
 * Brute Force Intuition:
 * - Try every possible subarray.
 * - Create a clone of the array.
 * - Sort in that range.
 * - Check is it sorts the array
 * - Track the minimum continuous subarray.
 *
 * - Why it is inefficient?
 * - Generating all teh subarray is not at all optimal.
 * - Sorting and checking makes the entire process completely inefficient.
 *
 * Time Complexity:
 * - O(N^3 log N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Create a clone of the array.
 * - Sort the clone.
 * - Track the indices of the first mismatch.
 * - Keep tracking the index of the last mismatch.
 *
 * - Why it is still not optimal?
 * - Requires sorting and creating a new array.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - A sorted array is like max(nums[0...i-1]) <= nums[i] <= min(nums[i+1...n-1]).
 * - Every element is >= max of its left.
 * - Every element is <= min of its right.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Use the property of the sorted array.
 * - Do not exit at an early violation.
 * - Keep updating the pointers because the disorder can extend.
 */
package two_pointers;

public class ShortestUnsortedContinuousSubarray {
    public static void main(String[] args) {
        System.out.println(findUnsortedSubarray(new int[]{2,6,4,8,10,9,15}));
    }

    public static int findUnsortedSubarray(int[] nums) {
        int n = nums.length;

        int maxSoFar = Integer.MIN_VALUE, end = -1;

        for(int i = 0; i < n ; i++){
            if(nums[i] < maxSoFar){
                end = i;
            }
            else{
                maxSoFar = nums[i];
            }
        }

        int minSoFar = Integer.MAX_VALUE, start = -1;

        for(int i = n - 1; i >= 0; i--){
            if(nums[i] > minSoFar){
                start = i;
            }
            else{
                minSoFar = nums[i];
            }
        }

        return start == -1 ? 0 : end - start + 1;
    }
}
