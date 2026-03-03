/**
 * Problem:
 * <Valid Sudoku>
 *
 * Link:
 * <https://leetcode.com/problems/valid-sudoku/description/>
 *
 * Pattern:
 * <Matrix + Hashing>
 *
 * Brute Force Intuition:
 * - For each character in the matrix:
 * - We check every row.
 * - We check every col.
 * - Every 3x3 grid.
 * - Formula to check the grid.
 * - boxRowStart = 3 * (i/3), boxColStart = 3 * (j/3)
 *
 * - Why it is inefficient?
 * - Re-scans row/col/box
 *
 * Time Complexity:
 * - O(1) {where n = 9. So O(1)}
 * Space Complexity:
 * - O(1)
 *
 * Better Approach intuition:
 * - Instead of checking the already checked rows and cols repeatedly, we:
 * - Remember the elements seen in that row.
 * - Remember the elements seen in that col.
 * - Identify the box index.
 * - Formula to identify the box index:
 * - boxIndex = (i/3) * 3 + (j/3).
 *
 * Time Complexity:
 * - O(1) {Single-pass over all the indices}
 * Space Complexity:
 * - O(1)
 *
 * Why this is still not optimal?
 * - Hashing adds overhead.
 *
 * Optimal Approach (Used below):
 * - We only need to check if the char is seen before in rows, cols or boxes.
 * - Use boolean[][] to check if the char is visited before.
 * - rows[i][num] - to check if the num had been seen before in the rows.
 * - cols[i][num] - to check if the num had been seen before in the cols.
 * - boxes[boxIndex][num] - to check if the num had been seen before in the boxes.
 *
 * Time Complexity:
 * - O(1).
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Using boolean[][] prevents object overhead and enables faster lookups.
 */

package hashing;

public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){

                char ch = board[i][j];

                if(ch == '.') continue;

                int num = ch - '1';
                int boxIndex = (i/3) * 3 + (j/3);

                if(rows[i][num] || cols[j][num] || boxes[boxIndex][num]) return false;

                rows[i][num] = true;
                cols[j][num] = true;
                boxes[boxIndex][num] = true;
            }
        }
        return true;
    }
}
