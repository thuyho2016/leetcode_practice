package string;

import java.util.HashMap;

/*
 * 316. Remove Duplicate Letters  ( hard level)
 * https://leetcode.com/problems/remove-duplicate-letters/

Given a string which contains only lowercase letters, remove duplicate letters so that every letter appears once and only once. 
You must make sure your result is the smallest in lexicographical order among all possible results.

you should remain the relative position of the letters.

Example 1:   NOTE: remove first duplicate

Input: "bcabc"
Output: "abc"    // not bca

Example 2:

Input: "cbacdcbc"
Output: "acdb"

NOTE :
lexicographical order as the order used in a dictionary.

Some examples.  a < aa < aaa < ab < abb < abc < b < bcd < be < ...

Solution:
The basic idea is to find out the smallest result letter by letter (one letter at a time). 
Here is the thinking process for input "cbacdcbc":
                                  Index 01234567
1. find out the last appeared position for each letter;
c - 7  ( 0, 3, 5, 7)
b - 6   (1, 6)
a - 2
d - 4

2. find out the smallest index from the map in step 1 (a - 2);
3. the first letter in the final result must be the smallest letter from index 0 to index 2;
4. repeat step 2 to 3 to find out remaining letters.

	the smallest letter from index 0 to index 2: a
	the smallest letter from index 3 to index 4: c
	the smallest letter from index 4 to index 4: d
	the smallest letter from index 5 to index 6: b
so the result is "acdb"

Notes:

after one letter is determined in step 3, it need to be removed from the "last appeared position map", and the same letter should be ignored in the following steps
in step 3, the beginning index of the search range should be the index of previous determined letter plus one

 */
public class RemoveDuplicateChars {
  
	public static String removeDuplicateLetters(String s) {
        char[] array = s.toCharArray();
        
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        
     // Use HashMap to keep the last index of each char
        for (int i = 0; i < array.length; i++) {
            char c = array[i];
            map.put(c, i);  // {a=2, b=6, c=7, d=4}
        }
        
        char[] result = new char[map.size()];
        int start = 0;
        for (int i = 0; i < result.length; i++) {
            int end = findMinIndex(map);
          
            char min_c = Character.MAX_VALUE;   //char min = 'z' + 1; 
            for (int j = start; j <= end; j++) {
            	
            	 // find the minimun char in s[start:end] ,  this char should be in our map
                if (map.containsKey(array[j]) && array[j] < min_c) {
                    min_c = array[j]; // a, c , d
                    start = j + 1;
                }
            }
            
            result[i] = min_c;   //min = a, c , d, b
            map.remove(min_c);  // erase current char from our map - {b=6, c=7, d=4} , {b=6, d=4}, {b=6}
        }
        
        return String.valueOf(result);
    }
    
    private static int findMinIndex(HashMap<Character, Integer> map) {
        int index = Integer.MAX_VALUE;
        for (Integer i : map.values()) {
        	index = Math.min(index, i);
        }
        return index;
    }
  		  
		  
  public static void main(String[] args) {
 //    String input = "bcabc";
 //    System.out.println(removeDuplicateLetters(input));
  
     
     String input2 = "cbacdcbc";
     System.out.println(removeDuplicateLetters(input2)); //acdb
  }
  
}
