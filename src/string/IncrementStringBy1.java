package string;
/*
 Increment string by one 

Input: "abcd"
Output: "abce"

*/

public class IncrementStringBy1{
    
    
    /* Steps
 
     1.Create a new String .

	 2. Use str.charAt(index) method of String class to access each character.

	 3.And simply add ‘1’ to it and type cast it back to char

	 4.And substring start from index 0 to len - 1  and  add the incremented char : abc + e 
	 5. return 
     */
    
    
    
    public static String incrementString(String string)
	{
    	int len = string.length() - 1; //abcd --> len = 3
		if(string.length() == 1 ) // string ONLY has one char
		{
			if(string.equals("z"))
				return "aa";
			else if(string.equals("Z"))
				return "Aa";
			else              // case:  b --> after increment c
				return (char)(string.charAt(0) + 1 ) + "" ;
		}	
		
		if(string.charAt(len) !='z')  // if last char is not z
		{   
			
			char incremented = (char) (string.charAt(len) + 1); // string.charAt(len) = d --> (char) d + 1 = e
			
			System.out.println("Substring: " + string.substring(0,len) + " , incremented next char: " + incremented ); // abc
			
			return string.substring(0,len) + incremented; // combine and return 
		}
		else {
			// case : abz - last char = z , then recursive substring ab ( index from 0 ->len = 1)
			return incrementString(string.substring(0, len)) + "a"; 
		}
	}
    
    public static void main(String[] args) {
    	
    	//increment by 1
     	String s1 = "abcd";
    	System.out.println("After increment abcd: " + incrementString(s1)); //abce
    	
    	String s2 = "zz";
    	System.out.println("fter increment zz: " + incrementString(s2)); //abce
    	
    	String s3 = "azz";
    	System.out.println("After increment azz: " + incrementString(s3)); //baa
    	
    	
    	String s4 = "z";
    	System.out.println("After increment a: " + incrementString(s4)); //aa because z + 1 = aa
    	String s5 = "Z";
    	System.out.println("After increment Z: " + incrementString(s5)); //aa because Z + 1 = Aa
    	String s6 = "b";
    	System.out.println("After increment b: " + incrementString(s6)); //aa because b + 1 = c
    	
   
    }

}
	
	