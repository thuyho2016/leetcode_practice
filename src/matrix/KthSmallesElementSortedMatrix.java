package matrix;

/* 378. Kth Smallest Element in a Sorted Matrix
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.

 */

public class KthSmallesElementSortedMatrix {

	/** use binary search
	    kth smallest element is between matrix[0][0] and matrix[rows - 1][cols - 1] 
  		Let low = matrix[0][0], and high = matrix[rows - 1][cols - 1], 
  		using binary search to calculate mid value, 
  		and find the count of elements which are less than and equal to mid.

        mid is just a value calculated by using low and high 
        when count < k, it means that the mid is small, let low = mid + 1 to find the true element in matrix one by one. 
        when count > k, it means that the mid is big enough, let high = mid 
 
    For example
	[[ 1, 5, 9],
	[10, 11, 13],
	[12, 13, 15]],
	
	low = matrix[0][0] = 1, high = matrix[2][2] = 15: -> mid = 8, count is element number of set {1, 5} = 2, count < k, let low = mid + 1 = 9;
	low = 9, high = 15:  -> mid = 12, count is element number of set {1, 5, 9, 10, 11, 12} = 6, count < k, let low = mid + 1 = 13;
	low = 13, high = 15: -> mid = 14, count is element number of set {1, 5, 9, 10, 11, 12, 13, 13} = 8, count = k, but the element we need is 13, not the mid(14), 
																							so let high = mid = 14 to narrow the search gap.
	low = 13, high = 14: -> mid = 13, count = 8, count = k, let high = mid = 13,
	low = 13, high = 13:  low and high are meet, now return low.
	
	Note:  using (low < high) in while loop rather than using (low <= high) to avoid stay in the loop. 
	It takes log(m * n) times to find mid, and using (m + n) times to get count in each loop, so time complexity is O((m + n) log (m * n)).
	The matrix is n x n, So the time complexity is O(n log (n^2)).
	
	This problem is similar to problem 668. Kth Smallest Number in Multiplication Table.
	Here is the link: https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/
	 */
	
	public static int kthSmallest(int[][] matrix, int k) {
		int rows = matrix.length, cols = matrix[0].length;
		
		int low = matrix[0][0]; //1
		int high = matrix[rows - 1][cols - 1]; //15
		
		while (low < high) {
			int mid = low + (high - low) /2;  //8 , 12, 14, 13
			
			int count = 0;
			int j = cols - 1;  //j = 2
			
			for (int i = 0; i < rows; i++) {
				
				while ( j >= 0 && matrix[i][j] > mid) {
					//System.out.println("matrix[" + i + "][" + j +"]= "+ matrix[i][j]);
					j --;  //j = 1
				}
				count += j + 1;  //2
			}
			
			if (count < k) {   // count = 2 < 8 , count = 6 < 8
				low = mid + 1;  //low = 8 + 1 = 9 , low = 12 + 1 = 13
			}
			else {
				high = mid; // 
			}
		}
		return low;
    }

	public static void main(String[] args) {
		  
		  int[][] matrix = {{1,5,9},
				            {10,11,13}, 
				            {12,13,15}};
		  
		  System.out.println(kthSmallest(matrix, 8));
	}
}
