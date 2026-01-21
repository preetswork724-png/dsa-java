/**
 * Problem:
 * <Peak Index in a Mountain Array>
 *
 * Link:
 * <https://leetcode.com/problems/peak-index-in-a-mountain-array/description/>
 *
 * Pattern:
 * <Mountain / Bitonic Array>
 *
 * Brute Force Intuition:
 * - Linearly traverse the array and return / store the first index where the values start to descend.
 *
 * - Why it is inefficient?
 * - Brute force requires O(N) time to find the peak index in a mountain array.
 * - Loss of given information that the array is bitonic.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used Below):
 * - The array is sorted in 2 parts:
 * - All the values are sorted in ascending order before the peak index.
 * - All the values are sorted in descending order after the peak index.
 * - Due to this sorted nature, Binary search can be applied.
 * - Whenever we find a next value lesser than the current value that means that we are in the descending part of the mountain.
 * - Whenever we find a next value greater than the current value that means that we are in the ascending part of the mountain.
 *
 * Why better approach is the optimal one?
 * - The array is sorted, so any comparison-based search for an element has a lower bound of Ω(log N).
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
 * - Once you are in the descending part of the mountain, try to ascend to find the peak of the mountain.
 * - Update high = mid - 1.
 */

package binary_search;

public class PeakIndexInAMountainArray {

    public static void main(String[] args) {
        System.out.println(peakIndexInMountainArray(new int[]{0,1,0}));
    }

    public static int peakIndexInMountainArray(int[] arr) {
        int n = arr.length, ans = -1, low = 0, high = n - 1;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(mid + 1 < n && arr[mid] > arr[mid + 1]){
                ans = mid;
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        return ans;
    }
}
