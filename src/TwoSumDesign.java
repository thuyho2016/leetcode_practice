import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
170. Two Sum III - Data structure design
https://leetcode.com/problems/two-sum-iii-data-structure-design/
 
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

Example 1:

add(1); add(3); add(5);
find(4) -> true
find(7) -> false
Example 2:

add(3); add(1); add(2);
find(3) -> true
find(6) -> false

 */
public class TwoSumDesign {
	
	private ArrayList<Integer> arr = new ArrayList<Integer>();
	/** Initialize your data structure here. */
    public TwoSumDesign() {
       arr = new ArrayList<Integer>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        arr.add(number);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    /* sort array = [-1, 0, 1, 2]
     *                i        j
     * sum = num1 + num 2
     * if sum == target, return true
     * if sum < target, then i++
     * if sum > target, then j--
     */
    public boolean find(int value) {
        Collections.sort(arr);
        
        int i = 0;
        int j = arr.size() -1;
        
        while ( i < j) {
        	
        	int sum = arr.get(i) + arr.get(j);
        	if (sum == value) {
        		return true;
        	} else if ( sum < value) {
        		i++;
        	} else {
        		j--;
        	}	
        }
        return false;
    }
	
	public static void main (String[] args)
    {		
		TwoSumDesign obj = new TwoSumDesign();
		obj.add(1);
		obj.add(3);
		obj.add(5);
		
		boolean found = obj.find(4);
		System.out.println(found); //true
		
		found = obj.find(7);
		System.out.println(found); //false
	
    }
}
