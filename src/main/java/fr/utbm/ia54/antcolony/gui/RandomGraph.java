package fr.utbm.ia54.antcolony.gui;

import java.util.Random;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.Viewer;

public class RandomGraph {

	private Graph graph = new SingleGraph("Random");
	private Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_SWING_THREAD);

	public void generateGraph(int nodes, float connexity) {

		viewer.enableAutoLayout();

		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");
		// Set color white for the edges and orange for the nodes
		graph.addAttribute("ui.stylesheet",
				"edge { fill-mode: dyn-plain; fill-color: white; } node { fill-color : orange;}");

		// Add the nodes
		for (int inode = 1; inode < nodes + 1; inode++) {
			graph.addNode(String.valueOf(inode));
		}

		// number of edges
		float maxEdges = connexity * nodes * (nodes - 1) / 200;
		boolean noDupplicate = true;
		Random r = new Random();
		int rstart = 1, rend = 1, rlength = 0;
		

		for (float iedge = 1; iedge <= maxEdges; iedge++) {

			// Random edges
			// while to test if the edge alredy exists
			noDupplicate = true;
			while (noDupplicate) {
				noDupplicate = false;
				rstart = r.nextInt((nodes - 1) + 1) + 1;
				rend = rstart;

				// prevent the node start and end to be the same
				while (rend == rstart) {
					rend = r.nextInt((nodes - 1) + 1) + 1;
				}
				// check if the edge already exists
				if (graph.getNode(String.valueOf(rstart)).getEachEdge() != null) {
					for (Edge e : graph.getNode(String.valueOf(rstart)).getEachEdge()) {
						if (e != null) {
							if (e.getOpposite(graph.getNode(String.valueOf(rstart))) == graph
									.getNode(String.valueOf(rend))) {
								noDupplicate = true;
							} else if (Integer
									.parseInt(e.getOpposite(graph.getNode(String.valueOf(rstart))).getId()) == rstart
									&& Integer.parseInt(e.getSourceNode().getId()) == rend) {
								noDupplicate = true;
							}
						}
					}
				}
			}

			rlength = r.nextInt((100 - 1) + 1) + 1;
			graph.addEdge(String.valueOf(iedge), graph.getNode(String.valueOf(rstart)).getIndex(),
					graph.getNode(String.valueOf(rend)).getIndex());
			graph.getEdge(String.valueOf(iedge)).addAttribute("length", rlength);
			graph.getEdge(String.valueOf(iedge)).addAttribute("pheromones", 0);

			if (iedge == (maxEdges - nodes)) {
				// Test to see if all nodes are connected otherwise we connect
				// it with the next node
				for (Node n : graph.getEachNode()) {
					if (n.getEachEnteringEdge() == null && n.getEachLeavingEdge() == null) {
						// if it's the last node, link it to the first
						if (Integer.parseInt(n.getId()) == nodes) {
							graph.addEdge(String.valueOf(iedge), Integer.parseInt(n.getId()), 1);
							graph.getEdge(String.valueOf(iedge)).addAttribute("length", rlength);
							graph.getEdge(String.valueOf(iedge)).addAttribute("pheromones", 0);
							iedge++;
						} else {
							graph.addEdge(String.valueOf(iedge), Integer.parseInt(n.getId()),
									Integer.parseInt(n.getId()) + 1);
							graph.getEdge(String.valueOf(iedge)).addAttribute("length", rlength);
							graph.getEdge(String.valueOf(iedge)).addAttribute("pheromones", 0);
							iedge++;
						}
					}

				}

			}
		}
		new XMLToGraph().GraphToXML(graph);
	}

}
