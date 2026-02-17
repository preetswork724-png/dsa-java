/**
 * Problem:
 * <Postfix to Prefix Conversion>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/postfix-to-prefix-conversion/1>
 *
 * Pattern:
 * <Expression Parsing and Processing>
 *
 * Brute Force Intuition:
 * - Iterate left -> right.
 * - Build an expression tree from the characters in the postfix expression.
 * - Perform a pre-order traversal on the tree to get the prefix expression.
 * - For each char in postfix expression:
 * - If it is a letter or digit:
 * - Then push it as a node in the stack.
 * - Else:
 * - Pop out two nodes.
 * - Rearrange them as operator operand1 operand2.
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
 * - Iterate left -> right.
 * - Compute fragments of prefix and push it inside the stack.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Rearrange the expression as: operator + operand1 + operand2.
 */

package stack;
import java.util.Stack;
public class PostfixToPrefixConversion {

    public static void main(String[] args) {
        System.out.println(postToPre("ABC/-AK/L-*"));
    }

    public static String postToPre(String post_exp) {

        Stack<String> stack = new Stack<>();

        for(char ch : post_exp.toCharArray()){

            if(Character.isLetterOrDigit(ch)){
                stack.push(ch + "");
            }
            else{
                String operand2 = stack.pop(), operand1 = stack.pop();

                stack.push(ch + operand1 + operand2);
            }
        }
        return stack.pop();
    }
}
