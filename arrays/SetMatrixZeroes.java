/**
 * Problem:
 * <Set Matrix Zeroes>
 *
 * Link:
 * <https://leetcode.com/problems/set-matrix-zeroes/description/>
 *
 * Pattern:
 * <Matrix>
 *
 * Brute Force Intuition:
 * - Store the position of zeroes.
 * - Iterate through the position of zeroes stored.
 * - For every position stored, zero all the numbers in the row and zero all the elements in the col.
 *
 * - Why it is inefficient?
 * - Repeated work.
 * - Extra list.
 * - Not in-space optimal.
 *
 * Time Complexity:
 * - O((m * n) * (m + n))
 * Space Complexity:
 * - O(number_of_zeroes)
 *
 * Better Approach intuition:
 * - Row + column markers.
 * - Instead of storing all the coordinates, just remember:
 * - which row needs to be zeroes.
 * - which col needs to be zeroed.
 *
 * Time Complexity:
 * - O(m * n)
 * Space Complexity:
 * - O(m + n)
 *
 * Why this is still not optimal?
 * - Extra arrays, violate in-place constraint.
 *
 * Optimal Approach (Used below):
 * - Use the matrix itself as markers.
 * - first row -> column markers.
 * - first column -> row markers.
 * - Check if first row / col contains 0.
 * - Use first row / col as markers.
 * - Zero inner cells using markers.
 * - Handle first row / col separately.
 * - Use input array itself as storage.
 *
 * Time Complexity:
 * - O(m * n)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - The reason we handle first row / col separately, so that we don't overwrite them.
 */

package arrays;

import java.util.Arrays;

public class SetMatrixZeroes {
    public static void main(String[] args) {
        int[][] matrix = {{1,1,1}, {1,0,1}, {1,1,1}};
        setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    public static void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        boolean firstRowZero = false, firstColZero = false;

        for(int i = 0; i < n; i++){
            if(matrix[0][i] == 0){
                firstRowZero = true;
            }
        }

        for(int i = 0; i < m; i++){
            if(matrix[i][0] == 0){
                firstColZero = true;
            }
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        if(firstRowZero){
            for(int i = 0; i < n; i++){
                matrix[0][i] = 0;
            }
        }

        if(firstColZero){
            for(int i = 0; i < m; i++){
                matrix[i][0] = 0;
            }
        }
    }
}
