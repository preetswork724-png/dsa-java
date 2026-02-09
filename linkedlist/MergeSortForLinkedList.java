/**
 * Problem:
 * <Merge Sort for Linked List>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/sort-a-linked-list/1>
 *
 * Pattern:
 * <Sorting>
 *
 * Intuition:
 * - Split the list from the middle, merge the two lists recursively in a sorted manner.
 *
 * Approach:
 * - Find the middle of the Linked List.
 * - Store the left part of the list from head -> mid.
 * - Store the right part of the list from mid.next -> tail.
 * - Detach the list from the middle.
 * - Merge the two lists in a sorted manner.
 * - Apply the above steps recursively.
 *
 * Time Complexity:
 * - O(N Log N)
 * Space Complexity:
 * - O(N) {Recursion Stack}
 *
 * Notes:
 * - Edge case:
 * - Do not forget to write the base case.
 */

package linkedlist;

public class MergeSortForLinkedList {

    public Node mergeSort(Node head) {

        if(head == null || head.next == null) return head;

        Node mid = getMid(head);
        Node left = head, right = mid.next;
        mid.next = null;
        Node leftSort = mergeSort(left);
        Node rightSort = mergeSort(right);

        return merge(leftSort, rightSort);
    }

    public Node merge(Node LL1, Node LL2){

        if(LL1 == null) return LL2;
        if(LL2 == null) return LL1;

        Node dummy = new Node(-1), tail = dummy;

        while(LL1 != null && LL2 != null){

            if(LL1.data < LL2.data){
                tail.next = LL1;
                tail = LL1;
                LL1 = LL1.next;
            }
            else{
                tail.next = LL2;
                tail = LL2;
                LL2 = LL2.next;
            }
        }

        tail.next = (LL1 != null) ? LL1 : LL2;
        return dummy.next;
    }

    public Node getMid(Node head){
        Node fast = head, slow = head;

        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

}
