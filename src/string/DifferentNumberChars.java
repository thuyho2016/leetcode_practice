package string;
/*
Validate String:

Problem1: Check if input is an integer or a string
	Input : 127
	Output : Integer
	
	Explanation : All digits are in the range '0-9'.
	
	Input : 122B
	Output : String
	
	Explanation : A alphabet is present.


Problem 2: Given a string str, return true string follows pattern anbn, i.e., it has a’s followed by b’s 

	Input : str = "aabb"
	Output : Yes
	
	Input : str = "abab"
	Output : No
	
	Input : str = "aabbb"
	Output : No

	 My thought is to first count a’s. If number of a’s is not equal to half of string’s length, 
	 then return false. Else check if all remaining characters are b’s or not.
 
 
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class DifferentNumberChars {
	  
	public static int solution(String S) {
        // write your code in C# 6.0 with .NET 4.5 (Mono)
		 
		HashMap<Character, Integer> map = new HashMap<Character,Integer>();
		
		/*count each characters frequency*/
		for (char c : S.toCharArray())
			map.put(c, map.getOrDefault(c, 0) + 1); //{a=3, b=4}
	    
	    Collection<Integer> values =  map.values();
        System.out.println("Get all Values: " + map.values());
       
     
        int diff = 0; // number of deletion
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Character> q = new PriorityQueue<>();
        q.addAll(map.keySet());
        
        while(!q.isEmpty()) {
            char c = q.poll(); //remove the head of queue
           // int freq = map.get(c); //get value of the head (character)  
            result.add(map.get(c));
        } 
        
        for (int i = 0; i < map.size(); i++) {
        	diff = Math.abs(result.get(i++) - result.get(i));
        }
		return diff;
    }
	
	public static void main(String[] args) {
	    
	 //   String s = "aaabbbb";
	 //   System.out.println(solution(s)); // different number of characters
	   
	    String s2 = "aaaabbbb";
	    System.out.println(solution(s2)); // different number of characters
	      	    
	}
}
	
	