/**
 * Problem:
 * <Postfix to Infix Conversion>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/postfix-to-infix-conversion/1>
 *
 * Pattern:
 * <Expression Parsing and Processing>
 *
 * Brute Force Intuition:
 * - Iterate from left -> right.
 * - Build an expression tree from the characters in the postfix expression.
 * - Perform an in-order traversal on the tree to get the postfix expression.
 * - For each char in postfix expression:
 * - If it is a letter or digit:
 * - Then push it as a node in the stack.
 * - Else:
 * - Pop out two nodes.
 * - Rearrange them as operand1 operator operand2.
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
 * - Iterate from left -> right.
 * - Compute fragments of infix and push it in the stack.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Append opening and closing brackets when pushing inside the stack.
 */
package stack;

import java.util.Stack;

public class PostfixToInfixConversion {
    public static void main(String[] args) {
        System.out.println(postToInfix("ab*c+"));
    }

    public static String postToInfix(String exp) {
        Stack<String> stack = new Stack<>();

        for(char ch : exp.toCharArray()){

            if(Character.isLetterOrDigit(ch)){
                stack.push(ch + "");
            }
            else{
                String operand2 = stack.pop(), operand1 = stack.pop();
                stack.push("(" + operand1 + ch + operand2 + ")");
            }
        }
        return stack.pop();
    }
}
