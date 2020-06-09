
import java.util.ArrayList;
import java.util.List;

public class NestedIntegerImpl {
	List<NestedIntegerImpl> values;
	public Object value;
	
	public NestedIntegerImpl (List<NestedIntegerImpl> value){
		this.value = value;
	}
	
	public NestedIntegerImpl(Integer value){
		this.value= value;
	}
	public boolean isInteger() {
		// TODO Auto-generated method stub
		if(value instanceof Integer)
			return true;
		else 
			return false;
	}	
	
	public  List<NestedIntegerImpl> getList(){
		// TODO Auto-generated method stub
		if(value instanceof ArrayList)
			return (List<NestedIntegerImpl>) value;
		return null;
			
	}

	 // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return 0 if this NestedInteger holds a nested list
	public int getInteger() {
		// TODO Auto-generated method stub
		if(value instanceof Integer)
			return (int) value;
		else
			return 0;
	}
	
	// Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedIntegerImpl ni) {
    	values.add(ni);
    }
	   
}