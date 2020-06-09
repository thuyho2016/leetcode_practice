/*
 * Left rotation:
 * Input: int[] array = {1, 2, 3, 4};
 * Output: [3,4,1,2]
 * 
 * Explanation:
 * rotate 1 step to the left: 2, 3, 4, 1
 * rotate 2 step to the left: 3, 4, 1, 2
 * 
 * How to solve?
 * 1 is pushed to last element a[array.length - 1] and 4 will be at position 0, array[0] 
 * so, I need to swap element at index 0 and last index [array.length - 1] 
 * first loop: j = 0,   4 2 3 1  <- after swap  1 & 4
 *             j = 1,   4 1 3 2  <- after swap  2 & 1
 *             j = 2,   4 1 2 3  <- after swap 3 & 2
 *             j = 3,   4 1 2 3  <- after swap 3 and itself
 * 
 * 2nd loop:   j = 0,   3 1 2 4  <- swap 3 & 4
 *             j = 1,   3 4 2 1
 *             j = 2,   3 4 1 2
 *             j = 3,   3 4 1 2   <- after swap 2 and itself
*/

public class RotationArrayToLeft  {	

	/*
	 O(1) space solution

	 for example, nums = [1,2,3,4,5,6,7] and k = 3 
	 first, we reverse half left: [1,2,3], it becomes[3,2,1]; 
	 second, we reverse half right: [4,5,6,7], it becomes[7,6,5,4]
	 finally we reverse the array as a whole, it becomes[3,2,1,7,6,5,4] ---> [4 5,6,7,1,2,3]. 

	*/
	public static int[] rotateLeft( int[] nums, int k) {
		int[] arr = new int[nums.length]; // create a new array 
		
		if (nums == null || nums.length <= 1) {
			return nums;
		}
		//3 % 7 = 3
		k = k % nums.length;
		int n = nums.length - 1;
		
		reverse(nums, 0, k - 1 ); // reverse half left from index 0 -> k - 1 = 2

		reverse(nums, k , n);    // reverse half right k -> n  . It is  3 -> 6
		
		reverse(nums, 0, n);     // //reverse the array as a whole 0-> 6
		
		for(int i = 0; i < nums.length; ++i) 
			System.out.print(nums[i] + ","); 
		return arr;
	}
	
	/**	
	 Reverse is done by using two pointers, one point at the head and the other point at the tail,
	 after switch these two, these two pointers move one position towards the middle
	 */
	private static void reverse(int[] nums, int i, int j){
		int tmp = 0;
		while (i < j) {
			tmp = nums[i];
			nums[i] = nums[j];
			nums[j] = tmp;
			i++;
			j--;
		}
	}
	
    //Bruto: Time complexity : O(n*k)
	//Space complexity : O(1)O(1). No extra space is used
	public static void rotate(int[] a, int k) {
		
		for (int i = 0; i < k; i++) {
		    for(int j = 0; j < a.length; ++j) {
		    	//swap
		    	int temp = a[j];
		    	a[j] = a[a.length - 1];
		    	a[a.length-1] = temp;
		    }
		  
		}
		 for(int i = 0; i < a.length; ++i)  
			 System.out.print(a[i] + ",");
	}
	
		
	public static void main (String[] args)
    {
		// create a new array with same length = 4
		int[] array = {1, 2, 3, 4};
		int k  = 2 ; // rotate 2 steps
		System.out.println("\nBrute force");
		rotate(array, k);
		
		System.out.println("\nUse reverse");
		int[] array2 = {1,2,3,4,5,6,7};
		k = 3;
		rotateLeft(array2, k);  //[4, 5,6,7,1,2,3]
    }
}
