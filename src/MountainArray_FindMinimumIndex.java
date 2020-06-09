
/* 1095. Find in Mountain Array
 * https://leetcode.com/problems/find-in-mountain-array/

You may recall that an array A is a mountain array if and only if:

 A.length >= 3
 There exists some i with 0 < i < A.length - 1 such that:
     A[0] < A[1] < ... A[i-1] < A[i]
	 A[i] > A[i+1] > ... > A[A.length - 1]

Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target.  If such an index doesn't exist, return -1.

You can't access the mountain array directly.  You may only access the array using a MountainArray interface:

   MountainArray.get(k) returns the element of the array at index k (0-indexed).
   MountainArray.length() returns the length of the array.

Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.  
Also, any solutions that attempt to circumvent the judge will result in disqualification.

Example 1:

Input: array = [1,2,3,4,5,3,1], target = 3
Output: 2
Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.

Example 2:

Input: array = [0,1,2,4,2,1], target = 3
Output: -1
Explanation: 3 does not exist in the array, so we return -1.


Idea: Mountain - first half has number ascending - second half has number descending

3 steps with binary search:

1. Find peak index of the mountain array
2. Find target on the left side of the peak ( search before the peak index)
3. Find target on the right side of the peak ( search after the peak index)

Note: first search left half, if not found, then search right half

Find peak index can also be done using normal binary search,  pivoting based on mid element.
 */

interface MountainArray {
	int get(int index);		 
	int length();
} 

public class MountainArray_FindMinimumIndex implements MountainArray {	

	public static int findInMountainArray(int target, MountainArray arr) {
		int high = arr.length() - 1;
		
        int peak = findPeakIndex(arr, 0, high); //index of peak 4 is 3
        
        int leftIndex = searchAscending(arr, 0, peak, target);
       
        int rightIndex = searchDescending(arr, peak + 1, high, target);
            
        if(leftIndex != -1) 
        	return leftIndex;
        else
        	return rightIndex;
      //  return res;
        
    }
	
	// find peak index
	private static int findPeakIndex(MountainArray a, int low, int high) {
        while(low < high){
            int mid = low+(high-low)/2;
            
            int midL = a.get(mid-1); //midL
            int midVal = a.get(mid);
            int midR = a.get(mid+1); // midR
            
            if(midL < midVal && midVal > midR)  // midVal is peak
                return mid;  // return index of midVal
            else if(midL < midVal)
                low = mid;
            else
                high = mid;
        }
        return -1;
    }
	
	//binary search - find target in the left of peak
	private static int searchAscending(MountainArray mountainArr,  int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            int m = mountainArr.get(mid);
            if (m == target) {
                return mid;
            } else if (m < target) { //search on right side from  mid + 1(left), right 
                left = mid + 1;
            } else {  // target < m
                right = mid - 1; // search left  from left, mid -1 (right)
            }
        }

        return -1;
    }

	//binary search - find target in the right of peak
    private static int searchDescending(MountainArray mountainArr, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            int m = mountainArr.get(mid);
            if (m == target) {
                return mid;
            } else if (m > target) {
                // search right  from  mid + 1 (left), right 
                left = mid + 1;
            } else {
                // search left from left, mid - 1(right)
                right = mid - 1;
            }
        }

        return -1;
    }
    
   public static void main(String[] args) {
   //[1,2,3,4,5,3,1]
  	MountainArray_FindMinimumIndex o = new MountainArray_FindMinimumIndex();
    o.get(1);
    o.get(2);
    o.get(3);
    o.get(4);
    o.get(5);
    o.get(3);
    o.get(1);
   	System.out.println(findInMountainArray(3, o)); //Leetcode passed - output =2
   	
   }

@Override
public int get(int index) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int length() {
	// TODO Auto-generated method stub
	return 0;
}


}
	
	