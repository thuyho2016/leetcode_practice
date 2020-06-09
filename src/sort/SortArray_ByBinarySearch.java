package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

Create a Sorted Array Using Binary Search - Interview Question
Given an array, the task is to create a new sorted array in ascending order from the elements of the given array.

Examples:

Input : arr[] = {2, 5, 4, 9, 8}
Output : [2 4 5 8 9]

Time Complexity: O (N log N)
Auxiliary Space: O(N)

1. Get middle of the array and make it root
2. Do recursively the same for left and right halves
  - get middle of left half and make it left child of root
  - get middle of right half and make it right child of root 
  
  {1, 2, 3}
  
    2
  1   3  
  
 */

public class SortArray_ByBinarySearch {
		
	//interative
	public static List<Integer> sortByBinarySearch(int arr[]) 
    { 
		//int[] newArr = new int[arr.length];
		List<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < arr.length; i++) {
			if (list.size() == 0) 
				list.add(arr[i]);
			
			else {
				int left = 0;
				int right = list.size() - 1;
				int position = 0;  // index
				
		        while ( left <= right ) { 
		            int mid = left + (right - left) / 2; 
		  
		            // If the element is present at the middle, add arr[left] at mid + 1 
		            if (arr[mid] == arr[i]) {
		            	list.add(Math.max(0, mid + 1), arr[left]); // b.add(index, element);
		                break;
		            }
		            // If element is smaller than mid, then it can only be present in left sub-array. It means position should be between left and mid - 1
		            if (arr[i] < arr[mid] ) {
		                 position = right = mid - 1; 
		            }
		            // Else the element can only be present in right sub-array. It means position should be between mid + 1 and right
		            else {
		            	position = left = mid + 1; 
		            }
		            
		            if (left > right)  
	                { 
	                    position = left; 
	                    list.add(Math.max(0, position), arr[i]);  // Max(0, position) is used because sometimes pos can be negative, as smallest duplicates can be present in the array 
	                    break; 
	                }  
		        } 
		    } //else
	    } //for 
		
		
        return list;
    }
	
	public static void main (String[] args)
    {
	
		int[] nums = {3, 6, 2, 8, 1, 5};
		
		List<Integer> sorted = sortByBinarySearch(nums);
        System.out.print("Sort in binary search" + sorted);  // 1 2 3 5 6 8 
    }
	
	
}
 
	
