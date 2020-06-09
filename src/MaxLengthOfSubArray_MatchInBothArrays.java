
/*
 * 718. Maximum Length of Repeated Subarray
https://leetcode.com/problems/maximum-length-of-repeated-subarray/

Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]

Output: 3

Explanation: 
The repeated subarray with maximum length is [3, 2, 1].
 
 */
public class MaxLengthOfSubArray_MatchInBothArrays {
 
/*  Dynamic Programming 
 * 
 * tmp[i][j] array represents the current longest subarray ENDING WITH ith element from A, jth element from B, that is, A[i - 1], B[j - 1] 
 * that it is NOT the case that it represents the length of longest subarray for first i elements from A and j elements from B, 

tmp[m][n] only represents length of longest subarray ending with last element from A and B  

Time Complexity: O(M*N), where M, N are the lengths of A, B.
Space Complexity: O(M*N), the space used by dp
*/

	public static int findLength(int[] A, int[] B) {
        
		int m = A.length, n = B.length;
		if(m == 0 ||  n == 0) return 0;
		
		int res = 0;
		
        int[][] tmp = new int[m + 1][n + 1];
        
        for(int i = 1; i <= m; i ++) {
            for(int j = 1; j <= n; j++) {
            	
                if(A[i - 1] == B[j - 1]) { // compare element from array A and array B
                	
                    tmp[i][j] = tmp[i - 1][j - 1] + 1; //[[0, 0, 0, 0, 0, 0], [0, 0, 0, 1, 0, 0],...
                    
                    res = Math.max(res, tmp[i][j]); // use Math.max to get max 
                    //[[0, 0, 0, 0, 0, 0], [0, 0, 0, 1, 0, 0], [0, 0, 1, 0, 0, 0], [0, 1, 0, 0, 0, 0], [0, 0, 2, 0, 0, 0], [0, 0, 0, 3, 0, 0]]
                }
            }
        }
        return res;
    } 
	
	
    public static void main(String[] args) {
  
    	int[] A = {1,2,3,2,1};
    	int[] B = {3,2,1,4,7};
		
		System.out.println("Max length " +  findLength(A, B)); //3
      
    }
}