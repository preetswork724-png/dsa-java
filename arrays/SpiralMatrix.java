/**
 * Problem:
 * <Spiral Matrix>
 *
 * Link:
 * <https://leetcode.com/problems/spiral-matrix/description/>
 *
 * Pattern:
 * <Matrix>
 *
 * Brute Force Intuition:
 * - Keep a boolean matrix to track all the visited cells.
 * - Start from (0,0) and move in 4 directions: right, down, left, up.
 * - Maintain dr = {0, 1, 0, -1} and dc = {1, 0, -1, 0}.
 * - Whenever you hit a boundary or a visited cell, change direction.
 * - To change the dir: dir = (dir + 1) % 4
 * - Continue until all r * c elements are added to the list.
 * - How do we prevent adding duplicates to the list?
 * - Because we never revisit a cell.
 * - In every iteration, (row, col) is guaranteed to be a new unvisited cell.
 * - Because in the previous iteration, we did:
 * - nextRow = row + dr[row], nextCol = col + dc[dir]
 * - If (nextRow is invalid or nextCol is invalid or visited[nextRow][nextCol]) { change direction }.
 * - row = nextRow, col = nextCol.
 * - So, (row, col) is updated only after ensuring:
 * - They are in bounds.
 * - They are unvisited.
 *
 * - Why it is inefficient?
 * - We explicitly track every cell using extra memory to avoid revisits, instead of using smart boundary shrinking.
 *
 * Time Complexity:
 * - O(r * c)
 * Space Complexity:
 * - O(r * c)
 *
 * Better an Optimal Approach (Used below):
 * - Make use of 4 pointers, where each pointer denotes a boundary.
 * - Move the boundary or update the pointers as soon as a boundary is printed or all the boundary elements are added to the list.
 * - Repeat the process until the boundary becomes invalid.
 *
 * Time Complexity:
 * - O(r * c)
 * Space Complexity:
 * - O(1)
 *
 * Why better approach is the optimal one?
 * - Because we need to traverse each element in the matrix at least once.
 * - This is already done in-place with smart boundary shrinking.
 *
 * Notes:
 * - In each iteration of the outer loop, after updating a pair of boundaries,
 * - Always verify that the remaining boundaries are still valid before traversing them.
 * - This prevents revisiting the elements, out-of-bounds, duplicate additions to the list where only a single row or a single column exists.
 */

package arrays;
import java.util.List;
import java.util.ArrayList;
public class SpiralMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.println(spiralOrder(matrix));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiral = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length, top = 0, bottom = m-1, left = 0, right = n-1;

        while(top <= bottom && left <= right){

            for(int i = left; i <= right; i++){
                spiral.add(matrix[top][i]);
            }
            top++;

            for(int i = top; i <= bottom; i++){
                spiral.add(matrix[i][right]);
            }
            right--;

            if(top <= bottom){

                for(int i = right; i >= left; i--){
                    spiral.add(matrix[bottom][i]);
                }
                bottom--;
            }

            if(left <= right){

                for(int i = bottom; i >= top; i--){
                    spiral.add(matrix[i][left]);
                }
                left++;
            }

        }
        return spiral;
    }
}
