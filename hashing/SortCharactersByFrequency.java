/**
 * Problem:
 * <Sort Characters By Frequency>
 *
 * Link:
 * <https://leetcode.com/problems/sort-characters-by-frequency/>
 *
 * Pattern:
 * <Hashing / Sorting / Frequency Count>
 *
 *
 * Brute Force Intuition:
 * - Count frequency of each character using a HashMap.
 * - Transfer entries into a List of Pairs.
 * - Sort the list by frequency in descending order using Collections.sort.
 * - Rebuild result by appending each character freq times.
 *
 * - Why it is inefficient?
 * - Sorting takes O(K log K) where K = distinct characters.
 * - Creating a custom Pair list adds unnecessary overhead.
 * - For large inputs with many distinct characters, sort dominates.
 *
 * Time Complexity:
 * - O(N + K log K)   N = string length, K = distinct characters
 *
 * Space Complexity:
 * - O(N + K)         HashMap + Pair list + StringBuilder
 *
 *
 * Better Approach Intuition (Sorting on Map.Entry):
 * - Same idea as brute force but removes the need for a custom Pair class.
 * - Use map.entrySet() directly and sort entries by value descending.
 * - Cleaner and more idiomatic Java — no extra class, no manual transfer.
 *
 * - Why it is better?
 * - Eliminates the overhead of creating a separate Pair object per character.
 * - Code is more concise and readable.
 *
 * - Why it is still not optimal?
 * - Still pays O(K log K) for sorting.
 * - Sorting is unnecessary when frequency range is bounded and known (1..N).
 *
 * Time Complexity:
 * - O(N + K log K)
 *
 * Space Complexity:
 * - O(N + K)
 *
 *
 * Optimal Approach (Bucket Sort):
 * - Observation:
 *   Character frequencies range from 1 to N (length of string).
 *   This bounded range makes bucket sort applicable → no comparison sort needed.
 *
 * - Define:
 *   buckets[i] = list of characters whose frequency is exactly i.
 *
 * - Steps:
 *   1. Count frequency of each character → HashMap.
 *   2. Place each character into buckets[freq].
 *   3. Iterate buckets from index N down to 1.
 *   4. For each character in bucket[i], append it i times to result.
 *
 * - Why it is optimal?
 * - Replaces O(K log K) sort with O(N) bucket traversal.
 * - Overall linear time.
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(N)   HashMap + buckets array + StringBuilder
 *
 *
 * Key Insight:
 * - Frequencies are always bounded by string length N.
 * - Bounded range → bucket sort → eliminates comparison sort entirely.
 * - Same Count → Sort → Reconstruct pattern, but "sort" becomes a bucket scan.
 *
 * Notes:
 * - Bucket index represents frequency → iterate high to low for descending order.
 * - Multiple characters can share the same bucket (same frequency).
 * - StringBuilder preferred over String concatenation → O(1) per append vs O(N).
 * - 'A' and 'a' are treated as different characters → HashMap handles naturally.
 * - Digits and spaces are valid characters → no special handling needed.
 *
 * - Common mistakes:
 * - Using String += in loop instead of StringBuilder → O(N^2) time ❌
 * - Sorting map.keySet() directly → HashMap has no order guarantee ❌
 * - Bucket array sized N instead of N+1 → frequency N maps to index N → off by one ❌
 * - Assuming only lowercase letters → breaks for uppercase, digits, spaces ❌
 *
 * - Mental model:
 * - "Count → Bucket by frequency → Reconstruct from highest bucket down"
 * - Same pattern used in: Top K Frequent Elements, Task Scheduler,
 *   Reorganize String.
 *
 * Edge cases:
 * - Single character string      → return it as is
 * - All characters same          → single bucket at index N, return unchanged
 * - All characters distinct      → all in bucket[1], any order valid
 * - Mix of uppercase + lowercase → 'A' and 'a' are different characters
 * - String contains digits/spaces → handled naturally by HashMap
 *
 * Comparison of Approaches:
 * ┌────────────────────────────┬──────────────────┬─────────────────┐
 * │ Approach                   │ Time             │ Space           │
 * ├────────────────────────────┼──────────────────┼─────────────────┤
 * │ Brute (Pair + sort)        │ O(N + K log K)   │ O(N + K)        │
 * │ Better (Map.Entry + sort)  │ O(N + K log K)   │ O(N + K)        │
 * │ Optimal (Bucket Sort)      │ O(N)             │ O(N)            │
 * └────────────────────────────┴──────────────────┴─────────────────┘
 *
 * Key takeaway:
 * - Whenever a value is bounded in range, ask: "Can I use bucket sort?"
 * - Bounded frequency → bucket sort → O(N) replaces O(N log N).
 * - This substitution is the core optimization across all frequency problems.
 */

package hashing;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

class Pair {
    char ch;
    int freq;

    public Pair(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }
}

public class SortCharactersByFrequency {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        List<Pair> list = new ArrayList<>();

        for (char ch : map.keySet()) {
            list.add(new Pair(ch, map.get(ch)));
        }

        Collections.sort(list, (a, b) -> {
            if (b.freq != a.freq) return b.freq - a.freq;
            else return b.ch - a.ch;
        });

        StringBuilder res = new StringBuilder();

        for (Pair pair : list) {
            char ch = pair.ch;
            int freq = pair.freq;

            while (freq-- > 0) {
                res.append(ch);
            }
        }
        return res.toString();
    }
}
