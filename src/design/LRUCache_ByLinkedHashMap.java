package design;
import java.util.LinkedHashMap;
import java.util.Map;


/*
146. LRU Cache
https://leetcode.com/problems/lru-cache/

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present.

When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.
Could you do both operations in O(1) time complexity?

Solution: Use LinkedHashMap to reduce time complexity of get() and put() to O(1)

LRU = least recently used - Cache = fast storage

Example: 
Capacity = 3, Map = { 1=>1, 2=>2, 3=>3 }
a pair ( 1,1) used first, so to insert a pair of (4,4), I need to delete a pair of (1,1)
Map = { 2=>2
        3=>3
        4=>4 }
        
  To get (2), 
  HashMap is unorder, so use linkedlist.      
*/

public class LRUCache_ByLinkedHashMap {
	Map<Integer, Integer> map = new LinkedHashMap<>();
    int limit;
    
    public LRUCache_ByLinkedHashMap(int capacity) {
        limit = capacity;
    }
    
    //check map has key. If it does, get value and return
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        // key is in the map
        int val = map.get(key);
        map.remove(key); //key is most recent used and add back 
        map.put(key, val);
        return val;
    }
    
    // List [ 1], insert 2: list= [2,1]
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            map.remove(key);
            map.put(key, value);
        }
        else if(map.size() == limit) 
        	//remove the first key , use iterator to give you the first key 
             map.remove(map.keySet().iterator().next());
        
    }
	
	public static void main(String[] args) {

	   LRUCache_ByLinkedHashMap cache = new LRUCache_ByLinkedHashMap(2); 
	   cache.put(1, 1);
	   cache.put(2, 2);
	   cache.get(1);       // returns 1
	   cache.put(3, 3);    // evicts key 2
	   cache.get(2);       // returns -1 (not found)
	   cache.put(4, 4);    // evicts key 1
	   cache.get(1);       // returns -1 (not found)
	   cache.get(3);       // returns 3
	   cache.get(4);       // returns 4
    
      System.out.println("LRU: " + cache.get(4));
     
   }
}