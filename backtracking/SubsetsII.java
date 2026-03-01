/**
 * Problem:
 * <Subsets II>
 *
 * Link:
 * <https://leetcode.com/problems/subsets-ii/description/>
 *
 * Pattern:
 * <Loop-based backtracking>
 *
 * Brute Force Intuition:
 * - Generate the subsets.
 * - Maintain a List<List<Integer>> to store the subsets.
 * - Before storing the subsets, check if the list already contains that list of subset or not.
 * - If it contains -> skip the current list.
 * - Else -> add the current list.
 *
 * - Why it is inefficient?
 * - Total subsets generated are 2^N.
 * - The list contains 2^N subsets.
 * - Checking each subset takes O(N) time.
 * - Checking for duplicate becomes -> O(N * 2^N).
 * - And, we do this for each subset -> O(2^N * (N * 2^N)).
 * - Total = O(N * 4^N).
 * - Explodes badly.
 *
 * Time Complexity:
 * - O(N * 4^N)
 * Space Complexity:
 * - O(N * 2^N){Output storage} + O(N){Recursion depth}
 *
 * Better Approach intuition:
 * - Use a set to check for duplicate subsets.
 * - If the current subset is not a part of the set:
 * - Add it to the set.
 * - Add it to the list.
 *
 * Time Complexity:
 * - O(N * 2^N)
 * Space Complexity:
 * - O(N * 2^N) {Output storage} + O(N * 2^N) {set} + O(N) {Recursion depth}.
 *
 * Why this is still not optimal?
 * - We use extra space to check for duplicate subsets.
 * - We are still generating duplicates.
 *
 * Optimal Approach (Used below):
 * - Sort the array first.
 * - Sorting ensures duplicates become adjacent.
 * - Use loop-based backtracking.
 * - At every recursion level:
 *       Add the current subset to the result immediately.
 *       Iterate from index to n - 1.
 * - If:
 *       i > index && nums[i] == nums[i - 1]
 *       → Skip (duplicate at same level).
 * - Otherwise:
 *       Include nums[i]
 *       Recur using helper(i + 1, ...)
 *       Backtrack (remove last element).
 * - In loop-based backtracking:
 * - There is no explicit base case like if(index == n) return.
 * - The recursion naturally stops when index == n, because:
 * - The for loop will not execute.
 * - The function simply returns.
 * - The loop structure implicitly handles termination.

 *
 * Time Complexity:
 * - O(N * 2^N).
 * Space Complexity:
 * - O(N) {Recursion depth} + O(N * 2^N) {Output storage}.
 *
 * Notes:
 * - Why we add subset immediately?
 * - We add before exploring further because:
 * - Every partialAns is a valid subset.
 * - We don't need to wait for a leaf node.
 */

package backtracking;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
public class SubsetsII {

    public static void main(String[] args) {
        System.out.println(subsetsWithDup(new int[]{1,2,2}));
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        helper(0, n, nums, new ArrayList<>(), res);
        return res;
    }

    public static void helper(int index, int n, int[] nums, List<Integer> partialAns,
                       List<List<Integer>> res){

        res.add(new ArrayList<>(partialAns));

        for(int i = index; i < n; i++){

            if(i > index && nums[i] == nums[i-1]) continue;

            partialAns.add(nums[i]);
            helper(i + 1, n, nums, partialAns, res);
            partialAns.remove(partialAns.size() - 1);
        }
    }
}
