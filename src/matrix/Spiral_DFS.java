package matrix;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 
54. Spiral Matrix 
https://leetcode.com/problems/spiral-matrix/

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]

 */
public class Spiral_DFS {
  
    
    // DFS
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        
        if (matrix.length == 0) return result;
        
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        
        dfs(matrix, 0, 0, result, visited);
		return result;
    }
      
    private static void dfs(int[][]matrix, int i, int j, List<Integer> list, boolean[][] visited) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
          return;
        }
        
        if (visited[i][j]) return;
        visited[i][j] = true;
        
        list.add(matrix[i][j]); //[1,2,3]
        
        if( i == 0  || visited[i-1][j]) {
          dfs( matrix, i, j + 1, list, visited); // move from left to right , visited = [true,true, true]
        }
       
        dfs( matrix, i + 1, j, list, visited); //move to down, [[true, true, true], [false, false, true], [false, true, true]]
        dfs( matrix, i, j - 1, list, visited);  // move to left, [[true, true, true], [false, false, true], [false, true, true]]
        dfs( matrix, i - 1, j, list, visited);  // move up
        
    }
  

    // Function print matrix in spiral form 
	//Time and Space Complexity: O(N)
    public static void spiral(int a[][]) 
    { 
    	if (a.length == 0) return;
    	
        int i, top = 0, left = 0; 
        /*  k - starting row index 
            left - starting column index 
            i - iterator 
        */
  
        int rows = a.length;
        int cols = a[0].length;
        
        while (top < rows && left < cols) { 
        	
        	// left to right
            // Print the first row from the remaining rows 
            for (i = left; i < cols; ++i) { 
                System.out.print(a[top][i] + " "); //1 2 3 , 2nd round: print 5
            } 
            top++; //1
  
            //top to bottom
            // Print the last column from the remaining columns 
            for (i = top; i < rows; ++i) { 
                System.out.print(a[i][cols - 1] + " "); //a[1][2] = 6 a[2][2] = 9
            } 
            cols--; //2
  
            
            //right to left
            // Print the last row from the remaining rows */ 
            if (top < rows) {  // 1 < 3
                for (i = cols - 1; i >= left; --i) { 
                    System.out.print(a[rows - 1][i] + " "); //8 7
                } 
                rows--; //2
            } 
  
            //botton to top
            // Print the first column from the remaining columns */ 
            if (left < cols) { 
                for (i = rows - 1; i >= top; --i) { 
                    System.out.print(a[i][left] + " "); //4
                } 
                left++; //1
            } 
        } 
    }
    // driver program 
    public static void main(String[] args) 
    {  
    	int[][] matrix = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }};


    	//spiralPrint(matrix); 
	
    	List<Integer> out = spiralOrder(matrix);
    	System.out.println("Print Spiral matrix by DFS: " + out);
    	
    	System.out.println("Print Spiral matrix: ");
        int a[][] = { { 1, 2, 3, 4, 5, 6 }, 
                      { 7, 8, 9, 10, 11, 12 }, 
                      { 13, 14, 15, 16, 17, 18 } }; 
        
        spiral(a);  //1 2 3 4 5 6 12 18 17 16 15 14 13 7 8 9 10 11 
    } 
} 
