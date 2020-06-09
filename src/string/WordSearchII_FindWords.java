package string;

import java.util.ArrayList;
import java.util.List;

/* 212. Word Search II
 * https://leetcode.com/problems/word-search-ii/

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

Example:

Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]


 */
public class WordSearchII_FindWords{
	//for each word do dfs search
	//if word is found break the loop and go for next word
	
	public static List<String> findWords(char[][] board, String[] words) {
		
		if(board == null || board.length == 0)
			return null;
		
		ArrayList<String> res = new ArrayList<>();
		boolean found = true;
		
		int m = board.length;    //n = 3  elements
		int n = board[0].length; //n = 4 elements of array
		
		/* for each word */
        for ( String word : words) {
			for(int i=0; i < m; i++) {
				for(int j=0; j < n; j++) {
					//System.out.print(board[i][j] + " ");
					if(board[i][j] == word.charAt(0) && dfs(board, word, i, j, 0))  //first letter should always match . Make recursive call
						res.add(word);
				}
				
			}
        }
		return res;
	}
	
	//count - count how many letter I found
	private static boolean dfs(char[][] board, String word,  int i, int j, int count) {
		// if i found the word , return true
		if(count == word.length()) {
			return true;
		}
		
		// check for array out of bound 
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)	{
			return false;
		}
		// if character does not match, return false
	    if (board[i][j] != word.charAt(count)) {
	    	return false;
	    }
		
	    char temp = board[i][j]; //replace the cuurrent charater since it should not be visited again for current word
	    board[i][j] = ' ';    //board[i][j] = '0';
	    
	   // recursive call in 4 directions up, down, left, right 
	     boolean found = dfs(board, word, i + 1, j, count + 1) 
	    		|| dfs(board, word, i - 1, j, count + 1) 
	    		|| dfs(board, word, i, j + 1, count + 1) 
	    		|| dfs(board, word, i , j - 1, count + 1);
	    
	   
	    //restore the replaced character
	    board[i][j] = temp;
	    System.out.println("Restored: " + board[i][j]);
	    return found;

	}
	
	public static void main(String[] args) {
		char[][] board = {{'o','a','a','n'}, {'e','t','a','e'}, {'i','h','k','r'}, {'i','f','l','v'}}; 
		String[] words = {"oath","pea","eat","rain"}; 
		
	    System.out.println( findWords(board, words)); //["eat","oath"]
	    
	}

}
	
	