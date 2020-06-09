import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees( upside down)
 The numbers "69", "88", and "818" are all strobogrammatic.
  
 Q1: 246. Strobogrammatic Number
	
	 Write a function to determine if a number is strobogrammatic.
	 Input:  "69"
	 Output: true
	 
	 Input:  "962"
	 Output: false
 
   	Step:  Use  sb.reverse() to reverse string
    

Q2: 247. Strobogrammatic Number II
	Find all strobogrammatic numbers that are of length = n.
	Given n = 2, return ["11","69","88","96"].

   	Steps: Use recursive or DFS

Q3: 248. Strobogrammatic Number III

	Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

	Input: low = "50", high = "100"
	Output: 3 
	Explanation: 69, 88, and 96 are three strobogrammatic numbers.

 */


public class StrobogrammaticNumber_DFS{
	//Q3: Version 2
	
    
	//Q3 - count the total strobogrammatic numbers in the range
	public static int strobogrammaticInRange(String low, String high) {
		
		int count = 0;
		int n = low.length();
		
		List<String> list = findStrobogrammatic2(n);
		
		for (String s:  list) {
			//  compare low = 50 < 69,88,96 , increase count
			//	if (Integer.parseInt(s) > Integer.parseInt(low) && Integer.parseInt(s) < Integer.parseInt(high))  
			if((low.length() < s.length() || low.compareTo(s) <= 0) && (high.length() > s.length() || high.compareTo(s) >= 0)){
				count++;
			}
		//	if (Integer.parseInt(s) > Integer.parseInt(low) && Integer.parseInt(s) < Integer.parseInt(high))   
		//		count ++;
		}
		
		return count;
    }
	
	 	 
	// Q2: Find all strobogrammatic numbers - O(2^n)
	// use Depth First Search - DFS 
	private static char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
	  
	public static List<String> findStrobogrammatic2(int n) {
	        List<String> res = new ArrayList<>();
	        dfs(new char[n], 0, n-1, res);  //left = 0, right starts at index n -1
	        return res;
	}
	    
	private static void dfs(char[] chars, int l, int r, List<String> res) {
	  
		if (l > r ) {
		String str = new String(chars);
        res.add(str);     // res.add(String.valueOf(arr));
        return;
      }
      
	  // r = 2 , arr[0] = 0, arr[2] =0, arr = [0, ,0]
      for (char[] pair : pairs) {
        char[] m = pair;//[0,0], [1,1],...
        
        chars[l] = pair[0]; //0, 1, 6, 8, 9
        chars[r] = pair[1]; //0, 1, 9, 8, 6
      
        if(l == r && pair[0] != pair[1]) continue;
      
        if (chars[0] =='0' && chars.length > 1) continue;// Ignore 0s at the first of the integer
        
        dfs (chars, l + 1, r - 1 , res);  //increase 1 for left, reduce 1 for right
      }
   }
	
	//Q2: Find all strobogrammatic numbers - O(2^n)
	// use recursive
	public static List<String> findStrobogrammatic(int n) {
	    return  helper(n,n);
  	}
	  
	// n = curLength, m = length
	public static List<String> helper(int n, int m) {
	    List<String> res = new ArrayList<>();
	    
	    if (n == 0) return new ArrayList<>(Arrays.asList(""));
	    if (n == 1) return res = Arrays.asList("0", "1", "8");
	    
	    // n >= 2 , start from 2 ...m
	    // if n = 2, start from 0 ...m
	    List<String> list = helper(n - 2, m); //n = 1, m = 3, list = [0,1,8]
	    
	    for (int i = 0; i < list.size(); i++) { // [0,1,8] 
	      String s = list.get(i);	
	      // so, s = 0, 1, 8
	      if (n != m) {		// n = 3, m = 3
	        res.add("0" + s + "0");
	      }
	      res.add("1" + s + "1");   // insert s=0, then s=1, s=8 
	      res.add("6" + s + "9");
	      res.add("8" + s + "8");
	      res.add("9" + s + "6");
	    }
	    return res;
	}
	
	// Q1: a function to determine if a number is strobogrammatic.
	// Step:  Use  sb.reverse() to reverse string
   public static boolean isStrobogrammatic(String s) {
	  if (s == null || s == "") return false;
	    
	  StringBuilder sb = new StringBuilder();
	    
	  for (int i = 0; i < s.length(); i++) {
	      char c = s.charAt(i);					// can create char[] arr = num.toCharArray(); 
	      if (c == '0' || c == '1' || c == '8') {
	        sb.append(c);
	      } else if (c == '6') {
	        sb.append('9');			//sb = 9
	      } else if ( c == '9') {
	        sb.append('6');	//sb = 96
	      } else {
	        return false;
	      }
	  }
	  String reversed = sb.reverse().toString(); //69
	    
	  if ( reversed.equalsIgnoreCase (s) ) {
	      return true;
	  }
	  return false;
	    
   }
	    
   public static void main(String[] args) {
	   String s = "69"; 
	   System.out.println(isStrobogrammatic(s)); //true
	   
	   String s2 = "88"; 
	   System.out.println(isStrobogrammatic(s2)); //true
	   
	   //Q2
	   System.out.println(findStrobogrammatic(2));  //[11, 69, 88, 96]
	   
	   System.out.println(findStrobogrammatic2(2));  //[11, 69, 88, 96]
	   
	   System.out.println(findStrobogrammatic(3)); //[101, 609, 808, 906, 111, 619, 818, 916, 181, 689, 888, 986]
		  
	   System.out.println(findStrobogrammatic2(3)); 
	   
	   //Q3
	   System.out.println("total in range: " + strobogrammaticInRange("50", "100")); //3
	   System.out.println("total in range: " + strobogrammaticInRange("100", "200")); //3
	   

	}
}
	
	