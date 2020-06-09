import java.util.ArrayList;
import java.util.List;

/* 658. Find K Closest Elements
 * https://leetcode.com/problems/find-k-closest-elements/

 Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. 
 If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]


Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]


Idea: first use binary search to find the index such that |arr.get(index)-x| is the smallest. 
Then use two pointers, one points to the left part of index, and the other one points to the right part of index. 
Compare the absolute distance of (leftElement, x) and (rightElement, x) to decide if we need to include the left or the right element.
 
Time complexity:  O(logN+K) , where O(logn) is for the time of binary search and O(k) is for shrinking the index range to k elements.
Space complexity : O(k).
 */


public class FindKClosestNumberToTarget_InSortedArray_BS {	
	
	// FAST solution - O(Log N+K)
	public List<Integer> findClosestElements(int[] arr, int k, int target) {
		List<Integer> res = new ArrayList<>();
		if (arr == null || arr.length == 0) {
			return res;
		}
		
		int n = arr.length;
		int left = 0;
		int right = n - k - 1;
		
		while (left <= right) {
          int mid = (right - left)/2 + left;
          
          if (Math.abs(target - arr[mid]) > Math.abs(arr[mid + k] - target)) {
              left = mid + 1;
          } else {
              right = mid - 1;
          }
		}
		
         // add to result
		for (int i = left; i < left + k; i++) {
	          res.add(arr[i]);
		}
	      return res;
	  }




    // Baseed on FindClosestNumberToTarget_InSortedArray_BS.java
	public static List<Integer> findKClosestNumber(int[] arr, int target, int k) { // k is size of closest numbers
		List<Integer> res = new ArrayList<>();
		
		if (arr.length <= k) return res;
		
		int closestIndex = getClosestElementIndex(arr, target);
		
		// two pointers   // Traverse left and right to get K closest numbers
		int left = closestIndex - 1;
		int right = closestIndex + 1;
				
		
		for(int count = 1; count < k; count++) {
			if ( left < 0 )
				right++; //include right index
			else if ( right > arr.length - 1)
				left--; // include left index
			else {
				
				if (target - arr[left] <= arr[right] - target) {
					left--; //include left
				} else {
					right++; // include right
				}
			}
		}
		
		//return res.subList(left + 1, right); 
		for (int i = left + 1; i < right; i++) 
			res.add(arr[i]);
		
		return res;
		
    }
	 
	private static int getClosestElementIndex(int[] arr, int target)
    {
        int left = 0,right = arr.length-1;
        
        while(left + 1 < right ) // // left + 1 to avoid ArrayIndexOutOfBoundsException:
        {
            int mid = left + (right - left)/2; //mid index = 3
            
            if(target < arr[mid])  // search on the left side
            	right = mid - 1;
            else 
            	left = mid + 1;
        }
        
      //  int closestIdx = Math.abs(arr[left] - target) <= Math.abs(arr[right] - target) ? left : right;
      //  return closestIdx;
        if (Math.abs(target - arr[left]) <= Math.abs(arr[right] - target))
        	return left;
        else
        	return right;
      
    }
	
	
	
   public static void main(String[] args) {
  
    int[] arr = {10,20, 30,40, 50, 60, 70};
   	System.out.println(getClosestElementIndex(arr, 45)); //index = 4
   	System.out.println(findKClosestNumber(arr,45, 3));  //[30, 40, 50]
   	
   	int[] arr2 = {10,20, 30,40, 50, 60, 70};
   	System.out.println(getClosestElementIndex(arr2, 50)); //index = 4
   	System.out.println(findKClosestNumber(arr2,50,3));  //[40,50,60]
   	
   	int[] arr3 = {10,20, 30,40, 50, 60, 70};
   	System.out.println(getClosestElementIndex(arr3, 75)); //index = 6
   	System.out.println(findKClosestNumber(arr3,75,3));  //[50,60,70]
   	
 	int[] arr4 = {1,2,3,4,5};
	System.out.println(findKClosestNumber(arr4, 3, 4)); //[1,2,3,4]
	
	int[] arr5 = {1,2,3,4,5};
 	System.out.println(getClosestElementIndex(arr5, -1)); //index = 0
	System.out.println(findKClosestNumber(arr5, -1, 4)); //[1,2,3,4]

   }


}
	
	