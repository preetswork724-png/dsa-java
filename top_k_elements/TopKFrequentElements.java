/**
 * Problem:
 * <Top K Frequent Elements>
 *
 * Link:
 * <https://leetcode.com/problems/top-k-frequent-elements/description/>
 *
 * Pattern:
 * <Min Heap + HashMap>
 *
 * Brute Force Intuition:
 * - Maintain a boolean array to track the numbers whose frequency have been already considered.
 * - Count the frequency of a number using nested loops.
 * - Maintain a List<List<Integer>> where each list stores the number and its frequency.
 * - Sort the list by the frequency of the number.
 * - Fetch the numbers from n-k to k-1.
 *
 * - Why it is inefficient?
 * - Computing frequency of an element takes O(N^2) in worst case.
 * - We sort the entire list when we only want the k most frequent numbers.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Map + Sorting.
 * - Use A map to cunt the frequency of number.
 * - Convert the map to a nested list.
 * - Sort the list by frequency of a number.
 * - Fetch the elements from n-k to n-1.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * - Why this is still not optimal?
 * - We sort the entire list when we only want the k most frequent numbers
 *
 *
 * Optimal Approach (Used below):
 * - Map + min heap.
 * - Use A map to count the frequency of number.
 * - Define the sorting logic inside the comparator.
 * - Maintain a min heap of size k.
 *
 * Time Complexity:
 * - O(N log K)
 * Space Complexity:
 * - O(N) map + O(K) heap
 *
 * Notes:
 * - The comparator defines ordering within the heap, but we still need explicit logic to control the size of the heap and decide which elements belong in the top k .
 */

package top_k_elements;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
public class TopKFrequentElements {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1,1,1,2,2,3}, 2)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        if(n == 1) return nums;

        Map<Integer, Integer> freqMap = new HashMap<>();

        for(int num : nums) freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
                (a, b) -> freqMap.get(a) - freqMap.get(b)
        );

        for(int key : freqMap.keySet()){
            int freq = freqMap.get(key);
            minHeap.add(key);

            if(minHeap.size() > k){
                minHeap.poll();
            }
        }

        int[] topKFreq = new int[k];

        for(int i = 0; i < k; i++){
            topKFreq[i] = minHeap.poll();
        }

        return topKFreq;
    }
}
