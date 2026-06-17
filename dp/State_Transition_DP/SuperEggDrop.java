/**
 * Problem:
 * Super Egg Drop
 * (Find the minimum number of trials to determine the critical floor
 *  with k eggs and n floors)
 *
 * Link:
 * <https://leetcode.com/problems/super-egg-drop/>
 *
 * Pattern:
 * Bottom-Up DP (2D Tabulation) — Reframe: Max Floors from Trials
 *
 * Brute Force Intuition:
 * - Drop an egg from every possible floor x (1 to n).
 * - At each floor x, two outcomes:
 *   - Egg breaks   -> critical floor is below, recurse on (k-1, x-1).
 *   - Egg survives -> critical floor is above, recurse on (k, n-x).
 * - Worst case at floor x = 1 + max(helper(k-1, x-1), helper(k, n-x)).
 * - Take minimum over all x: minMoves = min(minMoves, worst).
 * - Base cases:
 *   n == 0 || n == 1 -> return n   {0 floors = 0 trials, 1 floor = 1 trial}
 *   k == 1           -> return n   {1 egg -> must check every floor linearly}
 *
 * Time Complexity:
 * - O(N^K) {At every level, N choices each branching into two subproblems}
 * Space Complexity:
 * - O(N) {Recursion depth}
 *
 * Better Approach Intuition:
 * - Memoization (Top-Down DP).
 * - dp[k][n] :- minimum trials to check n floors with k eggs.
 * - Same recurrence as brute force — just cache (k, n) results.
 * - Base cases remain identical.
 * - Cache hit guard: if(dp[k][n] != null) return dp[k][n].
 *
 * Time Complexity:
 * - O(N * K * N) = O(K * N^2) {K*N unique states, O(N) inner floor loop per state}
 * Space Complexity:
 * - O(K * N) {Memoization table + recursion stack}
 *
 * Why this is still not optimal?
 * - The inner floor loop costs O(N) per state even with memoization.
 * - O(K * N) states * O(N) inner loop = O(K * N^2) — TLE on large inputs.
 * - The reframe below eliminates the inner loop entirely.
 *
 * Optimal Approach (Used below):
 * - Reframe: instead of "min trials for N floors", ask
 *   "with e eggs and f trials, what is the maximum floors we can check?"
 * - dp[moves][eggs] :- maximum floors checkable with given eggs and moves.
 * - At each trial, drop from some floor:
 *   - Egg breaks   -> check dp[moves-1][eggs-1] floors below.
 *   - Egg survives -> check dp[moves-1][eggs]   floors above.
 *   - Current floor itself = +1.
 *   dp[moves][eggs] = dp[moves-1][eggs-1] + dp[moves-1][eggs] + 1
 * - Base case: dp[0][*] = 0 {0 moves -> 0 floors checkable, implicit from int[][]}.
 * - Answer: increment moves until dp[moves][k] >= n, return moves.
 *
 * Time Complexity:
 * - O(K * log N) {At most log N moves needed, K eggs per move}
 * Space Complexity:
 * - O(K * N) {2D dp table}
 *
 * Notes:
 * - The reframe is the entire insight of this problem:
 *   - Brute / Memo : dp[k][n]       = min trials for n floors, k eggs.
 *                    Inner loop over all drop floors -> O(N) per state.
 *   - Optimal      : dp[moves][eggs] = max floors for given moves, eggs.
 *                    No inner loop -> O(1) per state.
 *   - Both model the same physics — only the index direction is flipped.
 * - The +1 in dp[moves-1][eggs-1] + dp[moves-1][eggs] + 1 is the
 *   current drop floor itself:
 *   - dp[moves-1][eggs-1] = floors covered BELOW if egg breaks.
 *   - dp[moves-1][eggs]   = floors covered ABOVE if egg survives.
 *   - The drop floor is always definitively resolved — hence +1.
 *   - Omitting +1 makes coverage perpetually one short, causing the
 *     while loop to overshoot by one move on boundary inputs.
 * - Note the axis swap from memo to optimal:
 *   - Memo    : dp[k][n]        -> rows = eggs, cols = floors.
 *   - Optimal : dp[moves][eggs] -> rows = moves, cols = eggs.
 *   - Mixing the two indexing schemes is the most common bug when
 *     translating between approaches.
 * - An egg is consumed ONLY on a break (eggs-1), not on a survive (eggs stays):
 *   - Break branch  : dp[moves-1][eggs-1]  {one fewer egg remaining}
 *   - Survive branch: dp[moves-1][eggs]    {same egg reusable}
 *   - Writing dp[moves-1][eggs-1] for both branches is a silent bug —
 *     it models eggs as always consumed, underestimating coverage.
 * - The answer is NOT dp[k][n]:
 *   - Increment moves in a while loop until dp[moves][k] >= n.
 *   - The first moves value that satisfies this is the answer.
 *   - Reading a fixed cell as the answer is the classic indexing trap.
 * - k == 1 base case intuition:
 *   - With one egg, you cannot afford any breaks — must go floor by floor.
 *   - This costs exactly n trials in the worst case.
 *   - The optimal reframe handles this implicitly via the recurrence,
 *     but the brute/memo approaches need it as an explicit guard.
 */

package dp.State_Transition_DP;

public class SuperEggDrop {
    public int superEggDrop(int k, int n) {
        int[][] dp = new int[n + 1][k + 1];
        int moves = 0;

        while (dp[moves][k] < n) {
            moves++;
            for (int eggs = 1; eggs <= k; eggs++) {
                dp[moves][eggs] = dp[moves - 1][eggs - 1] +
                        dp[moves - 1][eggs] + 1;
            }
        }
        return moves;
    }
}
