/**
 * Problem:
 * <Find All Anagrams In A String>
 *
 * Link:
 * <https://leetcode.com/problems/find-all-anagrams-in-a-string/description/>
 *
 * Pattern:
 * <Sliding Window>
 *
 * Brute Force Intuition:
 * - Precompute the char[] for string p.
 * - Sort it and then store it in a string.
 * - Now generate substrings of length p from s
 * - Store it in a char[], sort and store it in a string.
 * - Compare the two strings.
 *
 * - Why it is inefficient?
 * - Repeatedly sorting every substring where sorting is not necessary for checking if two strings are anagrams of each other.
 *
 * Time Complexity:
 * - O(N * M log M)
 * Space Complexity:
 * - O(M)
 *
 * Better Approach Intuition:
 * - Still checks every window of size p.
 * - But instead if sorting, build frequency arrays fresh each time.
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
 * - Precompute the freq array for string p.
 * - Compute the first window of size p and compute the freq of chars in string s.
 * - Now maintain, the window size.
 * - Remove char out of the window by decrementing its frequency.
 * - Add char in the window by incrementing it's frequency.
 * - Compare both the arrays.
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
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class FindAllAnagramsInAString {
    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        int n = s.length(), window = p.length();

        List<Integer> res = new ArrayList<>();

        if(n < window) return res;

        int[] pFreq = new int[26], sFreq = new int[26];

        for(char ch : p.toCharArray()) pFreq[ch - 'a']++;

        for(int i = 0; i < window; i++){
            sFreq[s.charAt(i) - 'a']++;
        }

        if(Arrays.equals(pFreq, sFreq)) res.add(0);

        for(int i = window; i < n; i++){
            sFreq[s.charAt(i - window) - 'a']--;
            sFreq[s.charAt(i) - 'a']++;

            if(Arrays.equals(pFreq, sFreq)) res.add(i - window + 1);
        }

        return res;
    }
}
