/**
 * Problem:
 * <Longest Substring Without Repeating Characters>
 *
 * Link:
 * <https://leetcode.com/problems/longest-substring-without-repeating-characters/description/>
 *
 * Pattern:
 * <Sliding Window>
 *
 * Brute Force Intuition:
 * - Try every starting index i.
 * - Try every ending index j.
 * - Between i to j, check for the duplicate characters.
 * - Track the longest valid one.
 *
 * - Why it is inefficient?
 * - Way too slow for large strings.
 * - Re-checks many repeated sub-problems.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Try substrings from every index i.
 * - Expand till you find non-repeating characters.
 * - Track length.
 * - Break when a character repeats.
 *
 * - Why it is still not optimal?
 * - Computing substrings from every index wastes the time of checking overlapping intervals.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Maintain a window between left and right pointers where the characters are unique.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Shrink when a character repeats.
 * - Expand when a character is unique in the window.
 * - Do not remove the character directly from the window as it breaks the continuity of the substrings.
 * - Remove all the characters until that specific character is removed.
 */
package sliding_window;
import java.util.Set;
import java.util.HashSet;
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();

        if(n == 0) return 0;
        if(n == 1) return 1;

        int left = 0, maxLen = 0;
        HashSet<Character> window = new HashSet<>();

        for(int right = 0; right < n; right++){
            char ch = s.charAt(right);

            while(window.contains(ch)){
                window.remove(s.charAt(left));
                left++;
            }
            window.add(ch);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
