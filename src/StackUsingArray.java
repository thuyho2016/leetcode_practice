import java.util.Arrays;

/*
 * An implementation of Stack in Java using array. This class is generic, so you can create Stack of Integer or Stack of String.
(Stack class which can hold any type of object like String or Integer)

Read more: https://javarevisited.blogspot.com/2017/03/how-to-implement-stack-in-java-using-array-example.html#ixzz5v8HoTYDa
implement push(), pop(), contains() methods 

- push() method does is it checks if Stack is full or not. If Stack is full i.e. size == length then it resizes the stack by creating another array 
  of 1.5 times of original size and copying the content of old array into a new array.

Read more: https://javarevisited.blogspot.com/2017/03/how-to-implement-stack-in-java-using-array-example.html#ixzz5v8GwIlCs
- pop() method  first checks if the array is empty. If it is empty then it return null, otherwise it first reduces the size and 
	then return the value from the top of the stack i.e. current index.
    if there is one element in the array then size is 1 but the index of the element is 0, 
    hence we first reduce the size and then retrieve the value from the array.

- contains() method should return true if an element passed to this method exists in the Stack, otherwise false.

 */

public class StackUsingArray<T > implements StackInterface<T>
{
	private static final int DEFAULT_CAPACITY = 10; 
	private T[] store; 
	private int size = 0; 
	private int capacity;

	public StackUsingArray() { 
		this.capacity = DEFAULT_CAPACITY; 
		store = (T[]) new Object[DEFAULT_CAPACITY]; 
	} 

	public StackUsingArray(int capacity) { 
		this.capacity = capacity; 
		store = (T[]) new Object[capacity]; 
	}

	@Override
	public boolean push(T value) {
		if (this.size >= store.length) {
			int newSize = size + (size >> 1); 
			store = Arrays.copyOf(store, newSize);
		}
		
		// not only store value in Stack but also increases size of the array, a good use  ++ operator which ensures that 
		//first you store at current index and then increment size. 
		
		store[size++] = value; 
		return true;

	}
	
	@Override
	public T pop() { 
		if (size <= 0) { 
			return null; 
		}
		// we are reducing size and then getting value from Stack, because size is always 1 more array index 
		// because index starts at 0. So if you have just one element in Stack, then valid index is zero but size would be one. 
		T value = store[--size];

		// make sure you dereference element at top of the stack // to prevent memory leak in Java 
		store[size] = null; 
		
		// shrinking array if its too big 
		int reducedSize = size; 
		
		if (size >= capacity && size < (reducedSize + (reducedSize << 1))) { 
			System.arraycopy(store, 0, store, 0, size); 
		} 
		return value;
	}
	
	@Override
	public boolean contains(T value) {
		
		boolean found = false;
		for (int i = 0; i < size; i++) {
			T element = store[i];
			if (element.equals(value)) {
				found = true;
			}
		}
		return found;
	}
	
	@Override
	public int size() { 
		return size; 
	}
	
	@Override
	public boolean isEmpty() { 
		return size == 0; 
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			store[i] = null;
		}
		size = 0;
	}

	public static void main(String args[])
   {
	  StackUsingArray<Integer> stack = new StackUsingArray<Integer>();
	  stack.push(-2);
	  stack.push(0);
	  stack.push(3);
	  System.out.println("Size " + stack.size()); //3
	  System.out.println("Stack contains 0 " + stack.contains(0)); //3
	  stack.pop();
	  System.out.println("Size " + stack.size()); //2
   }	  
	      	
	
	 
}
	
