package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

/*
 * 
381. Insert Delete GetRandom O(1) - Duplicates allowed  (hard level)
https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/

Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
Example:

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();

 */

//Use HashMap + ArrayList

public class InsertDeleteGetRandom_DuplicateAllow {
	 private Map<Integer, Set<Integer>> map;  //// stores the value and the corresponding multiple indexes where we can find the number.
	 //The usage of set is due to the O(1) requirement
	 private List<Integer> nums;  // stores the numbers that are currently in the collection
	 private Random rand;
     
	   /** Initialize your data structure here. */
    public InsertDeleteGetRandom_DuplicateAllow() {
    	map = new HashMap<>();
        nums = new ArrayList<>();
        rand = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
    	boolean res = false;
    	
    	if(!map.containsKey(val)) {
    		map.put(val, new HashSet<>());
    		res = true;
    	}
       
        map.get(val).add(nums.size());
        nums.add(val);
        return res;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
    	
    	if (!map.containsKey(val)) return false;
    	
    
        Set<Integer> indexes = map.get(val);
        int index = indexes.iterator().next();
        
        if (indexes.size() == 1) {
        	map.remove(val);   
        }
        else {
        	indexes.remove(index);  // remove from the last element
        }
        
        if (index < nums.size() - 1) {
	        
	        nums.set(index,  nums.get(nums.size() - 1));
	        map.get(nums.get(nums.size() - 1)).remove(nums.size() - 1);
	        map.get(nums.get(nums.size() - 1)).add(index);
        }
        
        nums.remove(nums.size() - 1);
       
        return true;
        
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
    	return nums.get(rand.nextInt(nums.size()));
    }
    


    
  public static void main(String[] args) {
	  InsertDeleteGetRandom_DuplicateAllow obj = new InsertDeleteGetRandom_DuplicateAllow();
	  boolean param_1 = obj.insert(1);
	  param_1 = obj.insert(1);
	  param_1 = obj.insert(2);
	  
	  
	  System.out.println(obj.getRandom());   //Collection now contains [1,1,2].
	  
	  boolean param_2 = obj.remove(1);
	  System.out.println(param_2);
	  
	  System.out.println(obj.getRandom());  //Collection now contains [1,2].
	
	  
  }
}

