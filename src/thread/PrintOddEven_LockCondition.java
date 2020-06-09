package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * 
 * Printing odd and even number by two Threads
 * [1,2,3,4,,5,6,7,8,9,10]
 */
public class PrintOddEven_LockCondition {
	

 public static void main(String[] args) {
    Lock lock =  new ReentrantLock();
    Condition evenCondition = lock.newCondition();
    Condition oddCondition = lock.newCondition();
    
    Thread evenThread =  new Thread(new EvenPrinter(10, lock, evenCondition, oddCondition));
    Thread oddThread =  new Thread(new OddPrinter(10, lock, evenCondition, oddCondition));
    oddThread.start();
    evenThread.start();
}

	static class OddPrinter implements Runnable{
	    int i = 1;
	    int limit;
	    Lock lock;
	    Condition evenCondition;
	    Condition oddCondition;
	
	    public OddPrinter(int limit) {
	        super();
	        this.limit = limit;
	    }
	
	    public OddPrinter(int limit, Lock lock, Condition evenCondition, Condition oddCondition) {
	        super();
	        this.limit = limit;
	        this.lock = lock;
	        this.evenCondition = evenCondition;
	        this.oddCondition = oddCondition;
	    }
	
	    @Override
	    public void run() {
	        while( i <=limit) {
	            lock.lock();
	            System.out.println("Odd:"+i);
	            evenCondition.signal();
	            i += 2;
	            
	            try {
	                oddCondition.await();
	            } catch (InterruptedException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } finally {
	                lock.unlock();
	            }
	        }
	    }
	}

	static class EvenPrinter implements Runnable {
	    int i = 2;
	    int limit;
	    Lock lock;
	    Condition evenCondition;
	    Condition oddCondition;
	
	    public EvenPrinter(int limit) {
	        super();
	        this.limit = limit;
	    }
	
	
	    public EvenPrinter(int limit, Lock lock, Condition evenCondition, Condition oddCondition) {
	        super();
	        this.limit = limit;
	        this.lock = lock;
	        this.evenCondition = evenCondition;
	        this.oddCondition = oddCondition;
	    }
	
	
	    @Override
	    public void run() {
	        while( i <= limit) {
	            lock.lock();
	            System.out.println("Even:"+i);
	            i+=2;
	            
	            oddCondition.signal();
	            try {
	                evenCondition.await();
	            } catch (InterruptedException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } finally {
	                lock.unlock();
	            }
	        }
	    }
	}

}
  