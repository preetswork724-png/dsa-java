/**
 * Problem:
 * <XOR Operation in an Array>
 *
 * Link:
 * <https://leetcode.com/problems/xor-operation-in-an-array/>
 *
 * Pattern:
 * <Bit Manipulation / XOR>
 *
 * Brute Force / Optimal Approach (Used Below):
 * - Construct the array implicitly using the formula nums[i] = start + 2 * i.
 * - XOR each generated value into a running accumulator for i = 0 to n - 1.
 * - Return the accumulated result.
 * - No actual array needs to be stored — each value is computed on the
 *   fly and XORed directly, keeping space constant.
 * - Example: n = 5, start = 0
 *       nums = {0, 2, 4, 6, 8}
 *       0 ^ 2 ^ 4 ^ 6 ^ 8 = 8 ✓
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - There is no need for a brute force / better / optimal breakdown here
 *   because the problem does not impose any constraint beyond returning
 *   the XOR of a well-defined sequence — a single linear pass is both
 *   the simplest and the most efficient approach.
 * - The array is virtual: nums[i] = start + 2 * i is evaluated inline
 *   inside the loop, so no O(N) array allocation is required.
 * - The step size of 2 means consecutive elements differ by 2, which
 *   preserves the parity of start throughout — all elements are even
 *   if start is even, and all are odd if start is odd.
 * - A closed-form O(1) mathematical solution also exists by leveraging
 *   the XOR-from-0-to-N cycle pattern (N % 4 determines the result),
 *   but the linear pass above is the standard and most readable solution
 *   for this problem.
 */

package bit_manipulation;

public class XOROperationInAnArray {
    public int xorOperation(int n, int start) {
        int s = start >> 1;

        int xor = xorTill(s - 1) ^ xorTill(s + n - 1);

        if ((start & 1) == 1 && (n % 2 == 1)) {
            xor = xor << 1 | 1;
        } else {
            xor = xor << 1;
        }

        return xor;
    }

    public int xorTill(int n) {
        if (n % 4 == 0) return n;
        else if (n % 4 == 1) return 1;
        else if (n % 4 == 2) return n + 1;
        else return 0;
    }
}
