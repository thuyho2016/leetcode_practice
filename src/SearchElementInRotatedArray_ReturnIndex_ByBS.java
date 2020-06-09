/*
 * 33. Search in Rotated Sorted Array (Medium level) 
 *  https://leetcode.com/problems/search-in-rotated-sorted-array/
 * 
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4   // index of 0 is 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1  // because 3 is not found in array
 
 
 
Solution:  standard binary search - It works with BOTH UNSORTED and SORTED array
 
1. Take the middle and compare with target, if matches return.

2. if middle is bigger than left side, it means left is sorted
  2a. if [left] < target < [middle] then do recursion with left, middle - 1 (right)
  2b. Else left side is sorted, but target not in here, search on right side middle + 1 (left), right

3. if middle is less than right side, it means right is sorted
  3a. if [middle] < target < [right] then do recursion with middle + 1 (left), right
  3b. Else right side is sorted, but target not in here, search on left side left, middle -1 (right)

  
Let's take some examples and see how we can simplify the condition.

Original sorted array
[1, 2, 3, 4, 5, 6, 7]

After rotation, it might be something like
[3, 4, 5, 6, 7, 1, 2]
[6, 7, 1, 2, 3, 4, 5]
[1, 2, 3, 4, 5, 6, 7] <-- rotated and end up the same
and etc..

*  When you divide the rotated array into two halves, using mid index, at least one of them should remain sorted ALWAYS.

   [3, 4, 5, 6, 7, 1, 2]
-> [3, 4, 5] [ 6, 7, 1, 2]
  the left side remains sorted
  
   [6, 7, 1, 2, 3, 4, 5]
-> [6, 7, 1] [2, 3, 4, 5]
   the right side remains sorted

[1, 2, 3, 4, 5, 6, 7]
-> [1, 2, 3] [4, 5, 6, 7]
Both sides remain sorted.

If you know one side is sorted, the rest of logic becomes very simple.
If one side is sorted, check if the target is in the boundary, otherwise it's on the other side.   
 */

public class SearchElementInRotatedArray_ReturnIndex_ByBS {
	
	// One-pass Binary Search - Time Complexity: O(log n) and Space O(1)
	
	public static int searchIndex(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		
		while (left <= right) {
	        int mid = left + (right - left) /2;
	        
	        if(nums[mid] == target) return mid;
	        
	        // When dividing the rotated array into two halves, one must be sorted.
	        
	        // if middle is greater than left side - Check if the left side is sorted
	        else if (nums[mid] >= nums[left] ) {
	        	
	           // if left < target < mid
	            if (  nums[left] <= target && target < nums[mid] ) {
	            	//do recursion with left, mid -1 (right)       (target is in the left)
	            	right = mid -1;
	            }
	            else //search on right side mid + 1(left), right (target is in the right)
	              left = mid + 1;
	        }
	        
	        
	        //if middle is less than right side, right side is sorted
	        else { // or else if (nums[mid] < nums[left]) {  
	        	
	            // if middle < target < right
	            if (  nums[mid] < target  && target <= nums[right] ) {
	            	//do recursion with mid + 1 (left), right     (target is in the right)
	            	left = mid + 1;
	            } 
	            else { //search on left side left, mid - 1(right)  (target is in the left)
	                right = mid - 1;
	            }
	        }
	        
		}
		return -1;    //target is not found    
    }
	
	//check for duplicate number
	public static int searchIndex2(int[] nums, int target) {
		if(nums == null || nums.length == 0){
		       return -1;
			}
			 
			int left = 0, right = nums.length - 1;
			
			while (left <= right) {
		        int mid = left + (right - left) /2;
		        
		        if(nums[mid] == target) return mid;	        
	
		        // When dividing the rotated array into two halves, one must be sorted.	        
		        // if the middle is greater than left side - Check if the left side is sorted
		        if (nums[mid] > nums[left] ) {
		        	
		           // if left < target < mid
		            if (  nums[left] <= target && target < nums[mid] ) {
		              right = mid -1;  //do recursion with left, mid -1 (right),  (target is in the left)
		            }
		            else //search on right side mid + 1(left), right (target is in the right)
		              left = mid + 1;
		        }
		        
		        
		        //if the middle is less than left side, check if right side is sorted
		        else if  (nums[mid] < nums[left]) {
		        	
		            // if middle < target < right
		            if (  nums[mid] < target  && target <= nums[right] ) {
		              left = mid + 1; //do recursion with mid + 1 (left), right     (target is in the right)
		            } 
		            else {  //search on left side left, mid - 1(right)  (target is in the left)
		                right = mid - 1;
		            }
		        } else { // nums[mid] == nums[left]  // check for duplicate number
		        	left++; 
		        	 // Move forward 
		        	 // [1, 2, 1, 1, 1] -> [2, 1, 1,1]
		        }
			}
			
			return -1;   //target is not found  
	 }
	
	public static void main(String[] args) {
		int[] nums = {4,5,6,7,0,1,2};
		System.out.println("Index " +  searchIndex(nums, 0)); //4
		
		int[] arr = {9, 12, 17, 2, 4, 5};
		System.out.println("Index " +  searchIndex(arr, 2)); //3
		

        int[] arr2 = {0,1,2,3,4,5};
        System.out.println("Index: " + searchIndex(arr2, 0)); //expect 0
        
		int[] arr3 = {1,2,3,4,5,0};
		System.out.println("Index " +  searchIndex(arr3, 0)); //Expected:5		 
		
	}
}
