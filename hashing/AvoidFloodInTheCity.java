/**
 * Problem:
 * <Avoid Flood in The City>
 *
 * Link:
 * <https://leetcode.com/problems/avoid-flood-in-the-city/description/>
 *
 * Pattern:
 * <TreeSet + HashMap>
 *
 * Brute Force Intuition:
 * - For each lake in the rains[]:
 * - Check if the lake is already full.
 * - If the lake is already full, then check are there any dry days between the last rain on that lake and the current.
 * - prev < dry day < i.
 * - If there aren't any, then it is impossible to prevent flood.
 * - Else, dry the lake on the earliest dry day > prev rain day on that lake.
 * - Update the index in the output array accordingly.
 *
 * - Why it is inefficient?
 * - Repeated checks if the lake is already full or not.
 * - Repeated checks for dry days.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used below):
 * - Instead of always checking if it has rained on the lake previously or not, remember it.
 * - Use a HashMap which tracks the already full lake.
 * - Instead of checking for dry days again and again, remember it.
 * - Use TreeSet.
 * - But why do we need to use a TreeSet?
 * - Because TreeSet helps us to store the dry days in a sorted order.
 * - For a lake that is already full:
 * - Find a dry day > prev rain day using ceiling().
 * - If it returns null, then there are no dry days in between.
 * - If there is a dry day, then mark that dry day index as the current lake which was dried.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 * 
 * Why better approach is the optimal one?
 * - The problem is equivalent to:
 * - Scheduling tasks before deadline and the optimal strategy for such problems is to always assign the earliest possible available resource.
 * - That means choosing the smallest dry day strictly greater than lastRain[lake].
 *
 * Notes:
 * - Why the deque does not work here?
 * - The operation that we need is:
 * - Give me the earliest dry day that is strictly after lastRain[lake].
 * - This is a successor query.
 * - Deque only supports pop front, pop back and peek front / back.
 * - It does not support first element greater than X without scanning -> O(N).
 *
 * Mistake log:
 * - Tried to use the first dry day seen.
 * - Didn't think that the dry day should be between last rain day and the i.
 */
package hashing;
import java.util.*;

public class AvoidFloodInTheCity {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(avoidFlood(new int[]{1,2,3,4})));
    }

    public static int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];

        Map<Integer, Integer> lastRainDay = new HashMap<>();
        TreeSet<Integer> dryDays = new TreeSet<>();

        for(int i = 0; i < n; i++){
            int lake = rains[i];

            if(lake == 0){
                dryDays.add(i);
            }
            else{
                if(lastRainDay.containsKey(lake)){

                    Integer dryIndex = dryDays.ceiling(lastRainDay.get(lake) + 1);
                    if(dryIndex == null) return new int[]{};

                    ans[dryIndex] = lake;
                    dryDays.remove(dryIndex);
                }
                lastRainDay.put(lake, i);
                ans[i] = -1;
            }
        }
        return ans;
    }
}
