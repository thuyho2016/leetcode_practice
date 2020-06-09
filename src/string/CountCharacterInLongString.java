package string;


/*
Input is  string and  character in interested. Return count the number of occurrences of a char in a String?


 Input = 'aabcdefa' , 'a' return 3
 
 Input = 'aaajrigjregioregjo', 'o' , return 1
       
*/

public class CountCharacterInLongString {
	
	public static int countCharacter(String s, char c) {
	     if (s == null) return 0;
	     
	   //  HashMap<String, HashMap<Character,Integer>> map = new HashMap<String, HashMap<Character,Integer>>();
	   //  HashMap<Character, Integer>   map2 = new HashMap<Character, Integer>();
	     
	     char[] charArry = s.toCharArray();
	      int count = 0;
	      
	     for (int i = 0; i < charArry.length; i++) {
	          if ( charArry[i] == c) {
	            count++;
	          }
	     }

	    return count;
	    
	  }
	 
	//by recursive
	public static int countOccurrences(String s, char c)
	{
	    return countOccurrences(s, c, 0);
	}

	private static int countOccurrences(String s, char c, int index)
	{
	    if (index >= s.length())
	    {
	        return 0;
	    }

	    int contribution = s.charAt(index) == c ? 1 : 0;
	    return contribution + countOccurrences(s, c, index + 1); // move to next character in string
	}
	  
	  
	  public static void main(String[] args) {
		String s = "aabcdea";
		char c = 'a';
		System.out.println(countCharacter(s, c));
		 
		System.out.println("By recursive: " + countOccurrences(s, c));
		
	 /*   String str2 = "abcrrytftyfygugudggdfgygehuhiuhuhhuiihuiuiuiuhwiuhiahiwoiqjijqjcoicjqwojwiocjiowqc";
	
	    System.out.println(findCharacter(str2, 'c'));
	    System.out.println(findCharacter(str2, 'y')); */
	  }
}

	  
	