package string;
/*
  541. Reverse String II
 *  https://leetcode.com/problems/reverse-string-ii/
 *  Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. 
 *  If there are less than k characters left, reverse all of them. 
 *  If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
 *  
    Input: s = "abcdefg", k = 2
    Output: "bacdfeg"
    
    Time and Space Complexity: O(N)
 */

public class ReverseStringII_Every2kChars  {
	
	public static void main (String[] args)
    {
		String s = "abc";
	//	System.out.println(reverseStr(s, 2)); // reverse 2 chars ab -> ba, keep c because one char < k = 2
		
		String s2 = "abcdefg"; // reverse ab , ef
		System.out.println(reverseStr(s2, 2));		
    }

	/** 
	 * We will reverse each block of 2k characters directly.
	   Each block starts at a multiple of 2k: for example, 0, 2k, 4k, 6k,
	   k = 2, start : 0, 4
	 * suppose we are near the end of the char array a[]，and we need do once more reverse. 
	 * If there are exact k or more than k chars left, boundary start + k - 1 works just fine, we can just do the reverse. 
	 * if less than k characters left,  we need to reverse all of them, 
	 * the boundary should be length - 1 then, otherwise, index will be out of range.
	 */
	
	public static String reverseStr(String s, int k) {
        char[] a = s.toCharArray();
        
        for (int start = 0; start < a.length; start += 2 * k) { // start = 2 * 2 = 4 < 6 . when start = 8 out of range
            int i = start; // 0, 4
            int j = Math.min(start + k - 1, a.length - 1); // 1, 5
            
            while (i < j) {  //0 < 1; 4 < 5
                char tmp = a[i];
                a[i++] = a[j];
                a[j--] = tmp;
            }
        }
        return new String(a);  // return a String which has char arry a
    }

}