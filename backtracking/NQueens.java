/**
 * Problem:
 * <N-Queens>
 *
 * Link:
 * <https://leetcode.com/problems/n-queens/description/>
 *
 * Pattern:
 * <Permutation>
 *
 * Brute Force Intuition:
 * - Create a char[][] board.
 * - Try to place the queen in first columns of every row.
 * - After placing the queen in the first column:
 * - Try to place it in the 2nd column, if safe.
 * - To check if it is safe or not:
 * - Check if there is a queen placed before that columns in the current row.
 * - Check all the diagonals.
 * - Is it is safe:
 * - Place the queen and recurse to the next column.
 *
 * - Why it is inefficient?
 * - Each placement checks:
 * - Row check - O(N)
 * - Column check - O(N)
 * - Diagonal check - O(N)
 *
 * Time Complexity:
 * - O(N * N!)
 * Space Complexity:
 * - O(N^2) {board}
 *
 * Better Approach intuition:
 * - Avoid unnecessary checks.
 * - Each column can have only one queen, so column check is unnecessary.
 * - Queens are placed from left -> right, so we don't need to check right-upper and right-lower diagonal.
 *
 * Time Complexity:
 * - O(N * N!) {Single-pass over all the indices}
 * Space Complexity:
 * - O(N^2)
 *
 * Why this is still not optimal?
 * - Still rechecks the entire row and diagonals for safety.
 *
 * Optimal Approach (Used below):
 * - Instead of scanning, use hashing to see if the position for the placement of the queen is safe or not.
 * - Use 3 boolean arrays:
 * - rows[]
 * - lowerDiag[]
 * - upperDiag[]
 * - If it is safe to place the queen:
 * - Update:
 * - rows[row] = true.
 * - lowerDiag[row + col] = true.
 * - UpperDiag[(n - 1) + col - row] = true.
 * - Place the queen.
 * - Recurse.
 * - Backtrack.
 *
 *
 * Time Complexity:
 * - O(N!).
 * Space Complexity:
 * - O(N^2)
 *
 * Notes:
 * - lower diagonal = row + col
 * - upper diagonal = col - row (adjust for negative indices) = (n-1) + col - row.
 */

package backtracking;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class NQueens {

    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }

    public static List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];

        for(char[] row : board) Arrays.fill(row, '.');

        List<List<String>> ans = new ArrayList<>();

        boolean[] rows = new boolean[n];
        boolean[] lowerDiag = new boolean[2*n-1];
        boolean[] upperDiag = new boolean[2*n-1];

        canPlace(0, n, board, rows, lowerDiag, upperDiag, ans);
        return ans;
    }

    public static void canPlace(int col, int n, char[][] board, boolean[] rows,
                         boolean[] lowerDiag, boolean[] upperDiag, List<List<String>> ans){

        if(col == n){
            List<String> temp = new ArrayList<>();

            for(char[] arr : board) temp.add(new String(arr));

            ans.add(new ArrayList<>(temp));

            return;
        }

        for(int row = 0; row < n; row++){

            if(!rows[row] && !lowerDiag[row + col] && !upperDiag[(n-1) + col - row]){
                rows[row] = true;
                lowerDiag[row + col] =  true;
                upperDiag[(n-1) + col - row] = true;

                board[row][col] = 'Q';

                canPlace(col + 1, n, board, rows, lowerDiag, upperDiag, ans);

                board[row][col] = '.';
                rows[row] = false;
                lowerDiag[row + col] =  false;
                upperDiag[(n-1) + col - row] = false;
            }
        }
    }
}
