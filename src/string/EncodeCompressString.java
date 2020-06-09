package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Problem 1: Compress string. Input is string

Input : str = "aabbbc"
Output : a2b3c
 
 Time: O(n)
 Space: O(1)
 
 Problem2: Input is array
 
 * 443. String Compression (easy level)
https://leetcode.com/problems/string-compression/

Given an array of characters, compress it in-place. return the new length of the array.

Example 1:

Input:
["a","a","b","b","c","c","c"]

Output:
Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]


Solution:
I use two pointers i & j, and a counter.

If the chars[i] == chars[i-1], then we add up the counter by 1.
Otherwise, we have to compress the string with the counter.
2.1 If the counter == 1, it means we don't need to add the number in ans.
2.2 If the counter > 1, we add the counter as a string in ans.

["a","a", "a", "b","b","c","c","c"]
  i
  j -> shift j from index 0 to 1, to 2 when character a repeats
  so j - i will tell you how many times character occurs
  a 2 ( j - i = 2 - 0 = 2)
  when update i to index of b
  
  a 2 b 2 c 3
  
  Case: [a]  , j - i = 1 , answer = a 1 , but we need to display a
  so, make Condition j -i > 1,
  
  Case : [a, b, b, b, b, b, b, b, b, b, ]
  Output: [a, b, 11]  - b occurs 11 times so need "1" "2"
  
  
 Time O(n), Space O(1) 

 */

public class EncodeCompressString {
	
	//Prefer this solution - compress aabbbc -> a2b3c
	public static String compressString(String s) {
		if(s.length() < 2) {
			return s;
		}
		
		char[] chars = s.toCharArray();
		int count = 1;
	
		StringBuilder sb = new StringBuilder(); // for display output
		
		for(int i = 0; i < chars.length; i++) {
			if(i != s.length() - 1 && chars[i] == chars[i+1]) { // or if (...)
				count++;			
			} else {
				
				if (count != 1 ) { 
					sb.append(s.charAt(i));
					sb.append(count);
					count = 1; // Reset the counter
				} else { //count == 1,  don't display count 1 for only character a.  case: ab2c3
					sb.append(s.charAt(i));
				}
			}
		}
		return sb.toString();
	}

	
	//Using HashMap
	public static String compressString2(String input) {

        if (input == null || input.length() == 0) return null;
        char[] arr = input.toCharArray();
        
        Map<Character,Integer> map = new HashMap<>(); // /*count each number frequency*/
        
        
        for (int i =0 ; i < arr.length ; i ++) // Time : O(n)
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1 ); // count 1 when add new pair of key, value. increase 1 if number exists , {1=3, 2=2, 3=1} 
        
        //get list of all keys
        List<Character> list = new ArrayList<>(map.keySet());
        
        StringBuilder output=new StringBuilder();
        for (Character c :list ) {
        	output.append(c);
        	output.append(map.get(c));
        }
        return output.toString();
	}
	
	//443. String Compression
	 // Prefer this solution: 2 pointers - i and j point at index 0, shift j when character repeats
	//so j -i will tell you how many times character occurs
	public static int compress(char[] chars) {
	
		int index = 0;
		int i = 0;
		while ( i < chars.length) { // i = 6, don't go inside while
			int j = i;
			while ( j < chars.length && chars[j] == chars[i]) {
				j++; // j = 3, j = 5
			}
			
			chars[index++] = chars[i];   // index = 1, index = 3, index = 5
			if (j - i > 1 ) { // when j = 6 ( out of length) , i = 5 , j - i = 1, so dont go inside if condition
				String count = j - i + ""; // convert to string "3" , "2"
				for (char c : count.toCharArray()) {
					chars[index++] = c;  // [a, 3, a, b, b, c], increase index = 2; [a, 3, b, 2, b, c], increase index = 4
				}
			}
			i = j; // i = 3, i = 5, i = 6
		}
		
		return index; // index = 5
	}
	
	
	//return the new length. 
	public static int compress_length(char[] chars) {
        int count = 1;
        int j = 0;  // new len
                
        for (int i = 0; i < chars.length; i++) {
        	
            if (i != chars.length - 1 && chars[i] == chars[i + 1]) { // [a, a, a , then count = 3
                count++;
            } else {  // current character at i is different with next character at index i + 1
                chars[j++] = chars[i]; //j = 1, i = 2
                
                if (count != 1) {  // i = 4, j = 3, [a, 3, b, b, b, c]; i = j = 5, [a, 3, b, 2, c, c]
                	String str =  String.valueOf(count);  //"3", "2" 
                	// or String str = count + "";
                    for (int k = 0; k < str.length(); k++) { //j=2, j=3
                        chars[j++] = str.charAt(k); //[a, 3, a, b, b, c] , [a, 3, b, 2, b, c]
                    }  
                }
                count = 1; // reset count
            }
        }
        return j;
    }
	
	public static void main(String[] args) {
	      	   
	    String str = "aabbbc";
	    System.out.println(compressString(str));  //a2b3c
	    
	    String str3 = "aaabbc";
	    System.out.println(compressString2(str3));  //a3b2c1

	    
	    char[] arry = {'a','a','a','b','b','c'};
	    System.out.println(compress(arry));
	    
	   // String str2 = "aaabbc";
	    char[] arry2 = {'a','a','a','b','b','c'};
	    System.out.println("Length: " + compress_length(arry2));  //5 for case a3b2c 
	    
	}
  
}
	
	