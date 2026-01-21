/**
 * Problem:
 * <Find Smallest Letter Greater Than Target>
 *
 * Link:
 * <https://leetcode.com/problems/find-smallest-letter-greater-than-target/description/>
 *
 * Pattern:
 * <Core Binary Search>
 *
 * Brute Force Intuition:
 * - Linearly traverse the array and check for the smallest character that is lexicographically greater than target.
 *
 * - Why it is inefficient?
 * - Brute force requires O(N) time to find the answer.
 * - When the array is large or when many queries are performed, the total time becomes O(Q * N).
 * - Inefficient compared to O(Q * log N) using binary search.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used Below):
 * - Since the array is sorted, apply Binary search to directly locate the smallest character that is lexicographically greater than target.
 * - Maintain a candidate answer and shrink the search space accordingly.
 * - If arr[mid] <= x, discard the left half (including mid), by moving the lower bound to low = mid + 1, since any valid answer must lie to the right.
 * - If arr[mid] > x, store arr[mid] as a potential candidate (it could be the smallest element greater than target) and continue searching in the left half by updating high = mid - 1 to find a smaller such value.
 *
 * Why better approach is the optimal one?
 * - The array is sorted, so any comparison-based search for a boundary element (ceil/floor) has a lower bound of Ω(log N).
 * - Binary Search achieves this bound by eliminating half of the search space at every step.
 * - Each character is considered at most once in the decision path, and no linear scan is required.
 * - Therefore, the time complexity is O(log N), which matches the theoretical lower bound for searching in a sorted array, making this approach optimal.
 *
 * Time Complexity:
 * - O(log N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Remember to store the potential candidates and then reduce the search space.
 */

package binary_search;

public class FindSmallestLetterGreaterThanTarget {

    public static void main(String[] args) {
        System.out.println(nextGreatestLetter(new char[]{'c','f','j'}, 'a'));
    }

    public static char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length, low = 0, high = n - 1;
        char ans = ' ';

        while(low <= high){

            int mid = low + (high-low) / 2;
            char middle = letters[mid];

            if(middle <= target){
                low = mid + 1;
            }
            else{
                ans = middle;
                high = mid - 1;
            }
        }
        return ans == ' ' ? letters[0] : ans;
    }
}
