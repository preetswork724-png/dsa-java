/**
 * Problem:
 * <Sudoku Solver>
 *
 * Link:
 * <https://leetcode.com/problems/sudoku-solver/>
 *
 * Brute Force intuition:
 * - Traverse board
 * - If empty cell found:
 *   Try digits 1–9
 *   If safe:
 *   Place
 *   Recurse
 * - If recursion returns true → stop
 * - Else undo
 * - If no digit works → return false
 * - If no empty cells left → return true
 *
 * Why it is inefficient?
 * - Scans the entire row, col and grid when we replace an empty cell with a char.
 * - That's like O(27) checks for each character.
 *
 * Time Complexity:
 * - O(9^81) {if the board is completely empty else O(9^n) {where n = number of empty cells}}
 *
 * Space Complexity:
 * - O(1)
 *
 * Better Approach intuition:
 * - Reduce the O(27) scans to true O(1) scan.
 * - Keep boolean[][] to track if the number is already seen.
 * - rows[r][num] = true -> means that the number is already seen in the row.
 * - cols[c][num] = true -> means that the number is already seen in the col.
 * - box[boxIndex][num] = true -> means that the number is already seen in the 3x3 box.
 * - Formula for boxIndex:
 * - boxIndex = (row/3) * 3 + (col/3).
 *
 * Time Complexity:
 * - O(9^N)
 * Space Complexity:
 * - O(N) {Recursion depth}
 *
 * Why this is still not optimal?
 * - We choose an empty cell with 7 possible values.
 * - This explodes the recursion tree.
 *
 * Optimal Approach (used below):
 * - MRV (Minimum Remaining Values) approach:
 * - Instead of choosing an empty cell, we choose an empty ell with the fewest possible number values.
 * - We choose an empty cell with possibly 1 or 2 possible values.
 * - If it fails -> fail early.
 * - Massive pruning.
 * - At each recursion level:
 * - Scan the entire board.
 * - For an empty cell:
 * - Count how many valid numbers are possible.
 * - Choose the cell with minimum count.
 * - Try those values only.
 * - Backtrack.
 *
 * Time Complexity:
 * - O(9^N)
 * Space Complexity:
 * - O(N) {Recursion depth}
 *
 * Notes
 * - The worst case complexity for the optimal approach is still O(9^N) but it is much faster in runtime as it prunes early.
 */

package backtracking;

public class SudokuSolver {

    boolean[][] rows = new boolean[9][10];
    boolean[][] cols = new boolean[9][10];
    boolean[][] boxes = new boolean[9][10];

    public void solveSudoku(char[][] board) {

        // Initialize tracking arrays
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {

                if (board[r][c] != '.') {
                    int num = board[r][c] - '0';
                    int box = (r / 3) * 3 + (c / 3);

                    rows[r][num] = true;
                    cols[c][num] = true;
                    boxes[box][num] = true;
                }
            }
        }

        solve(board);
    }

    private boolean solve(char[][] board) {

        int minOptions = 10;
        int targetRow = -1;
        int targetCol = -1;

        // 🔎 Find cell with minimum remaining values
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {

                if (board[r][c] == '.') {

                    int box = (r / 3) * 3 + (c / 3);
                    int count = 0;

                    for (int num = 1; num <= 9; num++) {
                        if (!rows[r][num] &&
                                !cols[c][num] &&
                                !boxes[box][num]) {
                            count++;
                        }
                    }

                    if (count < minOptions) {
                        minOptions = count;
                        targetRow = r;
                        targetCol = c;
                    }
                }
            }
        }

        // ✅ No empty cell left → solved
        if (targetRow == -1) return true;

        int box = (targetRow / 3) * 3 + (targetCol / 3);

        // Try only valid values
        for (int num = 1; num <= 9; num++) {

            if (!rows[targetRow][num] &&
                    !cols[targetCol][num] &&
                    !boxes[box][num]) {

                board[targetRow][targetCol] = (char)(num + '0');
                rows[targetRow][num] = true;
                cols[targetCol][num] = true;
                boxes[box][num] = true;

                if (solve(board)) return true;

                board[targetRow][targetCol] = '.';
                rows[targetRow][num] = false;
                cols[targetCol][num] = false;
                boxes[box][num] = false;
            }
        }

        return false;
    }

}
