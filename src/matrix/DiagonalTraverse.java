package matrix;
import java.util.Arrays;

/*
 * 498. Diagonal Traverse
 * https://leetcode.com/problems/diagonal-traverse/

 Given a matrix of M x N elements (M rows, N columns), 
 return all elements of the matrix in diagonal order as shown in the below image.

 
Example:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

Output:  [1,2,4,7,5,3,6,8,9]


Key Observation : If you write down the matrix in terms of the indices, you will notice that whenever the indices add to an even number, we go in the upward direction, else in the downward direction.

We use 2 variables row and col as indices of the matrix. We update them at each iteration of filling in the solution array. 
There are 2 corner cases for each direction which we handle separately.
 */
public class DiagonalTraverse {
	
	//prefer this
	public static int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int row = 0, col = 0, m = matrix.length, n = matrix[0].length; 
        int arr[] = new int[m * n];
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = matrix[row][col];
           
            //when the sum of row & col is even, the direction is always up 
            if ((row + col) % 2 == 0) { // moving up
                
            	if      (col == n - 1) { row++; }  // For last column, go down
               
            	else if (row == 0)     { col++; }  // For first row & non-last columns, go right
               
            	else  		    { row--; col++; }  // For not first row & non-last columns, go up and to the right  (means go up diagonally)
            
            } else {                // moving down - when the sum of row & col is odd, the direction is always down 
                
            	if      (row == m - 1) { col++; }  // For last row, go right
                
            	else if (col == 0)     { row++; } //  For non-last row & first column, go down
               
            	else            { row++; col--; } // For non-last row & non-first column, go down and to the left ( means go down diagonally)
            }   
        }   
        return arr;
    }
	
	public static int[] findDiagonalOrder2(int[][] matrix) {
		if (matrix == null || matrix.length == 0)   return new int[0];
        
		int m = matrix.length, n = matrix[0].length;
        int[] res = new int[m*n];
        
        for (int layer = 0, i = 0; layer < n + n - 1; layer++) {
        	int upperBound = Math.min(layer, m -1);
            int lowerBound = Math.max(0, layer - n + 1);
        
        
	        if (layer % 2 != 0) {
	        	for (int x = lowerBound; x <= upperBound; x++) {
	                res[i++] = matrix[x][layer - x];
	            }
	        } 
	        else {
	        	for (int x = upperBound; x >= lowerBound; x--) {
	                res[i++] = matrix[x][layer - x];
	            }
	        }
        }
        return res;
        
    }
	
	public static void main (String[] agrs) {
		int[][] matrix = {
					      { 1, 2, 3 },
					      { 4, 5, 6 },
					      { 7, 8, 9 }
						 };
		
		int[] output = findDiagonalOrder(matrix);
		System.out.println(Arrays.toString(output));
	}
}
