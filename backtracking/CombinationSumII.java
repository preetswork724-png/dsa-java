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
 * - Generate all the combinations that sum up to target.
 * - To generate combinations:
 *      Iterate from the current index.
 *      Pick candidate.
 *      Recurse with index + 1.
 *      Reduce target.
 *      Backtrack.
 * - Maintain a List<List<Integer>> to store the combinations.
 * - Before storing the combinations, check if the list already contains that combination.
 * - If it contains -> skip the current combination.
 * - Else -> add the current combinations.
 *
 * - Why it is inefficient?
 * - Total combinations generated are 2^N.
 * - The list contains 2^N combinations.
 * - Checking each combinations takes O(N) time.
 * - Checking for duplicate combinations -> O(N * 2^N).
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
 * - Use a set at each recursion level to track the already used numbers.
 * - If the current element is not present in set then use it reach to the target.
 * - Else, skip it.
 *
 * Time Complexity:
 * - O(N * 2^N)
 * Space Complexity:
 * - O(N * 2^N) {Output storage} + O(N^2) {set} + O(N) {Recursion depth}.
 *
 * Why this is still not optimal?
 * - We use extra space to check for duplicate combinations.
 * - We are still generating duplicates.
 *
 * Optimal Approach (Used below):
 * - Sort the array first.
 * - Sorting ensures duplicates become adjacent.
 * - Use loop-based backtracking.
 * - At every recursion level:
 *       Iterate from index to n - 1.
 *      If candidates[i] > target break.
 * - If:
 *       i > index && candidates[i] == candidates[i - 1]
 *       → Skip (duplicate at same level).
 * - Otherwise:
 *       Include candidates[i]
 *       Recur using helper(i + 1, ...)
 *       Backtrack (remove last element).
 * - Pruning:
 *      If target == 0 add the current combination
 *
 * Time Complexity:
 * - O(N * 2^N).
 * Space Complexity:
 * - O(N) {Recursion depth} + O(N * 2^N) {Output storage}.
 *
 * Notes:
 * - Depth bounded by N → think 2^N
 * - Depth bounded by target → think 2^T
 */

package backtracking;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class CombinationSumII {

    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int n = candidates.length;
        Arrays.sort(candidates);
        List<List<Integer>> combinations = new ArrayList<>();
        helper(0, n, target, candidates, new ArrayList<>(), combinations);
        return combinations;
    }

    public static void helper(int index, int n, int target, int[] candidates,
                       List<Integer> partialComb, List<List<Integer>> combinations){

        if(target == 0){
            combinations.add(new ArrayList<>(partialComb));
            return;
        }

        for(int i = index; i < n; i++){

            if(i > index && candidates[i] == candidates[i - 1]) continue;

            if(candidates[i] > target) break;

            partialComb.add(candidates[i]);
            helper(i + 1, n, target - candidates[i], candidates, partialComb, combinations);
            partialComb.remove(partialComb.size() - 1);
        }
    }
}
