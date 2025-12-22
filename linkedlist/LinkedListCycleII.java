/**
 * Problem:
 * <Linked List Cycle II>
 *
 * Link:
 * <https://leetcode.com/problems/linked-list-cycle-ii/description/>
 *
 * Pattern:
 * <Fast & Slow Pointer>
 *
 * Brute Force Intuition:
 * - Maintain a list of all the visited nodes that you traverse.
 * - Store the nodes by addresses and not by values, as values can be duplicated but addresses cannot be.
 * - Check for each node that it has been visited before.
 * - If it is then return that node.
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
 * - When fast and slow pointers meet, this indicates a cycle in the Linked List.
 * - Reset the slow pointer to the head.
 * - Move fast pointer and slow pointer with the same speed.
 * - The point where they meet again is the start of the cycle.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - A = distance from the head to the start of cycle, B = distance from the start of the cycle to the point of first meet, C = remaining distance from the point of meet to the cycle start.
 * - Fast enters the cycle earlier and completes one extra full cycle more than slow before they meet.
 * - Slow completes B distance at the time of first meet.
 * - So now, fast must complete C distance to complete the cycle.
 * - And as slow is reset to head, slow must complete A distance to reach the start of the cycle.
 * - When they meet again, indicates the start of the cycle.
 *
 * - Why reset slow to head?
 * - Because at the first meeting point, the distance from that point to the cycle start = distance from the head to the cycle start.
 * - So resetting slow to head, makes the point equally far from the cycle entry.
 * - Moving them together forces them to meet there.
 */
package linkedlist;

public class LinkedListCycleII {

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {
                slow = head;

                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                return slow;
            }
        }
        return null;
    }
}
