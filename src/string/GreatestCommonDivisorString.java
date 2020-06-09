package string;
/* 1071. Greatest Common Divisor of Strings
 * https://leetcode.com/problems/greatest-common-divisor-of-strings/

 Find common GCD string
  Input: str1 = "ABCABC", str2 = "ABC"
   Output: "ABC"
 */
public class GreatestCommonDivisorString  {
	public static void main (String[] args)
    {
		/*String s1 = "abcabc";
		String s2 = "abc";
		System.out.println("output: " + gcdOfStrings(s1, s2)); //abc
	*/	
		
	    String s3 = "ababab";
	    String s4 = "ab";
	    System.out.println("output: " + gcdOfStrings(s3, s4)); //ab
	    
	    System.out.println("output2: " + gcdOfStrings2(s3, s4)); //ab
	   
    }
	
	
	public static String gcdOfStrings(String str1, String str2) {
	    int l1 = str1.length();  
	    int l2 = str2.length(); //3
	    
        for (int i = Math.min(l1, l2); i > 0; --i) { // i = 3
        	
            if (l1 % i != 0 || l2 % i != 0) continue;
            boolean ok = true;
            String cur = str1.substring(0, i);
            
            //string 1
            for (int j = 0; j < l1; ++j) {
            	 System.out.println("Compare: " + str1.charAt(j) + " != " +  cur.charAt(j % i)); 
                
            	 if (str1.charAt(j) != cur.charAt(j % i)) { // 0 % 3 = 0, 1 % 3 = 1,...
                    ok = false;
                    break;
                }
            }
            //string 2
            for (int j = 0; j < l2; ++j) {
            	 System.out.println("Index j %i = " + j + " % " + i + "= "+ j % i); 
            	 if (str2.charAt(j) != cur.charAt(j % i)) {
                    ok = false;
                    break;
                }
            }
            if (ok) 
            	//return str1.substring(0, i);
            	return cur;
        }
        
        return "";
    }	
	
	//Using recursive
	public static String gcdOfStrings2(String str1, String str2) {
		 int l1 = str1.length();  
		 int l2 = str2.length();
		 
		 if ( l1 == l2) {
			 if (str1.equals(str2))
				 return str1;		 
			 else
				 return "";
		 
		 } else if ( l1 < l2 ){
			 return  gcdOfStrings2(str1, str2.substring(l1)); //"ababab.substring(2) --> ab"
		 } else {
			 return  gcdOfStrings2(str2, str1.substring(l2));
		 }
	}
	
	
	//Euclid's Algorithm for finding GCD
	public static int gcd_recursive(int a, int b) {
	    if (b == 0)
	        return a;
	    else 
	        return gcd_recursive(b, a % b); // 6 % 3 = 0
	}      		
}