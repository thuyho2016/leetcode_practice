package string;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
 /*
Input : angles, "><< ><"
Output: "<> <<><>>"

Write a function that adds angle brackets to the beginning and end to make all angle brackets
match and return it.
 * 
 */

public class Parentheses_AngleBrackets  {
	
	//By Stack - if stack is empty, the string is valid
	private static boolean isAngelBrackets(String s) {
		 Stack<Character> stack = new Stack<>();
		 
		 for (int i = 0; i < s.length(); i++) {
			 char c = s.charAt(i);
			 
			 if (c == '<') {
				 stack.push(s.charAt(i));
			 } else if (c == '>') {
				 if(! stack.empty()) {
				 // if stack is not empty && top pairs with s.charAt(i), then pop(). Else return false.
					 char peek = (char) stack.peek();
					if ( c == '>' && peek == '<' ) {
						 stack.pop(); // pop <  until stack is empty
					 } else 
						 return false;
				 }  
			 }
		 }
		 return stack.isEmpty() ? true : false;		
	}		
	
	public static boolean isValid(String str){
        int count = 0;
        for(int i = 0; i < str.length(); i++){
            char tmp = str.charAt(i);
            if(tmp == '<'){
                count++;
            }else if(tmp == '>'){
                count--;
            }
            if(count < 0)
                return false;
        }
        return count == 0;
    }
	
	// not working ??? 
	public static List<String> addAngleBrackets(String s) {
        if(s == null) {
           return null;
        }
        List<String> result = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        
        visited.add(s);
        queue.add(s);
        
        while(!queue.isEmpty()) {
            String cur = queue.remove();
            if(isValid(cur)) {
                result.add(cur);
            }
            
            for(int i = 0; i < cur.length() && result.isEmpty(); i++) {
                if(cur.charAt(i) == '<' || cur.charAt(i) == '>') {
                    String newStr = cur.substring(0,i) + cur.substring(i+1);
                    if(!visited.contains(newStr)) {
                        visited.add(newStr);
                        queue.add(newStr);
                    }
                }
            }
        }
        
        if(result.isEmpty()) {
            result.add("");
        }
        return result;
    }
	
	public static void main (String[] args)
    {
		String s = "<<><>>";
		System.out.println("Is " + s + " balanced? " + isAngelBrackets(s));
		
		String s2 = "><<><";
		System.out.println("Is " + s2 + " balanced? " + isAngelBrackets(s2));
		
		System.out.println("Is " + s + " balanced? " + isValid(s));
		System.out.println("Is " + s2 + " balanced? " + isValid(s2));
		
		System.out.println("After Add angle:  " + addAngleBrackets(s2));
	
    }
	
}