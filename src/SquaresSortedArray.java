import java.util.Arrays;

/* 977. Squares of a Sorted Array
 * https://leetcode.com/problems/squares-of-a-sorted-array/

Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.

Example 1:

Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]

Example 2:

Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]

 */

public class SquaresSortedArray {
	//Time Complexity: O(n)
	// 2 pointers
	public static int[] sortedSquares(int[] A) {
		int[] result = new int[A.length]; // [0, 0, 0, 0, 0] same size with A
		
		int left = 0;
		int right = A.length - 1;
		
		//start from last index until 0 index
		for (int i = A.length - 1; i >= 0 ; i--) {
			if (Math.abs(A[left])  >  A[right] ) {
				result[i] = A[left] * A[left]; // result[3] = 4 * 4 -> result = [0, 0, 0, 16, 100] , result[1] =[0, 1, 9, 16, 100]
				left++;
			} else { //4 < 10
				result[i] = A[right] * A[right]; // result[4] = 10 * 10 --> result = [0, 0, 0, 0, 100], result[2] = 3 * 3 -> res =[0, 0, 9, 16, 100]
				right--;
			}
		}
		return result;
    }    
   
	
	public static void main(String[] args) {
	    int[] a = {-4,-1,0,3,10};	
		System.out.println(Arrays.toString(sortedSquares(a)));
	}

}
	
	