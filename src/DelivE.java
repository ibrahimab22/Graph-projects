import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import DelivB.nameSorter;
import DelivD.ValueComparator;

public class DelivE {

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;

	//Constructor - DO NOT MODIFY
	public DelivE(File in, Graph gr) {
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
		
		// Calls the method that will do the work of deliverable E
		runDelivE();

		output.flush();
	}

	//*********************************************************************************
	//               This is where I begin my work.
	

	private void runDelivE() {
		 
	
		
	    int totalDistance = calculateTotalDistance();
	    
	    String tourString = TourString();
	    output.println("Original Tour: " + tourString + " with total distance: " + totalDistance);
	    System.out.println("Original Tour: " + tourString + " with total distance: " + totalDistance);

	    
	    if (graph.getNodeList().size() > 1) {
	        swapCities(0, 1);
	        
	        totalDistance = calculateTotalDistance();
	        tourString = TourString();

	        output.println("Tour after swaps: " + tourString + " with total distance: " + totalDistance + " (swap)");
	        System.out.println("Tour after swaps: " + tourString + " with total distance: " + totalDistance );
	    }
	    if (graph.getNodeList().size() > 1) {
	    	 AdjacentSwaps();
	    	 int totalDistancenew2 = calculateTotalDistance();
	         tourString = TourString();
	    	 output.println(" Tour: " + tourString + " with total distance: " + totalDistancenew2+" (adjacent swap)");
	 	    System.out.println(" Tour: " + tourString + " with total distance: " + totalDistancenew2);

	    
	    }
	   
	    exhaustiveSwap();
	    int totalDistancenew = calculateTotalDistance();
        tourString = TourString();

	    output.println("Shortest path found was: " + tourString + " with total distance: " + totalDistancenew+" (2 opt) , after 3 steps");
	    System.out.println("Shortest path found was: " + tourString + " with total distance: " + totalDistancenew +" after 3 steps");



	    output.flush();
	    output.close();
	}
	
	// Method to calculate the total distance of a tour in a graph
	private int calculateTotalDistance() {
	    int totalDistance = 0;
	    // Loop through each pair of adjacent nodes in the graph
	    for (int i = 0; i < graph.getNodeList().size() - 1; i++) {
	        Node currentNode = graph.getNodeList().get(i);
	        Node nextNode = graph.getNodeList().get(i + 1);
	        // Add distance between current and next node to total distance
	        totalDistance += findDistance(currentNode, nextNode);
	    }

	    // Include the distance from the last node back to the first to complete the tour
	    if (!graph.getNodeList().isEmpty()) {
	        Node lastNode = graph.getNodeList().get(graph.getNodeList().size() - 1);
	        Node firstNode = graph.getNodeList().get(0);
	        totalDistance += findDistance(lastNode, firstNode);
	    }

	    return totalDistance;
	}
	
	// Method to find the distance between two connected nodes
	private int findDistance(Node node1, Node node2) {
	    // Iterate through outgoing edges of the first node
	    for (Edge e : node1.getOutgoingEdges()) {
	        // Check if the edge connects to the second node
	        if (e.getHead().equals(node2)) {
	            // Return the distance of this edge
	            return e.getDistance();
	        }
	    }
	    
	    // Return -1 if no edge connects the two nodes directly
	    return -1;
	}
	

	
	// Method to do a direct swap two cities in the tour
	private void swapCities(int i, int j) {
	    
	    Collections.swap(graph.getNodeList(), i, j);
	}
	
	
	// Method to create a string representation of the tour
	private String TourString() {
	    String tourString = "";
	    // Build a string of abbreviations of nodes in the tour
	    for (Node node : graph.getNodeList()) {
	        tourString += node.getAbbrev();
	    }
	    // Append the abbreviation of the first node to close the loop
	    if (!graph.getNodeList().isEmpty()) {
	        tourString += graph.getNodeList().get(0).getAbbrev();
	    }
	    return tourString;
	}
	
	// Method to perform adjacent city swaps to optimize the tour
	private void AdjacentSwaps() {
	    int totalDistance = calculateTotalDistance();
	    // Loop through each adjacent pair of cities
	    for (int i = 0; i < graph.getNodeList().size() - 1; i++) {
	        // Swap adjacent cities
	        swapCities(i, i + 1);
	        int newDistance = calculateTotalDistance();
	        // If the new distance is shorter, keep the swap
	        if (newDistance < totalDistance) {
	            totalDistance = newDistance;
	        } 
	        // Otherwise, swap back to original positions
	        else {
	            swapCities(i, i + 1);
	        }
	    }
	}

	
	// Method to optimize the tour by exhaustively testing all node swaps for the shortest route
	private void exhaustiveSwap() {
	    boolean improvement = true;
	    while (improvement) {
	        improvement = false;
	        int bestDistance = calculateTotalDistance();

	        // Iterate over all pairs of nodes
	        for (int i = 0; i < graph.getNodeList().size() - 1; i++) {
	            for (int j = i + 1; j < graph.getNodeList().size(); j++) {
	                // Swap two non-adjacent cities
	                swapCities(i, j);
	                int newDistance = calculateTotalDistance();

	                // If the new distance is shorter, keep the swap
	                if (newDistance < bestDistance) {
	                    bestDistance = newDistance;
	                    improvement = true;
	                } 
	                // Otherwise, swap back to original positions
	                else {
	                    swapCities(i, j);
	                }
	            }
	        }
	    }
	}
	
	
	
   
			
	
}
