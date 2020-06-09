package design;
import java.util.HashMap;
import java.util.Map;

/*
146. LRU Cache
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present.

When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity. Could you do both operations in O(1) time complexity?


Solution: 
Use 2 data structure - a HashMap and a Double Linked List

Add 0: 0 1 2 3 4 ,  so 4 is an item that is least used in the cache.

To Access 2: move 2 in front of cache because it has been used, so 2 is the most access
2 0 1 3

Items that are not used will go to the "end" of the cache eventually and get evicted since they are the least used items.

We need: 1. Fast Lookups --> use HashTable
		 2. Fast Removals  --> Double Linked List

		 
Every time we put/update or get item, we need to move item in front of the list

Time complexity : O(1) both for put(key,value) and get(key).

Space complexity : O(capacity) since the space is used only for a hashMap and double linked list with at most capacity + 1 elements.

*/


public class LRUCache_ByDoubleLL {
	// define a double LinkedList Node, with key, value, pointers to next node and previous node
	class Node {
		int key;
		int value;
		Node next;
		Node prev;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
		
		@Override
        public String toString()
        {
            return "{" + this.key +":" + this.value + "}";
        }
	}
		
	// using hashmap to get the Node in O(1) 
    //define dummy head and tail to store the most recently used key
	//and dummy tail points to  the least recently used key
	
    //global variables
	private Map<Integer, Node> map = new HashMap<>();
	private int capacity;
	private Node head = null;
	private Node tail = null;
    
    public LRUCache_ByDoubleLL(int capacity) {
		this.capacity = capacity;
		
		head = new Node(0, 0);   // dummy head points to most recently used key
        tail = new Node(0, 0);   // dummy tail points to least recently used key
     // connect the head and tail together
        head.next = tail;
        tail.prev = head;
    }
    
    //check map has key. If it does, get value and return
    public int get(int key) {

    	Node node = map.get(key);
    	if(node == null) return -1;        // no such key exists, so return -1
    	
    	// Else remove the node from tail and add it to the front of list
    	remove(node);                      // else we remove the node from tail
    	add(node);                         // and add it add the head, because now it is most recently used
    	return node.value;
    	
    }
    
    private void remove(Node node){        // a simple routine to remove a node from the tail
    	Node before = node.prev;
    	Node after = node.next;
    	
    	before.next = after;
    	after.prev = before;
    }    
    
    private void add(Node node) {           // a simple routine to add a node at the head
     	
    	Node tmp = head.next ;
        head.next = node;
        
        node.next = tmp;
        node.prev = head;
        
        tmp.prev = node;
    }
    
    /**
     *  If key is found in the map, just update it and move it to the head of the list
     *  Else key not found in the map, create a new node and put it in the map. 
     *       Check if map size equal capacity, then delete last node 
     */
    public void put(int key, int value) {
        Node node = map.get(key);
        
        if(node != null) {                // map contains the key, 
        	node.value = value;           // so just update the value        
        	remove(node);                 // remove the node from tail. 
        	add (node);				      // and add it to the head of the list. Now it is most recently used
        }
    	
        else {   							// map does not contain key
        	Node newNode = new Node(key, value);    // create new node and put it in map
        
    		if(map.size() == capacity){     //if map size equal capacity, we have to delete last node (the least recently used)
                Node lastNode = tail.prev;
                map.remove(lastNode.key);    //remove method - Removes the mapping for a key from this map if it is present                
                remove(lastNode);
            }
    		
    		map.put(key, newNode);
    		add(newNode);
        }
    }
     
	
	public static void main(String[] args) {

	   LRUCache_ByDoubleLL cache = new LRUCache_ByDoubleLL(2); //capacity = 2
	   cache.put(1, 1);
	   cache.put(2, 2);
	   cache.get(1);       // returns 1
	   cache.put(3, 3);    // evicts key 2
	   cache.get(2);       // returns -1 (not found)
	   cache.put(4, 4);    // evicts key 1
	   cache.get(1);       // returns -1 (not found)
	   
	   System.out.println("LRU: " + cache.get(3));       // returns 3	      
       System.out.println("LRU: " + cache.get(4));  // returns 4
     
   }
}