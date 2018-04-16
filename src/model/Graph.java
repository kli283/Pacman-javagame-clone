package model;

import java.util.HashSet;
import java.util.Set;

public class Graph {

	private Set<Node> nodes = new HashSet<>();
	
	public void addNode(Node someNode) {
		nodes.add(someNode);
	}
	
}
