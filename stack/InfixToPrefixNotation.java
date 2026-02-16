/**
 * Problem:
 * <Infix To Prefix Notation>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/infix-to-prefix-notation/1>
 *
 * Pattern:
 * <Expression Parsing and Processing>
 *
 * Brute Force Intuition:
 * - For every operator in the string:
 * - Split the expression into left and right.
 * - Recursively convert the left subtree.
 * - Recursively convert the right subtree.
 * - Example: a*(b+c)/d
 * - Find the lowest precedence operator not inside parentheses.
 * - Split around it.
 * - Recurse on both the sides.
 * - Why are we trying to find the lowest precedence operator?
 * - Because recursion tries to find the root of the expression tree.
 * - The root is always the operator with the lowest precedence outside the parentheses.
 * - You must choose the operator that binds the least.
 * - That's the one that gets evaluated first.
 * - That becomes the root.
 *
 * - Why it is inefficient?
 * - For a correct split:
 * - You must repeatedly scan the entire string.
 * - Track parentheses balance.
 * - Track the lowest precedence operator.
 * - Handle right associativity of ^.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach:
 * - Reverse -> Swap '(' and ')' -> Convert to Postfix -> Reverse.
 * - This works due to symmetry of operator associativity.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why it is still not optimal?
 * - Multiple passes.
 *
 * Optimal Approach (used below):
 * - Infix -> Prefix Using Stack.
 * - Traverse from right -> left.
 * - Use Stack.
 * - Build prefix directly.
 * - How does scanning right → left with a stack reconstruct the same tree structure?
 * - When converting infix to postfix:
 * - We ensure:
 * - Higher precedence operators are applied first.
 * - Stack delays lower precedence operators.
 * - In postfix (left -> right):
 * - We want left subtree first.
 * - So operands appear before operators.
 * - In prefix:
 * - We want operators before operators.
 * - Right-to-left traversal naturally gives:
 * - Right subtree first.
 * - Then Left subtree.
 * - Then reversing fixes order.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Why precedence condition changes?
 * - Because associativity direction flips when you reverse traversal.
 * - Why final reverse?
 * - Because we built expression in reverse order.
 * - While traversing right -> left.
 * - We append operators and operands in reverse pre-order.
 * - Final reverse restores the correct prefix order.
 */

package stack;
import java.util.Stack;
public class InfixToPrefixNotation {

    public static void main(String[] args) {
        System.out.println(infixToPrefix("a*(b+c)/d"));
    }

    public static String infixToPrefix(String s) {

        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        int n = s.length();

        for(int i = n - 1; i >= 0; i--){

            char ch = s.charAt(i);

            if(Character.isLetterOrDigit(ch)){
                sb.append(ch);
            }

            else if(ch == ')'){
                stack.push(ch);
            }

            else if(ch == '('){
                while(!stack.isEmpty() && stack.peek() != ')'){
                    sb.append(stack.pop());
                }
                stack.pop();
            }

            else{ // operator

                while(!stack.isEmpty() &&
                        stack.peek() != ')' &&
                        (
                                precedence(stack.peek()) > precedence(ch)
                                        ||
                                        (precedence(stack.peek()) == precedence(ch) && ch == '^')
                        )
                ){
                    sb.append(stack.pop());
                }

                stack.push(ch);
            }
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }

    private static int precedence(char ch){
        if(ch == '+' || ch == '-') return 1;
        if(ch == '*' || ch == '/') return 2;
        if(ch == '^') return 3;
        return -1;
    }
}
