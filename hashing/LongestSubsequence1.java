/**
 * Problem:
 * <Longest subsequence-1>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/longest-subsequence-such-that-difference-between-adjacents-is-one4724/1>
 *
 * Pattern:
 * <Dp>
 *
 * Brute Force Intuition:
 * - Generate all subsequences of a string.
 * - For each subsequence:
 * - Check if every adjacent pair: |x-y| == 1.
 * - If yes, update the maximum length.
 *
 * - Why it is inefficient?
 * - Generating subsequences of a string takes O(2^N).
 * - Checking all the adjacent pairs takes another O(N).
 * - This is pure brute force and complexity is infeasible.
 *
 * Time Complexity:
 * - O(2^N * N)
 * Space Complexity:
 * - O(N) {Recursion stack / storing subsequences}
 *
 * Better Approach Intuition:
 * - Dynamic Programming:
 * - dp[i] = length of longest valid subsequence ending at index i.
 * - For every j < i:
 * - if |nums[i] - nums[j]| == 1.
 * - Then, we can append nums[j] after nums[i].
 * - dp[i] = max(dp[i], dp[j+1]).
 * -
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N) {dp array}
 *
 * Why this is still not optimal?
 * - The time complexity for thr given problem is quadratic.
 *
 * Optimal Approach (used below):
 * - We do not care about the indices anymore.
 * - We only care about the values and the best subsequence ending with value x.
 * - dp[x] = length of longest valid subsequence ending with the value x.
 * - We can reach x from:
 * - x-1
 * - x+1
 * - So, dp[x] = Math.max(dp[x-1], dp[x+1]).
 * - Update this as you scan the array.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Why dp[x] = max(dp[x-1], dp[x+1]) + 1 and not something like dp[x] = dp[x-1] + dp[x+1] + 1 (i.e., “take both”)?
 * - dp[x] = length of longest valid subsequence ending with the value x.
 * - Not:
 * - Total number of combinations.
 * - Not combination of multiple paths.
 * - But the longest valid chain ending at index i.
 * - Why only one side can be extended?
 * - Suppose, we are processing value x:
 * - Valid previous values that can come before x are:
 * - x-1
 * - x+1
 * - But in a subsequence, the element just before x must be one single value, not two.
 * - So, the chain ending at x must come from either x-1 or x+1.
 * - Because a subsequence is not a merge of two sequences.
 * - It's a straight line and not a graph.
 */
package hashing;
import java.util.Map;
import java.util.HashMap;
public class LongestSubsequence1 {

    public static void main(String[] args) {
        System.out.println(longestSubseq(new int[]{1, 2, 3, 4, 5}));
    }

    public static int longestSubseq(int[] arr) {
        int n = arr.length, maxLen = 0;
        Map<Integer, Integer> dp = new HashMap<>();

        for(int x : arr){
            int left = dp.getOrDefault(x-1, 0);
            int right = dp.getOrDefault(x+1, 0);
            int best = Math.max(left, right) + 1;

            dp.put(x, best);
            maxLen = Math.max(maxLen, best);
        }
        return maxLen;
    }
}
