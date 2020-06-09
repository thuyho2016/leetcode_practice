package thread;
public class ThreadSample_Runnable implements Runnable {
	
	 public void run() {
		 System.out.println("MyClass running");
	} 
	 
	 public static void main(String args[]) {
		 Thread t1 = new Thread(new ThreadSample_Runnable());
		 t1.start();
	 }
}




