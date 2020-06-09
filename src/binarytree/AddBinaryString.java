
package binarytree;

/* 67. Add Binary
https://leetcode.com/problems/add-binary/

Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"

Time complexity: O(max(N,M)), where NN and MM are lengths of the input strings a and b.

Space complexity: )O(max(N,M)) to keep the answer.

 */

public class AddBinaryString {		
	
	public static String addBinary(String a, String b) {
		if (a == null) return b;
		if (b == null) return a;
		
		StringBuilder sb = new StringBuilder();
		int lenA = a.length() - 1;
		int lenB = b.length() - 1;
		int carry =0;
		
		while ( lenA >= 0 ||  lenB >= 0) {
			int sum = carry;
			
			if ( lenA >= 0) {
				sum += a.charAt(lenA--) - '0';
			}
			
			if (lenB >= 0) {
				sum += b.charAt(lenB--) - '0';
			}
			
			//
			sb.insert(0, sum % 2);
			carry = sum / 2;
		}
		
		if ( carry > 0) {
			sb.insert(0, 1); // case 11 + 1
		}
		return sb.toString();
	
	}

	public static void main(String[] args) {
		
		System.out.println(addBinary("11", "1")); // 100
		System.out.println(addBinary("1010", "1011")); // 10101
				
    }
}