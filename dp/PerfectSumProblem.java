/**
 * Problem:
 * <Perfect Sum Problem - Count Subsets with Sum Equal to Target>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1>
 *
 * Pattern:
 * <Dynamic Programming / Subset Sum / Count Variants>
 *
 *
 * Brute Force Intuition:
 * - Try all subsets using recursion (take / not take).
 * - For each subset:
 *   - Compute sum and check if it equals target.
 *   - If yes, increment count.
 *
 * - Why it is inefficient?
 *   - Total subsets = 2^N.
 *   - Repeated computation of same states (index, remaining target).
 *   - Does unnecessary work by exploring all possibilities.
 *
 * Time Complexity:
 * - O(2^N)
 *
 * Space Complexity:
 * - O(N) recursion stack
 *
 *
 * Better Approach Intuition (Memoization):
 * - Define state:
 *   - dp[index][target] → number of subsets using elements from index
 *                         onward that sum to target.
 *
 * - Recurrence:
 *   - dp[index][target] = dp[index+1][target]                        (not take)
 *                       + dp[index+1][target - arr[index]]           (take, if arr[index] <= target)
 *
 * - Base Case:
 *   - dp[n][0] = 1   → empty subset always forms sum 0
 *   - dp[n][t] = 0   → no elements left, non-zero target → no subset
 *
 * - Why it is better?
 *   - Eliminates overlapping subproblems.
 *   - Each (index, target) state computed only once.
 *
 * - Why it is still not optimal?
 *   - Uses recursion stack overhead.
 *   - Uses O(N * target) space for memo table + O(N) stack.
 *
 * Time Complexity:
 * - O(N * target)
 *
 * Space Complexity:
 * - O(N * target) + O(N) recursion stack
 *
 *
 * Better++ Approach (Bottom-Up DP / Tabulation):
 * - Define:
 *   - dp[i][j] = count of subsets using first i elements that sum to j.
 *
 * - Transition:
 *   - dp[i][j] = dp[i-1][j]                         (not take arr[i-1])
 *              + dp[i-1][j - arr[i-1]]               (take, if arr[i-1] <= j)
 *
 * - Base Case:
 *   - dp[i][0] = 1 for all i   → empty subset always valid
 *
 * - Why this works?
 *   - Builds solution iteratively from smaller subproblems.
 *   - No recursion overhead.
 *
 * - CRITICAL - Handling Zeros in Array:
 *   - If arr[i] = 0:
 *     - "Not take" path: dp[i-1][j]
 *     - "Take" path:     dp[i-1][j - 0] = dp[i-1][j]   ← same state!
 *     - So dp[i][j] = 2 * dp[i-1][j]  (zero doubles the count!)
 *   - This is intentional and CORRECT.
 *   - Example: arr = [0, 1], target = 1
 *     - Subsets: {1}, {0, 1} → count = 2 ✓
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
 *   - dp[i][j] depends only on dp[i-1][...] → compress to 1D.
 *
 * - Define:
 *   - dp[j] = count of subsets that sum to j
 *
 * - Initialization:
 *   - dp[0] = 1
 *
 * - Transition:
 *   - for each num in arr:
 *     - for j from target → num:           ← REVERSE to avoid reuse
 *       - dp[j] += dp[j - num]
 *
 * - Why reverse iteration?
 *   - In 0/1 knapsack, each element used at most once.
 *   - Forward iteration would allow reuse of same element → wrong count.
 *   - Reverse ensures we only use values from the "previous row."
 *
 * - CRITICAL - Zeros break 1D DP:
 *   - If arr[i] = 0, then j - num = j → dp[j] += dp[j]
 *     → dp[j] doubles every time a 0 is processed.
 *   - But this happens during the SAME row update, not previous row!
 *   - Example: arr = [0], target = 0
 *     - Correct answer: 2 subsets ({} and {0})
 *     - 1D DP gives: dp[0] = 2 ✓ (doubles once, which is correct here)
 *   - For multiple zeros it can miscount → handle zeros separately
 *     or stick with 2D DP for correctness when zeros are present.
 *
 * Time Complexity:
 * - O(N * target)
 *
 * Space Complexity:
 * - O(target)
 *
 *
 * Key Insight:
 * - Unlike Partition Equal Subset Sum (boolean answer),
 *   this problem asks for a COUNT → accumulate instead of OR-ing.
 *
 * - The shift from boolean to count:
 *   - Partition:    dp[j] = dp[j] OR dp[j - num]
 *   - Perfect Sum:  dp[j] = dp[j] +  dp[j - num]
 *
 * Notes:
 * - dp[0] = 1 is the seed: empty subset has sum 0.
 * - Zeros in input require careful handling:
 *   - 2D DP handles zeros naturally and correctly.
 *   - 1D DP may overcount with multiple zeros → prefer 2D when zeros present.
 * - Answer may be large → problem may ask for result modulo 10^9+7.
 *
 * - Important conditions:
 *   - dp[i][0] = 1 for all i (not just i=0)
 *   - Only add take-path when arr[i-1] <= j
 *
 * - Common mistakes:
 *   - Initializing dp[0][0] = 1 only, forgetting dp[i][0] = 1 ❌
 *   - Using OR instead of addition (boolean vs count) ❌
 *   - Using forward loop in 1D DP → allows element reuse ❌
 *   - Ignoring zeros in array → wrong count for zero-containing inputs ❌
 *   - Forgetting modulo when answer can overflow ❌
 *
 * - Mental model:
 *   - "Count the number of ways to fill a bag of capacity target
 *      using given numbers, each used at most once."
 *
 * Edge cases:
 * - target = 0       → answer is at least 1 (empty subset)
 *                      + 1 more for each zero in array
 * - arr contains 0s  → zeros multiply subset count; handle carefully
 * - All elements > target → answer is 0 (or 1 if target = 0)
 * - Large N / target → DP feasible; remember modulo
 *
 * Comparison with Related Problems:
 * ┌─────────────────────────────────┬──────────┬───────────────────────────────┐
 * │ Problem                         │ Answer   │ DP Transition                 │
 * ├─────────────────────────────────┼──────────┼───────────────────────────────┤
 * │ Subset Sum (exists?)            │ Boolean  │ dp[j] = dp[j] OR dp[j-num]    │
 * │ Partition Equal Subset Sum      │ Boolean  │ Same, target = totalSum / 2   │
 * │ Perfect Sum (count subsets)     │ Integer  │ dp[j] = dp[j] + dp[j-num]     │
 * │ Coin Change II (unbounded)      │ Integer  │ Forward loop (reuse allowed)   │
 * └─────────────────────────────────┴──────────┴───────────────────────────────┘
 *
 * Key takeaway:
 * - Recognizing "count" vs "exists" variants is the most important step.
 * - Zero handling is the main gotcha unique to this problem.
 * - 2D DP is safer and more intuitive; 1D is optimal but needs care with zeros.
 */

package dp;

public class PerfectSumProblem {
    public int perfectSum(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int num : nums) {

            for (int j = target; j >= num; j--) {

                dp[j] = dp[j] + dp[j - num];
            }
        }
        return dp[target];
    }
}
