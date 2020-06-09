/*
 * 153. Find Minimum in Rotated Sorted Array (medium level)
 * 
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * 
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element. You may assume no DUPLICATE  exists in the array.


Input: [3,4,5,1,2] 
Output: 1

Input: [4,5,6,7,0,1,2]
Output: 0

Steps: use 2 pointers left and right

--------->  ----> increase
3,	4,	5,	1,  2	
        -----> decrease
          ^| inflection point  5 > 1
  

3,	4,	5,	1,  2
left    mid     right   


All the elements to the left of inflection point > first element of the array.
All the elements to the right of inflection point < first element of the array.

Algorithm 
1. Find the mid element of the array  mid = 2.

2. If mid element > first element of array (5 > 3) this means that we need to look for the inflection point on the right. --->

3. If mid element < first element of array this that we need to look for the inflection point on the left.(<---)

4. stop our search when we find the inflection point, when either of the two conditions is satisfied:

	nums[mid] > nums[mid + 1] Hence,nums[mid+1] is the smallest.

	nums[mid - 1] > nums[mid] Hence, nums[mid] is the smallest.


 Time complexity Same as Binary Search O(logN)
 Space Complexity : O(1)
 
 Since the given array is sorted, we can make use of binary search. However, the array is rotated. So simply applying the binary search won't work here.
 
 */
public class RotatedSortedArray_FindMinimumNumber {

	//Prefer Binary Search - array sorted
	public static int findMinimum(int[] nums) {
        int left = 0, right = nums.length -1;  //4
        
        while (left < right) {
        	
            int mid = left + (right - left) / 2; // mid=2, mid= 3
            
            if (nums[mid] >  nums[right]) {   // IN THE RIGHT PART  - Case 5 > 2 : arr[2] >= nums[4] ? 
            	left = mid + 1;               //so left = 3
            	
            } else {                // IN THE LEFT PART   arr[3] <= nums[4] => 1 < 2 , 
            	right = mid;        //so right = 3
            }
        }
        return nums[left];  //nums[3]
    }
	
	  
	public static void main(String args[]) 
    { 
		int[] nums = {3,4,5,1,2};
		System.out.println("Min " +  findMinimum(nums)); //1
		
		int[] nums2 = {4,5,6,7,0,1,2};
		System.out.println("Min " +  findMinimum(nums2)); //0
    }
}
