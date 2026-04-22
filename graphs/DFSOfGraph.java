/**
 * Problem:
 * <DFS Traversal of a Graph>
 *
 * Link:
 * <Generic Graph Traversal Problem>
 *
 * Pattern:
 * <Graph Traversal / DFS / Recursion>
 *
 * Approach (Method):
 * - Use recursion to explore as deep as possible before backtracking.
 * - Maintain a visited[] array to avoid revisiting nodes.
 * - Start DFS from a source node (usually 0).
 * - Mark the current node as visited.
 * - Add it to the result list.
 * - Traverse all its neighbors:
 *     - If a neighbor is not visited:
 *         - Recursively call DFS on that neighbor.
 *
 * - Key Insight:
 * - DFS explores one complete path before moving to another.
 * - Recursion stack implicitly acts like a stack (LIFO).
 *
 * Time Complexity:
 *
 * - Using Adjacency List:
 *   - Each node is visited once → O(V)
 *   - Each edge is explored once → O(E)
 *   - Total: O(V + E)
 *
 * - Using Adjacency Matrix:
 *   - For each node, we scan all V vertices
 *   - Total: O(V^2)
 *
 * Space Complexity:
 *
 * - Visited array → O(V)
 * - Recursion stack (worst case, skewed graph) → O(V)
 * - Output list → O(V)
 * - Total Auxiliary Space: O(V)
 *
 * - Graph Storage:
 *   - Adjacency List → O(V + E)
 *   - Adjacency Matrix → O(V^2)
 *
 * Notes:
 * - For disconnected graphs:
 *   - Run DFS from every unvisited node.
 * - Recursive DFS may cause stack overflow for very deep graphs.
 * - Iterative DFS using stack can be used as an alternative.
 * - DFS is useful for:
 *     - Cycle detection
 *     - Topological sort
 *     - Connected components
 */

package graphs;

import java.util.ArrayList;

public class DFSOfGraph {
    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[adj.size()];
        ArrayList<Integer> list = new ArrayList<>();
        helper(0, adj, visited, list);
        return list;
    }

    public static void helper(int node, ArrayList<ArrayList<Integer>> adj,
                              boolean[] visited, ArrayList<Integer> list) {

        visited[node] = true;
        list.add(node);

        for (int neighbour : adj.get(node)) {

            if (!visited[neighbour]) {
                helper(neighbour, adj, visited, list);
            }
        }
    }
}
