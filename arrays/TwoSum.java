/**
 * Problem:
 * <Two Sum>
 *
 * Link:
 * <https://leetcode.com/problems/two-sum/description/>
 *
 * Pattern:
 * <Hash Map>
 *
 * Brute Force Intuition:
 * - Try all pairs of indices (i,j).
 * - Check if nums[i] + nums[j] = target.
 * - Return indices when a match is found.
 *
 * - Why it is inefficient?
 * - While the approach is valid, it is still very low for large arrays which might exceed the time limit.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Store the elements with their indices in a 2-D array or a nested list.
 * - Sort the data structure by value.
 * - Use 2 pointer approach and when a pair's sum = target, return the indices stored corresponding to the numbers.
 *
 * - Why it is still not optimal.
 * - Reduces nested loop, but still costs sorting time
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Use a HashMap to store previously seen values.
 * - For each number, check if it's compliment exists.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Store elements in the map only after checking compliment to avoid using same index
 */
package arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
class TwoSum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2,7,11,15}, 9)));
    }

    public static int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < n ; i++){
            int compliment = target - nums[i];

            if(map.containsKey(compliment)){
                return new int[]{map.get(compliment), i};
            }

            map.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }
}
