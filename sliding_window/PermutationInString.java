/**
 * Problem:
 * <Permutation in String>
 *
 * Link:
 * <https://leetcode.com/problems/permutation-in-string/description/>
 *
 * Pattern:
 * <Sliding Window>
 *
 * Brute Force Intuition:
 * - Precompute the char[] for string s1.
 * - Sort it and then convert it back to string.
 * - Generate all the substrings of len s1 from s2.
 * - Sort them and convert it back into the string.
 * - Compare the 2 strings.
 *
 * - Why it is inefficient?
 * - Repeatedly sorting every substring where sorting is not necessary for checking if two strings are permutations (anagrams) of each other.
 *
 * Time Complexity:
 * - O(N * M log M)
 * Space Complexity:
 * - O(M)
 *
 * Better Approach Intuition:
 * - Still checks every window of size s1.
 * - Instead of sorting every time, we create fresh frequency arrays.
 *
 * - Why it is still not optimal?
 * - Creating frequency arrays each time is redundant as they can be maintained globally.
 *
 * Time Complexity:
 * - O(N * M)
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used Below):
 * - Compute the freq array for string s1.
 * - Now compute the first window of len s1 from s2.
 * - Compare the arrays.
 * - Now slide the window, by adding one char and removing the another.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - The approach might look O(n^2) in first glance, but the Arrays.equals() runs exactly 26 times in each iteration.
 */
package sliding_window;
import java.util.Arrays;
public class PermutationInString {
    public static void main(String[] args) {
        System.out.println(checkInclusion("ab","eidbaooo"));
    }

    public static boolean checkInclusion(String s1, String s2) {
        int n = s2.length(), window = s1.length();

        if(window > n) return false;

        int[] s2Freq = new int[26], s1Freq = new int[26];

        for(char ch : s1.toCharArray()) s1Freq[ch - 'a']++;

        for(int i = 0; i < window; i++){
            s2Freq[s2.charAt(i) - 'a']++;
        }

        if(Arrays.equals(s1Freq, s2Freq)) return true;

        for(int i = window; i < n; i++){
            s2Freq[s2.charAt(i - window) - 'a']--;
            s2Freq[s2.charAt(i) - 'a']++;

            if(Arrays.equals(s1Freq, s2Freq)) return true;
        }
        return false;
    }
}
