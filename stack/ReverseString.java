/**
 * Problem:
 * <Reverse String>
 *
 * Link:
 * <https://leetcode.com/problems/reverse-string/>
 *
 * Pattern:
 * <Stack>
 *
 * Brute Force Intuition:
 * - Append all the characters of the array in a string.
 * - Convert the String to a StringBuilder.
 * - Use the reverse method.
 * - And then again convert back to the string.
 *
 * - Why it is inefficient?
 * - Need to convert the String to StringBuilder to reverse it.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Push characters in the stack as you traverse the char[] from left to right.
 * - Pop out the characters and append it to the string.
 *
 * - Why it is still not optimal?
 * - Stack solution often requires extra memory.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 *  Optimal Approach ( Used Below ):
 * - Take 2 pointers.
 * - One at the start and another at the end.
 * - Swap the characters until the pointers meet.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - reverse() of StringBuilder reverses the string in-place.
 */

package stack;

import java.util.Arrays;

public class ReverseString {

    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        reverseString(s);
        System.out.println(Arrays.toString(s));
    }

    public static void reverseString(char[] s) {
        int n = s.length, i = 0, j = n - 1;

        while(i < j){
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}
