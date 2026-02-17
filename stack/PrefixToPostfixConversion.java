/**
 * Problem:
 * <Prefix to Postfix Conversion>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/prefix-to-postfix-conversion/1>
 *
 * Pattern:
 * <Expression Parsing and Processing>
 *
 * Brute Force Intuition:
 * - Iterate from right -> left.
 * - Build an expression tree from the characters in the prefix expression.
 * - Perform a post-order traversal on the tree to get the postfix expression.
 * - For each char in prefix expression:
 * - If it is a letter or digit:
 * - Then push it as a node in the stack.
 * - Else:
 * - Pop out two nodes.
 * - Rearrange them as operand1 operand2 operator
 *
 * - Why it is inefficient?
 * - You don't need to create the entire tree structure.
 * - Multiple passes.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better and Optimal Approach (used below):
 * - Iterate from right -> left.
 * - Compute fragments of postfix and push it in the stack.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Why traverse from right to left??
 * - Because in prefix the order is: operator operand1 operand2.
 * - The operator comes before the operands.
 * - The operator does not know about its operands.
 * - Therefore, we need to scan from right -> left.
 */
package stack;

import java.util.Stack;

public class PrefixToPostfixConversion {
    public static void main(String[] args) {
        System.out.println(preToPost("*-A/BC-/AKL"));
    }

    public static String preToPost(String pre_exp) {
        Stack<String> stack = new Stack<>();
        int n = pre_exp.length();

        for(int i = n-1; i >= 0; i--){
            char ch = pre_exp.charAt(i);

            if(Character.isLetterOrDigit(ch)){
                stack.push(ch + "");
            }
            else{
                String left = stack.pop(), right = stack.pop();

                stack.push(left + right + ch);
            }
        }
        return stack.pop();
    }
}
