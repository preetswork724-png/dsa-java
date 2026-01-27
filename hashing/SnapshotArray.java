/**
 * Problem:
 * <Snapshot Array>
 *
 * Link:
 * <https://leetcode.com/problems/snapshot-array/description/>
 *
 * Pattern:
 * <Hashing>
 *
 * Brute Force Intuition:
 * - Store a full copy of the array every time snap() is called.
 * - Maintain a List<int[]> snapShots.
 * - On snap():
 * - Clone the entire array and store it.
 * - On get(index, id):
 * - Return snapShots.get(id)[index]
 *
 * - Why it is inefficient?
 * - if n = 10^5 and snaps = 10^5, the space becomes 10^10, impossible.
 *
 * Time Complexity:
 * - set - O(1)
 * - get - O(1)
 * - snap - O(N) {Copy of whole array}
 * Space Complexity:
 * - O(N * number_of_snaps)
 *
 * Better Approach Intuition:
 * - Instead of copying the entire array, store only changes since the last snapshot.
 * - We only store indices where a change was made.
 * - For each snapshot, store a map: index -> value.
 * - On get(i, snap_id):
 * - Walk backward from snap_id until you find a snapshot where index i was changed.
 *
 * Time Complexity:
 * - set - O(1)
 * - get - O(1)
 * - snap - O(N) {number_of_snaps} worst case
 * Space Complexity:
 * - O(number_of_sets)
 *
 * Why it is still not optimal?
 * - get() can degrade to O(snaps), too slow.
 *
 * Optimal Approach (Used below):
 * - Maintain a List<List<int[]>> snapshots.
 * - In int[] arr, arr[0] - snapCnt, arr[1] - value.
 * - On snap():
 * - Increment the snap counter.
 * - On set(index, val):
 * - Check if the last snapshot added has the same snapCnt.
 * - If it does, update the value corresponding to that snap.
 * - Else, add a new arr[] at the end of the list.
 * - On get(index, snap_id):
 * - Perform binary search on the snap_ids.
 * - Return the value stored corresponding to the snap_id.
 *
 * Time Complexity:
 * - set - O(1)
 * - get - O(1)
 * - snap - O(log N)
 * Space Complexity:
 * - O(sets)
 *
 * Notes:
 * - Snapshot is just like a picture taken of the array at that snapCnt.
 */
package hashing;
import java.util.List;
import java.util.ArrayList;
public class SnapshotArray {
    private List<List<int[]>> snapShots;
    private int snapCnt;

    public SnapshotArray(int length) {
        snapShots = new ArrayList<>(length);
        this.snapCnt = 0;

        for(int i = 0; i < length; i++){
            List<int[]> list = new ArrayList<>();
            list.add(new int[]{0,0});
            snapShots.add(list);
        }
    }

    public void set(int index, int val) {
        List<int[]> list = snapShots.get(index);

        if(list.get(list.size()-1)[0] == snapCnt){
            list.get(list.size()-1)[1] = val;
        }
        else{
            list.add(new int[]{snapCnt, val});
        }
    }

    public int snap() {
        return snapCnt++;
    }

    public int get(int index, int snap_id) {
        List<int[]> list = snapShots.get(index);
        int low = 0, high = list.size()-1;

        while(low <= high){
            int mid = low + (high - low) / 2;
            if(list.get(mid)[0] <= snap_id) low = mid+1;
            else high = mid-1;
        }
        return list.get(high)[1];
    }
}
