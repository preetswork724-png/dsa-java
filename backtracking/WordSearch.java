/**
 * Problem:
 * <Word Search>
 *
 * Link:
 * <https://leetcode.com/problems/word-search/>
 *
 * Pattern:
 * <Backtracking + DFS on Grid>
 *
 *
 * Core Intuition:
 * - We need to check if a given word exists in the grid.
 * - The word must be formed by sequentially adjacent cells
 *   (up, down, left, right).
 * - Each cell can be used only once in a path.
 *
 * - Key idea:
 *   → Try to match the word character by character using DFS.
 *
 *
 * Key Understanding:
 * - This is NOT a generation problem.
 * - We do NOT generate all possible words.
 * - We MATCH the given word using guided DFS.
 *
 *
 * Approach:
 *
 * 1. Start DFS from every cell:
 *    - For each cell (i, j):
 *        → Try to match word starting from index 0
 *
 *
 * 2. DFS Logic:
 *    - Base case:
 *        → If index == word.length → return true
 *
 *    - Invalid case:
 *        → Out of bounds
 *        → Character mismatch
 *
 *    - Mark current cell as visited:
 *        → Temporarily change value (e.g., '#')
 *
 *    - Explore 4 directions:
 *        → up, down, left, right
 *
 *    - Backtrack:
 *        → Restore original character
 *
 *
 * 3. Return:
 *    - If any DFS path returns true → answer is true
 *
 *
 * Why This Works:
 * - DFS explores all possible paths starting from each cell.
 * - Pruning happens when characters don’t match.
 * - Backtracking ensures we reuse cells correctly for other paths.
 *
 *
 * Time Complexity:
 * - O(N * M * 4^L)
 *   (N*M starting points, 4 directions, L = word length)
 *
 *
 * Space Complexity:
 * - O(L) recursion stack
 *
 *
 * Notes:
 * - We modify the board in-place to mark visited cells.
 * - Always restore the cell (backtracking step).
 * - No extra visited array needed.
 *
 *
 * Common mistakes:
 * - Not restoring the board after DFS
 * - Returning true unconditionally
 * - Not checking bounds properly
 * - Reusing the same cell in one path
 *
 *
 * Mental model:
 * - "Try to match word step-by-step in the grid"
 * - "If mismatch → stop exploring that path"
 * - "Backtrack and try another direction"
 *
 *
 * Edge cases:
 * - Empty grid
 * - Word length = 1
 * - Word longer than total cells
 *
 *
 * Key takeaway:
 * - Backtracking is used to explore VALID paths only,
 *   not to generate all possibilities.
 *
 *
 * Follow-up:
 * - Word Search II (Trie + DFS + pruning)
 * - Number of Islands (DFS on grid)
 * - Path existence problems
 */
package backtracking;

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int n = board.length, m = board[0].length;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {
                if (dfs(i, j, n, m, board, word, 0)) return true;
            }
        }
        return false;
    }

    public boolean dfs(int i, int j, int n, int m,
                       char[][] board, String word, int index) {

        if (index == word.length()) return true;

        if (i < 0 || i >= n || j < 0 || j >= m ||
                board[i][j] != word.charAt(index)) return false;

        char temp = board[i][j];
        board[i][j] = '#';

        boolean found =
                dfs(i + 1, j, n, m, board, word, index + 1) ||
                        dfs(i - 1, j, n, m, board, word, index + 1) ||
                        dfs(i, j + 1, n, m, board, word, index + 1) ||
                        dfs(i, j - 1, n, m, board, word, index + 1);

        board[i][j] = temp;
        return found;
    }
}
