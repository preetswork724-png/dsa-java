/**
 * Problem:
 * <Group the People Given the Group Size They Belong To>
 *
 * Link:
 * <https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/description/>
 *
 * Pattern:
 * <HashMap>
 *
 * Brute Force Intuition:
 * - For each person:
 * - Try to place them into an existing group of required size.
 * - If no such group exists, then create a new group.
 * - Maintain groups and check size every time.
 *
 * - Why it is inefficient?
 * - Repeated scanning of groups.
 * - Inefficient when many group exists.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Better and Optimal Approach (Used below):
 * - Hashing.
 * - Map the indices with their correct group size.
 * - Iterate over the Map.
 * - Add List of that groupSize to the result.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why better approach is the optimal one?
 * - Because we group the indices to their correct groupSize, and then split the indices stored into the lists of correct groupSize.
 * - Mapping indices and then splitting prevents the redundant check for the availability of thr groups.
 *
 * Notes:
 * - Remember to split the indices according to their groupSize.
 *
 * Mistake log:
 * - Did not split the indices correctly.
 */

package hashing;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class GroupThePeopleGivenTheGroupSizeTheyBelongTo {
    public static void main(String[] args) {
        System.out.println(groupThePeople(new int[]{3,3,3,3,3,1,3}));
    }

    public static List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();

        for(int i = 0; i < groupSizes.length; i++){
            map.computeIfAbsent(groupSizes[i], k -> new ArrayList<>()).add(i);
        }

        for(int size : map.keySet()){
            List<Integer> people = map.get(size);

            for(int i = 0; i < people.size(); i += size){
                res.add(new ArrayList<>(people.subList(i, i + size)));
            }
        }
        return res;
    }
}
