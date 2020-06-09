package string;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
49. Group Anagrams
https://leetcode.com/problems/group-anagrams/

Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],

Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note:

All inputs will be in lowercase.
The order of your output does not matter.

Time & Space Complexity: O(NK) - N is the length of strs, and K is the maximum length of a string in strs
*/

public class AnagramGroup {
	
	/* Solution: Sort to find anagram and put hashmap
	   For each element: put it in char array, sort it, then convert it as String
	     E.g eat -> [ a,e,t] --> String key = aet;
	     This key String will be key in hashmap
	      
	    if key is not seen in Hash Map
	    	add it as key and create a new List as value  to hashmap 
	    then add current element as value to the new List    
	    // hm = {aet=[eat, tea, ate], abt=[bat], ant=[tan, nat]}    
	   
	   Last step, need to get values from HM and return
	     
	*/
	public static List<List<String>> groupAnagrams(String[] strs) {
	    
		List<List<String>> result = new ArrayList<List<String>>();
	    HashMap<String, List<String>> hm = new HashMap<>();
	    
	    
	    for (int i = 0; i < strs.length; i++) {
	    	// convert to array to sort each character:  aet, tea-> aet and tan ->ant  
	   	    // and convert back to string
	    
	        char[] chars = strs[i].toCharArray(); //[e,a,t]
	        Arrays.sort(chars); //[a,e,t]
	        String key = new String(chars);
	        
	        if (!hm.containsKey(key)) {
	        	hm.put(key, new ArrayList<String>());
	        }
	        hm.get(key).add(strs[i]); // value hold a list of original Strings {aet=[eat]}    	  	   
	    }  
	    
	   /*hm = {aet=[eat, tea, ate], 
	           abt=[bat],
	           ant=[tan, nat]}
	    */
	    //get values from hm
	    for(Map.Entry<String, List<String>> entry: hm.entrySet()) {
		   List<String>values = entry.getValue();
		   //sort the result 
	       Collections.sort(values);               

	        result.add(values);
	    }
	    return result;
	 }
	
	
	//Problem 2:  remove words that are anagram and only keep one. 
	// For words not anagram, keep it. Return a list in alphapet orders
	public static List<String> findAnagrams(List<String> s) {
		List<String> res = new ArrayList<>();
	    HashMap<String, Integer> hm = new HashMap<>();
	    
	    int count = 0;
	    
	    for (int i = 0; i < s.size(); i++) {
	    	//for each element, sort it
	    	char[] charArry = s.get(i).toCharArray();  //[c, d, e, o]
	    	Arrays.sort(charArry);                   // sort [c, d, e, o]
	    	String word = new String(charArry);    //convert to string cdeo

	    	if(!hm.containsKey(word)) {
		        hm.put( word, count); // count to track for same index {cdeo=0}
		        count++;
		        res.add(s.get(i));
	    	}
	    }
	    //hm = {aefmr=2, cdeo=0, aefmrr=1}
	    Collections.sort(res);
	    return res;
    }

	//Prefer use Set
	public static List<String> findAnagrams2(List<String> s) {
		List<String> res = new ArrayList<>();
	    Set<String> hm = new HashSet<String>();
	  
	    for (int i = 0; i < s.size(); i++) {
	    	//for word, convert to char array, sort and convert back to string
	    	char[] charArry = s.get(i).toCharArray();  //convert to arry [c, d, e, o]
	    	Arrays.sort(charArry);                    // sort array[c, d, e, o]
	    	String word = new String(charArry);     //convert to string cdeo

	    	if(!hm.contains(word)) {
		        hm.add(word); // count to track for same index {cdeo=0}
		        res.add(s.get(i));
	    	}
	    }
	    
	    Collections.sort(res);
	    return res;
    }
	
	public static void main (String[] agrs) {
		String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
		  
 	   List<List<String>> result = groupAnagrams(input);
		for (List<String> e : result) {
			System.out.println(e);
		}
		
		//problem 2
		List<String> input2 = Arrays.asList("code", "doce", "edoc", "framer", "frame" );
		List<String> result2 = findAnagrams(input2);
		System.out.println(result2);
		
		List<String> result3 = findAnagrams2(input2);
		System.out.println(result3);
		
	}
}

	  
	