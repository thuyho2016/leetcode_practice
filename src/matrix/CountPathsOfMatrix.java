package matrix;
/*
Count all possible paths from top left to bottom right of a mXn matrix

Input :  m = 2, n = 2;
Output : 2

There are two paths
(0, 0) -> (0, 1) -> (1, 1)
(0, 0) -> (1, 0) -> (1, 1)

Input :  m = 2, n = 3;
Output : 3
There are three paths
(0, 0) -> (0, 1) -> (0, 2) -> (1, 2)
(0, 0) -> (0, 1) -> (1, 1) -> (1, 2)
(0, 0) -> (1, 0) -> (1, 1) -> (1, 2)
 */
public class CountPathsOfMatrix
{
	
	public static int numberOfPaths(int m, int n) 
    { 
        // If either given row number is first or 
        // given column number is first 
        if (m == 1 || n == 1) 
            return 1; 
  
        // If diagonal movements are allowed then the last addition is required. 
        return numberOfPaths(m - 1, n) + numberOfPaths(m, n - 1); 
       
    } 
  
    public static void main(String args[]) 
    { 
        System.out.println(numberOfPaths(3, 3)); //6
    } 
}
