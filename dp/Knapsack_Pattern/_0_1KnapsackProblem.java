/**
 * Problem:
 * <0/1 Knapsack Problem>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1>
 *
 * Pattern:
 * <Dynamic Programming / 0-1 Knapsack>
 *
 *
 * Brute Force Intuition:
 * - For each item, two choices: TAKE it or SKIP it.
 * - Recurse over all items tracking current index and remaining capacity.
 * - At base case (index == n), record the value accumulated so far.
 *
 * - Why it is inefficient?
 * - Total subsets = 2^N.
 * - Same (index, remainingCapacity) state recomputed many times.
 * - Explores all possibilities without caching anything.
 *
 * Time Complexity:
 * - O(2^N)
 *
 * Space Complexity:
 * - O(N) recursion stack
 *
 *
 * Brute Force Bugs (in original code):
 *
 * BUG 1 → Wrong base case:
 * - Original: if(index == n) { if(W == 0) update maxVal; }
 * - Problem:  Knapsack does NOT need to be filled completely.
 *             Valid solutions can have leftover capacity.
 *             Only counted subsets using EXACTLY W weight → wrong.
 * - Fix:      if(index == n) { maxVal = Math.max(maxVal, value); return; }
 *
 * BUG 2 → Redundant W < 0 guard:
 * - Original: if(W < 0) return; at the top of helper
 * - Problem:  Take-branch already checks wt[index] <= W before recursing,
 *             so W can never go negative. This is dead code.
 * - Fix:      Remove it entirely.
 *
 *
 * Better Approach Intuition (Memoization):
 * - Observation from brute force:
 *   Same (index, remainingW) pair is solved multiple times → cache it.
 *
 * - Define state:
 * - dp[index][w] → max value achievable from index..n-1 with remaining capacity w
 *
 * - Recurrence:
 * - dp[i][w] = max(
 *       dp[i+1][w],                            (skip item i)
 *       val[i] + dp[i+1][w - wt[i]]            (take item i, if wt[i] <= w)
 *   )
 *
 * - Base Case:
 * - i == n → return 0 (no items left → no value possible)
 *
 * - Why it is better?
 * - Eliminates overlapping subproblems.
 * - Each (index, capacity) state computed only once.
 *
 * - Why it is still not optimal?
 * - Uses recursion stack overhead.
 * - Uses 2D memo table (higher space usage).
 *
 * Time Complexity:
 * - O(N * W)
 *
 * Space Complexity:
 * - O(N * W) memo table + O(N) recursion stack
 *
 *
 * Better++ Approach (Bottom-Up DP / Tabulation):
 * - Define:
 * - dp[i][j] = max value using first i items with capacity j
 *
 * - Transition:
 * - dp[i][j] = dp[i-1][j]                                    (skip item i)
 * - OR
 * - val[i-1] + dp[i-1][j - wt[i-1]]      (take item i, if wt[i-1] <= j)
 * - → take the max of both
 *
 * - Base Case:
 * - dp[0][j] = 0 for all j   → no items, no value
 * - dp[i][0] = 0 for all i   → no capacity, no value
 * - (both handled by default Java int[] initialization to 0)
 *
 * - Why this works?
 * - Builds solution iteratively from smaller subproblems.
 * - Removes recursion overhead entirely.
 *
 * Time Complexity:
 * - O(N * W)
 *
 * Space Complexity:
 * - O(N * W)
 *
 *
 * Optimal Approach (1D DP Space Optimization):
 * - Observation:
 * - dp[i][j] depends only on dp[i-1][...] → compress to a single 1D array.
 *
 * - Define:
 * - dp[j] = max value achievable with capacity j
 *
 * - Initialization:
 * - dp[0] = 0, all others = 0
 *
 * - Transition:
 * - for each item i:
 * -   for j from W down to wt[i]:        ← REVERSE
 * -     dp[j] = max(dp[j], val[i] + dp[j - wt[i]])
 *
 * - Why reverse iteration?
 * - dp[j - wt[i]] must still hold the value from the PREVIOUS item's row.
 * - Forward iteration would overwrite dp[j - wt[i]] before we use it,
 *   effectively allowing item i to be used multiple times → wrong for 0/1.
 * - Reverse ensures each item is used at most once.
 *
 * - Why it is optimal?
 * - Same logic as 2D DP.
 * - Space reduced to O(W).
 *
 * Time Complexity:
 * - O(N * W)
 *
 * Space Complexity:
 * - O(W)
 *
 *
 * Key Insight:
 * - At every index, the decision is binary: TAKE or SKIP.
 * - This "take/skip" pattern is the DNA of all 0/1 knapsack variants.
 *
 * Notes:
 * - dp[i][0] = 0 and dp[0][j] = 0 are natural base cases (zero capacity or zero items).
 * - 1-indexed DP table → item i maps to val[i-1] and wt[i-1] in 0-indexed arrays.
 * - Answer is always dp[n][W] (tabulation) or dp[W] (1D).
 *
 * - Reverse vs Forward loop — the single most important 1D DP rule:
 * - 0/1 Knapsack   → REVERSE (j: W → wt[i])   each item used at most once
 * - Unbounded Knap → FORWARD  (j: wt[i] → W)   items can be reused freely
 *
 * - Common mistakes:
 * - Requiring W == 0 at base case (confuses knapsack with exact-sum) ❌
 * - Using forward loop in 1D DP → accidentally solves unbounded knapsack ❌
 * - Off-by-one: forgetting dp table is 1-indexed but arrays are 0-indexed ❌
 * - Initializing memo with 0 instead of -1 → treats unvisited as computed ❌
 *
 * - Mental model:
 * - "Fill a bag of capacity W by choosing items to maximize total value,
 *    each item used at most once."
 *
 * Edge cases:
 * - All weights > W        → no item fits, return 0
 * - Single item fits       → return its value
 * - W = 0                  → return 0 (no capacity)
 * - All items same weight  → pick highest value ones that fit
 *
 * - Key takeaway:
 * - Classic 0/1 Knapsack.
 * - The reverse iteration trick is the bridge between 2D and 1D DP.
 * - Distinguishing 0/1 (reverse) vs unbounded (forward) is essential.
 */

package dp.Knapsack_Pattern;

public class _0_1KnapsackProblem {
    public int knapsack(int W, int val[], int wt[]) {
        int n = wt.length;

        int[] dp = new int[W + 1];

        for (int i = 0; i < n; i++) {
            for (int j = W; j >= wt[i]; j--) {
                dp[j] = Math.max(dp[j], val[i] + dp[j - wt[i]]);
            }
        }

        return dp[W];
    }
}
