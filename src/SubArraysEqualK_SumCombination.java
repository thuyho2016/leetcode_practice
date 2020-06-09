import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 39. Combination Sum 
 * https://leetcode.com/problems/combination-sum/
 
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

**** NOTE: Output can contains duplicates ****

Example 1:

Input: candidates = [2,3,6,7], target = 7,  *** Array has NO duplicates.
A solution set is:
[
  [7],
  [2,2,3]
]

Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
 */

public class SubArraysEqualK_SumCombination {
	 
	/* Best Solution - Depth First Search
	 Find combination to target
	 1. Need to sort array
	 2. recursive to generate combination
	    every step, i decide to take number or not ( 2 choices - take or not take). Add to current List 
	    return result.

Example: [2,3,6,7] 
	
	[]    
	/
Index 0: [2] , remain = target - 2 = 5
  Continue, idx 0,0:    [2 2], remain = 3
  Continue,idx 0,0,0:	  [2 2 2] , remain 1     
  Continue,..:     	  [2 2 2 2] invalid, remain = -1 . 
  Go back -> [2 2 2] --> [2 2]
	
  index 0 0 1: [ 2 2 3], remain = 0, valid ***
  Go back -> [2 2] -> [2]
	
Index 0,1:	[2 3], remain = 2
  Continue, Index 0,1,1: [2 3 3] remain = -1, invalid
  Go back -> [2 3] -->[2]
  Index 0,2: [2,6] , remain = -1, invalid
  Goback -->[2] -->[]

Index 1: [3], remain = 4
Continue, idx 1,1: [3 3], remain = 1
Continue, idx 1,1,1: [3 3 3], remain =-2, invalid
Go back -->[3]--> []

Index 2: [6]
 Continue, Index 2 2: [6 6], invalid, 
 Go back  ->[6]->[]
	
Index 3: [7], remain = 0, valid ***

Answer: contains valid combination [[7], [2,2,3]]
	
	*/
	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
		
		 List<List<Integer>> result = new ArrayList<List<Integer>>();
		 
		 if (candidates == null || candidates.length ==0) return result;
		 
		 Arrays.sort(candidates);	// sort array
		 List<Integer> combination = new ArrayList<Integer>();  // new ArrayList<Integer> represent current list I have
		 findCombinationsToTarget(candidates, 0, target, combination, result); 
         return result;
    } 
	
	//int startIndex - where I am 
	private static void findCombinationsToTarget(int[] candidates, int startIndex, int target, List<Integer> current, List<List<Integer>> result ) {
		
		//base case
		if(target == 0) {   //is remain target
			result.add(new ArrayList<Integer>(current)); //do deep copy which reference current combination , tricky way the remaining target is (target - candidates[i]) = 0
			return;
		}
		
		if (target < 0) {
			return;
		}
		
		//[2,3,6,7]
		for (int i = startIndex; i < candidates.length; i++) {
		 
			if (candidates[i] > target) { //then go back the previous level
				break;
			}
			
			current.add(candidates[i]);
			findCombinationsToTarget(candidates, i , target - candidates[i], current, result); // dont move i to next element, the remaining target = target - candidates[i]
			current.remove(current.size() - 1); // I just make a recursive call to  simulate the number, so  I need to remove the number I just took
	 
		 }
	}
		
	
	public static void main(String[] args) {
		int[] candidates = {2,3,6,7};
		int target = 7;

		List<List<Integer>> output = combinationSum(candidates, target);  //[[7],[2,2,3]]
		System.out.println(output);
	}
}
	
