/**
 * Problem:
 * <Floor in a Sorted Array>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/floor-in-a-sorted-array-1587115620/1>
 *
 * Pattern:
 * <Core Binary Search>
 *
 * Brute Force Intuition:
 * - Linearly traverse the array and check for the largest element lesser than or equal to x.
 *
 * - Why it is inefficient?
 * - Brute force requires O(N) time to find the floor for a single value.
 * - When the array is large or when many queries are performed, the total time becomes O(Q * N).
 * - Inefficient compared to O(Q * log N) using binary search.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used Below):
 * - Since the array is sorted, apply Binary search to directly locate the floor.
 * - Maintain a candidate answer and shrink the search space accordingly.
 * - If the element at the middle is greater than x, then all the elements further to the right will always be greater.
 * - Therefore, shrink from right.
 * - If the element at the middle is lesser than or equal to x, store the potential candidate and try to maximize the answer.
 * - Therefore, shrink from left.
 *
 * Why better approach is the optimal one?
 * - The array is sorted, so any comparison-based search for a boundary element (ceil/floor) has a lower bound of Ω(log N).
 * - Binary Search achieves this bound by eliminating half of the search space at every step.
 * - Each element is considered at most once in the decision path, and no linear scan is required.
 * - Therefore, the time complexity is O(log N), which matches the theoretical lower bound for searching in a sorted array, making this approach optimal.
 *
 * Time Complexity:
 * - O(log N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Do not return mid directly, remember to maximize the answer.
 */
package binary_search;

public class FloorInASortedArray {

    public static void main(String[] args) {
        System.out.println(findFloor(new int[]{1, 2, 8, 10, 10, 12, 19}, 5));
    }

    public static int findFloor(int[] arr, int x) {

        int n = arr.length, low = 0, high = n - 1, ans = -1;

        while(low <= high){

            int mid = low + (high - low) / 2;

            if(arr[mid] <= x){
                ans = mid;
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }

        }
        return ans;
    }
}
