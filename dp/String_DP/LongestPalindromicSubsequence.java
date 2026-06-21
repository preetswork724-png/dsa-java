/**
 * Problem:
 * Longest Palindromic Subsequence
 *
 * Link:
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 *
 * Pattern:
 * Bottom-Up DP (2D Tabulation) — Interval DP
 *
 * Brute Force Intuition:
 * - Generate all subsequences of the string and check each one for being
 *   a palindrome, tracking the maximum length found.
 * - At each character, we have 2 choices:
 *   - Include the character in the current subsequence.
 *   - Exclude the character from the current subsequence.
 * - Base case:
 *   A single character is always a palindrome of length 1.
 *   An empty string has length 0.
 *
 * - Why it is inefficient?
 * - Generates 2^N subsequences — exponential blowup.
 * - Re-checks overlapping subproblems from scratch every time.
 * - Time complexity is exponential.
 *
 * Time Complexity:
 * - O(2^N) {Two choices at every character}
 * Space Complexity:
 * - O(N) {Recursion depth for current subsequence}
 *
 * Better Approach Intuition:
 * - Memoization (Top-Down DP).
 * - Key insight: LPS of a string is the same as the LCS of the string
 *   with its reverse. Alternatively, think directly in terms of the
 *   interval [i, j]:
 *   - memo[i][j] :- represents the LPS length of s[i...j].
 * - Recurrence:
 *   - If s[i] == s[j] -> 2 + solve(i+1, j-1)              {both ends match, contribute 2}
 *   - Else -> max(solve(i+1, j), solve(i, j-1))            {skip one end and take the better result}
 * - Base cases:
 *   - i == j    -> return 1  {single character}
 *   - i  > j    -> return 0  {empty interval, crossed pointers}
 * - Why a 2D memo and not 1D?
 * - The result depends on both the left and right boundary of the
 *   current substring simultaneously — two independent variables.
 *
 * Time Complexity:
 * - O(N^2) {N^2 unique (i, j) states, O(1) work per state}
 * Space Complexity:
 * - O(N^2) {Memoization table + recursion stack}
 *
 * Why this is still not optimal?
 * - Recursive call stack overhead (up to O(N) depth).
 * - The bottom-up traversal order is non-obvious from recursion alone;
 *   tabulation makes the diagonal fill order explicit.
 * - Bottom-Up also enables O(N) space optimization by keeping only two
 *   diagonals at a time (though the 2D version is shown below for clarity).
 *
 * Optimal Approach (Used below):
 * - Bottom-Up 2D Interval DP (fill by increasing substring length).
 * - Key Insight: LPS(s) == LCS(s, reverse(s)).
 *   We can either reduce to LCS or solve the interval DP directly.
 *   The interval DP is cleaner for understanding.
 * - Step 1: Initialize the DP table.
 *   - dp[i][j] :- LPS length of s[i...j].
 *   - Base case: dp[i][i] = 1 for all i (every single character is a palindrome).
 *   - Cells where i > j are 0 (empty interval).
 * - Step 2: Fill diagonally — iterate over increasing lengths L = 2..N.
 *   - For each (i, j) with j = i + L - 1:
 *     - If s[i] == s[j] -> dp[i][j] = 2 + dp[i+1][j-1]
 *     - Else -> dp[i][j] = max(dp[i+1][j], dp[i][j-1])
 * - Step 3: Answer is dp[0][N-1].
 *
 * Time Complexity:
 * - O(N^2) {Fill the upper triangle of the DP table}
 * Space Complexity:
 * - O(N^2) {DP table} — reducible to O(N) with two arrays
 *
 * Notes:
 * - Whenever you see:
 *   - "Longest palindromic subsequence / substring in a single string."
 *   - "Minimum insertions / deletions to make a string a palindrome."
 *   - "Two pointers shrinking from both ends of an interval."
 *   - This is the Interval DP Pattern (single-string, shrinking window).
 * - Key relationships to remember:
 *   - LPS(s) = LCS(s, reverse(s)).
 *   - Min insertions to make palindrome = N - LPS(s).
 *   - Min deletions to make palindrome  = N - LPS(s).
 *   - These three problems share the same DP table.
 * - The diagonal fill order is critical:
 *   - dp[i][j] depends on dp[i+1][j-1] (inner), dp[i+1][j] (down), dp[i][j-1] (left).
 *   - All three cells lie on shorter intervals — always computed before (i, j).
 *   - Fill by length L = 1, 2, ..., N, not row by row.
 * - Watch the edge case when L == 2 (two characters):
 *   - If they match -> dp[i][j] = 2 + dp[i+1][j-1] = 2 + dp[i+1][i] = 2 + 0 = 2. ✓
 *   - The crossed-pointer base case (i > j -> 0) handles this automatically.
 * - The 2D table can be space-optimized:
 *   - Since dp[i][j] only needs dp[i+1][*] and dp[i][*], two 1D arrays suffice.
 *   - Keep this in mind if asked to optimize space in a follow-up.
 */

package dp.String_DP;

public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {

                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
