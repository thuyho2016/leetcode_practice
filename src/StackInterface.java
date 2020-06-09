/* An interface to represent Stack data structure. */

public interface StackInterface<T> {

	public boolean push(T value); 
	
	public T pop(); 
	
	public boolean contains(T value); 
	
	public int size(); 
	
	public void clear(); 
	
	public boolean isEmpty();

}
