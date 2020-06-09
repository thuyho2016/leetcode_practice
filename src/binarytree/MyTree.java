package binarytree;

import java.util.List;


public class MyTree {

    public int parentID; //val
    public List<MyTree> children;
   
    public MyTree() {}
    
    public MyTree(int x) {
    	parentID = x;
    	children = null;
    }
    

    public MyTree(int id, List<MyTree> childId) {
    	parentID = id;
        children = childId;
    }
    
    public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	public List<MyTree> getChildren() {
    	return children;
    }
    
    public void setChildren(List<MyTree> childs) {
    	children = childs;
    }
	
}
