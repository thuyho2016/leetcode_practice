
/*
 Find the max sum continuous subarray of a fixed size K
 
Input: [4, 2, 1, 7, 8, 1,2,8,1,0 ], size k = 3
Output: 16


Input: {2, 1, 5, 1, 3, 2} , size k = 3
Output: 9 

Time Complexity O(n)
 */
public class ContinuousSubArraySizeK_FindMaxSum {
	
    /** Prefer Solution 2 to easy understand: apply sliding window - find the maximum sum of any continuous subarray of size k
	 
	  Arry = [1, 9, -1, -2, 7, 3, -1, 2] , k = 4,  
	   
	  1.  window = [ 1, 9, -1, -2] , add all elements in window size 4
	      windowSum = 1 +  9 + ( -1) + (-2)  = 7 ,  so maxSum = 7
	  
	  After windowSize equals k. To Shift window to the right side, I need to add  new element from the right side to the window and also remove the first element from the left window.
	  
	  2.  window = [ 9, -1, -2, 7] , to add 7, shift Right pointer to the right window and also remove 1 from the left window.
	        windowSum = 7 + add 7 - 1  = 13
	        maxSum = 13
	        
	  3.  window = [ -1, -2, 7, 3 ] ,
	        widnowSum = 13 + 3 - 9 = 7   ( add arry[right] = 3 to window and substract element from the left window = 9)
	        maxSum = 13 (remain the same)
	                
	  4. window = [ -2, 7, 3 , -1] ,  
	         widnowSum =  7 + (-1) - (-1) = 7         
	         maxSum = 13 (remain the same)
	    
	  5.  window = [ 7, 3 , -1, 2] ,  
	         widnowSum =  7 + 2 - (-2) = 11       
	         maxSum = 13 (remain the same)           
	   
	  
	  so, The formula to calculate windowSum:   windowSum = windowSum + arry[right] - arry[right - k] 
	       right - k is index of left window  
          right pointer has index 4, size k = 4 ---> arr[right] = 7 , arry[right - k] = arry[4 - 4] = arry[0]= 1 
	      --> widnowSum = 7 + 7 - 1 = 13
	                
	*/
	public static int maxSumSubArrayHasSizeK2(int[] nums, int k) {
		if (nums.length == 0) return 0;
		
		int maxSum = 0;
		int windowSum = 0;
		
		//Step 1
		for (int i = 0; i < k; i++) {   // start 0 to 3 < 4
			windowSum += nums[i];      // windowSum = 7
		}
		
		//Step 2 to step 5
		for (int right = k; right < nums.length; right++ ) {  // right index = 4 
			windowSum += nums[right] - nums[right - k];    //+ nums[4] = 7 --> add element from right window,   - nums[0] --> substract first element from left window
			maxSum = Math.max(maxSum,  windowSum);
		}
		return maxSum;
			
	}
	
	//Prefer this
	public static int maxSumSubArrayHasSizeK(int[] nums, int k) {
		if (nums.length == 0) return 0;
		
		int maxSum = Integer.MIN_VALUE;
		int curSum = 0;   
		
		for(int i = 0; i < nums.length; i++) {   // nums = {1, 9, -1, -2, 7, 3, -1, 2};
			curSum += nums[i];                   // curSum = nums[0] + nums[1] + nums[2] + nums[3] =  1 +  9 + (-1) + (-2) = 7
												 // i = 4: curSum = curSum + nums[4] = 6 + 7 = 13 . i = 5: curSum = 4 + 3 =7. i = 6: curSum=8+(-1)=7
			//step 2
			if (i >= k - 1) {                       // i = 3 >= k = 4 - 1 = 3 curSum I am adding so far = 7. i = 4, curSum = 13 above
				maxSum = Math.max(maxSum, curSum);  //i = 3: maxSum = max(0,7) = 7. i = 4: maxSum = max(7, 13) = 13. i = 5: maxSum=(13,7) = 13
				
				//to shrink window from left side by substracting the first element from left window (i - k + 1)
				curSum = curSum - nums[i - (k-1)]; // i = 3 >= k - 1 : nums[i - (k-1)] = nums[3 - (4-1)] = nums[0] = 1, so curSum = 7 - 1 = 6
				                                   // i = 4 >= k - 1 : nums[i - (k-1)] = nums[4 - (4-1)] = nums[1] = 9, so curSum = 13 - 9 = 4
			}										//i = 5:  curSum = 7 - (-1) = 8. i = 6: curSum = 7 -(-2) = 9
		}
		return maxSum;
	}
	
	
    public static void main(String[] args) {
 
    	int[] input3 = {1, 9, -1, -2, 7, 3, -1, 2};
		System.out.println( maxSumSubArrayHasSizeK(input3, 4)); //max sum = 13 with subarray = [9, -1, -2, 7]
		System.out.println( maxSumSubArrayHasSizeK2(input3, 4));
		
		int[] input = {4, 2, 1, 7, 8, 1,2,8,1,0};
    	int k = 3;
    	System.out.println(maxSumSubArrayHasSizeK(input, k)); // 16 , subarray of size 3 = {1,7,8}
    	System.out.println(maxSumSubArrayHasSizeK2(input, k)); 
    	
		int[] input2 = {2, 1, 5, 1, 3, 2};
		System.out.println(maxSumSubArrayHasSizeK(input2, k)); //9 with subarray [5, 1, 3]
		System.out.println(maxSumSubArrayHasSizeK2(input2, k)); 
		 			
	
    }
}