package string;

/* Problem : increment each character in a string 

1.Create a new String .

2. Use str.charAt(index) method of String class to access each character.

3.And simply add ‘1’ to it and type cast it back to char

4.And add it back to String.
*/

public class IncrementString {
	  
	   // Increment abcd -- > abc3 
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
	    	
	    	
	    	
	    	String s = "abcd";
	    	System.out.println("New string: " + incrementString(s)); //abce
	    }

}
