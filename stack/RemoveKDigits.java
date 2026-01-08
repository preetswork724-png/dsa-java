/**
 * Problem:
 * <Remove K Digits>
 *
 * Link:
 * <https://leetcode.com/problems/remove-k-digits/description/>
 *
 * Pattern:
 * <Stack>
 *
 * Brute Force Intuition:
 * - Repeat the following k times :
 * - Scan the number from left to right.
 * - Find the first index i such that : nums[i] < nums[i+1]
 * - Delete nums[i].
 * - Restart scanning.
 * - If no such index exists iin a scan :
 * - The number is already non-decreasing.
 * - Delete the last digit.
 * - After all deletions, remove leading zeroes.
 * - If empty, return "0".
 *
 * - Why it is inefficient?
 * - Much slower.
 * - Redundant comparisons.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used Below):
 * - To make the number as small as possible, we want smaller digits as early as possible.
 * - If a digit is larger than a digit coming after it, keeping it hurts the final number.
 * - So, whenever a smaller digit appears, we should remove bigger digits before it (if we still can).
 * - This naturally leads to a monotonic increasing stack.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why the better approach here is the optimal one?
 * - Use a StringBuilder as a stack to store digits.
 * - Traverse the digits of num from left to right.
 * - For each digit ch:
 * - While:
 * - the stack is not empty
 * - we still have deletions left (k > 0)
 * - the top of the stack is greater than ch
 * - Pop the stack (remove the larger digit).
 * - Decrease k.
 * - Push the current digit onto the stack.
 *
 * Notes:
 * - If after processing all digits, k > 0:
 * - It means the number was already in increasing order.
 * - The best choice is to remove digits from the end (least significant digits).
 */
package stack;

public class RemoveKDigits {

    public static void main(String[] args) {
        System.out.println(removeKdigits("1432219", 3));
    }

    public static String removeKdigits(String num, int k) {
        StringBuilder stack = new StringBuilder();

        for(char ch : num.toCharArray()){

            while(!stack.isEmpty() && k > 0 && ch < stack.charAt(stack.length() - 1)){
                stack.deleteCharAt(stack.length() - 1);
                k--;
            }
            stack.append(ch);
        }

        while(k > 0){
            stack.deleteCharAt(stack.length() - 1);
            k--;
        }

        int idx = 0;

        while(idx < stack.length() && stack.charAt(idx) == '0') {
            stack.deleteCharAt(idx);
        }
        return stack.isEmpty() ? "0" : stack.toString();
    }
}
