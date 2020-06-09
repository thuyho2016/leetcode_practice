import java.util.HashSet;

/*
Given A of N integer, return the smallest positive interger ( greater than 0) that does not occur in A

For example 

int[] A = {1,3, 6,4,1,2}; Return 5

int[] A2 = new int[] {1,2, 3} , return 4

int[] A3 = new int[] {-1,-3}, return 1

 */
public class SmallestPositiveNumber {
	
	public static int solution(int[] A) {
        // write your code in Java SE 8
        int m = 1; // default smallest positive number
        
        HashSet<Integer> map = new HashSet<Integer>();
        
        for (int i = 0; i < A.length; i++) {
            if ( m < A[i]) {
                map.add(A[i]);
            }
            else if (m == A[i]) {
                m = m + 1; //increment m when it is equal to current elment
                while ( map.contains(m)) {
                    map.remove(m);
                    m = m + 1; //increment m when it is one of the element of the set 
                }
            }
        }
        return m;
    }

	
	
	public static void main (String[] args)
    {
		int[] A = new int[] {1,3, 6,4,1,2};
		
//		int result = solution(A);
//		System.out.println(result); //5
		
		int[] A2 = new int[] {1,2, 3};
		System.out.println(solution(A2));  //4
				
		int[] A3 = new int[] {-1,-3};
		System.out.println(solution(A3));  //1
	
    }
}
