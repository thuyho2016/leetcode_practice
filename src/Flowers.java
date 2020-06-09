
/*
605. Can Place Flowers  (Easy level)

https://leetcode.com/problems/can-place-flowers/
 
Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots 
- they would compete for water and both would die.

Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, 
return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: True

Example 2:
Input: flowerbed = [1,0,0,0,1], n = 2
Output: False

 
 */

public class Flowers {
	/**
	Solution:
	
	Scan flowerbed elements from left to right, start from 0 to last one.
	
	if flowerbed[i] is 1
	   set zeroFlag = false;
	else if flowerbed[i] is 0, in this case, we need check the zero flag and its next number
	
	if zeroFlag is false( prev number is not zero), set zeroFlag = true;
	
	if zeroFlag is true(prev number is zero), we then check together with next element (flowerbed[i+1])
	  • if next number is 0, then we increase count by 1, and move the index
	       (no need to reset zeroFlag, since next one is zero)
	  • if next number is 1, we need set zeroFlag=false, and move the index.
	
	Note: since we already check the next number flowerbed[i+1]: for both a and b, so we skip further check of flowerbed[i+1] by increase i by 1 here;
	
	How to handle two edge cases, flowerbed[0] and flowerbed[flowerbed.length-1], which has only one neighbour
	
	Actually, just need to treat those non-exist neighbours as zero, so
	for the first number: flowerbed[0], we set initial value of zeroFlag = true;
	for the last number if( i== (flowerbed.length-1) || flowerbed[i+1]==0)

	 */
	public static boolean canPlaceFlowers(int[] flowerbed, int n) {
		int count = 0;
        boolean zeroFlag = true;
        
        for(int i = 0; i < flowerbed.length; i++){
        	
            if(flowerbed[i] == 1){
                zeroFlag =false;
                
            }else {   // == 0
            	
                if(zeroFlag == false) {
                    zeroFlag=true;
                    
                }else{
                    if(i == (flowerbed.length-1) || flowerbed[i+1] == 0){ //for last number or next number flowerbed[i+1]
                        count++;
                    }else{
                        zeroFlag=false;                 
                    }
                    i++;
                }
                //if(count >= n) return true;
            }            
        }        
        return count >= n;       
	}
	
	public static void main (String[] args)
    {
		int[] nums = new int[] {1,0,0,0,1};
		
		boolean result = canPlaceFlowers(nums,1);
		System.out.println(result);
	
    }
}
