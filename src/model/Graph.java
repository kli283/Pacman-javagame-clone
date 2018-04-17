package model;

import java.util.HashSet;
import java.util.Set;

public class Graph {
	
	private Set<Node> nodes = new HashSet<>();
	private String[] levelData;
	
	public Graph(String[] levelData){
//		this.levelData = levelData;
//		int nodeNum = 0;
//		for(int i = 0; i < levelData.length; i++) {
//			String line = levelData[i];
//			for(int j = 0; j < line.length(); j++) {
//				switch (line.charAt(j)) { 
//				case '0':
//					break;
//				case 'p':
//				case 'r':
//				case '1':
//				case '2':
//				case '3':
//				case '4':
//				case '5':
//					this.addNode(createNode(i, j, nodeNum));
//					nodeNum++;
//					break;
//
//				}
//			}
//		}
		
		Node V1 = new Node("V1");
		Node V2 = new Node("V2");
		Node V3 = new Node("V3");
		Node V4 = new Node("V4");
		Node V5 = new Node("V5");
		Node V6 = new Node("V6");
		Node V7 = new Node("V7");
		Node V8 = new Node("V8");
		Node V9 = new Node("V9");
		Node V10 = new Node("V10");
		Node V11 = new Node("V11");
		Node V12 = new Node("V12");
		Node V13 = new Node("V13");
		Node V14 = new Node("V14");
		Node V15 = new Node("V15");
		Node V16 = new Node("V16");
		Node V17 = new Node("V17");
		Node V18 = new Node("V18");
		Node V19 = new Node("V19");
		Node V20 = new Node("V20");
		Node V21 = new Node("V21");
		Node V22 = new Node("V22");
		Node V23 = new Node("V23");
		Node V24 = new Node("V24");
		Node V25 = new Node("V25");
		Node V26 = new Node("V26");
		Node V27 = new Node("V27");
		Node V28 = new Node("V28");
		Node V29 = new Node("V29");
		Node V30 = new Node("V30");
		
		V1.addDestination(V2, 4);
		V1.addDestination(V5, 2);
		V2.addDestination(V1, 4);
		V2.addDestination(V3, 5);
		V2.addDestination(V6, 2);
		V3.addDestination(V2, 5);
		V3.addDestination(V7, 2);
		V3.addDestination(V4, 4);
		V4.addDestination(V3, 4);
		V4.addDestination(V8, 2);
		V5.addDestination(V1, 2);
		V5.addDestination(V6, 4);
		V5.addDestination(V9, 4);
		V6.addDestination(V5, 4);
		V6.addDestination(V2, 2);
		V6.addDestination(V28, 1);
		V28.addDestination(V6, 1);
		V28.addDestination(V11, 4);
		V28.addDestination(V29, 3);
		V29.addDestination(V28, 3);
		V29.addDestination(V12, 4);
		V29.addDestination(V7, 1);
		V7.addDestination(V29, 1);
		V7.addDestination(V3, 2);
		V7.addDestination(V8, 4);
		V8.addDestination(V4, 2);
		V8.addDestination(V14, 4);
		V9.addDestination(V5, 4);
		V9.addDestination(V10, 4);
		V9.addDestination(V15, 2);
		V10.addDestination(V9, 4);
		V10.addDestination(V11, 1);
		V10.addDestination(V18, 4);
		V11.addDestination(V10, 1);
		V11.addDestination(V28, 4);
		V11.addDestination(V12, 3);
		V12.addDestination(V11, 3);
		V12.addDestination(V7, 4);
		V12.addDestination(V13, 1);
		V13.addDestination(V12, 1);
		V13.addDestination(V14, 4);
		V13.addDestination(V19, 4);
		V14.addDestination(V8, 4);
		V14.addDestination(V13, 4);
		V14.addDestination(V21, 2);
		V15.addDestination(V9, 2);
		V15.addDestination(V16, 2);
		V15.addDestination(V22, 5);
		V16.addDestination(V15, 2);
		V16.addDestination(V17, 2);
		V17.addDestination(V16, 2);
		V17.addDestination(V18, 2);
		V17.addDestination(V24, 3);
		V18.addDestination(V17, 2);
		V18.addDestination(V10, 4);
		V18.addDestination(V19, 5);
		V19.addDestination(V13, 4);
		V19.addDestination(V18, 5);
		V19.addDestination(V20, 2);
		V20.addDestination(V19, 2);
		V20.addDestination(V30, 2);
		V20.addDestination(V25, 3);
		V21.addDestination(V14, 2);
		V21.addDestination(V30, 2);
		V21.addDestination(V26, 5);
		V22.addDestination(V15, 5);
		V22.addDestination(V24, 2);
		V24.addDestination(V22, 2);
		V24.addDestination(V17, 3);
		V24.addDestination(V25, 9);
		V25.addDestination(V24, 9);
		V25.addDestination(V20, 3);
		V25.addDestination(V26, 2);
		V26.addDestination(V25, 2);
		V26.addDestination(V21, 5);
	}

	public void addNode(Node someNode) {
		nodes.add(someNode);
	}	
}
