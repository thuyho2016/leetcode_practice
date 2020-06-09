

/* 34. Find First and Last Position of Element in Sorted Array 
 Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
 */


public class FindDuplicateFirstAndLastIndex {
			 
	/* I can binary search for a duplicated number. I go through the list and count the number of integers between 1 and N/2. 
	 * If the count is greater than the number of possible integers in that range, then I know there's a duplicate in that range. 
	 * Otherwise, a duplicate must exist in the range of N/2+1 to N. Once I know which half of the range the duplicate is in, I can do recursion and binary search in that half, 
	 * then keep repeating the process until I've found a duplicated number. 
	 * 
Steps: 	 
1. Check if the middle element is the repeating one.
2. If not then check if the middle element is at proper position if yes then start searching repeating element in right.
3. Otherwise the repeating one will be in left.

Time complexity is O(n*log n) and Space complexity is O(1).

if Number is negative
[2, -1, 2, 1, 2]
int index = Math.abs(arr[i]) - 1;
	
*/
		
	//wrong output????
	
	//find the first index of duplicates of k in a sorted array
	 public static int searchFirstIndexOfK(int[] a, int k, boolean searchFirst) {

	     int left = 0, right = a.length - 1, result = -1;
	   //  Arrays.sort(a);
	     // [left : right] 
	     while (left <= right) { //0 <= 5
	    	 
	       int mid = (left + right ) /2 ;
	       
	       if (a[mid] == k) {
		       result = mid; //update result we have so far and continue searching toward left (lower indexes)
	           
		       if (searchFirst)
		    	   right = mid -1;
		       else
		           left = mid + 1; //go on searching toward right  ( high indexes)
	       }
	       //left side
	       else if (k < a[mid] ) {  //[1, 2, 2, 3, 4, 6] --> a[mid] = a[2] = 2
	         right = mid - 1;   // [ left, right]
	         
	       } else { // a[mid] < k  (right side) ,  a[0] = 1 < 2
	    	  left = mid + 1;
	      }
	    }
	    return result;
	 }
		
	public static void main (String[] agrs) {				
		
		int[] arr = {5,7,7,8,8,10};	//index 3, 4 has 8, 8
		System.out.println("\nFirst index of 8: " + searchFirstIndexOfK(arr, 8, true)); //wrong output
	
	}
}
