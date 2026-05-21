/**
 * Problem:
 * <Design Browser History>
 *
 * Link:
 * <https://leetcode.com/problems/design-browser-history/>
 *
 * Pattern:
 * <Doubly Linked List>
 *
 * Brute Force Intuition:
 * - Store all visited pages in an array/list.
 * - Maintain an index representing the current page.
 * - For every visit:
 *      Remove all forward history manually.
 *      Add the new page.
 * - For back/forward:
 *      Move the index accordingly.
 *
 * - Why it is inefficient?
 * - Removing forward history may take O(N).
 * - Insertions/deletions in the middle are costly.
 * - Not natural for bidirectional navigation.
 *
 * Time Complexity:
 * - Visit  : O(N)
 * - Back   : O(steps)
 * - Forward: O(steps)
 *
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Use two stacks:
 *      Backward history stack
 *      Forward history stack
 *
 * - Visit:
 *      Push current page into backward stack.
 *      Clear forward stack.
 *
 * - Back:
 *      Push current page into forward stack.
 *      Pop from backward stack.
 *
 * - Forward:
 *      Push current page into backward stack.
 *      Pop from forward stack.
 *
 * - Why it is still not optimal?
 * - Requires managing multiple stacks.
 * - Current page handled separately.
 * - DLL provides more natural navigation.
 *
 * Time Complexity:
 * - Visit  : O(1)
 * - Back   : O(steps)
 * - Forward: O(steps)
 *
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach Intuition:
 * - Use a Doubly Linked List.
 * - Each node represents a webpage.
 * - Maintain a pointer to the current page.
 *
 * - Visit(url):
 *      Remove forward history.
 *      Create a new node.
 *      Attach it after current.
 *      Move current to new page.
 *
 * - Back(steps):
 *      Move current pointer backward using prev links.
 *
 * - Forward(steps):
 *      Move current pointer forward using next links.
 *
 * - Why DLL works perfectly here?
 * - Browser navigation is naturally bidirectional.
 * - prev handles backward navigation.
 * - next handles forward navigation.
 * - Deleting forward history is O(1).
 *
 * Time Complexity:
 * - Visit  : O(1)
 * - Back   : O(steps)
 * - Forward: O(steps)
 *
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Important Edge Case:
 * - If we move back and then visit a new page,
 *   all forward history must be deleted.
 *
 * Example:
 * - A -> B -> C
 *              ^
 *            current
 *
 * - back(1)
 *
 * - A -> B -> C
 *         ^
 *       current
 *
 * - visit(D)
 *
 * - Final History:
 * - A -> B -> D
 *
 * - NOT:
 * - A -> B -> C
 *          \
 *           D
 *
 * Key Observation:
 * - Browser history behaves exactly like
 *   bidirectional traversal with dynamic insertion.
 * - Therefore, Doubly Linked List is the most natural fit.
 */

package linkedlist.doubly;

class dllNode {
    dllNode next, prev;
    String data;

    public dllNode(String data) {
        this.data = data;
    }
}

public class DesignBrowserHistory {
    dllNode head, tail, curr;

    public DesignBrowserHistory(String homepage) {
        curr = new dllNode(homepage);
    }

    public void visit(String url) {
        dllNode newNode = new dllNode(url);
        curr.next = newNode;
        newNode.prev = curr;
        curr = newNode;

        curr.next = null;
    }

    public String back(int steps) {
        while(steps > 0 && curr.prev != null){
            curr = curr.prev;
            steps--;
        }
        return curr.data;
    }

    public String forward(int steps) {
        while(steps > 0 && curr.next != null){
            curr = curr.next;
            steps--;
        }
        return curr.data;
    }
}
