
/* 
 * 213. House Robber II
 * https://leetcode.com/problems/house-robber-ii/

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. 
That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police 
if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

**** NOTE: Difference with HouseRobberI is all houses arranged in a circle *****

Example 1:

Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.
Example 2:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.

 
Solution:
 
if rob first house, cannot rob the last since they are neighbor
if not rob the first house, can rob the last house
compare the two cases and return the max
	 
Subproblem 1: rob 0, not rob n-1  ==> rob(nums, 0, nums.length-2)
Subproblem 2: not rob 0,rob n-1  ==>rob(nums, 1, nums.length-1)
And find the bigger one of these two sub problems.

example:
Subproblem 1: rob house 1 ~ 8
Subproblem 2: rob house 2 ~ 9


Case 1: Rob the current house
If current ith house is robbed, it means he can't rob  previous (i-1)th or (i+1)th house, but cab safely proceed to 
(i-2)th or (i+2)th and get all cumulative money that follow -
    
    "robbery of current house + money from house before previous"
 
    ith_house_is_selected = currentHouseValue + rob(A, i-2) 
 

Case 2: Do not rob the current house
If current ith house is not robbed, it means he will get all possible money of (i-1)th or (i + 1)th house and so on...

      "money from the previous house robbery and any money captured before that"
 
      ith_house_is_not_selected = rob(A, i-1)

 
Solution 1: 
             int[] cache = new int[A.length];
             

    			   A[i] + cache[i - 2]  if ith house is selected
                 /
             max  
                 \ 
                   cache[i - 1]         if ith house is NOT selected
  
 
To get Max money robbed:  rob(i) = Math.max( ith_house_is_selected, ith_house_is_not_selected)
  

Solution 2:

              Index  0	  1	  2	  3	  4
1st round     A[]    2 	  7	  9	  3	  1
 swap                |    |   |
                  prev2 prev1 i
                        tmp = prev1 = 7 , 
                        prev1 = max(9 + 2, 7) = 11
                        prev2 = tmp = 7
2nd round,swap
 				A[]    2 	7	 9	  3	 1
            				|    |    |
        				prev2  prev1  i        
               				    tmp = prev1 = 11 (from above), 
               				    prev1 = max(ith_house_is_selected, ith_house_is_not_selected);
               				    prev2 = tmp = 11 
 */


public class HouseRobberII 
{
	//Efficient solution: - 2 pointers and tmp variable
    //Time: O(n), Space complexity: O(1)
	public static int maxRob(int[] nums) {
		int n = nums.length;
		
		if (nums == null || n == 0) return 0;
		
		// base case has one house
		if (n == 1) return nums[0]; 
		
		//base case has 2 houses
		if (n == 2) return Math.max(nums[0], nums[1]);
		 
		//with FirstHouse, withLastHouse
		int withFirstHouse = robHellper2(nums, 0, nums.length - 2);
	    int withLastHouse = robHellper2(nums, 1, nums.length - 1);
	        
	     return Math.max(withFirstHouse, withLastHouse);
	}
	
	
	// Differ from HouseRobber.java is use start and end pointers in helper method
	public static int robHellper2(int[] A, int start, int end) {
		 
		//base case - has 2 houses
	     int prev2 = A[0];
	     int prev1 = Math.max(A[0], A[1]);  //has 2 houses , prev1 = max(2,7) = 7
	     
	     for ( int i = start + 2; i <= end; i++) {
	    	 int tmp = prev1;						//tmp = = 7, 11 , 11
	    	 prev1 = Math.max(A[i] + prev2, prev1); //prev1 = max(9 + 2, 7) = 11 , prev1 = max(3 + 7, 11) = 11, prev1 = max(1+11, 11)=12
	    	 prev2 = tmp;                           //prev2 = 7, 11, 11
	        
	     }
	     return  prev1; //12
	}
	

	//solution: cache is used local level - Iterative + Bottom up approach.
	//Time and Space complexity: O(n)
	  public static int rob(int[] nums) {
		  if(nums == null || nums.length == 0) return 0;
		  
		  if(nums.length == 1) return nums[0];
		  
		  if(nums.length == 2) return Math.max(nums[0], nums[1]);
	        
		  return Math.max(robWith(nums, 0, nums.length - 2), robWith(nums, 1, nums.length - 1));
	  }
	  
	  // if rob first house, cannot rob the last since they are neighbor
	  // if not rob the first house, can rob the last house
	    // compare the two cases and return the max
	   //cache is used local level - Bottom up
	    public static int robWith(int[] nums, int start, int end){
	        
	        // for the size of dp: if nums.length = 5, then when it's 0-3 (rob last house)
	        // dp size should be 3 + 1 = 4, end = 3
	        // when it's 1->4; dp size is 4 + 1 = 5 because 1->4 are the indexs to nums
	    	
	        // in other words, when we rob house 0, dp actually starts filling value with its index 1, 
	    	//nothing in index 0 or it's initialized as 0
	        
	        int[] dp = new int[end + 1]; // +1 for length , This line is different with HouseRobber.java
	        //base case - has 2 houses
	        dp[start] = nums[start];
	        dp[start + 1] = Math.max(nums[start], nums[start + 1]);
	        
	        for(int i = start + 2; i <= end; i++)
	            dp[i] = Math.max(dp[i-2]+ nums[i], dp[i-1]);
	        
	        return dp[dp.length - 1];
	    }
	
		
	public static void main(String[] args)
	{
		int[] nums = {2,3, 2};
		System.out.println(maxRob(nums)); //3
		
		System.out.println(rob(nums)); //3
		
		int[] nums2 = {1,2,3,1};
		System.out.println(maxRob(nums2)); //4
		
		System.out.println(rob(nums2)); //4
	
	}
}
