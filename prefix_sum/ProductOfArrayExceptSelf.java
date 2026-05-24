/**
 * Problem:
 * <Product of Array Except Self>
 *
 * Link:
 * <https://leetcode.com/problems/product-of-array-except-self/>
 *
 * Pattern:
 * <Prefix Product / Suffix Product>
 *
 * -----------------------------------------------------
 *
 * Brute Force Intuition:
 * - For each index i:
 *      Multiply all elements except nums[i].
 * - Use nested loops to compute product for every index.
 *
 * - Why it is inefficient?
 * - Recomputes products repeatedly.
 * - Time complexity grows quadratically.
 *
 * Time Complexity:
 * - O(N^2)
 *
 * Space Complexity:
 * - O(1) (excluding output array)
 *
 * -----------------------------------------------------
 *
 * Better Approach Intuition:
 * - Precompute prefix and suffix product arrays:
 *
 *      prefix[i] = product of elements from 0 → i-1
 *      suffix[i] = product of elements from i+1 → n-1
 *
 * - For each index:
 *      result[i] = prefix[i] * suffix[i]
 *
 * - Why it is still not optimal?
 * - Uses extra space for prefix and suffix arrays.
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(N)
 *
 * -----------------------------------------------------
 *
 * Optimal Approach (Used Below):
 * - Avoid extra space by reusing the output array.
 *
 * - Step 1:
 *      Build prefix product directly in result array:
 *
 *      result[i] = product of all elements before i
 *
 * - Step 2:
 *      Traverse from right, maintain a running suffix product:
 *
 *      suffix = 1
 *      For i from n-1 → 0:
 *          result[i] *= suffix
 *          suffix *= nums[i]
 *
 * - This combines prefix and suffix in one pass without extra arrays.
 *
 * - Steps:
 *      1. Initialize result[0] = 1
 *      2. Fill prefix products from left
 *      3. Maintain suffix while traversing right → left
 *      4. Multiply prefix and suffix contributions
 *
 * -----------------------------------------------------
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(1) (excluding output array)
 *
 * -----------------------------------------------------
 *
 * Why optimal approach is the best?
 * - Eliminates extra space used in prefix/suffix arrays.
 * - Computes result in two linear passes.
 * - Meets constraint: no division allowed.
 *
 * -----------------------------------------------------
 *
 * Notes:
 * - Division approach is NOT allowed (and fails with zeros).
 *
 * - Handles zeros naturally:
 *      - If one zero → only that index gets non-zero product
 *      - If more than one zero → all results are zero
 *
 * - Prefix product = left contribution
 * - Suffix product = right contribution
 *
 * - Core idea:
 *      result[i] = (product of left side) * (product of right side)
 *
 * -----------------------------------------------------
 *
 * Mistake Log:
 * - Used division → fails for zero cases
 * - Forgot to initialize prefix as 1
 * - Incorrect suffix update order
 * - Overwrote prefix values before using them
 *
 */

package prefix_sum;

public class ProductOfArrayExceptSelf {
    public long[] productOfArrayExceptSelf(int n, int[] a) {
        long[] output = new long[n];

        // Step 1: fill prefix product
        output[0] = 1;
        for (int i = 1; i < n; i++) {
            output[i] = output[i - 1] * a[i - 1];
        }

        // Step 2: multiply with suffix product
        long suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            output[i] *= suffix;
            suffix *= a[i];
        }

        return output;
    }
}
