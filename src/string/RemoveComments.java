package string;

import java.util.ArrayList;
import java.util.List;

/* 
 * 722. Remove Comments
 https://leetcode.com/problems/remove-comments/


Given a C++ program, remove comments from it. The program source is an array where source[i] is the i-th line of the source code. This represents the result of splitting the original source code string by the newline character \n.

In C++, there are two types of comments, line comments, and block comments.

The string // denotes a line comment, which represents that it and rest of the characters to the right of it in the same line should be ignored.

The string /* denotes a block comment, which represents that all characters until the next (non-overlapping) occurrence of  
.....

*/


public class RemoveComments {
	
	public static List<String> removeComments(String[] source) {
		int i = 0;
        List<String> res = new ArrayList<>();
        
        while ( i < source.length) {
        	
        	int lineStart = source[i].indexOf("//");
        	int blockStart = source[i].indexOf("/*");
        	
        	if (blockStart < 0 && lineStart < 0) { // no comments
        		if (source[i].length() > 0) {
        			res.add(source[i]);
        		}
        		i++;
        	}
        	 // line comments
            else if ( blockStart < 0 || lineStart >= 0 && lineStart < blockStart) {
        
        		if ( lineStart != 0) {
        			res.add(source[i].substring(0, lineStart));
        		}
        		i++;
        	} 
        	//block comments
            else {
            	for(int j = i; j < source.length; j++) { 
            		int blockEnd = source[j].indexOf("*/");
            		
            		if (j == i) {  // corner case:  "/*/",   "/*/ */"
            			blockEnd = source[i].indexOf("*/", blockStart + 2);
            		}
            		if (blockEnd != -1) {
            			source[j] = source[i].substring(0, blockStart) + source[j].substring(blockEnd + 2);
            			i = j;
            			break;
            		}	
            	}
        	}
        }//while
		return res;
    }
	
	/*
 	Solution:

	We only need to check for two things:

	If we see '//' we stop reading the current line, and add whatever characters we have seen to the result.
	If we see '/*' then we start the multiline comment mode and we keep on ignoring characters until we see '*\/'.
	If the current character is neither of the above two and the multiline comment mode is off, then we add that character to the current line.
	Once we parse one line (source[i]), then if the mode is off, we add the currently generated line (StringBuilder) to the result and repeat for source[i + 1].

	We need to be careful not to insert empty lines in the result.

	 */
	public List<String> removeComments2(String[] source) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();   
        
        boolean mode = false;
        
        for (String s : source) {
        	// for each line
            for (int i = 0; i < s.length(); i++) {
                if (mode) {
                    if (s.charAt(i) == '*' && i < s.length() - 1 && s.charAt(i + 1) == '/') {
                        mode = false;
                        i++;        //skip '/' on next iteration of i
                    }
                }
                else {
                    if (s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '/') {
                        break;      //ignore remaining characters on line s
                    }
                    else if (s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '*') {
                        mode = true;
                        i++;           //skip '*' on next iteration of i
                    }
                    else    
                    	sb.append(s.charAt(i));     //not a comment
                }
            }
            
            if (!mode && sb.length() > 0) {
                res.add(sb.toString());
                sb = new StringBuilder();   //reset for next line of source code
            }
        }
        return res;
    }
	
	
	public static void main(String args[])
	{  
		String[] source = {"a/*comment", "line",  "more_comment*b/" };
		System.out.println(removeComments(source));
		
		String[] source2 = {"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}" };
		
		System.out.println(removeComments(source2)); //["int main()","{ ","  ","int a, b, c;","a = b + c;","}"]
	}
}
