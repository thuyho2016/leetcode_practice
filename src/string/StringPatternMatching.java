package string;


/*
 * Uber interview
 * 
The matching should cover the entire input string (not partial).
The function prototype should be:

bool isMatch(String str, String patter)
Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa","a{1,3}") → true
isMatch("aaa","a{1,3}") → false
isMatch("ab","a{1,3}b{1,3}") → true
isMatch("abc","a{1,3}b{1,3}c") → true
isMatch("abbc","a{1,3}b{1,2}c") → false
isMatch("acbac","a{1,3}b{1,3}c") → false
isMatch("abcc","a{1,3}b{1,3}cc{1,3}") → true


Similar with 44. Wildcard Matching
 https://leetcode.com/problems/wildcard-matching/description/

*/

public class StringPatternMatching {
	public static boolean isMatch(String text, String pattern) {
        return isMatch(text, 0, pattern, 0, new Boolean[text.length()][pattern.length()]);
    }

    private static boolean isMatch(String text, int t, String pattern, int p, Boolean[][] memo) {
        if (t >= text.length() && p >= pattern.length()) return true;
        if (t >= text.length() || p >= pattern.length()) return false;

        if (memo[t][p] != null) return memo[t][p];

        boolean match = false;

        if (p + 1 < pattern.length() && pattern.charAt(p + 1) == '{') {
            int end = pattern.indexOf('}', p + 2);
            int count = getCount(pattern.substring(p + 2, end));

            for (int i = t; --count >= 0 && text.charAt(t) == text.charAt(i); i++) {
                if (isMatch(text, i + 1, pattern, end + 1, memo)) return true;
            }
        } else if (text.charAt(t) == pattern.charAt(p)) {
            match = isMatch(text, t + 1, pattern, p + 1, memo);
        }

        return memo[t][p] = match;
    }

    // "1,3" -> 3 - 1 = 2
    private static int getCount(String range) {
        int comma = range.indexOf(',');
        int start = Integer.parseInt(range.substring(0, comma));
        int end = Integer.parseInt(range.substring(comma + 1));
        return end - start;
    }

	
	 
	public static void main (String[] args)
    {
		String s = "dog cat cat dog";  
		String pattern = "abba";
//		System.out.println("Match pattern ? " + wordPattern(pattern, s)); //true
		
		String pattern2 = "abab";
		String s2 = "redblueredblue";  
		System.out.println("Match pattern ? " + isMatch(pattern2, s2)); //false
    }
}
