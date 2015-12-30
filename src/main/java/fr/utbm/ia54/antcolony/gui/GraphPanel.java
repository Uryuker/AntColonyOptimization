package fr.utbm.ia54.antcolony.gui;

import fr.utbm.ia54.antcolony.gui.XMLToGraph;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class GraphPanel {
	
	public JPanel createGraphPanel(){
		JPanel graphPanel = new JPanel();
		//add graph to panel
		graphPanel.setLayout(new GridLayout(0, 1));
		graphPanel.add(new XMLToGraph().graphRender(), BorderLayout.CENTER);
		return graphPanel;
	}
}
