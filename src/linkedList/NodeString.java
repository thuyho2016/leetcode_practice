package linkedList;

public class NodeString
{
	  public String info;
	  public NodeString next;
	  
	  public NodeString() {
		  this(null,null);
	  }
	  public NodeString(String info) {
		  this(null, info);
	  }
	  
	  public NodeString(NodeString next, String info) {
		  this.info = info;
	      this.next = next;
	  }
}
