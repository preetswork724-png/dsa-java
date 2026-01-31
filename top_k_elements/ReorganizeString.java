/**
 * Problem:
 * <Reorganize String>
 *
 * Link:
 * <https://leetcode.com/problems/reorganize-string/description/>
 *
 * Pattern:
 * <Max Heap>
 *
 * Brute Force Intuition:
 * - Generate all the permutations of the string.
 * - Scan the string to check for adjacent duplicate elements.
 *
 * - Why it is inefficient?
 * - Time Complexity explodes for large n.
 *
 * Time Complexity:
 * - O(N * N!)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach intuition:
 * - MaxHeap + Hashing.
 * - Compute the frequencies of the characters and store them inside a map.
 * - Maintain a maxHeap where characters are stored in decreasing order of their frequencies.
 * - Add all the keys of a hashmap to heap.
 * - Keep polling until only one character is left in the heap.
 * - In each iteration:
 * - poll() out the 2 top most frequent characters from the heap.
 * - Append them to a string.
 * - Add them back to the heap only if their frequency after deletion is greater than 0.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Why this is still not optimal?
 * - Uses extra space.
 *
 * Optimal Approach (Used below):
 * - Counting fill.
 * - Compute the frequency of the characters.
 * - Find the most frequent character along with its frequency.
 * - If the character appears more than (n+1)/2 times, return "".
 * - Put the most frequent character at the even indices,
 * - Put the other characters at even indices.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - We only ever visit each index once:
 * - Phase 1: even indices.
 * - Phase 2: odd indices.
 */

package top_k_elements;

public class ReorganizeString {

    public static void main(String[] args) {
        System.out.println(reorganizeString("aab"));
    }

    public static String reorganizeString(String s) {
        int n = s.length();

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) freq[ch - 'a']++;

        int max = 0, letter = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] > max) {
                max = freq[i];
                letter = i;
            }
        }

        if (max > (n + 1) / 2) return "";
        char[] res = new char[n];
        int idx = 0;

        while (freq[letter]-- > 0) {
            res[idx] = (char) (letter + 'a');
            idx += 2;
        }

        for (int i = 0; i < 26; i++) {

            while (freq[i]-- > 0) {
                if (idx >= n) idx = 1;
                res[idx] = (char) (i + 'a');
                idx += 2;
            }
        }
        return new String(res);
    }
}
