package fr.utbm.ia54.antcolony.gui;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.DefaultGraph;

import fr.utbm.ia54.antcolony.graphe.Edge;
import fr.utbm.ia54.antcolony.graphe.Node;

public class GraphStreamToGraph {
	fr.utbm.ia54.antcolony.graphe.Edge edge;
	fr.utbm.ia54.antcolony.graphe.Graph graph = new fr.utbm.ia54.antcolony.graphe.Graph();
	org.graphstream.graph.Graph graphStream = new DefaultGraph("Converted");
	//Convert Graph from env to graphStream graph
	public fr.utbm.ia54.antcolony.graphe.Graph GS2Graph(org.graphstream.graph.Graph gs){
		
		
		for( org.graphstream.graph.Node gsNode : gs.getEachNode()){
			graph.addNode(new Node(gsNode.getId()), true);
			
		}
		for(org.graphstream.graph.Edge gsEdge : gs.getEachEdge()){
			edge.setPheromon(gsEdge.getAttribute("pheromones"));
			graph.addEdge(gsEdge.getNode0(), gsEdge.getNode1(),gs.getAttribute("length"));
		}
		
		
		return graph;
		
	}
	//Convert graphstream graph to understandable graph for env
	public Graph Graph2GS(fr.utbm.ia54.antcolony.graphe.Graph graph){
		int i=0;
		graphStream=new DefaultGraph("Converted");
		while(graph.containsNode(new Node(String.valueOf(i)))){
			graphStream.addNode(String.valueOf(i));
			i++;
		}
		i=0;
		for(Edge e : graph.getEdges()){
			graphStream.addEdge(String.valueOf(i), graphStream.getNode(String.valueOf(e.getOne())), graphStream.getNode(String.valueOf(e.getTwo())));
			graphStream.getEdge(String.valueOf(i)).setAttribute("length", e.getWeight());
			graphStream.getEdge(String.valueOf(i)).setAttribute("pheromones", e.getPheromon());
			i++;
		}
		
		
		return graphStream;
		
	}

}
