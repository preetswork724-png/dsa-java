/**
 * Problem:
 * <Minimum Bit Flips to Convert Number>
 *
 * Link:
 * <https://leetcode.com/problems/minimum-bit-flips-to-convert-number/>
 *
 * Pattern:
 * <Bit Manipulation / XOR>
 *
 * Approach (Used Below):
 * - XOR start and goal together.
 * - A bit in the XOR result is 1 wherever start and goal differ, and 0
 *   wherever they agree — because:
 *       0 ^ 0 = 0  (no flip needed)
 *       1 ^ 1 = 0  (no flip needed)
 *       0 ^ 1 = 1  (flip needed)
 *       1 ^ 0 = 1  (flip needed)
 * - The answer is therefore the number of set bits (1s) in (start ^ goal),
 *   which is the Hamming distance between start and goal.
 * - Count the set bits using Brian Kernighan's algorithm:
 *       while (xor != 0) { xor = xor & (xor - 1); count++; }
 *   Each iteration strips the lowest set bit, so the loop runs exactly
 *   as many times as there are set bits.
 * - Example: start = 10 (1010), goal = 7 (0111)
 *       XOR = 1010 ^ 0111 = 1101 → 3 set bits → 3 flips ✓
 *
 * Time Complexity:
 * - O(K), where K is the number of set bits in (start ^ goal)
 *   → at most O(32) for 32-bit integers, effectively O(1)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - No brute / better / optimal breakdown is needed here — XOR directly
 *   and naturally models the problem, making a single-pass bit-count
 *   both the simplest and most efficient solution.
 * - The problem is equivalent to finding the Hamming distance between
 *   start and goal.
 * - Brian Kernighan's algorithm (x = x & (x - 1)) is preferred over a
 *   naive 32-bit loop because it iterates only over set bits, not all bits.
 * - Alternatively, most languages provide a built-in popcount / bitCount
 *   function (e.g., Integer.bitCount() in Java, bin().count('1') in Python)
 *   which can replace the manual loop in production code.
 * - Each bit flip is independent — flipping one bit in start never affects
 *   whether another bit needs to be flipped, so greedy (flip every differing
 *   bit exactly once) is trivially optimal.
 */

package bit_manipulation;

public class MinimumBitFlipsToConvertNumber {
    public int minBitFlips(int start, int goal) {

        int ans = start ^ goal;
        int count = 0;

        for (int i = 0; i < 31; i++) {
            if ((ans & (1 << i)) != 0) count++;
        }

        return count;
    }
}
