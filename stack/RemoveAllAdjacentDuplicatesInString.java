/**
 * Problem:
 * <Remove All Adjacent Duplicates In String>
 *
 * Link:
 * <https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/description/>
 *
 * Pattern:
 * <Stack>
 *
 * Brute Force Intuition:
 * - Scan the string and whenever you find two adjacent equal characters, remove them and restart.
 *
 * - Why it is inefficient?
 * - Not scalable if string is large.
 * - Even after removing the characters, the string has to be scanned from the start which makes it redundant.
 * - Inefficient.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1) / O(N)
 *
 * Better Approach Intuition:
 * - Use Stack to store the characters.
 * - For adjacent duplicates, check the top of the stack.
 * - If the characters are equal then pop it from the stack.
 * - Push in the stack when there is no adjacent duplicate character or the stack is empty.
 * - Build the string from the characters stored in the stack, reverse it and return it.
 *
 * - Why it is still not optimal?
 * - Stack solution often requires extra reverse step.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(K)
 *
 * Optimal Approach Intuition:
 * - Same logic as stack, but we use StringBuilder as an actual stack, less overhead.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Why is stack not the optimal approach but a better approach when the complexities are the same for StringBuilder and Stack ?
 * - Stack is a slow legacy structure.
 * - It is synchronized.
 * - Synchronization = overhead push / pop.
 * - Stack stores character objects / mot primitive chars which takes more memory per element.
 * - Stack solution often requires extra step.
 * - StringBuilder is designed as a mutable stack.
 * - It is not synchronized, less overhead and it allows primitive storage.
 * - StringBuilder simulates stack behavior using a mutable character buffer, avoiding unnecessary overhead.
 * - It operates directly on a primitive char array, making it faster, lighter, and cleaner in practice.
 */
package stack;

public class RemoveAllAdjacentDuplicatesInString {

    public static void main(String[] args) {
        System.out.println(removeDuplicates("abbaca"));
    }

    public static String removeDuplicates(String s) {
        if(s.length() == 1) return s;

        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));

        for(int i = 1; i < s.length(); i++){

            if(!sb.isEmpty() && sb.charAt(sb.length()-1) == s.charAt(i)){
                sb.deleteCharAt(sb.length()-1);
            }
            else{
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

}
