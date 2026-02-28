/**
 * Problem:
 * <Decode Ways>
 *
 * Link:
 * <https://leetcode.com/problems/decode-ways/description/>
 *
 * Pattern:
 * <1D Dp>
 *
 * Brute Force Intuition:
 * - Recursively explore all the valid partitions of the string.
 * - For every index i, we have 2 choices:
 * - Take 1 digit (0 to 9).
 * - Take 2 digit (digits between 10 and 26).
 * - Recursively move forward.
 * - Define:
 * - helper(i) - number of ways to decode a string starting at index i.
 * - Base case:
 * - s[i] == '0' -> return 0.
 * - i == n -> valid decoding path completed.
 * - Recursive structure:
 * - count = helper(i + 1).
 * - If 2-digit number is valid:
 *       count += helper(i + 2).
 *
 * - Why it is inefficient?
 * - Exponential branching.
 * - Same index computed multiple times.
 * - Overlapping sub problems.
 *
 * Time Complexity:
 * - O(2^N)
 * Space Complexity:
 * - O(N) {Recursion stack depth}
 *
 * Better Approach intuition:
 * - Memoization (Top-down dp).
 * - Number of ways after reaching index i will always be the same regardless of how we reach i.
 * - dp[i] - number of ways to decode substring starting at index i.
 * - dp[i] - if already computed, return it.
 * - Recurrence relation:
 * - s[i] == '0', return 0.
 * - count = dp[i + 1].
 * - If valid two-digit number:
 * - count += dp[i + 2].
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N) {Memo table} + O(N) {recursion depth}
 *
 * Why this is still not optimal?
 * - Extra recursion stack space.
 * - Memo table can be reduced.
 *
 * Optimal Approach (Used below):
 * - Bottom-Up DP.
 * - dp[i] - number of ways to decode string from index i.
 * - Recurrence:
 * - If s[i] == '0' -> return 0.
 * - Else:
 * - dp[i] = dp[i + 1].
 * - If 2-digit number is valid:
 * - dp[i] += dp[i + 2].
 * - We can see that the current state only depends on the next 2 indices.
 * - So, instead of computing the whole array, we use 2 variables:
 * - next1 = dp[i + 1], next2 = dp[i + 2].
 * - We iterate from right -> left and update next1 and next2 during iteration.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Brute force fails due to overlapping subproblems.
 * - Memoization converts exponential to linear.
 * - The optimal solution uses Fibonacci-style DP.
 * - f(i) :- f(i + 1) + f(i + 2).
 * - With constraints on valid transitions.
 * - Think in terms of "How can I reach index i" and not in "How can I construct strings".
 */

package dp;

public class DecodeWays {

    public static void main(String[] args) {
        System.out.println(numDecodings("12"));
    }

    public static int numDecodings(String s) {
        int n = s.length();

        if (n == 0 || s.charAt(0) == '0') return 0;

        int next1 = 1, next2 = 1;

        for (int i = n - 1; i >= 0; i--) {

            int curr;

            if (s.charAt(i) == '0') {
                curr = 0;
            } else {
                curr = next1;

                if (i + 1 < n) {
                    int two = (s.charAt(i) - '0') * 10 + s.charAt(i + 1) - '0';
                    if (two >= 10 && two <= 26) {
                        curr += next2;
                    }
                }
            }
            next2 = next1;
            next1 = curr;
        }
        return next1;
    }
}
