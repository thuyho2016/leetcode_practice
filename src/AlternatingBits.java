/*
 * https://leetcode.com/problems/binary-number-with-alternating-bits/
 * 693. Binary Number with Alternating Bits
 * 
 * Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.

Example 1:
Input: 5
Output: True
Explanation:
The binary representation of 5 is: 101 (= 2 ^1 + 2^0 + 2^1)  Bit is alternated 1, 0, 1

Example 2:
Input: 7
Output: False
Explanation:
The binary representation of 7 is: 111 (= 2^2 + 2^1 + 2^0)

Example 3:
Input: 11
Output: False
Explanation:
The binary representation of 11 is: 1011.

Example 4:
Input: 10
Output: True
Explanation:
The binary representation of 10 is: 1010.
 
Solution: 
- check number is even or odd, use n % 2
- shift number: n >> 1 
- compare current and last. If numbers are different, then keep continuing.
if find 2 numbers are the same like 11, return false.
 */


public class AlternatingBits {
	
	//Prefer this solution
	public static boolean hasAlternatingBits(int n) {
		int last = n % 2;  //get the last bit
		n>>=1;
		
		while( n > 0) {
			int current = n % 2;
			if (current == last) {  //- check that no two adjacent digits are the same.
				return false;
			}
			
			last = current;
			n >>=1;
		}
		return true;
	}
	
	public static boolean hasAlternatingBits2(int n) {
		int cur = n % 2;  //1
        n /= 2;  //2
        
        while (n > 0) {
            if (cur == n % 2) return false;
            cur = n % 2; //2 % 2 = 0
            n /= 2;  //1
        }
  
		return true;
	}
	
	public static void main (String[] args) {
		System.out.println(hasAlternatingBits2(5)); // true
		System.out.println(hasAlternatingBits(7)); // false
		
	}

}
