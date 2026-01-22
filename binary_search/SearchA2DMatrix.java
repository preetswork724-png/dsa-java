/**
 * Problem:
 * <Search a 2D Matrix>
 *
 * Link:
 * <https://leetcode.com/problems/search-a-2d-matrix/description/>
 *
 * Pattern:
 * <binary search on matrix>
 *
 * Brute Force Intuition:
 * - Linearly pick the row.
 * - Apply binary search on the row to find target.
 *
 * - Why it is inefficient?
 * - Brute force requires O(N * log M) time to find the target in the matrix.
 * - Loss of information as the rows in the matrix are sorted in non-decreasing order.
 *
 * Time Complexity:
 * - O(N * log M)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach intuition:
 * - Since each row is sorted and the first element of a row is greater than the last element of the previous row, the matrix can be viewed as a sorted sequence of rows.
 * - Binary search in 2 phases.
 * - Perform binary search on the rows using the last element of each row to identify the only possible row in which the target can exist.
 * - Once the candidate row is found, apply binary search within that row.
 *
 * Time Complexity:
 * - O(log M + log N)
 * Space Complexity:
 * - O(1)
 *
 * Why this is still not optimal?
 * - The above code implements binary search iin 2 phases which is already optimal.
 * - But a cleaner approach would be to apply binary search once.
 *
 * Optimal Approach (Used below):
 * - 1D flatten binary search trick:
 * - The matrix behaves like a single sorted array of size M * N.
 * - Treat the matrix as a virtual 1D sorted array.
 * - Apply binary search on indices 0...M * N-1.
 * - Covert mid to (row, col) using division and modulo.
 * - Compute matrix[row][col] with target and shrink the space.
 *
 * Time Complexity:
 * - O(log M*N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Asymptotically, the time complexities for both the better and optimal approach are equal.
 * - There is no Big-OSearch a 2D Matrix difference.
 * - The flattened 1D binary search performs a single logarithmic search over teh entire matrix, making the algorithm conceptually simpler.
 * - Smaller constant factors as compared to the two-phase binary search.
 */
package binary_search;

public class SearchA2DMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7}, {10,11,16,20}, {23,30,34,60}};
        System.out.println(searchMatrix(matrix, 5));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, low = 0, high = n - 1, index = -1;

        while(low <= high){

            int mid = low + (high - low) / 2;
            int m = matrix[mid].length - 1;

            if(target == matrix[mid][m]){
                return true;
            }
            else if(target > matrix[mid][m]){
                low = mid + 1;
            }
            else{
                high = mid - 1;
                index = binSearch(matrix[mid], target);
            }
        }
        return index != -1;
    }

    public static int binSearch(int[] row, int target){
        int n = row.length, low = 0, high = n - 1;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(target == row[mid]){
                return mid;
            }
            else if(target > row[mid]){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return -1;
    }
}
