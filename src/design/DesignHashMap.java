package design;
/*
 * 706. Design HashMap   (Level = Easy)
 
 Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:

put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

Example:

MyHashMap hashMap = new MyHashMap();
hashMap.put(1, 1);          
hashMap.put(2, 2);         
hashMap.get(1);            // returns 1
hashMap.get(3);            // returns -1 (not found)
hashMap.put(2, 1);          // update the existing value
hashMap.get(2);            // returns 1 
hashMap.remove(2);          // remove the mapping for 2
hashMap.get(2);            // returns -1 (not found) 

Note:

All keys and values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashMap library.

Use ListNode
https://leetcode.com/problems/design-hashmap/discuss/387395/Design-hashmap-Java

 */

public class DesignHashMap {
	
     //Initialize a int array
    int[] arr = new int[1000001];
    boolean[] isExist = new boolean[1000001];
    
    /** Initialize your data structure here. */
    public DesignHashMap() {
        
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        arr[key] = value;
        isExist[key] = true;
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        if(isExist[key] == true) {
            return arr[key];
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        arr[key] = -1;
        isExist[key] = false;
    }
    
    
    /*Test Case:
       ["MyHashMap","put","put","get","get","put","get", "remove", "get"]
    	[[],[1,1],[2,2],[1],[3],[2,1],[2],[2],[2]]
    	
    	Output: [null,null,null,1,-1,null,1,null,-1]
    */
    
    public static void main(String[] args) { 
    	DesignHashMap hashMap = new DesignHashMap();
    	hashMap.put(1, 1);          
    	hashMap.put(2, 2);         
    	System.out.println(hashMap.get(1));            // returns 1
    	System.out.println(hashMap.get(3));            // returns -1 (not found)
    	hashMap.put(2, 1);                            // update the existing value
    	System.out.println(hashMap.get(2));            // returns 1 
    	hashMap.remove(2);                           // remove the mapping for 2
    	System.out.println(hashMap.get(2));            // returns -1 (not found)
    
    }
}

