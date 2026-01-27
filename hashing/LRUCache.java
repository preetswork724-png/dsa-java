/**
 * Problem:
 * <LRU Cache>
 *
 * Link:
 * <https://leetcode.com/problems/lru-cache/description/>
 *
 * Pattern:
 * <HashMap + doubly linked list>
 *
 * Brute Force Intuition:
 * - Maintain a list of int[] where each arr[] is of size 2.
 * - arr[0] = key and arr[1] = value.
 * - Whenever get(key) is called:
 * - Linearly scan the list.
 * - Return -1 if key is not found.
 * - If key is found, store the value corresponding to the key.
 * - Remove the pair[] {key, value} and add it at the back of the list.
 * - Whenever set(key, value) is called:
 * - Check if the key exists in the list.
 * - If it does, update the value corresponding to the key, remove it from the list and add it to the back of the list.
 * - If the key does not exist in the list but the capacity of the list is full:
 * - Remove the pair[] from the front.
 * - If the capacity is not full:
 * - In both the cases, add the new pair[] {key, value} at the back of the list.
 *
 * - Why it is inefficient?
 * - Violates the O(1) behavior for get() and set().
 *
 * Time Complexity:
 * - O(N) {get() and set()}
 * Space Complexity:
 * - O(N) {Storing pair[]}
 *
 * Better Approach Intuition:
 * - HashMap + Deque.
 * - Use HashMaps for fast lookups.
 * - Use deque to access the least recently used key at the front and the most recently used key at the back.
 * - Whenever get(key) is called:
 * - HashMap enables the lookup in O(1).
 * - Return -1 if key is not found.
 * - Remove the key from the deque and add it at the back of the list.
 * - Whenever set(key, value) is called:
 * - Check if the key exists in the map.
 * - If it does, update the value corresponding to the key, remove it from the deque and add it to the back of the deque.
 * - If the key does not exist in the map but the capacity of the map is full:
 * - Remove from the head of the deque.
 * - If the capacity is not full:
 * - In both the cases, add it at the back of the list.
 *
 * Time Complexity:
 * - O(N) {O(1) :- Fast lookups, O(N) :- Maintain the list}
 * Space Complexity:
 * - O(N)
 *
 * Why it is still not optimal?
 * - Violates the O(1) behavior for set().
 *
 * Optimal Approach (Used below):
 * - LinkedHashMap + Doubly Linked List.
 * - Used LinkedHashMap's constructor (capacity, load factor, access order).
 * - Hash Lookup + move node to tail :- O(1)
 * - Hash insert / update + link relay out :- O(1)
 * - Remove LRU :- Head of DLL :- O(1)
 *
 * Time Complexity:
 * - O(1) {O(1) :- get(), O(1) :- set()}
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - access order = true.
 * - This true means that the map order becomes:
 * - [Least Recently Used ... Most Recently Used].
 * - Every get() or put() moves the key to the end automatically.
 * - int lruKey = cache.keySet().iterator().next().
 * - Fetches the head of the doubly linked list -> the least recently used key.
 * - Removal in O(1).
 */

package hashing;
import java.util.Map;
import java.util.LinkedHashMap;
public class LRUCache {
    private final int capacity;
    private Map<Integer,Integer> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<>(capacity,0.75f,true);
        // true -> moves recently used keys to the end
    }

    public int get(int key) {
        return cache.getOrDefault(key,-1);
    }

    public void set(int key, int value) {
        if(cache.size() >= capacity && !cache.containsKey(key)){
            int lruKey = cache.keySet().iterator().next();
            cache.remove(lruKey);
        }
        cache.put(key,value);
    }
}
