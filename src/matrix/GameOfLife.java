package matrix;
/*
 * 289. Game of Life
 
 Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
 Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules:

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Write a function to compute the next state (after one update) of the board given its current state. 
The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

Example:

Input: 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]

Output: 
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
 
Algorithm

Iterate the cells of the Board one by one.

The rules are computed and applied on the original board. The updated values signify both previous and updated value.

The updated rules can be seen as this:

Rule 1: Any live cell with fewer than two live neighbors dies, as if caused by under-population. Hence, change the value of cell to -1. 
       This means the cell was live before but now dead.

Rule 2: Any live cell with two or three live neighbors lives on to the next generation. Hence, no change in the value.

Rule 3: Any live cell with more than three live neighbors dies, as if by over-population. Hence, change the value of cell to -1. 
        This means the cell was live before but now dead. Note that we don't need to differentiate between the rule 1 and 3. 
        The start and end values are the same. Hence, we use the same dummy value.

Rule 4: Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction. Hence, change the value of cell to 2. 
        This means the cell was dead before, but now live.

Apply the new rules to the board.

Since the new values give an indication of the old values of the cell, we accomplish the same results as approach 1 but without saving a copy.

To get the Board in terms of binary values i.e. live(1) and dead(0), we iterate the board again and change the value of a cell to a 1 
if its value currently is greater than 0  and change the value to a 0 if its current value is lesser than or equal to 0.  
 */

import java.util.Arrays;

public class GameOfLife {

   public static void main(String[] args) {
  
      int[][] board = { {0,1,0}, {0,0,1}, {1,1,1}, {0,0,0} };
      gameOfLife(board);
      
      System.out.println(Arrays.deepToString(board));
  /*    for (int i = 0; i < board.length; i++) {
    	  System.out.println();
          for (int j = 0; j < board[0].length ;j++) 
        	  System.out.print(board[i][j] + " ");
      } */
   }
   
   public static void gameOfLife(int[][] board) {
	// Neighbors array to find 8 neighboring cells for a given cell
       int[] neighbors = {0, 1, -1};

       int rows = board.length;
       int cols = board[0].length;

       // Iterate through board cell by cell.
       for (int row = 0; row < rows; row++) {
           for (int col = 0; col < cols; col++) {

               // For each cell, count the number of live neighbors.
               int liveNeighbors = 0;

               for (int i = 0; i < 3; i++) {
                   for (int j = 0; j < 3; j++) {

                       if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                           int r = (row + neighbors[i]);
                           int c = (col + neighbors[j]);

                           // Check the validity of the neighboring cell.
                           // and whether it was originally a live cell.
                           if ((r < rows && r >= 0) && (c < cols && c >= 0) && (Math.abs(board[r][c]) == 1)) {
                               liveNeighbors += 1;
                           }
                       }
                   }
               }

               // Rule 1 or Rule 3
               if ((board[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                   // -1 signifies the cell is now dead but originally was live.
                   board[row][col] = -1;
               }
               // Rule 4
               if (board[row][col] == 0 && liveNeighbors == 3) {
                   // 2 signifies the cell is now live but was originally dead.
                   board[row][col] = 2;
               }
           }
       }

       // Get the final representation for the newly updated board.
       for (int row = 0; row < rows; row++) {
           for (int col = 0; col < cols; col++) {
               if (board[row][col] > 0) {
                   board[row][col] = 1;
               } else {
                   board[row][col] = 0;
               }
           }
       }
   }
}

