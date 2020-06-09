import java.util.PriorityQueue;

public class DecreasingArray {

	
	public static int DecreasingArray(int a[], int n) 
    { 
        int sum = 0, dif = 0; 
  
        PriorityQueue<Integer> pq = new PriorityQueue<>(); 
  
        // Here in the loop we will 
        // check that whether the upcoming 
        // element of array is less than top 
        // of priority queue. If yes then we calculate the difference. After that we will remove that element and push the current element in queue. 
        //And the sum is incremented by the value of difference 
        for (int i = 0; i < n; i++) 
        { 
            if (!pq.isEmpty() && pq.element() < a[i]) 
            { 
                dif = a[i] - pq.element(); 
                sum += dif; 
                pq.remove(); 
                pq.add(a[i]); 
            } 
            pq.add(a[i]); 
        } 
      
        return sum; 
    } 
  
    // Driver Code 
    public static void main(String[] args) 
    { 
          
        int[] a = {0,1,2,5,6,5,7}; 
          
        int n = a.length; 
  
        System.out.println(DecreasingArray(a, n));  //15
    } 
}
