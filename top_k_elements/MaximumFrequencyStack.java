/**
 * Problem:
 * <Maximum Frequency Stack>
 *
 * Link:
 * <https://leetcode.com/problems/maximum-frequency-stack/description/>
 *
 * Pattern:
 * <K most frequent elements>
 *
 * Brute Force Intuition:
 * - Maintain a simple list.
 * - push():
 * - Add value to the list.
 * - pop():
 * - Scan the list and put the frequencies of elements in the map.
 * - Find the maximum frequency.
 * - Scan the list from end to start and check for each element's frequency in the map.
 * - If the element's frequency = max frequency, remove it from the list.
 *
 * - Why it is inefficient?
 * - Scans the list to put the frequency of the elements -> O(N).
 * - Rescans the list from the end to the start -> O(N).
 * - Shifting operation in the list -> O(N).
 *
 * Time Complexity:
 * - pop() :- O(N)
 * - push() :- O(1)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach intuition:
 * - Max Heap + Map.
 * - Use the map to store the frequency of the elements and to maintain the current state.
 * - Use Max Heap as a log event where every entry in the maxHeap logs:
 * - The current frequency of that element, time stamp and the element itself.
 * - Set the comparator such as the maxHeap maintains the order inside sorted by the most frequent element to the least frequent one.
 * - If frequencies tie, return the most recent one.
 * - push():
 * - Updates the frequency of the element in the map.
 * - Adds an entry to the map with updated time stamp. For example {frequency, time++, element}.
 * - pop():
 * - poll() the top entry from the heap.
 * - Decrement its frequency inside the map which is basically updating the current state.
 * - Return the value.
 *
 * Time Complexity:
 * - push() and pop() :- O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Why this is still not optimal?
 * - maxHeap gives a overhead of O(log n) for push() and pop() operations which can be reduced to O(1).
 *
 * Optimal Approach (Used below):
 * - As the problem screams, most frequent and the most recent one:
 * - A map and stack would be the ideal choice.
 * - Maintain a map which stores the elements with their frequency.
 * - Now we have a range of frequencies and elements with this particular frequency and that too we need to pop the most frequent one.
 * - Therefore, maintain a Map<Integer, Stack<Integer>>.
 * - Track the maxFreq globally.
 * - push():
 * - Update the frequency of the element and track the maxFreq.
 * - Add that element to the elements stored to that corresponding frequency, if no elements are stored, then out that element and a new Stack corresponding to it in the map.
 * - pop():
 * - Pop out the most frequent and the recent one.
 * - Fetch the maxFreq and pop out the most recent element stored corresponding to that frequency.
 * - If there are no elements with the maxFreq, decrement the maxFreq.
 * - Update the current state.
 *
 * Time Complexity:
 * - push() and pop() :- O(1)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Use ArrayDeque for faster operations.
 */

package top_k_elements;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

class FreqStack {

    private Map<Integer, Integer> map;                 // value -> freq
    private Map<Integer, ArrayDeque<Integer>> group;   // freq -> stack
    private int maxFreq;

    public FreqStack() {
        map = new HashMap<>();
        group = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        int f = map.getOrDefault(val, 0) + 1;
        map.put(val, f);

        maxFreq = Math.max(maxFreq, f);

        group.computeIfAbsent(f, k -> new ArrayDeque<>()).push(val);
    }

    public int pop() {
        int val = group.get(maxFreq).pop();

        map.put(val, map.get(val) - 1);

        if (group.get(maxFreq).isEmpty()) {
            maxFreq--;
        }
        return val;
    }
}

public class MaximumFrequencyStack {
    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();

        freqStack.push(5);
        freqStack.push(6);
        freqStack.push(7);
        freqStack.push(7);
        freqStack.push(8);
        freqStack.push(6);
        freqStack.push(5);

        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
    }
}
