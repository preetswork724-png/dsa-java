/**
 * Problem:
 * <Count of Range Sum>
 *
 * Link:
 * <https://leetcode.com/problems/count-of-range-sum/>
 *
 * Pattern:
 * <Prefix Sum>
 *
 * Brute Force Intuition:
 * - Try all the subarrays.
 * - Check if the sum falls within the range.
 *
 * - Why it is inefficient?
 * - Calculating sum of the subarray takes O(N) each time which is quiet inefficient.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Building prefix sum.
 * - Checking every i and j pair such as : lower <= prefix[j] - prefix[i] <= upper.
 *
 * - Why it is still not optimal?
 * - Checking every i,j pair produces the same time complexity even after pre-processing step.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Compute the prefix sum array.
 * - prefix[j+1] - prefix[i] = sum of subarray.
 * - Now this subarray should fall within the bounds i.e lower <= prefix[j+1] - prefix[i] <= upper.
 * - Readjusting the inequality, we get prefix[j+1] - upper <= prefix[i] <= prefix[j+1] - lower.
 * - For every right index, we count how many previous prefix[i] fall within this range.
 * - If prefix array is sorted, we can count efficiently.
 * - So, we do a merge-sort like Count Inversions problem.
 * - While merging :
 * - left half contains earlier prefix sums.
 * - right half contains later prefix sums.
 * - For each element in right half,
 * - Count how any from left fall into the allowed range using two pointers.
 * - Steps :-
 * - 1. Build prefix sum.
 * - 2. Perform merge sort.
 * - 3. Count the previous valid prefix[i].
 * - 4. Create a new array to merge and sort the prefixes.
 * - 5. Put the merged sorted prefixes in the original prefix[] for future use.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Count subarrays in range.
 * - Count prefix difference in range.
 * - n up to 100k.
 * - negative numbers exist.
 *
 */
package prefix_sum;
import java.util.Arrays;
public class CountOfRangeSum {

    public static void main(String[] args) {
        System.out.println(countRangeSum(new int[]{-2, 5, -1},-2, 2));
    }

    public static int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] prefix = new long[n + 1];

        for(int i = 0; i < n ; i++){
            prefix[i + 1] = prefix[i] + nums[i];
        }

        return mergeSort(prefix, 0, n, lower, upper);
    }

    public static int mergeSort(long[] prefix, int left, int right, int lower, int upper){
        if(left >= right) return 0;

        int mid = (left + right) / 2;
        int count = mergeSort(prefix, left, mid, lower, upper) + mergeSort(prefix, mid + 1, right, lower, upper);

        int low = mid + 1, high = mid + 1;

        for(int i = left; i <= mid; i++){
            while(low <= right && prefix[low] - prefix[i] < lower) low++;
            while(high <= right && prefix[high] - prefix[i] <= upper) high++;
            count += (high - low);
        }

        long[] temp = new long[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while(i <= mid && j <= right){
            temp[k++] = prefix[i] <= prefix[j] ? prefix[i++] : prefix[j++];
        }

        while(i <= mid) temp[k++] = prefix[i++];
        while(j <= right) temp[k++] = prefix[j++];

        System.arraycopy(temp, 0, prefix, left, temp.length);

        return count;
    }
}
