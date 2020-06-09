

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
 * https://stackoverflow.com/questions/14037404/java-read-large-text-file-with-70million-line-of-text
 * 
 * http://nadeausoftware.com/articles/2008/02/java_tip_how_read_files_quickly
 */
public class ReadFile_Sort {
	
		
	// sort lines line # and save to new file
	public static void sortFile(File file) throws IOException{

		BufferedReader reader = null;
		PrintWriter outputStream = null;
		ArrayList<String> rows = new ArrayList<String>();
	
		
		try {
		    reader  = new BufferedReader(new FileReader("input.txt"));
		    outputStream = new PrintWriter(new FileWriter("output.txt"));
		
		    String line;
		    while ((line = reader .readLine()) != null) {
		        rows.add(line);
		    }		    
		    
		    Collections.sort(rows);          
		    
		    String[] strArr= rows.toArray(new String[0]); //Returns an array containing all of the elements in this lis
		    
		    for (String cur : strArr)
		        outputStream.println(cur);
		    
		} catch(IOException ex) {
		
		    System.err.println("An IOException was caught!");
		
		    ex.printStackTrace();
		
		} finally {
		    if (reader  != null) {
		        reader.close();
		    }
		    if (outputStream != null) {
		        outputStream.close();
		    }
		}
	}
	
	public static void readLargeFile() throws Exception {

        //
        File file = new File("input.txt");
        long length = file.length();
        
        String encoding = "UTF-8";
        InputStream content = new FileInputStream(file); 

        // Now the real job.
        Reader input = new InputStreamReader(content, encoding);
        RandomAccessFile output = new RandomAccessFile(new File("filereversed.txt"), "rwd");
        
        CharsetEncoder encoder = Charset.forName(encoding).newEncoder();

        for (int data; (data = input.read()) != -1;) {
            ByteBuffer bytes = encoder.encode(CharBuffer.wrap(new char[] { (char) data }));
            length -= bytes.limit();
            output.seek(length);
            output.write(bytes.array());
        }

        // Should actually be done in finally.
        input.close();
        output.close();
    }

   // for large files
	public void testLargeFileIO_Scanner() throws Exception {

	    long start = new Date().getTime();

	    String fileName = "/Downloads/SampleTextFile_1000kb.txt"; //this path is on my local
	   
	    InputStream inputStream = new FileInputStream(fileName);

	    try (Scanner fileScanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
	        while (fileScanner.hasNextLine()) {
	            String line = fileScanner.nextLine();
	            //System.out.println(line);
	        }
	    }
	    long end = new Date().getTime();

	    long time = end - start;
	    System.out.println("Scanner Time Consumed => " + time); //167

	}


	 public void testLargeFileIO_BufferedReader() throws Exception {

	    long start = new Date().getTime();

	    String fileName = "/Downloads/SampleTextFile_1000kb.txt"; //this path is on my local
	    
	    try (BufferedReader fileBufferReader = new BufferedReader(new FileReader(fileName))) {
	        String fileLineContent;
	        while ((fileLineContent = fileBufferReader.readLine()) != null) {
	            //System.out.println(fileLineContent);
	        }
	    }
	    long end = new Date().getTime();

	    long time = (long) (end - start);
	    System.out.println("BufferedReader Time Consumed => " + time); //129

	}


	public void testLargeFileIO_Stream() throws Exception {

	    long start = new Date().getTime();

	    String fileName = "/Downloads/SampleTextFile_1000kb.txt"; //this path is on my local
	    
	    try (Stream inputStream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
	        //inputStream.forEach(System.out::println);
	    }
	    long end = new Date().getTime();

	    long time = end - start;
	    System.out.println("Stream Time Consumed => " + time); //134
	}
	
	
    public static void main(String[] args) throws Exception {
    	String filePath = "input.txt";
    	File file = new File(filePath);
    	
		sortFile(file);
		
		readLargeFile();
    }
}

