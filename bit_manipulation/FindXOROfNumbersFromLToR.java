/**
 * Problem:
 * <Find XOR of Numbers from Index L to R>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/find-xor-of-numbers-from-index-l-to-r/1>
 *
 * Pattern:
 * <Bit Manipulation / Prefix XOR>
 *
 * Brute Force Intuition:
 * - Iterate through the array from index a to index b.
 * - XOR each element into a running accumulator and return the result.
 *
 * - Why it is inefficient?
 * - Every query scans the entire range [a, b] from scratch, making it
 *   slow when multiple queries are fired on the same array.
 *
 * Time Complexity:
 * - O(N) per query
 * Space Complexity:
 * - O(1)
 *
 * Better / Optimal Approach (Used Below):
 * - Precompute a prefix XOR array where:
 *       prefix[i] = nums[0] ^ nums[1] ^ ... ^ nums[i]
 * - XOR of any range [a, b] can then be answered in O(1) using:
 *       XOR(a, b) = prefix[b] ^ prefix[a - 1]
 *   (if a == 0, the answer is simply prefix[b])
 * - This works because XOR is its own inverse (A ^ A = 0), so
 *   XORing prefix[b] and prefix[a-1] cancels out all elements
 *   before index a, leaving only the XOR of elements in [a, b].
 * - Example: nums = {1, 3, 5, 7, 9, 11}, a = 1, b = 3
 *       prefix = {1, 2, 7, 0, 9, 2}
 *       XOR(1, 3) = prefix[3] ^ prefix[0] = 0 ^ 1 = 1 ✓
 *
 * Time Complexity:
 * - O(N) to build the prefix XOR array, O(1) per query
 * Space Complexity:
 * - O(N) for the prefix XOR array
 *
 * Notes:
 * - The prefix XOR technique is the direct XOR analogue of the prefix
 *   sum technique used for range sum queries.
 * - XOR being its own inverse (A ^ A = 0) and having an identity
 *   element of 0 (A ^ 0 = A) are the two properties that make prefix
 *   XOR possible.
 * - If only a single query is needed and no preprocessing budget exists,
 *   the brute force O(N) loop is perfectly acceptable.
 * - Prefix XOR shines when Q queries are fired on the same static array,
 *   reducing total complexity from O(N * Q) to O(N + Q).
 * - Be careful with the boundary: XOR(a, b) = prefix[b] ^ prefix[a-1]
 *   requires a >= 1; for a == 0, return prefix[b] directly to avoid
 *   an out-of-bounds access on prefix[a-1].
 */

package bit_manipulation;

public class FindXOROfNumbersFromLToR {
    public static int findXOR(int l, int r) {
        return getXor(l - 1) ^ getXor(r);
    }

    public static int getXor(int n) {
        if (n % 4 == 0) return n;
        else if (n % 4 == 1) return 1;
        else if (n % 4 == 2) return n + 1;
        else return 0;
    }
}
