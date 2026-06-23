/**
 * Problem:
 * Minimum Number of Deletions and Insertions
 *
 * Link:
 * https://www.geeksforgeeks.org/minimum-number-deletions-insertions-transform-one-string-another/
 *
 * Pattern:
 * Bottom-Up DP (2D Tabulation) — LCS Reduction
 *
 * Brute Force Intuition:
 * - Try all possible sequences of delete and insert operations on s1.
 * - At each step, either delete a character from the current string or
 *   insert a character from s2 into the current string.
 * - Track the minimum total operations needed to reach s2.
 * - Why it is inefficient?
 * - The search space of possible intermediate strings is exponential.
 * - No reuse of overlapping subproblems.
 * - Re-explores the same intermediate states repeatedly.
 * - Time complexity is exponential.
 *
 * Time Complexity:
 * - O(2^(N + M)) {Exponential states from all operation sequences}
 * Space Complexity:
 * - O(N + M) {Recursion depth}
 *
 * Better Approach Intuition:
 * - Key Insight: The characters we do NOT need to touch are exactly the
 *   characters in the Longest Common Subsequence (LCS) of s1 and s2.
 *   - Characters in s1 but NOT in LCS -> must be deleted.
 *   - Characters in s2 but NOT in LCS -> must be inserted.
 * - So the problem reduces entirely to finding LCS(s1, s2):
 *   - Deletions  = len(s1) - LCS
 *   - Insertions = len(s2) - LCS
 *   - Total ops  = (len(s1) - LCS) + (len(s2) - LCS)
 *                = len(s1) + len(s2) - 2 * LCS
 * - This insight collapses an exponential problem into a standard LCS solve.
 *
 * Why memoization alone is still not the final step?
 * - Recursive call stack overhead on large inputs.
 * - Bottom-Up is cleaner, avoids stack depth issues, and the LCS
 *   table is a well-known pattern easy to implement correctly.
 *
 * Optimal Approach (Used below):
 * - Step 1: Compute LCS(s1, s2) using standard Bottom-Up 2D tabulation.
 *   - dp[i][j] :- LCS length of s1[0..i-1] and s2[0..j-1].
 *   - If s1[i-1] == s2[j-1] -> dp[i][j] = 1 + dp[i-1][j-1]
 *   - Else                  -> dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 * - Step 2: Apply the reduction formulas directly.
 *   - int lcs        = dp[n][m]
 *   - int deletions  = n - lcs
 *   - int insertions = m - lcs
 *
 * Time Complexity:
 * - O(N * M) {Fill the LCS DP table}
 * Space Complexity:
 * - O(N * M) {DP table} — reducible to O(M) with two rolling rows
 *
 * Notes:
 * - Whenever you see:
 *   - "Minimum operations to convert / transform one string into another."
 *   - "How many characters to remove from s1 and add from s2?"
 *   - "Cost of aligning two sequences with only deletions and insertions."
 *   - This is the LCS Reduction Pattern — solve LCS, then apply formulas.
 * - Key relationships to remember:
 *   - Deletions  = len(s1) - LCS(s1, s2)
 *   - Insertions = len(s2) - LCS(s1, s2)
 *   - Total      = len(s1) + len(s2) - 2 * LCS(s1, s2)
 *   - These are not guesses — they follow directly from the definition
 *     of LCS as the maximum characters already in the right relative order.
 * - Do NOT confuse with Edit Distance (Levenshtein):
 *   - Edit Distance also allows substitutions (replace one char for another).
 *   - This problem allows ONLY deletions and insertions — no substitutions.
 *   - Substitution = 1 edit in Levenshtein, but = 2 ops here (delete + insert).
 *   - Using the Edit Distance recurrence here will produce wrong answers.
 * - Do NOT confuse with Shortest Common Supersequence:
 *   - SCS length = len(s1) + len(s2) - LCS  {each char counted once}
 *   - Total del+ins = len(s1) + len(s2) - 2*LCS  {LCS subtracted twice}
 *   - The factor of 2 is the key difference — LCS characters are
 *     preserved as-is and need zero operations, so they are subtracted
 *     from BOTH strings, not just once.
 * - The 1-indexed dp table trick (dp[i] maps to s1[i-1]) cleanly
 *   eliminates boundary checks and simplifies base case initialization.
 * - Space can be reduced to O(M) since dp[i][j] only depends on
 *   dp[i-1][j-1], dp[i-1][j], and dp[i][j-1] — the previous row suffices.
 */

package dp.String_DP;

public class MinimumNumberOfDeletionsAndInsertions {
    public int minOperations(String s1, String s2) {
        int lcs = findLCS(s1, s2);
        return (s1.length() - lcs) + (s2.length() - lcs);
    }

    public int findLCS(String s1, String s2) {

        int n = s1.length(), m = s2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {

                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[0][0];
    }
}
