/**
 * Problem:
 * <The Knight's tour problem>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/the-knights-tour-problem/1>
 *
 * Pattern:
 * <Backtracking / DFS on grid>
 *
 * Brute Force Intuition:
 * - The knight starts from (0, 0) on an n x n chessboard.
 * - The knight must visit every cell exactly once.
 * - At any position, the knight has 8 possible moves:
 *   (r+2,c+1), (r+2,c-1), (r-2,c+1), (r-2,c-1),
 *   (r+1,c+2), (r+1,c-2), (r-1,c+2), (r-1,c-2).
 * - For each move:
 * - Check if the next cell is within the grid and not visited.
 * - Mark the cell with the current number of steps.
 * - Recursively attempt the next move.
 * - If the knight visits exactly n*n-1 steps:
 * - It means all cells are visited exactly once.
 * - If none of this leads to a valid solution:
 * - Undo the current move, backtrack and try the next move possible.
 *
 * - Why it is inefficient?
 * - Each step explores into 8 possible moves.
 * - Recursion explores many invalid paths before it finds a valid tour.
 *
 * Time Complexity:
 * - O(8^(N^2))
 * Space Complexity:
 * - O(N^2) {Recursion Depth}
 *
 * Better and Optimal Approach (used below):
 * - Instead of exploring the moves randomly:
 * - Heuristics like Warnsdorff's rule can be applied.
 * - The idea is to always move to the next square with minimum onward moves.
 * - Maintain a board[][] initialized with -1 to indicate the unvisited cells.
 * - Mark board[r][c] with steps when a knight visits a cell.
 * - After exploring a move, reset the cell to -1 while backtracking.
 * - Stop recursion immediately when a valid tour is found.
 *
 * Time Complexity:
 * - O(8^(N^2)) {Each cell is visited exactly once in the grid}
 * Space Complexity:
 * - O(N^2)
 *
 * Notes:
 * - For the brute force:
 * - Time Complexity:
 * - O(8^(N²))
 * - For the heuristic approach:
 * - Practical Complexity:
 *   ≈ O(N²)
 * - Worst Case:
 * - Still exponential
 */

package graphs;
import java.util.ArrayList;
import java.util.Arrays;
public class TheKnightsTourProblem {

    static int[][] moves = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

    public static void main(String[] args) {
        System.out.println();
    }

    public static ArrayList<ArrayList<Integer>> knightTour(int n) {

        int[][] board = new int[n][n];

        for(int[] arr : board) Arrays.fill(arr, -1);

        board[0][0] = 0;

        if(!dfs(board, 0, 0, n, 1)) return new ArrayList<>();

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        for(int[] arr : board){
            ArrayList<Integer> temp = new ArrayList<>();
            for(int x : arr) temp.add(x);
            res.add(new ArrayList<>(temp));
        }
        return res;
    }

    public static boolean dfs(int[][] board, int cr, int cc, int n, int steps){

        if(steps == n*n) return true;

        for(int[] move : moves){

            int nr = cr + move[0];
            int nc = cc + move[1];

            if(nr >= 0 && nr < n && nc >= 0 && nc < n && board[nr][nc] == -1){
                board[nr][nc] = steps;

                if(dfs(board, nr, nc, n, steps + 1)) return true;

                board[nr][nc] = -1;
            }
        }
        return false;
    }
}
