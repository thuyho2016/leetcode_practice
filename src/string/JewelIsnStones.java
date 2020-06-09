package string;

import java.util.HashSet;

/*
 *
You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  
Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

The letters in J are guaranteed distinct, and all characters in J and S are letters. 
Letters are case sensitive, so "a" is considered a different type of stone from "A".

Example 1:
Input: J = "aA", S = "aAAbbbb"
Output: 3 
Explaination: a appear 1 time in S, A appear 2 times in S. Total count = 3

Example 2:
Input: J = "z", S = "ZZ"
Output: 0
Note:

S and J will consist of letters and have length at most 50.
The characters in J are distinct.

Time Complexity O(n + m)
Space Complexity:O(m) m is length of J
 */
public class JewelIsnStones {
	
	public static int numJewelsInstones(String J, String S) {
		HashSet<Character> jewels = new HashSet<Character>();
		
		for (char c: J.toCharArray()) {
			jewels.add(c);
		}
		
		int numJewels = 0;
		
		for (char c: S.toCharArray()) {
			if (jewels.contains(c)) {
				numJewels++;
			}
		}
		
		return numJewels; // return counts;
		
	}
	
	public static void main(String[] args) {
		System.out.println(numJewelsInstones("aA", "aAAbbbb"));
		
		System.out.println(numJewelsInstones("z", "ZZ"));
	}
}
