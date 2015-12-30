package fr.utbm.ia54.antcolony.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -905943631093499446L;

	public void createMainFrame(){
		//Create the frame
    	JFrame frame = new JFrame("Ant Colony Optimization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create split pane (graph on right and settings on left)
        JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        frame.getContentPane().add(splitPane, BorderLayout.CENTER);
        frame.setSize(1000, 800);
        frame.setVisible(true);
        splitPane.setLeftComponent(new SettingsPanel().createSettingsPanel());
        splitPane.setRightComponent(new GraphPanel().createGraphPanel());
    }
}
