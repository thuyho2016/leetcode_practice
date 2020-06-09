
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/* 
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *    1  --------> 0 power  0th
 *   1 1  -------> 1st power 
 *  1 2 1  ------> 2nd power = a^2 + 2ab + b^2
 * 1 3 3 1 ------>  3rd power
 
   Input: 3
   Output: [1,3,3,1]
   
  Use only O(k) extra space
 */

public class PascalTriangle {
    
 /*
	i = 1, j = 1, result is : 1 1 0 0
	i = 2, j = 2, result is : 1 1 1 0
	i = 2, j = 1, result is : 1 2 1 0
	i = 3, j = 3, result is : 1 2 1 1
	i = 3, j = 2, result is : 1 2 3 1
	i = 3, j = 1, result is : 1 3 3 1
*/
	
	//Prefer version1
    public static int[] getRow(int rowIndex) { 
        // rowIndex start from 0
        int size = rowIndex+1;
        
    	int[] result = new int[size];
    	result[0] = 1; //[1,0,0,0]
    	
    	for (int i = 1; i < rowIndex + 1; i++) {
			for (int j = i; j > 0; j--) {
				result[j] +=  result[j-1];  //result[j] = result[j] + result[j-1]
			}
    	}
    	return result;
    }
    
	//Version 2
    public static List<Integer> getRow2(int rowIndex) 
    {
    	List<Integer> l = new ArrayList<>(); //create a Array List
    	l.add(1); // add 1 for fist row
    	
    	for (int i = 1; i <= rowIndex; i++) {
    		List<Integer> row = new ArrayList<>();   //temporary List
    		row.add(1);
    		
    		for (int j = 1; j < i; j++) {
    			row.add( l.get(j) + l.get(j-1) ); // row = [1, 2] , next round row = [1,3,3]
    		}
    		row.add(1);
    		l = row;
    	}
        return l;
    }

    public static void main (String args[]) {
	    int row = 3;  //Output: [1,3,3,1] 
	    
	    int[] res = getRow(row);

	    //put to List to print out [1,3,3,1]
	    List<Integer> l = new ArrayList<>();
	    for (int i = 0; i < res.length; i++) {
	    	l.add(res[i]);
	    }
	    System.out.println(l);
	    
	    List<Integer> list = getRow2(row);
	    System.out.println(list);
    }
}


