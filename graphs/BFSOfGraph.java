/**
 * Problem:
 * <BFS Traversal of a Graph>
 *
 * Link:
 * <Generic Graph Traversal Problem>
 *
 * Pattern:
 * <Graph Traversal / BFS>
 *
 * Approach (Method):
 * - Use a Queue (FIFO) to process nodes level by level.
 * - Maintain a visited[] array to avoid revisiting nodes.
 * - Start from a source node (usually 0).
 * - Mark the source as visited and push it into the queue.
 * - While the queue is not empty:
 *     - Pop the front node.
 *     - Add it to the BFS result.
 *     - Traverse all its neighbors.
 *     - If a neighbor is not visited:
 *         - Mark it visited.
 *         - Push it into the queue.
 *
 * - Key Insight:
 * - In adjacency list, we only traverse actual neighbors.
 * - In adjacency matrix, we check all possible vertices.
 *
 * Time Complexity:
 *
 * - Using Adjacency List:
 *   - Each node is visited once → O(V)
 *   - Each edge is processed once → O(E)
 *   - Total: O(V + E)
 *
 * - Using Adjacency Matrix:
 *   - For each node, we scan all V vertices
 *   - Total: O(V^2)
 *
 * Space Complexity:
 *
 * - Visited array → O(V)
 * - Queue (worst case) → O(V)
 * - BFS result list → O(V)
 * - Total Auxiliary Space: O(V)
 *
 * - Graph Storage:
 *   - Adjacency List → O(V + E)
 *   - Adjacency Matrix → O(V^2)
 *
 * Notes:
 * - Works efficiently for sparse graphs using adjacency list.
 * - For disconnected graphs:
 *   - Run BFS from every unvisited node.
 * - Always mark visited at insertion time (not removal) to avoid duplicates.
 * - BFS guarantees shortest path in unweighted graphs.
 */

package graphs;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class BFSOfGraph {
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {

        ArrayList<Integer> bfs = new ArrayList<>();

        boolean[] visited = new boolean[adj.size()];

        Queue<Integer> q = new LinkedList<>();

        q.offer(0);
        visited[0] = true;

        while (!q.isEmpty()) {

            Integer front = q.poll();
            bfs.add(front);
            List<Integer> temp = adj.get(front);

            for (int nodeVal : temp) {
                if (!visited[nodeVal]) {
                    visited[nodeVal] = true;
                    q.offer(nodeVal);
                }
            }
        }
        return bfs;
    }
}
