/**
 * Problem:
 * <Coin Change>
 *
 * Link:
 * <https://leetcode.com/problems/coin-change/description/>
 *
 * Pattern:
 * <1D Bottom-Up DP and Knapsack style problem>
 *
 * Brute Force Intuition:
 * - At each step:
 * - Try every coin.
 * - Reduce the amount.
 * - Update minimum result
 * - Base case:
 *      amount == 0 -> return 0.
 *      amount < 0 -> Integer.MAX_VALUE;
 *
 * - Why it is inefficient?
 * - For each amount:
 * - We try all coins.
 * - Height of recursion is approximately equal to amount.
 *
 * Time Complexity:
 * - O(coins ^ amount)
 * Space Complexity:
 * - O(amount) {Recursion depth}
 *
 * Better Approach intuition:
 * - Memoization.
 * - So, instead of computing the minimum number of coins for the same amount repeatedly:
 * - We store the minimum number of coins corresponding to that amount.
 * - dp[i] - represents the minimum number of coins that were used to make this amount.
 *
 * Time Complexity:
 * - O(amount * number_of_coins) {For each amount from 0 -> A: We compute once for each coin}
 * Space Complexity:
 * - O(N) {amount} + O(amount) {Recursion depth}
 *
 * Why this is still not optimal?
 * - It is optimal but it still uses recursion.
 *
 * Optimal Approach (Used below):
 * - dp[i] - minimum number of coins to make amount i.
 * - dp[0] = 0. Minimum number of coins needed to make amount 0.
 * - Outer loop:
 * - Now, we compute the minimum number of coins to make every amount from 1 to amount:
 * - We try from smaller amounts to bigger amounts.
 * - Because when we form a bigger amount, all smaller states will have already been computed.
 * - Inner loop:
 * - For each amount i:
 * - We ask if I use this coin what happens?
 * - We are implementing:
 * - dp[i] = min(dp[i], 1 + dp[i-coin]).
 * - I choose this coin now, +1.
 * - Remaining amount [i - coin].
 * - That sub problem is already solved.
 * - So combine them.
 *
 * Time Complexity:
 * - O(amount * number_of_coins).
 * Space Complexity:
 * - O(amount)
 *
 * Notes:
 * - Why using amount + 1 is safe?
 * - Because the sum of coins should never exceed amount.
 * - And besides, using something like MAX_VALUE + 1 could overflow.
 */

package dp;

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
