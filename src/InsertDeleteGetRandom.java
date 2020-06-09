import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
 * 380. Insert Delete GetRandom O(1)
 * https://leetcode.com/problems/insert-delete-getrandom-o1/solution/

Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.

Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();


Solution:
 initialize a hash and a list. 
 
insert(int val)
If val is already in the list (i.e hash contains val), we return false.
Otherwise:
1. add to hash (key => value) = (element => index of element) 
    E.g   list = [3, 2, 1, 5], then hash = {3=>0, 2=>1, 1=>2. 5=>3}.

2. add val to the end of the list (at list.size()).
3. we return true

remove(int val)
 
If val is not in the hash, meaning that it is not in the list, we return false since there is nothing to be removed.
Otherwise:

1. Let indexOfVal be the index of val in the list (the value of val in the hash) and let lastElement be the last element of the list. 
   We remove val from the hash and lastElement from the list.

2. If lastElement is the same as val, we are done; just return true.
   Otherwise, we put lastElement at index indexOfVal in the list. We then update the hash to have key-value pair lastElement=>indexOfVal
3. we return true

getRandom();
This method returns a random value in the list.
We get a random number between 0 and list.size()-1.
We then return the list value located at the index corresponding to the above random number.

 */


public class InsertDeleteGetRandom 
{
	 List<Integer> list;
	 Map<Integer, Integer> hashMap;
    
	 /** Initialize your data structure here. */
    public InsertDeleteGetRandom() {
    	list = new ArrayList<Integer>();
        hashMap = new HashMap<Integer, Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
    	 if (hashMap.containsKey(val)) 
             return false;
         
       
         hashMap.put(val, list.size()); // first key has index = 0
         list.add(val);  //add val to the end of the list
         
         return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
    	
    	 if (!hashMap.containsKey(val)) 
             return false;
    	 
    	// val is in our list
        int indexOfVal = hashMap.remove(val); //Removes the mapping for a key if is present
        int lastElement = list.remove(list.size() - 1);
        
        if (lastElement != val) {
            list.set(indexOfVal, lastElement);
            hashMap.put(lastElement, indexOfVal);
        }
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
    	  Random random = new Random(); // choosing a different seed each time
          int randomIndex = random.nextInt(list.size()); // random number between 0 and list.size()-1
          return list.get(randomIndex);
    }
	
	public static void main(String[] args)
	{
		InsertDeleteGetRandom o = new InsertDeleteGetRandom();
		
		System.out.println(o.insert(1));  //true
		
		System.out.println(o.remove(2));  // false
		
		System.out.println(o.insert(2));  //true
		
		System.out.println(o.getRandom()); //2 or 1
	}
}
