/**
 * Problem:
 * <Combination Sum III>
 *
 * Link:
 * <https://leetcode.com/problems/combination-sum-iii/description/>
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
 * - Iterate over the list of combinations.
 * - Only include the subLists of size k.
 *
 * - Why it is inefficient?
 * - We generate combinations with all the numbers when only the combinations of K numbers that sum up to the target are valid.
 *
 * Time Complexity:
 * - O(2^9)
 * Space Complexity:
 * - O(2^9){Output storage} + O(N){Recursion depth}
 *
 * Better and Optimal Approach (used below):
 * - Early pruning.
 * - Prune when:
 * - If size > k -> stop.
 * - If target < 0 -> stop.
 * - If size == k:
 *      Check if target == 0.
 *      Else return.
 *
 * Time Complexity:
 * - O(2^9)
 * Space Complexity:
 * - O(2^9) {Output storage} + O(K) {Recursion depth}.
 *
 * Notes:
 * - Since, the candidate range is fixed (1-9), the complexity is bounded by 2^9, which is constant.
 */

package backtracking;
import java.util.List;
import java.util.ArrayList;
public class CombinationSumIII {

    public static void main(String[] args) {
        System.out.println(combinationSum3(3, 7));
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> combinations = new ArrayList<>();
        helper(1, n, k, new ArrayList<>(), combinations);
        return combinations;
    }

    public static void helper(int index, int n, int k, List<Integer> partialComb,
                       List<List<Integer>> combinations){

        if(n == 0 && partialComb.size() == k){
            combinations.add(new ArrayList<>(partialComb));
            return;
        }

        if(partialComb.size() > k) return;

        for(int i = index; i < 10; i++){

            if(i > n) break;

            partialComb.add(i);
            helper(i + 1, n - i, k, partialComb, combinations);
            partialComb.remove(partialComb.size() - 1);
        }
    }
}
