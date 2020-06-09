
import java.util.HashMap;
import java.util.Map;

public class ErrorHandlingExample {
	
	private Map<String,Integer> map;

	  public ErrorHandlingExample() {
	    map = new HashMap<>();
	    map.put("foo", 1);
	    map.put("bar", 3);
	  }
	
	  public int getValue(String input, int numRetries) throws Exception {
	    try {
	      return map.get(input);
	    }
	    catch (Exception e) {
	      if (numRetries > 3) {
	        throw e;
	      }
	      return getValue(input, numRetries + 1);
	    }
	  }
	
	  
	  public static void tryBlockSample() {
		  try{ 
			  
			int num= 121 / 0;  
			System.out.println(num);  
		  }  
		  catch(ArithmeticException e){
		     System.out.println("Number should not be divided by zero");
		  }
		
		  //Finally block will always execute even if there is no exception in try block
	
		  finally{
			  System.out.println("This is finally block. Finally block ran even after return statement");

		  }  
		  System.out.println("Out of try-catch-finally"); 
	  }
	  
	  public static int divide(int a, int b) {
		  int c = 1;
		  
		  try {
			  c = a / b;
		  }
		  catch (Exception e) {
			  System.err.print("Exception");
	      }
	      finally {
	    	  System.err.println("Finally");
	      }
		  return c;
	  }
	  
	public static void main(String args[]) throws Exception{
		
		tryBlockSample();
		
		System.out.println(divide(4, 0));  //ExceptionFinally
		
		ErrorHandlingExample o = new ErrorHandlingExample();
		System.out.println(o.getValue("foo", 0));
		System.out.println(o.getValue("bar", 2)); 
		System.out.println(o.getValue("baz", 0)); // print 1, 3, then throw exception (java.lang.NullPointerException) because hashmap doesnot have an entry (baz,0)
		
		
	}
}

