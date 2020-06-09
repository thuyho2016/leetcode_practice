

import java.util.HashMap;
import java.util.Map;

/*
 * 523. Continuous Subarray Sum
 * https://leetcode.com/problems/continuous-subarray-sum/
 
Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 
that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:

Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.

Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.


 */
public class ContinuousSubArraySumEqualMultipleK_ReturnTrueFalse {
	   
    //Use HashMap to keep track of the running sum mod k of the elements in the process. (k,v) = ( remainder, index)
	
	//If we have seen the same remainder before,it means the subarray sum if a multiple of k
	
	//Time complexity : O(n). Only one traversal of the array nums is done.
	//Space complexity : O(min(n,k)). The HashMap can contain upto min(n,k) different pairings.
	
    public static boolean checkSubarraySum(int[] nums, int k) { //[23, 2, 4, 6, 7]
    	if (nums == null || nums.length < 2) return false;
    	
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0,-1);
        
        int curSum = 0;
       
	    for (int i=0; i < nums.length;i++) {
	    	curSum += nums[i]; //23, 5 + 2 = 7, 1 + 4 = 5
	        
	        if (k != 0) {
	        	curSum = curSum % k; // curSum is remainder 23 % 6 = 5, 7 % 6 = 1, 5 % 6 = 5
	        	System.out.println("Remainder " + curSum);
	        }
	    
            if (map.containsKey(curSum)) {        //{0=-1, 1=1, 5=0} when key = 5, go inside
                if (i - map.get(curSum) > 1) {   // i -  value of key 5 = 2 - 0 = 2 > 1
                	System.out.println("Key " + curSum + " = " + map.get(curSum));
                    return true;
                }
            } else  {         
                map.put(curSum, i); // add curSum as key and index i as value:  {0=-1, 5=0}, {0=-1, 1=1, 5=0}
            }
	    }
	    
	    return false;
    }
     
   
    //keeping track of the running sum mod k of the elements in the process
    //Time complexity : O(n)
    //Space complexity : O(1). 
      
    // not optimize solution
    public static boolean checkSubarraySum2(int[] nums, int k) 
    {
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0,-1);
        
	    int runningSum = 0;
	       
	    for (int i=0;i<nums.length;i++) {
	        runningSum += nums[i];
	        
	        if (k != 0) runningSum %= k; 
	        
	        Integer prev = map.get(runningSum);
	        if (prev != null) {
	            if (i - prev > 1) return true;
	        }
	        else 
	        	map.put(runningSum, i);
	    }
	    return false;
    }
     
    
    public static void main(String[] args) {
    	int[] arr = {23, 2, 4, 6, 7};
    	int k = 6; //-6

    	System.out.println("True? : " + checkSubarraySum(arr, k)); //true
    	System.out.println("True? : " + checkSubarraySum2(arr, k)); //true
    	
 	    
    }
}
