import java.util.HashMap;

/* 13. Roman to Integer
 * https://leetcode.com/problems/roman-to-integer/

   Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

Example 1:

Input: "III"
Output: 3
Example 2:

Input: "IV"
Output: 4
Example 3:

Input: "IX"
Output: 9
Example 4:

Input: "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.

Solution:

If I compare current with next one, 
= nextOne, plus currentValue
< nextOne, minus currentValue

 */


public class RomanToInteger {

	//Prefer this solution: Read the string backwards (test 3 will work), then add the value of the character if it is greater than or equal to the previous character. 
	//Otherwise, subtract it.  Runtime: 3 ms, 
	public static int romanToInt(String s) {
    	int sum = 0;
    	int lastVal = 0; 
    	
    	for (int i = s.length() - 1; i >= 0; i--) {

    		int curVal = getValue(s.charAt(i));
    		
	    	if ( curVal >= lastVal) {  //IV
    			sum = sum + getValue(s.charAt(i));
    		} else {
    			sum = sum - getValue(s.charAt(i));
    		}
	    	
	    	lastVal = curVal;
    	}
    	
		return sum; 
    }
    
	private static int getValue(char c) {
    	switch (c) {
			case 'I': 	return 1;
			case 'V':	return 5;
			case 'X':	return 10;
			case 'L':	return 50;
			case 'C':	return 100;
			case 'D':	return 500;
			case 'M':	return 1000;
			default:	return 0;
    	}
	}
	
	/* hashmap
	//	Time & Space complexity : O(1).
	public static int romanToInt(String s) {
    	int sum = 0;
    	int lastVal = 0;  
	    HashMap<Character, Integer> romanVals = new HashMap<>();
        romanVals.put('I', 1);
        romanVals.put('V', 5);
        romanVals.put('X', 10);
        romanVals.put('L', 50);
        romanVals.put('C', 100);
        romanVals.put('D', 500);
        romanVals.put('M', 1000);
        
        int lastVal = 0, total = 0;
        
        char[] charArr = s.toCharArray();
        for (int i = charArr.length-1; i>=0; i--) {
            int curVal = romanVals.get(charArr[i]);
            
            if (curVal < lastVal) {
                total -= curVal;
            } else {
                total += curVal;
            }
            lastVal = curVal;
        }
        return total; 
	}
     */
	
	
	//FASTER and LESS memory usage
	 public static int romanToInt2(String s) {
	        
	        int sum = 0;
	        int length = s.length();
	        for(int i = 0; i < length; i++) {
	            System.out.print(sum + " ");
	            
	        	switch(s.charAt(i)){
	                case 'I':
	                    sum += (i < (length - 1) && (s.charAt(i+1) == 'X' | s.charAt(i+1) == 'V')) ? -1 : 1;
	                    break;
	                case 'V':
	                    sum += 5;
	                    break;
	                case 'X':
	                    sum += (i < (length - 1) && (s.charAt(i+1) == 'L' | s.charAt(i+1) == 'C')) ? -10 : 10;
	                    break;
	                case 'L':
	                    sum += 50;
	                    break;
	                case 'C':
	                    sum += (i < (length - 1) && (s.charAt(i+1) == 'D' | s.charAt(i+1) == 'M')) ? -100 : 100;
	                    break;
	                case 'D':
	                    sum += 500;
	                    break;
	                case 'M':
	                    sum += 1000;
	                    break;
	            }
	        }
	        return sum;
	    }

    public static void main (String[] agrs) {
    /*	String input = "III";
    
    	System.out.println(romanToInt(input));
		System.out.println(romanToInt2(input));*/
		
		String input2 = "LVIII";
		System.out.println(romanToInt(input2));
    }
}