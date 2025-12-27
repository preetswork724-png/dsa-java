/**
 * Problem:
 * <Substring with Concatenation of All Words>
 *
 * Link:
 * <https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/>
 *
 * Pattern:
 * <Sliding Window>
 *
 * Brute Force Intuition:
 * - For every possible starting index :
 * - Try all the substrings of total len (len of word * number of words).
 * - Split the substring into k chunks os len of word.
 * - Check whether the chunk list is a permutation of the words.
 * - To check permutations, use hashmap frequency comparison or sorting.
 *
 * - Why it is inefficient?
 * - Rebuilds frequency map for every index.
 * - Re-checks large overlapping chunks unnecessarily.
 *
 * Time Complexity:
 * - O(N * K * wordLen)
 * Space Complexity:
 * - O(k)
 *
 * Better Approach Intuition:
 * - Still check every substring of size wordLen.
 * - Use a frequency map for words.
 * - Walk in the substring word by word instead of dividing the substring into k chunks of words each of len of word.
 * - Stop early when mismatch occurs.
 * - For each index i :
 * - Walk in steps of wordLen.
 * - Build frequency map for each word.
 * - If any word exceeds required frequency -> break early.
 * - If all match -> answer.
 *
 * - Why it is still not optimal?
 * - Restart counting for every i.
 * - Completely rebuild window counts repeatedly.
 *
 * Time Complexity:
 * - O(N * K)
 * Space Complexity:
 * - O(K)
 *
 * Optimal Approach (Used Below):
 * - Words are of fixed length, so chunk sliding in windowLen steps, not by 1 char.
 * - We only care about word frequencies, not permutations.
 * - A window of exactly k words must match.
 * - Find every window of size k in s where the word frequency matches the target.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(k)
 *
 * Notes:
 * - For each offset from len 0 to wordLen - 1 :
 * - Maintain left pointer, current window HashMap, number if valid matched words.
 * - Move right pointer:
 * - read word = s[right : right + wordLen]
 * - if not in target → clear window, move left , else:
 * - add to window
 * - if frequency exceeds required → shrink from left until valid
 * - if window has exactly k words → record index
 */
package sliding_window;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class SubstringWithConcatenationOfAllWords {

    public static void main(String[] args) {
        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo","bar"}));
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0)
            return res;

        int wordLen = words[0].length();
        int totalWords = words.length;
        int totalLen = wordLen * totalWords;

        if (s.length() < totalLen) return res;

        Map<String, Integer> target = new HashMap<>();
        for (String w : words)
            target.put(w, target.getOrDefault(w, 0) + 1);

        // try each offset
        for (int offset = 0; offset < wordLen; offset++) {
            Map<String, Integer> window = new HashMap<>();
            int left = offset, count = 0;

            for (int right = offset; right + wordLen <= s.length(); right += wordLen) {
                String word = s.substring(right, right + wordLen);

                if (target.containsKey(word)) {
                    window.put(word, window.getOrDefault(word, 0) + 1);
                    count++;

                    // shrink if freq exceeds allowed
                    while (window.get(word) > target.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        window.put(leftWord, window.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    if (count == totalWords) {
                        res.add(left);
                        String leftWord = s.substring(left, left + wordLen);
                        window.put(leftWord, window.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }
                } else {
                    window.clear();
                    count = 0;
                    left = right + wordLen;
                }
            }
        }

        return res;
    }
}
