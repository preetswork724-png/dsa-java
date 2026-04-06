/**
 * Problem:
 * <Word Break>
 *
 * Link:
 * <https://leetcode.com/problems/word-break/>
 *
 * Pattern:
 * <Dynamic Programming / String Partitioning / Trie Optimization>
 *
 *
 * Brute Force Intuition:
 * - Try all possible splits of the string.
 * - For each prefix:
 *     - If it exists in dictionary → recursively solve remaining string.
 *
 * - Why it is inefficient?
 * - Exponential number of splits.
 * - Same substrings recomputed multiple times.
 *
 * Time Complexity:
 * - O(2^N)
 *
 * Space Complexity:
 * - O(N) recursion stack
 *
 *
 * Better Approach Intuition (Memoization):
 * - Store results of subproblems (starting index).
 * - Avoid recomputation by caching results.
 *
 * - Why it is better?
 * - Each index is computed only once.
 *
 * - Why it is still not optimal?
 * - Substring creation is costly (O(N)).
 * - HashSet lookup still involves string hashing/comparison.
 *
 * Time Complexity:
 * - O(N^2)
 *
 * Space Complexity:
 * - O(N)
 *
 *
 * Better++ Approach (Bottom-Up DP - Used Below):
 * - Define:
 *     dp[i] = true if s[0...i-1] can be segmented
 *
 * - Transition:
 *     - For each i:
 *         - Check all j < i:
 *             if dp[j] == true AND s[j...i-1] in dictionary
 *             → dp[i] = true
 *
 * - Why this works?
 * - Builds solution from smaller prefixes.
 * - Avoids recursion overhead.
 *
 * - Key Insight:
 * - "If a prefix is valid, extend it using dictionary words"
 *
 * Time Complexity:
 * - O(N^2)
 *
 * Space Complexity:
 * - O(N)
 *
 *
 * Optimal Approach (Trie + DP Optimization):
 * - Build Trie from dictionary words.
 *
 * - For each index i where dp[i] == true:
 *     - Traverse Trie from s[i...]
 *     - If a word ends at j → mark dp[j+1] = true
 *
 * - Why it is optimal?
 * - Avoids substring creation.
 * - Prefix matching is done character-by-character.
 *
 * Time Complexity:
 * - O(N * L)
 *   where L = max word length
 *
 * Space Complexity:
 * - O(W * L) (Trie)
 *
 *
 * Notes:
 * - Same word can be reused multiple times.
 * - Order of words does not matter.
 *
 * - Important conditions:
 *     - dp[0] = true (empty string)
 *     - Only extend from valid prefixes
 *
 * - Common mistakes:
 * - Using greedy approach (fails for many cases)
 * - Not handling overlapping subproblems
 * - Ignoring substring cost
 *
 * - Mental model:
 * - "At each index, decide if a valid cut can be made"
 *
 * - Edge cases:
 * - Empty string → true
 * - No valid segmentation → false
 * - Single word equal to s → true
 *
 * - Key takeaway:
 * - Core problem is DP (partitioning)
 * - Trie is an optimization for faster lookup
 */

package trees._10_SpecialTrees.Trie;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();

        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for(int i = 0; i <= n; i++){
            for(int j = 0; j < i; j++){

                if(dp[j] && set.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
