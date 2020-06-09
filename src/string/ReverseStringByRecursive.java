package string;
/*
 * 344. Reverse String
 * https://leetcode.com/problems/reverse-string/
 * Given s = "hello", return "olleh"
 *
 */

public class ReverseStringByRecursive  {
	
	public static void main (String[] args)
    {
		String s = "top";
		System.out.println("Input: " + s);
		
		System.out.println(reverse(s));
		
		System.out.println("Reversed by recursive: "+ reverse2(s));		
    }

	    // use char array , swap from last char to first char
		public static String reverse(String s) {
			if (s.length() <= 1) {
				return s;
			}
			//convert String to character array
			char[] arry = s.toCharArray();
			
			/*for (int i = arry.length - 1;  i >= 0; i--) {
				System.out.println("Reversed: " +  arry[i]);
			}*/
			
			// use 2 pointers
			int i = 0;
			int j = s.length() - 1;
			
			while (i < j) { // then swap char at index i and j
			/*	char tmp = arry[i];
				arry[i++] = arry[j];  //increment i++
				arry[j--] = tmp;	  //decrement j--;
			*/	
				swap (arry, i , j);
				i++;
				j--;
			}
			return new String(arry);
		}
		
		private static void swap(char[] s, int l, int r) {
	        char tmp = s[l];
	        s[l] = s[r];
	        s[r] = tmp;
	    }
		
	/**
	 * use recursive
	 *  1. Divide string in 2 parts - first character and rest of the string
	 * 2. Call reverse for the rest of the string
	 * 3. Link the rest to first character
	 *
	 * Step 1:
	 * Use substring(0,1) is  first character
	 * substring(1, s.lengt()) is starting second character to last character
	 * Step 2 & 3:
	 *  Call reverse for substring(1, s.lengt())  + substring(0,1)
	 *  s is top --> result = reverse(op) + t
	 *  
	 * reverse goes from second char top --> t and op -> swap = op + t
	 */
	
	public static String reverse2(String s) {
		//System.out.println("First character: " + s.substring(0, 1) + " and second char to last: " + s.substring(1, s.length())); 
		
		String result;
		if (s.length() <= 1)   
			result = s;
		else {  
			System.out.println("First char: " +  s.substring(0, 1));          //Note: reverse goes from top --> op -> p 
			result = reverse(s.substring(1, s.length())) + s.substring(0, 1) ;   // s is top --> reverse(op) + t            
			System.out.println("Result: " + result);                             // s is op --> reverse(p) + o) + t
																				 // s is p  --> line 40: result = p
			                                                                     // Result: po --> pot			                                                  
		}
		System.out.println("Reversed: " + result); 
		return result;
	}


}