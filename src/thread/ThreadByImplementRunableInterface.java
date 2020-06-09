package thread;
/*
 * Thread creation by implementing Runnable Interface
 */

class CountThread2 implements Runnable{
  
   CountThread2()
   { 
	/*  Thread mythread = new Thread(this, "my runnable thread");
      System.out.println("my thread created" + mythread);
      mythread.start(); */
   }
   
   public void run()
   {
      try
      {
        for (int i=0 ;i< 5;i++)
        {
          System.out.println("Printing the count " + i);
          Thread.sleep(1000);
        }
     }
     catch(InterruptedException e)
     {
        System.out.println("my thread interrupted");
     }
     System.out.println("mythread run is over" );
   }
}

class ThreadByImplementRunableInterface
{
    public static void main(String args[])
    {
    	CountThread2 obj = new CountThread2();

  	    Thread mythread = new Thread( obj, "my runnable thread");
        System.out.println("my thread created by " + mythread);
        mythread.start();
        
    	try
    	{
          while(mythread.isAlive())
          {
            System.out.println("Main thread will be alive till the child thread is live"); 
            Thread.sleep(1500);
          }
    	}
    	catch(InterruptedException e)
    	{
    		System.out.println("Main thread interrupted");
    	}
    	System.out.println("Main thread run is over" );
    }
}
