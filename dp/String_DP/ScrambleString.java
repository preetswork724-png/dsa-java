/**
 * Problem:
 * Scramble String
 * (Determine if string s2 is a scrambled version of string s1)
 *
 * Link:
 * https://leetcode.com/problems/scramble-string/
 *
 * Pattern:
 * Top-Down DP (3D Memoization) — Interval DP on Two Strings
 *
 * Brute Force Intuition:
 * - A string can be scrambled by recursively splitting it at any index,
 *   then either keeping both halves in the same order or swapping them.
 * - At every split point k (1 <= k < len), two choices exist:
 *   - No swap  : s1[0..k-1] scrambles to s2[0..k-1]
 *                AND s1[k..n-1] scrambles to s2[k..n-1].
 *   - Swap     : s1[0..k-1] scrambles to s2[n-k..n-1]
 *                AND s1[k..n-1] scrambles to s2[0..n-k-1].
 * - Base cases:
 *   - If s1 == s2           -> true  {identical strings are trivially scrambled}
 *   - If sorted(s1) != sorted(s2) -> false  {impossible if character counts differ}
 * - Why it is inefficient?
 * - At each level, we try N-1 split points, each spawning 2 recursive calls.
 * - The same (s1_substring, s2_substring) pair is re-evaluated many times
 *   via different recursion paths.
 * - Time complexity is exponential — O(N^4) unique subproblem pairs but
 *   without memoization each is recomputed repeatedly.
 *
 * Time Complexity:
 * - O(2^N * N) {Exponential recursive tree without memoization}
 * Space Complexity:
 * - O(N^2) {Recursion depth * string length at each level}
 *
 * Better Approach Intuition:
 * - Memoization (Top-Down DP) — cache results by (i, j, len):
 *   - i   :- starting index in s1.
 *   - j   :- starting index in s2.
 *   - len :- length of the current substring being compared.
 * - memo[i][j][len] :- whether s1[i..i+len-1] is a scramble of s2[j..j+len-1].
 * - Recurrence: for each split point k from 1 to len-1:
 *   - No swap  : isScramble(i,     j,         k      )
 *                && isScramble(i+k, j+k,       len-k  )
 *   - Swap     : isScramble(i,     j+len-k,   k      )
 *                && isScramble(i+k, j,         len-k  )
 *   - Return true if ANY split point with either choice succeeds.
 * - Why 3D memo and not 2D?
 * - Two strings + variable window length = three independent dimensions.
 * - (i, j) alone is insufficient — the same starting pair appears in
 *   subproblems of different lengths.
 *
 * Time Complexity:
 * - O(N^4) {N^3 unique (i, j, len) states * O(N) split points per state}
 * Space Complexity:
 * - O(N^3) {3D memoization table}
 *
 * Why memoization IS the optimal approach here?
 * - Bottom-Up tabulation is possible but extremely tricky to order correctly:
 *   dp[len][i][j] must be filled in increasing order of len, and the
 *   dependency on two sub-intervals of varying lengths makes the iteration
 *   order non-trivial to get right without bugs.
 * - Top-Down with memoization naturally handles the dependency order via
 *   recursion and is the standard accepted approach for this problem.
 * - The HashMap<String, Boolean> variant (keying on substring pairs) is
 *   functionally equivalent but slower due to string hashing overhead —
 *   the int[n][n][n+1] array memo is strictly faster.
 *
 * Optimal Approach (Used below):
 * - Top-Down 3D Memoization.
 * - memo[i][j][len] stores:
 *     0  -> not yet computed
 *     1  -> true  (s1[i..i+len-1] IS a scramble of s2[j..j+len-1])
 *    -1  -> false (it is NOT a scramble)
 * - Step 1: Base case — if the substrings are equal, return true immediately.
 * - Step 2: Pruning — if sorted characters differ, return false immediately.
 * - Step 3: Try all N-1 split points, both swap and no-swap variants.
 *   Return true as soon as any combination succeeds.
 * - Step 4: Cache the result before returning.
 *
 * Time Complexity:
 * - O(N^4) {N^3 states * O(N) split points}
 * Space Complexity:
 * - O(N^3) {3D memo table}
 *
 * Notes:
 * - Whenever you see:
 *   - "Recursive tree splitting with optional swap at each level."
 *   - "Two strings of equal length — is one a rearrangement via splits?"
 *   - "Interval DP where both strings shrink together."
 *   - This is the 3D Interval DP Pattern — index in s1, index in s2,
 *     and window length as three independent state dimensions.
 * - The three-value memo (0 / 1 / -1) is critical:
 *   - A boolean array cannot distinguish "not computed" from "computed false."
 *   - Initialise the entire array to 0. Write 1 for true, -1 for false.
 *   - Checking memo[i][j][len] != 0 is the correct cache-hit guard.
 *   - Using a Boolean[] (nullable) is the alternative — null means unvisited.
 * - The sorted-characters pruning is not optional decoration:
 *   - If s1 and s2 have different character frequency maps, no amount of
 *     splitting and swapping can make them equal — return false immediately.
 *   - This prune cuts massive branches of the recursion tree early.
 *   - Implement as: sort chars of s1[i..i+len-1] and s2[j..j+len-1] and compare,
 *     or maintain a frequency diff array and check all-zeros.
 * - Split point indexing — the most common off-by-one trap:
 *   - k runs from 1 to len-1 inclusive (never 0 or len — those are empty splits).
 *   - No-swap  left  half : (i,     j,       k    )
 *   - No-swap  right half : (i+k,   j+k,   len-k  )
 *   - Swap     left  half : (i,     j+len-k, k    )
 *   - Swap     right half : (i+k,   j,       len-k )
 *   - Write these four lines out explicitly — compressing them into a
 *     single expression is a readability trap that hides index bugs.
 * - This problem does NOT reduce to LCS or Edit Distance:
 *   - Those are linear scan problems; this is a tree-split problem.
 *   - Attempting an LCS-style recurrence produces wrong answers because
 *     it cannot model the swap operation at arbitrary split points.
 * - HashMap<String, Boolean> vs int[n][n][n+1] memo:
 *   - Both are correct. The array version avoids string hashing and
 *     substring allocation — prefer it in interviews for performance.
 *   - The string key version is easier to reason about when first
 *     deriving the solution — use it to find the recurrence, then
 *     switch to the array for the final implementation.
 * - Equal length is a prerequisite:
 *   - If s1.length() != s2.length(), return false immediately before
 *     any DP work — scrambling never changes the total length.
 */
package dp.String_DP;

import java.util.HashMap;
import java.util.Map;

public class ScrambleString {
    Map<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;

        String key = s1 + "#" + s2;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (!isAnagram(s1, s2)) {
            memo.put(key, false);
            return false;
        }

        int n = s1.length();

        for (int k = 1; k < n; k++) {

            if (isScramble(s1.substring(0, k), s2.substring(0, k)) &&
                    isScramble(s1.substring(k), s2.substring(k))) {
                memo.put(key, true);
                return true;
            }

            if (isScramble(s1.substring(0, k), s2.substring(n - k)) &&
                    isScramble(s1.substring(k), s2.substring(0, n - k))) {
                memo.put(key, true);
                return true;
            }
        }
        memo.put(key, false);
        return false;
    }

    public boolean isAnagram(String s1, String s2) {
        int[] freq = new int[26];
        for (char ch : s1.toCharArray()) freq[ch - 'a']++;
        for (char ch : s2.toCharArray()) freq[ch - 'a']--;

        for (int f : freq) if (f != 0) return false;
        return true;
    }
}
