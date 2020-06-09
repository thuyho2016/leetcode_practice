package design;

/*
 * 641. Design Circular Deque
 
 Design your implementation of the circular double-ended queue (deque).

Your implementation should support following operations:

MyCircularDeque(k): Constructor, set the size of the deque to be k.
insertFront(): Adds an item at the front of Deque. Return true if the operation is successful.
insertLast(): Adds an item at the rear of Deque. Return true if the operation is successful.
deleteFront(): Deletes an item from the front of Deque. Return true if the operation is successful.
deleteLast(): Deletes an item from the rear of Deque. Return true if the operation is successful.
getFront(): Gets the front item from the Deque. If the deque is empty, return -1.
getRear(): Gets the last item from Deque. If the deque is empty, return -1.
isEmpty(): Checks whether Deque is empty or not. 
isFull(): Checks whether Deque is full or not.
 

Example:

MyCircularDeque circularDeque = new MycircularDeque(3); // set the size to be 3
circularDeque.insertLast(1);			// return true
circularDeque.insertLast(2);			// return true
circularDeque.insertFront(3);			// return true
circularDeque.insertFront(4);			// return false, the queue is full
circularDeque.getRear();  			// return 2
circularDeque.isFull();				// return true
circularDeque.deleteLast();			// return true
circularDeque.insertFront(4);			// return true
circularDeque.getFront();			// return 4
 
 
 */
public class CircularDeque {
	/** Initialize your data structure here. Set the size of the deque to be k. */
    int deque[];
    int front;
    int rear;
    
    public CircularDeque(int k) {
        deque = new int[k];
        front = rear = -1;
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(isFull())
            return false;
        if(rear == -1 && front == -1){
            rear = front = 0;
            deque[rear] = value;
        }else{
            if(front == 0){
                front = deque.length - 1;
            }else{
                front--;
            }
            deque[front] = value;
        }
        return true;
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(isFull())
            return false;
        if(rear == -1 && front == -1){
            rear = front = 0;
            deque[rear] = value;
        }else{
            rear = (rear + 1) % deque.length;
            deque[rear] = value;
        }
        return true;
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(isEmpty())
            return false;
        if(front == rear){
            front = rear = -1;
        }else{
            front = (front + 1) % deque.length;
        }
        return true;
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(isEmpty())
            return false;
        if(front == rear){
            front = rear = -1;
        }else{
            if(rear == 0){
                rear = deque.length - 1;
            }else{
                rear--;
            }
        }
        return true;
    }
    
    /** Get the front item from the deque. */
    public int getFront() {
        if(isEmpty())
            return -1;
        return deque[front];
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
        if(isEmpty())
            return -1;
        return deque[rear];
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return (front == -1 && rear == -1);
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return (front == (rear + 1) % deque.length);
    }
}
