package string;

/* 686. Repeated String Match  (easy level)
https://leetcode.com/problems/repeated-string-match/

Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.

For example, with A = "abcd" and B = "cdabcdab".

Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; 
and B is not a substring of A repeated two times ("abcdabcd").
 */

public class RepeatedStringMatch { 
	
	public static int repeatedStringMatch(String a, String b) {
        StringBuilder sb = new StringBuilder(a);
        
        int count = 1;
        while(sb.length() < b.length()) {
            sb.append(a);
            ++count;
        }
        
        if(sb.toString().contains(b))
            return count;
        
        sb.append(a);
        ++count;
        
        return sb.toString().contains(b) ? count : -1;
    }
		
    public static void main(String[] args) 
    { 
    	 String A = "abcd"; 
    	 String B = "cdabcdab";
    	 
	  
    	 int count = repeatedStringMatch(A,B);  // 3
    	 System.out.println(count);
    }
  
}
	
	