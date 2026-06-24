/**
 * Problem:
 * Minimum Cost to Cut a Stick
 *
 * Link:
 * <https://leetcode.com/problems/minimum-cost-to-cut-a-stick/>
 *
 * Pattern:
 * Interval DP (Bottom-Up)
 *
 * Brute Force Intuition:
 * - Try all possible orderings of cuts recursively.
 * - For a stick segment [l, r], try every cut point k in between.
 * - Each cut costs (r - l), and then recursively solve the two sub-segments.
 * - Base case:
 *   If no cut point exists between l and r -> return 0 (no cost).
 *
 * - Why it is inefficient?
 * - Re-computes overlapping sub-problems (same [l, r] segment evaluated multiple times).
 * - Time complexity is exponential in the number of cuts.
 *
 * Time Complexity:
 * - O(M! ) in the worst case (all orderings)
 * Space Complexity:
 * - O(M) {Recursion Depth}
 *
 * Better Approach Intuition:
 * - Memoization (Top-Down DP).
 * - Instead of recomputing the same segment [l, r], cache the result.
 * - memo[i][j] :- represents the minimum cost to make all cuts between
 *   arr[i] and arr[j] (where arr contains boundary points + cut points).
 * - Why not just a 1D array?
 * - Because cost depends on both the left and right boundary of the current segment,
 *   not just a single index.
 *
 * Time Complexity:
 * - O(M^3) {M^2 states, M transitions each}
 * Space Complexity:
 * - O(M^2) {Memoization table}
 *
 * Why this is still not optimal?
 * - Recursive call stack overhead and cache lookup cost.
 * - Bottom-Up avoids this by filling the table iteratively.
 *
 * Optimal Approach (Used below):
 * - Bottom-Up Interval DP.
 * - Key Insight: The cost of cutting segment [arr[i], arr[j]] is always
 *   (arr[j] - arr[i]), regardless of which cut point k we choose first.
 * - Reformulate: Merge arr[0]=0 and arr[m+1]=n with sorted cut points
 *   to treat boundaries uniformly.
 * - Enumerate all segment lengths (len = 2 to m+1) and all start indices i.
 * - For each segment [i, j], try every internal cut point k:
 *   dp[i][j] = min(dp[i][j], (arr[j] - arr[i]) + dp[i][k] + dp[k][j])
 * - If no cut exists between i and j -> dp[i][j] = 0 (base case).
 * - Build from smaller segments to larger ones so sub-problems are
 *   already solved when needed.
 *
 * Time Complexity:
 * - O(M^3) {Two loops for segment + one loop for cut point}
 * Space Complexity:
 * - O(M^2) {DP table}
 *
 * Notes:
 * - Whenever you see:
 *   - "Minimum/Maximum cost over a range or interval."
 *   - "Order of operations affects the result."
 *   - "Splitting a structure and combining sub-results."
 *   - This is the Interval DP Pattern.
 * - Classic siblings of this pattern:
 *   - Matrix Chain Multiplication.
 *   - Burst Balloons.
 *   - Strange Printer.
 * - The trick of appending 0 and n to the cuts array is crucial —
 *   it eliminates edge-case handling for boundary segments.
 * - Always sort the augmented array before filling the DP table,
 *   since interval DP relies on contiguous, ordered segments.
 */

package dp.Partition_DP;

import java.util.Arrays;

public class MinimumCostToCutAStick {
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        int[] arr = new int[m + 2];

        for (int i = 0; i < m; i++) {
            arr[i + 1] = cuts[i];
        }

        arr[0] = 0;
        arr[m + 1] = n;

        Arrays.sort(arr);

        int[][] dp = new int[m + 2][m + 2];

        for (int len = 2; len < m + 2; len++) {
            for (int i = 0; i + len < m + 2; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i + 1; k < j; k++) {
                    int cost = (arr[j] - arr[i]) + dp[i][k] + dp[k][j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }

                if (dp[i][j] == Integer.MAX_VALUE) {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[0][m + 1];
    }
}
