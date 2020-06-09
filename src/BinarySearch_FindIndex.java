
import java.util.Arrays;
import java.util.List;

/*
Given a sorted array of n elements, write a function to search index of a given element in arr.

Example 
arr[] = { 2, 3, 4, 10, 40 }, target = 10 

Output: return index of 10 is 3

use binary search to find out the index of the largest element


 time complexity to O(Log n).
 
 */

public class BinarySearch_FindIndex {
		
	//1. Recursive - find index  of target. Return - 1 if not found
	public static int binarySearch(int arr[], int l, int r, int target) 
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
		
	//Iterative implementation of Binary Search
	private static int binarySearch2(int arr[], int target) 
    { 
        int l = 0, r = arr.length - 1; 
        while (l <= r) { 
            int m = l + (r - l) / 2; 
  
            // Check if x is present at mid 
            if (arr[m] == target) 
                return m; 
  
            // If target greater mid, target is in the right sub-array
            if (arr[m] < target) 
                l = m + 1; 
  
            // If target is smaller,  target is in the left sub-array
            else
                r = m - 1; 
        } 
  
        // if we reach here, then target was not present 
        return -1; 
    } 
  
	
	public static int binarySearch3( List<Integer> nums, int target) { //nums =[2,5] target = 3
		int left = 0;
		int right = nums.size() - 1; // index of last element
		
		while ( left <= right) {       // go to each element
			int mid = left + (right - left) / 2;
			
			if (nums.get(mid) == target) 
				return mid;
			
			else if (nums.get(mid) < target)  // left side 2 < 3
				left = mid + 1 ;
			else  // nums.get(mid) >= target   // 5 > 3
				right = mid - 1;
		}
		return left;
	}
	
	public static void main (String[] args)
    {	
		int[] arr = {2, 3, 4, 10, 40};
		System.out.println("Index of 10: " + binarySearch(arr, 0, arr.length - 1, 10));  //3
		
		System.out.println("Index of 10: " + binarySearch2(arr, 10));  //3
		
		List<Integer> list = Arrays.asList(2, 3, 4, 10, 40);
		System.out.println("Index of 10: " + binarySearch3(list, 10));
		
    }
}