/**
 * Problem:
 * <Longest Repeating Character Replacement>
 *
 * Link:
 * <https://leetcode.com/problems/longest-repeating-character-replacement/description/>
 *
 * Pattern:
 * <Sliding Window>
 *
 * Brute Force Intuition:
 * - Store all the characters in a set.
 * - Fix one char at a time.
 * - Try to generate all the possible substrings with mismatches < k.
 * - Track the max length.
 *
 * - Why it is inefficient?
 * - Way too slow for large strings.
 * - Re-checks many repeated sub-problems.
 *
 * Time Complexity:
 * - O(K * N^2)
 * Space Complexity:
 * - O(K) unique chars int the string which is approximately O(1).
 *
 * Better Approach Intuition:
 * - Fix one char at a time.
 * - Maintain a window between left and right pointers.
 * - Expand the window when mismatches are less than or equal to k and shrink when vice versa.
 *
 * - Why it is still not optimal?
 * - Trying all the windows char by char
 *
 * Time Complexity:
 * - O(O(K * N))
 * Space Complexity:
 * - O(K) unique chars int the string which is approximately O(1).
 *
 * Optimal Approach (Used Below):
 * - In every window, in order to make all the chars equal, we can replace the non-majority chars with the majority ones.
 * - Track the most frequent char in every window.
 * - Replacements = windowSize - maxFreq
 * - If replacements <= k, expand and shrink when vice versa.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(K) unique chars int the string which is approximately O(1).
 *
 * Notes:
 * - The approach is safe even when a window shrinks and the character's frequency is not decremented.
 * - Because a smaller window cannot definitely profit from a bigger window.
 * - Using a while loop is redundant in this case because the window only shrinks by one as the freq of char keeps monotonically increasing in right direction.
 */
package sliding_window;

public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        System.out.println(characterReplacement("ABAB", 2));
    }

    public static int characterReplacement(String s, int k) {
        int[] freq = new int[26];

        int n = s.length(), left = 0, maxFreq = 0, maxLen = 0;

        for(int right = 0; right < n; right++){
            int idx = s.charAt(right) - 'A';
            freq[idx]++;

            maxFreq = Math.max(maxFreq, freq[idx]);
            int window = right - left + 1;

            if(window - maxFreq > k){
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
