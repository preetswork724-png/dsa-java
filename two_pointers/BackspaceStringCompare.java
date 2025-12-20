/**
 * Problem:
 * <Backspace String Compare>
 *
 * Link:
 * <https://leetcode.com/problems/backspace-string-compare/description/>
 *
 * Pattern:
 * <Two Pointer>
 *
 * Brute Force Intuition:
 * - Maintain 2 StringBuilders for easy modification in the string.
 * - Remove the last character appended when there's a backspace.
 * - It works the same as using the Stack.
 * - Important to convert the builders to covert the string before comparing or use the contentEquals().
 *
 * - Why it is inefficient?
 * - Uses extra space to maintain the string when it is not required.
 * - We only need to compare the string and not store them.
 *
 * Time Complexity:
 * - O(N + M)
 * Space Complexity:
 * - O(N + M)
 *
 * Optimal Approach (Used Below):
 * - Smarter to travel the string from right to left because a backspace only affects the previous characters.
 * - Compare the strings using two pointers.
 * - Skip until a valid character.
 * - Compare the characters if the indexes for both the strings are in bounds.
 * - Else if anyone of the strings is exhausted that means they are not equal.
 *
 * - What to say in questions where the better approach is the optimal one?
 * - Every character needs to be visited once in both the strings. Therefore, the time complexity can't be reduced.
 * - The extra space can be reduced by using pointers for comparison.
 *
 * Time Complexity:
 * - O(N + M)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - A window is only valid it's product < k.
 * - Use the left pointer to shrink the window when it becomes invalid.
 * - Use the right pointer to expand the window until it's invalid.
 */
package two_pointers;

public class BackspaceStringCompare {
    public static void main(String[] args) {
        System.out.println(backspaceCompare("ab#c","ad#c"));
    }

    public static boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1, j = t.length()-1;

        while(i >= 0 || j >= 0){

            int skipS = 0;

            while(i >= 0){

                if(s.charAt(i) == '#'){
                    skipS++;
                    i--;
                }
                else if(skipS > 0){
                    skipS--;
                    i--;
                }
                else break;
            }

            int skipT = 0;

            while(j >= 0){

                if(t.charAt(j) == '#'){
                    skipT++;
                    j--;
                }
                else if(skipT > 0){
                    skipT--;
                    j--;
                }
                else break;

            }

            if(i >= 0 && j >= 0){
                if(s.charAt(i) != t.charAt(j)) return false;
            }else{
                if(i >= 0 || j >= 0) return false;
            }

            i--;
            j--;
        }

        return true;
    }
}
