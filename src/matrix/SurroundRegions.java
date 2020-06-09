package matrix;

/*  
 * 130. Surrounded Regions
 * https://leetcode.com/problems/surrounded-regions/
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X

After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. 
Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. 
Two cells are connected if they are adjacent cells connected horizontally or vertically.


 * Steps:
 * 
 * 1. Check the board
 *    if board[i][j] =='O'
 *       mark all the 'O' connected to visited - DFS
 *       
 * 2. Iterate from row 1 to row m - 1 , from col 1 to col n -1
 *        if board[i][j] =='O' and !visited[i][j]
 *             flip  - DFS
 *             
 *      Example 2. flip : board[1][1] =='O', flip, keep finding unvisited 'O'  
 *      
 *   If flip is true, when we meet 'O', change it to 'X'
 *   Otherwise, just mark it visited
 *                
 *                
 */

public class SurroundRegions {
	
	public static void solve(char[][] board) {
		 if (board == null || board.length == 0) return;
	        int rows = board.length;
	        int cols = board[0].length;
	        
	        boolean[][] visited = new boolean[rows][cols];
	        
	        for (int j = 0; j < cols; j++) { 
	            if (board[0][j] == 'O') {
	                dfs(board, 0, j, visited, false);
	            }
	            
	            if (board[rows - 1][j] == 'O') {
	                dfs(board, rows - 1, j, visited, false);
	            }
	        }
	        
	        for (int i = 0; i < rows; i++) {
	            if (board[i][0] == 'O') {
	                dfs(board, i, 0, visited, false);
	            }
	            
	            if (board[i][cols - 1] == 'O') {
	                dfs(board, i, cols - 1, visited, false);
	            }
	        }
	        
	        for (int i = 1; i < rows - 1; i++) {
	            for (int j = 1; j < cols - 1; j++) {
	                if (board[i][j] == 'O' && !visited[i][j]) {
	                    dfs(board, i, j, visited, true);
	                }
	            }
	        }
	    }
	    
	    private static void dfs(char[][] board, int row, int col, boolean[][] visited, boolean flip) {
	        
	    	if (row < 0 || col < 0 || row > board.length - 1 ||
	           col > board[0].length - 1) return;
	        
	        if (visited[row][col]) return;
	        
	        if (board[row][col] == 'X') return;
	        
	        if (flip) {
	            board[row][col] = 'X';
	        }
	        
	        visited[row][col] = true;
	        dfs(board, row + 1, col, visited, flip);
	        dfs(board, row - 1, col, visited, flip);
	        dfs(board, row, col + 1, visited, flip);
	        dfs(board, row, col - 1, visited, flip);
	    }
	
	  
	  public static void main(String[] args) {
	    char[][] board = {{'X','X','X','X'}, {'X','O','O','X'}, {'X','X','O','X'}, {'X','O','X','X'}};
	    		
	    solve(board);
	    for (int i = 0; i < board.length; i++) {
	    	 for (int j = 0; j < board[0].length; j++) {
	    		 System.out.print(board[i][j]); 
	    	 }
	    	 System.out.println();
	    }
	    
	}
}
	
	