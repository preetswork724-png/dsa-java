/**
 * Problem:
 * <Combinations>
 *
 * Link:
 * <https://leetcode.com/problems/combinations/description/>
 *
 * Pattern:
 * <Loop-based backtracking>
 *
 * Brute Force Intuition:
 * - Generate all the combinations.
 * - To generate combinations:
 *      Iterate from the current index.
 *      Pick candidate.
 *      Recurse with index + 1.
 *      Backtrack.
 * - Maintain a List<List<Integer>> to store the combinations.
 * - Iterate over the list of combinations.
 * - Only include the subLists of size k.
 *
 * - Why it is inefficient?
 * - We generate combinations with all the numbers when only the combinations of K numbers are valid.
 *
 * Time Complexity:
 * - O(N * 2^N)
 * Space Complexity:
 * - O(N * 2^N){Output storage} + O(N){Recursion depth}
 *
 * Better and Optimal Approach (used below):
 * - Early pruning.
 * - Prune when:
 * - If size > k -> stop.
 * - If size == k:
 *      Add the sublist to the res list.
 *
 * Time Complexity:
 * - O(C(n, k) * k)
 * Space Complexity:
 * - O(C(n,k) * k) {Output storage} + O(k) {Recursion depth}.
 *
 * Notes:
 * - When the problem says "Generate all combinations of size k”
 * - Immediately think:
 * - Time ≈ number_of_combinations × cost_per_combination
 */

package backtracking;
import java.util.List;
import java.util.ArrayList;
public class Combinations {

    public static void main(String[] args) {
        System.out.println(combine(4,2));
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        helper(1, n, k, new ArrayList<>(), ans);
        return ans;
    }

    public static void helper(int index, int n, int k,
                       List<Integer> partialComb,
                       List<List<Integer>> ans) {

        if (partialComb.size() == k) {
            ans.add(new ArrayList<>(partialComb));
            return;
        }

        for (int i = index;
             i <= n - (k - partialComb.size()) + 1;
             i++) {

            partialComb.add(i);
            helper(i + 1, n, k, partialComb, ans);
            partialComb.remove(partialComb.size() - 1);
        }
    }
}
