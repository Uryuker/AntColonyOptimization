package fr.utbm.ia54.antcolony.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingsPanel {
	public JPanel createSettingsPanel(){
		
	JTextField textField = new JTextField();;
    JPanel settingsPanel = new JPanel();
    settingsPanel.setLayout( new GridLayout(15,1) );
    JPanel antPanel = new JPanel();
    antPanel.setLayout(new GridLayout(1,2));
    antPanel.setVisible(true);
    //settingsPanel.add( new JButton( "Button 1" ) );
    
    JSlider ant = new JSlider(0,5000);
    textField.setText(String.valueOf(ant.getValue()));
    ant.setPaintLabels(true);
    ant.setPaintTicks(true);
    ant.setMajorTickSpacing(1000);
    ant.setMinorTickSpacing(500);
    //add listener for completing the textbox
    ChangeListener listener  = new ChangeListener()
    { 
        // update text field when the slider value changes
		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			JSlider source = (JSlider) e.getSource();
			textField.setText("" + source.getValue());
		}
    };
	ant.addChangeListener(listener);
	antPanel.add(new JLabel("Number of ants : ", SwingConstants.CENTER));
	antPanel.add(textField, BorderLayout.NORTH);
    settingsPanel.add(antPanel);
	settingsPanel.add( ant, BorderLayout.CENTER);	    
    return settingsPanel;
	}

}
