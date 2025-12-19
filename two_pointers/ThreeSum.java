/**
 * Problem:
 * <3 Sum>
 *
 * Link:
 * <https://leetcode.com/problems/3sum/description/>
 *
 * Pattern:
 * <Two Pointer>
 *
 * Brute Force Intuition:
 * - Try out triplets of all the indices (i, j, k).
 * - When their sum = 0, add them to the list.
 * - Sort the list and store it inside a set.
 *
 * - Why it is inefficient?
 * - Very slow for large arrays.
 * - Repeated sorting of the list, so that the set detects it as a duplicate because the brute force approach generates duplicates naturally.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Fixate one index and make it as target.
 * - Now the remaining problem simulates like a two sum where you need to find the needed = -(nums[i] + nums[j])
 * - Use a HashSet to check for the values in O(1)
 *
 * - Why it is still not optimal.
 * - Skipping duplicates is not clean and efficient as the array is not yet sorted.
 * - However, the time complexity is reduced but the space complexity remains the same.
 * - Needs to maintain a Set<List<Integer>> to store the unique triplets.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Sort the array as it is easy to skip the duplicates.
 * - Fixate one value at the index i.
 * - Use 2-pointer approach.
 * - Move the right pointer inwards when sum > 0.
 * - Move the left pointer inward when sum < 0.
 * - Shift until left < right.
 *
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Remember to skip the duplicates at the outer level.
 * - Remember to skip the duplicates after generating a triplet so that the duplicate triplet is not generated.
 */
package two_pointers;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;    
public class ThreeSum {

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;

        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);

        for(int i = 0; i < n; i++){

            if(i > 0 && nums[i] == nums[i-1]) continue;

            int left = i + 1, right = n-1;

            while(left < right){

                int sum = nums[i] + nums[left] + nums[right];

                if(sum == 0){
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));

                    // Skip duplicates
                    while(left < right && nums[left] == nums[left + 1]) left++;
                    while(left < right && nums[right] == nums[right - 1]) right--;

                    // Move pointers
                    left++;
                    right--;

                }
                else if(sum > 0){
                    right--;
                }
                else{
                    left++;
                }
            }
        }
        return res;
    }

}
