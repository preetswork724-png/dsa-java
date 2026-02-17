/**
 * Problem:
 * <Prefix to Infix Conversion>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/prefix-to-infix-conversion/1>
 *
 * Pattern:
 * <Expression Parsing and Processing>
 *
 * Brute Force Intuition:
 * - Iterate from right -> left.
 * - Build an expression tree from the characters in the prefix expression.
 * - Perform an in-order traversal on the tree to get the infix expression.
 * - For each char in prefix expression:
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
 * - Iterate from right -> left.
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
public class PrefixToInfixConversion {

    public static void main(String[] args) {
        System.out.println(preToInfix("*-A/BC-/AKL"));
    }

    public static String preToInfix(String pre_exp) {
        Stack<String> stack = new Stack<>();
        int n = pre_exp.length();

        for(int i = n-1; i >= 0; i--){
            char ch = pre_exp.charAt(i);

            if(Character.isLetterOrDigit(ch)){
                stack.push(ch + "");
            }
            else{
                String operand1 = stack.pop(), operand2 = stack.pop();
                stack.push("(" + operand1 + ch + operand2 + ")");
            }
        }
        return stack.pop();
    }
}
