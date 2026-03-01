/**
 * Problem:
 * <Combination Sum>
 *
 * Link:
 * <https://leetcode.com/problems/combination-sum/description/>
 *
 * Approach:
 * - Iterate from the current index.
 * - Pick candidate.
 * - Recurse with same index.
 * - Reduce target.
 * - Backtrack.
 *
 * Time Complexities:
 * - O(2^T) {T - target depth}
 *
 * Space Complexity:
 * - O(T) {Recursion depth}
 * - O(T * number_of_combinations) {Output storage}
 */

package backtracking;
import java.util.List;
import java.util.ArrayList;
public class CombinationSum {

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2,3,6,7}, 7));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        int n = candidates.length;
        helper(0, n, 0, target, candidates, new ArrayList<>(), combinations);
        return combinations;
    }

    public static void helper(int index, int n, int sum, int target, int[] candidates,
                       List<Integer> partialComb, List<List<Integer>> combinations){

        if(sum == target){
            combinations.add(new ArrayList<>(partialComb));
            return;
        }

        if(sum > target) return;

        for(int i = index; i < n; i++){
            partialComb.add(candidates[i]);
            helper(i, n, sum + candidates[i], target, candidates, partialComb, combinations);
            partialComb.remove(partialComb.size() - 1);
        }
    }
}
