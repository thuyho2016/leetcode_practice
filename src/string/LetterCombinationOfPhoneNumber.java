package string;

import java.util.LinkedList;
import java.util.List;

/*
 * 17. Letter Combinations of a Phone Number
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * 
 
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Time complexity :  O(3^N x 4^M) where N is the number of digits in the input that maps to 3 letters (e.g. 2, 3, 4, 5, 6, 8) and M is the number of digits in the input that maps to 4 letters (e.g. 7, 9), 
and N+M is the total number digits in the input.

Space complexity :  O(3^N x 4^M)since one has to keep 3^N \times 4^M3 solutions.
  
 */
public class LetterCombinationOfPhoneNumber {
	
	//BFS
	public static List<String> letterCombinations(String digits) {
        List<String> list = new LinkedList<>();
        
        if(digits == null || digits.length() == 0) return list;
        
        list.add("");
        char[][] map = {{},{},{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};
        
        for(int i = 0;i < digits.length();i++) {
        	
            List<String> nextList = new LinkedList<>();
            int num = digits.charAt(i) - '0'; // convert character to number 2, 3
            
            for(String s: list){
                for(int k = 0; k < map[num].length; k++){
                    nextList.add(s + map[num][k]);
                }
            }
            list = nextList;
        }
        return list;
    }

	public static void main(String[] args) {
		String digits = "23";
		System.out.println(letterCombinations(digits));
		
	}
}
