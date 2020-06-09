
/*
you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. 
Implement a function to find the first bad version. You should minimize the number of calls to the API.

Example:
Given n = 5
call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true

Then 4 is the first bad version. 
*/

public class FirstBadVersion {
        
    public static void main(String[] args) {
    	int steps = firstBadVersion(5);
    	System.out.println("First bad version: " + steps);
    }
  

    public static int firstBadVersion(int n) {
        
        int left = 1;
        int right = n;
        
        while (left < right) {
        	
            int mid = left + (right - left) / 2;
            
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1; //move forward 1
            }
        }
        return left;
      
    }
    
    public static boolean isBadVersion(int ver) {
    	if (ver == 0) {
    		return false;
    	}
    	return true;
    }
}
	
	