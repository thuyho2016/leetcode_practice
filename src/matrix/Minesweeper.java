package matrix;

import java.util.Arrays;

/*
 * 529. Minesweeper
 * https://leetcode.com/problems/minesweeper/

Let's play the minesweeper game (Wikipedia, online game)!

You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 
'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, 
digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'),
return the board after revealing this position according to the following rules:

If a mine ('M') is revealed, then the game is over - change it to 'X'.

If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') 
and all of its adjacent unrevealed squares should be revealed recursively.

If an empty square ('E') with at least one adjacent mine is revealed, 
then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.
 

Example 1:

Input: 

[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['*E', 'E', 'E', 'E', 'E']]

Click : [3,0]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:

Example 2:
Input: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Click : [1,2]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

 */

public class Minesweeper {
	private static int[][] dirs = {{0,1},{0,-1}, {1,0}, {-1,0}, {-1,-1}, {1,1}, {1,-1}, {-1,1}};
	
	public static char[][] updateBoard(char[][] board, int[] click) {
        // 8 directions
		
		int row = click[0], col = click[1];
		int m = board.length, n = board[0].length;
		
		if (board[row][col] =='M' || board[row][col] == 'X') {
				board[row][col] = 'X';
			return board;
		}
	
		int num =0;
		//calculate 8 dirs to see where is M
		for (int[] dir: dirs) {
			int newRow = dir[0] + row;
			int newCol = dir[1] + col;
			//dir  should be valid
			if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && board[newRow][newCol] =='M' ) {
				num++;
			}
		}
		
		if (num > 0) {
			board[row][col] = (char)(num + '0'); //convert int to char
			return board;
		}
		
		// if no M
		board[row][col] = 'B';
		for (int[] dir: dirs) {
			int newRow = dir[0] + row;
			int newCol = dir[1] + col;
			//dir  should be valid
			if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && board[newRow][newCol] =='E' ) {
				updateBoard(board, new int[] {newRow, newCol});
			}
		}
		
		return board;
				
				
	}
    
    public static void main (String[] agrs) {
    	char[][] board = { 
    			{'E', 'E', 'E', 'E', 'E'},
    			{'E', 'E', 'M', 'E', 'E'},
    			{'E', 'E', 'E', 'E', 'E'},
				{'E', 'E', 'E', 'E', 'E'} };
    	
    	int[] click = {3,0};
    	
    	char[][] output = updateBoard(board, click);
		
		for (char[] arr: output) {
			System.out.println(Arrays.toString(arr));
		}		
	
    }
}