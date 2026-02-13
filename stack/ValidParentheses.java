/**
 * Problem:
 * <Valid Parentheses>
 *
 * Link:
 * <https://leetcode.com/problems/valid-parentheses/description/>
 *
 * Pattern:
 * <Stack>
 *
 * Brute Force Intuition:
 * - Scan the string repeatedly and remove the valid bracket pair, until you get stuck.
 * - The reason to get stuck might be :
 * - Every valid bracket pair is removed or there is no valid pair left.
 * - Simulate the process.
 * - Take a StringBuilder to store the intermediate strings.
 * - Maintain a boolean variable to see if the state is changed or not.
 * - Reassign the string to the intermediate string.
 * - Start scanning from the start.
 * - If you are stuck, the boolean variable will detect that and break out of the loop.
 *
 * - Why it is inefficient?
 * - It is inefficient as the string is scanned from the start everytime.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach:
 * - Push the opening brackets above each other.
 * - Whenever you get a closing bracket, check the most recent opening bracket.
 * - If they are a valid pair, pop.
 * - If they are not then return false.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why it is still not optimal?
 * - The above approach is already optimal but as Stacks are synchronized they are slow.
 *
 * Optimal Approach (used below):
 * Use StringBuilder as Stack.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - At the end, if the stack is not empty then :
 * - There might be some unclosed brackets left.
 * - If empty :
 * - Every opening bracket found a closing bracket.
 */

package stack;
import java.util.Stack;
public class ValidParentheses {

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));
    }

    public static boolean isValid(String s) {

        if (s.length() == 1) return false;

        StringBuilder stack = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.append(ch);
            } else {

                int len = stack.length();

                if (stack.isEmpty()) return false;

                if (ch == ')') {

                    if (stack.charAt(len - 1) == '(') {
                        stack.deleteCharAt(len - 1);
                    } else {
                        return false;
                    }
                }

                if (ch == ']') {

                    if (stack.charAt(len - 1) == '[') {
                        stack.deleteCharAt(len - 1);
                    } else {
                        return false;
                    }
                }

                if (ch == '}') {

                    if (stack.charAt(len - 1) == '{') {
                        stack.deleteCharAt(len - 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}
