
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.List;

/* 1. Two Sum
 * https://leetcode.com/problems/two-sum/
 * 
 Given an array of integers, return indexes of the two numbers such that they add up to a specific target.
Space complexity : O(n).

 Given nums = [2, 7, 11, 15], target = 9

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 
Input:  [4,7,9,2,6], Target 8
return [3,4]

Solution: use HashMap

 1. Go through array
 2. x + y = target -> y = target - x	E.g 2 + 7 = 9 -> 7 = 9 -2
 3. Create HashMap to store current number as key and current index of array as value.
    if HashMap contains key:  y = target - x
       Get value of key k that is index
       
 4. Create  List<List<Integer>> to hold pairs found

 
 Note: HashMap does not allow duplicate keys, but it allows to have duplicate values
       HashSet: does not allow duplicate elements. It allows null value, 
       
Question: Is array sorted? Is it possible if array is empty?    

 Time & Space complexity : O(n).
 */


public class TwoSumEqualK_ReturnIndexes_ReturnPairs {  
	
	//return  List of List indexes
    public static List<List<Integer>> twoSum_ReturnIndex2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
    
        List<List<Integer>> list = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums.length; i++) {
        	
	        int y = target - nums[i];  //y = target - x	 = target - nums[i]
	        	       	
	        if (map.containsKey(y)) {  // check if map contains key y
	            
	        	//add List<Integer> to List
	        	list.add(Arrays.asList(map.get(y), i));    //   List<Integer> result = Arrays.asList(0, 1);
        
	        }
	        //must call here after checking if hashmap has keys 
	        map.put(nums[i], i); // stores each element as key  and index i as value,  //{2=0, 3=4, 6=3, 7=1, 8=2}	       
	    }
              
        return list;
    }
        
	//Returning indexes      	
    public static int[][] twoSum_ReturnIndex(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(); //map = (k, v) = (nums[i], index i)   - value is index . {2=0, 3=4, 6=3, 7=1, 8=2}
        
        List<int[]> list = new ArrayList<>(); 
        
        for (int i = 0; i < nums.length; i++) {
        	
	        int y = target - nums[i];  //y = target - x	 = target - nums[i]
	        	       
	        if (map.containsKey(y)) {   	
	        	
	        	// store index i and index of y to array 
	        	int[] res = new int[] {map.get(y), i};   //	int[] res = {0, 1}
	        	list.add(res);
        
	        }
	        //must call here after checking if hashmap has keys 
	        map.put(nums[i], i); // add current element as key and its index i as value  // {2=0, 3=4, 6=3, 7=1, 8=2}
	       
	    }
        System.out.println("Returning indexes: " + Arrays.deepToString(list.toArray()));
        
        //convert list to int[][] array - new int[list.size()][]
         return list.toArray(new int[list.size()][]);             
    }
    
    
   /*******   Return pairs of element that equals target *******/
   
    //return array of pairs of indexes
    public static int[][] twoSum_ReturnsPairs(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();  // create map to store (current nums, its index) . E.g map = {x => i}   //{ 2=0, 3=4, 6=3, 7=1, 8=2}
        List<int[]> list = new ArrayList<>(); //to hold result
        
        for (int i = 0; i < nums.length; i++) {
        	
	        int y = target - nums[i];  //y = target - x	 = target - nums[i]
	        
	       
	        map.put(nums[i], i); // value is index i   // add current nums and its index to map 
	       
	        // check if Map contains key y 	 	
	        if (map.containsKey(y)) { 
	        	int[] res = new int[] {y, nums[i]};   // store nums[i] and y to array 
	        	list.add(res);
	        }
	    }
 
        System.out.println("Returning pairs: " + Arrays.deepToString(list.toArray()));
        return list.toArray(new int[list.size()][]);     //convert list to int[][] array   
    }
    
     
    //return a list of pairs of 2 numbers
    public static List<List<Integer>> twoSum_ReturnsPairs2(int[] nums, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        HashMap < Integer, Integer> map = new HashMap<>();  // // create map to store (current nums, index of it) map = {x = i} 
         
        
        for (int i = 0; i < nums.length; i++) {
        	int y = sum - nums[i];  
        	map.put(nums[i], y);   // map = {1 = 9}, {2 = 8}
       
          
        	if (map.containsKey(y)) {
        		
        		result.add(Arrays.asList(y, map.get(y)));  //System.out.println("Map " + y + "=> " + map.get(y));
        	}
        }
        return result;
    }
    
    public static void main (String args[]) {
    
	    int[] array = { 2, 7, 8, 6, 3 };  
	    int[][] out = twoSum_ReturnIndex(array, 9);
	    System.out.println("Indexes: " + Arrays.deepToString(out)); //Output: [0,1],[3,4] 
    	
	    System.out.println("Indexes in list of list: " + twoSum_ReturnIndex2(array, 9));
    	
    	// return pairs of 2 elements
	    int target = 10;
	    int[] nums = {1,2,3,4,5,6,7,8,9,10};
	    
	    System.out.println("Pairs: " + Arrays.deepToString(twoSum_ReturnsPairs(nums, target)));	  //[[5, 5], [4, 6], [3, 7], [2, 8], [1, 9]]  
	    
	    System.out.println(twoSum_ReturnsPairs2(nums, target));  //[[5, 5], [6, 4], [7, 3], [8, 2], [9, 1]]
    }
}


