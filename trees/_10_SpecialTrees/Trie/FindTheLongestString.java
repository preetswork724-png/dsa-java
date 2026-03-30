/**
 * Problem:
 * <Longest Word with All Prefixes>
 *
 * Link:
 * <https://leetcode.com/problems/longest-word-in-dictionary/>
 *
 * Pattern:
 * <Strings + Trie + Prefix Validation>
 *
 *
 * Core Intuition:
 * - We need to find the longest word such that:
 *     → ALL its prefixes exist in the given array.
 *
 * - Example:
 *     words = ["a", "ap", "app", "appl", "apple"]
 *     → "apple" is valid because:
 *         "a", "ap", "app", "appl" all exist
 *
 *
 * Key Understanding:
 * - We are NOT comparing words with each other.
 * - We are validating a single word using the dictionary.
 *
 *
 * Approaches:
 *
 * 1. Brute Force:
 *    - For each word:
 *        → Generate all prefixes.
 *        → For each prefix:
 *            → Scan entire array to check existence.
 *
 *    - Time Complexity:
 *        → O(N^2 * L^2)
 *
 *    - Issue:
 *        → Repeated scanning of array for every prefix.
 *
 *
 * 2. Better Approach (HashSet):
 *    - Store all words in a HashSet.
 *
 *    - For each word:
 *        → Generate all prefixes.
 *        → Check existence using set.contains(prefix).
 *
 *    - Key Insight:
 *        → Existence check (not frequency).
 *
 *    - Time Complexity:
 *        → O(N * L^2)
 *
 *    - Why better?
 *        → Prefix lookup becomes O(1).
 *
 *
 * 3. Optimized Approach (Sorting + Set):
 *    - Sort words lexicographically.
 *
 *    - Maintain a set of valid words.
 *
 *    - For each word:
 *        → If:
 *            word.length == 1 OR
 *            prefix (word[0..len-2]) exists in set
 *
 *        → Then:
 *            add word to set
 *            update answer if longer
 *
 *    - Key Insight:
 *        → Build valid words incrementally
 *        → Avoid checking all prefixes
 *
 *    - Time Complexity:
 *        → O(N log N + N * L)
 *
 *
 * 4. Optimal Approach (Trie + DFS):
 *
 *    Step 1: Build Trie
 *    Step 2: Traverse using DFS
 *
 *    - Only move forward if:
 *        → child != null AND child.isEnd == true
 *
 *    - This ensures:
 *        → All prefixes are valid automatically
 *
 *    - During traversal:
 *        → build string
 *        → update answer if:
 *            - longer OR
 *            - same length but lexicographically smaller
 *
 *
 *    - Why Trie works?
 *        → It converts prefix validation into traversal constraint
 *
 *
 *    - Time Complexity:
 *        → O(N * L)
 *
 *    - Space Complexity:
 *        → O(N * L)
 *
 *
 * Key Observations:
 * - Prefix validation = existence check (not count).
 * - HashSet helps reduce lookup time.
 * - Trie eliminates prefix checking entirely.
 *
 *
 * Common mistakes:
 * - Checking how many words share prefix.
 * - Breaking on mismatch instead of match.
 * - Using isEnd in wrong place.
 * - Forgetting lexicographical condition.
 *
 *
 * Mental model:
 * - Brute → check prefixes manually
 * - HashSet → fast lookup
 * - Trie → only explore valid prefixes
 *
 *
 * Edge cases:
 * - No valid word → return ""
 * - Multiple answers → return lexicographically smallest
 * - Single character words
 *
 *
 * Key takeaway:
 * - Trie turns validation into traversal → avoids redundant work
 *
 *
 * Follow-up:
 * - Word Search II (Trie + DFS + pruning)
 * - Replace Words
 * - Word Break
 */

package trees._10_SpecialTrees.Trie;

public class FindTheLongestString {
    TrieNode root = new TrieNode();

    public String longestString(String[] words) {

        for(String word : words) insert(word);

        String[] ans = new String[1];
        ans[0] = "";

        dfs(root, new StringBuilder(), ans);
        return ans[0];
    }

    public void dfs(TrieNode node, StringBuilder curr, String[] ans){

        for(int i = 0; i < 26; i++){

            TrieNode child = node.children[i];

            if(child != null && child.isEnd){

                char ch = (char)(i + 'a');
                curr.append(ch);

                String word = curr.toString();

                if(word.length() > ans[0].length() ||
                        word.length() == ans[0].length() && word.compareTo(ans[0]) < 0){
                    ans[0] = word;
                }

                dfs(child, curr, ans);

                curr.deleteCharAt(curr.length() - 1);

            }

        }
    }

    public void insert(String word){

        TrieNode node = root;

        for(char ch : word.toCharArray()){

            int idx = ch - 'a';

            if(node.children[idx] == null){
                node.children[idx] = new TrieNode();
            }

            node = node.children[idx];
        }
        node.isEnd = true;
    }
}
