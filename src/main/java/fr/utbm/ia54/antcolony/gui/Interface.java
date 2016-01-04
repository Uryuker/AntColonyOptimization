package fr.utbm.ia54.antcolony.gui;
import fr.utbm.ia54.antcolony.gui.MainFrame;
import javax.swing.SwingUtilities;

public class Interface{
	

	public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	//Setting the frame
                MainFrame mf = new MainFrame();
                mf.createMainFrame();
               
            }
        });
    }
	
}