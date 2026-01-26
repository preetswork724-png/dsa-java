/**
 * Problem:
 * <Pascal's Triangle>
 *
 * Link:
 * <https://leetcode.com/problems/pascals-triangle/description/>
 *
 * Pattern:
 * <Math recurrence>
 *
 * Brute Force Intuition:
 * - Use the combinatorics formula:
 * - nCr = n! / r! * (n-r)!
 * - For every i, j compute iCj.
 *
 * - Why it is inefficient?
 * - Computing factorial for every num takes O(N) time everytime.
 * - As factorials explode, this approach works for numbers with small factorials.
 * - For numbers with big factorials, the result is likely to overflow and wrapping them produces incorrect results.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(N^2)
 *
 * Better Approach Intuition:
 * - No factorial, no overflow.
 * - In Pascal's Triangle, each number is the sum of two numbers directly above it.
 * - triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j].
 * - Using the above formula, we can compute the entire triangle.
 *
 * - Why it is still not optimal?
 * - Dependency on previous states.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N^2)
 *
 * Optimal Approach (Used Below):
 * - Use math recurrence relation to compute the pascal's triangle.
 * - Each row of the Pascal's Triangle is:
 * - C(n,0),C(n,1),C(n,2),...,C(n,n)
 * - Instead of using factorial:
 * - nCk = n! / k! * (n-k)!
 * - We use the recurrence relation:
 * - C(n, k) = C(n, k-1) * (n - k + 1) / k
 * - This lets us generate each row in O(n) time using only constant number of variables.
 * - val = val * (n-k+1) / k for k != 0.
 * - val = val * (n-k) / k+1 for k == 0.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N^2) {output}, O(1) {auxiliary}.
 *
 * Notes:
 * - Why is math recurrence considered to be space optimal and not dp?
 * - Because, dp approach needs previous row dependency.
 * - To compute row i, it must keep row i-1 in memory.
 * - Recurrence approach generates rows independently.
 * - Each row is computed on a single variable val.
 * - It does not depend on storing any earlier rows.
 * - If the problem were:
 * - “Return only the kth row of Pascal’s Triangle”
 * - Then:
 * - DP would need O(k) space (previous row).
 * - Recurrence would need O(1) space.
 */
package dp;
import java.util.List;
import java.util.ArrayList;
public class PascalsTriangle {

    public static void main(String[] args) {
        System.out.println(generate(5));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for(int n = 0; n < numRows; n++){
            List<Integer> row = new ArrayList<>();
            int val = 1;
            for(int k = 0; k <= n; k++){
                row.add(val);
                val = val * (n - k) / (k + 1);
            }
            triangle.add(row);
        }
        return triangle;
    }
}
