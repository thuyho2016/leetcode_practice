import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
78. Subsets  (Medium level)
 https://leetcode.com/problems/subsets/
 
 Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */


public class Subsets {
	
	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		   
	    Arrays.sort(nums);
	    backtrack( nums, 0,  new ArrayList<>(),result);
	    return result;
	}

	private static void backtrack(int [] nums, int start, List<Integer> curList, List<List<Integer>> result ){
		result.add(new ArrayList<>(curList));
	    
	    for(int i = start; i < nums.length; i++){
	    	
	        curList.add(nums[i]);
	        backtrack( nums, i + 1, curList, result); // go to the next element, so move i to next index  
	        curList.remove(curList.size() - 1);
	  
	    }
	}
	    
	public static void main (String[] args)
    {
		int[] nums = new int[] {1,2,3};
		
		List<List<Integer>> result = subsets(nums);
		System.out.println( Arrays.asList(result));
	
    }
}
