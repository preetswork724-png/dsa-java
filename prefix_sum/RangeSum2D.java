/**
 * Problem:
 * <Range Sum Query 2D - Immutable>
 *
 * Link:
 * <https://leetcode.com/problems/range-sum-query-2d-immutable/>
 *
 * Pattern:
 * <2D Prefix Sum>
 *
 * -----------------------------------------------------
 *
 * Brute Force Intuition:
 * - For each query (row1, col1) → (row2, col2):
 *      Iterate over all cells inside the rectangle.
 *      Sum them up manually.
 *
 * - Why it is inefficient?
 * - Each query takes O(rows * cols) in worst case.
 * - If multiple queries exist, total time becomes very large.
 *
 * Time Complexity:
 * - O(Q * rows * cols)
 *
 * Space Complexity:
 * - O(1)
 *
 * -----------------------------------------------------
 *
 * Better Approach Intuition:
 * - Precompute row-wise prefix sums.
 * - For each row, store cumulative sum of columns.
 *
 * - For a query:
 *      Sum each row from col1 → col2 using prefix.
 *
 * - Why it is still not optimal?
 * - Each query still takes O(rows) time.
 * - Not efficient when number of queries is large.
 *
 * Time Complexity:
 * - Preprocessing: O(rows * cols)
 * - Query: O(rows)
 *
 * Space Complexity:
 * - O(rows * cols)
 *
 * -----------------------------------------------------
 *
 * Optimal Approach (Used Below):
 * - Use 2D Prefix Sum Matrix.
 *
 * - Key Idea:
 *      prefix[i][j] stores sum of rectangle from (0,0) → (i-1, j-1)
 *
 * - Build prefix matrix:
 *
 *      prefix[i][j] =
 *          matrix[i-1][j-1]
 *        + prefix[i-1][j]
 *        + prefix[i][j-1]
 *        - prefix[i-1][j-1]
 *
 * - Why subtract prefix[i-1][j-1]?
 *      To remove double-counted overlap.
 *
 * - Query formula:
 *
 *      sumRegion(row1, col1, row2, col2) =
 *          prefix[row2+1][col2+1]
 *        - prefix[row1][col2+1]
 *        - prefix[row2+1][col1]
 *        + prefix[row1][col1]
 *
 * - Intuition:
 *      Big rectangle
 *      - remove top
 *      - remove left
 *      + add overlap back
 *
 * - Steps:
 *      1. Build prefix matrix of size (rows+1 x cols+1)
 *      2. Use inclusion-exclusion to answer queries
 *
 * -----------------------------------------------------
 *
 * Time Complexity:
 * - Preprocessing: O(rows * cols)
 * - Query: O(1)
 *
 * Space Complexity:
 * - O(rows * cols)
 *
 * -----------------------------------------------------
 *
 * Why optimal approach is the best?
 * - Converts repeated submatrix summation into constant time queries.
 * - Avoids recomputation completely.
 * - Ideal for multiple queries on static matrix.
 *
 * -----------------------------------------------------
 *
 * Notes:
 * - Extra row and column simplify boundary handling.
 * - prefix[0][*] = 0 and prefix[*][0] = 0 act as base case.
 * - Works using inclusion-exclusion principle.
 * - Useful in:
 *      - submatrix sum queries
 *      - image processing (summed-area tables)
 *      - grid DP optimizations
 *
 * -----------------------------------------------------
 *
 * Mistake Log:
 * - Forgot to subtract overlap (double counting bug)
 * - Off-by-one errors in indices
 * - Confused matrix index vs prefix index
 * - Used 0-based prefix instead of 1-based (causes edge issues)
 *
 */

package prefix_sum;

public class RangeSum2D {

}

class NumMatrix {

    private int[][] prefix;   // prefix sum matrix
    private int rows, cols;   // to store matrix dimensions

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            prefix = new int[1][1];
            return;
        }

        this.rows = matrix.length;
        this.cols = matrix[0].length;
        processPrefix(matrix);
    }

    public void processPrefix(int[][] matrix){
        prefix = new int[rows + 1][cols + 1]; // one extra row & col for easy calculation

        for(int i = 1; i <= rows; i++){
            for(int j = 1; j <= cols; j++){
                prefix[i][j] = matrix[i - 1][j - 1] + prefix[i - 1][j] +
                        prefix[i][j - 1] - prefix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = prefix[row2 + 1][col2 + 1] - prefix[row2 + 1][col1]
                - prefix[row1][col2 + 1] + prefix[row1][col1];
        return sum;
    }
}
