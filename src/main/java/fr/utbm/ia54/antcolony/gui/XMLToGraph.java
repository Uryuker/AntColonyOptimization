package fr.utbm.ia54.antcolony.gui;
/**
 * @author quentin barthelemy
 *
 */
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.ui.swingViewer.View;
import org.graphstream.ui.swingViewer.Viewer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

public class XMLToGraph {

	public Graph colorizeEdge(Graph graph) {
		int ph = 0;
		for (Edge e : graph.getEachEdge()) {
			ph = Integer.parseInt(e.getAttribute("pheromones"));

			// getting the pheromone rate and set the appropriate color
			if (ph > 90) {
				e.setAttribute("ui.style", "fill-color: rgb(253,30,30);size: 3px;");
			} else if (ph > 70) {
				e.setAttribute("ui.style", "fill-color: rgb(253,70,70);");
			} else if (ph > 45) {
				e.setAttribute("ui.style", "fill-color: rgb(253,120,120);");
			} else if (ph < 20) {
				e.setAttribute("ui.style", "fill-color: rgb(253,225,225);");
			} else
				e.setAttribute("ui.style", "fill-color: rgb(253,180,180);");
			// else
			// graph.getEdge(eElement.getElementsByTagName("Id").item(0).getTextContent()).addAttribute("ui.style",
			// "fill-color: rgb(253,180,180);");
		}
		return graph;
	}

	public View graphRender() {
		Graph graph = new DefaultGraph("Render");
		Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_SWING_THREAD);
		View view = viewer.addDefaultView(false);
		// Definition of the style of the graph
		viewer.enableAutoLayout();
		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");
		// Set color white for the edges and orange for the nodes
		graph.addAttribute("ui.stylesheet",
				"edge { fill-mode: dyn-plain; fill-color: white; } node { fill-color : orange;}");

		try {
			// Open the XML file
			File fXmlFile = new File("src/graph.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			EntityResolver resolver = new EntityResolver () {
				public InputSource resolveEntity (String publicId, String systemId) {
				String empty = "";
				ByteArrayInputStream bais = new ByteArrayInputStream(empty.getBytes());
				return new InputSource(bais);
				}
				};
				dBuilder.setEntityResolver(resolver); 
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			// Get the list of nodes and add them to the GraphStream graph
			NodeList nList = doc.getElementsByTagName("Node");
			if(nList!=null){
				for (int temp = 0; temp < nList.getLength(); temp++) {
	
					Node nNode = nList.item(temp);
	
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	
						Element eElement = (Element) nNode;
						graph.addNode(eElement.getElementsByTagName("Id").item(0).getTextContent());
					}
				}
			}

			// Get the edges and add them to the GraphStream graph
			NodeList eList = doc.getElementsByTagName("Edge");
			for (int temp = 0; temp < eList.getLength(); temp++) {
				Node nNode = eList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					// adding the edge
					graph.addEdge(eElement.getElementsByTagName("Id").item(0).getTextContent(),
							eElement.getElementsByTagName("IdStart").item(0).getTextContent(),
							eElement.getElementsByTagName("IdEnd").item(0).getTextContent());
					graph.getEdge(eElement.getElementsByTagName("Id").item(0).getTextContent()).addAttribute("length",
							eElement.getElementsByTagName("Length").item(0).getTextContent());
					graph.getEdge(eElement.getElementsByTagName("Id").item(0).getTextContent()).addAttribute(
							"pheromones", eElement.getElementsByTagName("Pheromone").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		colorizeEdge(graph);
		return view;
	}

	public void GraphToXML(Graph graph) {
		Document dom;
		Element echild = null;

		// instance of a DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// use factory to get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			// create instance of DOM
			dom = db.newDocument();
			dbf.setValidating(false);
			
			Element element=null;;
			Element e=null;
			EntityResolver resolver = new EntityResolver () {
				public InputSource resolveEntity (String publicId, String systemId) {
				String empty = "";
				ByteArrayInputStream bais = new ByteArrayInputStream(empty.getBytes());
				return new InputSource(bais);
				}
				};
				db.setEntityResolver(resolver); 
				Document doc = db.parse(new FileInputStream(new File("src/graph.xml")));
			Element root = doc.getDocumentElement();
			// removing the old graph
			// retrieve the element 'Node' and 'Edge'
			while (doc.getElementsByTagName("Node").getLength() > 0) {
				element = (Element) doc.getElementsByTagName("Node").item(0);
				// remove the specific node
				element.getParentNode().removeChild(element);
			}
			while (doc.getElementsByTagName("Edge").getLength() > 0) {
				element = (Element) doc.getElementsByTagName("Edge").item(0);
				// remove the specific edge
				element.getParentNode().removeChild(element);
			}

			// create data elements and place them under root
			for (org.graphstream.graph.Node n : graph.getEachNode()) {
				e = dom.createElement("Node");
				echild = dom.createElement("Id");
				echild.appendChild(dom.createTextNode(String.valueOf(n.getId())));
				e.appendChild(echild);
				Node importedNode = doc.importNode(e, true);
				root.appendChild(importedNode);
			}
			for (org.graphstream.graph.Edge edg : graph.getEachEdge()) {
				e = dom.createElement("Edge");
				// ceate the ID
				echild = dom.createElement("Id");
				echild.appendChild(dom.createTextNode(String.valueOf(edg.getId())));
				e.appendChild(echild);
				// Create the Id of the start node
				echild = dom.createElement("IdStart");
				echild.appendChild(dom.createTextNode(String.valueOf(edg.getNode0().getId())));
				e.appendChild(echild);
				// Create the Id of the end note
				echild = dom.createElement("IdEnd");
				echild.appendChild(dom.createTextNode(String.valueOf(edg.getNode1().getId())));
				e.appendChild(echild);
				// Create the length

				echild = dom.createElement("Length");
				echild.appendChild(dom.createTextNode(edg.getAttribute("length").toString()));
				e.appendChild(echild);
				// create the pheromone rate
				echild = dom.createElement("Pheromone");
				echild.appendChild(dom.createTextNode(edg.getAttribute("pheromones").toString()));
				e.appendChild(echild);

				Node importedNode = doc.importNode(e, true);
				root.appendChild(importedNode);
			}
			
			doc.normalize();

			Transformer tr = TransformerFactory.newInstance().newTransformer();
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			tr.transform(new DOMSource(doc), new StreamResult(new FileOutputStream("src/graph.xml")));

		} catch (

		Exception e1)

		{
			e1.printStackTrace();
		}
	}

}
