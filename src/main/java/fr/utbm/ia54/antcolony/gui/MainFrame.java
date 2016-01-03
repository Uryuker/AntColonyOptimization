package fr.utbm.ia54.antcolony.gui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.graphstream.ui.swingViewer.View;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -905943631093499446L;

	public void createMainFrame() {
		// Create the frame
		JFrame frame = new JFrame("Ant Colony Optimization");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create split pane (graph on right and settings on left)
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		frame.setSize(1000, 800);
		frame.setVisible(true);
		splitPane.setLeftComponent(new SettingsPanel().createSettingsPanel());
		splitPane.setRightComponent(new GraphPanel().createGraphPanel());

		// Add listener so when random graph is pressed, the viewer refresh
		splitPane.getLeftComponent().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
				Point p = e.getLocationOnScreen();
				// If we click on (50,50) it es the graphrefresh
				if (p.getX() == 50 && p.getY() == 50) {
					((JPanel) splitPane.getRightComponent()).removeAll();
					View gp = new XMLToGraph().graphRender();
					((JPanel) splitPane.getRightComponent()).add(gp, BorderLayout.CENTER);
					((JPanel) splitPane.getRightComponent()).repaint();
					((JPanel) splitPane.getRightComponent()).revalidate();
				}

			}

		});

	}
}
