package matrix;
/*
 * 48. Rotate Image
 * https://leetcode.com/problems/rotate-image/
 * 
 * You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).


 * Given input matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place ( rotate to right) such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
Example 2:

Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]

Steps:

One by one rotate all rings of elements, starting from the outermost. To rotate a ring, we need to do following.
    1) Move elements of top row.
    2) Move elements of last column.
    3) Move elements of bottom row.
    4) Move elements of first column.
Repeat above steps for inner ring while there is an inner ring.
 */
public class RotateImage_Matrix {
	
    public static void rotate(int[][] matrix) {
        if (matrix == null || ( matrix.length ==0 && matrix[0].length == 0 )) {
          return;
        }
        
        int topRow = 0;
        int topCol = 0;
        int bottomRow = matrix.length - 1;
        int bottomCol = matrix[0].length - 1;
      
        while ( topRow < bottomRow) {
          doRotation (matrix, topRow++, topCol++, bottomRow--, bottomCol--); 
        }
    }

 /* 
            tc+i
  (tr,tc)   ---->    (tr,bc)	  
      |	    1 2 3      |
 br-i |     4 5 6      | tr+i
      |	    7 8 9      |
  (br,tc)   <----    (br,bc)
            bc-i
 
    */
    
    public static void doRotation(int[][] matrix,int tr,int tc,int br,int bc){ 
       int diff = bc - tc;
      
       for(int i=0;i<diff;i++){
    	   
           int tmp = matrix[tr][tc+i];
           System.out.println("matrix[tr][tc+i] = " + matrix[tr][tc + i]); // 1 , 2
           
           System.out.println("matrix[br-i][tc] = " + matrix[br-i][tc]); // 7, 4
           matrix[tr][tc+i] = matrix[br-i][tc];
          
           System.out.println("matrix[br][bc-i] " + matrix[br][bc - i]); // 9, 8
           matrix[br-i][tc] = matrix[br][bc-i];
           
           System.out.println("matrix[tr+i][bc] " +  matrix[tr + i][bc]); // 3, 6
           matrix[br][bc-i] = matrix[tr+i][bc];
           
           matrix[tr+i][bc] = tmp;
           
          
       } 
    }
  
  public static void main(String args[])
  {
      int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
      rotate(matrix);
      
      //[[7, 4, 1], [8, 5, 2], [9, 6, 3]]
      
      for(int i=0;i< matrix.length ;i++){
    	  for(int j = 0;j < matrix[0].length; j++){
    		  System.out.print(matrix[i][j] + " ");
    	  }
    	  System.out.println("");
      }
  }
 
}
