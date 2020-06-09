package design;

/*
 * 705. Design HashSet  (Level = Easy)
 https://leetcode.com/problems/design-hashset/
 
Design a HashSet without using any built-in hash table libraries.

To be specific, your design should include these functions:

add(value): Insert a value into the HashSet. 
contains(value) : Return whether the value exists in the HashSet or not.
remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.

Example:

MyHashSet hashSet = new MyHashSet();
hashSet.add(1);         
hashSet.add(2);         
hashSet.contains(1);    // returns true
hashSet.contains(3);    // returns false (not found)
hashSet.add(2);          
hashSet.contains(2);    // returns true
hashSet.remove(2);          
hashSet.contains(2);    // returns false (already removed)

 */


public class DesignHashSet {
	class ListNode {
        int key;
        int val;
        ListNode next;
        
        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
	
	private int hash(int key) { //getIdx
        return Integer.hashCode(key) % buckets.length;
    }
    
    ListNode find(Bucket bucket, int key){
        ListNode node = bucket.head;
        ListNode prev = null;
        
        if (node != null && node.key != key) {
            prev = node;
        	node = node.next;
        }
        return prev;
    }
        
    class Bucket{
        ListNode head = new ListNode(-1,-1);
    }
    
    
	    
    Bucket[] buckets;
    
    /** Initialize your data structure here. */
    public DesignHashSet() {
         buckets = new Bucket[10001];
    }
    
    public void add(int key) {
        int hashKey = hash(key);
        if (buckets[hashKey] == null) {
            buckets[hashKey] = new Bucket();
        }
        
        ListNode node = find(buckets[hashKey], key);
        if (node.next == null) {
            node.next = new ListNode(key, 1);
        } else {
            node.next.val = 1;
        }
    }
    
    public void remove(int key) {
        int hashKey = hash(key);
        if (buckets[hashKey] == null) {
            return;
        }
        
        ListNode node = find(buckets[hashKey], key);
        if (node.next == null) { return; }
        node.next = node.next.next;
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hashkey = hash(key);
        if (buckets[hashkey] == null){
            return false;
        }
        
        ListNode node = find(buckets[hashkey], key);
        if (node.next == null){
            return false;
        }
        return node.next.val == 1 ? true : false;
    }
  
   
	    
	 
    public static void main(String[] args) { 
    	DesignHashSet hashSet = new DesignHashSet();
    	hashSet.add(1);         
    	hashSet.add(2);         
    	System.out.println(hashSet.contains(1));            // returns true
    	System.out.println(hashSet.contains(3));            // returns false (not found)
    	hashSet.add(2);                             // update the existing value
    	System.out.println(hashSet.contains(2));            // returns true
    	hashSet.remove(2);                           // remove the mapping for 2
    	System.out.println(hashSet.contains(2));            // returns false (not found)
    
    }
}

