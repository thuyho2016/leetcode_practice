import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
90. Subsets II ( medium level)
https://leetcode.com/problems/subsets-ii/

Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

Similar with SubArraysEqualK_SumCombination2.java
 */


public class SubsetsII {
	
	public static List<List<Integer>> subsets(int[] nums) {
	    List<List<Integer>> result = new ArrayList<List<Integer>>();
	   
	    Arrays.sort(nums);
	    backtrack( nums, 0,  new ArrayList<>(),result);
	    return result;
	}

	private static void backtrack(int [] nums, int start, List<Integer> curList, List<List<Integer>> result ){
		result.add(new ArrayList<>(curList));
	    
	    for(int i = start; i < nums.length; i++){
	    	
	    	//diffrent with Subsets.java - is for duplicate
	    	if ( i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
	    	
	        curList.add(nums[i]);
	        backtrack( nums, i + 1, curList, result); // go to the next element, so move i to next index  
	        curList.remove(curList.size() - 1);
	    }
	}
	
	public static void main (String[] args)
    {
		int[] nums = new int[] {1,2,2};
		
		List<List<Integer>> result = subsets(nums);
		System.out.println( Arrays.asList(result));
	
    }
	
}
