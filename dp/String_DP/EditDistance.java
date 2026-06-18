/**
 * Problem:
 * Edit Distance
 *
 * Link:
 * <https://leetcode.com/problems/edit-distance/>
 *
 * Pattern:
 * Bottom-Up DP (2D Tabulation) — Two-Sequence DP with Three-Way Choice
 *
 * Brute Force Intuition:
 * - At every pair of positions (i, j), try all three operations:
 *   - Insert  : insert s2[j] into s1, advance j only.
 *   - Delete  : delete s1[i] from s1, advance i only.
 *   - Replace : replace s1[i] with s2[j], advance both i and j.
 * - If s1[i] == s2[j], no operation needed — advance both for free.
 * - Recurse and take the minimum cost across all choices.
 * - Base cases:
 *   If i == s1.length() -> insert all remaining characters of s2
 *                          (cost = s2.length() - j).
 *   If j == s2.length() -> delete all remaining characters of s1
 *                          (cost = s1.length() - i).
 * - Why it is inefficient?
 * - Re-computes the same (i, j) pair through multiple recursive paths.
 * - Three branching choices at every step leads to O(3^(N+M)) calls.
 * - Time complexity is exponential.
 *
 * Time Complexity:
 * - O(3^(N + M)) {Three choices at every step}
 * Space Complexity:
 * - O(N + M) {Recursion depth}
 *
 * Better Approach Intuition:
 * - Memoization (Top-Down DP).
 * - memo[i][j] :- minimum edit distance to convert s1[i..] into s2[j..].
 * - Recurrence:
 *   - If s1[i] == s2[j] -> solve(i+1, j+1)                   {free match, no op}
 *   - Else -> 1 + min(
 *       solve(i,   j+1),   {insert  s2[j] before s1[i]}
 *       solve(i+1, j  ),   {delete  s1[i]}
 *       solve(i+1, j+1)    {replace s1[i] with s2[j]}
 *     )
 * - Why a 2D memo and not 1D?
 * - The result depends on the current position in BOTH strings
 *   simultaneously — two independent variables, one state cell.
 *
 * Time Complexity:
 * - O(N * M) {N * M unique states, O(1) work per state}
 * Space Complexity:
 * - O(N * M) {Memoization table + recursion stack}
 *
 * Why this is still not optimal?
 * - Recursive call stack overhead.
 * - Three recursive branches make the call pattern harder to trace.
 * - Bottom-Up eliminates the stack and makes the three-way choice
 *   explicit and easy to read at every cell.
 *
 * Optimal Approach (Used below):
 * - Bottom-Up 2D Tabulation.
 * - dp[i][j] :- minimum operations to convert s1[0..i-1] into s2[0..j-1].
 * - Step 1: Initialize base cases.
 *   - dp[i][0] = i  for all i  {delete all i characters of s1}
 *   - dp[0][j] = j  for all j  {insert all j characters of s2}
 * - Step 2: Fill the table left-to-right, top-to-bottom.
 *   - If s1[i-1] == s2[j-1] -> dp[i][j] = dp[i-1][j-1]          {free match}
 *   - Else -> dp[i][j] = 1 + min(
 *       dp[i][j-1],    {insert}
 *       dp[i-1][j],    {delete}
 *       dp[i-1][j-1]   {replace}
 *     )
 * - Step 3: Answer is dp[n][m].
 *
 * Time Complexity:
 * - O(N * M) {Fill the entire DP table}
 * Space Complexity:
 * - O(N * M) {DP table} — reducible to O(M) with two rolling rows
 *
 * Notes:
 * - Whenever you see:
 *   - "Minimum operations to convert one string into another."
 *   - "Insert, delete, and replace are all allowed."
 *   - "Spell correction / diff between two sequences."
 *   - This is the Edit Distance (Levenshtein) Pattern —
 *     three-way choice at every mismatch.
 * - The three operations map directly to three neighbours in the table:
 *   - Insert       -> dp[i][j-1]    {came from left   — consumed one char of s2}
 *   - Delete       -> dp[i-1][j]    {came from above  — consumed one char of s1}
 *   - Replace      -> dp[i-1][j-1]  {came from diagonal — consumed one of each}
 *   - Free match   -> dp[i-1][j-1]  {diagonal, no +1 cost}
 *   Memorise the direction → operation mapping — it unlocks reconstruction too.
 * - Do NOT confuse with Min Deletions + Insertions:
 *   - That problem forbids substitution — a replace costs 2 there (delete + insert).
 *   - Here replace costs 1 — a single atomic operation.
 *   - Using the LCS-reduction formula from that problem here underestimates cost.
 * - Do NOT confuse with Longest Common Subsequence:
 *   - LCS counts maximum characters already aligned.
 *   - Edit Distance counts minimum fixes for characters not aligned.
 *   - They are inverses in spirit but the recurrences differ:
 *     LCS never uses a replace branch; Edit Distance does.
 * - Base case intuition — never skip this reasoning:
 *   - dp[i][0] = i  means "to convert s1[0..i-1] to an empty string,
 *     delete all i characters."
 *   - dp[0][j] = j  means "to convert an empty string to s2[0..j-1],
 *     insert all j characters."
 *   - These two boundary conditions seed the entire table correctly.
 *   - Initialising them as 0 (as in LCS) is the most common setup bug.
 * - Reconstruction (if asked to print the operations):
 *   - Backtrack from dp[n][m] to dp[0][0].
 *   - Diagonal + equal   -> no op, move to (i-1, j-1).
 *   - Diagonal + unequal -> replace s1[i-1] with s2[j-1], move to (i-1, j-1).
 *   - Left  (j-1)        -> insert s2[j-1], move to (i, j-1).
 *   - Up    (i-1)        -> delete s1[i-1], move to (i-1, j).
 * - Space can be reduced to O(M):
 *   - dp[i][j] depends on dp[i-1][j-1], dp[i-1][j], dp[i][j-1] only.
 *   - Keep the previous row and one running value for the diagonal.
 * - The 1-indexed dp table trick (dp[i] maps to s1[i-1]) cleanly
 *   eliminates boundary checks and maps base cases to row 0 / column 0.
 */

package dp.String_DP;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) dp[i][0] = i;
        for (int j = 0; j <= m; j++) dp[0][j] = j;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j],
                            Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }
        return dp[n][m];
    }
}
