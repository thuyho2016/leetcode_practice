package thread;
public class ThreadSample_ExtendThread extends Thread  {
	
	 public void run() {
		 System.out.println("MyClass is using extends Thread");
	} 
	 
	 public static void main(String args[]) {
		 ThreadSample_ExtendThread  t1 = new ThreadSample_ExtendThread();
		 t1.start();
	 }
}




