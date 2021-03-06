/*
415. Add Strings ( easy level)
https://leetcode.com/problems/add-strings/

Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

String num1 =  "291";
String num2 = "1294";

Output: 1585 ( carry = 1)
    	
Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
 
String is immutable, can NOT be changed
StringBuilder are mutable, it can be changed
The difference between StringBuffer and StringBuilder is that StringBuffer is thread-safe and synchronized.
Thats why StringBuilder is more faster than StringBuffer

*/

public class AddTwoStrings{
        
	/*Steps: 
	- Start at last char of string and Go through each char of string 
	
	- Use str.charAt(index) method of String class to access each character
	
	- add each char from 2 strings.
    - if sum > 9, then carry = 1. otherwise carry = 0
    - insert carry at offset 0 (in front of string)
    
	if use  append(int) is used to append the string representation of the char 
	then  reverse() to reverse string
	 
	 E.g: sb.append(sum % 10);  // 18 % 10 = 8
	      sb.reverse().toString(); //5851 ->1585
  
  	*/
	  
    public static String addStrings(String num1, String num2) {
        if(num1 == null || num2 == null) return null;
        
        int len1 = num1.length() - 1; // 1, 0, -1
        int len2 = num2.length() - 1; // 2, 1, 0
        int carry = 0;
        int sum = 0;
        
        StringBuilder sb = new StringBuilder();
        
        while(len1 >= 0 || len2 >= 0){
        	
    		// len1 = 1 and n1 = s1.charAt(len1) = 3, len1 = 0 and n1 = s1.charAt(len1) = 2, 
    	    // when len1 = -1 => s1.charAt(-1) will throw exception    	 //num1 = "23" and  num2 = "234";	 
            // so if len1 < 0,  set n1 = 0 
        	
        	// length len1 is not the same len2
    		int n1 = len1 >= 0 ? num1.charAt(len1) - '0' : 0;   // return char 3, 2, 0
    		
    		int n2 = len2 >=0 ? num2.charAt(len2) - '0' : 0;    //  return 4, 3, 2          
                           //len2 = 2 and s2.charAt(len2) = 4, len2 = 1 and s2.charAt(len2) = 3, len2 = 0 and s2.charAt(len2) = 2
        
            
            sum = n1 + n2 + carry; //Case 1:  7 + 4 + 0 = 11         
            carry = sum > 9 ? 1 : 0;  // when sum = 10, 11,..., set carry = 1 . if sum = 9, 8,... carry = 0
                   
            // use % to get last digit 
            //  sum % 10 = 11 % 10 = 1 --> sb = 1 , 14 % 10 = 4 --> sb = 4 , 8 %10 = 8 -->sb = 8
            System.out.println( sum + " %10 = " + sum % 10); 
            
            // insert(int offset, int n1) means insert number at offset 0 
            sb.insert(0, sum % 10);  //[7,  ,  ,...],  [5, 7,  ,  ,...]
               						
            len1--; // move to next char
            len2--;
            
        } //while
        
        //sb = 7 -> 57 -> 257 
        
        if(carry == 1) sb.insert(0, 1); // insert 1 at offset 0 (in front of string) for adding two digits = 10 . E.g case:  147 + 894 = 1041
        return sb.toString();
        
       //String res = sb.toString();
       //return res;
    }
    
   
      public static void main(String[] args) {
    	
    	//increment by 1
      	String num1 =  "1";
    	String num2 = "99";
    //	String total = addStrings(num1, num2);  // 100
    //	System.out.println(total);
    	
    	// length is not same 
    	num1 =  "23";
    	num2 = "234";
    	String total2 = addStrings(num1, num2);  //257
    	System.out.println(total2);
    	
    	num1 = "147";
    	num2 = "894";
    	String total3 = addStrings(num1, num2);  //  1041
    	System.out.println(total3);
    
    }

}
	
	