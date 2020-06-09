package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * 451. Sort Characters By Frequency
 * https://leetcode.com/problems/sort-characters-by-frequency/

Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:
Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.

Example 3:
Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.

Steps:

- create a hasmap to count each characters frequency as value
- create a queue to add key based on sorting value in descending order
- pop from queue until it is empty and append character to string builder based off the frequency of the character stored in the map. 
 */

public class SortCharactersByFrequency {
	
	//prefer this
	public static String sortByFrequent(String s) {

        if (s == null) return null;
        char[] arr = s.toCharArray();
        
        Map<Character,Integer> map = new HashMap<>(); // /*count each number frequency*/
        for(char c : arr)
        	map.put(c, map.getOrDefault(c, 0) + 1 ); 
        
        //get list of allKey
        List<Character> frequentList = new ArrayList<>(map.keySet());
        
        // sort value in ascending by sort method
       Collections.sort(frequentList, (k1, k2) -> map.get(k1).equals(map.get(k2)) ?
    		            k1.compareTo(k2):  map.get(k2) - map.get(k1) ); // sort value in descending      
       
       StringBuilder sb = new StringBuilder();
       for(int i = 0; i < frequentList.size(); i++)   {
    	   char c = frequentList.get(i);
    	   int freq = map.get(c); 
           for(int j = 0; j < freq; j++)  
        	   sb.append(c);
       }
       
        return sb.toString(); //"eert";
    }
  
	
	public static String frequencySort(String s) {
	    /*count each characters frequency*/
	    Map<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()) 
        	map.put(c, map.getOrDefault(c, 0) + 1 );  //{r=2, t=1, e=3}
        
        //create a heap which is ordered by their frequency based on value of key . 
        //(a,b)-> b - a is descending.  (a,b) -> a - b is ascending.
        
        //sort by frequent value
        PriorityQueue<Character> q = new PriorityQueue<>((a,b) ->  map.get(b) - map.get(a));
        
        q.addAll(map.keySet());  //add [e, t, r]
        
       /*pop from queue until it is empty and append character to string builder based off the frequency of the character stored in the map. */
        StringBuilder sb = new StringBuilder();
        
        while(!q.isEmpty()) {  //[e, r, t], [t, r]
            char c = q.poll(); //e - remove the head of queue e
            
            int freq = map.get(c); //3 - get value of key e           
            for(int i = 0; i < freq; i++)   //append character to string builder based on the frequency of the character
            	sb.append(c);    //eee , eeerr, eeerrt
        }      
        return sb.toString(); //"eert"
    }
	
	public static void main(String[] args) {
	    
	    String s = "trreee"; 
	    System.out.println(sortByFrequent(s));
	    
	    String s1 = "trreee"; 
	    System.out.println(frequencySort(s1)); //"eert"
	      	    
	}
}
