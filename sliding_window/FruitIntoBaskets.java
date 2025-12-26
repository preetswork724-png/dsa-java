/**
 * Problem:
 * <Fruit Into Baskets>
 *
 * Link:
 * <https://leetcode.com/problems/fruit-into-baskets/description/>
 *
 * Pattern:
 * <Sliding Window>
 *
 * Brute Force Intuition:
 * - Try every possible starting point.
 * - Extend to right until you have more than 2 distinct fruits to appear.
 * - Stop when you have more than 2 distinct fruits.
 * - Track max length.
 *
 * - Why it is inefficient?
 * - We waste time checking overlapping window intervals.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Still two loops.
 * - But instead if using Set each time, we maintain frequency counts.
 * - Using a Set works with brute force, because we stop as soon as we find a third fruit.
 * - A set only detects absence / presence of an element.
 * - But in order to implement an optimal approach using frequency counts we need a Map.
 * - As removing from the set means that an element is no longer part of the window but that element might have appeared later.
 *
 * - Why it is still not optimal?
 * - Still not ideal because we keep starting new windows again and again.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used Below):
 * - Maintain a window between left and right pointers where the number of type of fruits is exactly 2.
 * - Use Map for frequency counts.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Shrink when distinct chars in the current window exceed k.
 * - Expand when distinct chars in the current window is less than or equal to k.
 */
package sliding_window;
import java.util.Map;
import java.util.HashMap;
public class FruitIntoBaskets {

    public static void main(String[] args) {
        System.out.println(totalFruit(new int[]{1,2,1}));
    }

    public static int totalFruit(int[] fruits) {
        int n = fruits.length, left = 0, maxLen = 0;

        Map<Integer, Integer> freq = new HashMap<>();

        for(int right = 0; right < n; right++){

            freq.put(fruits[right], freq.getOrDefault(fruits[right], 0) + 1);

            while(freq.size() > 2) {
                int fruit = fruits[left];
                freq.put(fruit, freq.getOrDefault(fruit, 0) - 1);
                if (freq.get(fruit) == 0) freq.remove(fruit);
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
