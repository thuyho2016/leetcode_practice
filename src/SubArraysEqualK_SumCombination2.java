
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/*
 * 40. Combination Sum II
 * https://leetcode.com/problems/combination-sum-ii/
 
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers. The solution set must not contain duplicate combinations.

Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8, *** Array has duplicate 
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]


Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]



Example how to find combination to target by DFS because i call recursive

[[1, 3, 2, 1] , target = 3

sort [ 1, 1, 2, 3]

Start empty          [] 
			      //^     								                  
Add 1:         [1]                                 
            //^  \\^	               \\^                    
Add 1: [1,1]   Add2:[1,2]valid ***,go back  Add3:[1,3]invalid, go back the previous level 
       remain target = 1  (target - cur sum of array = 3 - 2)  
        //^
Add 2: [1, 1, 2], 
      remain target = 3 - 4 = -1, invalid.
      Go back -> [1,1] above ->[1] above

Add 3: [1, 1, 3], remain target 5 -3 = -2, invalid     
       Go back -> [1,1] ->[1]     
     
Next element [1] is duplicate, so skip because add 2 [1,2] is the same above.

Next element [2]
		    //^
Add 3: [2,3],target = -2, invalid, go back [2]-> []


Next element [3]
[3] --> valid *** , go back []


Answer: contains valid combination [[1,2] [3]]

 */

public class SubArraysEqualK_SumCombination2 {
	
	// Best Solution - BackTrack - Time Complexity: o(2n)
	// Find sub array (combination) to target
	// 1. Need to sort array
	// 2. recursive to generate combination
	// every step, i have 2 choices - decide to take number or not take. Add to current List 
	// return result.
	
	public static List<List<Integer>> combinationSum(int[] nums, int target) {
		
		 List<List<Integer>> results = new ArrayList<List<Integer>>();
		 
		 if (nums == null || nums.length ==0) return results;
		 
		 Arrays.sort(nums);	 
		 
		 List<Integer> curCombination = new ArrayList<Integer>();   // new ArrayList<Integer> represents current list I have
		 findCombinationsToTarget(nums, 0, target, curCombination, results); 
         return results;
    } 
	
	//startIndex  - where I am , nums = [1, 1, 2, 3]
	private static void findCombinationsToTarget(int[] candidates, int startIndex, int target, List<Integer> current, List<List<Integer>> results ) {
		
		//base case
		if(target == 0) {   //is the remaining target. It is (target - candidates[i]). 1st round: target =3, 2nd round target = 2
			results.add(new ArrayList<Integer>(current));  // current list is combination list - [[1, 2]]
			return;
		}
		
		if (target < 0) {
			return;
		}
		
		//[1, 2, 2, 2, 5]
		for (int i = startIndex; i < candidates.length; i++) { //startIndex = 0, 1, 2,...
			 //1. I have to check duplicate. If number is not the same with previous number, add current number to current list. If duplicate, skip
			// 2. Next, I make recursive call
			 
			if (i != startIndex && candidates[i - 1] == candidates[i])  // skip duplicates
				continue;
			
			if (candidates[i] > target) { // then go back the previous level
				break; 
			}
			
		//	if (i == startIndex || candidates[i] != candidates[i-1]) { //case - not duplicate number
			current.add(candidates[i]);  //	add current number to current list. [1], [1, 1], [1, 2]	, [2]					 
			findCombinationsToTarget(candidates, i + 1, target - candidates[i], current, results);  //go to next element so i + 1 , target - candidates[i] is the remaining target  
			current.remove(current.size() - 1); // I just make a recursive call to  simulate the number, so  I need to remove the number I just took
												 // [1, 1] -> [1] after 1 is removed. [1, 2] -> [1] after 2 is removed
		 }
	}
	
	public static void main(String[] args) {
		int[] candidates = {1, 3, 2, 1};   // has duplicate numbers
		int target = 3;
		
	//	int[] candidates = {2,5,2,1,2};
	//	int target = 5;
		
	//	int[] candidates = {10,1,2,7,6,1,5};
	//	int target = 8;
		
		//int[] candidates = {1, 2, 3, 4, 5};
	    //int target = 6;
	       
		List<List<Integer>> output = combinationSum(candidates, target);  
		System.out.println(output);  //[[1,2,2],[5]]
		
	}
}
	
