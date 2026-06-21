/**
 * Problem:
 * Shortest Common Supersequence
 *
 * Link:
 * https://leetcode.com/problems/shortest-common-supersequence/
 *
 * Pattern:
 * Bottom-Up DP (2D Tabulation) + String Reconstruction
 *
 * Brute Force Intuition:
 * - Generate all supersequences of both strings and find the shortest one
 *   that contains both str1 and str2 as subsequences.
 * - For each character, we have 3 choices:
 *   - Take the character from str1 only.
 *   - Take the character from str2 only.
 *   - Take a common character from both (when they match).
 * - Base case:
 *   If i == str1.length() -> append remaining characters of str2.
 *   If j == str2.length() -> append remaining characters of str1.
 *
 * - Why it is inefficient?
 * - Re-computes already computed (i, j) pairs.
 * - Exploring all combinations is exponential.
 * - Time complexity is exponential.
 *
 * Time Complexity:
 * - O(3^(N + M)) {Three choices at every step}
 * Space Complexity:
 * - O(N + M) {Recursion Depth}
 *
 * Better Approach Intuition:
 * - Memoization (Top-Down DP).
 * - Instead of recomputing the same (i, j) pair, cache the result.
 * - memo[i][j] :- represents the length of the shortest common supersequence
 *   of str1[i...] and str2[j...].
 * - Recurrence:
 *   - If str1[i] == str2[j] -> 1 + solve(i+1, j+1)              {characters match, take once}
 *   - Else -> 1 + min(solve(i+1, j), solve(i, j+1))             {take the cheaper option}
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
 * - Reconstructing the actual string from memoization is harder to trace.
 * - Bottom-Up makes reconstruction straightforward by backtracking the DP table.
 *
 * Optimal Approach (Used below):
 * - Bottom-Up 2D Tabulation + Backtracking for reconstruction.
 * - Key Insight: SCS length = len(str1) + len(str2) - LCS(str1, str2).
 *   The LCS part is shared, so it is counted once instead of twice.
 * - Step 1: Build the LCS DP table.
 *   - dp[i][j] :- LCS length of str1[0...i-1] and str2[0...j-1].
 *   - If str1[i-1] == str2[j-1] -> dp[i][j] = 1 + dp[i-1][j-1]
 *   - Else -> dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 * - Step 2: Reconstruct the SCS by backtracking the DP table.
 *   - If str1[i-1] == str2[j-1] -> take the character once, move diagonally (i-1, j-1).
 *   - Else if dp[i-1][j] > dp[i][j-1] -> take str1[i-1], move up (i-1, j).
 *   - Else -> take str2[j-1], move left (i, j-1).
 *   - Append any remaining characters of str1 or str2 once a boundary is hit.
 * - Reverse the reconstructed string at the end (built backwards during backtracking).
 *
 * Time Complexity:
 * - O(N * M) {Fill DP table} + O(N + M) {Backtrack and reconstruct}
 * Space Complexity:
 * - O(N * M) {DP table}
 *
 * Notes:
 * - Whenever you see:
 *   - "Shortest string that contains both sequences as subsequences."
 *   - "Merge two sequences with minimum total length."
 *   - "Two pointers moving independently across two inputs with reconstruction."
 *   - This is the Two-Sequence DP Pattern with String Reconstruction.
 * - Key relationship to remember:
 *   - SCS Length = len(str1) + len(str2) - LCS(str1, str2).
 *   - Every character NOT in the LCS must appear separately in the SCS.
 *   - Every character IN the LCS appears exactly once (shared contribution).
 * - Reconstruction is the trickier part of this problem compared to plain LCS:
 *   - On a match -> include once and move diagonally.
 *   - On a mismatch -> include the character from the string that contributed
 *     to the larger LCS value and move in that direction.
 * - The 1-indexed dp table trick (dp[i] maps to str1[i-1]) cleanly
 *   eliminates boundary checks and simplifies base case initialization.
 */

package dp.String_DP;

public class ShortestCommonSupersequence {
    public String shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length(), m = str2.length();

        // Step 1: Build LCS DP
        int[][] dp = new int[n + 1][m + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        // Step 2: Build SCS string
        StringBuilder res = new StringBuilder();
        int i = 0, j = 0;

        while (i < n && j < m) {
            if (str1.charAt(i) == str2.charAt(j)) {
                res.append(str1.charAt(i));
                i++;
                j++;
            } else if (dp[i + 1][j] > dp[i][j + 1]) {
                res.append(str1.charAt(i));
                i++;
            } else {
                res.append(str2.charAt(j));
                j++;
            }
        }

        // Remaining characters
        while (i < n) {
            res.append(str1.charAt(i++));
        }
        while (j < m) {
            res.append(str2.charAt(j++));
        }

        return res.toString();
    }
}
