/**
 * Problem:
 * <Minimum Window Substring>
 *
 * Link:
 * <https://leetcode.com/problems/minimum-window-substring/>
 *
 * Pattern:
 * <Sliding Window>
 *
 * Brute Force Intuition:
 * - Precompute frequency of characters in t.
 * - Generate all substrings of s.
 * - For each substring, convert to char[].
 * - Count frequency (or sort / compare arrays)
 * - Check if it contains all chars of t
 * - Track minimum valid window
 *
 * - Why it is inefficient?
 * - Way too slow for large strings.
 * - Re-checks many repeated sub-problems.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(1) or O(charset) for counting arrays.
 * - O(U) number of distinct characters in the map.
 *
 * Better Approach Intuition:
 * - Precompute frequency of characters in t.
 * - Try substrings from every index i.
 * - Expand j to the right till you have formed a valid substring.
 * - Maintain running frequency.
 * - Stop expanding when you have formed a valid substring.
 * - Track length and keep updating the valid substring.
 * - Avoids comparison of arrays from scratch.
 *
 * - Why it is still not optimal?
 * - O(1) or O(charset) for counting arrays.
 * - O(U) number of distinct characters in the map.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Maintain counts dynamically and shrink from left when a window is valid.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1) or O(charset) for counting arrays.
 * - O(U) number of distinct characters in the map.
 *
 * Notes:
 * - The key to obtain the shortest valid substring is to keep tracking and updating the minimum length.
 */
package sliding_window;
import java.util.Map;
import java.util.HashMap;
public class MinimumWindowSubstring{
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    public static String minWindow(String s, String t) {
        if(s.isEmpty() || t.isEmpty() || s.length() < t.length()) return "";

        Map<Character, Integer> tMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();

        for(char ch : t.toCharArray()) tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);

        int n = s.length(), m = t.length(), left = 0, formed = 0, minLen = Integer.MAX_VALUE, startIdx = 0;

        for(int right = 0; right < n; right++){

            char rightChar = s.charAt(right);
            sMap.put(rightChar, sMap.getOrDefault(rightChar, 0) + 1);

            if(tMap.containsKey(rightChar) && sMap.get(rightChar) <= tMap.get(rightChar)) formed++;

            while(formed == m){

                if(right - left + 1 < minLen){
                    minLen = right - left + 1;
                    startIdx = left;
                }

                char leftChar = s.charAt(left);
                sMap.put(leftChar, sMap.getOrDefault(leftChar, 0) - 1);

                if(tMap.containsKey(leftChar) && sMap.get(leftChar) < tMap.get(leftChar)) formed--;
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(startIdx, startIdx + minLen);
    }
}
