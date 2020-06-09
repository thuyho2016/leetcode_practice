
/*
 * 374. Guess Number Higher or Lower
 * We are playing the Guess Game. The game is as follows: 
 * I pick a number from 1 to n. You have to guess which number I picked.

 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 *
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!

Example :

Input: n = 10, pick = 6
Output: 6

Time complexity : O(log_2 n) . Binary Search is used
Space complexity : O(1). No extra space is used. 
 */

public class GuessGameUsingBinarySearch{
	
	//Using Binary Search
	public static int guessNumber(int n) {
	    int start = 1, end = n;
	    
	    while(start  < end) {
	    	
	        int mid = start  + (end - start) / 2;
	        
	        if(guess(mid) == 0) {
	            return mid;
	        } else if(guess(mid) == 1) {
	        	start  = mid + 1;
	        } else { // guess(mid) == -1 
	        	end = mid - 1;
	        }
	    }
	    return start;
	}
	
	/** Solution 2
	 * @param num, your guess
	 *  @return -1 if my number is lower, 
	 *           1 if my number is higher,
	 *           otherwise return 0
	 */
	
	public static int guessNumber2(int n) { //input is guess number
		//use binary search  -- start 1 to n
		return binSearch(1, n);
	}
	
	//recursive
	public static int binSearch(int start, int end) {
		if(start > end) return -1;
	    
		if(guess(start)==0) return start;
	    if(guess(end)==0) return end;
	    
		int mid =start + (end - start) /2;
		
		if (guess(mid) == 0) return mid;
		else if (guess(mid) == -1) 
			return binSearch(start, mid);
		else {
			return binSearch( mid + 1, end);
		}
	}
	

	//??? return -1, it implies that the guessed number is larger than the required one
	public static int guess(int num) {
		
		return 0;
	}
		
	public static void main(String[] args) {
	  
	   System.out.println(guessNumber(10));
	   
	   System.out.println(guessNumber2(10));
	
	}
}
	
	