import java.util.ArrayList;
import java.util.List;

/*
 * Find shift 0 to the end of array
 * arr = { 1,1,1,1,0,1,0,1}
 * Output = 3 
 */
public class MoveZerosToEnd_FindMinMoves {

	public static int minMoves(List<Integer> avg) 
    { 
		int j = 0;
		int count = 0;
		
        for(int i= 1; i < avg.size();i++) {
    	
            if(avg.get(i) != 0 && avg.get(j) == 0) {
        		count++;
        		swap(avg, i, j);
        		
        		int prev = j - 1;
        		
        		while ( prev > 0 ) {
	                if (avg.get(prev) == 0 && avg.get(j) == 1) {
	        			swap(avg, prev, j);
	        			count++;
	                }
	                prev--;
	                j--;
        		}
            }
            j = i;
    		
        }
     
        return count;
    } 
	
	private static void swap(List<Integer> nums, int i, int j){
        int temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }

    public static void main(String[] args) 
    { 
          
    	List<Integer> a = new ArrayList<Integer>(); 
         a.add(0);
         a.add(1);
         a.add(0);
         a.add(1);
  
        System.out.println(minMoves(a)); //3
        
        List<Integer> a2 = new ArrayList<Integer>(); 
        a2.add(1);
        a2.add(1);
        a2.add(0);
        a2.add(0);
       System.out.println(minMoves(a2)); //0
       

       List<Integer> a3 = new ArrayList<Integer>(); 
       a3.add(1);
       a3.add(1);
       a3.add(1);
       a3.add(1);
       
       a3.add(0);
       a3.add(1);
       a3.add(0);
       a3.add(1);
      System.out.println(minMoves(a3)); //3
      
    } 
}
