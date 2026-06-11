/**
 * Problem:
 * <Coin Change II (Count Combinations)>
 *
 * Link:
 * <https://leetcode.com/problems/coin-change-ii/>
 *
 * Pattern:
 * <Dynamic Programming / Unbounded Knapsack (Counting)>
 *
 *
 * Brute Force Intuition:
 * - For each coin, two choices: TAKE it or SKIP it.
 * - TAKE → reduce amount, stay at same index (unlimited usage).
 * - SKIP → move to next index.
 * - Count all valid ways where amount becomes 0.
 *
 * - Why it is inefficient?
 * - Same (index, amount) state recomputed many times.
 * - Recursion explores all combinations → exponential growth.
 *
 * Time Complexity:
 * - O(2^amount) (loose upper bound, exponential)
 *
 * Space Complexity:
 * - O(amount) recursion stack
 *
 *
 * Brute Force Bugs (common mistakes):
 *
 * BUG 1 → Counting permutations instead of combinations:
 * - Allowing coins to be reused in different orders (e.g., [1,2] and [2,1])
 * - Fix: Always move forward (index + 1) in skip → avoids reordering.
 *
 * BUG 2 → Wrong base case for counting:
 * - Returning 0 when amount == 0 ❌
 * - Fix: amount == 0 → return 1 (one valid way found)
 *
 * BUG 3 → Returning -1 for invalid cases:
 * - Counting problems should return 0, not -1
 *
 *
 * Better Approach Intuition (Memoization):
 * - Observation:
 *   Same (index, amount) states repeat → cache results.
 *
 * - Define state:
 * - dp[index][amount] → number of ways to form amount using coins[index...]
 *
 * - Recurrence:
 * - dp[i][a] = dp[i][a - coins[i]]     (take coin i)
 *            + dp[i+1][a]              (skip coin i)
 *
 * - Base Case:
 * - amount == 0 → return 1 (valid combination)
 * - index == n or amount < 0 → return 0 (invalid)
 *
 * - Why it is better?
 * - Eliminates overlapping subproblems.
 * - Each state computed only once.
 *
 * - Why it is still not optimal?
 * - Uses recursion stack.
 * - Uses 2D memo table.
 *
 * Time Complexity:
 * - O(N * amount)
 *
 * Space Complexity:
 * - O(N * amount) memo + O(amount) recursion stack
 *
 *
 * Better++ Approach (Bottom-Up DP / Tabulation):
 * - Define:
 * - dp[i][j] = number of ways to form amount j using first i coins
 *
 * - Transition:
 * - dp[i][j] = dp[i-1][j]                       (skip coin)
 * - OR
 * - dp[i][j - coins[i-1]]                       (take coin, if coins[i-1] <= j)
 * - → sum both
 *
 * - Base Case:
 * - dp[i][0] = 1 → one way (choose nothing)
 * - dp[0][j] = 0 → no coins → no way to form positive amount
 *
 * - Why this works?
 * - Builds solution iteratively.
 * - Avoids recursion overhead.
 *
 * Time Complexity:
 * - O(N * amount)
 *
 * Space Complexity:
 * - O(N * amount)
 *
 *
 * Optimal Approach (1D DP Space Optimization):
 * - Observation:
 * - dp[i][j] depends on current row → can compress to 1D
 *
 * - Define:
 * - dp[j] = number of ways to form amount j
 *
 * - Initialization:
 * - dp[0] = 1
 *
 * - Transition:
 * - for each coin:
 * -   for j from coin → amount:       ← FORWARD (important)
 * -     dp[j] += dp[j - coin]
 *
 * - Why forward iteration?
 * - Ensures combinations (not permutations)
 * - Allows reuse of same coin (unbounded)
 *
 * - Why it is optimal?
 * - Same logic as 2D DP.
 * - Space reduced to O(amount).
 *
 * Time Complexity:
 * - O(N * amount)
 *
 * Space Complexity:
 * - O(amount)
 *
 *
 * Key Insight:
 * - This is an Unbounded Knapsack (counting version).
 * - At each step → either reuse same coin or move forward.
 * - COUNT problems → use addition, not min/max.
 *
 * Notes:
 * - dp[0] = 1 is crucial (empty combination).
 * - Answer is dp[amount].
 * - Returning 0 means no valid combinations exist.
 *
 * - Reverse vs Forward loop:
 * - 0/1 Knapsack → REVERSE
 * - Unbounded (count/min) → FORWARD ✅
 *
 * - Common mistakes:
 * - Using reverse loop → counts subsets incorrectly ❌
 * - Returning -1 for no solution ❌
 * - Forgetting dp[0] = 1 ❌
 * - Allowing permutations instead of combinations ❌
 *
 * - Mental model:
 * - "Count how many ways we can build the amount using given coins,
 *    where order does NOT matter."
 *
 * Edge cases:
 * - amount = 0 → return 1
 * - no coins → return 0 (unless amount = 0)
 * - no possible combination → return 0
 *
 * - Key takeaway:
 * - Classic Unbounded Knapsack (counting).
 * - Forward iteration ensures combination counting.
 * - Difference from Coin Change I → SUM vs MIN.
 */

package dp.Unbounded_Knapsack;

public class CoinChangeII {
    public int change(int amount, int[] coins) {
        int n = coins.length;

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = dp[i] + dp[i - coin];
            }
        }
        return dp[amount];
    }
}
