/**
 * Problem:
 * <Valid Parentheses String>
 *
 * Link:
 * <https://leetcode.com/problems/valid-parenthesis-string/description/>
 *
 * Pattern:
 * <Greedy>
 *
 * Brute Force Intuition:
 * - Memoization.
 * - Each asterisk could be '(', ')' or a "".
 * - We try out all the possibilities using backtracking.
 * - We explore all the three choices for each asterisk and return true if any of the possible combinations balances the string.
 * - But, the time complexity for the above approach is exponential i.e O(N * 3^K).
 * - Therefore, instead of exploring an already explored branch, we simply store the result / memoize it inside a memo table.
 * - Memo table returns the result for that branch in O(1).
 * - We maintain a memo table of size: indexes x balances.
 * - index -> current position in the string, balances -> number of unmatched '(' so far.
 * - Same (index, balance) can be reached again because of stars.
 *
 * - Why it is inefficient?
 * - It is inefficient for large strings.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N^2)
 *
 * Better Approach:
 * - Use two stacks.
 * - Push i of opening brackets in open stack.
 * - Push i of asterisk in star stack.
 * - When you encounter a ')':
 * - If open stack is non-empty, pop.
 * - Else if star stack is non-empty, pop (treating '*' as '(')
 * - Else return false.
 * - Because now a ')' has occurred before a '(' or a '*' which can never be balanced.
 * - Now if there are still opening or asterisk left, then compare indexes of both and return false ig invalid.
 * - At the end check, if there are opening brackets left.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why it is still not optimal?
 * - The above approach uses extra space.
 *
 * Optimal Approach (used below):
 * - Greedy.
 * - Instead of deciding what each is * (which causes exponential branching), we track the range of possible open parentheses counts at every step.
 * - Because '*' can be '(', ')' or empty.
 * - The number of '(' is not fixed - it lies between low (minimum possible open brackets) and high (maximum possible open brackets).
 * - When we see '(', both bounds increase.
 * - When we see ')', both bounds decrease.
 * - When we see '*', the minimum decreases (we treat as ')'), the maximum increases ( we treat as '(').
 * - If high < 0, then even in the best case which treating all '*' as '(', it is impossible to balance the string.
 * - If low < 0, low = 0 that means we can treat some '*' as "" instead of ')'.
 * - In the end, if low == 0, it means at least one assignment of '*' balances the string, so string is valid.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Remember to check, if low == 0.
 */

package stack;

public class ValidParenthesisString {
    public static void main(String[] args) {
        System.out.println(checkValidString("(*)"));
    }

    public static boolean checkValidString(String s) {
        int low = 0, high = 0;

        for(char ch : s.toCharArray()){

            if(ch == '('){
                low++;
                high++;
            }
            else if(ch == ')'){
                low--;
                high--;
            }
            else{
                low--;
                high++;
            }

            if(low < 0) low = 0;

            if(high < 0) return false;
        }
        return low == 0;
    }
}
