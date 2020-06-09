package sort;

/*
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] 
 * and >= nums[2] <= nums[3]....
   For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
   
   The basic idea is to make sure that every odd position is greater than (or equal to) its two adjacent even postions. 
 */

import java.util.Arrays;

public class WiggleSort  {
	
	
	public static void wiggleSort2(int[] nums) {
	    Arrays.sort(nums);
	    for (int i = 1; i < nums.length - 1; i += 2) {
	        swap(nums, i, i + 1);
	    }
	}

	private static void swap(int[] nums, int i, int j) {
	    int temp = nums[i];
	    nums[i] = nums[j];
	    nums[j] = temp;
	}
	
	//The basic idea is to make sure that every odd position is greater than (or equal to) its two adjacent even postions. 
	//For example, if the current odd position is i, then we need to make sure the nums[i-1] <= nums[i] and nums[i+1] <= nums[i].
	
	public static void wiggleSort(int[] nums) {
    	 for (int i = 1; i < nums.length; i++) {
             if (i % 2 == 0 && nums[i-1] < nums[i]) {  // at even index, check if it's greater than previous number
                 swap(nums, i-1, i);
             }
             if (i % 2 != 0 && nums[i-1] > nums[i]) {  // at odd index, check if it's smaller than previous number
                 swap(nums, i-1, i);
             }
         }
    }
	
	public static void main (String[] args)
    {
		int[] nums = {3, 5, 2, 1, 6, 4};
		wiggleSort(nums);
		for (int i = 0; i <nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		
		System.out.println();
		
		wiggleSort2(nums);
		for (int i = 0; i <nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
    }
	
}