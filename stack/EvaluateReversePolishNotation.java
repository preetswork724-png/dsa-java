/**
 * Problem:
 * <Evaluate Reverse Polish Notation>
 *
 * Link:
 * <https://leetcode.com/problems/evaluate-reverse-polish-notation/description/>
 *
 * Pattern:
 * <Stack>
 *
 * Brute Force Intuition:
 * - Store tokens in a list.
 * - Scan from left -> right.
 * - Whenever you see an operator, pop out two operands.
 * - Compute result.
 * - Remove 3 elements from the list and insert at that position.
 * - Restart scan.
 *
 * - Why it is inefficient?
 * - Repeated scan of the list from scratch.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach intuition:
 * - Scan from left -> right.
 * - Whenever you see an operator:
 * - Immediately evaluate it.
 * - Replace 3 elements in-place.
 * - Remove i.
 * - Remove i-1.
 * - Remove i-2.
 * - Move pointer slightly backward.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Why this is still not optimal?
 * - Shifting operation takes O(N) time.
 *
 * Optimal Approach (Used below):
 * - In reverse polish notation, operator lies after the operands.
 * - So exactly two operands lie before the operator.
 * - Perfect LIFO order.
 * - push(): When numbers.
 * - pop(): Pop out two operands when you see an operator. Evaluate them and push back the result.
 * - In the end, the top of the stack represents the result.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Use Deque for faster operation.
 */

package stack;
import java.util.Deque;
import java.util.ArrayDeque;

public class EvaluateReversePolishNotation {
    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"2","1","+","3","*"}));
    }

    public static int evalRPN(String[] tokens) {

        Deque<Integer> dq = new ArrayDeque<>();

        for (String token : tokens) {

            if (token.equals("+") || token.equals("-") ||
                    token.equals("*") || token.equals("/")) {

                int b = dq.pop();
                int a = dq.pop();

                if (token.equals("+")) {
                    dq.push(a + b);
                }
                else if (token.equals("-")) {
                    dq.push(a - b);
                }
                else if (token.equals("*")) {
                    dq.push(a * b);
                }
                else {
                    dq.push(a / b);  // truncates toward zero automatically in Java
                }
            }
            else {
                dq.push(Integer.parseInt(token));
            }
        }

        return dq.pop();
    }
}
