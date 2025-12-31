/**
 * Problem:
 * <Subarray Sum Equals K>
 *
 * Link:
 * <https://leetcode.com/problems/subarray-sum-equals-k/description/>
 *
 * Pattern:
 * <Prefix Sum>
 *
 * Brute Force Intuition:
 * - Generate all the subarrays.
 * - Count them if their sum equals k.
 *
 * - Why it is inefficient?
 * - Calculating sum of a subarray takes O(N) time.
 * - Very slow for large arrays.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used Below):
 * - Maintain a prefix sum.
 * - A prefix sum helps to answer the sum of the subarray in O(1) time.
 * - Use a map to track the prefix sum with their occurrences.
 * - Always check if there exists a prev sum = current sum - k.
 * - Because, prev + curr = k. Then, prev = curr - k.
 * - Then there exists two points (i..j) in the array such that the subarray sum between them equals k.
 * - Increment the count by the number of such subarrays.
 * -
 * - What to say in questions where the better approach is the optimal one?
 * - In this problem, the better approach is the optimal one.
 * - Because we reduced O(N) sum query time to O(1).
 * - And prefix sum acts as a memory.
 * - To optimize the time of query answer, we bear the cost of pre-processing which takes O(N) in terms of space.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Include the edge case, where the sum of the subarray is O and its frequency is 1.
 * - So that, if there exists a point in the array where prefix Sum equals k, then you need to count the number of subarrays where sum equals 0.
 */
package prefix_sum;
import java.util.Map;
import java.util.HashMap;
public class SubarraySumEqualsK {
    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1,1,1}, 2));
    }

    public static int subarraySum(int[] nums, int k) {
        int n = nums.length, sum = 0, count = 0;
        Map<Integer, Integer> prefixMap = new HashMap<>();

        prefixMap.put(0, 1);

        for(int num : nums){
            sum += num;

            if(prefixMap.containsKey(sum - k)){
                count += prefixMap.get(sum - k);
            }

            prefixMap.put(sum, prefixMap.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
