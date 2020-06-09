
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* 
 532. K-diff Pairs in an Array
 
Given an array arr of distinct integers and a nonnegative integer k, write a function findPairsWithGivenDifference that 
returns an array of all pairs [x,y] in arr, such that x - y = k. 

If no such pairs exist, return an empty array.
 
input:  arr = [0, -1, -2, 2, 1], k = 1
output: [[1, 0], [0, -1], [-1, -2], [2, 1]]

input:  arr = [1, 7, 5, 3, 32, 17, 12], k = 17
output: []

Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).

First and second steps take O(nLogn). So overall complexity is O(nLogn).

 */

public class TwoDifferent_ReturnPairs_Count_ByHashSet {
 	/* Steps:
	  1.  store all element in HashSet //not allow duplicate elements
	  2.  use Math.abs 		    
	  3.  Create  List<List<Integer>> to store pairs found
	 
	  int[] arr = {0, -1, -2, 2, 1};
	  List<List<Integer>> res = new ArrayList<List<Integer>>();
	    res.add(Arrays.asList(y, a));
	*/	       

	//count pairs
	public static int findPairs(int[] nums, int k) 
	{
		List<List<Integer>> res = new ArrayList<List<Integer>>();
	    
	    for(int i =0; i <nums.length; i++)
	    {
	    	for(int j = i + 1 ;j < nums.length;j++) //start 2nd index
	        {
	            if(Math.abs(nums[i] - nums[j]) == k) // might negative, so use Math.abs
	            {
	                if(nums[i] > nums[j]) // case [0,-1]
	                {    
	                   // store nums[i] at index =0 and nums[j] at index =1, 
	                    res.add(Arrays.asList(nums[i], nums[j])); //// convert int[] to List<Integer> and add to List
	                }   
	                else
	                {
	                    res.add(Arrays.asList(nums[j], nums[i])); // convert int[] to List<Integer> and add to List
	                }  
	            }    
	        }    
	    }
	    
	    System.out.println ("Pairs: " + res);
	    return res.size(); //count
	}
	
	//Best solution using HashMap - [0, -1, -2, 2, 1]
	public static int[][] findPairsWithGivenDifference(int[] arr, int k) {
	    HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
	    int[] pair = new int[2];
	    List<int[]> result = new ArrayList<int[]>();
	    
	    for (int i = 0; i < arr.length; i++) {
	       hm.put(arr[i], i);  // hm = {0=0, -1=1, -2=2, 2=3, 1=1}	   
	    }   
	    
	    for (int i = 0; i< arr.length; i++) {  //[0, -1, -2, 2, 1]
	    
	        // x- y = k -> x = y + k
	    	int distinct = arr[i] + k; 
	    	
	    	if (hm.containsKey(distinct) && hm.get(distinct) != i ) {
	    	  pair = new int[]{distinct, arr[i]};
	    	  result.add(pair);
	    	}
	    }
	    System.out.println ("Count Pairs: " + result.size()); // 4
	    //convert List to Array [][]
	    return result.toArray(new int[result.size()][]);
	}
	
	public static void main(String[] args) {
		int[] arr = {0, -1, -2, 2, 1};
		int k = 1;
		// output: 4, [[1, 0], [0, -1], [-1, -2], [2, 1]]
		System.out.println ("Count pairs: " + findPairs(arr, 1));
		
		int[] arr1 = {3,1,4,1,5};
		int[][] intArray = findPairsWithGivenDifference(arr1, k);
		System.out.println ("Pairs by way2: " +  Arrays.deepToString(intArray));
		
		
		int[] arr2 = {1, 7, 5, 3, 32, 17, 12};
		// output: 0
		System.out.println ("Count pairs: " + findPairs(arr2, 1));
		
		int[] arr3 = {1, 2, 3, 4, 5};
		// output: 4 with (1, 2), (2, 3), (3, 4) and (4, 5).
		System.out.println ("Count pairs: " + findPairs(arr3, 1));
		
		
	}
}


