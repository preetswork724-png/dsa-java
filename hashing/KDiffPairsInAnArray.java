/**
 * Problem:
 * <K-diff Pairs in an Array>
 *
 * Link:
 * <https://leetcode.com/problems/k-diff-pairs-in-an-array/description/>
 *
 * Pattern:
 * <Frequency count>
 *
 * Brute Force Intuition:
 * - Try all the unique pairs.
 * - Maintain a set of strings where each key is the concatenation of a unique pair.
 * - In the string keep the first char as the minimum num and the second as the maximum out of 2.
 * - This way, only unique pairs will be added to the string.
 *
 * - Why it is inefficient?
 * - Checks all the pairs.
 * - Uses extra memory.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Sort the array.
 * - Use 2-pointers to calculate the difference between any 2-pairs.
 * - As difference depends on the distance between two values, place the pointers next to each other.
 * - To decrease the difference, move the left pointer.
 * - To increase the difference, move the right pointer.
 * - Remember to skip when both pointers point to the same index.
 * - Remember to skip the duplicates.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(1)
 *
 * Why this is still not optimal?
 * - Requires sorting the array.
 *
 * Optimal Approach (used below):
 * - We store all the numbers with their corresponding frequency.
 * - For a number, if number + k exists in the map, count the pair.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - If k == 0, then only count the pair if the number has occurred more than one time.
 */
package hashing;
import java.util.Map;
import java.util.HashMap;
public class KDiffPairsInAnArray {

    public static void main(String[] args) {
        System.out.println(findPairs(new int[]{3,1,4,1,5}, 2));
    }

    public static int findPairs(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);

        int count = 0;

        for(int key : map.keySet()){

            if(k == 0){
                if(map.get(key) > 1) count++;
            }
            else{
                if(map.containsKey(key + k)) count++;
            }
        }
        return count;
    }
}
