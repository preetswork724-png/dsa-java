/**
 * Problem:
 * <Longest Common Subsequence>
 *
 * Link:
 * <https://leetcode.com/problems/longest-common-subsequence/>
 *
 * Pattern:
 * <Bottom-Up DP (2D Tabulation)>
 *
 * Brute Force Intuition:
 * - Generate all subsequences of both strings and find the longest match.
 * - For each character in text1, we have 2 choices:
 *   - Either include it in the subsequence and check if it matches text2.
 *   - Or skip it.
 * - Base case:
 *   If i == text1.length() || j == text2.length() -> return 0.
 *
 * - Why it is inefficient?
 * - Re-computes already computed (i, j) pairs.
 * - Generating all subsequences is exponential.
 * - Time complexity is exponential.
 *
 * Time Complexity:
 * - O(2^N * 2^M) {All subsequences of both strings}
 * Space Complexity:
 * - O(N + M) {Recursion Depth}
 *
 * Better Approach Intuition:
 * - Memoization (Top-Down DP).
 * - Instead of recomputing the same (i, j) pair, cache the result.
 * - memo[i][j] :- represents the length of the longest common subsequence
 *   of text1[i...] and text2[j...].
 * - Recurrence:
 *   - If text1[i] == text2[j] -> 1 + solve(i+1, j+1)  {characters match, take both}
 *   - Else -> max(solve(i+1, j), solve(i, j+1))        {skip one character from either}
 * - Why a 2D memo and not 1D?
 * - Because the result depends on the current position in BOTH strings simultaneously,
 *   not just one.
 *
 * Time Complexity:
 * - O(N * M) {N * M unique states, O(1) work per state}
 * Space Complexity:
 * - O(N * M) {Memoization table + recursion stack}
 *
 * Why this is still not optimal?
 * - Recursive call stack overhead.
 * - Bottom-Up avoids this by filling the table iteratively
 *   and can further be space-optimized to O(M).
 *
 * Optimal Approach (Used below):
 * - Bottom-Up 2D Tabulation.
 * - dp[i][j] :- represents the LCS length of text1[0...i-1] and text2[0...j-1].
 * - Recurrence:
 *   - If text1[i-1] == text2[j-1] -> dp[i][j] = 1 + dp[i-1][j-1]  {match: extend LCS}
 *   - Else -> dp[i][j] = max(dp[i-1][j], dp[i][j-1])               {no match: best of skipping either}
 * - Base case:
 *   dp[i][0] = 0 and dp[0][j] = 0 for all i, j  {empty string has LCS = 0}
 * - Fill row by row; each cell depends only on the previous row and
 *   the current row's previous column, so it can be reduced to O(M) space
 *   using two 1D arrays (prev and curr).
 *
 * Time Complexity:
 * - O(N * M) {Fill entire DP table}
 * Space Complexity:
 * - O(N * M) -> reducible to O(M) with two 1D rolling arrays
 *
 * Notes:
 * - Whenever you see:
 *   - "Longest / Shortest common structure across two sequences."
 *   - "Subsequence (not substring — characters need not be contiguous)."
 *   - "Two pointers moving independently across two inputs."
 *   - This is the Two-Sequence DP Pattern.
 * - Classic siblings of this pattern:
 *   - Edit Distance (Levenshtein Distance).
 *   - Shortest Common Supersequence.
 *   - Longest Common Substring (slight variation — reset on mismatch).
 *   - Wildcard / Regex Matching.
 * - Key distinction — LCS vs LCS Substring:
 *   - LCS: dp[i][j] = max(dp[i-1][j], dp[i][j-1]) on mismatch  {carry forward}
 *   - Substring: dp[i][j] = 0 on mismatch                       {reset}
 * - The 1-indexed dp table trick (dp[i] maps to text1[i-1]) cleanly
 *   eliminates boundary checks and simplifies the base case initialization.
 */

package dp;

public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();

        int[][] dp = new int[n + 1][m + 1];

        for(int i = n - 1; i >= 0; i--){
            for(int j = m - 1; j >= 0; j--){

                if(text1.charAt(i) == text2.charAt(j)){
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                }
                else{
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[0][0];
    }
}
