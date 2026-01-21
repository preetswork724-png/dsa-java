/**
 * Problem:
 * <Minimum Absolute Difference>
 *
 * Link:
 * <https://leetcode.com/problems/minimum-absolute-difference/description/>
 *
 * Pattern:
 * <Core Binary Search>
 *
 * Brute Force Intuition:
 * - Check all the pairs and find the minimum absolute difference between two pairs.
 * - Again, check all the pairs and add the pair with the minimum absolute difference to the result.
 *
 * - Why it is inefficient?
 * - Brute force checks every (i, j) pair to find the minimum absolute difference.
 * - And the same task is repeated while adding the pairs with minimum absolute difference.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used Below):
 * - In a sorted array, the minimum absolute difference always occurs between adjacent elements only.
 * - Sort the array.
 * - One pass to compute the minimum absolute difference.
 * - One pass to compute the pairs with minimum absolute difference.
 *
 * Why better approach is the optimal one?
 * - Sorting gives array a structure.
 * - The element with the minimum absolute difference will always be next to the current element.
 * - Therefore, the time complexity is O(N log N), which matches the theoretical lower bound for sorting an array, making this approach optimal.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Why Temp ArrayList works measurably faster then Arrays.asList() ?
 * - Temp ArrayList:
 * - Single simple object.
 * - Plain resizable list.
 * - Less GC pressure.
 * - Direct Storage :- directly stores boxes integers.
 *
 * - Arrays.asList():
 * - Creates hidden array + wrapper (creates an Object[], auto boxes int -> integer)
 * - Wraps that array inside a special java.util.Arrays$ArrayList object.
 * - Fixed-size list with extra logic.
 * - More GC pressure.
 * - More indirections.
 */

package binary_search;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class MinimumAbsoluteDifference {

    public static void main(String[] args) {
        System.out.println(minimumAbsDifference(new int[]{4,2,1,3}));
    }

    public static List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length, minAbsDiff = Integer.MAX_VALUE;

        for(int i = 1; i < n; i++){
            minAbsDiff = Math.min(minAbsDiff, arr[i] - arr[i-1]);
        }

        List<List<Integer>> ans = new ArrayList<>();

        for(int i = 1; i < n; i++){

            if(arr[i] - arr[i-1] == minAbsDiff){
                List<Integer> list = new ArrayList<>();
                list.add(arr[i-1]);
                list.add(arr[i]);
                ans.add(list);
            }

        }
        return ans;
    }
}
