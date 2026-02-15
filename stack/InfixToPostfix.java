/**
 * Problem:
 * <Infix to Postfix>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/infix-to-postfix-1587115620/1>
 *
 * Pattern:
 * <Shunting yard>
 *
 * Brute Force Intuition:
 * - Recursively resolve parentheses.
 * - Inside each segment, find the operator with the highest precedence.
 * - Convert that sub-expression first.
 * - Replace it.
 * - Repeat the process until the string is completely converted.
 * - For example :
 * - A+B*C
 * - Scan full expression.
 * - Find the highest precedence operator(*).
 * - Convert B*C -> BC*
 * - Replace in the original string A+BC*.
 * - Now process, A+(BC*).
 * - Convert ABC*+.
 * - For a given expression:
 * - Step 1 : If parentheses exist.
 * - Find innermost (...).
 * - Recursively convert inside.
 * - Replace in original string.
 * - Step 2 : If no parentheses:
 * - Look for ^ first.
 * - Then look for * and /.
 * - Then + and -.
 * - Each time:
 * - Identify left operand.
 * - Identify right operand.
 * - Convert to postfix.
 * - Replace substring.
 * - Repeat until one postfix remains.
 *
 * - Why it is inefficient?
 * - Repeated scanning of the string for each operator.
 * - Repeated substring creation.
 * - Recursion for parentheses.
 * - Heavy String replacement.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach:
 * - Instead of rescanning the string again for parentheses and operator.
 * - We build postfix fragments using:
 * - Stack<Character> -> operands.
 * - Stack<String> -> postfix fragments.
 * - When an operator is ready to be applied:
 * - operand1 + operand2 + operator
 * - And push back into operand stack.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why it is still not optimal?
 * - Uses two Stack.
 * - Memory heavy.
 *
 * Optimal Approach (used below):
 * - Uses the Shunting yard algorithm.
 * - Maintain only one stack for operators.
 * - Use a StringBuilder to directly build the postfix output.
 * - Operators are delayed in the stack until it is safe to append them (based on precedence and associativity).
 * - Parentheses act as boundaries that control where operators are popped.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - The algorithm does not repeatedly modify the string.
 * - It dynamically enforces operator precedence and associativity during a single left-to-right scan.
 */

package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class InfixToPostfix {

    public static void main(String[] args) {
        System.out.println(infixToPostfix("a*(b+c)/d"));
    }

    public static String infixToPostfix(String s) {
        Map<Character, Integer> precedence = new HashMap<>();
        precedence.put('+', 1);
        precedence.put('-', 1);
        precedence.put('*', 2);
        precedence.put('/', 2);
        precedence.put('^', 3);


        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int n = s.length();

        for(int i = 0; i < n ; i++){
            char ch = s.charAt(i);

            if(Character.isLetterOrDigit(ch)){

                while(i < n && Character.isLetterOrDigit(s.charAt(i))){
                    sb.append(s.charAt(i));
                    i++;
                }
                i--;
            }
            else if(ch == '('){
                stack.push(ch);
            }
            else if(ch == ')'){

                while(!stack.isEmpty() && stack.peek() != '('){
                    sb.append(stack.pop());
                }
                stack.pop();
            }
            else{
                while(!stack.isEmpty() && stack.peek() != '(' &&
                        (precedence.get(stack.peek()) >= precedence.get(ch) && ch != '^')){
                    sb.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
