/**
 * Problem:
 * <Spiral Matrix II>
 *
 * Link:
 * <https://leetcode.com/problems/spiral-matrix-ii/description/>
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
 * - As you iterate in the matrix, fill that position with num and increment num.
 * - How do we prevent adding duplicates to the list?
 *
 * - Why it is inefficient?
 * - We explicitly track every cell using extra memory to avoid revisits, instead of using smart boundary shrinking.
 *
 * Time Complexity:
 * - O(n^2)
 * Space Complexity:
 * - O(n^2)
 *
 * Better and Optimal Approach (Used below):
 * - Make use of 4 pointers, where each pointer denotes a boundary.
 * - Move the boundary or update the pointers as soon as a position is allocated with a num.
 * - Repeat the process until the boundary becomes invalid.
 *
 * Time Complexity:
 * - O(n^2)
 * Space Complexity:
 * - O(n^2) {output}, O(1) {auxiliary}
 *
 * Notes:
 * - In each iteration of the outer loop, after updating a pair of boundaries,
 * - Always verify that the remaining boundaries are still valid before traversing them.
 * - This prevents revisiting the positions, out-of-bounds, duplicate allocations of num to the position where only a single row or a single column exists.
 */

package arrays;

import java.util.Arrays;

public class SpiralMatrixII {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(generateMatrix(3)));
    }

    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int top = 0, bottom = n-1, left = 0, right = n-1, num = 1;

        while(top<=bottom && left<=right)
        {
            for(int i = left ; i<=right ; i++)
            {
                matrix[top][i] = num++;
            }
            top++;

            for(int i = top ; i<=bottom ; i++)
            {
                matrix[i][right]=num++;

            }
            right--;
            if(top<=bottom)
            {
                for(int i = right ; i>=left  ; i--)
                {
                    matrix[bottom][i] = num++;

                }
                bottom--;
            }

            if(left<=right)
            {
                for(int i = bottom ; i>=top ; i--)
                {
                    matrix[i][left] = num++;
                }
                left++;
            }
        }
        return matrix;
    }
}

