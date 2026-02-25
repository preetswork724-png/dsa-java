/**
 * Problem:
 * <Asteroid Collision>
 *
 * Link:
 * <https://leetcode.com/problems/asteroid-collision/description/>
 *
 * Pattern:
 * <Queue>
 *
 * Brute Force Intuition:
 * - Traverse array
 * - If collision condition satisfied:
 * - Compare magnitudes
 * - Remove smaller
 * - Restart checking because structure changed
 * - Repeat until no changes
 *
 * - Why it is inefficient?
 * - Repeated Scanning.
 *
 * Time Complexity:
 * - O(n^2)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (used below):
 * - The problem is about the collision with the most recent surviving asteroid.
 * - Two asteroids collide only if:
 * - Left asteroid -> positive.
 * - Right asteroid -> negative.
 * - They collide because they are moving in the opposite direction.
 * - Each new asteroid only needs to check against the top of the stack.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why better approach is the optimal one?
 * - The redundancy with brute force was scanning the entire array again to see the survived asteroids.
 * - But stack already maintains the most recent surviving asteroid.
 *
 * Notes:
 * - We need LIFO behaviour because the collision always happens with the latest surviving asteroid.
 */

package queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class AsteroidCollision {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(asteroidCollision(new int[]{5,10,-5})));
    }

    public static int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> dq = new ArrayDeque<>();

        for(int a : asteroids){

            boolean isDestroyed = false;

            while(!dq.isEmpty() && a < 0 && dq.peekLast() > 0){

                if(Math.abs(a) > dq.peekLast()){
                    dq.removeLast();
                    continue;
                }
                else if(Math.abs(a) == dq.peekLast()){
                    dq.removeLast();
                }
                isDestroyed = true;
                break;
            }
            if(!isDestroyed) dq.addLast(a);
        }

        int[] remaining = new int[dq.size()];

        int i = 0;
        for(int num : dq) remaining[i++] = num;

        return remaining;
    }
}
