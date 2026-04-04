/**
 * Problem:
 * <Longest Common Prefix>
 *
 * Link:
 * <https://leetcode.com/problems/longest-common-prefix/>
 *
 * Pattern:
 * <Trie / Prefix Counting>
 *
 *
 * Brute Force Intuition:
 * - Take the first string as reference.
 * - Compare it character by character with all other strings.
 * - Stop when mismatch occurs.
 *
 * - Why it is inefficient?
 * - Repeated comparisons for each character across all strings.
 * - Not scalable for large inputs.
 *
 * Time Complexity:
 * - O(N * L)
 *
 * Space Complexity:
 * - O(1)
 *
 *
 * Better Approach Intuition:
 * - Sort the array of strings.
 * - Compare only the first and last string.
 *
 * - Why it works?
 * - After sorting, most different strings are at extremes.
 * - LCP of entire array = LCP of first and last string.
 *
 * Time Complexity:
 * - O(N log N + L)
 *
 * Space Complexity:
 * - O(1)
 *
 *
 * Optimal Approach (Trie - Used Below):
 * - Build a Trie using all strings.
 * - Each node maintains countPrefix = number of strings passing through it.
 *
 * - Traverse Trie from root:
 *     - Move forward only if:
 *         - Exactly one child exists
 *         - AND child.countPrefix == total number of strings (n)
 *
 * - Stop when:
 *     - More than one child exists OR
 *     - countPrefix < n
 *
 * - Why this works?
 * - countPrefix == n ensures that prefix is common to ALL strings.
 * - Single-child condition ensures no branching (same path).
 *
 * - Key Insight:
 * - "LCP is the path where all strings pass together without branching"
 *
 * Time Complexity:
 * - Build Trie: O(N * L)
 * - Traverse: O(L * 26) ≈ O(L)
 * - Overall: O(N * L)
 *
 * Space Complexity:
 * - O(N * L)
 *
 *
 * Optimal Approach (Most Practical):
 * - Horizontal scanning:
 *     - Start with first string as prefix.
 *     - Compare with each string and shrink prefix.
 *
 * - OR Vertical scanning:
 *     - Compare characters column-wise across all strings.
 *
 * - Why it is optimal?
 * - No extra data structure required.
 * - Minimal space usage.
 *
 * Time Complexity:
 * - O(N * L)
 *
 * Space Complexity:
 * - O(1)
 *
 *
 * Notes:
 * - Prefix = starting substring common across all strings.
 *
 * - Trie Insight:
 * - countPrefix tracks how many strings share this prefix.
 *
 * - Important conditions:
 *     - countPrefix == n → valid common prefix
 *     - Multiple children → prefix diverges → stop
 *
 * - Common mistakes:
 * - Using max count instead of checking count == n
 * - Ignoring branching in Trie traversal
 * - Overcomplicating when simpler approaches exist
 *
 * - Mental model:
 * - "Follow the path where all strings move together"
 *
 * - Edge cases:
 * - Empty array → return ""
 * - One string → return the string itself
 * - No common prefix → return ""
 *
 * - Key takeaway:
 * - Trie solves it using prefix counting, but is overkill
 * - Simpler linear scan is usually preferred in interviews
 */

package trees._10_SpecialTrees.Trie;

public class LongestCommonPrefix {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int countPrefix = 0;
    }

    TrieNode root = new TrieNode();
    int maxCountPrefix = 0;

    public String longestCommonPrefix(String[] strs) {

        int n = strs.length;

        for (String word : strs) {
            insert(word);
        }

        StringBuilder lcp = new StringBuilder();

        TrieNode node = root;

        while (true) {
            int count = 0;
            int nextIdx = -1;

            for (int i = 0; i < 26; i++) {

                if (node.children[i] != null) {

                    if (node.children[i].countPrefix == n) {
                        count++;
                        nextIdx = i;
                    }
                }
            }

            if (count != 1) break;
            node = node.children[nextIdx];
            lcp.append((char) (nextIdx + 'a'));
        }
        return lcp.toString();
    }

    public void insert(String word) {

        TrieNode node = root;

        for (char ch : word.toCharArray()) {

            int idx = ch - 'a';

            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }

            node = node.children[idx];
            node.countPrefix++;
        }
    }
}
