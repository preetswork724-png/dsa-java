/**
 * Problem:
 * <Subarray Product Less Than K>
 *
 * Link:
 * <https://leetcode.com/problems/subarray-product-less-than-k/description/>
 *
 * Pattern:
 * <Sliding Window>
 *
 * Brute Force Intuition:
 * - Maintain a counter variable.
 * - For every index i, try to expand the subarray until the product is less than k.
 * - Keep incrementing the counter variable.
 *
 * - Why it is inefficient?
 * - Very slow for large arrays.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used Below):
 * - Keep accumulating the product until it's less than k.
 * - Now this entire range is a subarray, represented between two indices.
 * - Keep summing the current window size to obtain the number of subarrays.
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
package sliding_window;

public class SubarrayProductLessThanK {
    public static void main(String[] args) {
        System.out.println(numSubarrayProductLessThanK(new int[]{10,5,2,6}, 100));
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {

        if(k <= 1) return 0;

        int n = nums.length;
        int left = 0, count = 0, prod = 1;

        for(int right = 0; right < n; right++){

            prod *= nums[right];

            while(left < n && prod >= k){
                prod /= nums[left];
                left++;
            }

            count += right - left + 1;
        }

        return count;
    }
}
