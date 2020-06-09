
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * - Oracle interview
 * Given an array of integers and an integer k, return continuous subarrays whose sum equals to k.

Example :
array = [1, 2, 3, 4, 5] and k is 9 
Output: there are two subarrays that sum to 9: [2, 3, 4] and [4, 5]
 
 array =  {3, 5, 2, 1} , k = 3 
 output [[3], [2,1]]
  
 Sliding window - ( apply for min, max, longest, shorted, contained, calculate something)
 */
public class ContinuousSubArraySumEqualsK_ReturnSubArrays {

	/**  
	 *  Solution: use 2 pointers left and right
	 *  if curSum <= target, move the right pointer 
	 *  when curSum > target, move the left pointer and subtract curSum - nums[left]
	 *     continue checking if curSum <= target. If curSum is less target, move left pointer and subtract curSum - nums[left]
	 *     
	 *  When curSum, keep position of left pointer and right pointer  
	 *     
	 *  Whenever, we update the i index, we need to reset the sum value to 0.
	 *  
	 *  Time complexity : O(n^2), Space complexity : O(1)
	 */
	
	// i pointer start index 0 and j pointer start index 1
	public static List<List<Integer>> subarraySum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();		
	
		for (int i = 0; i < nums.length; i++) {
			int sum = nums[i]; // local variable should be declared HERE
    		List<Integer> list = new ArrayList<>();
    		
    		if (sum == target) {
    			list.add(nums[i]);
    			result.add(list);
    		}
    		
	    	for (int j = i + 1; j < nums.length; j++) {
	        	
	    		sum += nums[j];
	    		
	    		if (sum == target) {
	    			for (int k = i; k <= j; k++) { //Add subarray to list
	                 	list.add(nums[k]);
	                 }
	                 result.add(list);
	    		 }
	        }
	    }
	    return result;
	}
	 
	// Both of two pointers start index 0
	public static List<List<Integer>> subarraySum2(int[] nums, int target) {
		
		List<List<Integer>> result = new ArrayList<>();	 

		for (int i = 0; i < nums.length; i++) {
	    	int curSum = 0; // cur Sum so far must be declared HERE
	    	List<Integer> list = new ArrayList<>();
	        
	    	for (int j = i; j < nums.length; j++) {
	        	
	            curSum += nums[j];
	            
	            if (curSum == target) {
	            	for (int k = i; k <= j; k++) { //Add subarray to list
	                 	list.add(nums[k]);
	            	}
	            	result.add(list);
	            } 
	        }
	    }
	    return result;
	}
	
		
 
    public static void main(String[] args) { 	   
 	   int[] arr = {3, 5, 2, 1}; // expect output [[3], [2,1]]
       int k = 3;
 	  
       System.out.println("Continous subArrays: " +  subarraySum(arr, k));
       System.out.println("Continous subArrays: " +  subarraySum2(arr, k));
       
     
       int[] arr2 = {1, 2, 3, 4, 5};  
       
 	   System.out.println("Continous subArrays: " +  subarraySum(arr2, 9)); //[2, 3, 4] and [4, 5]
 	   System.out.println("Continous subArrays: " +  subarraySum2(arr2, 9));
 	 
    }
}
