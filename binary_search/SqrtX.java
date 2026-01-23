/**
 * Problem:
 * <Sqrt(x)>
 *
 * Link:
 * <https://leetcode.com/problems/sqrtx/description/>
 *
 * Pattern:
 * <Core Binary Search>
 *
 * Brute Force Intuition:
 * - Linearly traverse till the number.
 * - Return when you find the square of a number equal to target.
 *
 * - Why it is inefficient?
 * - Linear scan over a range of integer is not the most optimal one especially when the range is monotonic.
 *
 * Time Complexity:
 * - O(x)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used below):
 * - Apply binary search in the range.
 *
 * Time Complexity:
 * - O(log x)
 * Space Complexity:
 * - O(1)
 *
 * Why better approach is the optimal one?
 * - As the range is monotonic, apply binary search on this integer range.
 * - Binary Search achieves this bound by eliminating half of the search space at every step.
 * - Each element is considered at most once in the decision path, and no linear scan is required.
 * - Therefore, the time complexity is O(log N), which matches the theoretical lower bound for searching in a range, making this approach optimal.
 *
 * Notes:
 * - Take long to accumulate the product.
 */

package binary_search;

public class SqrtX {

    public static void main(String[] args) {
        System.out.println(mySqrt(4));
    }

    public static int mySqrt(int x) {
        long start = 1, end = x, mid = 0, prdt = 0;

        while(start <= end){
            mid = start + (end - start) / 2;
            prdt = mid * mid;

            if(prdt == x){
                return (int)mid;
            }
            else if(prdt < x){
                start = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }
        return (int)end;
    }
}
