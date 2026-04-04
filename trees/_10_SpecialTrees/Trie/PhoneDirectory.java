/**
 * Problem:
 * <Phone Directory / Contacts Suggestion System>
 *
 * Link:
 * <https://www.geeksforgeeks.org/implement-a-phone-directory/>
 *
 * Pattern:
 * <Trie / Prefix Search>
 *
 *
 * Brute Force Intuition:
 * - For each prefix of string s:
 *     - Traverse all contacts.
 *     - Check if contact starts with prefix.
 *     - Collect matching contacts.
 *     - Sort results lexicographically.
 *
 * - Why it is inefficient?
 * - Re-checking all contacts for every prefix.
 * - Repeated prefix comparisons.
 * - Sorting again and again.
 *
 * Time Complexity:
 * - O(|s| * n * L + sorting per prefix)
 *
 * Space Complexity:
 * - O(n * L)
 *
 *
 * Better Approach Intuition:
 * - Sort contacts once.
 * - For each prefix:
 *     - Scan contacts and collect matches.
 *
 * - Why it is better?
 * - Avoids repeated sorting.
 *
 * - Why it is still bad?
 * - Still scanning entire contact list for each prefix.
 * - Redundant prefix checks.
 *
 * Time Complexity:
 * - O(|s| * n * L)
 *
 * Space Complexity:
 * - O(n * L)
 *
 *
 * Better++ Approach (Trie - Used Below):
 * - Build a Trie using all unique contacts (sorted).
 *
 * - Each node stores:
 *     - List of words passing through that prefix
 *
 * - For each prefix of s:
 *     - Traverse Trie
 *     - If path exists → return stored list
 *     - Else → return ["0"]
 *
 * - Why this works?
 * - Prefix search becomes O(L)
 * - Avoids scanning all contacts repeatedly
 * - Precomputes results during insertion
 *
 * - Key Insight:
 * - "Each Trie node stores all contacts sharing that prefix"
 *
 * Time Complexity:
 * - Sorting: O(n log n)
 * - Trie build: O(n * L)
 * - Query: O(|s| * L)
 * - Overall: O(n log n + nL + |s|L)
 *
 * Space Complexity:
 * - O(n * L) (Trie + stored words)
 *
 *
 * Optimal Approach (Discussion):
 * - Alternative: Trie without storing words at each node
 *
 * - Idea:
 * - Store only structure in Trie
 * - Perform DFS at query time to collect words
 *
 * - Trade-off:
 *     - Less memory
 *     - More query time
 *
 * Time Complexity:
 * - Query becomes slower due to DFS
 *
 *
 * Notes:
 * - Remove duplicates using Set before building Trie.
 * - Sort contacts to ensure lexicographical order.
 *
 * - Important behavior:
 *     - If no match → return ["0"]
 *
 * - Common mistakes:
 * - Iterating over n instead of s.length()
 * - Not handling duplicates
 * - Not sorting results
 * - Using static Trie across test cases
 *
 * - Mental model:
 * - "Pre-store answers at each prefix node for fast lookup"
 *
 * - Edge cases:
 * - No matching prefix → ["0"]
 * - Duplicate contacts → remove before inserting
 * - Single contact → return same for all valid prefixes
 *
 * - Key takeaway:
 * - Trie converts repeated prefix scans into direct lookup
 * - Trades space for faster query time
 */

package trees._10_SpecialTrees.Trie;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;

public class PhoneDirectory {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        List<String> words = new ArrayList<>();
    }

    static ArrayList<ArrayList<String>> displayContacts(int n, String contact[], String s) {

        // Step 1: Remove duplicates + sort
        Set<String> set = new HashSet<>(Arrays.asList(contact));
        List<String> uniqueContacts = new ArrayList<>(set);
        Collections.sort(uniqueContacts);

        // Step 2: Build Trie
        TrieNode root = new TrieNode();

        for (String word : uniqueContacts) {
            insert(root, word);
        }

        ArrayList<ArrayList<String>> res = new ArrayList<>();
        StringBuilder prefix = new StringBuilder();

        // Step 3: Process each prefix of s
        for (int i = 0; i < s.length(); i++) {

            prefix.append(s.charAt(i));
            List<String> list = findWordsStartingWith(root, prefix.toString());

            if (list.isEmpty()) {
                list.add("0");
            }

            res.add(new ArrayList<>(list));
        }

        return res;
    }

    static void insert(TrieNode root, String word) {

        TrieNode node = root;

        for (char ch : word.toCharArray()) {

            int idx = ch - 'a';

            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }

            node = node.children[idx];
            node.words.add(word); // already sorted insertion
        }
    }

    static List<String> findWordsStartingWith(TrieNode root, String prefix) {

        TrieNode node = root;

        for (char ch : prefix.toCharArray()) {

            int idx = ch - 'a';

            if (node.children[idx] == null) {
                return new ArrayList<>();
            }

            node = node.children[idx];
        }

        return node.words;
    }
}
