/**
 * Problem:
 * <Single Number II>
 *
 * Link:
 * <https://leetcode.com/problems/single-number-ii/>
 *
 * Pattern:
 * <Bit Manipulation>
 *
 * Brute Force Intuition:
 * - For each element, scan the rest of the array to check if it appears
 *   again. If no match is found, that is the single number.
 *
 * - Why it is inefficient?
 * - Every element triggers a full scan of the remaining array, leading
 *   to quadratic time.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Use a HashMap to store the frequency of each element in a single pass.
 * - Iterate over the map and return the element whose count is 1.
 *
 * - Why it is still not optimal?
 * - Allocating a HashMap uses extra space proportional to the number of
 *   distinct elements, violating the constant space requirement.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Plain XOR from Single Number I cannot be used here because A ^ A ^ A = A,
 *   not 0, so triplets do not cancel out.
 * - Instead, examine each of the 32 bit positions independently:
 *     - Count how many numbers in the array have a 1 at that bit position.
 *     - If the count is not divisible by 3, the single number contributes
 *       a 1 at that position; otherwise it contributes a 0.
 *     - Taking count % 3 at every bit position reconstructs the single number.
 * - Alternatively, simulate a base-3 bit counter using two bitmasks,
 *   `ones` and `twos`:
 *     - `ones` holds bits that have appeared 1 time (mod 3).
 *     - `twos` holds bits that have appeared 2 times (mod 3).
 *     - When a bit reaches 3 appearances it is cleared from both masks.
 *     - After processing all elements, `ones` contains the single number
 *       since its bits have appeared exactly once and never rolled over.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - The 32-bit counting approach is the most intuitive: iterate over all
 *   32 bit positions, sum up the 1-bits across every number, mod by 3,
 *   and reconstruct the answer bit by bit.
 * - The two-bitmask (ones/twos) approach is the most elegant and achieves
 *   the same result with pure bitwise operations and no inner loop:
 *     ones = (ones ^ num) & ~twos
 *     twos = (twos ^ num) & ~ones
 * - This problem is the canonical generalisation of Single Number I:
 *   wherever Single Number I uses XOR to cancel pairs (mod 2),
 *   Single Number II uses bit-counting mod 3 to cancel triplets.
 * - The same generalised technique extends to "every element appears K
 *   times except one" by counting bits modulo K.
 * - Be mindful of signed 32-bit integer representation when reconstructing
 *   the answer from individual bits — handle the sign bit (bit 31) carefully
 *   to avoid returning a wrong positive value for negative inputs.
 */

package bit_manipulation;

public class SingleNumberII {
    public int singleNumber(int[] nums) {
        int ans = 0;

        for (int bitIndex = 0; bitIndex < 32; bitIndex++) {
            int count = 0;

            for (int num : nums) {

                if ((num & (1 << bitIndex)) != 0) {
                    count++;
                }
            }

            if (count % 3 == 1) {
                ans = ans | (1 << bitIndex);
            }
        }
        return ans;
    }
}

