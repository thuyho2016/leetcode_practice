
/*
 * 198. House Robber ( easy level)
 * https://leetcode.com/problems/house-robber/
 * 
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, 
the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and 
it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house,
determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.

Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
 

This is the problem " Given an array, find the maximum sum of a subsequence with the constraint that no two numbers should be adjacent in the array"
It means "Find the maximum sum in an array such that no two elements are adjacent"


https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems./209401
1. Find recursive relation
2. Recursive (top-down)
3. Recursive + memo (top-down)
4. Iterative + memo (bottom-up)
5. Iterative + N variables (bottom-up)

Case 1: Rob the current house
  If current ith house is selected, it means he can't rob  previous (i-1)th or (i+1)th house, but cab safely proceed to (i-2)th or (i+2)th and get all cumulative money that follow -
    
    "robbery of current house + money from house before previous"
 
    ith_house_is_selected = currentHouseValue + rob(A, i-2) 
 

Case 2: Do not rob the current house
   If current ith house is not robbed, it means he will get all possible money of (i-1)th or (i + 1)th house and so on...

      "money from the previous house robbery and any money captured before that"
 
      ith_house_is_not_selected = rob(A, i-1)

 
  rob(A[],i) =  0  if i < 0
                  (A[i] + rob(A, i - 2)  if ith house is selected
                 /
             max  
                 \ 
                  (rob(A, i - 1)         if ith house is NOT selected
                  
  To get Max money robbed: : rob(i) = Math.max( ith_house_is_selected, ith_house_is_not_selected)



Solution 1: recursive
  
   rob(int[] A) {
      return rob(A, A.length - 1);
   }
   
     rob(A[],i) {
           if (A.length == 1) return A[0];
           
           if ( i < 0) return 0;
            
           int ith_house_is_selected = A[i] + rob(A, i - 2)
           int ith_house_is_not_selected = rob(A, i - 1)  
           
           return Math.max(ith_house_is_selected, ith_house_is_not_selected)
      }
               
  Time complexity: O(n^2)
  

Solution 2:  Top down approach - Use Memoization 
  1. cache is passed as parameter method
  
   rob(int[] A) {
      return rob(A, A.length - 1);
   }
   
  rob(A, i, int[] cache) {
        if ( i < 0) return 0;
        
        if ( cache[i] != 0) return cache[i];
        
        ith_house_is_selected = A[i] + rob(A,i-2, cache);
        ith_house_is_not_selected = rob(A, i-1, cache);
 		
 		return cache[i] = Math.max(ith_house_is_selected, ith_house_is_not_selected);
 	
  2. cache declaration is global level
  
 	    int cache[] = new int[A.length + 1];
 	    
 	    rob(int[] A) {
      		return rob(A, A.length - 1);
        }
 		rob(A, i) {.....}
 		
  Time and Space complexity: O(n)	


===>Tweak solution: cache is used local level - Iterative + Bottom up approach
  
  rob(A) {
     int[] cache = new int[A.length];
     cache[0] = A[0];
     cache[1] = Math.max(A[0], A[1]);
     
     for ( int i = 2; i < A.length; i++) {
     	ith_house_is_selected = A[i] + cache[i-2];
        ith_house_is_not_selected = cache[i-1];
        
        cache[i] = Math.max(ith_house_is_selected, ith_house_is_not_selected);
     }
     return  cache[A.length - 1];
  }
 
   Time and Space complexity: O(n)
   

Efficient solution: Iterative + 2 pointers
Time: O(n), Space complexity: O(1) we are not using extra space

              Index  0	  1	  2	  3	  4
1st round     A[]    2 	  7	  9	  3	  1
 swap                |    |   |
                  prev2 prev1 i
                        tmp = 7 , prev1 = max(9 + 2, 7) = 11

2nd round , swap
 A[]    2 	7	 9	 3	 1
            |    |   |
        prev2  prev1 i        
               tmp = 11, prev1 
 
*/

public class HouseRobberI_MaxSum_NoTwoNumbersAdjacent 
{
	
	//Efficient solution: Iterative + 2 pointers, tmp variable
	//Time complexity : O(n), Space complexity: O(1)
	public static int rob2(int[] A) {
		 if (A == null || A.length == 0) return 0;
		 if (A.length == 1) return A[0];  //base case - has 1 house
		 
		 //base case - has 2 houses
	     int prev2 = A[0];
	     int prev1 = Math.max(A[0], A[1]);  //has 2 houses , prev1 = max(2,7) = 7
	     
	     for ( int i = 2; i < A.length; i++) {
	    	 int tmp = prev1;						//tmp = = 7, 11 , 11
	    	 prev1 = Math.max(A[i] + prev2, prev1); //prev1 = max(9 + 2, 7) = 11 , prev1 = max(3 + 7, 11) = 11, prev1 = max(1+11, 11)=12
	    	 prev2 = tmp;                           //prev2 = 7, 11, 11
	        
	     }
	     return  prev1; //12
	}
		
		
	//Dynamic Program: Prefer Tweak solution: cache is used local level - Bottom up
	//Time and Space complexity: O(n)
	
	public static int rob(int[] A) {
		if (A == null || A.length == 0) return 0;
		
		if (A.length == 1) return A[0];  //has one house
		
		 int[] cache = new int[A.length]; //int[] dp = new int[A.length] - create cache array to hold money robbed 
		 
		 //base case - has 2 houses
	     cache[0] = A[0];
	     cache[1] = Math.max(A[0], A[1]);  //max(2,7) = 7
	     
	     for ( int i = 2; i < A.length; i++) {
	    	 
	     	int ith_house_is_selected = A[i] + cache[i-2];    //= 9 + cache[0] = 9 + 2 = 11
	        int ith_house_is_not_selected = cache[i-1];       //= cache[1] = 7
	        
	        cache[i] = Math.max(ith_house_is_selected, ith_house_is_not_selected); //i = 2: max(11,7) = 11, then cache = [2,7,11]
	         																	   //i = 3: max(3 + 7, 11)=11, then cache = [2,7,11,11]
	        																	   //i = 4: max(1 + 11, 11)=12, then cache = [2,7,11,11,12]
	        //You can combine 3 lines to:   cache[i] = Math.max( A[i] + cache[i-2], cache[i-1])
	     }
	     return  cache[A.length - 1]; //get last element from cache =[2, 7, 11, 11, 12]
	}
	
	
	
	
	public static void main(String[] args)
	{
		int[] nums = {2,7,9,3,1};
		System.out.println(rob(nums)); //12
		System.out.println(rob2(nums)); //12
		
		int[] nums2 = {1,2,3,1};
		System.out.println(rob(nums2)); //4
		System.out.println(rob2(nums2));
		
		int[] nums3 = {1,2};
		System.out.println(rob(nums3)); //2
		System.out.println(rob2(nums3));
	}
}
