
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Dropbox - Phone interview
Find duplicate file in file system ( directory/ file…)

- How to get all the files with duplicate file names given a path name.
Given a string value for the top level directory as input to your function and helper functions to get files/directories in a given directory (that you need to define, not code), 
write a function to return a list of all duplicate files in the file system. 

For example: 
//Helper functions (need to be defined by you) public List<String> getFiles(String directoryPath); 

//Returns all files directly under this directory public List<String> getDirectories(String directoryPath); 

//Returns all directories directly under this directory public String getFileContent(String filePath); 

//Returns content of a file 

//Write this function (Interviewer just cared about this) public List<String> findDuplicates(String rootDirectory) { ... ... }

/doc/
   |/dir1/dir2/file1
     |
     ---/dir3/file2 file1 <--duplicate
         |
         ---dir4/file3 file2 <--duplicate
         |__dir5/file4 file3 <--duplicate
    dir6/file6
    dir7/file7
 
 public List findDuplicates(string rootDirectory) {             var dup = new List();             var files = getFiles(rootDirectory);             foreach (var file in files) {                 //some hashing function                 var content = getFileContent(file);                 var hash = content.GetHashCode();                 if(list.ContainsKey(hash)){                     //add to duplicates                     dup.Add(file);                 } else {                     list.Add(hash, file);                 }             }              var dir = getDirectories(rootDirectory);             foreach(var d in dir){                 dup.AddRange(FindDuplicates(d, list));             }             return dup;         }
 
*/
public class Dropbox_DuplicateFiles {
   private List<String> listDirs = new ArrayList<>();
   private List<String> listFiles = new ArrayList<>();
   
   //get all files with duplicate file name
   public List<String> findDuplicateFile(String rootDirectory ) {
	   List<String> fileNames = new ArrayList<>();
	   
	   List<String> dirs = getDictories(rootDirectory);
		   
		   for (String dir: dirs) {
			   if (dir.isDir) 
				   List<String> files = getFiles()
		   }
	   }
	   
	  
	   
   }
	// Returns all directory under this directory
	 public List<String> getDirectories(String directoryPath) {
		 return listDirs;
	 }
	 

	//Returns all files directly under this directory
	 public List<String> getFiles(String directoryPath){
		 return listFiles;
	 }
		 
	 //Returns all directories directly under this directory 
	 public String getFileContent(String filePath) throws IOException {
		 
		 BufferedReader reader = null;
	       
	        List<String> rows = new ArrayList<String>();
	        StringBuilder ans = new StringBuilder();       
	        
	        try {
	            reader  = new BufferedReader(new FileReader(filePath));

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
	 
	  
	  public static void main (String[] args) 
	  {
		   String[] paths = { "doc/a 1.txt(abcd) 2.txt(efgh)",
		              "root/c 3.txt(abcd)",
		              "root/c/d 4.txt(efgh)",
		              "root 4.txt(efgh)"};

		   List<List<String>> res = findDuplicateFiles(paths);
		   System.out.println("Duplicate files: " + res);

    }
}
