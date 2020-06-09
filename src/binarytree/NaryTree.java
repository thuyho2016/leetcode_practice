package binarytree;

import java.util.ArrayList;
import java.util.List;

public class NaryTree {
	
    public int val;
    public List<NaryTree> children = new ArrayList<NaryTree>();;

    public NaryTree(int x) {
    	val = x;
    }

    public NaryTree(int x, List<NaryTree> children) {
        val = x;
        this.children = children;
    }
    
    public List<NaryTree> getChildren() {
    	return children;
    }
    public void setChildren(List<NaryTree> children) {
    	this.children = children;
    }
}
