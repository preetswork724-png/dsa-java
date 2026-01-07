/**
 * Problem:
 * <Valid Parentheses>
 *
 * Link:
 * <https://leetcode.com/problems/valid-parentheses/description/>
 *
 * Pattern:
 * <Stack>
 *
 * Brute Force Intuition:
 * - Scan the string repeatedly and remove the valid bracket pair, until you get stuck.
 * - The reason to get stuck might be :
 * - Every valid bracket pair is removed or there is no valid pair left.
 * - Simulate the process.
 * - Take a StringBuilder to store the intermediate strings.
 * - Maintain a boolean variable to see if the state is changed or not.
 * - Reassign the string to the intermediate string.
 * - Start scanning from the start.
 * - If you are stuck, the boolean variable will detect that and break out of the loop.
 *
 * - Why it is inefficient?
 * - It is inefficient as the string is scanned from the start everytime.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Better and Optimal Approach ( Used Below ):
 * - Push the opening brackets above each other.
 * - Whenever you get a closing bracket, check the most recent opening bracket.
 * - If they are a valid pair, pop.
 * - If they are not then return false.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why the better approach is the optimal one ?
 * - We must track the unsolved opening bracket, and stack does this in O(1) per operation and O(N) time and O(N) space.
 * - This is the best theoretical best possible since we must read every character and may need to store N opens.
 *
 * Notes:
 * - At the end, if the stack is not empty then :
 * - There might be some unclosed brackets left.
 * - If empty :
 * - Every opening bracket found a closing bracket.
 */

package stack;
import java.util.Stack;
public class ValidParentheses {

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));
    }

    public static boolean isValid(String s) {

        if(s.length() == 1) return false;

        Stack<Character> stack = new Stack<>();

        for(char ch : s.toCharArray()){

            if(ch == '(' || ch == '[' || ch == '{'){
                stack.push(ch);
            }
            else{

                if(stack.isEmpty()) return false;

                if(ch == ')'){

                    if(stack.peek() == '('){
                        stack.pop();
                    }
                    else{
                        return false;
                    }
                }

                if(ch == ']'){

                    if(stack.peek() == '['){
                        stack.pop();
                    }
                    else{
                        return false;
                    }
                }

                if(ch == '}'){

                    if(stack.peek() == '{'){
                        stack.pop();
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}
