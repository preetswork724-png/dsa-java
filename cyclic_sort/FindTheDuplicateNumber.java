/**
 * Problem:
 * <Find the Duplicate Number>
 *
 * Link:
 * <https://leetcode.com/problems/find-the-duplicate-number/>
 *
 * Pattern:
 * <Cyclic Sort / Index Placement>
 *
 * Brute Force Intuition:
 * - Compare every element with every other element.
 * - If two elements are equal,
 *   that element is the duplicate.
 *
 * Why it is inefficient?
 * - Nested traversal.
 * - Too many repeated comparisons.
 *
 * Time Complexity:
 * - O(N^2)
 *
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Use a HashSet.
 * - Traverse the array:
 *   - if element already exists in set,
 *     it is the duplicate.
 *
 * Why better than brute force?
 * - Lookup becomes O(1).
 * - Eliminates repeated searching.
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(N)
 *
 * Optimal Cyclic Sort Intuition:
 * - Numbers are in the range [1, N].
 * - Every number should ideally
 *   be at index (value - 1).
 *
 * - While placing elements:
 *   - if the target index already contains
 *     the same value,
 *     then duplicate is found.
 *
 * Key Observation:
 * - Duplicate number cannot occupy
 *   its correct position because
 *   another identical value is already there.
 *
 * Important Note:
 * - This approach modifies the array.
 * - Therefore it does NOT satisfy
 *   the original LeetCode constraint.
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(1)
 */

package cyclic_sort;

public class FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int n = nums.length, i = 0;

        while(i < n){
            int correctIndex = nums[i] - 1;

            if(nums[i] != nums[correctIndex]){
                swap(nums, i, correctIndex);
            }
            else{

                if(i != correctIndex){
                    return nums[i];
                }
                i++;
            }
        }
        return -1;
    }

    public void swap(int[] nums, int first, int second){
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
