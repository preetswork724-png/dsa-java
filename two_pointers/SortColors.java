/**
 * Problem:
 * <Sort Colors>
 *
 * Link:
 * <https://leetcode.com/problems/sort-colors/description/>
 *
 * Pattern:
 * <Two Pointer>
 *
 * Brute Force Intuition:
 * - Sort the array.
 *
 * - Why it is inefficient?
 * - Violates constraint (library sort, not one pass).
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - As the array only contains of 0s, 1s and 2s.
 * - Count them in one pass.
 * - Then put 0s, 1s and 2s one by one in a new array
 *
 * - Why it is still not optimal?
 * - Uses multiple passes.
 * - Uses extra space.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - It's a rearrangement problem.
 * - We use pointers in such a way that we should be able to partition the array accordingly.
 * - Mainly, the array should be partitioned like all the zeroes come first, ones in the middle and twos int end.
 *
 * - What to say in questions where the better approach is the optimal one?
 * - HashMaps / HashSets don't help because we are comparing ranges, not values.
 * - We cannot sort the array because that will break the contiguity.
 * - No prefix sum or product because it might overflow.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - A window is only valid it's product < k.
 * - Use the left pointer to shrink the window when it becomes invalid.
 * - Use the right pointer to expand the window until it's invalid.
 */
package two_pointers;
import java.util.Arrays;
public class SortColors {
    public static void main(String[] args) {
        int[] nums = {2,0,2,1,1,0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void sortColors(int[] nums) {
        int n = nums.length, low = 0, mid = 0, high = n-1;

        while(mid <= high){
            if(nums[mid] == 0){
                swap(nums, mid, low);
                low++;
                mid++;
            }
            else if(nums[mid] == 1){
                mid++;
            }
            else{
                swap(nums, mid, high);
                high--;
            }
        }
    }

    public static void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
