
import java.util.LinkedList;
import java.util.Queue;
	 
public class QueueExample
{
  public static void main(String[] args)
  {
    Queue<Integer> q = new LinkedList<Integer>();
 
    // Adds elements {0, 1, 2, 3, 4} to queue
    for (int i=0; i < 5; i++)
     q.add(i);
 
    // Display contents of the queue.
    System.out.println("Elements of queue: "+ q);
 
    // To remove the head of queue.
    int remove = q.remove();
    System.out.println("removed element: " + remove);
 
    System.out.println(q);
 
    //To add new element
    q.add(5);
    System.out.println("Elements of queue after add 5: "+ q);
    
    // To view the head of queue
    int head = q.peek();
    System.out.println("Head of queue: " + head);
 
    // Rest all methods of collection interface,
    // Like size and contains can be used with this
    // implementation.
    int size = q.size();
    System.out.println("Size of queue: " + size);
    
    q.offer(7);
    System.out.println("Elements of queue after insert 7: "+ q);
    
    q.poll();
    System.out.println("Elements of queue after poll a head: "+ q);
  }
}
