package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Node {

	private String name;
	
	private List<Node> shortestPath = new LinkedList<>();
	
	private Integer dist = Integer.MAX_VALUE;
	
	HashMap<Node, Integer> adjacentNodes = new HashMap<>();
	
	public void addDestination(Node destination, int distance) {
		adjacentNodes.put(destination, distance);
	}
	
	public Node(String name) {
		this.name = name;
	}
	
	public void setDist(Integer dist) {
		this.dist = dist;
	}
	
	public Integer getDist() {
		return this.dist;
	}
	
	public HashMap<Node, Integer> getAdjacentNodes() {
		return this.adjacentNodes;
	}
}
