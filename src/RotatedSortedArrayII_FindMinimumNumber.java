/*
 * 154. Find Minimum in Rotated Sorted Array II (hard level)
 * 
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element. The array contains ***duplicates****.

Example 1:

Input: [1,3,5]
Output: 1
Example 2:

Input: [2,2,2,0,1]
Output: 0
Note:

This is a follow up problem to Find Minimum in Rotated Sorted Array.
Would allow duplicates affect the run-time complexity? How and why?



Steps: use 2 pointers left and right
- Go through array, get the number at low index
 In binary search we find out the mid point and decide to either search on the left or right depending on some condition.
 
- Find the mid element of the array.

------------>   ---->
2,	2,	3,	3,	1,	2	
            ----> decrease
            ^ inflection point
            
All the elements to the left of inflection point > first element of the array (value = 2) .
All the elements to the right of inflection point < first element of the array.
  
2,	2,	3,	3,	1,	2
left    mid     right  
 
1. If  arr[left] == arr[right] (duplicate case) , shift left pointer

2. If mid element > first element of array ( 5 > 3) this means that we need to look for the inflection point on the right. --->

3. If mid element < first element of array this that we need to look for the inflection point on the left.(<---)

4. stop our search when we find the inflection point, when either of the two conditions is satisfied:

	nums[mid] > nums[mid + 1] Hence, nums[mid+1] is the smallest.

	nums[mid - 1] > nums[mid] Hence, mid is the smallest.


 Time complexity Same as Binary Search O(logN)
 Space Complexity : O(1)
 
 Since the given array is sorted, we can make use of binary search. However, the array is rotated. So simply applying the binary search won't work here.
 
 */
public class RotatedSortedArrayII_FindMinimumNumber {

	public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
        	// here is lines which is different with problem 153
            while (left < right && nums[left] == nums[right]) {  // check for duplicate
                left++;
            }
            
            int mid = left + (right  - left) / 2;
            if (nums[mid] > nums[right]) { // IN THE RIGHT PART 2 > 2
                left = mid + 1;
            } else {  /// IN THE LEFT PART  //1, 3 ,5 
                right = mid;
            }
        }
        return nums[left];
    }
	  
	public static void main(String args[]) 
    { 
		int[] nums = {1,3,5};
		System.out.println("Min " +  findMin(nums)); //1
		
		int[] nums2 = {2,2,2,0,1};
		System.out.println("Min " +  findMin(nums2)); //0
    }
}
