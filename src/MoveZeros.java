import java.util.Arrays;

/*
 * 283. Move Zeroes
 * https://leetcode.com/problems/move-zeroes/
 
Given an array nums, write a function to move all 0's to the end of it 
while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
		
*/
public class MoveZeros {
	public static void moveZeroes(int[] nums) {
		 int index = 0;
	        for(int i=0; i < nums.length;i++) {
	            if(nums[i]!= 0) {
	                nums[index] = nums[i];

	                System.out.println("non-zero element: "+ nums[index] + " at index=" + i);
	                index++;
	            }
	        }
    
	        System.out.println("total of non-zero number "+ index);
	       
	        // set 0  At index = 3
	        for(int i= index; i< nums.length;i++) {
	            nums[i] = 0; // set 0 At nums[3] as i = index = 3, nums = [1, 3, 12, 0, 12]; [1, 3, 12, 0, 0]
	        }
    }
	
	public static void main (String[] args)
	{
		int[] nums = {0,1,0,3,12};
		moveZeroes(nums);
		System.out.println(Arrays.toString(nums));
	}
}
