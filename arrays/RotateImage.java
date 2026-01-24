/**
 * Problem:
 * <Rotate Image>
 *
 * Link:
 * <https://leetcode.com/problems/rotate-image/description/>
 *
 * Pattern:
 * <Matrix>
 *
 * Brute Force Intuition:
 * - Transpose -> Reverse each row.
 * - Create a new matrix and fill it by swapping indices.
 * - Create another matrix to store the elements of the row in reversed order.
 * - Iterate over each row and reverse the elements in the row.
 * - Store the elements in the newly created matrix.
 *
 * - Why it is inefficient?
 * - We blindly use a full extra matrix without considering in-place possibilities.
 *
 * Time Complexity:
 * - O(n^2)
 * Space Complexity:
 * - O(n^2) {Uses two extra matrices}
 *
 * Better Approach intuition:
 * - As it is a square matrix, transpose the matrix in place.
 * - Create a new matrix to store the elements of each row in the reversed order.
 * - Iterate over each row and reverse the elements in the row.
 * - Store the elements in the newly created matrix.
 *
 * Time Complexity:
 * - O(n^2)
 * Space Complexity:
 * - O(n^2) {Uses only one extra matrix}
 *
 * Why this is still not optimal?
 * - Still uses an extra matrix to store the reverse of matrix.
 *
 * Optimal Approach (Used below):
 * - Do in-place transpose of the matrix.
 * - Reverse the elements of each row in-place using 2-pointer technique.
 *
 * Time Complexity:
 * - O(n^2)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - If it is a square matrix, in-place transpose is the optimal solution.
 * - If it is a rectangular matrix, we need to create a new matrix and fill it by swapping indices.
 */

package arrays;

import java.util.Arrays;

public class RotateImage {

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
        rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    public static void rotate(int[][] matrix) {
        matrix = transpose(matrix);

        for (int[] row : matrix) {
            reverse(row);
        }
    }

    public static int[][] transpose(int[][] matrix) {
        int n = matrix.length;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        return matrix;
    }

    public static void reverse(int[] row){
        int left = 0, right = row.length-1;

        while(left < right){
            int temp = row[left];
            row[left] = row[right];
            row[right] = temp;
            left++;
            right--;
        }
    }

}
