/**
 * Problem:
 * <Missing Number>
 *
 * Link:
 * <https://leetcode.com/problems/missing-number/>
 *
 * Pattern:
 * <Cyclic Sort / Index Placement>
 *
 * Brute Force Intuition:
 * - Traverse from 0 to N.
 * - For every number:
 *   - search the entire array to check
 *     whether it exists or not.
 * - The first missing number is the answer.
 *
 * Why it is inefficient?
 * - Repeated searching.
 * - Nested traversal.
 * - Very costly for large arrays.
 *
 * Time Complexity:
 * - O(N^2)
 *
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Use extra space to mark visited numbers.
 * - Create a boolean/frequency array.
 * - Mark every number present in the array.
 * - Traverse the frequency array:
 *   - the unmarked index is the missing number.
 *
 * Why better than brute force?
 * - Eliminates repeated searching.
 * - Presence checking becomes O(1).
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Numbers are in the range [0, N].
 * - Every value ideally belongs to its own index.
 *
 * - Example:
 *   - value 0 → index 0
 *   - value 1 → index 1
 *
 * - Place each element at its correct position.
 * - Ignore value N because index N does not exist.
 *
 * - After placement:
 *   - the index whose value mismatches
 *     is the missing number.
 *
 * Why optimal?
 * - Linear traversal.
 * - Constant extra space.
 * - Every swap places at least one element correctly.
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - This is an index-mapping problem.
 * - Cyclic Sort works because values
 *   are tightly bounded in [0, N].
 * - If all indices are correct,
 *   then missing number is N itself.
 */

package cyclic_sort;

public class MissingNumber {
    public int missingNumber(int[] nums) {
        int n = nums.length, i = 0;

        while(i < n){

            int correctIndex = nums[i];

            if(nums[i] < n && nums[i] != nums[correctIndex]){
                swap(nums, i, correctIndex);
            }
            else{
                i++;
            }
        }

        for(int index = 0; index < n; index++){
            if(nums[index] != index) return index;
        }

        return n;
    }

    public void swap(int[] nums, int first, int second){
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
