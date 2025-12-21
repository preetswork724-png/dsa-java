/**
 * Problem:
 * <Count Pairs whose sum is less than target>
 *
 * Link:
 * <https://leetcode.com/problems/count-pairs-whose-sum-is-less-than-target/description/>
 *
 * Pattern:
 * <Two Pointer>
 *
 * Brute Force Intuition:
 * - Try out all the pairs.
 * - When pair's sum = target, increment the count.
 *
 * - Why it is inefficient?
 * - Very slow for large arrays.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Sort the List.
 * - Perform binary search for compliment of every number.
 *
 * - Why it is still not optimal?
 * - Redundant work
 * - Log factor that can be eliminated.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used Below):
 * - Sort the array.
 * - Use 2 pointer approach to calculate all the pairs.
 * - After sorting, the two-pointer approach runs in O(N),
 * - While the binary-search approach runs in O(N log N).
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Count all the pairs between left and right when their indices sum < target.
 */
package two_pointers;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class CountPairsWhoseSumIsLessThanTarget {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(Arrays.asList(-1,1,2,3,1));
        System.out.println(countPairs(nums, 2));
    }

    public static int countPairs(List<Integer> nums, int target) {
        Collections.sort(nums);

        int left = 0, right = nums.size() - 1, count = 0;

        while(left < right){

            if(nums.get(left) + nums.get(right) < target){
                count += (right - left);
                left++;
            }
            else{
                right--;
            }

        }
     return count;
    }
}
