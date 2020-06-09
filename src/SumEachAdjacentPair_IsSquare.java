import java.util.Arrays;

/* Apple Interview

input: list contains number from 1 to n
output should be a re-arranged list, with the format like this [1,3,6,10,15...]
which has the following capability:
a). the sum of each adjacent pair should be a valid perfect square, like 1+3=2^2, 3+6=3^2, 6+10=4^2, 10+15=5^2, etc.

Input: [1, 3, 6, 10, 15, 21, 28, 36, 45, 55]
Output: [4, 9, 16, 25, 36, 49, 64, 81, 100]

Consider the graph whose nodes are {1,…,15} 
in which has two nodes are connected whenever their sum is a square

 */

public class SumEachAdjacentPair_IsSquare {
   

    public static int[] isSquare(int[] num) {
    
      
      
	}
    
  

    
   public static void main(String[] args) {
	   int[] nums = {1,3,6,10,15};
	   int[] out =  isSquare(nums);
	   System.out.println(Arrays.toString(out));
   	
   
   }
}
	
	