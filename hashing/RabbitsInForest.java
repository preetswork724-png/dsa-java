/**
 * Problem:
 * <Rabbits in Forest>
 *
 * Link:
 * <https://leetcode.com/problems/rabbits-in-forest/description/>
 *
 * Pattern:
 * <Hashing>
 *
 * Brute Force Intuition:
 * - Try to group rabbits manually.
 * - For each rabbit with answer x:
 * - He must belong to a color group of size exactly x + 1.
 * - If we already created such a group and it's not full, put him there.
 * - Otherwise, start a new group of size x + 1.
 * - Maintain a list of groups (each group has required size = x + 1 and current filled count)
 * - For each arr[i]:
 * - Try to place it into an existing group of size x + 1 that isn't full.
 * - If not possible, create a new group of size x + 1.
 * - Sum sizes of all groups.
 *
 * - Why it is inefficient?
 * - For each rabbit, may scan all groups.
 * - Quadratic matching.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N) {Storing groups}
 *
 * Better and Optimal Approach (Used below):
 * - If a rabbit says x:
 * - Then he belongs to exactly a group of size x + 1.
 * - Suppose, x = 2 appears k times in the array.
 * - Each color group can hold at most x + 1 rabbits.
 * - So number of groups needed for value x is:
 * - k / x + 1.
 * - Each such group contributes x + 1 rabbits.
 * - So, total rabbits for this x is:
 * - [k / x + 1] * (x + 1).
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why better approach is the optimal one?
 * - We group rabbits by the responses given by them.
 * - Instead of re-creating and checking groups for vacancy, we already create minimum number of groups.
 *
 * Notes:
 * - Why ceiling is needed?
 * - If 3 rabbits say "1"
 * - Then groupSize = 2, freq = 3.
 * - We need ceil(3 / 2) = 2 groups.
 * - Total rabbits = 2 * 2 = 4.
 */

package hashing;
import java.util.Map;
import java.util.HashMap;
public class RabbitsInForest {

    public static void main(String[] args) {
        System.out.println(numRabbits(new int[]{1,1,2}));
    }

    public static int numRabbits(int[] answers) {
        Map<Integer, Integer> rabbitMap = new HashMap<>();

        for (int ans : answers) rabbitMap.put(ans, rabbitMap.getOrDefault(ans, 0) + 1);

        int minRabbits = 0;

        for (int key : rabbitMap.keySet()) {
            int count = rabbitMap.get(key);
            int groupSize = key + 1;
            int groups = (count + groupSize - 1) / groupSize;

            minRabbits += (groups * groupSize);
        }

        return minRabbits;
    }
}