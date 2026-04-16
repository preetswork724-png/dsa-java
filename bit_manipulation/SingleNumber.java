/**
 * Problem:
 * <Single Number>
 *
 * Link:
 * <https://leetcode.com/problems/single-number/>
 *
 * Pattern:
 * <Bit Manipulation / XOR>
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
 * - XOR all elements in the array together into a single accumulator,
 *   starting from 0.
 * - XOR has two properties that make this work:
 *     - A ^ A = 0  → any number XORed with itself cancels out to zero.
 *     - A ^ 0 = A  → any number XORed with zero remains unchanged.
 * - Because every duplicate pair cancels to 0, all paired elements
 *   eliminate themselves, leaving only the single number behind.
 * - Example: [2, 2, 1] → 0 ^ 2 ^ 2 ^ 1 = 0 ^ 0 ^ 1 = 1 ✓
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - XOR is both commutative (A ^ B = B ^ A) and associative
 *   ((A ^ B) ^ C = A ^ (B ^ C)), so the order of elements in the
 *   array does not affect the final result.
 * - No sorting, no extra data structures, and a single pass — this is
 *   the canonical optimal solution for this problem.
 * - This approach generalises: if every element appears K times except
 *   one, bit-counting modulo K on each bit position isolates the unique
 *   element (though that requires a different technique beyond plain XOR).
 */

package bit_manipulation;

public class SingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;

        for (int num : nums) result ^= num;

        return result;
    }
}
