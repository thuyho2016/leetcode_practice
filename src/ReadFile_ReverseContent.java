
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;
import java.io.*;
import java.util.*;

/*
 * Problem1: Reverse File
 * 
 * Problem 2:  Find all direct and indirect reportees of a manager
 * File contains person A report to B, C report to B
 *    emp, mgr
 *    A, B
 *    C, B
 *    D, A
 *    K, B
 *    
 *    
 *    
 *    Output: B -> A, C, K
 *            A -> D    

 
 *  Tree structure:
Bill -> Dom, Samir, Michael
Dom -> Bob, Peter, Porter
Peter -> Milton, Nina

Sample Data:
CEO Bill has 3 employees reporting to him: {Dom, Samir, Michael}
Dom has three reports { Peter, Bob, Porter}
Samir has no reports {}
Michael has no reports {}
Peter has 2 reports {Milton, Nina}

 */
public class ReadFile_ReverseContent {
	
	/** read file and reverse into String
	   Suppose this is the file you try to read:

		line1
		line2
		line3

		And you want to write it to the output stream of the servlet as follows:

		line3
		line2
		line1
	*/
	
	//use List to hold each line
	public static String reverseFile(File file) throws IOException{
        BufferedReader reader = null;
       
        List<String> rows = new ArrayList<String>();
        StringBuilder ans = new StringBuilder();       
        
        try {
            reader  = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                rows.add(line);
            }
           
            for (int i = rows.size() - 1; i >= 0; i--) {
                ans.append(rows.get(i));
            }
            return ans.toString();
           
  
        } catch(IOException ex) {

            System.err.println("An IOException was caught!");

            ex.printStackTrace();

        } finally {
            if (reader  != null) {
                reader.close();
            }
        }
		return null;
    }
	
	//use Stack
	public static String reverseFile2(File file) throws IOException{
        BufferedReader reader = null;
        
        Stack<String> lines = new Stack<String>();
        StringBuilder ans = new StringBuilder();
        
        try {
            reader  = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
            	lines.push(line);
            }           

            while(! lines.empty()) {;
                ans.append(lines.pop());
            }
          
            return ans.toString();
           
  
        } catch(IOException ex) {

            System.err.println("An IOException was caught!");

            ex.printStackTrace();

        } finally {
            if (reader  != null) {
                reader.close();
            }
        }
		return null;
    }

	public static String report(File file) throws IOException{
        BufferedReader reader = null;
       
        List<String> rows = new ArrayList<String>();
        StringBuilder ans = new StringBuilder();       
        
        try {
            reader  = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                rows.add(line);
            }
           
            for (int i = rows.size() - 1; i >= 0; i--) {
                ans.append(rows.get(i));
            }
            return ans.toString();
           
  
        } catch(IOException ex) {

            System.err.println("An IOException was caught!");

            ex.printStackTrace();

        } finally {
            if (reader  != null) {
                reader.close();
            }
        }
		return null;
    }
	
	
    public static void main(String[] args) throws Exception {
    	String filename = "input2.txt";
    	File file = new File(filename);
   
	/*	String result = reverseFile(file);
		System.out.println(result);
		
		result = reverseFile2(file);
		System.out.println(result);
	*/	
		String filename2 = "input2.txt";
    	File file2 = new File(filename2);
    	System.err.println(report(file2));
    }
}

