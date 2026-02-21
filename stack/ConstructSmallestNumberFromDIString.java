/**
 * Problem:
 * <Construct Smallest Number From DI String>
 *
 * Link:
 * <https://leetcode.com/problems/construct-smallest-number-from-di-string/description/>
 *
 * Pattern:
 * <Stack>
 *
 * Brute Force Intuition:
 * - Generate all the permutations of digits 1-9.
 * - Take only permutations of length n+1.
 * - Check if each permutation satisfies the pattern.
 * - Return the smallest valid one.
 *
 * - Why it is inefficient?
 * - Generating all the permutations is very slow for the array.
 *
 * Time Complexity:
 * - O(9! * N)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach:
 * - Backtracking and pruning.
 * - Instead of generating all the permutations blindly:
 * - We build the pattern digit by digit and check the pattern condition immediately.
 * - While constructing the number:
 * - At position pos, check only pattern[pos-1].
 * - If condition fails, stop exploring that branch.
 *
 * Time Complexity:
 * - O(9!)
 * Space Complexity:
 * - O(N)
 *
 * Why this is not optimal?
 * - Still generates the permutations.
 *
 * Optimal Approach (used below):
 * - Use stack.
 * - The intuition is whenever you encounter an 'I' or reach the end of the pattern, reverse the block and append it.
 * - If we see the numbers 1, 2, 3, 4... and whenever we see 'i', we flush the stack.
 * - This automatically creates decreasing blocks.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Always push the current i + 1 in the stack.
 */

package stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class ConstructSmallestNumberFromDIString {
    public static void main(String[] args) {
        System.out.println(smallestNumber("IIIDIDDD"));
    }

    public static String smallestNumber(String pattern) {
        Deque<Integer> dq = new ArrayDeque<>();
        StringBuilder res = new StringBuilder();
        int n = pattern.length();

        for(int i = 0; i <= pattern.length(); i++){
            dq.push(i + 1);

            if(i == pattern.length() || pattern.charAt(i) == 'I'){

                while(!dq.isEmpty()){
                    res.append(dq.pop());
                }
            }
        }
        return res.toString();
    }
}
