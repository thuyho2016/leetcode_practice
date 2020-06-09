import java.util.HashMap;
import java.util.Map;

/*
 * 325. Maximum Size Subarray Sum Equals k
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
 * 
 Given an array nums and a target value k, find the maximum length of a sub-array that sums to k
 
 Example 1:
 Given nums = [1, -1, 5, -2, 3], k = 3,
 return Sub Array length is 4. (because the subarray [1, -1, 5, -2] sums to 3 and 4 is the longest)

Example 2:
Given nums = [-2, -1, 2, 1], k = 1,
return 2. (because the subarray [-1, 2] sums to 1 and 2 is the longest)

Can you do it in O(n) time?

Solution:
- use 2 pointers to find out the length of subarray .
- Apply Math.max
 */

public class ContinuousSubArraySumEqualK_ReturnMaxLength{
	
	/** Better solution: 
	    Use HashMap to store sum as key and value as current index ( that is right index of the leftmost subarray)
	    
	    sum = preSum + k => preSum = sum - k
	    If difference (sum - k) value appears in hashmap, then update maxLen =  value(that is index) - current index
	  
	    Time and Space O(n)
	*/
	
	public static int maxSubArrayLen2(int[] nums, int k) {
		if (nums == null || nums.length == 0)   return 0;		
		
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);  // add this fake entry to make sum from 0 to j consistent
        
        int sum = 0;
        int maxLen = 0;
        
        for (int i = 0;i < nums.length;i++) {
            sum += nums[i]; //sum starts at first element of array
        
            // see if there is a previous array whose prefix sum is (sum - k)
            int preSum = sum - k; // difference 3 - 10 = -7, -2, 0 (when sum is equal k)
            
            if(map.containsKey(preSum)) { // if preSum appear previously,
                maxLen = Math.max(maxLen, i - map.get(preSum)); // length = current index - index of preSum  max (0, 2 - (-1)) = (0, 3)=3
            }  
          
             //if the current sum value has not seen ( first time), add to map
            if(!map.containsKey(sum))  { 
                map.put(sum, i);  //{0=-1, 3=0}, {0=-1, 3=0, 8=1}, {0=-1, 3=0, 8=1, 10=2}
            }
        }

        map.forEach((key, value) -> { System.out.println( key + " = " + value);
        });
        
         return maxLen == Integer.MIN_VALUE ? 0 : maxLen;
        //return maxLen;
	}
	

	//O(n^2), Space complexity : O(1)
	public static int maxSubArrayLen( int[] nums, int k ) {
		
	    int maxLen = Integer.MIN_VALUE;  //int maxLen = 0
	    
	    for (int i = 0; i < nums.length; i++) {
	        int sum = 0;  
	       
	        for (int j = i ; j < nums.length; j++) { // j should point at i index
	        	
	        	sum += nums[j];
	            if(sum == k) {                // sum = k = 10, i = 0, j = 2 , so I can find out the length of subarray
	                int length = j - i + 1;   // 2 - 0 + 1 = 3
	            	maxLen = Math.max( maxLen, length); //update maxLen=3	            
	            }
	        }
	    }
	    return (maxLen == Integer.MIN_VALUE) ? 0 : maxLen;
	}	

	
    public static void main(String[] args) {
        int[] arry1 = {-2, -1, 2, 1};
        int k = 1;
        
      //  int[] arry1 = {3,5,2,4};
     //   int k = 10;
        
        int result = maxSubArrayLen2(arry1 , k); //subarray [-1,2]
        System.out.println("Max len: " + result); //2
        
        result = maxSubArrayLen2(arry1 , k); //subarray [-1,2]
        System.out.println("Max len: " + result); //2
        
        
   /*   int[] arry2 = {1, -1, 5, -2, 3};
        k = 3;

        System.out.println( maxSubArrayLen(arry2 , k));

        System.out.println( maxSubArrayLen2(arry2, k));  //len = 4
   */     
        		
    }
}
	
	