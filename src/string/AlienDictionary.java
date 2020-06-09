package string;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * 269. Alien Dictionary
https://leetcode.com/problems/alien-dictionary/

There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
 You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. 
 Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"


Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
] 

Output: "" 

Explanation: The order is invalid, so return "".

Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

Solution:
First, build a degree map for each character in all the words:

w:0
r:0
t:0
f:0
e:0
Then build the hashmap by comparing the adjacent words, the first character that is different between two adjacent words reflect the lexicographical order. For example:

 "wrt",
 "wrf",
    first different character is 3rd letter, so t comes before f

 "wrf",
 "er",
    first different character is 1rd letter, so w comes before e
The characters in set come after the key. x->y means letter x comes before letter y. x -> set: y,z,t,w means x comes before all the letters in the set. The final HashMap "map" looks like.

t -> set: f    
w -> set: e
r -> set: t
e -> set: r
and final HashMap "degree" looks like, the number means "how many letters come before the key":

w:0
r:1
t:1
f:1
e:1

Then use Kahn's aglorithm to do topological sort. This is essentially BFS.
https://en.wikipedia.org/wiki/Topological_sorting


*/

public class AlienDictionary {
	/** Pretty similar to Course Schedule problem (LC 210), the only difference is that
	 * we need to build graph by comparing every consecutive pair of strings firstly,
	 * and then doing topological sort for the graph to get the result string */
	
	public static String alienOrder(String[] words) {
		
	    Map<Character, Set<Character>> graph = new HashMap<Character, Set<Character>>();
	    Map<Character, Integer> inDegreeMap = new HashMap<Character, Integer>();
	    
	    String result="";
	    
	    if(words == null || words.length==0) return result;
	    
	    /* MUST initialize the map, to avoid null exception for those character that will have zero inDegrees (i.e. starting characters) */
	    for(String s: words){
	        for(char c: s.toCharArray()){
	        	inDegreeMap.put(c,0);
	        }
	    }
	    
	    /* build graph, as well as fill out inDegree map for every character */
	    for(int i=0; i<words.length-1; i++){
	        String curWord = words[i];
	        String nextWord = words[i+1];
	        int minLength = Math.min(curWord.length(), nextWord.length());
	        
	        /* according to given dictionary with specified order, traverse every pair of words,
	         * then put each pair into graph map to build the graph, and then update inDegree map
	         * for every "nextChar" (increase their inDegree by 1 every time) */
	        for (int j = 0; j < minLength; j++) {
	        	 char curChar = curWord.charAt(j);
	             char nextChar = nextWord.charAt(j);
	            
	             if (curChar != nextChar) {
	            	 /* update graph map */
	                 graph.putIfAbsent(curChar, new HashSet<>());
	                 Set<Character> set = graph.get(curChar);

	                 /** WARNING: we must check if we already build curChar -> nextChar relationship in graph
	                  * if it contains, we cannot update inDegree map again. Otherwise, this nextChar
	                  * will never be put in the queue when we do BFS traversal
	                  * eg: for the input: {"za", "zb", "ca", "cb"}, we have two pairs of a -> b relationship
	                  * if we increase inDegree value of 'b' again, the final result will not have 'b', since 
	                  * inDegree of b will stay on 1 when queue is empty 
	                  * correct graph: a -> b, z -> c
	                  * incorrect graph: a -> b, a -> b, z -> c
	                  * */
	                 if (!set.contains(nextChar)) {
	                     set.add(nextChar);
	                     graph.put(curChar, set);

	                     /* update inDegree map */
	                     inDegreeMap.put(nextChar, inDegreeMap.getOrDefault(nextChar, 0) + 1);
	                 }

	                 /* we can determine the order of characters ony by first different pair of characters so we cannot add relationship by the rest of characters */
	                 break;
	             }
	         }
	     }

	     /* after building graph, we will have an input that has exact same format as Course Schedule, then we can use BFS to do topological sort */
	     StringBuilder sb = new StringBuilder();
	     Queue<Character> queue = new LinkedList<>();

	     /* put all starting node into queue, which means put all nodes that have inDegree = 0 */
	     for (char key : inDegreeMap.keySet()) {
	         if (inDegreeMap.get(key) == 0) {
	             queue.offer(key);
	         }
	     }

	     /* BFS traversal to build result string */
	     while (!queue.isEmpty()) {
	         char curChar = queue.poll();
	         sb.append(curChar);

	         /* traverse all next node of current node in graph, update inDegree value then put all nodes with zero inDegree into queue */
	         if (graph.containsKey(curChar)) {
	             for (char nextChar : graph.get(curChar)) {
	                 inDegreeMap.put(nextChar, inDegreeMap.get(nextChar) - 1);
	                 if (inDegreeMap.get(nextChar) == 0) {
	                     queue.offer(nextChar);
	                 }
	             }
	         }
	     }

	     /* check if input order is valid */
	     if (sb.length() != inDegreeMap.size()) {
	         return "";
	     }
	     return sb.toString();
	}
	
	
    public static void main(String args[])
    {
    	String[] words = {"wrt",  "wrf",  "er", "ett", "rftt" };
    	System.out.println(Arrays.toString(words));
    	
    	System.out.println(alienOrder(words));
        
    }
}
