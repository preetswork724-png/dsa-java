/**
 * Problem:
 * <Count Distinct Substrings>
 *
 * Link:
 * <https://www.geeksforgeeks.org/count-number-of-distinct-substring-in-a-string/>
 *
 * Pattern:
 * <Trie / String Processing>
 *
 * Brute Force Intuition:
 * - Generate all possible substrings using two loops.
 * - Store them in a list.
 * - For each substring, check if it already exists.
 *
 * - Why it is inefficient?
 * - Checking duplicates using list.contains() is costly.
 * - Repeated string comparisons.
 * - Heavy string creation overhead.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(N^2)
 *
 *
 * Better Approach Intuition:
 * - Generate all substrings.
 * - Store them in a HashSet to ensure uniqueness.
 *
 * - Why it is better?
 * - Set removes duplicate-check overhead (O(1) avg).
 *
 * - Why it is still bad?
 * - Still creating all substrings explicitly.
 * - String creation cost is O(N), leading to hidden O(N^3).
 * - High memory usage due to storing strings.
 *
 * Time Complexity:
 * - O(N^3) (due to substring creation)
 * Space Complexity:
 * - O(N^2)
 *
 *
 * Better++ Approach (Trie - Used Below):
 * - Use Trie to store substrings character by character.
 * - Start from every index i:
 *     - Extend substring till j
 *     - Insert into Trie
 * - Each new node created represents a new distinct substring.
 *
 * - Why this works?
 * - Common prefixes are shared → avoids duplication.
 * - No need to store full strings.
 *
 * - Key Insight:
 * - Number of distinct substrings = number of new Trie nodes created
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N^2)
 *
 *
 * Optimal Approach (Advanced):
 * - Use Suffix Automaton
 *
 * - Idea:
 * - Build automaton representing all substrings efficiently.
 * - Count distinct substrings using state transitions.
 *
 * - Why it is optimal?
 * - Processes string in linear time.
 * - Avoids redundant traversal and storage.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 *
 * Notes:
 * - Substring = contiguous sequence
 * - Total substrings = N*(N+1)/2 (but not all are unique)
 *
 * - Trie Insight:
 * - Each new node = one unique substring
 *
 * - Common mistakes:
 * - Inserting only full string instead of all substrings
 * - Using wrong Trie structure (missing array[])
 * - Forgetting to reset node for each starting index
 *
 * - Mental model:
 * - "Start from every index and grow substrings → Trie compresses duplicates"
 *
 * - Edge cases:
 * - Empty string → return 0
 * - All same characters ("aaaa") → fewer unique substrings
 * - All distinct characters → max substrings
 *
 * - Key takeaway:
 * - Trie avoids string duplication but is still O(N^2)
 * - True optimal solution requires Suffix Automaton
 */

package trees._10_SpecialTrees.Trie;

public class CountOfDistinctSubstrings {
    static class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    static TrieNode root = new TrieNode();

    public static int countSubs(String s) {

        int count = 0, n = s.length();

        for(int i = 0; i < n; i++){

            TrieNode node = root;

            for(int j = i; j < n; j++){

                int idx = s.charAt(j) - 'a';

                if(node.children[idx] == null){
                    node.children[idx] = new TrieNode();
                    count++;
                }

                node = node.children[idx];
            }
        }
        return count;
    }
}
