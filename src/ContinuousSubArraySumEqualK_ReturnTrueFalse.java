
/* You have positive integers of array and target. You need to write a program that can tell if any consecutive numbers sum are matched to Target. 
 * For an example: 
 * array input: [4,6,2,9,20 ], Target: 12 
 *  Output: true because [4,6,2] has sum = 12
 *  
 *  target: 15. Output: false because no Consecutive Numbers
 * 
 * [3,2,9,5,8,12] , target = 13
 * Ouputt; True
 * 
 * Coding must be in Time : O(n), Space O(1)
*/


public class ContinuousSubArraySumEqualK_ReturnTrueFalse {
	/**
	 * Sliding Window
	 *
	 *     2 pointers i and j: j = 0, i = 1 
	 *     
	 *     Use for loop to iterate i 
	 *    
	 *     Shift i pointer until matching target , preSum = 3 + 2 + 9 = 14, i = 3
	 *     if  preSum  == target, return true;
	 *     if preSum > target, then shift j pointer
	 *     
	 *     E.g: [3, 2, 9, 5, 8, 12]  , target = 13
	 *             j shift to index 1
	 *          i goes from index 1, 2, 3
	 *          preSum = 2 + 9 + 5 = 14  > target , shift j 
	 *      
	 *      [3, 2, 9, 5, 8, 12] , i = 3
	 *             j at index 2
	 *             preSum = 9 + 5 = 14 > target , shift j 
	 * 	           
	 *       [3, 2, 9, 5, 8, 12]
	 *                 j at index 3 , i goes index 4 
	 *                 preSum = 5 + 8 = 13 equals target , return true
	 *       
	 */	
	
    public static boolean checkConsecutiveSubArrays( int[] nums, int target) { 
		int left = 0;
		int preSum =  0;//nums[left];
		
		 for ( int i = 0; i < nums.length; i++ ) {  
			 preSum += nums[i]; 
			 
			 if (preSum == target) {
				 return true;
			 }

			 if (preSum > target) { // then shift left pointer
				 left = i;
				 preSum = nums[left];
			 }
		}
		 return false;
	}
     
    //Time O(n^2)
    public static boolean subarraySum(int[] nums, int target) {
	

		for (int i = 0; i < nums.length; i++) {
	    	int curSum = 0; // cur Sum so far must be HERE
	        
	    	for (int j = i; j < nums.length; j++) {
	        	
	            curSum += nums[j];
	            
	            if (curSum == target) {
	            	return true;
	            } 
	        }
	    }
	    return false;
	}


    public static void main(String[] args) {
    	int[] a = {3,2,9,5,8,12}; 
    	  
        System.out.println(checkConsecutiveSubArrays(a, 13)); //true [5,8]
        System.out.println(checkConsecutiveSubArrays(a, 3));  //true [3]
        
        int[] a2 = {4, 6, 2 , 9, 20};
        System.out.println(checkConsecutiveSubArrays(a2, 12)); //true [4,6,2]
        
        System.out.println(checkConsecutiveSubArrays(a2, 15));  //false
 	    
    }
}
