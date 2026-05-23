/**
 * Problem:
 * <Fibonacci Number>
 *
 * Link:
 * <https://leetcode.com/problems/fibonacci-number/>
 *
 * Pattern:
 * <Recursion>
 *
 * Brute Force Intuition:
 * - Fibonacci follows a natural recursive definition.
 * - nth fibonacci number is:
 *      fib(n) = fib(n - 1) + fib(n - 2)
 *
 * - Base cases:
 *      fib(0) = 0
 *      fib(1) = 1
 *
 * - Recursively compute:
 *      fib(n - 1)
 *      fib(n - 2)
 * - Add both results.
 *
 * - Why is it inefficient?
 * - Same subproblems are solved repeatedly.
 * - Example:
 *      fib(5)
 *      ├── fib(4)
 *      │   ├── fib(3)
 *      │   └── fib(2)
 *      └── fib(3)
 *
 * - fib(3) is computed multiple times.
 * - Number of recursive calls grows exponentially.
 *
 * Time Complexity:
 * - O(2^N)
 *
 * Space Complexity:
 * - O(N)
 * - Recursive stack depth can go up to N.
 *
 * Better Approach Intuition:
 * - Use Memoization (Top-Down DP).
 * - Store already computed fibonacci values.
 * - Before solving a subproblem:
 *      Check if answer already exists.
 * - Avoid repeated recursive computations.
 *
 * - Why is it better?
 * - Each fibonacci state is computed only once.
 * - Removes overlapping subproblem recomputation.
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(N)
 * - DP array + recursion stack.
 *
 * Optimal Approach Intuition:
 * - Observe:
 *      fib(i) depends only on:
 *          fib(i - 1)
 *          fib(i - 2)
 *
 * - No need to store entire DP array.
 * - Maintain only:
 *      prev1 -> fib(i - 1)
 *      prev2 -> fib(i - 2)
 *
 * - Iteratively compute next fibonacci number.
 *
 * - Why is it optimal?
 * - Eliminates recursion overhead.
 * - Eliminates extra DP array.
 * - Uses constant extra space.
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Fibonacci is one of the most important examples of:
 *      Overlapping Subproblems
 *      Optimal Substructure
 *
 * - This problem explains why Dynamic Programming exists.
 *
 * - Why recursive solution becomes exponential?
 * - Every call creates two more recursive calls.
 * - Recursion tree keeps expanding rapidly.
 *
 * - Why recursive space complexity is O(N) and NOT O(2^N)?
 * - Only one recursive path stays active at a time.
 * - Stack depth grows linearly.
 *
 * - Recursive solution is intuitive and mirrors mathematical definition.
 *
 * - Memoization is usually the first DP optimization.
 *
 * - Tabulation avoids recursion completely.
 *
 * - Space optimization is possible because only previous two states matter.
 *
 * - Fibonacci teaches:
 *      Recursion
 *      DP Thinking
 *      State Transition
 *      Space Optimization
 *
 * - Real Interview Insight:
 * - Interviewers often expect progression:
 *      Recursion
 *      Memoization
 *      Tabulation
 *      Space Optimization
 *
 * - Important Transition Thinking:
 * - "What repeated work exists?"
 * - "Can we store answers?"
 * - "Do we really need the full DP array?"
 */

package recursion;

public class FibonacciNumber {
    public int fib(int n) {
        if(n == 0 || n == 1) return n;

        int prev2 = 0, prev1 = 1;

        for(int i = 2; i <= n; i++){
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
