package model;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

public class DSP {

	public static Graph findshortestPath(Graph graph, Node source) {
		source.setDist(0);
		
		Set<Node> relaxedNodes = new HashSet<>();
		Set<Node> unrelaxedNodes = new HashSet<>();
		
		unrelaxedNodes.add(source);
		
		while(unrelaxedNodes.size() != 0) {
			Node currentNode = getLowestDist(unrelaxedNodes);
			unrelaxedNodes.remove(currentNode);
			for(Entry <Node, Integer> adjacencyPair:
				currentNode.getAdjacentNodes().entrySet()) {
				Node adjacentNode = adjacencyPair.getKey();
				Integer edgeWeight = adjacencyPair.getValue();
				if(!relaxedNodes.contains(adjacentNode)) {
					calcMinimumDist(adjacentNode, edgeWeight, currentNode);
					unrelaxedNodes.add(adjacentNode);
				}
			}
		}
		
		return graph;
	}
	
	private static void calcMinimumDist(Node subjectNode, Integer edgeWeight, Node sourceNode) {
		Integer sourceDistance = sourceNode.getDist();
		if(sourceDistance + edgeWeight < subjectNode.getDist()) {
			subjectNode.setDist(sourceDistance + edgeWeight); 
			LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode);
			subjectNode.setShortestPath(shortestPath);
		}
	}
	
	private static Node getLowestDist(Set <Node> unrelaxedNodes) {
		Node lowestDistanceNode = null;
		
		return lowestDistanceNode;
	}
}
