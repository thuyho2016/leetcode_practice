import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* 609. Find Duplicate File in System
Given a list of directory info including directory path, and all the files with contents in this directory, 
you need to find out all the groups of duplicate files in the file system in terms of their paths.

A group of duplicate files consists of at least two files that have exactly the same content.


A single directory info string in the input list has the following format:

"root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"

It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content, f2_content ... fn_content, respectively) in directory root/d1/d2/.../dm.

The output is a list of group of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:

"directory_path/file_name.txt"

Example 1:

Input:
["root/a 1.txt(abcd) 2.txt(efgh)",
 "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)",
 "root 4.txt(efgh)"]

Output:  
[["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]   
 
 group a list of files that have same content
 */

public class FindDuplicateFiles {

   public static void main(String[] args) {
	   String[] paths = { "root/a 1.txt(abcd) 2.txt(efgh)",
			              "root/c 3.txt(abcd)",
			              "root/c/d 4.txt(efgh)",
			              "root 4.txt(efgh)"};
	   
	   List<List<String>> res = findDuplicate(paths);
	   System.out.println("Duplicate files: " + res);
      
   }
   /* Split(" ") 
    * Extract content, then add it as key in HashMap
    * start i = 1  to point to files: 1.txt(abcd) and 2.txt(efgh)
    * create hashMap, content as key and path/file as value
    * 
    * construct path/file_name and add it as value 
    * 
    *      fileName is tokens[i].substring(0,ind_l)
    *      concatenate the file path with file name = tokens[0] with fileName
    * 
    * return list of list contain values that path/filename

    */
   public static List<List<String>> findDuplicate(String[] paths) {
       HashMap<String ,List<String>> map = new HashMap<>(); // to store (K,V) = (same content, list of path/filename)
       
       for(String path : paths){
           String[] tokens = path.split(" ");  //[ root/a, 1.txt(abcd), 2.txt(efgh)]
           
           // tokens[0] = root/a  ( directory) , so start i = 1 to point to files: 1.txt(abcd) and 2.txt(efgh)
           for(int i = 1; i < tokens.length ;i++) { // i = 1, 2 < 3
			
        	   // To get content ,  find index of '(' and ')' 
               int ind_l = tokens[i].indexOf('('); // index of ( is 5 
               int ind_r = tokens[i].indexOf(')');  //index of ) is 10
               
               String content = tokens[i].substring(ind_l + 1, ind_r); //abcd  , efgh
               
               //create hashMap, content as key and path/file as value
               if(!map.containsKey(content)){
                   map.put(content, new ArrayList<>()); // {abcd =[]} create hashMap, content as key and path/file as value
               }
               
               // construct path/file_name = tokens[0] + / + fileName = root/a + / + 1.txt
               
               map.get(content).add(tokens[0] + "/" + tokens[i].substring(0, ind_l)); //abcd = [root/a/1.txt]
                     // tokens[i].substring(0, ind_l) is fileName
                     //and add it as value
           }
       }
       
		// initialize list of list result to add values of HashMap which is path/filename
       List<List<String>> res = new ArrayList<>();
       
       for(String content: map.keySet()){
           if(map.get(content).size() > 1 ){
               res.add(map.get(content)); //add values of each entry to result 
           }
       }
       return res;
   }
}