package string;

public class BackSpaceCompare {
	
	//Time Complexity: O(n)
	public static boolean backspaceCompare(String S, String T) {
		String s = getString(S);
        String t = getString(T);
        return s.equals(t);
    }
	
	private static String getString(String s) {
		 StringBuilder str = new StringBuilder(); 
		 char[] c = s.toCharArray();
		 for (char ch: c) {
			 if (ch == '#') {
				 if (str.length() != 0)  // len = 1 -> index = 0
					 str.deleteCharAt(str.length() - 1);  
			 } else {
				 str.append(ch);
			 }
		 }
		 return str.toString();
	}
	
	// O(MAX(m,n)) time, O(1) space
	 public boolean backspaceCompare2(String S, String T) {
	        int sPos = getNextValidPos(S, S.length() - 1); 
	        int tPos = getNextValidPos(T,T.length() - 1);
	        while (sPos >= 0 && tPos >= 0){
	            if (S.charAt(sPos) != T.charAt(tPos)) return false;
	            sPos--;
	            sPos = getNextValidPos(S, sPos); 
	            tPos--;
	            tPos = getNextValidPos(T, tPos); 
	            
	        }
	        if (sPos < 0 && tPos < 0) return true;
	        return false;
	    }
	    
	    private int getNextValidPos(String str, int pos){
	        int flag = 0; 
	        while(true) {
	            if (pos < 0) break;
	            if (str.charAt(pos) == '#'){
	                flag -= 1;
	            } else {
	                flag += 1;
	            }
	            if (flag == 1) break;
	            pos--;
	        }
	        return pos;
	    }
	    
	
	public static void main(String[] args) {
		String s = "ad#c";
		String p = "ab#c";
		System.out.println(backspaceCompare(s,p));
		
	}
}
