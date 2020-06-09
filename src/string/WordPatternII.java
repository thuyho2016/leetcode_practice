package string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
 * 291. Word Pattern II (hard level)

https://leetcode.com/problems/word-pattern-ii/

Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Example 1:

Input: pattern = "abab", str = "redblueredblue"
Output: true
Example 2:

Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
Output: true
Example 3:

Input: pattern = "aabb", str = "xyzabcxzyabc"
Output: false */

public class WordPatternII {
	//use one HashMap
	public static boolean wordPatternMatch(String pattern, String str) {
	    Map<Character, String> map = new HashMap<>();

	    return search(pattern, 0, str, 0, map);
	}

	//2 pointers i for pattern, j for string
	public static boolean search(String pattern, int pPos, String str, int sPos, Map<Character, String> map) {
		/*success base case*/
	    if(sPos == str.length() && pPos == pattern.length()) return true;
	    
	    if(sPos == str.length() || pPos == pattern.length()) return false;
	 
	    char c = pattern.charAt(pPos);  // a
	    
	    for(int i = sPos; i < str.length(); i++) {
	        String substr = str.substring(sPos, i+1); //r, e, db
	        System.out.println("Substr: " + substr);
	        
	        if(map.containsKey(c) && map.get(c).equals(substr) ) {
	            if(search(pattern, pPos + 1, str, i + 1, map)) return true;  /*Go deeper for search*/
	        }
	        
	        if(!map.containsKey(c) && !map.containsValue(substr) ) { // there is no mapping for current key, add pattern key
	            map.put(c, substr);
	            if(search(pattern, pPos+1, str, i + 1, map)) { // {a=r}, {a=r, b=e} continue the search
	            	return true; 
	            }
	            map.remove(c);
	        }
	    }           
	    return false;
	}
	
	
	//use 2 hashMap
	public static boolean wordPatternMatch2(String pattern, String str) {
	    if (pattern == null && str == null) {
	        return true;
	    }
	    if (pattern == null || str == null) {
	        return false;
	    }
	    HashMap<Character, String> map = new HashMap<Character, String>();
	    HashSet<String> used = new HashSet<String>();
	    return searchPath(pattern, str, 0, 0, map, used);
	}


	private static boolean searchPath(String pattern, String str, int i, int j, HashMap<Character, String> map, HashSet<String> used) {
	    /*success base case*/
	    if (i == pattern.length() && j == str.length()) {
	        return true;
	    }
	    if (i == pattern.length() || j == str.length()) {
	        return false;
	    }
	    char p = pattern.charAt(i);
	    /*If the pattern.charAt(i) has already been mapped.*/
	    if (map.containsKey(p)) {
	        String word = map.get(p);
	        /*It is impossible to meet the success base case along this search path. This search path could be cut off.*/
	        if (!str.startsWith(word, j)) {
	            return false;
	        }
	        /*Go deeper for search*/
	        return searchPath(pattern, str, i + 1, j + word.length(), map, used);
	    } else {
	        for (int k = j; k < str.length(); k++) {
	            String temp = str.substring(j, k + 1);
	            /*Same as WordPattern 1. If the word has already been mapped with other p.*/
	            if (!used.contains(temp)) {
	                map.put(p, temp);
	                used.add(temp);
	                /*Once there there is a search path meet the success base case*/
	                if (searchPath(pattern, str, i + 1, j + temp.length(), map, used)) {
	                    return true;
	                }
	                used.remove(temp);
	                map.remove(p);
	            }
	        }
	        /*none of "str.substring(j, k + 1)" is qualified*/
	        return false;
	    }
    }
	 
	public static void main (String[] args)
    {
		String s = "dog cat cat dog";  
		String pattern = "abba";
//		System.out.println("Match pattern ? " + wordPattern(pattern, s)); //true
		
		String pattern2 = "abab";
		String s2 = "redblueredblue";  
		System.out.println("Match pattern ? " + wordPatternMatch(pattern2, s2)); //false
    }
}
