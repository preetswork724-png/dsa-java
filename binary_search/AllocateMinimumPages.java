/**
 * Problem:
 * <Allocate Minimum Pages>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1>
 *
 * Pattern:
 * <Binary Search on answer>
 *
 * Brute Force Intuition:
 * - Try all possible ways of distributing x books.
 * - We must try all the contiguous partitions of size k.
 * - Calculate the maximum number of pages allocated to student for each split.
 * - Take minimum among those maximums.
 *
 * - Why it is inefficient?
 * - It has exponential time complexity.
 *
 * Time Complexity:
 * - O(branching ^ maxOperations)
 * Space Complexity:
 * - O(n + maxOperations)
 *
 * Better Approach intuition:
 * - Try every possible maximum page value and check feasibility.
 * - Linear scan on the range from max(arr) to sum of all the pages in the array.
 * - For a candidate page, if the sum of pages + curr pages exceeds the limit.
 * - Then allocate that book to the student and make the sum equal to the current pages in a book.
 * - If we can fit within x pages using ≤ k students, then we can always adjust to use exactly k students.
 *
 * Time Complexity:
 * - O(N * (sum - max(arr)))
 * Space Complexity:
 * - O(1)
 *
 * Why this is still not optimal?
 * - Linear scan on an integer range is not the best optimal solution to the problem.
 *
 * Optimal Approach (Used below):
 * - The range is monotonic, so we can implement binary search.
 * - If we are able to fit within x pages using <= k students then we can adjust exactly k students.
 * - Therefore, minimize the answer further.
 *
 * Time Complexity:
 * - O(N * log M) {where M = sumOfPages - max(arr)}
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Do not make the pagesSum = 0, if it exceeds the limit.
 * - First allocate that book to the student.
 * - Make the pagesSum = curr pages in a book.
 *
 * Mistake log:
 * - Directly reduced the pagesSum to 0 after it exceeded the limit.
 */
package binary_search;

public class AllocateMinimumPages {

    public static void main(String[] args) {
        System.out.println(findPages(new int[]{12, 34, 67, 90}, 2));
    }

    public static int findPages(int[] arr, int k) {

        int n = arr.length, min = Integer.MIN_VALUE, sumOfPages = 0;

        if(k > n) return -1;

        for(int pages : arr){
            min = Math.max(min, pages);
            sumOfPages += pages;
        }

        int low = min, high = sumOfPages, minPages = high;

        while(low <= high){

            int mid = low + (high - low) / 2;

            if(canAllocate(mid, arr, k)){
                minPages = mid;
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }

        }
        return minPages;

    }

    public static boolean canAllocate(int minPages, int[] arr, int k){
        int students = 1, pagesSum = 0;

        for(int pages : arr){

            if(pagesSum + pages > minPages){
                students++;
                pagesSum = pages;
            }
            else{
                pagesSum += pages;
            }

        }
        return students <= k;
    }
}
