package string;
import java.util.HashSet;
import java.util.Set;

/*
 * 3. Longest Substring Without Repeating Characters
https://leetcode.com/problems/longest-substring-without-repeating-characters/
 
Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of word abc is 3. 

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

 */

public class LongestSubStringWithoutRepeatCharacters{	
    
    /* Best solution: Sliding Window using Set
     
    1.  Use left and right pointers, initialize = 0 , to first index of first character from the string  
        Create a HashSet to store character.
        Use use right pointer to go to each character in string
    2.  If char does NOT exist:
           add to set, right++, update max_len ( expand window size)
    3.  Else char exists:
           remove character at left pointer from set, shift left ++ (shift window)
    4.  Repeat until right index and left index have reached length of input.
    
    Example: pwwkew  or goodyo
    
    1. right & left point to p: max = 0; set = []
    
    2. p doesn't exist in HashSet, so add p to hashset: set = [p], left -> p ( left pointer unchange) , shift right -> w ,update max = 1 ( int max = right - left)
    
    3. w doesn't exist in HashSet, so add w to hashset: set = [p,w], left -> p (unchange), shift right -> 2nd w, update max = 2    
    pww
    L R
    
    4. When right pointer is at 2nd w: because w is already existed, so remove p from Set: set = [w] , max = 2(no update), shift left -> w, right -> w (unchange). Now, window has ww. 
    ww
    LR
    
    5. right still-> 2nd w,  w already existed, remove w from Set: set[], windown has ww that is not satisfy, so shift window: shift left -> 2nd w, max = 2  (no update)
    
    6. right still-> 2nd w, Set is empty, so add w to set: set = [w] , shift right -> k , max = 2 ( no update because right - left = 3 -2 = 1, Math.max (2,1) = 2)
    
    7. right pointer --> k, k doesn't exist in HS: Add k to Set: set = [w,k] , shift right -> e (index = 4), left -> 2nd w (unchange), max = 2 (no update)
        
    8. right pointer --> e, e doesn't exist in HS: Add e to Set: set = [e,w,k] shift right -> w (index = 5), left -> 2nd w (unchange), max = 3 ( Math.max(max, 3) = 3)
    
    9. right pointer --> w, w already exist in HS: remove character at left pointer(index = 2), so set = [e,k], shift left to k (index = 3)
    
    10. right pointer still --> w, but w is not in HS: add w to Set: set = [e,w,k], right point to index 6 that is out of length.
     
        
     Example:    abcabcbb
                 L  , max = 0
                 R
                 
        Right point to a and Shift R to next char a, b ,c. Add char to HashSet,so HS = [abc] update max = 1->2->3,  max =3
        abc , max =3
        L R
        
        Shift right pointer -> a, a already exist in HS: remove 'a'from HS  at left pointer , so HS = [bc], shift left pointer to b ( index = 1)
        bca  , max = 3
        L R
        
        Shift right point to b, b already exist in HS: remove b from HS  at left pointer, so HS = [c], shift left pointer to c
        cab  ,  max = 3
        L R 
        
        cabc   Shift right point to c, c already exist in HS, remove c at left pointer from HS, so HS = [], shift left pointer to a
         L R  , max = 3
         
        Shift right point to b, Add b to HS, so HS = [b] , left point unchange
          abcb       , max = 3     
          L  R
         
        Shift right point to b, b already exist in HS, remove b from HS at left pointer, so HS = [], shift left pointer to b
          bcbb         
          L R
          
        Shift right point to b, Add b to HS, so HS = [b]. Right pointer reachs at the length, so exit .
          bcbb          
           L R
    */
	
    public static int lengthOfLongestSubstring(String s) {
    	if (s == null || s.length() == 0)
             return 0;
       
        Set<Character> set = new HashSet<>(); // to store char abc
        int left = 0, right = 0;   // 2 pointers
        
        int max = 0;
        int len = s.length(); //6
        
        while (right < len ) {
        	
        	char c = s.charAt(right); // use right pointer 0, 1...
            
        	//  2. if set doesn't contain char, then add char. - expand window
            if (!set.contains(c)) { 
            	set.add(c);   //[a], [a,b],[a,b,c] , c is not added because c already in HashSet
                right++;        // move to right index ++, 
                
                max = Math.max(max, right - left); // update max 1, 2, 3
            }
            else {     // 3 Shrink window - shift window because c already in HashSet and remove a character at left index
                set.remove(s.charAt(left)); // remove a from HashSet by using left index 
                left++;                     // shift left pointer ++
            }
        }
        return max;
    }
      

	public static int lengthOfLongestSubstring2(String s) {
       
        if (s.isEmpty() || s == null)
            return 0;
       
        int len = s.length();
       
        char[] c = s.toCharArray(); // convert to char array 
       
        int start = 0;
        int end = 1;
        int max = 1;
        
        while ( end < len) {
            
            int index = s.lastIndexOf(c[end], end - 1); // at index (1,0)
           
            if (index == -1 || index < start) {
                if (max < end - start + 1)  // 1 < 2
                    max = end - start + 1;  //2
                end++;
            } else   {  
                start = index + 1;
                end++;
            }
        }
        return max;
    }
	
	
	public static void main(String[] args) {
	  // String s = "abcabcbb";
	   String s = "pwwke";
	   System.out.println("Length: " + lengthOfLongestSubstring(s)); //3
	   	  
	   System.out.println("Length: " + lengthOfLongestSubstring2(s)); //3
	
/*	   String s2 = "leapleap";
	   System.out.println("Length of word: " + lengthOfLongestSubstring(s2)); //4

	   System.out.println("Length of word: " + lengthOfLongestSubstring2(s2)); //4
	   
	   */
	}

}
	
	