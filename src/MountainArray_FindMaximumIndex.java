
/* 852. Peak Index in a Mountain Array
https://leetcode.com/problems/peak-index-in-a-mountain-array/

Let's call an array A a mountain if the following properties hold:

A.length >= 3
There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]

Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].

Example 1:

Input: [0,1,0]
Output: 1


Example 2:

Input: [0,2,1,0]
Output: 1

Time Complexity: O(logN), where N is the length of A.

Space Complexity: O(1).

*/


public class MountainArray_FindMaximumIndex {	
	
	//binary search - find largest index
	public static int peakIndexInMountainArray(int[] A) {
        int lo = 0, hi = A.length - 1;
        
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (A[mi] < A[mi + 1])
                lo = mi + 1;
            else
                hi = mi;
        }
        return lo;
    }
		
	
   public static void main(String[] args) {
    int[] arr = {0,2,1,0};
   	System.out.println(peakIndexInMountainArray(arr)); //index = 1
   	
   }

}
	
	