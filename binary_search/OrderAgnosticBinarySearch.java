/**
 * Problem:
 * <Order-Agnostic Binary Search>
 *
 * Link:
 * <https://www.geeksforgeeks.org/dsa/order-agnostic-binary-search/>
 *
 * Pattern:
 * <Binary Search>
 *
 * Brute Force Intuition:
 * - Linearly traverse the array and check whether the element is present or not.
 *
 * - Why it is inefficient?
 * - The above approach proves to be inefficient when you have to search for multiple values.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used Below):
 * - We need to reduce the searching time from O(N).
 * - The given input is sorted but the order is unknown.
 * - Therefore, we implement a modified approach of Binary Search i.e Order - Agnostic Binary Search which comes with one more condition.
 * - If the first element is smaller than the last element : if (target > arr[mid]) start = mid + 1, else end = mid - 1.
 * - If the first element is greater than the last element : if (target > arr[mid]) end = mid - 1, else start = mid + 1.
 * - In the end, if the target matches with the middle element then the element which was given to search is found.
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
 * - If the sorted order of the array is not given then it is safer to implement the Order Agnostic Binary Search.
 */
package binary_search;

public class OrderAgnosticBinarySearch {

    public static void main(String[] args) {
        System.out.println(binSearch(new int[]{1,2,3,4,5}, 4));
    }

    public static int binSearch(int[] nums, int target){

        int n = nums.length, start = 0, end = n - 1;

        boolean isAsc = nums[start] <= nums[end];

        while(start <= end){

            int mid = start + (end - start) / 2;

            if(nums[mid] == target) return mid;

            if(isAsc){
                if(target > nums[mid]){
                    start = mid + 1;
                }
                else{
                    end = mid - 1;
                }
            }
            else{
                if(target > nums[mid]){
                    end = mid - 1;
                }
                else{
                    start = mid + 1;
                }
            }
        }
        return -1;
    }
}
