/**
 * Problem:
 * <Longest Substring with K uniques>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1>
 *
 * Pattern:
 * <Sliding Window>
 *
 * Brute Force Intuition:
 * - Generate all possible substrings.
 * - Scan the substring for distinct characters.
 * - If they match k, then record the len.
 *
 * - Why it is inefficient?
 * - Generating every possible substring and scanning each substring is not optimal and repeated work.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(k)
 *
 * Better Approach Intuition:
 * - Maintain a freq map for each substring.
 * - Stop early when you get more distinct chars than required.
 * - Start from another index.
 *
 * - Why it is still not optimal?
 * - Counts distinct in O(1).
 * - Starts the counting from the scratch.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(k)
 *
 * Optimal Approach (Used Below):
 * - Maintain a window between left and right pointers where the number of distinct chars is exactly k.
 * - Make use of char hashing as all the chars are lowercase.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Shrink when distinct chars in the current window exceed k.
 * - Expand when distinct chars in the current window is less than k.
 */
package sliding_window;

public class LongestSubstringWithKUniques {
    public static void main(String[] args) {
        System.out.println(longestKSubstr("aabacbebebe", 3));
    }

    public static int longestKSubstr(String s, int k) {

        int n = s.length(), countDistinct = 0, left = 0, len = -1;
        int[] freq = new int[26];

        for(int right = 0; right < n; right++){

            char rightChar = s.charAt(right);
            freq[rightChar - 'a']++;

            if(freq[rightChar - 'a'] == 1) countDistinct++;

            while(left < n && countDistinct > k){
                char leftChar = s.charAt(left);
                freq[leftChar - 'a']--;

                if(freq[leftChar - 'a'] == 0) countDistinct--;
                left++;
            }

            if(countDistinct == k) len = Math.max(len, right - left + 1);
        }
        return len;
    }
}
