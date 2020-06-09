import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * 41. First Missing Positive
 * https://leetcode.com/problems/first-missing-positive/

Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3


Example 2:

Input: [3,4,-1,1]
Output: 2
 
  Time complexity : O(n)
  Space complexity : O(1). This algorithm allocates only constant additional space.
 
 */


public class FindSmallestMissingNumber
{
	/** Easy to understand, but O(n)
	   1. Create a HashSet and add numbers to it.
	   2. Iteration - if set doesn't contains number, return	
	 */
	public static int firstMissingPositive(int[] nums) {
		if(nums.length==0) return 1;
	    
		HashSet<Integer> s = new HashSet<Integer>();
	    
	    for(int a: nums){
	        s.add(a);  //[-1, 1, 3, 4]
	    }
	    
	    int num = 0;
	    for(int i= 1; i<= nums.length; i++){
	        if(!s.contains(i)){ //i = 2
	        	System.out.println("Miss: " + i); //2
	        	return i;
	        }
	        num = i;  // update num
	    }
	    System.out.println("all numbers found: " + num);
	    return num + 1;   // + 1 to indicate next missing number for case input = [1] or [1,2]
    }
	
	
	// Way2: Use sort and traverse - sort takes O(nlogn)
	public static int firstMissingPositive2(int[] nums) {
        if (nums.length==0){
            return 1;
        }
        
        Arrays.sort(nums);
        
        int counter=0;
        for(int i=0;i < nums.length;i++){
            if(nums[i] == counter + 1 ) counter++;
            if(nums[i] <= counter) continue;
            else break;
        }
        return counter+1;
    }
	
	// Swap each element to its index position
	//Record which values are present by writing nums[i] to index num[i]-1
	//If nums[i] is between 1 and nums.length inclusive we save it at index nums[i]-1 by swapping it with index i
	
	public static int firstMissingPositive3(int[] nums) {
		if(nums.length == 0 || nums == null)
            return 1;
	     
		int res = 0;
	     
		for(int i = 0; i < nums.length; i ++)
		{

            while( nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1])
            {
            	//swapping it with index i
            	swap(nums, i, nums[i] - 1);
            }
        }
	     
	     //Pass 2, find first location where the index doesn't match the value
        for(int i = 0; i < nums.length; i ++) //[1, -1, 3, 4]
          if(nums[i] != i+1) { //i = 1
        	  res = i+1; //2
        	  break;
          }
        
        return res == 0? nums.length + 1 : res;
    }
	
	 public static void swap(int[] nums, int i, int j){
	        int temp = nums[i];
	        nums[i] = nums[j];
	        nums[j] = temp;
	}
	 
	public static void main(String[] args)
	{
		int[] nums = {1,2,0};
		System.out.println(firstMissingPositive2(nums));
		
		int[] nums2 = {3,4,-1,1};
		System.out.println(firstMissingPositive2(nums2)); //2
	}
}
