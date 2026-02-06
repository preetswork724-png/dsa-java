/**
 * Problem:
 * <Sort a linked list of 0s, 1s and 2s>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/given-a-linked-list-of-0s-1s-and-2s-sort-it/1>
 *
 * Pattern:
 * <Partitioning / Segregating>
 *
 * Brute Force Intuition:
 * - Use ArrayList to store the values of the linked list.
 * - Sort the ArrayList.
 * - Re-assign value to each node in a linked list.
 *
 * - Why it is inefficient?
 * - Uses extra space and avoid pointer manipulation.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Two pass.
 * - Counting and overwriting.
 * - Count the number of zeroes, ones and twos.
 * - Iterate iver the LinkedList and update the value of the nodes based on the count of zeroes, ones and twos.
 *
 * - Why it is still not optimal?
 * - Uses two pass.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used Below):
 * - Split -> Collect -> Merge.
 * - Create two dummy lists:
 * - zero list.
 * - one list.
 * - two list.
 * - Traverse once.
 * - Append nodes into the correct bucket.
 * - Concat the buckets.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Check for edge cases like the number of zeroes, ones or twos is 0.
 */

package linkedlist;

public class SortALinkedListOf0s1sAnd2s {

    public Node segregate(Node head) {
        Node zeroHead = new Node(-1), zeroTail = zeroHead;
        Node oneHead = new Node(-1), oneTail = oneHead;
        Node twoHead = new Node(-1), twoTail = twoHead;
        Node temp = head;

        while(temp != null){

            Node next = temp.next;
            temp.next = null;

            if(temp.data == 0){
                zeroTail.next = temp;
                zeroTail = temp;
            }
            else if(temp.data == 1){
                oneTail.next = temp;
                oneTail = temp;
            }
            else{
                twoTail.next = temp;
                twoTail = temp;
            }

            temp = next;
        }

        Node head0 = zeroHead.next;
        Node head1 = oneHead.next;
        Node head2 = twoHead.next;

        if(head0 != null){
            zeroTail.next = (head1 != null) ? head1 : head2;
        }
        if(head1 != null){
            oneTail.next = head2;
        }
        twoTail.next = null;

        return (head0 != null) ? head0 :(head1 != null) ? head1 : head2;
    }
}
