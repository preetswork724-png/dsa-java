/**
 * Problem:
 * <Max Product Subarray>
 *
 * Link:
 * <https://leetcode.com/problems/maximum-product-subarray/description/?utm_source=chatgpt.com>
 *
 * Pattern:
 * <Kadane's Algorithm>
 *
 * Brute Force Intuition:
 * - Try all the subarrays.
 * - Calculate product of all the elements in the array.
 * - Track the maximum product.
 * - Brute force already considers:
 * - All start points.
 * - All end points.
 * - All sign combinations.
 * - All prefix & suffix products implicitly.
 * - So the subarray [i..j] and [j..i] direction doesn't exist. A subarray id unordered in direction.
 * - We are already covering every contiguous segment.
 *
 * - Why it is inefficient?
 * - Recomputes product repeatedly for some subarrays.
 * - Very slow for large arrays.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Build a prefix array for forward product and backward product.
 * - Track the maximum product in both of them.
 *
 * - Why it is still not optimal?
 * - We don't need to compute the entire prefix product array for finding the maximum product.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Instead of computing the forward product and backward product arrays :
 * - Maintain running forward product and backward product.
 * - Keep updating the maximum product.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - If either of the product becomes 0, remember to initialize them correctly with the value at that index.
 */
package dp;

public class MaximumProductSubarray {
    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2,3,-2,4}));
    }

    public static int maxProduct(int[] nums) {
        int n = nums.length, forwardPrdt = 0, backWardPrdt = 0, maxPrdt = nums[0];

        for(int i = 0; i < n ; i++){

            if(forwardPrdt == 0){
                forwardPrdt = nums[i];
            }
            else{
                forwardPrdt *= nums[i];
            }
            maxPrdt = Math.max(maxPrdt, forwardPrdt);
        }

        for(int i = n - 1; i >= 0; i--){

            if(backWardPrdt == 0){
                backWardPrdt = nums[i];
            }
            else{
                backWardPrdt *= nums[i];
            }
            maxPrdt = Math.max(maxPrdt, backWardPrdt);
        }
        return maxPrdt;
    }
}
