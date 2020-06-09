package string;
/*
 * 
 * 345. Reverse Vowels of a String
 * https://leetcode.com/problems/reverse-vowels-of-a-string/
 * 
 * Input: leetcodi
 * Output: liotcede
 * 
 * Example 1:

Input: "hello"
Output: "holle"


 * Solution:
 * use while /for loop go through each charater, Check string contains vowel. 
 * If vowel appears, then swap positions of start char 'e' and end char 'i'. 
 * Otherwise, move forward 
 */


public class ReverseOnlyVowelsString{
	
	public static void main(String[] args) {
	   
	   String str ="lEetcodi";  // vowels = ee & o i
	   System.out.println(reverseVowels(str));  //liotcede
	   
	   String str2 ="hello";  // vowels = ee & o i
	   System.out.println(reverseVowels(str2)); //holle
	
	   String s = "leetcode";
	   System.out.println(removeVowels(s)); //ltcd
	}
	
	public static String reverseVowels(String s) {
	    if(s == null || s.length()==0) return s;
	    
	    char[] chars = s.toCharArray();
	    
	    int start = 0;
	    int end = s.length() - 1;
	    
	    while(start < end){
	    	if (!isVowel(chars[start])){
	            start++;
	        }
	    	else if (!isVowel(chars[end])){
	            end--;
	        }
	    	else {
	    		
	    	    if (chars[start] != chars[end]) {
			        //swap if chars[start] and chars[end] are vowel character
			        char temp = chars[start];  
			        chars[start] = chars[end];
			        chars[end] = temp;
	    	    }
	        start++;
	        end--;
	    	}
	    }
	    return new String(chars); //because string is immutable
	}
	
	private static boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch); // convert to lower case
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
	
	public static String removeVowels(String S) {
        StringBuilder builder = new StringBuilder();
		for(int i=0; i< S.length(); i++) {
			char s = S.charAt(i);
			if( 'a' == s || 'e' == s || 'i' == s || 'o' == s || 'u' == s) {
				continue;
			} else {
				builder.append(s);
			}
		}
		return builder.toString();
    }
}
	
	