/**
 * Problem:
 * <Word Search II>
 *
 * Link:
 * <https://leetcode.com/problems/word-search-ii/>
 *
 * Pattern:
 * <Trie + Backtracking (DFS) + Pruning>
 *
 *
 * Brute Force Intuition:
 * - Start DFS from every cell.
 * - Generate all possible strings by exploring 4 directions.
 * - Store all generated strings.
 * - Compare each with given words list.
 *
 * - Why it is inefficient?
 * - Exponential number of paths (4^(m*n)).
 * - Generates many unnecessary strings.
 * - High memory usage (storing all substrings).
 * - Repeated comparisons with dictionary.
 *
 * Time Complexity:
 * - O(4^(m*n))
 *
 * Space Complexity:
 * - Very high (due to storing all generated strings)
 *
 *
 * Better Approach Intuition:
 * - For each word:
 *     - Start DFS from each cell.
 *     - Try to match characters one by one.
 *
 * - Why it is better?
 * - Avoids generating all possible strings.
 * - Only explores paths relevant to a word.
 *
 * - Why it is still bad?
 * - Repeats DFS for each word.
 * - Common prefixes are recomputed multiple times.
 *
 * Example:
 * - words = ["cat", "car", "cap"]
 * - "ca" path explored 3 times
 *
 * Time Complexity:
 * - O(W * M * N * 4^L)
 *
 * Space Complexity:
 * - O(L) recursion stack
 *
 *
 * Better++ Approach (Trie + DFS - Used Below):
 * - Build a Trie from all words.
 *
 * - Start DFS from each cell:
 *     - Move character by character.
 *     - Check if current path exists in Trie.
 *
 *     - If:
 *         - child not present → stop (prune path)
 *         - node.word != null → word found
 *
 * - Why this works?
 * - Shared prefixes are stored once in Trie.
 * - Avoids repeated DFS for same prefixes.
 * - Early pruning reduces search space drastically.
 *
 * - Key Insight:
 * - "DFS explores paths, Trie validates paths in real-time"
 *
 * Time Complexity:
 * - Trie build: O(W * L)
 * - DFS: O(M * N * 4^L) (pruned heavily)
 *
 * Space Complexity:
 * - O(W * L) (Trie)
 * - O(L) recursion stack
 *
 *
 * Optimal Approach (Advanced Optimization):
 * - Remove leaf nodes from Trie after word is found.
 *
 * - Idea:
 * - Once a word is found and no longer needed:
 *     - Delete nodes if they have no children
 *
 * - Why it helps?
 * - Further reduces search space
 * - Speeds up future DFS calls
 *
 * Time Complexity:
 * - Still O(W * L + M * N * 4^L), but faster in practice
 *
 *
 * Notes:
 * - Use Trie to store all words efficiently.
 * - Store full word at end node instead of building strings.
 *
 * - Important optimizations:
 *     - node.word = null → avoid duplicates
 *     - board[i][j] = '#' → mark visited
 *     - Prune when Trie path does not exist
 *
 * - Common mistakes:
 * - Using Set<Character> instead of visited matrix
 * - Generating all strings instead of pruning
 * - Not stopping when Trie path is invalid
 * - Forgetting to backtrack
 *
 * - Mental model:
 * - "Only explore paths that can form valid words"
 *
 * - Edge cases:
 * - Empty board → return []
 * - No matching words → return []
 * - Duplicate words in input → handled by Trie
 *
 * - Key takeaway:
 * - Trie + DFS transforms exponential brute force into pruned search
 * - Major gain comes from prefix sharing and early stopping
 */

package trees._10_SpecialTrees.Trie;

import java.util.List;
import java.util.ArrayList;

public class WordSearchII {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    TrieNode root = new TrieNode();

    public List<String> findWords(char[][] board, String[] words) {
        for (String word : words) {
            insert(word);
        }

        int m = board.length, n = board[0].length;

        List<String> res = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, m, n, board, root, res);
            }
        }
        return res;
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
        node.word = word;
    }

    public void dfs(int row, int col, int m, int n, char[][] board, TrieNode node,
                    List<String> res) {

        char ch = board[row][col];

        if (ch == '#' || node.children[ch - 'a'] == null) return;

        node = node.children[ch - 'a'];

        board[row][col] = '#';

        if (node.word != null) {
            res.add(node.word);
            node.word = null;
        }

        if (row + 1 < m) dfs(row + 1, col, m, n, board, node, res);
        if (row - 1 >= 0) dfs(row - 1, col, m, n, board, node, res);
        if (col + 1 < n) dfs(row, col + 1, m, n, board, node, res);
        if (col - 1 >= 0) dfs(row, col - 1, m, n, board, node, res);

        board[row][col] = ch;
    }
}
