/**
 * Problem:
 * <Implement Trie (Prefix Tree)>
 *
 * Link:
 * <https://leetcode.com/problems/implement-trie-prefix-tree/>
 *
 * Pattern:
 * <Trie / Prefix Tree + Character Mapping>
 *
 *
 * Core Intuition:
 * - A Trie is a tree structure used to store strings efficiently.
 * - Each node represents a character.
 * - A path from root → node forms a prefix.
 * - A complete word is identified using a special marker (isEnd).
 *
 * - Key idea:
 *   → Common prefixes are stored once and shared across words.
 *
 *
 * Structure:
 * - Each node contains:
 *     → children[26] → references to next characters ('a' to 'z')
 *     → isEnd → indicates end of a valid word
 *
 * - Root node represents an empty string.
 *
 *
 * Operations:
 *
 * 1. Insert(word):
 *    - Start from root.
 *    - For each character:
 *        → map character to index (ch - 'a')
 *        → if child does not exist → create node
 *        → move to child node
 *    - After last character:
 *        → mark isEnd = true
 *
 *
 * 2. Search(word):
 *    - Traverse character by character.
 *    - If any character path is missing → return false.
 *    - After traversal:
 *        → return isEnd (ensures full word match)
 *
 *
 * 3. startsWith(prefix):
 *    - Traverse character by character.
 *    - If any character path is missing → return false.
 *    - If traversal completes → return true
 *
 *    - Note:
 *        → Do NOT check isEnd here
 *
 *
 * Key Observations:
 * - Trie stores characters as paths, not full strings.
 * - Each edge corresponds to a character transition.
 * - isEnd distinguishes between:
 *     → prefix ("app")
 *     → complete word ("apple")
 *
 *
 * Time Complexity:
 * - Insert: O(L)
 * - Search: O(L)
 * - startsWith: O(L)
 *   (L = length of input string)
 *
 *
 * Space Complexity:
 * - O(N * L)
 *   (Worst case: no shared prefixes)
 *
 *
 * Notes:
 * - Character to index mapping:
 *     → index = ch - 'a'
 *
 * - children[index] provides O(1) access to next node.
 *
 * - Trie is ideal for:
 *     → prefix search
 *     → dictionary lookup
 *     → autocomplete systems
 *
 *
 * Common mistakes:
 * - Forgetting to mark isEnd at last node.
 * - Using isEnd in startsWith().
 * - Not moving node pointer during traversal.
 * - Incorrect character-index mapping.
 *
 *
 * Mental model:
 * - "Insert → build path"
 * - "Search → verify path + end"
 * - "Prefix → verify path only"
 *
 *
 * Edge cases:
 * - Empty string insertion
 * - Searching prefix that is not a complete word
 * - Multiple words sharing the same prefix
 *
 *
 * Key takeaway:
 * - Trie optimizes prefix-based operations by sharing common paths
 *
 *
 * Follow-up:
 * - Word Break (Trie + DP)
 * - Word Search II (Trie + Backtracking)
 * - Replace Words
 * - Longest word with all prefixes
 */

package trees._10_SpecialTrees.Trie;

class Trie {

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // INSERT
    public void insert(String word) {

        TrieNode node = root;

        for(char ch : word.toCharArray()){

            int idx = ch - 'a';

            if(node.children[idx] == null){
                node.children[idx] = new TrieNode();
            }

            node = node.children[idx];
            node.countPrefix++;   // track prefix count
        }

        node.isEnd = true;
        node.endsWith++;
    }

    // SEARCH
    public boolean search(String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';

            if (node.children[idx] == null) return false;

            node = node.children[idx];
        }

        return node.isEnd;
    }

    // PREFIX
    public boolean startsWith(String prefix) {
        TrieNode node = root;

        for (char ch : prefix.toCharArray()) {
            int idx = ch - 'a';

            if (node.children[idx] == null) return false;

            node = node.children[idx];
        }

        return true;
    }

    // Count exact word
    public int countWordsEqualTo(String word){

        TrieNode node = root;

        for(char ch : word.toCharArray()){

            int idx = ch - 'a';

            if(node.children[idx] == null) return 0;

            node = node.children[idx];
        }
        return node.endsWith;
    }

    // Count prefix
    public int countWordsStartingWith(String prefix){

        TrieNode node = root;

        for(char ch : prefix.toCharArray()){

            int idx = ch - 'a';

            if(node.children[idx] == null) return 0;

            node = node.children[idx];
        }
        return node.countPrefix;
    }

    // Erase
    public void erase(String word){

        if(!search(word)) return;

        TrieNode node = root;

        for(char ch : word.toCharArray()){

            int idx = ch - 'a';

            node.children[idx].countPrefix--;
            node = node.children[idx];
        }
        node.endsWith--;
    }
}