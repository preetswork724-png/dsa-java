/**
 * Problem:
 * <Single Number III>
 *
 * Link:
 * <https://leetcode.com/problems/single-number-iii/>
 *
 * Pattern:
 * <Bit Manipulation / XOR>
 *
 * Brute Force Intuition:
 * - For each element, scan the rest of the array to check if it appears
 *   again. If no match is found, it is one of the two single numbers.
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
 * - Iterate over the map and collect all elements whose count is 1.
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
 * - Step 1 — XOR all elements together.
 *   Since every duplicate pair cancels out (A ^ A = 0), the result is
 *   xorAll = A ^ B, where A and B are the two unique numbers.
 *
 * - Step 2 — Isolate a differing bit between A and B.
 *   Because A ≠ B, xorAll is non-zero, meaning at least one bit differs
 *   between them. Extract the lowest set bit using:
 *       diffBit = xorAll & (-xorAll)
 *   This bit is 1 in one of {A, B} and 0 in the other.
 *
 * - Step 3 — Partition the array into two groups using diffBit.
 *   Every number in the array falls into exactly one of two buckets:
 *       Group 1 → numbers where (num & diffBit) != 0
 *       Group 2 → numbers where (num & diffBit) == 0
 *   A and B land in opposite groups. Within each group, all duplicates
 *   still cancel via XOR, leaving exactly one unique number per group.
 *
 * - Step 4 — XOR each group independently to recover A and B.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - The key insight that unlocks the optimal solution is that XORing
 *   the entire array gives A ^ B, not A or B individually — a separator
 *   bit must be used to distinguish and isolate the two unique numbers.
 * - The lowest set bit trick (x & -x) works because -x is the two's
 *   complement of x, which flips all bits above the lowest set bit and
 *   preserves the lowest set bit itself.
 * - Any set bit in xorAll would work as the separator, but the lowest
 *   set bit is the conventional and easiest choice to extract.
 * - This problem builds directly on Single Number I: the first pass is
 *   identical, and the added insight is the partitioning step that
 *   reduces the two-unique-number problem back into two independent
 *   instances of the one-unique-number problem.
 * - The two groups are processed implicitly in a single second pass —
 *   no physical partitioning of the array is needed.
 */

package bit_manipulation;

public class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        int xor = 0;

        for (int num : nums) {
            xor ^= num;
        }

        int diff = xor & (-xor);
        int a = 0, b = 0;

        for (int num : nums) {
            if ((num & diff) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }

        return new int[]{a, b};
    }
}
