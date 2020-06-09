package sort;

import java.util.Arrays;

/*
 * 75. Sort Colors
 * https://leetcode.com/problems/sort-array-by-parity/

Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?

Time complexity : O(N) since it's one pass along the array of length NN
Space complexity : O(1) since it's a constant space solution.

Steps:
 low and high are inclusive boundaries of number 1
and (0,low-1) is contained with 0
and (high + 1, nums.length-1) is contained with 2
and (low,high) is contained with 1
 */

public class SortColors {

	//One-pass algrorithm using only constant space - quick sort algorithm
	public static void sortColors(int[] nums) {
        
	   int left =0;  //int low = 0;
	   int right =  nums.length -1;   //int high = ....
	   int idx = 0;
	   
	   while ( idx <= right ) { //[2,0,2,1,1,0]
	    	
	        if (nums[idx] == 0 ){ //swap left pointer & current element [2,0]
	        	swap (nums, left,  idx);
	        	left++;
	        	idx++;
	        	
	        } else if (nums[idx] == 2) {  //[ 2,1]
	        	swap(nums, idx, right);  //swap element of right pointer and current element
	        	right--;
	        } else { // nums[idx] == 1
	        	idx++;
	        }
	       
	    }
	}
	
	
	private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
	
	public static void main(String[] args) {
	     int[] a = {2,0,2,1,1,0};  
	     sortColors(a);
	     System.out.println(Arrays.toString(a));
	  
	}

}
	
	