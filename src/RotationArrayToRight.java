/*
 * 189. Rotate Array (level = easy)
Given an array, rotate the array to the right by k steps, where k is non-negative.

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]


Explanation: Shift array to right  7 is shifted
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

https://leetcode.com/problems/rotate-array/

Could you do it in-place with O(1) extra space? Reverse
1. we firstly reverse all the elements of the array. 
2. Then, reversing the first k elements followed by reversing the rest n-k elements gives us the required result.

*/

public class RotationArrayToRight  {
	
/*	Using Reverse
   Original List                   : 1 2 3 4 5 6 7
	After reversing all numbers     : 7 6 5 4 3 2 1  
	After reversing first k numbers : 5 6 7 4 3 2 1   //k
	After revering last n-k numbers : 5 6 7 1 2 3 4 --> Result

	 for example, nums = [1,2,3,4, 5,6,7] and k = 3, 
	 Steps:
	 1. first we reverse [1,2,3,4], it becomes[4,3,2,1]; 
	 2. then we reverse [5,6,7], it becomes [7,6,5], 
	 3. finally we reverse the array as a whole, it becomes[4,3,2,1,7,6,5] ---> [5,6,7,1,2,3,4]. 
	 
	 Reverse is done by using two pointers, one point at the head and the other point at the tail,
	 after switch these two, these two pointers move one position towards the middle
	
	 Time complexity : O(n). n elements are reversed a total of three times
	 O(1) space solution
	*/
	
	// O(1) space and O(n) time
	public static void rotateRight( int[] nums, int k) {
		int[] arr = new int[nums.length]; // create a new array 
		
		if (nums == null || nums.length <= 1) {
			return;
		}

		//3 % 7 = 3
		k = k % nums.length;
		int len = nums.length - 1;
		
		//reverse half left to len - k 
		reverse(nums, 0, len - k);  // start from index 0 -> 3
		
		//reverse half right
		reverse(nums, len - k + 1, len);  //4 ->6
		
		//reverse the array as a whole
		reverse(nums, 0, len);    // 0 ->6
		
		for(int i = 0; i < nums.length; ++i) 
			System.out.print(nums[i] + ","); 
		
	}
	
	private static void reverse(int[] nums, int lo, int hi){
		int tmp = 0;
		while (lo < hi) {
			//swap
			tmp = nums[lo];
			nums[lo] = nums[hi];
			nums[hi] = tmp;
			lo++;
			hi--;
		}
	}
	
	
	//brute force
	public static void rotate(int[] a, int k) {
		
		for (int i = 0; i < k; i++) {
		    for(int j = 0; j < a.length; ++j) {
		    	//swap
		    	int temp = a[j];
		    	a[j] = a[a.length - 1];
		    	a[a.length-1] = temp;
		    }
		  
		}
		 
	}
	
		

	public static void main (String[] args)
    {
		
		System.out.println("\nReverse: "); 
		int[] array2 = {1,2,3,4,5,6,7};
		int k = 3;
		rotateRight(array2, k);   //[5,6,7,1,2,3,4]
		
		
		System.out.println("\nReverse2: ");
		int[] array3 = {1,2,3,4,5,6,7};
		rotate(array3, k);
		for(int i = 0; i < array3.length; ++i) 
			System.out.print(array3[i] + ","); 
		
    }
	
	
}
