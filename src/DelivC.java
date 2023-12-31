import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class DelivC{

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;

	//Constructor - DO NOT MODIFY
	public DelivC(File in, Graph gr) {
		inputFile = in;
		graph = gr;

		// Set up for writing to a file
		try {
			// Use input file name to create output file in the same location
			String inputFileName = inputFile.toString();
			String outputFileName = inputFileName.substring(0, inputFileName.length() - 4).concat("_out.txt");
			outputFile = new File(outputFileName);

			// A Printwriter is an object that can write to a file
			output = new PrintWriter(outputFile);
		} catch (Exception x) {
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}
		
		// Calls the method that will do the work of deliverable C
		runDelivC();

		output.flush();
	}

	//*********************************************************************************
	//                This is where I begin my work.
	
	int totalCost = 0;
	private void runDelivC() {
		//Delete these lines when you add functionality
		Node startnode = graph.findStartNode();
		Prim(startnode);
		for (Node n : graph.getNodeList()) {
		totalCost+=n.getKey();
		}
		System.out.print("The minimum spanning tree has a total cost of " + totalCost + " and includes the following edges");
		System.out.print("\n");

		for (Node n : graph.getNodeList()) {
			
			if(n.getPredecessor()!=null) {
	        System.out.println( n.getAbbrev() +"->" +n.getPredecessor().getAbbrev());
	        
	        
			}
	    } 


	}
	
	
	 //Implements Prim's algorithm to find the minimum spanning tree of a graph.
	 
	private void Prim(Node r) {
	    // Initialize all nodes with infinite key values and no predecessors
	    for (Node u : graph.getNodeList()) {
	        u.setKey(Integer.MAX_VALUE);
	        u.setPredecessor(null);
	    }
	    // Set the key of the root node to 0
	    r.setKey(0);

	    // Create a priority queue to hold nodes, sorted by their key values
	    PriorityQueue<Node> Q = new PriorityQueue<>(new NodeKeyComparator2());
	    Q.addAll(graph.getNodeList()); // Add all nodes to the priority queue

	    // Process nodes until the priority queue is empty
	    while (!Q.isEmpty()) {
	        // Remove the node with the smallest key value
	        Node u = Q.remove();
	        // Iterate through all outgoing edges of node u
	        for (Edge e : u.getOutgoingEdges()) {
	            Node v = e.getHead();
	            // Check if node v is in the queue and if the edge (u, v) has a smaller distance than the current key of v
	            if (Q.contains(v) && e.getDistance() < v.getKey()) {
	                // Update v's predecessor and key value
	                v.setPredecessor(u);
	                v.setKey(e.getDistance());

	                // Refresh node v in the priority queue to update its position
	                Q.remove(v);
	                Q.add(v);
	            }
	        }
	    }
	}



		
		//Comparator class for comparing nodes based on their key values.
	private class NodeKeyComparator2 implements Comparator<Node> {
	    @Override
	    public int compare(Node n1, Node n2) {
	        // Compare nodes based on their key values
	        if (n1.getKey() < n2.getKey()) {
	            // Return -1 if the key of n1 is less than the key of n2
	            return -1;
	        } else if (n1.getKey() > n2.getKey()) {
	            // Return 1 if the key of n1 is greater than the key of n2
	            return 1;
	        } else {
	            // Return 0 if keys are equal, indicating equal priority
	            return 0;
	        }
	    }
	}
	  
}



