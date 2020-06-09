
//Java program to demonstrate working of 
//interface.
import java.io.*;

//A simple interface
interface in1
{
	// public, static and final
	final int a = 10;

	// public and abstract 
	void display();
}

//A class that implements interface.
public class InterfaceSample implements in1
{
	 // Implementing the capabilities of
	 // interface.
	 public void display()
	 {
	     System.out.println("Geek");
	 }
	
	 // Driver Code
	 public static void main (String[] args)
	 {
		 InterfaceSample t = new InterfaceSample();
	     t.display();
	     System.out.println(a);
	 }
}
