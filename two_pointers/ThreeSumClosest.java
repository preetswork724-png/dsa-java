/**
 * Problem:
 * <3 Sum Closest>
 *
 * Link:
 * <https://leetcode.com/problems/3sum-closest/description/>
 *
 * Pattern:
 * <Two Pointer>
 *
 * Brute Force Intuition:
 * - try out all the triplets from the indices (i, j, k).
 * - Whenever the sum is closer to the target than before, update the closest sum.
 *
 * - Why it is inefficient?
 * - Very slow for large arrays
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - HashMaps / HashSets don't help much because there is no exact compliment to search for, they do nor prune combinations, do not improve brute force.
 * - Pre-processing tricks like prefix sum, frequency array don't help either.
 *
 * Optimal Approach (Used Below):
 * - Sort the array.
 * - Fixate at one index
 * - Find the pair's sum using 2-pointer.
 *
 * - What to say in questions where the better approach is the optimal one?
 * - HashSet-based approaches don’t help because the problem does not involve exact matching.
 * - It gives you control over pointer movement to move closer to the target.
 * -
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Move right pointer inwards sum > target
 * - Move left pointer inwards sum < target
 */
package two_pointers;
import java.util.Arrays;
public class ThreeSumClosest {
    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-1,2,1,-4}, 1));
    }

    public static int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2];

        for(int i = 0; i < n ; i++){

            int left = i+1, right = n - 1;

            while(left < right){

                int sum = nums[i] + nums[left] + nums[right];

                if(Math.abs(sum - target) < Math.abs(closestSum - target)){
                    closestSum = sum;
                }
                else if(sum < target){
                    left++;
                }
                else{
                    right--;
                }
            }
        }
        return closestSum;
    }
}
