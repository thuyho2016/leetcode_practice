package string;

import java.util.ArrayList;
import java.util.List;

/* 212. Word Search II
 * https://leetcode.com/problems/word-search-ii/

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once in a word.
 

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

Solution: Trie and DFS
1. check c == "#
2. de-duplicate
3. reset visited, when finished this spot.

               o   p   e  r
               |   |   |
               a   e   a
               |   |   |
               t   a   t
               |
               h
               |
               oath / true

 */
public class WordSearchII_FindWords_ByTrie{
	
	public class TrieNode {
        public TrieNode[] nodes = new TrieNode[26];
        public String word = "";
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = addWord(words);
        
        List<String> result = new ArrayList<>();  
        
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                boolean[][] visited = new boolean[board.length][board[0].length];
              
                dfs_search(board, i, j, root, result, visited);
            }
        }
        return result;
    }
    
    private void dfs_search(char[][] board, int i, int j, TrieNode curr, List<String> result, boolean[][] visited){
        
    	//check for board out of bound and revisited
    	if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] == true){
    		return;
		}
        
        char c = board[i][j];
        
        //"#" corner case
        if (c == '#' || curr.nodes[c - 'a'] == null){
        	return;
    	}
        
        if (curr.nodes[c - 'a'].word != "") {
            result.add(curr.nodes[c - 'a'].word);
            // deduplicate
            curr.nodes[c - 'a'].word = "";
        }
    
        visited[i][j] = true;
        curr = curr.nodes[c - 'a'];
        
        // 4 directions
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        
        for (int[] dir : dirs){
            //should use a different variable
            int k = i + dir[0];
            int l = j + dir[1];
            
            dfs_search(board, k, l, curr, result, visited);
        }
        
        //have to reset
        visited[i][j] = false;
    }

    private TrieNode addWord(String[] words) {
        TrieNode node = new TrieNode();
        
        for (String word : words){
        	
            TrieNode curr = node; // reference
            
            for (char c : word.toCharArray()) {
            	
                if (curr.nodes[c - 'a'] == null){
                    curr.nodes[c - 'a'] = new TrieNode();
                }
                curr = curr.nodes[c - 'a']; // set index
            }
            curr.word = word;
        }
        return node;
    } 
	
	public static void main(String[] args) {
		char[][] board = {{'o','a','a','n'}, {'e','t','a','e'}, {'i','h','k','r'}, {'i','f','l','v'}}; 
		String[] words = {"oath","pea","eat","rain"}; 
		
		WordSearchII_FindWords_ByTrie obj = new WordSearchII_FindWords_ByTrie();
	    System.out.println( obj.findWords(board, words)); //["eat","oath"]
	    
	}

}
	
	