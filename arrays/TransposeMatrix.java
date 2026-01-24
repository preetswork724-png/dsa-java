/**
 * Problem:
 * <Transpose Matrix>
 *
 * Link:
 * <https://leetcode.com/problems/transpose-matrix/description/>
 *
 * Pattern:
 * <Matrix>
 *
 * Brute Force Intuition:
 * - Create a new matrix and fill it by swapping indices.
 * - ans[j][i] = matrix[i][j]
 *
 * - Why it is inefficient?
 * - We blindly use a full extra matrix without considering in-place possibilities.
 *
 * Time Complexity:
 * - O(m * n)
 * Space Complexity:
 * - O(m * n)
 *
 * Better Approach intuition:
 * - If m == n (square matrix), we can transpose in-place using diagonal swapping.
 * - Swap matrix[i][j] = matrix[j][i] for i < j .
 *
 * Time Complexity:
 * - O(n^2)
 * Space Complexity:
 * - O(1)
 *
 * Why this is still not optimal?
 * - Works only for square matrices.
 *
 * Optimal Approach (Used below):
 * - For rectangular matrices (m != n):
 * - In-place transpose is practically impossible without reshaping the memory.
 * - So, the current brute force solution is already optimal.
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

public class TransposeMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.println(Arrays.deepToString(transpose(matrix)));
    }

    public static int[][] transpose(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        int[][] ans = new int[n][m];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                ans[j][i] = matrix[i][j];
            }
        }
        return ans;
    }
}
