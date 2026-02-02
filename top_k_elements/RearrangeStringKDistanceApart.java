/**
 * Problem:
 * <Rearrange String k Distance Apart>
 *
 * Link:
 * <https://algo.monster/liteproblems/358>
 *
 * Pattern:
 * <Max Heap and Queue>
 *
 * Brute Force Intuition:
 * - Scan all the 26 tasks.
 * - Choose the one:
 * - Remaining frequency > 0.
 * - Not used in last k positions.
 * - Highest frequency (optional).
 * - Place it.
 *
 * - Why it is inefficient?
 * - Scans all characters each time.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used below):
 * - The problem asks us to place the same character with k gaps in between.
 * - Therefore, we'll use the most frequent character to define the skeleton of the structure.
 * - And the elements with frequency lesser than the maximum frequency will fit in between.
 * - Instead of scanning 26 characters every time:
 * - Use max heap to get the highest frequency instantly.
 * - Handle cooldown using a queue.
 *
 * Time Complexity:
 * - O(N log 26) -> O(N)
 * Space Complexity:
 * - O(1)
 *
 * Why better is the optimal approach here?
 * - Since, we must output 26 characters, the lower bound is O(N).
 * - The heap solution runs in O(N log 26), which simplifies to O(N), which is asymptotically optimal.
 *
 * Notes:
 * - Use queue to store the cooldown characters.
 * - It is important to build the string char by char unlike task scheduler where n+1 tasks were polled directly.
 */

package top_k_elements;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class RearrangeStringKDistanceApart {
    public static void main(String[] args) {
        System.out.println(rearrangeString("aabbcc", 3));
    }

    public static String rearrangeString(String s, int k){
        int[] freq = new int[26];

        for(char ch : s.toCharArray()){
            freq[ch - 'a']++;
        }

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) ->{
                    return b[1] - a[1];
                }
        );

        for(int i = 0; i < 26; i++){
            if(freq[i] > 0){
                maxHeap.add(new int[]{i, freq[i]});
            }
        }

        Queue<int[]> coolDown = new ArrayDeque<>();

        StringBuilder sb = new StringBuilder();

        while(!maxHeap.isEmpty()){

            int[] curr = maxHeap.poll();

            int ch = curr[0], charFreq = curr[1];

            sb.append((char)(ch + 'a'));

            charFreq--;

            coolDown.offer(new int[]{ch, charFreq});

            if(coolDown.size() >= k){
                int[] front = coolDown.poll();
                if(front[1] > 0){
                    maxHeap.add(front);
                }
            }
        }
        return sb.length() == s.length() ? sb.toString() : "";
    }
}
