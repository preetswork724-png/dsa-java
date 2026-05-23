/**
 * Problem:
 * <Nth Even Fibonacci Number>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/nth-even-fibonacci-number1119/1>
 *
 * Pattern:
 * <Mathematical Recurrence + Space Optimized DP>
 *
 *
 * Brute Force Intuition:
 * - Generate normal Fibonacci sequence.
 * - Check whether each fibonacci number is even or not.
 * - Count even fibonacci numbers until nth even term is reached.
 *
 * - Why is it inefficient?
 * - We generate many unnecessary odd fibonacci numbers.
 * - Only every 3rd fibonacci number is even.
 *
 *
 * Time Complexity:
 * - O(K)
 * - K depends on how many fibonacci numbers are generated.
 *
 * Space Complexity:
 * - O(1)
 *
 *
 * Better Observation:
 * - Every 3rd Fibonacci number is even.
 *
 * Example:
 *      0 1 1 2 3 5 8 13 21 34 ...
 *            ↑     ↑       ↑
 *
 * - Instead of generating all fibonacci numbers,
 *   directly generate only even fibonacci numbers.
 *
 *
 * Important Mathematical Observation:
 * - Even Fibonacci numbers follow their own recurrence:
 *
 *      E(n) = 4 * E(n - 1) + E(n - 2)
 *
 * - Base values:
 *
 *      E(1) = 2
 *      E(2) = 8
 *
 *
 * Verification:
 *
 *      34 = 4(8) + 2
 *      34 = 32 + 2
 *
 *      144 = 4(34) + 8
 *      144 = 136 + 8
 *
 *
 * Optimal Approach Intuition:
 * - Maintain only previous two even fibonacci numbers.
 *
 *      num1 -> E(n - 2)
 *      num2 -> E(n - 1)
 *
 * - Compute current value using:
 *
 *      current = 4 * num2 + num1
 *
 * - Shift window after every iteration.
 *
 * - No need to store entire sequence.
 *
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(1)
 *
 *
 * Notes:
 * - This problem is a beautiful example of:
 *      Pattern Observation
 *      Mathematical Optimization
 *      State Transition
 *      Space Optimization
 *
 *
 * - Core Mathematical Formula:
 *
 *      E(n) = 4E(n - 1) + E(n - 2)
 *
 * - Instead of generating:
 *
 *      0 1 1 2 3 5 8 13 21 ...
 *
 * - We directly generate:
 *
 *      2 8 34 144 610 ...
 *
 *
 * - Sliding Window Technique:
 *
 *      num1 = previous even fibonacci
 *      num2 = current even fibonacci
 *
 * - After computing next:
 *
 *      num1 = num2
 *      num2 = current
 *
 *
 * - Why modulo is used?
 * - Fibonacci numbers grow exponentially.
 * - Integer overflow can occur for large n.
 *
 * - Standard modulo:
 *
 *      1_000_000_007
 *
 * - Used heavily in:
 *      Competitive Programming
 *      Dynamic Programming
 *      Number Theory Problems
 *
 *
 * - Important Edge Cases:
 * - n = 1
 * - n = 2
 * - Very large n
 *
 *
 * - Why is this optimal?
 * - We avoid generating unnecessary odd fibonacci numbers.
 * - We use only constant extra space.
 * - We directly generate required sequence.
 *
 *
 * - Interview Insight:
 * - This problem tests:
 *      Pattern Recognition
 *      Sequence Transformation
 *      Mathematical Thinking
 *      DP-style Optimization
 *
 * - Main transition:
 *
 *      "Can we derive recurrence only for useful terms?"
 *
 *
 * - Real-Life DP Insight:
 * - Many optimization problems become easier when:
 *      We identify hidden patterns
 *      Remove unnecessary states
 *      Build recurrence only on important states
 */
package recursion;

public class NthEvenFibonacciNumber {
    static int nthEvenFibonacci(int n) {

        if(n == 1) return 2;
        if(n == 2) return 8;

        int num1 = 2, num2 = 8;

        for(int i = 3; i <= n; i++){
            int curr = 4 * num2 + num1;
            num1 = num2;
            num2 = curr;
        }

        return num2;
    }
}
