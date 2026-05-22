/**
 * Problem:
 * <Top K Frequent Words>
 *
 * Link:
 * <https://leetcode.com/problems/top-k-frequent-words/>
 *
 * Pattern:
 * <Heap / Priority Queue>
 *
 * Brute Force Intuition:
 * - Count the frequency of every word.
 * - Store all unique words in a list.
 * - Sort the list:
 *      Higher frequency first.
 *      Lexicographically smaller word first if frequencies are same.
 * - Return first k words.
 *
 * - Why it is inefficient?
 * - Entire list gets sorted even though only top k elements are needed.
 * - Sorting all elements is unnecessary work.
 *
 * Time Complexity:
 * - O(N + M log M)
 *      N = total words
 *      M = unique words
 *
 * Space Complexity:
 * - O(M)
 *
 * Better Approach Intuition:
 * - Count the frequency of every word.
 * - Use a MaxHeap:
 *      Higher frequency gets higher priority.
 *      Lexicographically smaller word gets higher priority.
 * - Insert all unique words into heap.
 * - Extract top k elements.
 *
 * - Why it is better?
 * - Heap gives highest priority element quickly.
 * - No need for full sorting traversal repeatedly.
 *
 * - Why it is still not optimal?
 * - All M elements are inserted into heap.
 * - Heap operations become O(M log M).
 *
 * Time Complexity:
 * - O(N + M log M)
 *
 * Space Complexity:
 * - O(M)
 *
 * Optimal Approach Intuition:
 * - Count the frequency of every word.
 *
 * - Maintain a MinHeap of size k.
 * - Heap stores the WORST candidate among current top k at the top.
 *
 * - Comparator Rules:
 *      Lower frequency = smaller
 *      If same frequency:
 *          Lexicographically larger word = smaller
 *
 * - Why?
 * - Because worse elements should be removed first.
 *
 * - Insert every unique word into heap.
 * - If heap size exceeds k:
 *      Remove the top element.
 *
 * - At the end:
 *      Heap contains the best k elements.
 *
 * - Important:
 * - Heap does NOT store elements in final sorted order.
 * - Polling from MinHeap gives:
 *      Worst → Best
 *
 * - Therefore:
 *      Reverse the result at the end.
 *
 * Time Complexity:
 * - O(N + M log K)
 *
 * Space Complexity:
 * - O(M + K)
 *
 * Notes:
 * - Why MinHeap and not MaxHeap?
 * - MaxHeap keeps best element on top.
 * - But we only need top k elements.
 * - MinHeap efficiently removes the worst element among current top k.
 *
 * - Why lexicographically larger word is considered smaller in heap?
 * - If frequencies are same:
 *      Lexicographically smaller word should stay.
 * - Therefore:
 *      Lexicographically larger word should be removed first.
 *
 * - Why reverse at the end?
 * - MinHeap returns:
 *      Worst → Best
 * - But answer requires:
 *      Best → Worst
 *
 * - Important Java Heap Observation:
 * - Iterating over PriorityQueue does NOT guarantee sorted order.
 * - Always poll elements explicitly from heap.
 */

package top_k_elements;

import java.util.*;

public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {

        Map<String, Integer> freqMap = new HashMap<>();

        for(String word : words){
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> minHeap = new PriorityQueue<>(
                (a, b) -> {

                    if(freqMap.get(a).equals(freqMap.get(b))){
                        return b.compareTo(a);
                    }

                    return freqMap.get(a) - freqMap.get(b);
                }
        );

        for(String word : freqMap.keySet()){

            minHeap.offer(word);

            if(minHeap.size() > k){
                minHeap.poll();
            }
        }

        List<String> result = new ArrayList<>();

        while(!minHeap.isEmpty()){
            result.add(minHeap.poll());
        }

        Collections.reverse(result);

        return result;
    }
}
