/**
 * Problem:
 * <Permutations II>
 *
 * Link:
 * <https://leetcode.com/problems/permutations-ii/description/>
 *
 * Pattern:
 * <Loop-based backtracking>
 *
 * Brute Force Intuition:
 * - Generate all the permutations (including duplicates):
 * - For every permutation:
 * - If it is unique, add it.
 * - Else, skip it.
 *
 * - Why it is inefficient?
 * - Generating permutation takes N! time.
 * - The list contains at most n!
 * - Checking each permutation takes O(N) time.
 * - So, total = (N! * (N! * N))
 * - Explodes badly.
 *
 * Time Complexity:
 * - O(N! * (N! * N))
 * Space Complexity:
 * - O(N! * N) {Output storage} + O(N) {Recursion Stack}
 *
 * Better Approach intuition:
 * - Generate all the permutations.
 * - Store them inside a Set<List<Integer>>.
 * - This avoids explicit contains() checking.
 *
 * Time Complexity:
 * - O(N * N!)
 * Space Complexity:
 * - O(N * N!) {Output storage} + O(N) {Recursion depth} + O(N * N!) {Set storage}
 *
 * Why this is still not optimal?
 * - Hashing takes up to O(N) cost.
 * - We are still generating duplicates and removing them after generation.
 *
 * Optimal Approach (Used below):
 * - Sort the array first.
 * - Sorting ensures all the duplicates are adjacent.
 * - Use boolean[] used to track chosen elements.
 * - At every recursion level:
 *      Iterate from 0 to n-1.
 * - Skip condition:
 *      If used[i] -> continue.
 *      if i > 0 And nums[i] == nums[i-1] And !used[i-1].
 * - Meaning that among duplicates, only the leftmost duplicate can form the valid permutation at that recursion level.
 * - Otherwise:
 *      Include nums[i].
 *      Mark used[i] as true.
 *      Recurse
 *      Backtrack(remove + unmark).
 *
 * Time Complexity:
 * - O(N * N!).
 * Space Complexity:
 * - O(N * N!) {Output storage} + O(N) {Recursion depth} + O(N) {boolean[]}
 *
 * Notes:
 * - Why sorting works?
 * - Duplicates create identical subtree structure.
 * - We prevent duplicate branches at the same recursion level.
 * - Ensures each unique permutation is generated exactly once.
 */

package backtracking;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class PermutationsII {

    public static void main(String[] args) {
        System.out.println(permuteUnique(new int[]{1,2,2}));
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        boolean[] used = new boolean[n];
        helper(n, nums, used, new ArrayList<>(), permutations);
        return permutations;
    }

    public static void helper(int n, int[] nums, boolean[] used, List<Integer> partialAns,
                       List<List<Integer>> permutations){

        if(partialAns.size() == n){
            permutations.add(new ArrayList<>(partialAns));
            return;
        }

        for(int i = 0; i < n; i++){

            if(used[i]) continue;

            if(i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;

            used[i] = true;
            partialAns.add(nums[i]);
            helper(n, nums, used, partialAns, permutations);
            used[i] = false;
            partialAns.remove(partialAns.size() - 1);
        }
    }
}
