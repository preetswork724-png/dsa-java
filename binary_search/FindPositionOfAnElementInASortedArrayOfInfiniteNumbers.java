/**
 * Problem:
 * <Find position of an element in a sorted array of infinite numbers>
 *
 * Link:
 * <https://www.geeksforgeeks.org/dsa/find-position-element-sorted-array-infinite-numbers/>
 *
 * Pattern:
 * <Core Binary Search>
 *
 * Brute Force Intuition:
 * - Linearly traverse the array until you find the target.
 * - Keep the condition inside the while loop as true.
 * - Break the loop when you find the target.
 *
 * - Why it is inefficient?
 * - Brute force requires O(P) time to find the answer.
 * - When the array is large or when many queries are performed, the total time becomes O(Q * P).
 * - Inefficient compared to O(Q * log P) using binary search.
 *
 * Time Complexity:
 * - O(P) {P - index of the target key}
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used Below):
 * - Find a range to implement binary search.
 * - At each step keep doubling the range until the search space is exhausted or the target doesn't fall inside the range.
 * - Once you find the range, implement binary search.
 *
 * Why better approach is the optimal one?
 * - The array is sorted, so any comparison-based search for an element has a lower bound of Ω(log N).
 * - Binary Search achieves this bound by eliminating half of the search space at every step.
 * - Each element is considered at most once in the decision path, and no linear scan is required.
 * - Therefore, the time complexity is O(log N), which matches the theoretical lower bound for searching in a sorted array, making this approach optimal.
 *
 * Time Complexity:
 * - O(log P) {P - index of the target key}
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Store the high before updating it.
 * - Remember to clamp the upper bound if it exceeds the size of the given array.
 */

package binary_search;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class FindPositionOfAnElementInASortedArrayOfInfiniteNumbers {

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>(Arrays.asList(3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170));
        int target = 10;
        int pos = findPos(arr, target);
        System.out.println(pos);
    }

    public static int findPos(List<Integer> arr, int target){

        int low = 0, high = 1;
        while(low < arr.size() && target > arr.get(high)){

            int temp = high + 1;

            high = high + (high - low + 1) * 2;

            if(high >= arr.size()){
                high = arr.size()-1;
            }

            low = temp;
        }
        return binarySearch(arr, target, low, high);
    }

    public static int binarySearch(List<Integer> arr, int target, int low, int high){

        while(low <= high){

            int mid = low + (high - low) / 2;

            if(arr.get(mid) == target){
                return mid;
            }
            else if(arr.get(mid) < target){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }

        }
        return -1;
    }
}
