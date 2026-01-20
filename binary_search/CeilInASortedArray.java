/**
 * Problem:
 * <Ceil in a Sorted Array>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/ceil-in-a-sorted-array/1>
 *
 * Pattern:
 * <Core Binary Search>
 *
 * Brute Force Intuition:
 * - Linearly traverse the array and check for the smallest greater element than or equal to x.
 *
 * - Why it is inefficient?
 * - Brute force requires O(N) time to find the ceil for a single value.
 * - When the array is large or when many queries are performed, the total time becomes O(Q * N).
 * - Inefficient compared to O(Q * log N) using binary search.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used Below):
 * - Since the array is sorted, apply Binary search to directly locate the ceil.
 * - Maintain a candidate answer and shrink the search space accordingly.
 * - If the element at the middle is greater than x, store the potential candidate and try to minimize the answer.
 * - Therefore, shrink from right.
 * - If the element at the middle is lesser than x, then all the elements to the left will always be smaller.
 * - Therefore, shrink from left.
 *
 * Time Complexity:
 * - O(log N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Do not return mid directly, remember to minimize the answer.
 */
package binary_search;

public class CeilInASortedArray {
    public static void main(String[] args) {
        System.out.println(findCeil(new int[]{1, 2, 8, 10, 11, 12, 19}, 20));
    }

    public static int findCeil(int[] nums, int x){

        int n  = nums.length, low = 0, high = n - 1, ans = -1;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(nums[mid] < x){
                low = mid + 1;
            }
            else{
                ans = mid;
                high = mid - 1;
            }
        }
        return ans;
    }
}
