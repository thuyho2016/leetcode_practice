import java.util.Arrays;
import java.util.PriorityQueue;
/*
 * 36. Valid Sudoku
 Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

A partially filled sudoku which is valid.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

Example 1:

Input:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: true

0 = number is not in the row, 1 = number is in the row
000000000 means no numbers in the row
000000001 means a 1 in the row
100000000 means a 9 in the row
100000001 means a 1, 9 in the row

Time complexity:O(n^2)
*/

public class Sudoku {
	/*
	  1. Traverse through matrix, check for repetitions on a row level, column level, matrix level
	  2. row - i, j
	  3. column - j, i
	  0 1 2 3 4 ...9
	0
	1
	2
	3
	4
	5
	6
	
	 2 loops
	 i from 0 to 9
	 j from 0 to 9
	 
	 row index - 3 * ( i / 3)
	 col index - 3 * ( i % 3)
	 
	 row index + j/3 , colIndex + j % 3
	 
	 */
	public static boolean isValidSudoku(char[][] board) {
		short[]rows = new short[9];
		short[]cols = new short[9];
		short[] squares = new short[9];
		
		for (int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				
				if (board[row][col] == '.') continue;
				//  char '1' - '1' = 0, shift 000000001
				//  char '2' - '1' = 1, shift 000000010
				
				short value = (short) (1 << (board[row][col] - '1'));
				
				if ((value & rows[row])  > 0 ) return false;  // if value already exist,  return false
				if ((value & cols[col])  > 0 ) return false;
				if ((value & squares[ 3 * (row / 3) + col/3]) > 0) return false;
					
		//if i have row = 0, col = 0: 3 * 0/3 + 0/3 = square[0]
				// row = 2, col = 0: 3 * 2/3 + 0/3 = square[0]
			    // row = 0, col = 9: 3 * 0/3 + 8/3 = square[2]
			    // row = 4, col = 0: 3 * 4/3 + 0/3 = square[3]
				
				// if i see duplicate vaue, i need to update row, col, square for future iteration using OR |
				rows[row] |= value; 
				cols[col] |= value;
				squares[3 * (row/3) + col/3 ] |= value;
				
			}
		}
		
		return true;
	}
	
	public static boolean isValidSudoku2(char[][] board) {
	    int [] rows = new int [9];
	    int [] cols = new int [9];
	    int [] squares = new int [9];
	    int idx = 0;
	    
	    for (int i = 0; i < 9; i++) {
	        for (int j = 0; j < 9; j++) {
	        	
	            if (board[i][j] != '.') {
	            	
	                idx = 1 << (board[i][j] - '0') ; //different with 
	                
	                if ((rows[i] & idx) > 0 || (cols[j] & idx) > 0 || (squares[3 * (i / 3)  + j / 3] & idx) > 0)
	                	return false;
	                
	                rows[i] |= idx;
	                cols[j] |= idx;
	                squares[3 * (i / 3) + j / 3] |= idx;
	            }
	        }
	    }
	    return true;
	}
	
	
	public static void main (String[] agrs) {
		char [][] board = {
		                      {'5','3','.','.','7','.','.','.','.'},
		                      {'6','.','.','1','9','5','.','.','.'},
		                      {'.','9','8','.','.','.','.','6','.'},
		                      {'8','.','.','.','6','.','.','.','3'},
		                      {'4','.','.','8','.','3','.','.','1'},
		                      {'7','.','.','.','2','.','.','.','6'},
		                      {'.','6','.','.','.','.','2','8','.'},
		                      {'.','.','.','4','1','9','.','.','5'},
		                      {'.','.','.','.','8','.','.','7','9'}
		                    };
		
		System.out.println("Result: " +isValidSudoku(board));
		
		System.out.println("Result: " +isValidSudoku2(board));
	
	}
}