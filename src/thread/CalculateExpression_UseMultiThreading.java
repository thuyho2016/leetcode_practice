package thread;

/** 
 * Use Java multi-threading to calculate the expression 1 * 2 / (1+2) 
 * 
 */

// Thread Class for Add operation
class Add implements Runnable {

      private int num1;
      private int num2;
      public int sum;
      
   public Add (int num1, int num2) {
         	this.num1 = num1;
         	this.num2 = num2;
   }
   
   public void run() {
       sum = num1 + num2;
   } 

}

//Thread Class for Mul operation
class Mul extends Thread {

   private int num1;
   private int num2;
   int result;
   
	public Mul (int num1, int num2) {
	      	this.num1 = num1;
	      	this.num2 = num2;
	}
	
	public void run() {
	    result = num1 * num2;
	} 

}



public class CalculateExpression_UseMultiThreading {
   
   public static void main (String[] args) {
              //1 * 2 / (1+2) 
		int num1 = 1;
		int num2 = 2;
		
		Add addObj = new Add(num1, num2);
		
		Thread t1 = new Thread(addObj);
	    t1.start();
	   	
	   	Mul t2 = new Mul(num1, num2);
	    t2.start();
	    
	    try {
	    	t1.join();
	    	t2.join();
	    	
	    } catch (InterruptedException e) {
	    	e.printStackTrace();
	    }
	    
	    double n = ( (double) t2.result / addObj.sum);
	    System.out.println(n);	
            
   }
}

