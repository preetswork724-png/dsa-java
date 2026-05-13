/**
 * Problem:
 * <First Missing Positive>
 *
 * Link:
 * <https://leetcode.com/problems/first-missing-positive/>
 *
 * Pattern:
 * <Cyclic Sort / Index Placement>
 *
 * Brute Force Intuition:
 * - Start checking from 1 upwards.
 * - For every number:
 *   - scan the entire array to see
 *     whether it exists.
 * - The first absent number is the answer.
 *
 * Why it is inefficient?
 * - Repeated searching.
 * - Nested traversal.
 *
 * Time Complexity:
 * - O(N^2)
 *
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Use a HashSet.
 * - Store all positive numbers.
 * - Start checking from 1 upwards.
 * - First missing value becomes the answer.
 *
 * Why better than brute force?
 * - Presence checking becomes O(1).
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Only positive numbers in the range [1, N]
 *   are useful.
 *
 * Why?
 * - For an array of size N,
 *   answer must lie in range [1, N+1].
 *
 * - Ignore:
 *   - negative numbers
 *   - zero
 *   - numbers greater than N
 *
 * - Place every valid number
 *   at its correct index:
 *
 *   value 1 -> index 0
 *   value 2 -> index 1
 *   ...
 *
 * - After placement:
 *   - the first index whose value mismatches
 *     gives the missing positive.
 *
 * Key Observation:
 * - Correct index for a value:
 *
 *   correctIndex = nums[i] - 1
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Every swap places at least one element correctly.
 * - This is an index-mapping problem.
 * - Values outside [1, N] are irrelevant.
 */

package cyclic_sort;

public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int i = 0, n = nums.length;

        while(i < n){

            int correctIndex = nums[i] - 1;

            if(nums[i] > 0 && nums[i] <= n && nums[i] != nums[correctIndex]){
                swap(nums, i, correctIndex);
            }
            else{
                i++;
            }
        }

        for(int index = 0; index < n; index++){
            if(nums[index] != index + 1) return index + 1;
        }

        return n + 1;
    }

    public void swap(int[] nums, int first, int second){
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
