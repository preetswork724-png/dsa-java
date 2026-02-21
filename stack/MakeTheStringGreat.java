/**
 * Problem:
 * <Make The String Great>
 *
 * Link:
 * <https://leetcode.com/problems/make-the-string-great/description/>
 *
 * Pattern:
 * <Stack>
 *
 * Brute Force Intuition:
 * - Keep removing the current character if the current char and the next adjacent char form a bad pair.
 * - Keep modifying the string until you can't.
 *
 * - Why it is inefficient?
 * - Repeated scanning of the string from the scratch.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach:
 * - Use a stack and compare the top most character everytime before popping or pushing in the stack.
 * - Push when the top character and the current char does not form a bad pair.
 * - Pop when they form a bad pair.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why this is not optimal?
 * - The above solution is already optimal, and we cannot do better in terms of time complexity as:
 * - Each character is pushed once and popped once.
 *
 * Optimal Approach (used below):
 * - Use StringBuilder as a stack.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Do not overengineer the problem by comparing the cases of the characters.
 * - Instead, just check if their ASCII difference equals to 32.
 */
package stack;

public class MakeTheStringGreat {

    public static void main(String[] args) {
        System.out.println(makeGood("leEeetcode"));
    }

    public static String makeGood(String s) {
        int n = s.length();
        if(n == 1) return s;

        StringBuilder greatStr = new StringBuilder();

        for(char ch : s.toCharArray()){

            if(!greatStr.isEmpty() && Math.abs(greatStr.charAt(greatStr.length()-1) - ch) == 32){
                greatStr.deleteCharAt(greatStr.length()-1);
            }
            else{
                greatStr.append(ch);
            }
        }

        return greatStr.toString();
    }
}
