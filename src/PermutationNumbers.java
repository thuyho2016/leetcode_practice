import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * 47. Permutations II
https://leetcode.com/problems/permutations-ii/ 
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

* 46. Permutations
https://leetcode.com/problems/permutations/

Input: [1,2,6]
Output: 6 combination
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

Steps:

1. We need to fill position one by one
   - To fill a position, we need to know what is our choice
   - swap current index with the choice index
   - Follow each choice one by one and keep moving forward
   - When we run out of choice, we print our permutation and we backtrack   
    
 why we need the second swap?    
(Think Backtrack as  moving back up in the tree to explore the next branch.) 
When we moved down of one level, we swapped 2 elements (1st swap in the code). 
So when we go back up in the tree, we need to swap these 2 elements back to their original order at the parent node level (2nd swap in the code). 
This is called backtracking = done exploring a branch, we need to go back up and explore more branches

Example:  [1,2,3] swap to [2,1,3] and call recursion for [2,1,3]. then we need to swap back to [1,2,3] . 
next swap will be [3,2,1] then recursion and recover swap.

Level 1: Swap 1 with itself, 2 and 3: [1,2,3] , [2,1,3], [3,2,1]

Level 2: 
2a. [1,2,3] (1 is fixed) -> swap 2 with itself and 3: [1,2,3], [1,3,2]

2b. [2,1,3] (2 is fixed) -> swap 1 with 1 = [2,1,3], swap 1 with 3 = [2,3,1]

2c. [3,2,1] (3 is fixed) -> swap 2 with 2 = [3,2,1], swap 2 with 1 = [3,1,2]

*/


public class PermutationNumbers {
	
	/*  2 pointers: l start index 0 and r end index 3
	    
	    Prints all distinct permutations in str[0..n-1] 
     	i = 0 1 2 ( index)
		    1 1 2
		
		index = 0, nums[0] = 1
		
		   Start swapping nums[index] with nums[i] following i = index + 1 = 0 + 1 = 1 

		   Since nums[index] != nums[i], swap and recur.

		i = 2, nums[index] == nums[i], don't swap

		i = 3,  nums[index] != nums[i], swap and recur.
		
		add list to List<List<Integer>>
   */
	
	public void permute(int nums[], int l, int len, List<List<Integer>> result) {
		Set<Integer> set = new HashSet<>(); // to remove duplicate

    	if (len == 0) return;
    	
	    if (l == len) {
	    	//convert int array to ArrayList<Integer>
	    	List<Integer> list = new ArrayList<Integer>();
	    	for (int i: nums) {
	    		list.add(i);
	    	}
	    	result.add(list); // add list to result  	
        } 
	    
	    
	    for (int i = l; i < len; i++) {   // take choice one by one
    		//remove duplicate
	    	if (set.contains(nums[i]))
	    		continue;
	    	set.add(nums[i]);
	    	
	    	swap (nums, l, i); // take 1 choice and mark if as done for position i   		
    		permute(nums, l + 1, len, result); //fill the place 2, 3   		
    		swap(nums, l, i); //recover - put back original
	    	
	    }
	}	 
		
	  public void swap(int[] arr, int i, int j) {
	    int tmp = arr[i];
	    arr[i] = arr[j];
	    arr[j] = tmp;
	    
	  }
	  
	  //way 2: DFS and Recursvie	  
	  public List<List<Integer>> permute_Recursive(int[] nums) {
		    boolean[] visited = new boolean[nums.length];
		    Arrays.sort(nums);
		    
		    List<List<Integer>> ret = new ArrayList<>();
		    dfs(nums, visited, new ArrayList<>(), ret);
		    return ret;
		}

		private void dfs(int[] nums, boolean[] visited, List<Integer> path, List<List<Integer>> ret) {
		    if (path.size() == nums.length) {
		        ret.add(path);
		        return;
		    }
		    for (int i = 0; i < nums.length; i++) {
		        if (visited[i] || (i > 0 && nums[i] == nums[i-1] && !visited[i-1])) {
		            continue;   // remove duplicates
		        }
		        List<Integer> p = new ArrayList<>(path);
		        p.add(nums[i]);
		        visited[i] = true;
		        dfs(nums, visited, p, ret);  //recursive
		        visited[i] = false;  // recover
		    }
		}
	  		
	  public static void main(String[] args) { 
		PermutationNumbers p = new PermutationNumbers(); 
		List<List<Integer>> result = new ArrayList<>();
	  	
		int nums[] = {1, 1, 2}; 
		p.permute(nums, 0, nums.length, result);
		System.out.println(result); 
					  
		int nums2[] = {1, 2, 3};  
		List<List<Integer>> result2 = new ArrayList<>();
		p.permute(nums2, 0, nums2.length, result2);
		System.out.println(result2); 
		
		
		int nums3[] = {1, 2, 3};
		System.out.println(p.permute_Recursive(nums3));
		  
	  } 
}




