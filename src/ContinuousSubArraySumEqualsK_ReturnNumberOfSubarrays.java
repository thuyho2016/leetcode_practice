import java.util.HashMap;
import java.util.Map;

/*
 * 560. Subarray Sum Equals K
 * https://leetcode.com/problems/subarray-sum-equals-k/description/
 * 
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2    ( 2 arrays [1,1] and [1,1] 

[1, 2, 3, 4, 5] and k is 9 
then there are two subarrays that sum to 9; [2, 3, 4] and [4, 5]
 
 */
public class ContinuousSubArraySumEqualsK_ReturnNumberOfSubarrays {

    /* Best solution: Using hashmap = (key,val) = (sum, increase value by 1 if the sum occurs again)
	
	If the same sum occurs again, we increment the count corresponding to that sum in the hashmap. 	   
	for every sum encountered, we also determine the number of times the sum − k has occurred already, since it will determine the number of times a subarray with sum k
	 
	Time complexity : O(n). The entire nums array is traversed only once.
	Space complexity : O(n). Hashmap  can contain upto n distinct entries in the worst case
	*/
	
	public static int subarraySum(int[] nums, int k) {
		
	    if (nums == null || nums.length == 0) return 0;

        HashMap<Integer, Integer> map = new HashMap<>();  //to store current sum as key and increase value if the number of times the same sum occurs as value
        map.put(0, 1);  //{0=1} to store the sum as key and the number of times the same sum occurs as value     
        
        int count = 0;
        int sum = 0; //sum so far
        
        for (int i = 0; i < nums.length; i++) {
        	sum += nums[i];       // Input = [1,2,3,4,5], sum = [ 1, 3, 6, 10, 15]
        	                    
	        int diff = sum - k;  // diff = 10 - 9 = 1, diff = 15 - 9 = 6

	        if (map.containsKey(diff)) {   //check if the number of times the (sum - k) has occurred already
	        	count += map.get(diff);   // diff 1 and 6 has occurs in map = {0=1, 1=1, 3=1, 6=1, 10=1}
	        	System.out.println("key has seen: " + diff + " count = " + count);
	        }
	        // store current sum as key and increase value by 1 if the sum occurs again
	        map.put(sum, map.getOrDefault(sum, 0)  + 1 ); //{0=1, 1=1}, 2nd round: map = {0=1,1=1, 3=1}, 3rd round: map = {0=1, 1=1, 3=1, 6=1}, 4th round map={0=1, 1=1, 3=1, 6=1, 10=1}	       
	    }
         
        return count;             
    }
 
	//O(n^2)
	public static int subarraySum2(int[] nums, int k) {
		int count=0;
		
        for(int i=0;i<nums.length;i++){
            int sum = nums[i]; // local variable should be declared HERE
            
            if(sum == k){
                count++;
            }
            
            for(int j = i+1;j < nums.length;j++){
                sum += nums[j];
                if(sum == k) count++;
            }
        }
        return count;
    }

	
    public static void main(String[] args) { 

  	   int[] arr = {3, 5, 2, 1}; // expect output 2 because found [[3], [2,1]]
  	   int k = 3;
  	   System.out.println("number of subArrays: " +  subarraySum(arr, k)); // 2
  	   System.out.println("number of subArrays: " +  subarraySum2(arr, k)); //2
     	  
       int[] arr2 = {1, 2, 3, 4, 5};
       int k2 = 9;
 	   System.out.println("number of subArrays: " +  subarraySum(arr2, k2)); //2 as [2, 3, 4] and [4, 5]
 	   System.out.println("number of subArrays: " +  subarraySum2(arr2, k2));
 	  
 	   int []arr3 = {1,1,1};
 	   System.out.println("number of subArrays: " +  subarraySum(arr3, 2)); //2
 	   System.out.println("number of subArrays: " +  subarraySum2(arr3, 2));
    }
}
