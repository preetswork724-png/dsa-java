/**
 * Problem:
 * <Climbing Stairs>
 *
 * Link:
 * <https://leetcode.com/problems/climbing-stairs/description/>
 *
 * Pattern:
 * <Iterative Fibonacci>
 *
 * Brute Force Intuition:
 * - Explore all the possible ways to reach down from the top staircase.
 * - Recursive definition:
 * - ways(n - 1) + ways(n - 2).
 *
 * - Why it is inefficient?
 * - Exponential.
 * - Recomputes same states repeatedly.
 *
 * Time Complexity:
 * - O(2^n)
 * Space Complexity:
 * - O(n)
 *
 * Better Approach intuition:
 * - Use memoized recursion.
 * - Store the number of steps taken to reach the nth staircase.
 * - dp[n] :- represents number of steps taken to reach the nth staircase.
 * - dp[n] = dfs(n-1) + dfs(n-2).
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why this is still not optimal?
 * - Still uses recursion.
 *
 * Optimal Approach (Used below):
 * - Iterative fibonacci.
 * - To reach n steps:
 * - If you are at the nth step, ways -> 1 (do nothing).
 * - If you are at the n-1th step, ways -> 1 (climb one stair).
 * - If you are at the n-2th step, ways -> 2 ({1, 1}, {2}).
 * - prev1 = 1, prev2 = 1.
 * - steps = prev1 + prev2.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - If you ever see:
 * - f(n) = f(n-1) + f(n-2)
 * - And no constraints like uniqueness or subsets —
 * - That is NOT backtracking.
 * - That is Fibonacci-style DP.
 */

package dp;

public class ClimbingStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs(3));
    }

    public static int climbStairs(int n) {
        if (n <= 1) return 1;

        int prev2 = 1, prev1 = 1;

        for (int i = 2; i <= n; i++) {
            int steps = prev1 + prev2;
            prev2 = prev1;
            prev1 = steps;
        }
        return prev1;
    }
}
