import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/* 957. Prison Cells After N Days
 * https://leetcode.com/problems/prison-cells-after-n-days/
 * 
There are 8 prison cells in a row, and each cell is either occupied or vacant.

Each day, whether the cell is occupied or vacant changes according to the following rules:

If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
Otherwise, it becomes vacant.

(Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)

We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.

Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)

 

Example 1:

Input: cells = [0,1,0,1,1,0,0,1], N = 7
Output: [0,0,1,1,0,0,0,0]

Explanation: 

The following table summarizes the state of the prison on each day:
Day 0: [0, 1, 0, 1, 1, 0, 0, 1]

Day 1: [0, 1, 1, 0, 0, 0, 0, 0]

Day 2: [0, 0, 0, 0, 1, 1, 1, 0]

Day 3: [0, 1, 1, 0, 0, 1, 0, 0]

Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
Day 7: [0, 0, 1, 1, 0, 0, 0, 0]


Example 2:

Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
Output: [0,0,1,1,1,1,1,0]


Amazon Test - 

 * 1 - active cell
 * 0 - inactive
 * if neighbors on both sides of a cell are either active or inactive, the cell become active in next day

 
 Solution:
 
  For each day, we will decrement N, the number of days remaining, and transform the state of the prison forward (state -> nextDay(state)).
For example:  
  N = 7 and K = 3
Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
Day 3: [0, 1, 1, 0, 0, 1, 0, 0]

Then, we can just directly return the 7%3, which is Day 1 array in the cycle table.
  
 */
public class PrisonCellsAfterNdays {

	 
	 public static int[] prisonAfterNDays(int[] cells, int N) {
	 	int count = N;
        int[] initState = null;
        int days = 0;
        String key = null;
        
        while(N > 0){ 
        	
            int[] temp = cells.clone();
            StringBuilder sb= new StringBuilder();
           
            // Loop to find next state of array.
            for(int i= 0; i < temp.length; i++){
                if(i == 0 || i == temp.length-1){
                    if(temp[i] == 1) 
                    	cells[i] = 0;
                    
                } else {
                	
                	// if neighbors are the same, set the cell = 1. if cell has two adjacent neighbors that has both occupied or both vacant, then the cell becomes occupied.
                    if((temp[i-1] == 0 && temp[i+1] == 0) || (temp[i-1] == 1 && temp[i+1] ==1)){
                        cells[i] = 1;
                        
                    } else{  //Otherwise, it becomes vacant.
                        cells[i] = 0;
                    } 
                    
                    sb.append(String.valueOf(cells[i]));
                }
            }
            
            
            // Reduce iteration if see number again.
            if(key != null && key.equals(sb.toString())){
                N--;
                N = N % days;  //align with N % K index lookup
                continue;
            }
            
           // Set up initial state to set the state and key
            if(initState == null){
                initState = cells.clone();
                key = sb.toString();
            }
            
            days++;
            N--;
        }
        
        
        return cells;
 	} 
	 
	 
	 /**
	   a -> b, b -> c, c->a
	  a
	  b
	  c
	  a
	  b
	  c
	  a  , after 15 iterations, 15 mod 3
	  

	  */
	 public static int[] prisonAfterNDays2(int[] cells, int N) {
         
        HashSet<String> set = new HashSet<>();
        int size = 0;
        boolean flag = false;
        
        for (int i = 0; i < N; i++) {
        	int[] nextDayValues = nextDay(cells);   //[0, 0, 0, 0, 0, 0, 0, 0]
        	String s = Arrays.toString(nextDayValues);
        	
        	if (!set.contains(s)) {
        		set.add(s);
        		size++;        		
        	} 
        	else {
        		flag = true;
        		break;
        	}
        	cells = nextDayValues;
        }
        
        if (flag) {
        	N = N % size;
        	for (int i = 1; i <= N; i++) {
        		cells = nextDay(cells);
        	}
        }
        return cells;
     
 	}  
	 
	 private static int[] nextDay(int[] cells) {
		 int[] result = new int[cells.length];
		 
		 for(int i = 1; i < cells.length - 1; i++){
			// if neighbors are the same (occupied or vacant), set the cell = 1. 
			 if(cells[i-1] == cells[i+1]) {
	             cells[i] = 1;
	         } 
		 }
		 return result;
	 }
         
		// Amazon test
	 public static List<Integer> cellCompete(int[] states, int days){
		return null;
	 
	 }
	 
	 
	public static void main (String[] args)
    {
		 
		int[] cells = {0,1,0,1,1,0,0,1};
		System.out.println(Arrays.toString(prisonAfterNDays(cells, 7)));  // 8 cells, after 7 days,  cells will be {0,0,1,1,0,0,0,0}
		
		int[] cells2 = {0,1,0,1,1,0,0,1};
		System.out.println(Arrays.toString(prisonAfterNDays2(cells2, 7))); //wrong output
		
		int[] input = {1, 0, 0, 0, 0, 1, 0, 0};
		
	//	 System.out.println(cellCompete(input, 1));  // {0,1,0,0,1,0,1,0}
		 
		 int[] input2 = {1, 1, 1, 0, 1, 1, 1, 1};
			
		 System.out.println(cellCompete(input2, 2));  // {0,0,0,0,0,1,1,1,0}
    }
}
