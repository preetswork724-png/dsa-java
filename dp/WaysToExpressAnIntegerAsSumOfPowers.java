/**
 * Problem:
 * <Ways to Express an Integer as Sum of Powers>
 *
 * Link:
 * <https://leetcode.com/problems/ways-to-express-an-integer-as-sum-of-powers/description/>
 *
 * Pattern:
 * <0/1 KnapSack Dp>
 *
 * Brute Force Intuition:
 * - For each number i, we have 2 choices:
 * - Include i^x.
 * - Skip i^x.
 * - Recursive definition:
 * - ways(n, i) = ways(n - i^x) + ways(n, i + 1).
 * - Base cases:
 * - if(n == 0) -> return 1.
 * - if(n < 0 && i > n ) -> return 0.
 *
 * - Why it is inefficient?
 * - Exponential.
 * - Recomputes same states repeatedly.
 *
 * Time Complexity:
 * - O(2^K)
 * Space Complexity:
 * - O(K)
 *
 * Better Approach intuition:
 * - Use memoized recursion.
 * - Store result of each (n, i).
 * - State space:
 * - n -> 0 to N.
 * - i -> i to k.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N * K) + O(k) {Recursion stack}
 *
 * Why this is still not optimal?
 * - It is already optimal, but we can reduce space.
 *
 * Optimal Approach (Used below):
 * - Bottom-Up DP.
 * - Convert recursion to iterative DP.
 * - Let dp[s] = number of ways to make sum s.
 * - dp[0] = 1 {exactly one way to make sum 0, by choosing nothing}.
 * - Update s from n down to power:
 * - dp[s]= += dp[s - power]
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(n)
 *
 * Notes:
 * - The time complexity for the better and optimal approach would've been O(N * K) but it is O(N^2) when x = 1.
 * - Why loop backward?
 * - To avoid reuse of numbers.
 */

package dp;

public class WaysToExpressAnIntegerAsSumOfPowers {

    public static void main(String[] args) {
        System.out.println(numberOfWays(10, 2));
    }

    static int MOD = 1_00_00_00_007;

    public static int numberOfWays(int n, int x) {
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for(int i = 1; ; i++){

            long power = (long)(Math.pow(i, x));

            if(power > n) break;

            for(int s = n; s >= power; s--){
                dp[s] = (dp[s] + dp[(int)(s - power)]) % MOD;
            }
        }
        return dp[n];
    }
}
