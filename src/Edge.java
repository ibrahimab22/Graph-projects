
//import java.util.*;

// Edge between two nodes
public class Edge {
	
	private int distance;
	private Node tail;
	private Node head;
	private boolean tree;
	
	public Edge(Node tailNode, Node headNode, int dist) {
		distance = dist;
		tail = tailNode;
		head = headNode;
	}
	
	public Node getTail() {
		return tail;
	}
	
	public Node getHead() {
		return head;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setTail(Node newTail) {
		tail = newTail;
	}
	
	public void setHead(Node newHead) {
		head = newHead;
	}
	
	public void setDistance(int dist) {
		distance = dist;
	}


	
	
	public String getEdgeType() {
	    Node u = this.tail;  
	    Node v = this.head;  
	    if (tree)  {
	    	return "Tree edge";
	    }
	   else if (u.getDiscoveryTime() < v.getDiscoveryTime() && u.getFinishTime() > v.getFinishTime()) {
            	return "Forward Edge";
            }
	    
	     else if (u.getDiscoveryTime() > v.getDiscoveryTime() && u.getFinishTime() < v.getFinishTime()) {
            return "Back Edge";
	    }
         else {
            return "Cross Edge";
        } 
    }

	public boolean isTree() {
		return tree;
	}

	public void setTree(boolean tree) {
		this.tree = tree;
	}
	
	
	
	
	
	


}

	

	
	
	

