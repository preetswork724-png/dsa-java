/**
 * Problem:
 * <Basic Calculator II>
 *
 * Link:
 * <https://leetcode.com/problems/basic-calculator-ii/description/>
 *
 * Pattern:
 * <Expression Evaluation>
 *
 * Brute Force Intuition:
 * - Recursively split the expression for every operator and compute all possible results.
 *
 * - Why it is inefficient?
 * - Solves repeated overlapping sub-problems.
 *
 * Time Complexity:
 * - O(2^N)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach:
 * - Infix -> Postfix -> Evaluate the result.
 * - Use operator stack to build the postfix string.
 * - Evaluate the result using stack.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why it is still not optimal?
 * - Multiple passes.
 * - Uses two stacks.
 *
 * Optimal Approach (used below):
 * - There are only 2 precedence levels.
 * - There are no parentheses.
 * - Left to right traversal.
 * - Evaluate * and / immediately, delay + and -.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Add the lastNum value to the result at the end because the updated lastNum was never included in the result.
 */

package stack;

public class BasicCalculatorII {

    public static void main(String[] args) {
        System.out.println(calculate("3+2*2"));
    }

    public static int calculate(String s) {
        int n = s.length(), num  = 0, lastNum = 0, result = 0;
        char sign = '+';

        for(int i = 0; i < n; i++){

            char ch = s.charAt(i);

            if(Character.isDigit(ch)){
                num = num * 10 + (ch - '0');
            }

            if((!Character.isDigit(ch) && ch != ' ') || i == n-1){

                if(sign == '+'){
                    result += lastNum;
                    lastNum = num;
                }
                else if(sign == '-'){
                    result += lastNum;
                    lastNum = -num;
                }
                else if(sign == '*'){
                    lastNum = lastNum * num;
                }
                else if(sign == '/'){
                    lastNum = lastNum / num;
                }
                sign = ch;
                num = 0;
            }
        }
        result += lastNum;
        return result;
    }
}
