/**
 * Problem:
 * <Basic Calculator>
 *
 * Link:
 * <https://leetcode.com/problems/basic-calculator/description/>
 *
 * Pattern:
 * <Stack + Math>
 *
 * Brute Force Intuition:
 * - Infix -> Postfix -> Evaluate
 * - Stack + StringBuilder
 * - Build numbers using StringBuilder.
 * - Push the operators and '(' in the stack.
 * - If ch == digit :
 * - Build numbers when you see a digit using a StringBuilder.
 * - If ch == '( :
 * - Push in the stack.
 * - If ch == ')' :
 * - Pop the operators and append it to the string until the stack is empty or the there isn't a '(' at the top.
 * - This converts the string to postfix.
 * - If ch == '+' || ch == '-' :
 * - Pop the characters to the StringBuilder until the stack becomes empty.
 * - This is done to maintain operator precedence.
 * - To handle unary minus:
 * - Skip all the spaces till an '(', '+', '-' or out of bounds directly.
 * - Append 0 to the StringBuilder to convert the unary minus to binary subtraction.
 *
 * - Why it is inefficient?
 * - Multiple passes over String.
 * - Repeated parsing.
 * - Extra String concatenations.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach:
 * - Infix -> evaluate on the fly.
 * - Evaluate using 2 Stacks.
 * - Instead of converting to postFix string, we:
 * - Maintain stack of 2 numbers.
 * - Maintain stack of 2 operators.
 * - Process expression on the fly.
 * - When we see:
 * - Number -> Split it.
 * - Operator -> process previous operator.
 * - ( -> push operator.
 * - ) -> evaluate till (.
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
 * - Math.
 * - There is:
 * - No *.
 * - No /.
 * - Only + and -.
 * - Which means:
 * - No operator precedence.
 * - Parentheses only change sign context.
 * - So, we don't need operator stack.
 * - Keep result, sign and number.
 * - If ch == '+' :
 * - sign = 1.
 * - If ch == '-' :
 * - sign = -1.
 * - If ch == '(' :
 * - Push the current result and sign as in to store it, sign =  and make the result = 0 because you have already stored the result in the stack.
 * - If ch == ')' :
 * - Update the current result by using sign and the previous result, number = 0.
 * - If ch == digit :
 * - Build number.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Remember to typecast the result back to int before returning.
 * - Use Deque as it is faster.
 */

package stack;
import java.util.Deque;
import java.util.ArrayDeque;
public class BasicCalculator {

    public static void main(String[] args) {
        System.out.println(calculate("1 + 1"));
    }

    public static int calculate(String s) {
        Deque<Long> dq = new ArrayDeque<>();
        long result = 0, number = 0;
        int sign = 1, n = s.length();

        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);

            if(Character.isDigit(ch)){
                number = number * 10 + (ch - '0');
            }
            else if(ch == '+'){
                result += number * sign;
                number = 0;
                sign = 1;
            }
            else if(ch == '-'){
                result += number * sign;
                number = 0;
                sign = -1;
            }
            else if(ch == '('){
                dq.push(result);
                dq.push((long)sign);
                result = 0;
                sign = 1;
            }
            else if(ch == ')'){
                result += number * sign;
                number = 0;

                result *= dq.pop();
                result += dq.pop();
            }
        }
        result += number * sign;
        return (int)result;
    }
}
