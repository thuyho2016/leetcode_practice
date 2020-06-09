package string;

import java.util.HashMap;

/*
 * 205. Isomorphic Strings
 * https://leetcode.com/problems/isomorphic-strings/
 * Given two strings s and t, determine if they are isomorphic.(same form)

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. 
No two characters may map to the same character but a character may map to itself.

Example 1:
Input: s = "egg", t = "add"
Output: true

Example 2:
Input: s = "foo", t = "bar"
Output: false

Example 3:
Input: s = "paper", t = "title"
Output: true

Solution:
- mapping 2 string. HashMap helps me to achieve that goal.

- Go each character in string: 
First round, e maps to a (e->a): key e doesn't exist , value a doesn't exist, so add them( to hashMap { e=a}
            if  value exist , return FALSE.
Second round, g -> d doesn't exist, so add them to hashmap
Third round: g ->d, key 'g' exists, I have to check map.get('g') == 'd'. If value doesn't same, return false



- check both of string have to the same length. If not, return FALSE

Time Complexity: O(n)
Space O(1)

 */


public class IsomorphicString {
	
	public static boolean isIsomorphic(String s, String t) {
		if (s.isEmpty() && t.isEmpty()) return true;
		if (s.length() != t.length()) return false;
		
		HashMap<Character,Character> map = new HashMap<Character, Character>();
		
		for (int i=0; i < s.length(); i++) {
			char ci = s.charAt(i);
			char ti = t.charAt(i);
			
			if (map.containsKey(ci)) { // if key exists, check value
				if (map.get(ci) != ti) return false;
				
			} else {  // key doesn't exists, check value. 
				
				if (map.containsValue(ti)) { //If value exists --> return false 
					return false;
				} 
				//value doesn't exit, then add {key, value} pair to map
				map.put(ci, ti);
			}
		}
		return true;
        
    }
	
	
	public static void main(String[] args) {
		
		String s = "egg";
		String t = "add";
		System.out.println(isIsomorphic(s, t));
		
		s = "paper";
		t = "title";
		System.out.println(isIsomorphic(s, t));
		
		s = "";
		t = "";
		System.out.println(isIsomorphic(s, t)); //true
		
		s = "foo";
		t = "bar";
		System.out.println(isIsomorphic(s, t)); //false
				
		s = "ab";
		t = "aa";
		System.out.println(isIsomorphic(s, t)); //false
		
	}
}
