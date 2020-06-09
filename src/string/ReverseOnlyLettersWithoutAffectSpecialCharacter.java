 package string;
/* 
 * 917. Reverse Only Letters
 * Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, 
 * and all letters reverse their positions.
 * 
 * Reverse String contains special character like -, !
 * Input: a-bc-df
 * Output: f-dc-ba
 * 
 * Input: "Test1ng-Leet=code-Q!"
   Output: "Qedo1ct-eeLg=ntse-T!"

 * Solution:
 * use while /for loop go through each charater, . 
 * If character is special char, move forward. This will keep "-" at its position as origin index 
 * Otherwise, swap: swap first and last character 
 
 Time Complexity: O(N), where N is the length of S.
 Space Complexity: O(N). 

 */


public class ReverseOnlyLettersWithoutAffectSpecialCharacter{
	
	public static void main(String[] args) {
	   
	   String str ="a-bc-df";  
	   
	   System.out.println(reverse(str));
	   
	   str = "Test1ng-Leet=code-Q!";
	   System.out.println(reverse2(str)); //"Qedo1ct-eeLg=ntse-T!"
	
	}
	
	public static String reverse(String s) {
	    if(s == null || s.length()==0) return s;
	    
	    char[] chars = s.toCharArray();
	    
	    //initialize 
	    int start = 0;
	    int end = s.length() - 1;
	    
	    while(start < end){
	    	//if see special character, continue checking, move the pointer 
	    	if (!isAlphabetic(chars[start])){
	            start++;
	        }
	    	else if (!isAlphabetic(chars[end])){
	            end--;
	        }
	        
	        //swap if chars[start] and chars[end] are alphabetic
	    	swap (chars, start , end);
	     /*   char temp = chars[start];    // start swap a and f, then swap b and d
	        chars[start] = chars[end];
	        chars[end] = temp;
	      */  
	        start++;
	        end--;
	    }
	    return new String(chars);
	}
	
	private static boolean isAlphabetic(char ch) {
        
        return (( ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z' )); 
    }
	
	private static void swap(char[] s, int l, int r) {
        char tmp = s[l];
        s[l] = s[r];
        s[r] = tmp;
    }
	
	//Way 2: using Character.isLetter()
	
	public static String reverse2(String s) {
	    if(s == null || s.length()==0) return s;
	    
	    char[] chars = s.toCharArray();
	    
	    //initialize 
	    int start = 0;
	    int end = s.length() - 1;
	    
	    while(start < end){
	    	//ignore special character 
	    	if (!Character.isLetter(chars[start])){
	            start++;
	        }
	    	else if (!Character.isLetter(chars[end])){
	            end--;
	        }
	    	else {
		        //else, swap if chars[start] and chars[end] are alphabetic
	    		swap (chars, start , end);
		        /*char temp = chars[start];    // start swap a and f, then swap b and d
		        chars[start] = chars[end];
		        chars[end] = temp;*/
		        
		        start++;
		        end--;
	    	}
	    }
	    return new String(chars);
	}
	
}
	
	