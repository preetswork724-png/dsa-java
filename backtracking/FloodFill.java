/**
 * Problem:
 * <Flood Fill>
 *
 * Link:
 * <https://leetcode.com/problems/flood-fill/description/>
 *
 * Approach:
 * - This is a backtracking problem
 * - We identify the original color at image[sr][sc].
 * - Edge case :
 * - If originalColor == color return image.
 * - Because nothing changes.
 * - Starting from the sr and sc:
 * - We expand to the cells adjacent to the sr and sc.
 * - At every recursion level:
 * - Bounds are checked.
 * - A cell is filled with color if it is in bounds and it's color is same as the original color.
 *
 * Time Complexities:
 * - O(M * N) {Each cell is visited once}
 *
 * Space Complexity:
 * - O(M * N) {Recursion Stack}
 *
 * Notes:
 * - There is a template that almost solves every grid DFS problems.
 * - Always convert a grid problem into a graph problem.
 *   Think of each cell as a node.
 *   Each node connects to 4 neighbors.
 *   So every grid problem becomes: Graph traversal -> BFS or DFS
 * - Template:
 *   void dfs(int r, int c){
 *
 *     if(r < 0 || c < 0 || r >= rows || c >= cols)
 *         return;
 *
 *     if(invalid cell)
 *         return;
 *
 *     mark visited
 *
 *     dfs(r+1, c);
 *     dfs(r-1, c);
 *     dfs(r, c+1);
 *     dfs(r, c-1);
 * }
 */

package backtracking;

import java.util.Arrays;

public class FloodFill {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(floodFill(new int[][]{{1,1,1}, {1,1,0}, {1,0,1}},
                1, 1, 2)));
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int ogColor = image[sr][sc];
        if(ogColor == color) return image;

        dfs(image, sr, sc, image.length, image[0].length, color, ogColor);
        return image;
    }

    public static void dfs(int[][] image, int r, int c, int m, int n, int color, int ogColor){

        if(r < 0 || c < 0 || r >= m || c >= n || image[r][c] != ogColor) return;

        image[r][c] = color;
        dfs(image, r + 1, c, m, n, color, ogColor);
        dfs(image, r - 1, c, m, n, color, ogColor);
        dfs(image, r, c + 1, m, n, color, ogColor);
        dfs(image, r, c - 1, m, n, color, ogColor);
    }
}
