package string;

import java.util.HashMap;
import java.util.TreeSet;

/* 387. First Unique Character in a String
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 * 
 Given a string, find the first non-repeating character in it and return it's index. 
 If it doesn't exist, return -1.
s = "leetcode"
return 0.  // leter l is unique

s = "loveleetcode",
return 2.   // v is unique and its index is 2
*/

public class FirstUniqueCharacterInString_FirstNonReapeatingCharacter  {
	/**
	 
	 - create an array of 26 letters (lowercase english alphabet).
	 - I traverse the entire string as chars and add 1 to every char. once the 'for' loop is complete we have the exact count of every appearance of a char in the string.
	 - Then, I  do another traversal and return the first character which we count only appear ONE, if we found one we return its index. if not, we return -1.
	*/
	
	public static int firstUniqueCharacter(String s) {
        if (s.length() == 1) return 0;
        
        int[] chars = new int[26]; // ['z' - 'a' + 1]   int frequency[] = new int[26];
        for (char c: s.toCharArray()) { 
        	chars[c - 'a']++; // increase count - [0, 0, 0, 0, 1, 0, 0, '1', 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        }
        
        for (int i =0; i < s.length(); i++) {   //hello
        	int indx = s.charAt(i) - 'a'; // 'h' has index  = 7 
        	if (chars[indx] == 1 ) return i;  // chars[7] = '1', index i = 0 from string hello
        }
        return -1;
	}
	
	
	
	//Way 2 ; Use HashMap
	//Time complexity : O(N) since we go through the string of length N two times.
	//Space complexity : O(N) since we have to keep a hash map with N elements.
	
    public static int firstUniqueCharacter2(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int n = s.length();
       
        // build hash map : character and how often it appears
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);  //{h=1} map.getOrDefault(c, 0) return value
        }
        
        // find the index = 1
        for (int i = 0; i < n; i++) {
            if (map.get(s.charAt(i)) == 1) 
                return i;
        }
        return -1;
    }
    
    //Way 3: TreeSet
    public static int firstUniqChar(String s) 
    {
    	
        TreeSet<Character> unq = new TreeSet<Character>();
        StringBuilder sb = new StringBuilder();
        
        for(char val: s.toCharArray())
        {
        	// add method - Adds the specified element to this set if it is not already present
        	// to filter duplicate character from unq = [c,d,e,l,o,t]
            if(!unq.add(val)) 
            {
                sb.append(val); // case1: ee, case 2: leeoe
            }
        }
        String x = sb.toString(); 
        
        int i = 0;
        for(char val: s.toCharArray())  //convert String to char array
        {
            if(!x.contains(Character.toString(val))) // return true if x contains a specific val = l. Return false if does not contain val, then return index
            {
                return i;
            }
            i++;
        }
        return -1;
    }
    
	public static void main (String[] args)
    {  
		String s = "hello"; //"leetcode";
		System.out.println("Index of first Unique char "+ firstUniqueCharacter(s));  //l
		
		s = "google";
		System.out.println("Index of first Unique char: "+ firstUniqueCharacter(s)); // l
    }
}