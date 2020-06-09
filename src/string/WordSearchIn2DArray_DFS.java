package string;

/* 79. Word Search
 * https://leetcode.com/problems/word-search/
 * Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.

Given word = "SEE", return true.
Given word = "ABCB", return false.

Time complexity I believe is O(M * N * 4L) because it iterates through the board of size (M * N) 
and it goes upto through the length of the string in the recursion stack in all 4 possible directions.

space complexity is O(1)

 */
public class WordSearchIn2DArray_DFS{

	public static boolean exist(char[][] board, String word) {
		
		if(board == null || board.length == 0)
			return false;
		
		int m = board.length;    //n = 3  elements
		int n = board[0].length; //n = 4 elements of array
		

		for(int i=0; i < m; i++){
			for(int j=0; j < n; j++){
				System.out.println("Match: " + board[i][j]);
				if(board[i][j] == word.charAt(0) && dfs(board, word, i, j, 0))  //first letter should always match . Make recursive call
					return true;
			}
			
			System.out.println( " ");
		}
		return false;
	}
	
	//count - count how many letter I found
	private static boolean dfs(char[][] board, String word,  int i, int j, int count) {
		// if i found the word , return true
		if(count == word.length()) {
			return true;
		}
		
		//check for array out of bound and check if this charater does not match
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(count))	{
			return false;
		}
		
	    if (board[i][j] != word.charAt(count)) {
	    	return false;
	    }
		
	    char temp = board[i][j]; //replace the current character since it should not be visited again for current word
	    System.out.println("Replace: " + temp);
	    
	    board[i][j] = ' ';    //board[i][j] = '0';
	    
	   // recursive call to go 4 directions up, down, left, right 
	    boolean found = dfs(board, word, i + 1, j, count + 1) 
	    		|| dfs(board, word, i - 1, j, count + 1) 
	    		|| dfs(board, word, i, j + 1, count + 1) 
	    		|| dfs(board, word, i , j - 1, count + 1);
	    
	   
	  //restore the replaced character
	    board[i][j] = temp;
	    System.out.println("Restore: " + board[i][j]);
	    
	    return found;

	}
	
	public static void main(String[] args) {
		char[][] board = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}}; 
		String word = "ABCCE";  //SEE
	    System.out.println("Exist?  " + exist(board, word)); //true
	    
	    String word2 = "SEE";
	    System.out.println("Exist? " + exist(board, word2));  //false
	}

}
	
	