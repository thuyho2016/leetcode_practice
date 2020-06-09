package string;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
/*
 * 127. Word Ladder (medium level)

Given two words (beginWord and endWord), and a dictionary's word list, 
find all shortest transformation sequence(s) from beginWord to endWord, such that:

- Only one letter can be changed at a time
- Each transformed word must exist in the word list. Note that beginWord is not a transformed word.

Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5
Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.

Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0
Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.

Time Complexity: O(M×N), where M is the length of words and N is the total number of words in the input word list
*/

public class WordLadder_BFS {
	
/*	
  level = 1    hit   dict = [hot, dot, dog, lot, log]

		 ait bit cit ... xit yit zit ，  hat hbt hct ... hot ... hxt hyt hzt ，  hia hib hic ... hix hiy hiz


  level = 2    hot  dict = [dot, dog, lot, log]

		 aot bot cot dot ...  lot ... xot yot zot，hat hbt hct ... hxt hyt hzt，hoa hob hoc ... hox hoy hoz


  level = 3    dot lot  dict = [dog log]

		 aot bot ... yot zot，dat dbt ...dyt dzt，doa dob ... dog .. doy doz，

		 aot bot ... yot zot，lat lbt ... lyt lzt，loa lob ... log... loy loz

 
  level = 4   dog log dict = [] 

		 aog bog cog

  level = 5   cog  dict = []
*/

	/* Breadth First Search
	Steps:
		1. Create a HashSet to store words of wordList
		2. Add beginWord to Queue
		3. Remove a word from queue and  check a new word by change each character from a ...to z
		4. Set first char from a , like [a, i, t] .
		5. if tmpStr is the same endWords, increase count by 1
	*/
    public static int findLadders(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> words = new HashSet<>();
        for (String word : wordList) {  //add words to HashSet
        	words.add(word);
        }
        //corner case
        if (!words.contains(endWord)) return 0;
     
        Queue<String> q = new ArrayDeque<>(); // or  Queue<String> q = new LinkedList<>();
        q.offer(beginWord); //insert beginWord to the queue
        int level = 0; // int level = 1
        
        while (!q.isEmpty()) {
        	level++;
        	int levelItems = q.size(); // que size is changing, Take it out so we can know how many items are in the current level
            for (int k = 0; k < levelItems; k++) {
            	String word = q.poll(); // take word out of queue
                char[] chars = word.toCharArray();  //hit
                
                // check a new work by change each character from a ...to z
                for (int i = 0; i < chars.length; i++) { //i = 0 -> 2
                    char ch = chars[i]; // h , i , t
                    
                    for (char c = 'a'; c <= 'z'; c++) {  // Step 3.
                        if (ch == c) continue;
                        chars[i] = c;  // set first letter of chars to 'a','b',...'z' like [a,i,t] , bit , cit,.. Step 4
                        
                        String tmpStr = String.valueOf(chars); // ait , bit,  convert chars array to String
                        
                        if (tmpStr.equals(endWord)) 
                        	return level + 1; // ait.equals(cog)  Step 5.
                        // h != b, h != b ,...&&  words.contains(ait),...
                        else if (ch != c &&  words.contains(tmpStr)) {   // tmpStr = hot
	                        q.offer(tmpStr); // insert hot to the queue
	                        words.remove(tmpStr);
                        }
                    }
                    chars[i] = ch;
                }
            }     
        } //while
        return 0;
    }    
    
    public static void main (String args[]) {
	    
	    String beginWord = "hit";
	    String endWord = "cog";
	    List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog"); //6 words
	    				  	    
	    System.out.println("Output: " + findLadders(beginWord, endWord, wordList)); //5
	    
/*	    List<String> wordList2 = Arrays.asList("hot","dot","dog","lot","log"); //5 words
	    int res2 = findLadders(beginWord, endWord, wordList2); //0
	    System.out.println("Expected 0:  " + res2);
*/	    	    
    }
}
	