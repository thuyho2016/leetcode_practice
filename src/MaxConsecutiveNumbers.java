/*
 * 485. Max Consecutive Ones
https://leetcode.com/problems/max-consecutive-ones/
 
Input: [1,1,0,1,1,1]
Output: 3

Explanation: The first two digits or the last three digits are consecutive 1s.
             The maximum number of consecutive 1s is 3.
    
*/

public class MaxConsecutiveNumbers {
	
	//Approach: One pass
	//Time Complexity: O(N), where N is the number of elements in the array.
	//Space Complexity: O(1). We do not use any extra space.
	
	public static int findMaxConsecutiveOnes(int[] nums) {
        int result = 0;
        int count = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
            	count++;  // Increment the count of 1's by one.
            	result = Math.max(count, result);   // keep update result
            }
            else {
            	count = 0; //reset count of 1
            }
        }
        
        return result;
    }
	
	//Sliding window
	public static int findMaxConsecutiveOnes2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int res = 0;
        
        for(int left = 0; left < nums.length; left ++){
            if(nums[left] == 0) continue;
            
            int right = left;
            
            while(right + 1 < nums.length && nums[right + 1] == 1){ // if next number is 1, keep going
                right++;
            }
            res = Math.max(res, right - left + 1); //2, 3 distance between right and left pointer
        }
        return res;
    }
	
	public static void main(String[] args) {
		int[] nums = {1,1,0,1,1,1};
			
		System.out.println("maximum number of consecutive: " + findMaxConsecutiveOnes(nums));
		
		System.out.println("maximum number of consecutive: " + findMaxConsecutiveOnes2(nums));
		
    }
	
}