package thread;
/*
 * Thread creation by extending Thread class
 */

/*class ThreadDemo extends Thread{  
  public void run(){  
    System.out.println("My thread is in running state.");  
  }   
  public static void main(String args[]){  
     ThreadDemo obj=new ThreadDemo();   
     obj.start();  
  }  
}
*/

class CountThread extends Thread {
	
	public CountThread()
	{
		super("my extending thread");
		System.out.println("my thread created: " + this);
		start();  //start a thread by calling its run() method
	}
	
	public void run()
	{
		try
		{
	        for (int i=0 ;i < 5; i++)
	        {
	           System.out.println("Printing the count " + i);
	           Thread.sleep(1000);
	        }
		}
		catch(InterruptedException e)
		{
	        System.out.println("my thread interrupted");
		}
		System.out.println("My thread run is over" );
     
	}
}

class ThreadByExtendThreadClass
{
   public static void main(String args[])
   {
	  CountThread cnt = new CountThread();
	  
      try
      {
         while(cnt.isAlive())
         {
           System.out.println("Main thread will be alive till the child thread is live");
           Thread.sleep(1500);
         }
      }
      catch(InterruptedException e)
      {
        System.out.println("Main thread interrupted");
      }
      
      System.out.println("Main thread's run is over" );
   }
}


