/**
 * Problem:
 * <Target Sum>
 *
 * Link:
 * <https://leetcode.com/problems/target-sum/>
 *
 * Pattern:
 * <Dynamic Programming / Subset Sum / Count Variants>
 *
 *
 * Brute Force Intuition:
 * - For each number, two choices: assign '+' or assign '-'.
 * - Recurse over all numbers, reducing target accordingly.
 * - At base case (index == n), check if target has reached 0.
 * - If yes, increment count.
 *
 * - Why it is inefficient?
 * - Total expressions = 2^N.
 * - Same (index, remaining target) state recomputed many times.
 * - Explores all possibilities without caching anything.
 *
 * Time Complexity:
 * - O(2^N)
 *
 * Space Complexity:
 * - O(N) recursion stack
 *
 *
 * Brute Force Bug (in original code):
 *
 * BUG → Gating the '+' branch and treating second call as "skip":
 * - Original:
 *     if(nums[index] <= target) helper(..., target - nums[index], ...);  // '+'
 *     helper(..., target, ...);                                           // NOT '-'
 *
 * - Problem 1: Second call passes target unchanged → it is NOT assigning '-'.
 *              It is accidentally "skipping" the number entirely, which is
 *              not a valid operation in this problem.
 *
 * - Problem 2: Guard nums[index] <= target blocks valid paths.
 *              Target can go negative mid-recursion (e.g. when we subtract
 *              a large number). Never gate either branch.
 *
 * - Fix:
 *     helper(nums, index + 1, target - nums[index], n);  // assign '+'
 *     helper(nums, index + 1, target + nums[index], n);  // assign '-'
 *     Both branches always explored, no guard needed.
 *
 *
 * Better Approach Intuition (Memoization):
 * - Observation from brute force:
 *   Same (index, remaining target) pair solved multiple times → cache it.
 *
 * - Define state:
 * - dp[index][target] → number of ways to reach target using elements from index..n-1
 *
 * - Recurrence:
 * - dp[i][t] = dp[i+1][t - nums[i]]      (assign '+')
 *            + dp[i+1][t + nums[i]]      (assign '-')
 *
 * - Base Case:
 * - i == n and t == 0 → return 1 (valid expression found)
 * - i == n and t != 0 → return 0
 *
 * - Why it is better?
 * - Eliminates overlapping subproblems.
 * - Each (index, target) state computed only once.
 *
 * - Why it is still not optimal?
 * - Target can range from -sum(nums) to +sum(nums) → needs offset for array indexing.
 * - Uses recursion stack overhead.
 * - HashMap needed for memo (target can be negative → can't use plain 2D array easily).
 *
 * Time Complexity:
 * - O(N * sum)   where sum = sum of all nums
 *
 * Space Complexity:
 * - O(N * sum) memo + O(N) recursion stack
 *
 *
 * Better++ Approach (Mathematical Reduction to Subset Sum):
 * - Key Observation:
 *   Partition nums into two groups:
 *   - P = subset assigned '+'
 *   - N = subset assigned '-'
 *
 *   Then:
 *     sum(P) - sum(N) = target        ... (1)
 *     sum(P) + sum(N) = totalSum      ... (2)
 *
 *   Adding (1) and (2):
 *     2 * sum(P) = target + totalSum
 *     sum(P)     = (target + totalSum) / 2
 *
 * - Reduction:
 *   "Count expressions evaluating to target"
 *   becomes
 *   "Count subsets with sum = (target + totalSum) / 2"
 *
 * - Early exits:
 * - If (target + totalSum) is odd → return 0 (sum(P) can't be fractional)
 * - If |target| > totalSum        → return 0 (impossible to reach)
 *
 * - Why this is powerful?
 * - Reduces a ±assignment problem to a clean subset count problem.
 * - Now solvable with standard Perfect Sum / 0-1 Knapsack DP.
 *
 * Time Complexity:
 * - O(N * subsetTarget)
 *
 * Space Complexity:
 * - O(N * subsetTarget)   for 2D DP
 *
 *
 * Optimal Approach (1D DP Space Optimization):
 * - After reduction, apply 1D DP exactly as in Perfect Sum Problem.
 *
 * - Define:
 * - dp[j] = number of ways to form sum j
 *
 * - Initialization:
 * - dp[0] = 1
 *
 * - Transition:
 * - for each num in nums:
 * -   for j from subsetTarget down to num:     ← REVERSE (0/1, no reuse)
 * -     dp[j] += dp[j - num]
 *
 * - Why reverse iteration?
 * - Ensures each number is used at most once.
 * - Forward iteration would allow reuse → wrong count.
 *
 * - Why it is optimal?
 * - Same logic as 2D DP after reduction.
 * - Space reduced to O(subsetTarget).
 *
 * Time Complexity:
 * - O(N * subsetTarget)
 *
 * Space Complexity:
 * - O(subsetTarget)
 *
 *
 * Key Insight:
 * - The ± assignment looks like a different problem but is secretly a subset count.
 * - The algebraic reduction is the most important step:
 *     sum(P) = (target + totalSum) / 2
 * - Once reduced, it is identical to the Perfect Sum Problem.
 *
 * Notes:
 * - subsetTarget = (target + totalSum) / 2  → always non-negative after abs check.
 * - Zeros in nums double the count (each zero can be +0 or -0, both valid).
 *   1D DP handles this naturally via dp[j] += dp[j - 0] = dp[j] += dp[j].
 * - Unlike Pure Subset Sum, target here can be negative → always check |target| <= totalSum.
 *
 * - Common mistakes:
 * - Gating '+' branch with nums[i] <= target → blocks valid negative paths ❌
 * - Treating second recursive call as "skip" instead of '-' assignment ❌
 * - Forgetting odd-sum early exit → integer division silently gives wrong subsetTarget ❌
 * - Forgetting |target| > totalSum early exit → dp array sized incorrectly ❌
 * - Forward loop in 1D DP → reuses numbers, overcounts ❌
 *
 * - Mental model:
 * - "Split nums into a '+' group and a '-' group."
 * - "Count ways to pick a '+' group with sum = (target + totalSum) / 2."
 *
 * Edge cases:
 * - target = 0         → count subsets with sum = totalSum / 2
 * - nums contains 0s   → zeros double the count; handled automatically
 * - target > totalSum  → return 0
 * - target < -totalSum → return 0
 * - Single element     → 1 if nums[0] == |target|, else 0
 *
 * Comparison with Related Problems:
 * ┌──────────────────────────────────┬──────────┬─────────────────────────────────────┐
 * │ Problem                          │ Answer   │ Key idea                            │
 * ├──────────────────────────────────┼──────────┼─────────────────────────────────────┤
 * │ Subset Sum (exists?)             │ Boolean  │ dp[j] = dp[j] OR dp[j - num]        │
 * │ Perfect Sum (count subsets)      │ Integer  │ dp[j] += dp[j - num]                │
 * │ Target Sum (±assignment count)   │ Integer  │ Reduce to Perfect Sum, same DP      │
 * │ Partition Equal Subset Sum       │ Boolean  │ target = totalSum / 2, boolean DP   │
 * └──────────────────────────────────┴──────────┴─────────────────────────────────────┘
 *
 * Key takeaway:
 * - Recognizing the algebraic reduction is everything.
 * - Once reduced, it is a Perfect Sum count problem — no new DP needed.
 * - The ± framing is a disguise; the underlying structure is always subset sum.
 */

package dp;

public class TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length, totalSum = 0;

        for(int num : nums) totalSum += num;

        int sum = target + totalSum;

        if(sum < 0 || sum % 2 != 0) return 0;
        if(Math.abs(target) > totalSum) return 0;

        int subsetTarget = sum / 2;

        int[] dp = new int[subsetTarget + 1];
        dp[0] = 1;

        for(int num : nums){
            for(int j = subsetTarget; j >= num; j--){
                dp[j] += dp[j - num];
            }
        }

        return dp[subsetTarget];
    }
}
