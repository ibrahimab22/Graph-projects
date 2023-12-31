import java.io.*;


import java.util.Collections;
import java.util.Comparator;



public class DelivA {

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;

	//Constructor - DO NOT MODIFY
	public DelivA(File in, Graph gr) {
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
		
		// Calls the method that will do the work of deliverable A
		runDelivA();

		output.flush();
	}

	//*********************************************************************************
	//               This is where your work starts
	
	private void runDelivA  () {
		//Delete these lines when you add functionality
		System.out.println("DelivA:  To be implemented");//Prints to console
		output.println("DelivA:  To be implemented");//Prints to file
		     
	
	   
	    Collections.sort(graph.getNodeList(), new NodeIndegreeComparator());
	    for (Node n : graph.getNodeList()) {
	    	  System.out.println("Node " + n.getAbbrev() + " has indegree " + n.getIncomingEdges().size());
	    	
	    }
	    System.out.println("");
	    Collections.sort(graph.getEdgeList(), new EdgeSorter());
	    
	    Collections.sort(graph.getNodeList(), new  NodeOutdegreeComparator());
	    for (Node n : graph.getNodeList()) {
	    	  System.out.println("Node " + n.getAbbrev() + " has outdegree " + n.getOutgoingEdges().size());
	    	
	    }
	    
	}
	
	private class EdgeSorter implements Comparator<Edge>{

    	@Override
    	public int compare(Edge o1, Edge o2) {
    		if (o1.getDistance()> o2.getDistance()) {
    			return 1;
    			
    		}else if (o1.getDistance()< o2.getDistance()) {
    			return-1;
    		}
    			
    		return o1.getHead().getAbbrev().compareToIgnoreCase(o2.getHead().getAbbrev());
    	}
    		    
    }
   
		

	
	private class NodeOutdegreeComparator implements Comparator<Node> {
	    @Override
	    public int compare(Node node1, Node node2) {
	       
	 
	    		if (node1.getOutgoingEdges().size()>node2.getOutgoingEdges().size()) {
	    			return -1;
	    			
	    		}else if(node1.getOutgoingEdges().size()<node2.getOutgoingEdges().size()) {
	    			return 1;
	    		}
	    			
	    		return node1.getAbbrev().compareToIgnoreCase(node2.getAbbrev());
	    	}
	    		
	}
	
	private class NodeIndegreeComparator implements Comparator<Node> {
		 @Override
		    public int compare(Node node1, Node node2) {
		       
		 
		    		if (node1.getIncomingEdges().size()>node2.getIncomingEdges().size()) {
		    			return -1;
		    			
		    		}else if(node1.getIncomingEdges().size()<node2.getIncomingEdges().size()) {
		    			return 1;
		    		}
		    			
		    		return node1.getAbbrev().compareToIgnoreCase(node2.getAbbrev());
		    	}
		    		
		}
	    
	
	

	
	}

	

