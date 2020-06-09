package string;

import java.util.HashMap;

/*
 * 290. Word Pattern
 * https://leetcode.com/problems/word-pattern/
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Example 1:

Input: pattern = "abba", str = "dog cat cat dog"
Output: true
Example 2:

Input:pattern = "abba", str = "dog cat cat fish"
Output: false


Solutions:  
1. convert string to array and convert pattern to array
2. create a hash map to put character 'a' as key and dog as value 
  {a = dog}
  {b = cat}
3. check if map has key 'a' or 'b', if it doesn't, add a pair of key and value
   {a=dog, b=cat}

3. when key matches, need to check values. Return false if value is not matching 

NOTE: length of string and pattern should be the same ( # of character from pattern must equal # of words in string)
 */

public class WordPattern {
	//use hashMap
	public static boolean wordPattern(String pattern, String str) {
        HashMap<Character, String > map = new HashMap<Character, String >();
        
        String[] ary = str.split("\\s");   // convert string to array [dog, cat, cat, dog]
        char[] c = pattern.toCharArray();  // convert pattern to array [a, b, b, a]
        
        if(ary.length != c.length)    // length of string and pattern should be the same
            return false;
        
        for(int i = 0 ; i < ary.length ; i++){
            if(!map.containsKey(c[i])){    // check if map has key 'a' or 'b'
                if(map.containsValue(ary[i]))  //return false if value match, but key doesn't match 
                    return false;
                map.put(c[i] , ary[i]);  // add {a=dog} to hashMap 
                
            } else{                        // when key matches, need to check values
                if(!map.get(c[i]).equals(ary[i])) // compare value from hash with current element of arry
                    return false;
            }
        }
        return true;
    }
	 
	public static void main (String[] args)
    {
		String s = "dog cat cat dog";  
		String pattern = "abba";

//		System.out.println("Match pattern ? " + wordPattern(pattern, s)); //true
		
		String s1 = "dog cat cat fish";  
		System.out.println("Match pattern ? " + wordPattern(pattern, s1)); //false
    }
}
