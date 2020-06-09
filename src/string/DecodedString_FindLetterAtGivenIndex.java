package string;

/*  
 * 880. Decoded String at Index
 * https://leetcode.com/problems/decoded-string-at-index/
 An encoded string S is given.  To find and write the decoded string to a tape, the encoded string is read one character at a time and the following steps are taken:

If the character read is a letter, that letter is written onto the tape.
If the character read is a digit (say d), the entire current tape is repeatedly written d-1 more times in total.
Now for some encoded string S, and an index K, find and return the K-th letter (1 indexed) in the decoded string.

 
Example 1:

Input: S = "leet2code3", K = 10
Output: "o"

Explanation: 
The decoded string is "leetleetcodeleetleetcodeleetleetcode".
The 10th letter in the string is "o".

Example 2:

Input: S = "ha22", K = 5
Output: "h"

Explanation: 
The decoded string is "hahahaha".  The 5th letter is "h".

Example 3:

Input: S = "a2345678999999999999999", K = 1
Output: "a"

Explanation: 
The decoded string is "a" repeated 8301530446056247680 times.  The 1st letter is "a".
    
  
size of word 'leet' = 4. If we see a digit S[i] = 2, it mean sized of decoded string 4 * s[i]
Whenever the decoded string would equal some word repeated d times, we can reduce K to K % (word.length)
 */

public class DecodedString_FindLetterAtGivenIndex {
	
	//uncompress String
	public static String decodeAtIndex2(String s, int K) {
		char[] c = s.toCharArray();
		long cur = 0;
		long k = K;
		
		String result = null;
		
		for (int i = 0; i < c.length; i++) {
			System.out.println(c[i]);
			if (c[i] > '9') {
				cur++;
				result = "" + c[i]; //l
			} else {
				
				int diff = c[i] - '0';
				System.out.println("c[]i] " + c[i] + " Diff: " + diff);
				
				if ( k < diff * cur) {
					k = k % cur;
					cur = 0;
					i = -1;
					
				} else {
					System.out.println("cur: " + cur);
					cur = diff * cur;
				}
			}
			if ( k == 0 || cur == k ) break;
		}
		return result;
		
	}
	// Solution is Easy to understand		
	public static String decodeAtIndex(String s, int K) {
		char[] chars = s.toCharArray();
		long len = 0;
		long[] lens = new long[chars.length]; //  has size 10
		
	
		// go through each character in String s, to store length of word leet2code3  
		for ( int i = 0; i < chars.length; i++) {
			char c = chars[i]; 
			
			// if char is letter a -> z, len ++
			//when char is digit, leet has len 4 -> total len =  4 * 2 = 8 
			if ( c >= 'a' && c <= 'z') {
				len++;
			} else {  //when char is numeric, 
				int num = Character.getNumericValue(c); // 2
				len = len * num;   //total len =  4 * 2 = 8 , 12 * 3 = 36
			}
			
			//store index and total len to new array which has same size with string s
			lens[i] = len; //[1,2,3,4, 8 ,9, 10,11,12, 36]
			
		}
		
		//[l, e, e, t, 2, c, o, d, e, 3]
		for (int i = chars.length - 1; i >= 0; i--) { // start from i = 9, 8, ....0
			char c = chars[i]; //c = 3 , e , d, o = char[6]
			
			if ( c >= 'a' && c <= 'z') { //e
				if ( lens[i] == K) {  //  lens[8] = 12, lens[7] = 11, lens[6] = 10
					return Character.toString(c); 		// return char c = chars[2] 			
				}
			} else { // 3 is numberic
				
				K = (int) (K % lens[i - 1]); // 10 % lens[8] = 10 % 12  = 10
				if  (K == 0) {
					int j = i - 1;
					while (!(chars[j] >= 'a' && chars[j] <= 'z')) {
						j--;
					}
					return Character.toString(chars[j]);
				}
			}
		}
		return Character.toString(chars[0]);
	}
	
	
	public static void main(String[] args) {
	      
	    String str = "leet2code3"; // len of leet = 4 -> 4 * 2 = 8 , so leetleet 
	    int k = 10;
	    System.out.println("Output: " + decodeAtIndex(str, k));  //o
	    
	    String str2 = "ha21"; //haha
	    int k2 = 5;
	    System.out.println("Output: " + decodeAtIndex(str2, k2));  //a
	    
	}
}
	
	