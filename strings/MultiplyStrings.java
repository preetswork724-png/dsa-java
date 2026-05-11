/**
 * Problem:
 * <Multiply Strings>
 *
 * Link:
 * <https://leetcode.com/problems/multiply-strings/>
 *
 * Pattern:
 * <String Manipulation / Simulation>
 *
 * Brute Force Intuition:
 * - Convert both strings into numeric types (int / long).
 * - Multiply directly.
 * - Convert result back to string.
 *
 * - Why it is inefficient / incorrect?
 *   - Fails for large inputs due to overflow.
 *   - Violates constraint: cannot use built-in big integers.
 *   - Strings can be arbitrarily large (beyond long range).
 *
 * Time Complexity:
 * - O(N + M)
 *
 * Space Complexity:
 * - O(1)
 *
 *
 * Better Approach Intuition (Simulation - Manual Multiplication):
 * - Mimic how multiplication is done on paper.
 *
 * - Key Idea:
 *   - Multiply each digit of num1 with each digit of num2.
 *   - Store result in an array of size (n + m).
 *
 * - Why size (n + m)?
 *   - Maximum digits in result = sum of lengths.
 *
 * - Process:
 *   - Traverse from right to left (least significant digit).
 *   - Multiply digits:
 *       product = digit1 * digit2
 *
 *   - Add previous value at position:
 *       sum = product + result[i + j + 1]
 *
 *   - Store:
 *       result[i + j + 1] = sum % 10
 *       result[i + j] += sum / 10
 *
 * - Why it is better?
 *   - Handles very large numbers.
 *   - No overflow issues.
 *   - Uses only digit-level operations.
 *
 * Time Complexity:
 * - O(N * M)
 *
 * Space Complexity:
 * - O(N + M)
 *
 * Optimal Approach:
 * - Same as above (simulation is already optimal).
 * - No further asymptotic improvement possible.
 *
 *
 * Key Insight:
 * - Treat numbers as arrays of digits instead of integers.
 * - Position mapping:
 *     i * j → contributes to (i + j) and (i + j + 1)
 *
 * - This positional mapping is the MOST important trick.
 *
 *
 * Notes:
 * - Always iterate from right → left (LSD to MSD).
 * - Carry handling is crucial:
 *     - Ones place stays
 *     - Tens place moves left
 *
 * - Avoid:
 *     - Parsing to int/long ❌
 *     - Using BigInteger (not allowed in interviews) ❌
 *
 * - StringBuilder is preferred for result construction.
 *
 *
 * Common Mistakes:
 * - Converting string to number → overflow ❌
 * - Incorrect index mapping (i + j vs i + j + 1) ❌
 * - Not handling carry properly ❌
 * - Forgetting to skip leading zeros ❌
 * - Returning empty string instead of "0" ❌
 *
 *
 * Mental Model:
 * - Think of multiplication as:
 *     "Each digit contributes to a shifted position"
 *
 * - Exactly like:
 *       123
 *   ×   45
 *   ----------
 *       615   (123 * 5)
 *  +   4920   (123 * 4 shifted)
 *   ----------
 *       5535
 *
 *
 * Edge cases:
 * - num1 = "0" or num2 = "0" → return "0"
 * - Very large inputs → must not overflow
 * - Leading zeros in result → trim them
 * - Single digit inputs → still works correctly
 *
 * Key takeaway:
 * - Whenever numbers are given as strings → think digit simulation.
 * - Index mapping (i + j, i + j + 1) is the core trick.
 * - This is a classic "simulate math" problem.
 */

package strings;

public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        int n = num1.length(), m = num2.length();

        int[] res = new int[n + m];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int digit1 = num1.charAt(i) - '0';
                int digit2 = num2.charAt(j) - '0';

                int product = digit1 * digit2;
                int sum = product + res[i + j + 1];

                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }
        StringBuilder ans = new StringBuilder();

        for (int num : res) {
            if (!(ans.length() == 0 && num == 0)) {
                ans.append(num);
            }
        }
        return ans.length() == 0 ? "0" : ans.toString();
    }
}
