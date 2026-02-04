/**
 * Problem:
 * <String Compression>
 *
 * Link:
 * <https://leetcode.com/problems/string-compression/description/>
 *
 * Pattern:
 * <in-place two pointers>
 *
 * Brute Force Intuition:
 * - String concatenation.
 * - Take an empty string.
 * - Place a pointer i on the first char, place j on the same char.
 * - Skip the same char using j pointer.
 * - Count the difference between j and i.
 * - If diff > 1, concat the char with the diff which is basically the consecutive frequency of that char.
 * - Else, just concat the character.
 * - Copy the characters of the string back to the original array.
 *
 * - Why it is inefficient?
 * - Every operation performed on the string creates a new String object.
 * - Violates the in-place constraint.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach intuition:
 * - Instead of concatenating characters in the string, dd the operations on a mutable string i.e StringBuffer or StringBuilder.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why this is still not optimal?
 * - Violates the in-place constraint.
 *
 * Optimal Approach (Used below):
 * - In-place two-pointer,
 * - Maintain a variable write which points to the first position in the array.
 * - Increment write after placing the character at chars[write].
 * - If the count > 1, then covert the count to a string and append the digits of the count one by one.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - To modify the original array, you must copy values and not reassign references.
 */

package strings;

import java.util.Arrays;

public class StringCompression {
    public static void main(String[] args) {
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int end = compress(chars);
        for(int i = 0; i < end; i++){
            System.out.print(chars[i] + ", ");
        }
    }

    public static int compress(char[] chars) {
        int n = chars.length, write = 0, i = 0;

        while(i < n){

            char curr = chars[i];
            int j = i;

            while(j < n && chars[j] == curr){
                j++;
            }

            int count = j - i;
            chars[write++] = curr;

            if(count > 1){
                char[] cnt = Integer.toString(count).toCharArray();

                for(char ch : cnt){
                    chars[write++] = ch;
                }
            }
            i = j;
        }
        return write;
    }
}
