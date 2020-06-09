
/*
Given a string s, remove all the characters in another given string r from it, and return the new string in the original order. 
Also indicate how you would test the code by specifying test cases
 
// e.g. calling cleanup(“hello world”, “ol”) should return “he wrd”


hell and  world
hell w and rld
he and l wrld
he and  wrld
he wr and d
he wrd

*/

public class RemoveCharacters {
	
    public static String cleanup(String s, String t) {
		
       if (t.length() == 0) return s;
    
       if(s.indexOf(t.charAt(0))!= -1){
    	   System.out.println("s=" + s + " and t=" + t);
    	   System.out.println(s.substring(0, s.indexOf(t.charAt(0))) + "+" + s.substring(s.indexOf(t.charAt(0)) + 1) );
           s = s.substring(0, s.indexOf(t.charAt(0)))+ s.substring(s.indexOf(t.charAt(0)) + 1);
           s = cleanup(s,t);
       } else { //hell wrld , if no such character 'o' occurs in this string, then -1 is returned.
           s = cleanup(s,t.substring(1)); //t.substring(1) = l
       }
       return s;       
		
	}
	

	public static void main (String[] args)
    {  
		String s1 = "hello world"; 
		String t1 = "ol";
		System.out.println(cleanup(s1,t1)); //he wrd
		
		String s2 = "hello world"; 
		String t2 = "olh";
		System.out.println(cleanup(s2,t2)); //e wrd
		
	/*	String s3 = "hello world"; 
		String t3 = "o l";
		System.out.println(cleanup(s3,t3)); //hewrd
		
		String s4 = "hello"; 
		String t4 = "w";
		System.out.println(cleanup(s4,t4)); //hello
		
		String s5 = "hello world"; 
		String t5 = "";
		System.out.println(cleanup(s5,t5)); //hello world
		
		String s6 = ""; 
		String t6 = "world";
		System.out.println("Ouput: " + cleanup(s6,t6)); //empty
	*/	
    }
}