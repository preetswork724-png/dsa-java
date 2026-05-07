/**
 * Problem:
 * <Max Sum of Rectangle No Larger Than K>
 *
 * Link:
 * <https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/>
 *
 * Pattern:
 * <2D Prefix Sum + Kadane Variation + TreeSet>
 *
 *
 * ---------------------------------------------------
 * Brute Force Intuition:
 * ---------------------------------------------------
 * - A rectangle inside a matrix is determined by:
 *
 *      top row
 *      bottom row
 *      left column
 *      right column
 *
 * - So:
 *      Try every possible rectangle.
 *
 * - For each rectangle:
 *      Traverse all elements inside it.
 *      Compute the sum.
 *
 * - If:
 *
 *      sum <= k
 *
 *      update answer.
 *
 *
 * ---------------------------------------------------
 * Why is it inefficient?
 * ---------------------------------------------------
 * - Rectangle sum is recalculated repeatedly.
 * - Many overlapping areas are traversed again and again.
 *
 * Example:
 * - While calculating:
 *
 *      rectangle(0,0 → 2,2)
 *
 * - We again traverse most of the same cells for:
 *
 *      rectangle(0,0 → 2,1)
 *      rectangle(0,0 → 1,2)
 *
 * - Massive repeated work.
 *
 *
 * ---------------------------------------------------
 * Time Complexity:
 * ---------------------------------------------------
 * - O(M^3 × N^3)
 *
 * ---------------------------------------------------
 * Space Complexity:
 * ---------------------------------------------------
 * - O(1)
 *
 *
 *
 * ---------------------------------------------------
 * Better Approach Intuition:
 * ---------------------------------------------------
 * - Instead of recalculating rectangle sums repeatedly:
 *
 *      Build a 2D Prefix Sum Matrix.
 *
 * - Prefix sum helps compute any rectangle sum
 *   in O(1).
 *
 *
 * ---------------------------------------------------
 * Core Insight:
 * ---------------------------------------------------
 * - prefix[r][c] stores:
 *
 *      Sum of all elements from:
 *      (0,0) -> (r-1,c-1)
 *
 * - Using inclusion-exclusion:
 *
 *      rectangleSum =
 *      total
 *      - upperPart
 *      - leftPart
 *      + overlap
 *
 *
 * ---------------------------------------------------
 * Rectangle Sum Formula:
 * ---------------------------------------------------
 *
 *      sum =
 *      prefix[bottom+1][right+1]
 *      - prefix[top][right+1]
 *      - prefix[bottom+1][left]
 *      + prefix[top][left]
 *
 *
 * ---------------------------------------------------
 * Why is this better?
 * ---------------------------------------------------
 * - Rectangle sum becomes O(1).
 * - No need to traverse inside rectangle repeatedly.
 *
 *
 * ---------------------------------------------------
 * Time Complexity:
 * ---------------------------------------------------
 * - Building prefix matrix:
 *      O(M × N)
 *
 * - Trying all rectangles:
 *      O(M^2 × N^2)
 *
 * - Total:
 *      O(M^2 × N^2)
 *
 *
 * ---------------------------------------------------
 * Space Complexity:
 * ---------------------------------------------------
 * - O(M × N)
 *
 *
 *
 * ---------------------------------------------------
 * Why is it still not optimal?
 * ---------------------------------------------------
 * - Even though rectangle sum is O(1):
 *
 *      We still generate ALL possible rectangles.
 *
 * - Number of rectangles itself is huge:
 *
 *      O(M^2 × N^2)
 *
 * - Still too slow for larger constraints.
 *
 *
 *
 * ---------------------------------------------------
 * Optimal Approach (Used Below):
 * ---------------------------------------------------
 * - Instead of fixing:
 *
 *      top
 *      bottom
 *      left
 *      right
 *
 * - We optimize by:
 *
 *      Fixing only rows
 *      and compressing columns.
 *
 *
 * ---------------------------------------------------
 * Core Observation:
 * ---------------------------------------------------
 * - Suppose we fix:
 *
 *      top row
 *      bottom row
 *
 * - For every column:
 *
 *      colSum[col] =
 *      sum of elements between
 *      top -> bottom
 *
 * - This converts the 2D matrix into a:
 *
 *      1D array problem
 *
 *
 * ---------------------------------------------------
 * Example:
 * ---------------------------------------------------
 * matrix:
 *
 *      1   0   1
 *      0  -2   3
 *
 * If:
 *      top = 0
 *      bottom = 1
 *
 * compressed array:
 *
 *      [1, -2, 4]
 *
 *
 * ---------------------------------------------------
 * Reduced Problem:
 * ---------------------------------------------------
 * - Now the problem becomes:
 *
 *      Find maximum subarray sum <= k
 *
 * inside a 1D array.
 *
 *
 * ---------------------------------------------------
 * Why Normal Kadane Fails?
 * ---------------------------------------------------
 * - Kadane only finds:
 *
 *      maximum subarray sum
 *
 * - But here we need:
 *
 *      maximum subarray sum <= k
 *
 * Example:
 *
 *      arr = [2,2,-1]
 *      k = 3
 *
 * - Kadane gives:
 *      4
 *
 * - But:
 *      4 > 3
 *
 * - Invalid answer.
 *
 *
 * ---------------------------------------------------
 * Prefix Sum Intuition:
 * ---------------------------------------------------
 * - Suppose:
 *
 *      prefix[i]
 *
 * stores sum till i.
 *
 * - Then:
 *
 *      subarraySum =
 *      prefix[i] - prefix[j]
 *
 * - We need:
 *
 *      prefix[i] - prefix[j] <= k
 *
 * - Rearranging:
 *
 *      prefix[j] >= prefix[i] - k
 *
 *
 * ---------------------------------------------------
 * Key Insight:
 * ---------------------------------------------------
 * - For every current prefix:
 *
 *      currPrefix
 *
 * - We need the:
 *
 *      smallest previous prefix
 *      >= currPrefix - k
 *
 * - Why smallest?
 *
 * Because:
 *
 *      currPrefix - prevPrefix
 *
 * becomes maximum.
 *
 *
 * ---------------------------------------------------
 * Why TreeSet?
 * ---------------------------------------------------
 * - We need:
 *
 *      1. Insert prefix sums
 *      2. Find smallest prefix >= target
 *
 * - TreeSet provides:
 *
 *      ceiling(value)
 *
 * in:
 *
 *      O(log N)
 *
 *
 * ---------------------------------------------------
 * Algorithm Steps:
 * ---------------------------------------------------
 * 1. Fix top row.
 * 2. Expand bottom row.
 * 3. Build compressed column array.
 * 4. Solve:
 *
 *      max subarray sum <= k
 *
 *      using:
 *      prefix sums + TreeSet
 *
 * 5. Track global maximum answer.
 *
 *
 * ---------------------------------------------------
 * Mathematical Intuition:
 * ---------------------------------------------------
 *
 *      subarraySum =
 *      prefix[i] - prefix[j]
 *
 * Constraint:
 *
 *      prefix[i] - prefix[j] <= k
 *
 * Rearranged:
 *
 *      prefix[j] >= prefix[i] - k
 *
 * TreeSet helps efficiently find:
 *
 *      smallest prefix satisfying condition
 *
 *
 * ---------------------------------------------------
 * Dry Run:
 * ---------------------------------------------------
 * compressed array:
 *
 *      [1, -2, 4]
 *
 * k = 2
 *
 * Initialize:
 *
 *      set = [0]
 *      prefix = 0
 *
 *
 * Step 1:
 *      prefix = 1
 *
 *      target = 1 - 2 = -1
 *
 *      ceiling(-1) = 0
 *
 *      subarray = 1 - 0 = 1
 *
 *
 * Step 2:
 *      prefix = -1
 *
 *      target = -3
 *
 *      ceiling(-3) = 0
 *
 *      subarray = -1
 *
 *
 * Step 3:
 *      prefix = 3
 *
 *      target = 1
 *
 *      ceiling(1) = 1
 *
 *      subarray = 2
 *
 * Final Answer:
 *      2
 *
 *
 * ---------------------------------------------------
 * Time Complexity:
 * ---------------------------------------------------
 * - Row boundaries:
 *      O(M^2)
 *
 * - For each pair:
 *
 *      compression:
 *      O(N)
 *
 *      TreeSet operations:
 *      O(N log N)
 *
 * - Total:
 *
 *      O(M^2 × N log N)
 *
 *
 * ---------------------------------------------------
 * Space Complexity:
 * ---------------------------------------------------
 * - Compressed array:
 *      O(N)
 *
 * - TreeSet:
 *      O(N)
 *
 * - Total:
 *      O(N)
 *
 *
 *
 * ---------------------------------------------------
 * Key Learning:
 * ---------------------------------------------------
 * - Hard 2D problems are often solved by:
 *
 *      Reducing them into 1D problems.
 *
 * - Main transformation:
 *
 *      2D Rectangle
 *      -> 1D Subarray
 *
 * - Main trick:
 *
 *      prefix[i] - prefix[j] <= k
 *
 *      becomes
 *
 *      prefix[j] >= prefix[i] - k
 *
 * - TreeSet ceiling() is the heart
 *   of the optimal solution.
 */

package dp.Kadane;

public class MaxSumOfRectangleNoLargerThanK {

}
