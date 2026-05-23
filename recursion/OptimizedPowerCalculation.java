/**
 * Problem:
 * <Optimized Power Calculation / Binary Exponentiation>
 *
 * Problem Statement:
 * - Given two integers x and y,
 *   calculate:
 *
 *          x^y
 *
 * - Implement an optimized recursive solution.
 *
 * - The solution should avoid multiplying x repeatedly y times.
 *
 * Example:
 * Input:
 *      x = 2
 *      y = 5
 *
 * Output:
 *      32
 *
 * Explanation:
 *      2^5 = 32
 *
 *
 * Pattern:
 * <Recursion + Divide and Conquer>
 *
 * Brute Force Intuition:
 * - Multiply x repeatedly y times.
 *
 * Example:
 *      power(2, 5)
 *      = 2 * 2 * 2 * 2 * 2
 *
 * - Why is it inefficient?
 * - We perform y multiplications.
 * - For very large exponents, this becomes slow.
 *
 * Time Complexity:
 * - O(Y)
 *
 * Space Complexity:
 * - O(1)
 *
 *
 * Better / Optimized Approach Intuition:
 * - Observe the mathematical property:
 *
 *      If y is EVEN:
 *
 *          x^y = (x^(y/2))^2
 *
 *      If y is ODD:
 *
 *          x^y = x * (x^(y/2))^2
 *
 * - Instead of reducing exponent by 1,
 *   reduce it by half every time.
 *
 * - Recursively calculate:
 *
 *      halfPower = power(x, y / 2)
 *
 * - Reuse the same computed value.
 *
 * - Why is it efficient?
 * - Problem size reduces exponentially.
 * - Exponent becomes:
 *
 *      y → y/2 → y/4 → y/8 ...
 *
 *
 * Time Complexity:
 * - O(log Y)
 *
 * Space Complexity:
 * - O(log Y)
 * - Recursive stack depth.
 *
 *
 * Notes:
 * - This technique is called:
 *      Binary Exponentiation
 *      Fast Power
 *      Exponentiation by Squaring
 *
 * - Core Mathematical Observation:
 *
 *      EVEN exponent:
 *          x^y = (x^(y/2))^2
 *
 *      ODD exponent:
 *          x^y = x * (x^(y/2))^2
 *
 * - Important Optimization:
 * - Never write:
 *
 *      power(x, y/2) * power(x, y/2)
 *
 * - Why?
 * - It recomputes the same recursive call twice.
 *
 * - Instead:
 *
 *      long halfPower = power(x, y/2);
 *
 * - Reuse the computed answer.
 *
 *
 * - Recursive Reduction:
 *
 *      power(x, 16)
 *          ↓
 *      power(x, 8)
 *          ↓
 *      power(x, 4)
 *          ↓
 *      power(x, 2)
 *          ↓
 *      power(x, 1)
 *          ↓
 *      power(x, 0)
 *
 * - Height of recursion tree becomes logarithmic.
 *
 *
 * - Base Case:
 *
 *      y == 0
 *
 * - Why return 1?
 *
 *      x^0 = 1
 *
 *
 * - Important Edge Cases:
 * - x = 0
 * - y = 0
 * - Very large exponents
 * - Integer overflow
 *
 *
 * - Why is recursive stack O(log Y)?
 * - Because exponent keeps halving.
 * - Number of recursive calls becomes logarithmic.
 *
 *
 * - Real-Life Applications:
 * - Modular Arithmetic
 * - Cryptography
 * - RSA Encryption
 * - Matrix Exponentiation
 * - Competitive Programming
 * - Fast Numerical Computations
 *
 *
 * - Interview Insight:
 * - This problem tests:
 *      Divide and Conquer Thinking
 *      Mathematical Optimization
 *      Recursive Reasoning
 *      Time Complexity Reduction
 *
 * - Main transition:
 *
 *      "Can we reduce the problem size faster than subtracting 1?"
 */

package recursion;

public class OptimizedPowerCalculation {

    public long power(int x, int y){

        if(y == 1) return 1;

        long halfPower = power(x, y / 2);

        if(y % 2 == 0){
            return halfPower * halfPower;
        }
        else{
            return x * halfPower * halfPower;
        }
    }
}
