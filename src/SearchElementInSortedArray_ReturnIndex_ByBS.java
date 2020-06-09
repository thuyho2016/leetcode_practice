/* 
 * 704. Binary Search
 * https://leetcode.com/problems/binary-search/
 * 
 * This is for SORTED array
 * 
 * Implement Binary search in java using divide and conquer technique.
 * 
 * Given a sorted array arr[] of n elements, write a function to search a given element x in arr[].
 *
 * int arr[] = { 2, 3, 4, 10, 40}; 
 * number k = 10
 * 
 * Output: index = 3

702. Search in a Sorted Array of Unknown Size
https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/

Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4

Explanation: 9 exists in nums and its index is 4

*  Time complexity to O(Log n) and Space is O(1) 
*  It ONLY works with sorted array because binary search is for sorted array only

 */


public class SearchElementInSortedArray_ReturnIndex_ByBS {    

	/* https://www.geeksforgeeks.org/binary-search/
	 * 
	 * Use Binary Search in using divide and conquer technique.
 
	1. Compare x with the middle element.
	     If x matches with middle element, we return the mid index.
	2. Else if target is less than the mid element, then x can only stay in left half subarray after the mid element. 
		So we recursive for left half.
	3. Else (target is greater), recursive for the right half
	 
	  Returns index of target if it is present in array, else return -1 

	 */
	
	//Way 1:  use Recursive
	public static int searchIndexOfNumber(int[] arr, int target) {
	  
       int len = arr.length - 1;
       return binarySearch(arr, 0, len, target);
    }
	
    static int binarySearch(int arr[], int l, int r, int target) 
    { 
        while ( l <= r ) { 
            int mid = l + (r - l) / 2; 
  
            // If the element is present at the middle 
            if (arr[mid] == target) 
                return mid; 
  
            // If target is smaller than mid, then it can only be present in left sub-array 
            if (target < arr[mid] ) 
                return binarySearch(arr, l, mid - 1, target); 
  
            // Else the element can only be present in right sub-array 
            return binarySearch(arr, mid + 1, r, target); 
        } 
  
        // We reach here when element is not present in array 
        return -1; 
    } 
  
    
    // Way 2: Binary Search use Iterative 
    // Returns index of x if it is present in arr[], 
    // else return -1 
    int binarySearch(int arr[], int target) 
    { 
        int l = 0, r = arr.length - 1; 
        
        while (l <= r) { 
            int m = l + (r - l) / 2; 
  
            // Check if x is present at mid 
            if (target == arr[m]) 
                return m; 
            
            // If target is smaller mid , then it can only be in left sub-array 
            if (target < arr[m] ) 
                r = m - 1; 
            
            else  // If target is greater than mid , then it is present in right sub-array range (mid + 1, right)
            	l = m + 1; 
        } 
  
        // if we reach here, then element was not present 
        return -1; 
    } 
  
    public static void main(String args[]) 
    { 
    	
        int[] arr = { 2, 3, 4, 10, 40, 0 };  
        System.out.println("Index: " + searchIndexOfNumber(arr, 10)); //3
        
        int[] arr1 = {-1,0,3,5,9,12};  
        System.out.println("Index: " + searchIndexOfNumber(arr1, 9)); //4
        
        int[] arr2 = {0,1,2,3,4,5};
        System.out.println("Index : " + searchIndexOfNumber(arr2, 0)); //expect 0
        
        int[] arr3 = {1,2,3,4,5,0}; // this test failed
		System.out.println("Index " +  searchIndexOfNumber(arr3, 0)); //Expected:5
        
		int[] arr4 = {9, 12, 17, 2, 4, 5};//FAILED 
		System.out.println("Index " +  searchIndexOfNumber(arr4, 2)); //3
		
    } 
} 



