/**
 * Problem:
 * <4 Sum>
 *
 * Link:
 * <https://leetcode.com/problems/4sum/description/>
 *
 * Pattern:
 * <Two Pointer>
 *
 * Brute Force Intuition:
 * - Try out quadruplets of all the indices (i, j, k, l).
 * - When their sum = target, add them to the list.
 * - Sort the list and store it inside a set.
 *
 * - Why it is inefficient?
 * - Very slow for large arrays.
 * - Repeated sorting of the list, so that the set detects it as a duplicate because the brute force approach generates duplicates naturally.
 *
 * Time Complexity:
 * - O(N^4)
 * Space Complexity:
 * - O(N^2)
 *
 * Better Approach Intuition:
 * - Fixate two indexes one by one.
 * - Now the remaining problem simulates like a two sum where you need to find the target = -(nums[i] + nums[j] + nums[k] + nums[l])
 * - Use a HashSet to check for the needed in O(1)
 *
 * - Why it is still not optimal.
 * - Skipping duplicates is not clean and efficient as the array is not yet sorted.
 * - However, the time complexity is reduced but the space complexity remains the same.
 * - Needs to maintain a Set<List<Integer>> to store the unique triplets.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(N^3)
 *
 * Optimal Approach (Used Below):
 * - Sort the array as it is easy to skip the duplicates.
 * - Fixate one value at the index i.
 * - Fixate second value at the index j.
 * - Use 2-pointer approach.
 * - Move the right pointer inwards when sum > 0.
 * - Move the left pointer inward when sum < 0.
 * - Move the pointers until left < right.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Remember to skip the duplicates inside the first loop.
 * - Remember to skip duplicates inside the second loop.
 * - Remember to skip the duplicates after generating a quadruplet so that the duplicate triplet is not generated.
 */
package two_pointers;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
public class FourSum {
    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{1,0,-1,0,-2,2}, 0));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for(int i = 0; i < n; i++){

            if(i > 0 && nums[i] == nums[i-1]) continue; // Skip duplicates

            for(int j = i+1; j < n; j++){

                if(j > i+1 && nums[j] == nums[j-1]) continue; // Skip duplicates

                int left = j + 1, right = n - 1;

                while(left < right){

                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];

                    if(sum == target){
                        res.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right])));

                        while(left < right && nums[left] == nums[left + 1]) left++; // Skip duplicates
                        while(left < right && nums[right] == nums[right - 1]) right--; // Skip duplicates
                        left++;
                        right--;
                    }
                    else if(sum > target){
                        right--;
                    }
                    else{
                        left++;
                    }
                }

            }
        }
        return res;
    }
}
