import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 18. 4Sum
https://leetcode.com/problems/4sum/description/

Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:
The solution set must not contain duplicate quadruplets.

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 
A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]

*/

public class FourSumZero {
	  // Similar with ThreeSum match K
    // Time Complexity: O(n^3)
    // Space Complexity: O(n^3)
	  public static List<List<Integer>> fourSum(int[] nums, int target) {
	        List<List<Integer>> result = new ArrayList<>();
	        Arrays.sort(nums);
	        
	        for (int i = 0; i < nums.length - 3; i++) {
	            if (i != 0 && nums[i] == nums[i - 1]) {
	                continue;
	            }
	            
	            for (int j = i + 1; j < nums.length - 2; j++) {
	                if (j != i + 1 && nums[j] == nums[j - 1]) { // duplicate current number and previous, continue
	                    continue;
	                }
	                
	                int left = j + 1;
	                int right = nums.length - 1;
	                
	                while (left < right) {
	                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
	                    
	                    if (sum == target) {
	                    	List<Integer> list = new ArrayList<>();
	                        list.add(nums[i]);
	                        list.add(nums[j]);
	                        list.add(nums[left]);
	                        list.add(nums[right]);
	                        result.add(list);
	                    
	                        left++;
	                        right--;
	                        
	                        while (left < right && nums[left] == nums[left - 1]) left++;
	                        
	                        
	                        while (left < right && nums[right] == nums[right + 1]) right--;
	                      
	                    } else if (sum < target) {
	                        left++;
	                    } else { // (sum > target) {
	                        right--;
	                    }                   
	                }                
	            }            
	        }
	        
	        return result;
	       
	    }
	  
	  
  //Best Solution:  To do four sum, five sum , Apply Two Sum
	public static List<List<Integer>> zeroSum(int[] nums, int target, int N) 
    {
        Arrays.sort(nums);
        return kSum(nums, 0, target, N); //Four sum, target = 0
        
    }

	    // find k numbers such that their sum == target.
	    // assuming:
	    // 1) k >= 2
	    // 2) input array is sorted
    private static List<List<Integer>> kSum(int[] A, int start, int target, int k)
    {
        if (k == 2) return twoSum(A, start, target);
        
        List<List<Integer>> result = new ArrayList<>();
        for (int i = start; i <= A.length - k; ++i)
        {
            if (i == start || A[i] != A[i - 1])
            {
                for (List<Integer> cur : kSum(A, i + 1, target - A[i], k - 1))  // recursive
                {
                    cur.add(A[i]);
                    result.add(cur);
                }
            }
        }
        
        return result;
    }
	    
	    
	//target = 0, or any number
    private static  List<List<Integer>> twoSum(int[] ary, int start, int target)
    {
        List<List<Integer>> result = new ArrayList<>();
        int left = start;
        int right = ary.length - 1;
        
        while (left < right)
        {   
        	int sum = ary[left] + ary[right];
            if ( sum == target)
            {
                if (left == start || ary[left] != ary[left - 1]) // prevent duplicate data
                {
                   
                	List<Integer> r = new ArrayList<>();
                    r.add(ary[left]);
                    r.add(ary[right]);
                    result.add(r);
                }
                left++;
                right--;
            }
            else if (sum < target)
            {
            	left++;
                
            }
            else // sum > target
            {
            	right--;
            }
        }
        
        return result;
    }
		    
	  public static void main (String[] args)
	  {
			int[] nums = {1, 0, -1, 0, -2, 2};
			
			List<List<Integer>> result = fourSum(nums, 0);
			System.out.println(result);			
			
			List<List<Integer>> res2 = zeroSum(nums, 0, 4) ; // four sum , target = 0
			System.out.println("Way2: " + res2);
			
			List<List<Integer>> res3 = zeroSum(nums, 0, 5) ; // four sum , target = 0
			System.out.println("Five sum: " + res3);
		
	   }
}
