package string;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
 
/*
301. Remove Invalid Parentheses (hard level)
https://leetcode.com/problems/remove-invalid-parentheses/

Remove the minimum number of invalid parentheses in order to make the input string valid. 
Return all possible results.

Input: "()())()"
Output: ["()()()", "(())()"]

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]

Input: ")("
Output: [""]
*/


public class Parentheses_RemoveInvalid  {
	
	// Breadth First Search.
	public static List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<String>(); 
      
        boolean found = false;
        
        visited.add(s);
        queue.add(s); // queue.offer(s);
        
        while(!queue.isEmpty()){
                                         // )())(), (())(), ()))(), ()()(), ()())), ()())(
            String cur = queue.remove(); // or poll()
            if(isValidStr(cur)){       //if it is valid, then add result to return
                found = true;
              //  if(!results.contains(cur)) //(())() , ()()()
                result.add(cur);  
            }
            
            if(!found) {
	           //get unvisited adjacent list by removing one parenthesis from s.
	            for(int i = 0; i < cur.length(); i++){
	                char c = cur.charAt(i);
	                
	                if(c == '(' || c == ')'){
	                	System.out.println(cur.substring(0,i) + " concat " + cur.substring(i+1));
	                    
	                	String t = cur.substring(0,i) + cur.substring(i+1);  // )())(), (())(), ()))(), ()()(), ()())(), ()()))	                   
	                    if(!visited.contains(t)){ 
	                        queue.add(t);    // or queue.offer(t); 
	                        visited.add(t);  // add )())() to visited
	                    }
	                }
	            } 
            }
        }
        
        if(result.isEmpty()) {
            result.add("");
        }
        return result;
    }
	
	public static boolean isValidStr(String str){
        int count = 0;
        
        for(int i = 0; i < str.length(); i++){
            char tmp = str.charAt(i);
            
            if(tmp == '('){
                count++;
            } else if(tmp == ')'){
                count--;
            }
            
            if(count < 0)
                return false;
        }
        return count == 0; //Indicates left parenthesis and right parenthesis are balanced.
    }
	
	public static void main (String[] args)
    {
		//String s = "()())()";
		String s = "())()";
		System.out.println("Is " + s + " balanced? " + isValidStr(s));
		
		System.out.println("After remove invalid parenthesis:  " + removeInvalidParentheses(s));
	
    }
	
}