package fr.utbm.ia54.antcolony.gui;
/**
 * @author quentin barthelemy
 *
 */

import fr.utbm.ia54.antcolony.gui.XMLToGraph;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import org.graphstream.ui.swingViewer.View;

public class GraphPanel {
	View gp;

	public JPanel createGraphPanel() {
		JPanel graphPanel = new JPanel();

		// add graph to panel
		graphPanel.setLayout(new GridLayout(0, 1));
		gp = new XMLToGraph().graphRender();
		graphPanel.add(gp, BorderLayout.CENTER);

		graphPanel.removeAll();
		gp = new XMLToGraph().graphRender();
		graphPanel.add(gp, BorderLayout.CENTER);
		graphPanel.repaint();
		graphPanel.revalidate();
		return graphPanel;

	}
}
