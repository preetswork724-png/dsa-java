/**
 * Problem:
 * <Triplets With Smaller Sum>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/count-triplets-with-sum-smaller-than-x5549/1>
 *
 * Pattern:
 * <Two Pointer>
 *
 * Brute Force Intuition:
 * - try out all the triplets from the indices (i, j, k).
 * - Whenever the sum is smaller than the target, count that pair.
 *
 * - Why it is inefficient?
 * - Very slow for large arrays.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - HashMaps / HashSets don't help much because there is no exact compliment to search for, they do not prune combinations, do not improve brute force.
 * - Pre-processing tricks like prefix sum, frequency array don't help either.
 *
 * Optimal Approach (Used Below):
 * - Sort the array.
 * - Fixate at one index
 * - Find the pair's sum using 2-pointer.
 *
 * - What to say in questions where the better approach is the optimal one?
 * - HashSet-based approaches don’t help because the problem does not involve exact matching.
 * - It gives you control over pointer movement to move closer to the target.
 * -
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Whenever nums[i] + nums[left] + nums[right] form a valid triplet, then all the elements from (left...right) form a valid triplet.
 * - Move right pointer inwards when sum >= target.
 */
package two_pointers;
import java.util.Arrays;
public class TripletsWithSmallerSum {
    public static void main(String[] args) {
        System.out.println(countTriplets(4, 2, new long[]{-2, 0, 1, 3}));
    }

    static long countTriplets(int n, int sum, long arr[]) {

        long count = 0;
        Arrays.sort(arr);

        for(int i = 0; i < n ; i++){

            int left = i+1, right = n-1;

            while(left < right){

                int tripletSum = (int)(arr[i] + arr[left] + arr[right]);

                if(tripletSum < sum){
                    count += (right - left);
                    left++;
                }else{
                    right--;
                }

            }
        }
        return count;
    }
}
