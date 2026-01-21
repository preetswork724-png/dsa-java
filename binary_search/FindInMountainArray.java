/**
 * Problem:
 * < Find in Mountain Array>
 *
 * Link:
 * <https://leetcode.com/problems/find-in-mountain-array/description/>
 *
 * Pattern:
 * <Mountain / Bitonic Array>
 *
 * Brute Force Intuition:
 * - Linearly traverse the array and return / store the first index where the target is found.
 *
 * - Why it is inefficient?
 * - Brute force requires O(N) time to find the target in a mountain array.
 * - Loss of given information that the array is bitonic.
 * - While iterating for N elements, the most likely will exceed the limit of 100 calls to mountainArr.get().
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used Below):
 * - The array is sorted in 2 parts:
 * - All the values are sorted in ascending order before the peak index.
 * - All the values are sorted in descending order after the peak index.
 * - Due to this sorted nature, Binary search can be applied.
 * - Find the peak / pivot index.
 * - Binary search in the left half to find target.
 * - Binary search in the right half to find target if not found in the right half.
 *
 * Why better approach is the optimal one?
 * - The array is sorted, so any comparison-based search for an element has a lower bound of Ω(log N).
 * - Binary Search achieves this bound by eliminating half of the search space at every step.
 * - Each element is considered at most once in the decision path, and no linear scan is required.
 * - Therefore, the time complexity is O(log N), which matches the theoretical lower bound for searching in a sorted array, making this approach optimal.
 *
 * Time Complexity:
 * - O(log N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - You can implement Order Agnostic Binary Search instead of writing two separate binary search algorithms for both ascending and descending.
 */

package binary_search;

interface MountainArray {
      public int get(int index);
      public int length();
}

class MountainArrayImp1 implements MountainArray{
    private int[] arr;

    public MountainArrayImp1(int[] arr){
        this.arr = arr;
    }

    public int get(int index){
        return arr[index];
    }

    public int length(){
        return arr.length;
    }
}
public class FindInMountainArray {


    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,4,3,1};
        MountainArray mountainArr = new MountainArrayImp1(arr);

        FindInMountainArray obj = new FindInMountainArray();
        int target = 3;

        int ans = obj.findInMountainArray(target, mountainArr);
        System.out.println(ans);
    }

    public int findInMountainArray(int target, MountainArray mountainArr) {

        int peak = findPeak(mountainArr);

        int left = binarySearch(mountainArr, 0, peak, target, true);
        if (left != -1) return left;

        return binarySearch(mountainArr, peak + 1, mountainArr.length() - 1, target, false);
    }

    public int binarySearch(MountainArray arr, int l, int r, int target, boolean asc) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midVal = arr.get(mid);

            if (midVal == target) return mid;

            if (asc) {
                if (midVal < target) l = mid + 1;
                else r = mid - 1;
            } else {
                if (midVal > target) l = mid + 1;
                else r = mid - 1;
            }
        }
        return -1;
    }

    public int findPeak(MountainArray arr) {
        int l = 0, r = arr.length() - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            int midVal = arr.get(mid);
            int nextVal = arr.get(mid + 1);

            if (midVal > nextVal) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}
