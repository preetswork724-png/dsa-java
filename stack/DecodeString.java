/**
 * Problem:
 * <Decode String>
 *
 * Link:
 * <https://leetcode.com/problems/decode-string/>
 *
 * Pattern:
 * <Stack + String Parsing>
 *
 *
 * Brute Force Intuition:
 * - Try to recursively decode substrings whenever '[' is found.
 * - For each '[':
 *   - Find corresponding ']'
 *   - Extract substring inside
 *   - Repeat it k times
 *
 * - Why it is inefficient?
 *   - Finding matching brackets repeatedly is costly.
 *   - String concatenation is expensive.
 *   - Deep nesting leads to repeated work.
 *
 * Time Complexity:
 * - O(N * K)  (due to repeated string building)
 *
 * Space Complexity:
 * - O(N) recursion stack
 *
 *
 * Better Approach Intuition (Stack Simulation):
 * - Use a stack to simulate decoding process.
 *
 * - Key Idea:
 *   - Push characters into stack.
 *   - When ']' is encountered:
 *       1. Extract substring inside brackets.
 *       2. Extract number before '['.
 *       3. Repeat substring k times.
 *       4. Push result back to stack.
 *
 * - Why it is better?
 *   - Processes string in one pass.
 *   - Avoids repeated scanning.
 *   - Handles nested patterns naturally.
 *
 * Time Complexity:
 * - O(N * K)
 *
 * Space Complexity:
 * - O(N * K)
 *
 * Optimal Approach:
 * - Stack-based solution is already optimal.
 * - No better asymptotic solution exists due to output size dependency.
 *
 *
 * Key Insight:
 * - Every closing bracket ']' triggers:
 *     "Decode current segment"
 *
 * - Pattern:
 *     k[encoded_string]
 *   → repeat encoded_string k times
 *
 * - Must handle:
 *   - Nested decoding
 *   - Multi-digit numbers
 *
 *
 * Notes:
 * - Numbers can have multiple digits:
 *     "12[a]" → repeat 12 times
 *
 * - Always:
 *     - Extract string first
 *     - Then extract number
 *
 * - Stack stores:
 *     - Partial strings
 *     - Digits (as characters)
 *
 * - StringBuilder is preferred for efficiency.
 *
 *
 * Common Mistakes:
 * - Not handling multi-digit numbers ❌
 * - Treating digits as characters only ❌
 * - Incorrect bracket matching ❌
 * - Using == instead of .equals() for strings ❌
 * - Forgetting to reverse substring while popping ❌
 *
 *
 * Mental Model:
 * - Think of it like "unwrapping layers":
 *
 *   Example:
 *   "3[a2[c]]"
 *
 *   Step 1: 2[c] → cc
 *   Step 2: a + cc → acc
 *   Step 3: 3[acc] → accaccacc
 *
 *
 * Edge cases:
 * - Empty string → return ""
 * - Single character → return itself
 * - No brackets → return original string
 * - Large repeat values → watch memory usage
 * - Nested patterns → must handle correctly
 *
 * Key takeaway:
 * - Recognize pattern: k[...]
 * - Stack helps resolve innermost expression first.
 * - Parsing numbers correctly is the MOST critical part.
 */

package stack;

import java.util.Stack;

public class DecodeString {
    public String decodeString(String s) {
        Stack<String> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            if (ch == ']') {

                StringBuilder sb = new StringBuilder();

                while (!stack.isEmpty() && !stack.peek().equals("[")) {
                    sb.insert(0, stack.pop());
                }

                stack.pop();

                StringBuilder num = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peek().charAt(0))) {
                    num.insert(0, stack.pop());
                }

                int k = Integer.parseInt(num.toString());

                String repeated = sb.toString().repeat(k);

                stack.push(repeated);
            } else {
                stack.push(ch + "");
            }
        }
        StringBuilder res = new StringBuilder();

        for (String str : stack) res.append(str);

        return res.toString();
    }
}
