/**
 * Problem:
 * <Remove Duplicates From Sorted Array>
 *
 * Link:
 * <https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/>
 *
 * Pattern:
 * <Two Pointer>
 *
 * Brute Force Intuition:
 * - Maintain a list of the unique elements.
 * - Only add to the list if element does not exist in the list
 *
 * - Why it is inefficient?
 * - Repeated checking of elements in the list. Very slow for large arrays.
 * - Violates the in-place constraint.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Maintain a LinkedHashSet or an auxiliary array to collect unique elements.
 * - Since the array is sorted, duplicates are adjacent.
 *
 * - Why it is still not optimal.
 * - This reduces the complexity is O(N).
 * - However, it requires O(N) extra space and violates the in-place constraint
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Keep 2 pointers i.e slow and fast.
 * - The fast one keeps on scanning the array and moves in each iteration.
 * - The slow one only moves when all the duplicate element.
 * - right -> points to the first misplaced zero.
 * - Everything before left is zero.
 * - Everything after right is one.
 * - Swap and then move the pointers again.
 * - This continues until the pointers cross, ensuring all 0s are on the left and all 1s are on the right.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Performs in-place modifications in the array.
 */
package two_pointers;

public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1,1,2}));
    }

    public static int removeDuplicates(int[] arr)
    {
        int k = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != arr[k]){
                k++;
                arr[k] = arr[i];
            }
        }
        return k + 1;
    }
}
