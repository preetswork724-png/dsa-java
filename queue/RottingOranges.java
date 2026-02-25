/**
 * Problem:
 * <Rotting Oranges>
 *
 * Link:
 * <https://leetcode.com/problems/rotting-oranges/description/>
 *
 * Pattern:
 * <Queue + BFS>
 *
 * Brute Force Intuition:
 * - Simulate the problem until no fresh oranges are left or it is impossible to convert the fresh oranges to rotten oranges.
 * - We'll keep a boolean var to track the state i.e boolean changed
 * - Inside a while(changed) loop:
 * - Have a List<int[]> to store the indices where the fresh orange that will be rotten this minute.
 * - Traverse the entire matrix and store the indices of the orange that will be rotten this minute.
 * - Mark the changed = true.
 * - After storing all the indices, traverse the list and mark 2 at all the indices.
 * - minutes++.
 * - Continue the above until no fresh oranges are left, or it is impossible to convert the fresh oranges to rotten oranges.
 *
 * - Why it is inefficient?
 * - We literally simulate the entire problem.
 * - Each minute, you scan the entire grid.
 * - If grid is r*c, and worst case takes r*c minutes, then it is too slow for large arrays.
 *
 * Time Complexity:
 * - O((r*c)^2)
 * Space Complexity:
 * - O(N)
 *
 * Better and Optimal Approach (used below):
 * - Start BFS from all the rotten oranges at once.
 * - Because all the rotten oranges spread simultaneously.
 * - Traverse the grid once and store the positions of all the rotten oranges.
 * - Count the number of fresh oranges.
 * - For each BFS level:
 * - For each minute:
 * - Process current queue size.
 * - Poll out the cells for the rotten oranges.
 * - Infect neighbours.
 * - Add the infected ones back to the queue.
 * - Repeat until the queue is empty.
 * - At the end:
 * - If the number of fresh oranges become 0:
 * - Return minutes.
 * - Else, return -1.
 *
 * Time Complexity:
 * - O(N){r x c : total number of cells}
 * Space Complexity:
 * - O(N)
 *
 * Why better approach is the optimal one?
 * - The redundancy with brute force was scanning the entire grid each minute and even scanning cells that are already stable.
 * - So to improve, we must:
 * - We must stop scanning the entire grid repeatedly.
 * - We naturally move towards BFS.
 *
 * Notes:
 * - Whenever you see:
 * - Spread over time
 * - Equal time per step
 * - 4-direction movement
 * - Minimum time
 * - Immediately think:
 * - This is BFS layer traversal.
 */

package queue;
import java.util.Queue;
import java.util.ArrayDeque;
public class RottingOranges {

    public static void main(String[] args) {
        System.out.println(orangesRotting(new int[][]{{2,1,1}, {1,1,0}, {0, 1, 1}}));
    }

    public static int orangesRotting(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        Queue<int[]> rotten = new ArrayDeque<>();
        int fresh = 0;

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){

                if(grid[i][j] == 2){
                    rotten.offer(new int[]{i, j});
                }
                else if(grid[i][j] == 1){
                    fresh++;
                }
            }
        }

        if(fresh == 0) return 0;
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int minutes = 0;

        while(!rotten.isEmpty()){

            int size = rotten.size();
            boolean rottenThisMinute = false;

            for(int i = 0; i < size; i++){

                int[] cell = rotten.poll();
                int x = cell[0], y = cell[1];

                for(int[] d : dir){
                    int nx = x + d[0], ny = y + d[1];

                    if(nx >= 0 && ny >= 0 && nx < r && ny < c && grid[nx][ny] == 1){

                        grid[nx][ny] = 2;
                        rottenThisMinute = true;
                        fresh--;
                        rotten.offer(new int[]{nx, ny});
                    }
                }
            }
            if(rottenThisMinute) minutes++;
        }
        return fresh == 0 ? minutes : -1;
    }
}
