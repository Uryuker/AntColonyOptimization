package fr.utbm.ia54.antcolony.gui;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.View;
import org.graphstream.ui.swingViewer.Viewer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLToGraph {
	    
	public void colorizeEdge(Graph graph){
		int ph=0;
		for(Edge e: graph.getEachEdge()) {
			ph=Integer.parseInt(e.getAttribute("pheromones"));
			
			//getting the pheromone rate and set the appropriate color
			if(ph>90){e.setAttribute("ui.style", "fill-color: rgb(253,30,30);size: 3px;");
			}else if(ph>70){e.setAttribute("ui.style", "fill-color: rgb(253,70,70);");
			}else if(ph>45){e.setAttribute("ui.style", "fill-color: rgb(253,120,120);");
			}else if(ph<20){e.setAttribute("ui.style", "fill-color: rgb(253,225,225);");
			}else e.setAttribute("ui.style", "fill-color: rgb(253,180,180);");
			//else graph.getEdge(eElement.getElementsByTagName("Id").item(0).getTextContent()).addAttribute("ui.style", "fill-color: rgb(253,180,180);");
		}
	}
	public View graphRender(){
		Graph graph = new SingleGraph("Render");
		Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_SWING_THREAD);
	    View view = viewer.addDefaultView(false);
	    //Definition of the style of the graph
		viewer.enableAutoLayout();
	    graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");
		//Set color white for the edges and orange for the nodes
		graph.addAttribute("ui.stylesheet",
			"edge { fill-mode: dyn-plain; fill-color: white; } node { fill-color : orange;}");
	
		try{
			//Open the XML file
			File fXmlFile = new File("src/graph.XML");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);      		
			doc.getDocumentElement().normalize();
	
			//Get the list of nodes and add them to the GraphStream graph
			NodeList nList = doc.getElementsByTagName("Node");
			for (int temp = 0; temp < nList.getLength(); temp++) {
	
				Node nNode = nList.item(temp);
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	
					Element eElement = (Element) nNode;
					graph.addNode(eElement.getElementsByTagName("Id").item(0).getTextContent());
				}
			}
			
			//Get the edges and add them to the GraphStream graph
			NodeList eList = doc.getElementsByTagName("Edge");
			for (int temp = 0; temp < eList.getLength(); temp++) {	
				Node nNode = eList.item(temp);
	
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {	
					Element eElement = (Element) nNode;
					//adding the edge
					graph.addEdge(eElement.getElementsByTagName("Id").item(0).getTextContent(), eElement.getElementsByTagName("IdStart").item(0).getTextContent(), eElement.getElementsByTagName("IdEnd").item(0).getTextContent());
					graph.getEdge(eElement.getElementsByTagName("Id").item(0).getTextContent()).addAttribute("length", eElement.getElementsByTagName("Length").item(0).getTextContent());
					graph.getEdge(eElement.getElementsByTagName("Id").item(0).getTextContent()).addAttribute("pheromones",eElement.getElementsByTagName("Pheromone").item(0).getTextContent());
					}
				}
			}
		 catch (Exception e) {
			e.printStackTrace();
		}
		colorizeEdge(graph);
		return view;
	}

}
