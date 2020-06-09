/* 
866. Prime Palindrome
https://leetcode.com/problems/prime-palindrome/


Find the smallest prime palindrome greater than or equal to N.

Recall that a number is prime if it's only divisors are 1 and itself, and it is greater than 1. 

For example, 2,3,5,7,11 and 13 are primes.

Recall that a number is a palindrome if it reads the same from left to right as it does from right to left. 

For example, 12321 is a palindrome.
 

Input: 6
Output: 7


Input: 8
Output: 11  > 8


Input: 13
Output: 101

*/

public class PrimePalindrome {
	
	public static int primePalindrome(int N) {
		int even = 1, odd = 1;
		int cur = 0;
		
		
		while (cur < N || !isPrime(cur)) {
			int nextEven = Integer.valueOf(nextPalin(Integer.toString(even), true));
			int nextOdd =  Integer.valueOf(nextPalin(Integer.toString(odd), false));
			
			cur = Math.min( nextEven, nextOdd );
			if (nextEven > nextOdd) {
				++odd;
			}
			else {
				++even;
			}
		}
		return cur;
	}
	
	private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
      
        		
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
	
	private static String nextPalin(String halfString, boolean t) {
		StringBuilder sb= new StringBuilder();
		
		if (t) { //even
			sb.append(halfString.substring(0, halfString.length()));
        	sb.reverse();
			return  halfString + sb; 
		}
		else { //odd
			sb.append(halfString.substring(0, halfString.length() - 1));
        	sb.reverse();
			return  halfString + sb; 
		}
			
	}
	
	
	public static void main (String[] agrs) {	
		
		System.out.println("Prime Palindrome: " + primePalindrome(6)); //7
		System.out.println("Prime Palindrome: " + primePalindrome(8)); //11
		System.out.println("Prime Palindrome: " + primePalindrome(13)); //101
		System.out.println("Prime Palindrome: " + primePalindrome(930)); //10301
	}
}
