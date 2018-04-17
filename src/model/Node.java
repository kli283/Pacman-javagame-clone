package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Node {

	private String name;
	//private int[] origin;
	
	private List<Node> shortestPath = new LinkedList<>();
	
	private Integer dist = Integer.MAX_VALUE;
	
	HashMap<Node, Integer> adjacentNodes = new HashMap<>();
	
	public void addDestination(Node destination, int distance) {
		adjacentNodes.put(destination, distance);
	}
	
	public Node(String name) {//, int i, int j) {
		this.name = name;
		//this.origin[0] = i;
		//this.origin[1] = j;
	}
	
	public void setDist(Integer dist) {
		this.dist = dist;
	}
	
	public Integer getDist() {
		return this.dist;
	}
	
	public void setShortestPath(List<Node> shortPath) {
		this.shortestPath = shortPath;
	}
	
	public List<Node> getShortestPath(){
		return this.shortestPath;
	}
	
	public HashMap<Node, Integer> getAdjacentNodes() {
		return this.adjacentNodes;
	}
	
//	public int[] getOrigin() {
//		return origin;
//	}
}
