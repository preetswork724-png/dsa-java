/**
 * Problem:
 * <Coin Change (Minimum Coins)>
 *
 * Link:
 * <https://leetcode.com/problems/coin-change/>
 *
 * Pattern:
 * <Dynamic Programming / Unbounded Knapsack>
 *
 *
 * Brute Force Intuition:
 * - For each coin, two choices: TAKE it or SKIP it.
 * - TAKE → reduce amount, stay at same index (unlimited usage).
 * - SKIP → move to next index.
 * - Recurse until amount becomes 0 (valid) or negative (invalid).
 *
 * - Why it is inefficient?
 * - Same (index, amount) state recomputed many times.
 * - Explores all combinations without caching.
 * - Recursion tree grows exponentially.
 *
 * Time Complexity:
 * - O(2^amount)   (exponential, loose upper bound)
 *
 * Space Complexity:
 * - O(amount) recursion stack
 *
 *
 * Brute Force Bugs (in original code):
 *
 * BUG 1 → Using loop incorrectly:
 * - Loop variable not used → repeated same recursive calls.
 * - Causes massive duplication of states.
 *
 * BUG 2 → Tracking numOfCoins manually:
 * - Problem should return minimum coins, not track via global variable.
 * - Leads to harder logic and no reuse of subproblems.
 *
 * BUG 3 → No memoization:
 * - Same (index, amount) recomputed multiple times → exponential blowup.
 *
 *
 * Better Approach Intuition (Memoization):
 * - Observation:
 *   Same (index, amount) state solved multiple times → cache it.
 *
 * - Define state:
 * - dp[index][amount] → minimum coins needed to form amount using coins[index...]
 *
 * - Recurrence:
 * - dp[i][a] = min(
 *       dp[i+1][a],                         (skip coin)
 *       1 + dp[i][a - coins[i]]            (take coin, if a >= coins[i])
 *   )
 *
 * - Base Case:
 * - amount == 0 → return 0 (no coins needed)
 * - index == n or amount < 0 → return INF (invalid/impossible)
 *
 * - Why it is better?
 * - Eliminates overlapping subproblems.
 * - Each (index, amount) computed once.
 *
 * - Why it is still not optimal?
 * - Uses recursion stack.
 * - Uses 2D memo table → higher space.
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
 * - dp[i][j] = minimum coins to form amount j using first i coins
 *
 * - Transition:
 * - dp[i][j] = dp[i-1][j]                                  (skip)
 * - OR
 * - 1 + dp[i][j - coins[i-1]]      (take, if coins[i-1] <= j)
 * - → take minimum
 *
 * - Base Case:
 * - dp[i][0] = 0 → 0 coins needed for amount 0
 * - dp[0][j] = INF → no coins → impossible to form positive amount
 *
 * - Why this works?
 * - Builds solution iteratively.
 * - Removes recursion overhead.
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
 * - dp[i][j] depends on current row (unbounded case) → can compress to 1D
 *
 * - Define:
 * - dp[j] = minimum coins needed to form amount j
 *
 * - Initialization:
 * - dp[0] = 0
 * - rest = INF
 *
 * - Transition:
 * - for each coin:
 * -   for j from coin → amount:        ← FORWARD (important)
 * -     dp[j] = min(dp[j], 1 + dp[j - coin])
 *
 * - Why forward iteration?
 * - Allows reuse of same coin multiple times (unbounded).
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
 * - This is an Unbounded Knapsack (minimization version).
 * - At each step → either reuse same coin or move forward.
 *
 * Notes:
 * - Use INF = amount + 1 (safe upper bound).
 * - If dp[amount] remains INF → return -1.
 * - Unlike subset problems, we minimize instead of count/maximize.
 *
 * - Reverse vs Forward loop:
 * - 0/1 Knapsack   → REVERSE (no reuse)
 * - Unbounded      → FORWARD (reuse allowed) ✅
 *
 * - Common mistakes:
 * - Using reverse loop → turns into 0/1 knapsack ❌
 * - Not handling impossible states properly ❌
 * - Using global variable instead of return values ❌
 * - Forgetting dp[0] = 0 ❌
 *
 * - Mental model:
 * - "At every amount, try all coins and pick the minimum coins needed."
 *
 * Edge cases:
 * - amount = 0 → return 0
 * - no solution → return -1
 * - coins = [1] → always possible
 * - large amount → DP required (brute will fail)
 *
 * - Key takeaway:
 * - Classic Unbounded Knapsack (min coins).
 * - Forward iteration is the key difference from 0/1 knapsack.
 */

package dp.Unbounded_Knapsack;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1,2,5}, 11));
    }

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for(int i = 1; i <= amount; i++){
            for(int coin : coins){

                if(coin <= i){
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
