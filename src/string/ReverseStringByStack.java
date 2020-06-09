package string;

/* Reverse String use stack 
 *  Have to use 2 for loop
 *
 * One for loop: Push all characters of string to stack
 * 2nd for loop: Pop all characters of string and put them back to str
 */


public class ReverseStringByStack
	{
	  //Prefer Way1: function to reverse the string
	 public static void reverse(StringBuffer str) 
	  {
	      // Create a stack of capacity equal to length of string
	      int n = str.length();
	      Stack obj = new Stack(n);
	       
	      // Push all characters of string to stack
	      for (int i = 0; i < n; i++)
	         obj.push(str.charAt(i));
	     
	      // Pop all characters of string and put them back to str
	      for (int i = 0; i < n; i++)
	       { 
	           char ch = obj.pop();
	           System.out.println("Pop : " + ch);
	           str.setCharAt(i,ch);  //modify string
	       }
	  } 
	  
	  public static String reverse2(String str)
	  {
	      // Create a stack of capacity equal to length of string
	      int n = str.length();
	      Stack obj = new Stack(n);
	       
	      // Push all characters of string to stack
	      for (int i = 0; i < n; i++)
	         obj.push(str.charAt(i));
	     
	      // Pop all characters of string and put them back to str
	      String newStr = "";
	      char [] newchars = new char[n + 1];
	      
	      for (int i = 0; i < n; i++)
	       { 
	           char ch = obj.pop();
	            //can put each char into array, then create New String
	            newchars[i] = ch;
	            newStr = new String(newchars);// String(char [] value)
	            System.out.println("New String after pop() : " + newStr);
	       }
	      return newStr;
	      
	  } 
	
	  public static void main(String args[])
	  {
	      //create a new string
		 // A string buffer is like a String, but can be modified
	      StringBuffer  s= new StringBuffer("Geeks");
	      reverse(s);
	      //print the reversed string
	      System.out.println("Reversed string is : " + s);
	      

		  String s2 = "Google";
		  System.out.println("Reversed string is : " + reverse2(s2));
	      
	  }
}