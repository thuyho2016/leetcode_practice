import java.util.Arrays;

/* 
Implement IntArrayList using primitive array int[] with these operations 
1) default constructor 
2) void add(int value) // would append to the end of the array and extends internal storage if necessary
3) int get(int index) // return the value at given index 
4) void delete(int index) // left shift all trailing elements after the deleted entry. 
Bonus 
5) void set (int value, int index) // extends the internal storage as necessary. Can ignore the items in between. 
*/
public class AddGetDeleteNumberArrayInt {
	//Initialize a int array
	private int[] arr = new int[0];
		
    public AddGetDeleteNumberArrayInt() {
    	Arrays.fill(arr,  -1);
    }
    
    /** Add the number to arry int[] */
    public void add(int number) {
    	arr = Arrays.copyOf(arr, arr.length + 1);
    	//add new number to last index
        arr[arr.length - 1] = number;
    }
    
    public int get(int index) {
    	if (index < arr.length)
    		return arr[index];
    	else
    		return -1;
    }
    
    public void delete(int index) {
        // check for array is empty and index is not in array range
    	if (arr == null || index < 0 || index > arr.length) {
    		return;
    	}
    	int len = arr.length;
    	int i;
    	for (i = 0;i < len;i++) {
    		if (i == index) {
    			break;
    		}
    	}
    	if (i < len) {
	    	// when index is found, reduce len
	    	len = len -1;
	    	for (int j = i; j < len; j++) {
	    		arr[j] = arr[j+1];
	    	}
    	}
    	arr = Arrays.copyOf(arr, arr.length -1);
    }
        
    /** Set a number by given index */
    public void set(int value, int index) {
   
    	if (index > arr.length) {
    		arr = Arrays.copyOf(arr, index);
    		index--;
    		arr[index] = value;
    	} else {
    		arr[index] = value;
    	}
    }
	
	public static void main (String[] args)
    {		
		AddGetDeleteNumberArrayInt obj = new AddGetDeleteNumberArrayInt();
		obj.add(1);
		obj.add(3);
		obj.add(5);
		obj.add(7);
		
		System.out.println(Arrays.toString(obj.arr));
		
		System.out.println(obj.get(10)); // -1
		
		System.out.println(obj.get(2)); //5
		
		obj.delete(2); //delete 5 at index 2
		
		System.out.println(Arrays.toString(obj.arr));
		
		System.out.println(obj.get(2)); //7
		
		obj.set(5, 2); // set 5 at index 2
		
		System.out.println(Arrays.toString(obj.arr));
		
		obj.set(6, 6); // set 6 at index 6
		
		System.out.println(Arrays.toString(obj.arr));		
    }
}
