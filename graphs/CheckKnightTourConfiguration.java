/**
 * Problem:
 * <Check Knight Tour Configuration>
 *
 * Link:
 * <https://leetcode.com/problems/check-knight-tour-configuration/description/>
 *
 * Pattern:
 * <Simulation>
 *
 * Brute Force Intuition:
 * - DFS.
 * - For each position in the grid:
 * - Search for the next move of the knight.
 * - If the move is valid, update the current position of the knight.
 * - Check if you are able to make n*n-1 moves.
 *
 * - Why it is inefficient?
 * - The grid already contains the valid path.
 * - Instead of searching, we need to validate the existing path.
 *
 * Time Complexity:
 * - O(N^2) {Each cell is visited exactly once in the grid}
 * Space Complexity:
 * - O(N^2) {Recursion Depth}
 *
 * Better and Optimal Approach (used below):
 * - Instead of searching the next valid move of a knight, we:
 * - Validate the next possible move of a knight out of the 8 moves.
 * - Maintain a moves[][] which contains all the possible moves of a knight.
 * - For each step from 1 to n*n-1:
 *   Find the next valid move of a knight.
 *   Update the current position of a knight.
 *   Break and mark the found = true if a valid move is found.
 *   Else, the grid configuration is invalid.
 *
 * Time Complexity:
 * - O(N^2) {Each cell is visited exactly once in the grid}
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Check the base case:
 * - grid[0][0] != 0
 */

package graphs;

public class CheckKnightTourConfiguration {

    public static void main(String[] args) {
        System.out.println(checkValidGrid(new int[][]{{0,11,16,5,20}, {17,4,19,10,15}, {12,1,8,21,6},
                {3,18,23,14,9}, {24,13,2,7,22}}));
    }

    public static boolean checkValidGrid(int[][] grid) {
        if(grid[0][0] != 0) return false;
        int n = grid.length;

        int[][] moves = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

        int r = 0, c = 0;

        for(int steps = 1; steps < n*n; steps++){

            boolean found = false;

            for(int[] move : moves){
                int nr = r + move[0], nc = c + move[1];

                if(nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == steps){
                    r = nr;
                    c = nc;
                    found = true;
                    break;
                }
            }
            if(!found) return false;
        }
        return true;
    }
}
