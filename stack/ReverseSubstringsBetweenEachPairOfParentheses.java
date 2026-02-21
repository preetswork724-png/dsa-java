/**
 * Problem:
 * <Reverse Substrings Between Each Pair of Parentheses>
 *
 * Link:
 * <https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/description/>
 *
 * Pattern:
 * <Stack>
 *
 * Brute Force Intuition:
 * - Everytime you encounter a ')':
 * - Scan backward to find matching '('.
 * - Extract substring.
 * - Reverse it.
 * - Rebuild the entire string.
 *
 * - Why it is inefficient?
 * - Every reversal rebuilds the string.
 * - Rebuilding costs O(N).
 * - Could happen O(N) times.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach:
 * - Instead of rebuilding the entire string everytime we:
 * - Maintain Stack<StringBuilder>.
 * - Push new builder when '(' appears.
 * - When ')' appears:
 * - Reverse the current builder.
 * - Append it to the previous builder.
 *
 * Time Complexity:
 * - O(N^2) {Each character appended once, reversed once but StringBuilder.reverse() is O(K). If nested deeply, some characters may participate in multiple reversals.}
 * Space Complexity:
 * - O(N)
 *
 * Why this is not optimal?
 * - Reverse operation costs linear time.
 * - In deeply nested parentheses, reversals might stack up.
 *
 * Optimal Approach (used below):
 * - Use a Stack of Characters.
 * - If ch != ')':
 * - Push in the stack.
 * - Else:
 * - Pop out and append all the characters to a StringBuilder until '('.
 * - Now, push the characters of the StringBuilder back in the stack.
 * - Pop '('.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Reverse the entire string before returning.
 */

package stack;

import java.util.Deque;
import java.util.ArrayDeque;

public class ReverseSubstringsBetweenEachPairOfParentheses {

    public static void main(String[] args) {
        System.out.println(reverseParentheses("(abcd)"));
    }

    public static String reverseParentheses(String s) {
        Deque<Character> dq = new ArrayDeque<>();
        StringBuilder rev = new StringBuilder();

        for(char ch : s.toCharArray()){

            if(ch == ')'){

                rev.setLength(0);

                while(!dq.isEmpty() && dq.peek() != '('){
                    rev.append(dq.pop());
                }
                dq.pop();

                for(int i = 0; i < rev.length(); i++) dq.push(rev.charAt(i));
            }
            else{
                dq.push(ch);
            }
        }
        rev.setLength(0);

        while(!dq.isEmpty()) rev.append(dq.pop());
        return rev.reverse().toString();
    }
}
