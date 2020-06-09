package string;
/*
415. Add Strings

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
    
	 append(int) is used to append the string representation of the char 
	 reverse() to reverse string
	 
	 E.g: sb.append(sum % 10);  // 18 % 10 = 8
	      sb.reverse().toString(); //5851 ->1585
  314 + 9
  	*/
	  
    public static String addStrings(String num1, String num2) {
        if(num1 == null || num2 == null) return null;
        
        StringBuilder sb = new StringBuilder();
        int sum = 0, carry = 0;
    	
        int len1 = num1.length() - 1; // 1, 0, -1
        int len2 = num2.length() - 1; // 2, 1, 0
        int n1 = 0, n2 =0;
        
        while(len1 >= 0 || len2 >= 0){
         	// case: len1 < len2: 23 + 234 	 
    		// len1 = 1 and n1 = s1.charAt(len1) = 3, len1 = 0 and n1 = s1.charAt(len1) = 2, 
    	    // when len1 = -1 => s1.charAt(-1) will throw exception    	
            // so if len1 < 0,  set n1 = 0 
    		n1 = len1 >= 0 ? num1.charAt(len1) - '0' : 0;   // return char 3, 2
    		
    		n2 = len2 >= 0 ? num2.charAt(len2) - '0': 0;  //  return 4, 3, 2   , len2 < 0, set n2 = 0                   
           
            sum = n1 + n2 + carry; //Case 1:  7 + 4 + 0 = 11         
            carry = sum > 9 ? 1 : 0;  // when sum = 10, 11,..., set carry = 1 . if sum = 9, 8,... carry = 0
                     
            // use % to get last digit  
            // sum % 10 = 11 % 10 = 1 , 14 % 10 = 4  , 8 %10 = 8 , 10%10 =0
        	
            sb.insert(0, sum % 10);  //1 , 41 , 041
            // StringBuilder insert(int offset, int n1) means insert number at offset 0 
            
            len1--; // move to next char
            len2--;
            
        } //while
        
        if(carry == 1) sb.insert(0, 1); // insert 1 at offset 0 (in front of string) for case 99 + 10 = 100
        							  
        return sb.toString();
     
    }
    
    public static String addStringsHasDecimal(String num1, String num2) {
        if(num1 == null || num2 == null) return null;
        
        StringBuilder sb = new StringBuilder();
        int sum = 0, carry = 0;
        
        //check String contain dot. if String has decimal, then add 0
        if (num1.contains(".") ||  num2.contains(".")) {
        	num1 = addZero(num1); 
        	num2 = addZero(num2);
        }
        
        int len1 = num1.length() - 1; 
        int len2 = num2.length() - 1; 
        int n1 = 0, n2 = 0;
        
        while(len1 >= 0 || len2 >= 0){
        			
        	if ( len1 >=0 && !Character.isDigit(num1.charAt(len1)) || len2 >=0 && !Character.isDigit(num2.charAt(len2))){
        		sb.insert(0, ".");
        	} 
        	else {  // Character.isDigit(num1.charAt(len1))  && Character.isDigit(num2.charAt(len2))){
        		n1 = len1 >= 0 ? num1.charAt(len1) - '0' : 0;   
    		
        		n2 = len2 >= 0 ? num2.charAt(len2) - '0': 0;   
	        	 
	            sum = n1 + n2 + carry;          
	            carry = sum > 9 ? 1 : 0;  
	           
	            // use % to get last digit  
	            // sum % 10 = 11 % 10 = 1 , 14 % 10 = 4  , 8 %10 = 8 , 10 % 10 =0
	        	
	            sb.insert(0, sum % 10);  //1 , 41 , 041
	            // StringBuilder insert(int offset, int n1) means insert number at offset 0 	            
        	}	            
            len1--; // move to next char
            len2--;
            
        } //while
        
        if(carry == 1) sb.insert(0, 1); // insert 1 at offset 0, in front of string 
        							  
        return sb.toString();
     
    }
    public static String addZero(String s) {
    	//check String contain dot
    	if (s.contains(".")) {
    		String decimal = s.substring(s.indexOf(".") + 1);
	       
	        if (decimal.length() <= 1 ){
	            s = s + "0";
	        }
    	}   
    	else {
    		 s = s + ".00";
    	}
    
    	System.out.println(s);
        return s;
    }
    

    /* Problem 2: increment each character in a string 
 
     1.Create a new String .

	 2. Use str.charAt(index) method of String class to access each character.

	 3.And simply add ‘1’ to it and type cast it back to char

	 4.And add it back to String.
     */
    
    public static String incrementString(String string)
	{
		if(string.length()==1)
		{
			if(string.equals("z"))
				return "aa";
			else if(string.equals("Z"))
				return "Aa";
			else
				return (char)(string.charAt(0)+1)+ "" ;
		}	
		
		if(string.charAt(string.length() -1 ) !='z')
		{
			return string.substring(0, string.length()-1 )+ (char)(string.charAt(string.length()-1)+1);
		}
		return incrementString(string.substring(0, string.length()-1))+"a";
	}
    
    public static void main(String[] args) {
    	
    	//carry 1 in the front 
    	String num1 =  "1";
    	String num2 = "99";
   
     	String total = addStrings(num1, num2);  // 100
    	System.out.println(total);
  	
    	
    	num1 = "12";
    	num2 = "20";
     	String total2 = addStrings(num1, num2);  //32
    	System.out.println(total2);
  
    	num1 = "147";
    	num2 = "894";
    	String total3 = addStrings(num1, num2);  //  1041
    	System.out.println(total3);
    	
        
    	num1 =  "3.14";
    	num2 = "0.9";
    	System.out.println(addStringsHasDecimal(num1, num2)); //4.04
    	num1 =  "3.14";
    	num2 = "10";
    	System.out.println(addStringsHasDecimal(num1, num2)); //13.14
    	num1 =  "1";
    	num2 = "99";
    	System.out.println(addStringsHasDecimal(num1, num2)); //100
    	
    	String s = "abcd";
    	System.out.println("New string incremented: " + incrementString(s)); //abce    	
    
   }

}
	
	