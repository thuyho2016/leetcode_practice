package string;

import java.util.*;
	
/* 22. Generate Parentheses
 * https://leetcode.com/problems/generate-parentheses/
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
	
For example, given n = 3, a solution set is:
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]


Solution:
Length = n * 3
         
              ""
              |
             (
           /						\
         ((							 ()
        /   \						 |  
     (((	 (()				    ()(  
      |		   |    \				 |	 \
   ((( )	 (()(	 (())		  ()((    ()()
      |        |	  |				 |     |
   ((( ))    (()()   (())(		  ()(()   ()()(
      | 	   |	  |				 |     |
   ((( )))   (()())  (())()		  ()(())  ()()()
   
 */
public class Parentheses_Generate
{
	//backtrack
	public static List<String> generateParenthesis(int pair) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, pair);
        return list;
    }
    
	//the logic in comparing left and right
    public static void backtrack(List<String> list, String str, int open, int close, int pair){
        
        if(str.length() == pair * 2){ //max = 3, ()()() length = 6 ,then add to List
            list.add(str);
            return;
        }
        
        if(open < pair) // open = 0, 1, 2 < 3 = (((
            backtrack(list, str + "(", open + 1, close, pair); // binary by recursive , str = (((
        if(close < open) // close = 0, 1, 2 < 3
            backtrack(list, str + ")", open, close + 1, pair);  // str = )))
    }
    
    /**
    Prefer this solution: DFS
    left and right represents the remaining number of ( and ) that need to be added. 
	When left > right, there are more ")" placed than "(". Such cases are wrong and the method stops. 
	*/
    public static List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<String>();
        generateOneByOne(result, "", n, n); // left = 3, right = 3  //   dfs(result, "", n, n);
        return result;
    }
    
    public static void generateOneByOne(List<String> result, String sublist, int left, int right){
        if(left > right){
            return;
        }
        if(left > 0){ //3, 2, 1
            generateOneByOne(result, sublist + "(" , left-1, right); // sublist = (( , left = 1, 0,
        }
        
        if(right > 0){
            generateOneByOne(result, sublist + ")" , left, right-1); // sublist = (()), left = 0, right 1, 0
        }
        
        if(left == 0 && right == 0){
        	result.add(sublist);
            return;
        }
    }
  	
    public static void main(String args[])
    {
        List<String> res = generateParenthesis2(2); //[(()), ()()]
        System.out.println("Output: " + res);
        
   //     List<String> res2 = generateParenthesis2(3);
   //     System.out.println("Output: " + res2);
        
        System.out.println("Generate Brackets:  " + generateParenthesis(2));
    	//	System.out.println("Generate Brackets: " + generateParenthesis(3));
    }

	
}