/**
 * Problem:
 * <Find K Closest Elements>
 *
 * Link:
 * <https://leetcode.com/problems/find-k-closest-elements/description/>
 *
 * Pattern:
 * <Binary search + 2 pointer>
 *
 * Brute Force Intuition:
 * - Maintain a List<List<Integer>> to store the elements along with their distance from x.
 * - Sort the list on the basis of distance.
 * - Return the k closest distances from the start.
 *
 * - Why it is inefficient?
 * - Unnecessarily sorts the complete list when we only require the k closest points.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Use max heap of size k.
 * - Maintain the elements in the heap by the order of their distance from the x.
 * - Only keep the k closest points from x in the heap.
 * - Store the elements of the heap and sort them.
 *
 * Time Complexity:
 * - O(N log K)
 * Space Complexity:
 * - O(K)
 *
 * - Why this is still not optimal?
 * - Loss of additional information such as that the array is sorted.
 *
 * Optimal Approach (Used below):
 * - Binary Search + 2 pointers.
 * - Find the index of x in the array if present or find the insert position for x if it is not present in the array.
 * - The closest elements to k will always be adjacent to x in a sorted array.
 * - Try to expand the window after locating the index of x.
 *
 * Time Complexity:
 * - O(log N + K)
 * Space Complexity:
 * - O(K)
 *
 * Notes:
 * - Since the array is sorted:
 * - The k closest elements must lie in a contiguous window around x.
 * - So, we can find that using binary search and expand it with two pointers instead of scanning everytime.
 */

package top_k_elements;
import java.util.List;
import java.util.ArrayList;
public class FindKClosestElements {

    public static void main(String[] args) {
        System.out.println(findClosestElements(new int[]{1,2,3,4,5}, 3, 3));;
    }

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        int idx = searchInsertPos(arr, x, n);

        int left = idx-1, right = idx;

        while(right - left - 1 < k){

            if(left < 0){
                right++;
            }
            else if(right >= n){
                left--;
            }
            else if(Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)){
                left--;
            }
            else{
                right++;
            }
        }

        List<Integer> ans = new ArrayList<>();

        for(int i = left + 1; i < right; i++){
            ans.add(arr[i]);
        }
        return ans;
    }

    public static int searchInsertPos(int[] arr, int x, int n){
        int low = 0, high = n-1;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(arr[mid] == x){
                return mid;
            }
            else if(arr[mid] < x){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return low;
    }
}
