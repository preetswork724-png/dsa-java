/**
 * Problem:
 * <Replace Words>
 *
 * Link:
 * <https://leetcode.com/problems/replace-words/description/>
 *
 * Pattern:
 * <Trie / Prefix Matching>
 *
 *
 * Brute Force Intuition:
 * - For each root in dictionary:
 *     - Compare it with every word in the sentence.
 *     - Check character-by-character if it is a prefix.
 *     - If yes, replace the word.
 *
 * - Why it is inefficient?
 * - Repeated comparisons of same prefixes.
 * - Even after finding a valid root, we continue checking others.
 * - No early stopping → wasted work.
 *
 * Time Complexity:
 * - O(D * W * L)
 *   where:
 *   D = dictionary size
 *   W = number of words
 *   L = max length of word/root
 *
 * Space Complexity:
 * - O(W * L) (for output)
 *
 *
 * Better Approach Intuition:
 * - For each word:
 *     - Check all roots in dictionary.
 *     - Pick the shortest matching prefix.
 *
 * - Why it is better?
 * - Slight improvement if we break early after finding a shorter root.
 *
 * - Why it is still bad?
 * - Still scanning full dictionary for every word.
 * - Prefix matching repeated again and again.
 *
 * Time Complexity:
 * - O(W * D * L)
 *
 * Space Complexity:
 * - O(W * L)
 *
 *
 * Better++ Approach (Trie - Used Below):
 * - Build a Trie using all dictionary roots.
 *
 * - For each word in sentence:
 *     - Traverse Trie character by character.
 *     - If:
 *         - node.isEnd == true → found shortest root → stop
 *         - child not present → no root exists → keep original word
 *
 * - Why this works?
 * - Prefix lookup becomes efficient.
 * - Stops immediately after shortest root is found.
 * - Avoids redundant comparisons.
 *
 * - Key Insight:
 * - Trie helps in "fast prefix search with early stopping"
 *
 * Time Complexity:
 * - Building Trie: O(D * L)
 * - Processing words: O(W * L)
 * - Overall: O((D + W) * L)
 *
 * Space Complexity:
 * - O(D * L) (Trie storage)
 *
 *
 * Optimal Approach (Discussion):
 * - Trie is already optimal for this problem.
 *
 * - Why no better structure?
 * - We need prefix-based lookup with early termination.
 * - Hashing cannot efficiently handle prefix matching.
 *
 * - Alternative:
 * - Sorting dictionary by length and scanning
 *   → still worse than Trie in worst case
 *
 *
 * Notes:
 * - Always replace with the shortest root.
 * - Stop traversal as soon as isEnd is reached.
 *
 * - Common mistakes:
 * - Not stopping early after finding root
 * - Re-checking entire dictionary for every word
 * - Incorrect Trie traversal (missing null checks)
 *
 * - Mental model:
 * - "Instead of checking all roots → walk down prefix path once"
 *
 * - Edge cases:
 * - No root matches → keep word unchanged
 * - Multiple roots match → pick shortest
 * - Empty dictionary → return original sentence
 *
 * - Key takeaway:
 * - Trie converts repeated prefix checks into a single traversal
 * - Reduces complexity from O(D * W * L) → O((D + W) * L)
 */

package trees._10_SpecialTrees.Trie;

import java.util.List;

public class ReplaceWords {
    TrieNode root = new TrieNode();

    public String replaceWords(List<String> dictionary, String sentence) {

        for (String root : dictionary) {
            insert(root);
        }

        String[] words = sentence.split(" ");
        StringBuilder res = new StringBuilder();

        for (String word : words) {
            res.append(findRoot(word)).append(" ");
        }

        return res.toString().trim();
    }

    public void insert(String word) {

        TrieNode node = root;

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';

            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }

            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public String findRoot(String word) {

        StringBuilder prefix = new StringBuilder();
        TrieNode node = root;

        for (char ch : word.toCharArray()) {

            int idx = ch - 'a';

            if (node.children[idx] == null) {
                return word;
            }

            prefix.append(ch);

            node = node.children[idx];

            if (node.isEnd) {
                return prefix.toString();
            }
        }
        return word;
    }
}
