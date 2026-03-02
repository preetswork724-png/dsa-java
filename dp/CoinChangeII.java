/**
 * Problem:
 * <Coin Change II>
 *
 * Link:
 * <https://leetcode.com/problems/coin-change-ii/description/>
 *
 * Pattern:
 * <1D Bottom-Up DP and Knapsack style problem>
 *
 * Brute Force Intuition:
 * - For each index i:
 * - Keep subtracting amount - coins[index] until the amount becomes 0 or becomes negative.
 * - As soon as you have used coins[index] enough times to form amount, backtrack and choose the next coin.
 *
 * - Why it is inefficient?
 * - For each amount:
 * - We try all coins.
 * - Height of recursion is approximately equal to amount.
 *
 * Time Complexity:
 * - O(2 ^ amount)
 * Space Complexity:
 * - O(amount) {Recursion depth}
 *
 * Better Approach intuition:
 * - Memoization.
 * - So, instead of computing the number of ways for the same amount repeatedly:
 * - We store the number of ways corresponding to that amount and index.
 * - States of dp:
 *   amount
 *   index
 * - dp[amount][index] - represents the number of ways that were used to make this amount at that particular index.
 *
 * Time Complexity:
 * - O(amount * index)
 * Space Complexity:
 * - O(amount * index) + O(amount) {Recursion depth}
 *
 * Why this is still not optimal?
 * - It is optimal but it still uses recursion.
 *
 * Optimal Approach (Used below):
 * - 1D Bottom-Up Dp and KnapSack-type problem.
 * - dp[i] - number of ways to make amount i.
 * - Base case:
 * - dp[0] = 1. There is exactly one way to make amount 0 i.e choose nothing.
 * - So, the formula is:
 * - dp[i] = dp[i] + dp[i - coin].
 * - If I choose coin i.
 * - The remaining amount is i-coin.
 * - Add the number of ways to make i-coin.
 *
 * Time Complexity:
 * - O(coins * amount).
 * Space Complexity:
 * - O(amount)
 *
 * Notes:
 * - Why 1D DP Works
 * - Because:
 * - Each state depends only on previous smaller amounts.
 * - Coins are processed in order.
 * - Infinite usage allowed (unbounded knapsack).
 */

package dp;

public class CoinChangeII {
}
