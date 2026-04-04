/**
 * Problem:
 * <Palindrome Pairs>
 *
 * Link:
 * <https://leetcode.com/problems/palindrome-pairs/>
 *
 * Pattern:
 * <Trie / HashMap Lookup>
 *
 *
 * Brute Force Intuition:
 * - Try all pairs (i, j) where i != j.
 * - Concatenate words[i] + words[j].
 * - Check if the result is a palindrome.
 *
 * - Why it is inefficient?
 * - Checking all pairs leads to O(N^2).
 * - Palindrome check takes O(L).
 * - Repeated string concatenation.
 *
 * Time Complexity:
 * - O(N^2 * L)
 *
 * Space Complexity:
 * - O(1)
 *
 *
 * Better Approach Intuition:
 * - Store all words in a HashMap (word → index).
 * - For each word:
 *     - Check if reverse(word) exists.
 *
 * - Why it is better?
 * - Avoids checking all pairs.
 *
 * - Why it is still bad?
 * - Only works for full reverse matches.
 * - Misses cases where partial matching forms palindrome.
 *
 * Example Failure:
 * - ["lls", "s"] → "slls" is palindrome
 * - But reverse("lls") = "sll" not present
 *
 * Time Complexity:
 * - O(N * L)
 *
 * Space Complexity:
 * - O(N * L)
 *
 *
 * Better++ Approach (HashMap + Splitting - Used Below):
 * - Store all words in HashMap.
 *
 * - For each word:
 *     - Try all possible splits:
 *         word = prefix | suffix
 *
 *     - Case 1:
 *         - If prefix is palindrome
 *         - Check if reverse(suffix) exists
 *
 *     - Case 2:
 *         - If suffix is palindrome
 *         - Check if reverse(prefix) exists
 *
 * - Why this works?
 * - Handles partial matches.
 * - Covers all palindrome construction cases.
 *
 * - Key Insight:
 * - "Only unmatched part needs reverse match, rest must already be palindrome"
 *
 * Time Complexity:
 * - O(N * L^2)
 *
 * Space Complexity:
 * - O(N * L)
 *
 *
 * Optimal Approach (Trie - Used Below):
 * - Build Trie with reversed words.
 *
 * - Each node stores:
 *     - index → word ends here
 *     - list → indices of words where remaining prefix is palindrome
 *
 * - While searching:
 *     - Traverse Trie using current word.
 *
 *     - Case 1:
 *         - If node.index != -1
 *         - Remaining part of word is palindrome
 *         → valid pair
 *
 *     - Case 2:
 *         - After full traversal:
 *         - Check all indices in node.list
 *
 * - Why it is optimal?
 * - Avoids repeated substring creation.
 * - Efficient prefix matching.
 * - Handles partial matches naturally.
 *
 * Time Complexity:
 * - O(N * L^2)
 *
 * Space Complexity:
 * - O(N * L)
 *
 *
 * Notes:
 * - Reverse words before inserting into Trie.
 * - Use isPalindrome checks carefully on substrings.
 *
 * - Important conditions:
 *     - prefix palindrome → match reversed suffix
 *     - suffix palindrome → match reversed prefix
 *
 * - Common mistakes:
 * - Only checking full reverse
 * - Ignoring partial matches
 * - Missing edge case with empty string
 *
 * - Mental model:
 * - "Split word → match missing part → ensure remaining part is palindrome"
 *
 * - Edge cases:
 * - Empty string → pairs with all palindromes
 * - Single character words
 * - Duplicate palindrome possibilities
 *
 * - Key takeaway:
 * - Palindrome pairs require partial matching logic
 * - Trie helps optimize reverse lookup and pruning
 */

package trees._10_SpecialTrees.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PalindromePairs {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int index = -1;
        List<Integer> list = new ArrayList<>();
    }

    TrieNode root = new TrieNode();

    public List<List<Integer>> palindromePairs(String[] words) {

        List<List<Integer>> res = new ArrayList<>();

        // build Trie
        for (int i = 0; i < words.length; i++) {
            insert(words[i], i);
        }

        // search
        for (int i = 0; i < words.length; i++) {
            search(words[i], i, res);
        }

        return res;
    }

    private void insert(String word, int index) {

        TrieNode node = root;

        for (int i = word.length() - 1; i >= 0; i--) {

            if (isPalindrome(word, 0, i)) {
                node.list.add(index);
            }

            int idx = word.charAt(i) - 'a';

            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }

            node = node.children[idx];
        }

        node.index = index;
        node.list.add(index);
    }

    private void search(String word, int i, List<List<Integer>> res) {

        TrieNode node = root;

        for (int j = 0; j < word.length(); j++) {

            if (node.index >= 0 && node.index != i &&
                    isPalindrome(word, j, word.length() - 1)) {

                res.add(Arrays.asList(i, node.index));
            }

            int idx = word.charAt(j) - 'a';

            if (node.children[idx] == null) return;

            node = node.children[idx];
        }

        for (int j : node.list) {
            if (j != i) {
                res.add(Arrays.asList(i, j));
            }
        }
    }

    private boolean isPalindrome(String s, int l, int r) {

        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }

        return true;
    }
}
