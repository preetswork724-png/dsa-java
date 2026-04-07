/**
 * Problem:
 * <Partition Equal Subset Sum>
 *
 * Link:
 * <https://leetcode.com/problems/partition-equal-subset-sum/](https://leetcode.com/problems/partition-equal-subset-sum/>
 *
 * Pattern:
 * <Dynamic Programming / Subset Sum>
 *
 *
 * Brute Force Intuition:
 * - Try all subsets using recursion (take / not take).
 * - For each subset:
 * - Compute sum and check if it equals totalSum / 2.
 *
 * - Why it is inefficient?
 * - Total subsets = 2^N.
 * - Repeated computation of same states (index, target).
 * - Does unnecessary work by exploring all possibilities.
 *
 * Time Complexity:
 * - O(2^N)
 *
 * Space Complexity:
 * - O(N) recursion stack
 *
 *
 * Better Approach Intuition (Memoization):
 * - Reduce problem:
 * ```
 Instead of 2 subsets → find ONE subset with sum = totalSum / 2.
 ```
 *
 *  Define state:
 * - dp[index][target] → can we form target using elements from index onward?
 * - Cache results to avoid recomputation.
 *
 * - Why it is better?
 * - Eliminates overlapping subproblems.
 * - Each state computed only once.
 *
 * - Why it is still not optimal?
 * - Uses recursion stack.
 * - Uses 2D DP (higher space usage).
 *
 * Time Complexity:
 * - O(N * target)
 *
 * Space Complexity:
 * - O(N * target) + O(N) recursion stack
 *
 *
 * Better++ Approach (Bottom-Up DP):
 * - Define:
 * - dp[i][j] = can we form sum j using first i elements?
 *
 * - Transition:
 * - dp[i][j] = dp[i-1][j]                      (not take)
 * - OR
 * - dp[i-1][j - nums[i-1]]         (take, if nums[i-1] <= j)
 *
 * Base Case:
 * - dp[i][0] = true (sum 0 always possible)
 *
 * - Why this works?
 * - Builds solution iteratively.
 * - Removes recursion overhead.
 *
 * Time Complexity:
 * - O(N * target)
 *
 * Space Complexity:
 * - O(N * target)
 *
 *
 * Optimal Approach (1D DP Space Optimization):
 * - Observation:
 * - Current state depends only on previous row.
 * - Define:
 * - dp[j] = can we form sum j?
 *
 * - Initialization:
 * - dp[0] = true
 * - Transition:
 * - for each num in nums:
 * - for j from target → num:
 * - dp[j] = dp[j] OR dp[j - num]
 *
 * - Why reverse iteration?
 * - Prevents reuse of same element multiple times.
 *
 * - Why it is optimal?
 * - Same logic as 2D DP.
 * - Space reduced to O(target).
 *
 * Time Complexity:
 * - O(N * target)
 *
 * Space Complexity:
 * - O(target)
 *
 *
 * Key Insight:
 * - Total sum must be even.
 * - Problem reduces to:
 * - "Find subset with sum = totalSum / 2"
 *
 * Notes:
 * - If totalSum is odd → return false immediately.
 * - Max sum constraint makes DP feasible (≤ 20000).
 *
 * - Important conditions:
 * - dp[0] = true
 * - Only process nums[i] ≤ current target
 *
 * - Common mistakes:
 * - Trying to form two subsets explicitly ❌
 * - Forgetting even sum check ❌
 * - Using forward loop in 1D DP ❌
 *
 * - Mental model:
 * - "Fill a bag of capacity target using given numbers"
 *
 * Edge cases:
 * - Single element → false (unless 0)
 * - All elements equal → depends on count
 * - Large input size → DP still works due to bounded sum
 *
 * - Key takeaway:
 * - Classic Subset Sum problem.
 * - Recognizing reduction is the most important step.
 */

package dp;

public class PartitionEqualSubsetSum {
}
