package string;

import java.util.ArrayList;
import java.util.List;

/*
 * 68. Text Justification ( hard level)
https://leetcode.com/problems/text-justification/

Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. 
Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. 
If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
Example 1:

Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]

Example 2:

Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]

Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified because it contains only one word.
Example 3:

Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]

Runtime: 0 ms, faster than 100.00% of Java online submissions for Text Justification.
Memory Usage: 34.9 MB, less than 97.22% of Java online submissions for Text Justification.

 */

public class TextJustification {
	
	
	public static List<String> fullJustify(String[] words, int maxLen) {
		List<String> ans = new ArrayList<>();
        List<String> tempList = new ArrayList<>();  //to store the words that can be put in one same line
        int tempLen = 0;     // track the length of current line: wordsLen + spaces between words
        int index = 0;      //index of word in words array
        int wordsLen = 0;   // sum of all words that can be put in same line
        int spaces = 0;     // sapces that need to be filled
        
        while(index < words.length) {
            if((tempLen + words[index].length()) <= maxLen) {   //check if cur word can be put in cur line
                tempLen += words[index].length() + 1;
                tempList.add(words[index++]); //tempList = [This, is, an]; tempList = [example, of, text]; tempList = [justification.]
            } 
            else {             //tempList = [This, is, an] , tempLen = 11 , tempList.size() = 3
            	
                StringBuilder sb = new StringBuilder();
                wordsLen = tempLen - tempList.size();  // 11 -3 = 8; 16 - 3 = 13;
                spaces = maxLen - wordsLen;  //16 - 8 = 8; 16 - 13 = 3;
                
                if(tempList.size() == 1) {          // there's only one word at cur line, then just fill the spaces
                    sb.append(tempList.get(0));
                    for(int i = 0; i < spaces; i++) {
                        sb.append(" ");
                    }
                    ans.add(sb.toString());
                } 
                else if(tempList.size() > 1){      // there's many words in cur line, do the evenly spaces
                    int distance = spaces / (tempList.size() - 1); // 8 / tempList.size() = 3 - 1 = 8 / 2 = 4; 3 / 2= 1
                    int reminder = spaces % (tempList.size() - 1);  //8 % 2 = 0 ; 3%2 = 1
                    
                    for(int i = 0; i < tempList.size() - 1; i++) {
                        sb.append(tempList.get(i));   // sb = This ; sb = example
                       
                        if(reminder > 0){
                            sb.append(" ");
                            reminder--;
                        }
                        for(int j = 0; j < distance; j++)  // j < distance = 4, so append " " 4 times
                            sb.append(" ");                //sb = This    is  ; sb = example  of
                    }
                    
                    sb.append(tempList.get(tempList.size() - 1)); //sb = This    is    an ; sb = example  of text
                    ans.add(sb.toString());                       // ans = [This    is    an]; ans = [example  of text]
                }
                tempList.clear();  //[]
                tempLen = 0;
            }
        }
        
        // dealing with last line
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tempList.size() - 1; i++) {  //[justification.] , tempLen = 15
            sb.append(tempList.get(i));
            sb.append(" ");
        }
        sb.append(tempList.get(tempList.size() - 1)); //sb = justification.
        
        for(int i = 0; i < maxLen - (tempLen - 1); i++) // i < 16 - (15 - 1) = 2
             sb.append(" ");   					//sb = "justification.  "
        
        ans.add(sb.toString()); //ans = [This    is    an, example  of text, justification.  ]
        return ans;
    }
	
	public static void main(String[] args) {
		String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
		
		System.out.println(fullJustify(words, 16));
	}
}
