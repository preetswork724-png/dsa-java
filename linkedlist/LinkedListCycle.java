/**
 * Problem:
 * <Linked List Cycle>
 *
 * Link:
 * <https://leetcode.com/problems/linked-list-cycle/description/>
 *
 * Pattern:
 * <Fast & Slow Pointer>
 *
 * Brute Force Intuition:
 * - Maintain a list of all the nodes that you traverse.
 * - Store the nodes by addresses and not by values, as values can be duplicated but addresses cannot be.
 * - Check for each node that it has been visited before.
 * - If it is then that indicates a cycle in the Linked List.
 *
 * - Why it is inefficient?
 * - Checking if each node has been visited before is a O(N) approach.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Make use of a HashSet to optimize the checking process.
 *
 * - Why it is still not optimal?
 * - Uses extra space to store the nodes.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Use 2-pointers.
 * - Fast enters the cycle before the slow.
 * - The relative gap between the two decreases by 1 each step.
 * - Nodes are discrete positions, fast can't skip over slow without landing on the same node.
 * - So overtaking means collision.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - This approach is called as Floyd's cycle detection.
 * - Once slow also enters the cycle, both fast and slow pointer are moving on the same finite loop.
 * - Since fast moves one node more per step than slow, the distance between them shrinks by one node every step.
 * - Because there are only finitely many positions in the list and this shrinking distance must become zero at some point.
 * - This is the point where fast and slow meet.
 */
package linkedlist;

public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }

        return false;
    }
}
