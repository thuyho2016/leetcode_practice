package string;

public class Subsequence {
	
	public static boolean isSubsequence(String s, String t) {
        /*
            we use two pointers, i and j to keep tract of the characters, if they are the same, we increase both.
            else we increase j. At the end, we check if i = s.length()
            Time complexity = O(n) 
            Space complexity = O(1)
        */
        int i,j;
        for(i = 0,j = 0; i<s.length() && j < t.length(); ){
            if(s.charAt(i) == t.charAt(j)){
                i++; 
                j++;
            }else {
                j++;
            }
            
        }
       return i == s.length();
    }
	
	public static void main(String[] args) {
		String s = "abc";
		String t = "ahbgdc";
		System.out.println(isSubsequence(s,t));
		
		String s2 = "axc";
		String t2 = "ahbgdc";
		System.out.println(isSubsequence(s2,t2));
	}

}
