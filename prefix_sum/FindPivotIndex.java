/**
 * Problem:
 * <Find Pivot Index>
 *
 * Link:
 * <https://leetcode.com/problems/find-pivot-index/description/>
 *
 * Pattern:
 * <Prefix Sum>
 *
 * Brute Force Intuition:
 * - For each element, calculate the left sum and calculate the right sum.
 *
 * - Why it is inefficient?
 * - Calculating sum for each element takes O(N) time.
 * - Very slow for large arrays.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Compute two arrays such as leftSum and rightSum to calculate the sum for each element in O(1).
 * - Then for each element, check if left sum equals right sum.
 *
 * - Why it is still not optimal?
 * - Computing the arrays take extra memory.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Just calculate the total sum.
 * - Maintain the leftSum and for each element calculate the right sum.
 * - right sum = total sum - left sum.
 * - Check if they are equal.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Remember to subtract the element at the current index before comparing the leftSum and rightSum.
 */
package prefix_sum;

public class FindPivotIndex {
    public static void main(String[] args) {
        System.out.println(pivotIndex(new int[]{1,7,3,6,5,6}));
    }

    public static int pivotIndex(int[] nums) {
        int totalSum = 0, n = nums.length;

        for(int num : nums) totalSum += num;

        int leftSum = 0;

        for(int i = 0; i < n; i++){
            int rightSum = totalSum - leftSum - nums[i];

            if(leftSum == rightSum){
                return i;
            }

            leftSum += nums[i];
        }
        return -1;
    }
}
