import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DelivD {

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;

	//Constructor - DO NOT MODIFY
	public DelivD(File in, Graph gr) {
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
		
		// Calls the method that will do the work of deliverable D
		runDelivD();

		output.flush();
	}

	//*********************************************************************************
	 //              This is where I begin my work.	
	private void runDelivD() {
		//Delete these lines when you add functionality
		System.out.println("DelivD:  To be implemented");//Prints to console
		output.println("DelivD:  To be implemented");//Prints to file
		
		Collections.sort(graph.getNodeList(), new ValueComparator());

		int j = graph.getNodeList().size() - 1;
		int shortPath = bitonicTour(j-1, j) + findDistance(j-1, j);
		System.out.println("The shortest path is  " + shortPath);
		
		
	


		
	}
	/**
	 * Calculates the bitonic tour distance between two nodes.
	 */
	public int bitonicTour(int i, int j) {
	    // Handle base case for the first two nodes
	    if (i == 0 && j == 1) {
	       return findDistance(i, j);
	    } else if (i < j - 1) {
	    	// Recursively calculate distance for the node just before the last
	        return bitonicTour(i, j - 1) + findDistance(j - 1, j);
	    } else {
	        // Calculate minimum distance for the last node in tour
	        int minDistance = Integer.MAX_VALUE;
	        for (int k = 0; k < j - 1; k++) {
	            int distance = bitonicTour(k, j - 1) + findDistance(k, j);
	            // Update minimum distance if a shorter route is found
	            if (minDistance > distance) {
	                minDistance = distance;
	            }
	        }
	        return minDistance;
	    }
	}

	/**
	 * Finds the distance between two nodes by index.
	 */
	public int findDistance(int i, int j) {
	    Node nodeI = graph.getNodeList().get(i);
	    Node nodeJ = graph.getNodeList().get(j);

	    // Match target node's value to find the correct edge
	    for (Edge e : nodeI.getOutgoingEdges()) {
	        if (e.getHead().getValue().equals(nodeJ.getValue())) {
	            return e.getDistance();
	        }
	    }
	    // Return 0 if no direct connection exists
	    return 0;
	}
	
	/**
	 * Comparator for sorting Nodes based on their values.
	 */
	public class ValueComparator implements Comparator<Node> {
	    @Override
	    public int compare(Node node1, Node node2) {
	        // Convert node values to double for comparison
	        double val1 = Double.parseDouble(node1.getValue());
	        double val2 = Double.parseDouble(node2.getValue());
	        return Double.compare(val1, val2);  
	    }
	}
}