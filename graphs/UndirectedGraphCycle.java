/**
 * Problem:
 * Detect Cycle in Undirected Graph
 *
 * Link:
 * https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
 *
 * Pattern:
 * Graph Traversal / DFS / BFS / Parent Tracking
 *
 *
 * -------------------------------------------------------
 * Core Intuition:
 * -------------------------------------------------------
 * - In an undirected graph, every edge is bidirectional.
 * - So when you visit a neighbor, it naturally points back to you.
 *
 * Key Rule:
 * A cycle exists if:
 * - You visit a node that is already visited
 * - AND it is NOT the parent
 *
 *
 * -------------------------------------------------------
 * Brute Force Intuition:
 * -------------------------------------------------------
 * - Try exploring all paths from each node
 * - Check if we come back to the same node
 *
 * Why it fails:
 * - Exponential number of paths
 * - Revisits nodes repeatedly
 * - No efficient cycle detection logic
 *
 * Time Complexity:
 * - Exponential
 *
 * Space Complexity:
 * - O(V) recursion stack
 *
 *
 * -------------------------------------------------------
 * Common Brute Force Mistakes:
 * -------------------------------------------------------
 * BUG 1:
 * - Treating it like a directed graph
 *
 * BUG 2:
 * - Using only visited[] without parent
 *   → leads to false cycle detection
 *
 * BUG 3:
 * - Not handling disconnected components 
 *
 *
 * -------------------------------------------------------
 * Better Approach (DFS with Parent Tracking):
 * -------------------------------------------------------
 * - Maintain visited[]
 * - For every unvisited node:
 *     → run DFS
 *
 * - In DFS:
 *     - Mark node as visited
 *     - Traverse neighbors:
 *         - If not visited → recurse
 *         - If visited AND neighbor != parent → cycle
 *
 *
 * -------------------------------------------------------
 * ⚡ Alternative Approach (BFS with Parent Tracking):
 * -------------------------------------------------------
 * - Use queue storing (node, parent)
 * - For each unvisited node:
 *     → start BFS
 *
 * - For each node:
 *     - Traverse neighbors:
 *         - If not visited:
 *             → mark visited
 *             → push (neighbor, node)
 *         - If visited AND neighbor != parent:
 *             → cycle found
 *
 *
 * -------------------------------------------------------
 * Complexity Analysis:
 * -------------------------------------------------------
 * Time Complexity:
 * - O(V + E)
 * - Each node and edge visited once
 *
 * Space Complexity:
 * - O(V)
 * - visited[] + recursion stack (DFS) / queue (BFS)
 *
 *
 * -------------------------------------------------------
 * Key Observations:
 * -------------------------------------------------------
 * - Parent tracking is mandatory
 * - visited[] alone is NOT sufficient
 * - Graph may be disconnected → loop through all nodes
 *
 *
 * -------------------------------------------------------
 * Edge Cases:
 * -------------------------------------------------------
 * 1. Single node → no cycle
 * 2. Two nodes with one edge → no cycle
 * 3. Triangle (3 nodes fully connected) → cycle
 * 4. Disconnected graph with one cyclic component → cycle exists
 *
 *
 * -------------------------------------------------------
 * Common Mistakes:
 * -------------------------------------------------------
 * Not passing parent in DFS/BFS
 * Marking visited too late (after recursion/enqueue)
 * Assuming graph is connected
 * Confusing with directed graph logic (recursion stack)
 *
 *
 * -------------------------------------------------------
 * Mental Model:
 * -------------------------------------------------------
 * "If you reach an already visited node through a different path
 * (not your parent), you found a loop."
 *
 *
 * -------------------------------------------------------
 * Pattern Recognition Trick:
 * -------------------------------------------------------
 * If:
 * - Undirected graph
 * - Need to detect cycle
 *
 * Think:
 * DFS/BFS + Parent Tracking
 *
 * NOT:
 * - Recursion stack (that’s for directed graphs)
 *
 *
 * -------------------------------------------------------
 * DFS vs BFS (Reality Check):
 * -------------------------------------------------------
 * - Both give same complexity
 * - DFS → simpler to write
 * - BFS → iterative, sometimes preferred in interviews
 *
 *
 * -------------------------------------------------------
 * Key Takeaway:
 * -------------------------------------------------------
 * - Core trick = Parent Tracking
 * - Condition:
 *     visited[neighbor] == true AND neighbor != parent
 * - Works for both DFS and BFS
 */

package graphs;

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class UndirectedGraphCycle {
    public static boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (detectCycle(i, adj, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean detectCycle(int src, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{src, -1});

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int node = curr[0];
            int parent = curr[1];

            for (int neighbour : adj.get(node)) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    q.offer(new int[]{neighbour, node});
                } else if (parent != neighbour) {
                    return true;
                }
            }
        }
        return false;
    }
}
