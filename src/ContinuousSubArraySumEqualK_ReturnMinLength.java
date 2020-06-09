
/*
 * 209. Minimum Size Subarray Sum
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 * 
  Find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
  
  Input: [2,3,1,2,4,3] and s = 7,
  Output: 2
  Explanation: subarray [4,3] , the minimal length = 7
  
  Apply Math.min
*/
public class ContinuousSubArraySumEqualK_ReturnMinLength{

	/**
	Sliding window Using 2 pointers - left and i is end pointer 
	
	Iterate over nums:
	Add nums[i] to sum
	While sum is greater than or equal to s:
		Update min = Math.min(ans, i −left +1 ), where i −left +1  is the size of current subarray

    	Subtract nums[left] from sum and increment left

	Time complexity: O(n). Single iteration of O(n). Each element can be visited atmost twice, once by the right pointer(i) and once by the left pointer.
	Space complexity: O(1) extra space. Only constant space required for  \text{sum}, \text{ans}.
	*/
	public static int minSubArrayLen( int[] nums, int k) {
		int sum = 0;
		int windowStart = 0; //left pointer 
		
		int minLen = Integer.MAX_VALUE;  //minWindowSize
		
		if (nums == null || nums.length == 0)
		    return 0;
		
		 for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {  //right pointer - windowEnd
	            sum += nums[windowEnd];         //sum = 2 + 3 + 1 + 2 = 8 
	            
	            while (sum >= k) {     //when sum is greater or equal k , update minLen using index.  sum = 8 >= k=7 
	            	
	            	minLen = Math.min(minLen, windowEnd - windowStart + 1); // i = 3 - 0 + 1 = 4 , so min = 4; 4, 3
	                sum = sum - nums[windowStart]; //// to shrink window from left side , 8 - 2 = 6 , 
	                windowStart++; // increment window start
	            }
	      }    // when i = 5, left = 4, exit for loop 
		 return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
	    
	}
	
	//2 for loop so O(n^2), Space complexity : O(1)
	public static int minSubArrayLen2( int[] nums, int k ) {
		
	    int minLen = Integer.MAX_VALUE;  //int maxLen = 0
	    
	    for (int i = 0; i < nums.length; i++) {
	        int sum = 0;  
	       
	        for (int j = i ; j < nums.length; j++) { // j should point at i index
	        	
	        	sum += nums[j];
	            if(sum == k) {               
	                int length = j - i + 1;   
	            	minLen = Math.min( minLen, length); //update minLen            
	            }
	        }
	    }
		return minLen;
	}
		    
	
	public static void main(String[] args) {
	   int[] arr = {2,3,1,2,4,3};
	   int s = 7;
	   	 
	   System.out.println(minSubArrayLen( arr, 7)); //len = 2
	   System.out.println("Min len: " + minSubArrayLen2( arr, 7)); //2	 
	
	}
}
	
	