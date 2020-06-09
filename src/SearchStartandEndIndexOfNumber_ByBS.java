import java.util.Arrays;

/*
 * 34. Find First and Last Position of Element in Sorted Array
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 

Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
 
 
 Solution: 
 Use binary search twice to solve the problem. 
 The first binary search to find the first occurrence of the target. 
 The second binary search to find the second occurrence of the target.
 
 1. Get the mid point
 2. Check value
 3. Use 2 pointer start, end
 3. if value at mid point doesn't equal target, move pointer and eliminate half of our possible solution
 4. Move the pointer to midpoint index, reset the mid point
 
 Time & Space O(log(n)
 
 */
public class SearchStartandEndIndexOfNumber_ByBS {
	
	//Find target. if found, then try to find the first target and the last target from here
	// if target is not found return {-1, -1}
	 public static int[] searchRange(int[] nums, int target) {
	        
	        if(nums.length == 0) return new int[] {-1, -1};
	        
	        if(binarySearch(nums, target) == -1) return new int[] {-1, -1};
	        
			/*use two pointers to track startIndex and endIndex of the target*/
	        int start = binarySearch(nums, target);  //4
	        int end = start;  //initialize end = 4
	        
	        while((start >= 0) && nums[start] == target) {
	            start--; //3, 2
	        }
	        
	        while(end <= nums.length - 1 && nums[end] == target){
	            end++; // move end to 5 , continue to see if I can find target 
	        }
	        	        
	        return new int[]{start + 1, end - 1 };

	        
	    }
	    
	 /**
	  * binary search method: return the index if found, return -1 if target is not found.
	  */
    private static int binarySearch(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        
        while(left <= right){
            int mid = left + (right - left)/2;
            
            if(target == nums[mid]) 
            	return mid;    // If target is present at the middle 
            
            else if(target < nums[mid]) 
            	right = mid - 1;  // if target is smaller than mid, then it can only be in left sub-array 
            
            else 
            	left = mid + 1;  // If target is larger than mid, then it can only be present in right sub-array
        }
        
        return -1;
    }

	
	public static void main (String args[]) {
        int target = 8;
	    int[] nums = { 5,7,7,8,8,10 };  //[3,4]
	    
	    int[] res = searchRange(nums, target);
	    System.out.println(Arrays.toString(res));
	    
	    int[] nums2 = {5,7,7,8,8,10};
	    System.out.println(Arrays.toString(searchRange(nums2, 6)));
	    
	}

}
