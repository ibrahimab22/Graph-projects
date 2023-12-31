import java.io.*;
import java.util.Collections;
//import java.util.Comparator;
import java.util.Comparator;



public class DelivB {

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;

	//Constructor - DO NOT MODIFY
	public DelivB(File in, Graph gr) {
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
		
		// Calls the method that will do the work of deliverable B
		runDelivB();

		output.flush();
	}

	//****************************************************************************)*****
	//                This is where I begin my work.
	private int time ;
	private void runDelivB() {
		//Delete these lines when you add functionality
		System.out.println("DelivB:  To be implemented");//Prints to console
		output.println("DelivB:  To be implemented");//Prints to file
		
	
					
		DFS();

		System.out.println("Node " +"\t" +"Disc " +"\t"+ "Finish: " );
		for (Node node : graph.getNodeList()) {
            System.out.println( node.getAbbrev() +"\t" +node.getDiscoveryTime()+"\t" + node.getFinishTime());
            
        }
		System.out.println("Edge " +"\t" +"Type " );
		 System.out.println("Edge Classification:");
		 
		 for (Edge edge : graph.getEdgeList()) {
		        System.out.println( edge.getTail().getAbbrev() +"->" +edge.getHead().getAbbrev() +"\t" +  edge.getEdgeType());
		    }  
	    
		
	}

	
	       
//Performs a Depth-First Search (DFS) on the graph.
private void DFS() {
    // Initialize all nodes: set color to white and self as predecessor, reset time
    for (Node u : graph.getNodeList()) {
        u.setColor("WHITE");
        u.setPredecessor(u);
        time = 0;
    }

    // Start DFS from the start node
    Node startNode = graph.findStartNode();
    DFSVisit(startNode);

    // Sort nodes based on their names
    Collections.sort(graph.getNodeList(), new nameSorter());

    // Continue DFS for any unvisited nodes
    for (Node u : graph.getNodeList()) {
        if (u.getColor().equalsIgnoreCase("WHITE")) {
            DFSVisit(u);
        }
    }
}


private void DFSVisit(Node u) {
    time = time + 1; 
    u.setDiscoveryTime(time); // Set discovery time
    u.setColor("GRAY"); // Mark node as discovered but not fully explored

    // Sort outgoing edges based on distance, then by node name
    Collections.sort(u.getOutgoingEdges(), new EdgeSorter());

    // Explore adjacent nodes
    for (Edge v : u.getOutgoingEdges()) {
        Node adjacentNode = v.getHead();
        if (adjacentNode.getColor().equalsIgnoreCase("WHITE")) {
            adjacentNode.setPredecessor(u); // Set predecessor
            v.setTree(true); // Mark edge as part of the DFS tree
            DFSVisit(adjacentNode); // Recursively visit the node
        }
    }

    u.setColor("BLACK"); // Mark node as fully explored
    time = time + 1; // Update finish time
    u.setFinishTime(time);
}


 //Comparator for sorting edges based on distance, then by head node's name.

private class EdgeSorter implements Comparator<Edge> {
    @Override
    public int compare(Edge o1, Edge o2) {
        // Compare edges based on distance
        if (o1.getDistance() < o2.getDistance()) {
            return -1;
        } else if (o1.getDistance() > o2.getDistance()) {
            return 1;
        }
        // If distances are equal, compare by head node's name
        return o1.getHead().getName().compareToIgnoreCase(o2.getHead().getName());
    }
}

// Comparator for sorting nodes based on their names.
private class nameSorter implements Comparator<Node> {
    @Override
    public int compare(Node node1, Node node2) {
        // Compare nodes based on their names
        return node1.getName().compareToIgnoreCase(node2.getName());
    }
}
		
			

	  
	    	
}
	
	

		
		
	


		
			
			
		
		
	
	 



