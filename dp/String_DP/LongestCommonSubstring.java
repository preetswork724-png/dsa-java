/**
 * Problem:
 * Longest Common Substring
 *
 * Link:
 * https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 *
 * Pattern:
 * Bottom-Up DP (2D Tabulation)
 *
 * Brute Force Intuition:
 * - Generate all substrings of s1 and all substrings of s2.
 * - For every pair (sub1, sub2), check if they are equal and track
 *   the maximum length found.
 * - Base case:
 *   A single matching character is a common substring of length 1.
 *
 * - Why it is inefficient?
 * - O(N^2) substrings for s1, O(M^2) for s2 — O(N^2 * M^2) pairs.
 * - Each equality check costs O(min(N, M)) in the worst case.
 * - No reuse of previously computed matching work.
 * - Time complexity is polynomial but far from optimal.
 *
 * Time Complexity:
 * - O(N^2 * M^2) {All substring pairs} * O(min(N,M)) {equality check}
 * Space Complexity:
 * - O(1) {No extra storage beyond the pair comparison}
 *
 * Better Approach Intuition:
 * - Memoization (Top-Down DP).
 * - memo[i][j] :- length of the longest common substring ending exactly
 *   at s1[i] and s2[j].
 * - Recurrence:
 *   - If s1[i] == s2[j] -> 1 + solve(i-1, j-1)   {extend the match}
 *   - Else              -> 0                        {substring broken, reset}
 * - The global answer is the maximum value seen across all (i, j) calls,
 *   NOT the value at (n-1, m-1) — this is the key trap.
 * - Why a 2D memo and not 1D?
 * - The result depends on the current position in BOTH strings
 *   simultaneously — two independent variables driving one state.
 *
 * Time Complexity:
 * - O(N * M) {N * M unique states, O(1) work per state}
 * Space Complexity:
 * - O(N * M) {Memoization table + recursion stack}
 *
 * Why this is still not optimal?
 * - Recursive call stack overhead.
 * - Top-Down obscures the reset semantics — it is easy to forget that
 *   the mismatch branch returns 0, not a recursive call.
 * - Bottom-Up makes the reset explicit (dp[i][j] = 0 on mismatch) and
 *   the global max tracking straightforward.
 *
 * Optimal Approach (Used below):
 * - Bottom-Up 2D Tabulation with a running global maximum.
 * - Key Insight: dp[i][j] represents the length of the longest common
 *   substring ending at s1[i-1] and s2[j-1].
 *   Unlike LCS, we do NOT take max(dp[i-1][j], dp[i][j-1]) on a mismatch —
 *   a substring must be contiguous, so any mismatch resets the cell to 0.
 * - Step 1: Initialize dp table of size (n+1) x (m+1) with all zeros.
 *   - The extra row/column of zeros acts as the base case (empty prefix).
 * - Step 2: Fill the table left-to-right, top-to-bottom.
 *   - If s1[i-1] == s2[j-1] -> dp[i][j] = 1 + dp[i-1][j-1]
 *   - Else                  -> dp[i][j] = 0
 *   - Update ans = max(ans, dp[i][j]) after every match.
 * - Step 3: Return ans (the global max, not dp[n][m]).
 *
 * Time Complexity:
 * - O(N * M) {Fill the entire DP table}
 * Space Complexity:
 * - O(N * M) {DP table} — reducible to O(M) with a single previous row
 *
 * Notes:
 * - Whenever you see:
 *   - "Longest contiguous matching segment across two sequences."
 *   - "Match must not be broken — any mismatch restarts from zero."
 *   - "Two pointers moving in lockstep diagonally (i-1, j-1) only."
 *   - This is the Contiguous-Match / Reset DP Pattern.
 * - The single most important distinction from LCS:
 *   - LCS   -> mismatch takes max(dp[i-1][j], dp[i][j-1])  {skip allowed}
 *   - LCStr -> mismatch sets dp[i][j] = 0                  {no skip, hard reset}
 *   - Mixing these two rules is the most common bug on this problem.
 * - The answer is tracked as a running max, NOT read from dp[n][m]:
 *   - dp[n][m] is only the length of the common substring ending at the
 *     very last characters of both strings — often 0.
 *   - Forgetting to update ans inside the match branch is a silent bug
 *     (code compiles, produces wrong output).
 * - Space optimization is straightforward here:
 *   - dp[i][j] only depends on dp[i-1][j-1] — a single diagonal cell.
 *   - A single 1D array iterated right-to-left (or two rows) suffices,
 *     reducing space from O(N*M) to O(M).
 * - The 1-indexed dp table trick (dp[i] maps to s1[i-1]) cleanly
 *   eliminates boundary checks and makes the zero-row base case implicit.
 */

package dp.String_DP;

public class LongestCommonSubstring {
    public int longCommSubstr(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int ans = 0;
        int[][] dp = new int[n + 1][m + 1];

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){

                if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    ans = Math.max(ans, dp[i][j]);
                }
                else{
                    dp[i][j] = 0;
                }
            }
        }
        return ans;
    }
}
