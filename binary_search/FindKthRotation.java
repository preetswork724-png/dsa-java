/**
 * Problem:
 * <Find Kth Rotation>
 *
 * Link:
 * <http://geeksforgeeks.org/problems/rotation4723/1>
 *
 * Pattern:
 * <Find pivot>
 *
 * Brute Force Intuition:
 * - Create a clone of the array.
 * - Sort it.
 * - Find the minimum element.
 * - Find the index of the minimum element in the input array.
 * - Index of minimum element = number of rotations.
 *
 * - Why it is inefficient?
 * - Brute force requires O(N) space to create clone of the array.
 * - Additional sorting step to find the minimum.
 * - Iterating to find the index of the minimum element in the input array.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach intuition:
 * - Find the minimum element in the array and its index.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Why this is still not optimal?
 * - Finding minimum element in the array takes O(N) time.
 * - Doing linear scan while rotating the array divides the array into 2 parts: sorted and unsorted.
 *
 * Optimal Approach (Used Below):
 * - After rotations, there is exactly one place where the order breaks.
 * - The break happens at the minimum element.
 * - The problem is reduced to finding the minimum element using binary search.
 * - The array has this structure: [large ... largest | smallest ... large]
 * - Left part: increasing, right part: increasing, minimum: pivot point between them.
 * - At each step, compute mid.
 * - Compare arr[mid] with arr[high].
 * - Case 1: arr[mid] > arr[high], answer lies in the right half.
 * - Case 2: arr[mid] <= arr[high], mid might be the answer , search in the left half.
 *
 * Time Complexity:
 * - O(log N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Why this always converges to the minimum?
 * - One side is always sorted.
 * - The unsorted side contains the pivot (minimum)
 * - Binary search keeps throwing away the sorted half.
 * - Eventually one minimum survives.
 */
package binary_search;

public class FindKthRotation {

    public static void main(String[] args) {
        System.out.println(findKRotation(new int[]{5,1,2,3,4}));
    }

    public static int findKRotation(int arr[]) {
        int n = arr.length, low = 0, high = n - 1;

        while(low < high){
            int mid = low + (high - low) / 2;

            if(arr[mid] > arr[high]){
                low = mid + 1;
            }
            else{
                high = mid; // Always keep mid in the search space as it might be a potential answer.`
            }
        }
        return low;
    }
}
