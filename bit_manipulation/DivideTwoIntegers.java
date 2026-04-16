/**
 * Problem:
 * <Divide Two Integers>
 *
 * Link:
 * <https://leetcode.com/problems/divide-two-integers/>
 *
 * Pattern:
 * <Bit Manipulation / Exponential Search>
 *
 * Brute Force Intuition:
 * - Repeatedly subtract the divisor from the dividend and count how many
 *   times the subtraction can be performed before the dividend drops below
 *   the divisor.
 * - The count is the quotient.
 *
 * - Why it is inefficient?
 * - For large dividends and tiny divisors (e.g., dividend = 2^31 - 1,
 *   divisor = 1), this requires ~2 billion iterations.
 *
 * Time Complexity:
 * - O(dividend / divisor) → effectively O(N)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Instead of subtracting one divisor at a time, double the subtraction
 *   amount on each step using left bit shifts:
 *   divisor × 1, divisor × 2, divisor × 4, divisor × 8 ...
 * - Find the largest doubling that still fits inside the dividend, subtract
 *   it, accumulate the corresponding power-of-two into the quotient, and
 *   repeat on the leftover remainder.
 *
 * - Why it is still not optimal?
 * - Each outer pass restarts the doubling from scratch (divisor << 0),
 *   repeating work that could be avoided.
 *
 * Time Complexity:
 * - O(log^2 N)
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used Below):
 * - Determine the sign of the result upfront using XOR on the sign bits:
 *   negative if exactly one input is negative, positive otherwise.
 * - Convert both inputs to their absolute values and work with positive
 *   numbers throughout (use long / 64-bit to safely hold |INT_MIN|).
 * - Outer loop — while dividend >= divisor:
 *     - Inner loop: find the highest shift k such that (divisor << k) <= dividend.
 *     - Add (1 << k) to the quotient.
 *     - Subtract (divisor << k) from the dividend.
 * - Each outer iteration reduces the dividend by at least half, giving
 *   O(log N) outer passes × O(log N) inner shifts = O(log^2 N) total,
 *   but with no redundant recomputation between passes.
 * - Apply the sign determined earlier to the quotient.
 * - Clamp the result to [−2^31, 2^31 − 1] before returning.
 *
 * Time Complexity:
 * - O(log^2 N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - The operators *, /, and % must never appear anywhere in the solution.
 * - Left shift (<<) doubles a value; right shift (>>) halves it — these
 *   replace all multiplication and division in the algorithm.
 * - Critical edge case: dividend = INT_MIN (-2^31), divisor = -1 produces
 *   a mathematical result of 2^31, which exceeds INT_MAX and overflows a
 *   32-bit signed integer — clamp this to INT_MAX (2^31 - 1).
 * - Always use a 64-bit type (long) internally because Math.abs(INT_MIN)
 *   overflows a 32-bit int (INT_MIN has no positive counterpart in 32 bits).
 * - Sign is resolved at the end by applying negation only when exactly one
 *   of the original inputs was negative (XOR of their sign bits = 1).
 */

package bit_manipulation;

public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {

        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

        if (dividend == divisor) return 1;

        boolean sign = true;

        if ((dividend >= 0 && divisor < 0) || (dividend < 0 && divisor >= 0)) {
            sign = false;
        }

        long n = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);

        long quotient = 0;

        while (n >= d) {
            int count = 0;

            while (n >= (d << (count + 1))) {
                count++;
            }

            quotient += (1 << count);
            n -= (d << count);
        }

        if (quotient > Integer.MAX_VALUE) {
            return sign ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }

        return sign ? (int) quotient : (int) -quotient;
    }
}
