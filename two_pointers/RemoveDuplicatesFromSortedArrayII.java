/**
 * Problem:
 * <Remove Duplicates From Sorted Array II>
 *
 * Link:
 * <https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/>
 *
 * Pattern:
 * <Two Pointer (Fast & Slow)>
 *
 * -----------------------------------------------------
 *
 * Brute Force Intuition:
 * - Iterate through the array and maintain a list of elements.
 * - Allow insertion of an element only if it appears less than 2 times in the list.
 * - For every new element, count occurrences in the list before adding.
 *
 * - Why it is inefficient?
 * - For each element, checking count in the list takes O(N).
 * - Leads to nested iteration.
 * - Violates in-place constraint.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * -----------------------------------------------------
 *
 * Better Approach Intuition:
 * - Since array is sorted, duplicates are adjacent.
 * - Maintain a frequency counter while traversing.
 * - Store valid elements (max 2 occurrences) in an auxiliary array or LinkedHashSet.
 *
 * - Why it is still not optimal?
 * - Extra space is used.
 * - Does not satisfy in-place requirement.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * -----------------------------------------------------
 *
 * Optimal Approach (Used Below):
 * - Use two pointers:
 *      slow → position to place next valid element
 *      fast → scans the array
 *
 * - Key Idea:
 *   Allow at most 2 occurrences of each element.
 *
 * - Start slow from index 2 (since first two elements are always valid).
 *
 * - For each fast:
 *      If nums[fast] != nums[slow - 2]:
 *          nums[slow] = nums[fast]
 *          slow++
 *
 * - Why this works:
 *   nums[slow - 2] ensures we don't allow more than 2 duplicates.
 *
 * -----------------------------------------------------
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(1)
 *
 * -----------------------------------------------------
 *
 * Notes:
 * - This is a general pattern:
 *      "Allow at most K duplicates"
 *
 * - Generalized condition:
 *      if(nums[fast] != nums[slow - K])
 *
 * - Works only because array is SORTED.
 *
 * - Edge Case:
 *      If length <= 2 → return length directly
 *
 * - This is NOT just duplicate removal:
 *      It is controlled frequency placement.
 *
 * - Interview Insight:
 *      This is a classic extension of:
 *      "Remove Duplicates From Sorted Array I"
 *
 *      Difference:
 *      - There → allow only 1 occurrence
 *      - Here → allow 2 occurrences
 *
 * -----------------------------------------------------
 */
package two_pointers;

public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;

        if(n <= 2) return n;
        int k = 2;

        for(int i = 2; i < n; i++){
            if(nums[i] != nums[k - 2]){
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
}
