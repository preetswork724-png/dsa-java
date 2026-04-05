/**
 * Problem:
 * <Stream of Characters (StreamChecker)>
 *
 * Link:
 * <https://leetcode.com/problems/stream-of-characters/>
 *
 * Pattern:
 * <Trie (Reversed) / Suffix Matching / Streaming Queries>
 *
 *
 * Brute Force Intuition:
 * - Maintain the entire stream of characters.
 * - For each query:
 *     - Check every word in the dictionary.
 *     - Compare if the word matches the suffix of the stream.
 *
 * - Why it is inefficient?
 * - Re-checking all words for every query.
 * - Repeated substring comparisons.
 * - Creating substrings is costly.
 *
 * Time Complexity:
 * - O(Q * W * L)
 *   where:
 *   Q = number of queries
 *   W = number of words
 *   L = max word length
 *
 * Space Complexity:
 * - O(Q) (for storing stream)
 *
 *
 * Better Approach Intuition:
 * - Maintain stream.
 * - Instead of substring creation:
 *     - Compare characters from end of stream and word.
 *
 * - Why it is better?
 * - Avoids substring creation overhead.
 *
 * - Why it is still bad?
 * - Still checking all words for each query.
 * - No reuse of prefix/suffix computations.
 *
 * Time Complexity:
 * - O(Q * W * L)
 *
 * Space Complexity:
 * - O(Q)
 *
 *
 * Better++ Approach (Trie + Reverse - Used Below):
 * - Reverse all words and insert into Trie.
 *
 * - Maintain stream of characters.
 *
 * - For each query:
 *     - Traverse stream backwards.
 *     - Match characters in Trie.
 *
 *     - If:
 *         - node.isEnd == true → word found
 *         - child does not exist → stop (prune)
 *
 * - Why this works?
 * - Suffix matching becomes prefix matching on reversed words.
 * - Trie allows efficient prefix lookup.
 * - Early pruning reduces unnecessary checks.
 *
 * - Key Insight:
 * - "Reverse words → convert suffix problem into prefix problem"
 *
 * Time Complexity:
 * - Trie build: O(W * L)
 * - Query: O(L)
 * - Overall: O(Q * L)
 *
 * Space Complexity:
 * - O(W * L) (Trie)
 * - O(L) (stream limited to max word length)
 *
 *
 * Optimal Approach (Further Optimization):
 * - Limit stream size to max word length.
 *
 * - Idea:
 * - Only last L characters matter for suffix matching.
 * - Remove older characters from stream.
 *
 * - Why it helps?
 * - Reduces memory usage.
 * - Keeps traversal bounded.
 *
 *
 * Notes:
 * - Always reverse words before inserting into Trie.
 * - Traverse stream from end (latest character).
 *
 * - Important optimizations:
 *     - Limit stream size to max word length.
 *     - Stop traversal when Trie path breaks.
 *     - Always remove the oldest character.
 *
 * - Common mistakes:
 * - Checking only last character (incorrect logic)
 * - Not reversing words
 * - Not pruning search early
 * - Storing entire stream unnecessarily
 *
 * - Mental model:
 * - "Walk backwards in stream, forwards in Trie"
 *
 * - Edge cases:
 * - Single character words
 * - Multiple words sharing suffix
 * - Large number of queries
 *
 * - Key takeaway:
 * - Reverse Trie transforms repeated suffix checks into efficient prefix traversal
 */

package trees._10_SpecialTrees.Trie;

public class StreamOfCharacters {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    TrieNode root;
    StringBuilder stream;
    int maxLen;

    public StreamOfCharacters(String[] words) {

        root = new TrieNode();
        stream = new StringBuilder();
        maxLen = 0;

        for (String word : words) {
            maxLen = Math.max(maxLen, word.length());
            insert(word);
        }
    }

    private void insert(String word) {

        TrieNode node = root;

        for (int i = word.length() - 1; i >= 0; i--) {
            int idx = word.charAt(i) - 'a';

            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }

            node = node.children[idx];
        }

        node.isEnd = true;
    }

    public boolean query(char letter) {

        stream.append(letter);

        // keep only last maxLen characters
        if (stream.length() > maxLen) {
            stream.deleteCharAt(0);
        }

        TrieNode node = root;

        for (int i = stream.length() - 1; i >= 0; i--) {

            int idx = stream.charAt(i) - 'a';

            if (node.children[idx] == null) return false;

            node = node.children[idx];

            if (node.isEnd) return true;
        }

        return false;
    }
}
