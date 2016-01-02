package fr.utbm.ia54.antcolony.gui;

import fr.utbm.ia54.antcolony.gui.RandomGraph;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingsPanel {
	
	private JLabel separator1 = new JLabel();
	private JLabel separator2 = new JLabel();
	private JLabel separator3 = new JLabel();
	private JTextField antValue = new JTextField();
	private JTextField connexityValue = new JTextField();
	private JTextField pheromonesValue = new JTextField();
	private JTextField nodeNumber = new JTextField();
	private JSlider antSlider = new JSlider() ;
	private JSlider pheromonesSlider = new JSlider();
	private JSlider connexitySlider = new JSlider();
	private JButton playButton = new JButton(); 
	private JButton pauseButton = new JButton();
	private JButton stopButton = new JButton();
	private JButton generateButton = new JButton();
	private JPanel settingsPanel = new JPanel();
	private JPanel buttonPanel=new JPanel();

	private BufferedImage image;
	private BufferedImage buttonIcon;
	
	
	
	public JPanel createSettingsPanel() {

		// First line separator for beter rendering
		try {
			this.image = ImageIO.read(new File("src/img/line.png"));
			this.separator1 = new JLabel(new ImageIcon(this.image));
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		// Second line separator for beter rendering
		try {
			this.image = ImageIO.read(new File("src/img/line.png"));
			this.separator2 = new JLabel(new ImageIcon(this.image));
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		// Third line separator for beter rendering
		try {
			this.image = ImageIO.read(new File("src/img/line.png"));
			this.separator3 = new JLabel(new ImageIcon(this.image));
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		// Creating play/pause/stop buttons
		//play button
		try {
			this.buttonIcon = ImageIO.read(new File("src/img/play.png"));
			this.playButton = new JButton(new ImageIcon(this.buttonIcon));
			this.playButton.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// pause button
		try {
			this.buttonIcon = ImageIO.read(new File("src/img/pause.png"));
			this.pauseButton = new JButton(new ImageIcon(this.buttonIcon));
			this.pauseButton.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// stop button
		try {
			this.buttonIcon = ImageIO.read(new File("src/img/stop.png"));
			this.stopButton = new JButton(new ImageIcon(this.buttonIcon));
			this.stopButton.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Slider for the number of ants
		this.antSlider = new JSlider(0, 10000);
		this.antValue.setText(String.valueOf(this.antSlider.getValue()));
		this.antValue.setPreferredSize(new Dimension(100, 15));
		this.antSlider.setPaintLabels(true);
		this.antSlider.setPaintTicks(true);
		this.antSlider.setMajorTickSpacing(1500);
		this.antSlider.setMinorTickSpacing(500);
		// add listener for completing the textbox
		ChangeListener antListener = new ChangeListener() {
			// update text field when the slider value changes
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				antValue.setText("" + source.getValue());
			}
		};
		this.antSlider.addChangeListener(antListener);

		// Slider for the pheromone evaporation rate
		this.pheromonesSlider = new JSlider(0, 100);
		this.pheromonesValue.setText(String.valueOf(this.pheromonesSlider.getValue()));
		this.pheromonesValue.setPreferredSize(new Dimension(100, 15));
		this.pheromonesSlider.setPaintLabels(true);
		this.pheromonesSlider.setPaintTicks(true);
		this.pheromonesSlider.setMajorTickSpacing(10);
		this.pheromonesSlider.setMinorTickSpacing(5);
		// add listener for completing the textbox
		ChangeListener pheromonesListener = new ChangeListener() {
			// update text field when the slider value changes
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				pheromonesValue.setText("" + source.getValue());
			}
		};
		this.pheromonesSlider.addChangeListener(pheromonesListener);
		
		//Graph gneration settings
		//Node Number text field
		this.nodeNumber.setText("10");
		this.nodeNumber.setPreferredSize(new Dimension(100, 15));
		
		//Connexity slider
		this.connexitySlider = new JSlider(0, 100);
		this.connexityValue.setText(String.valueOf(this.connexitySlider.getValue()));
		this.connexityValue.setPreferredSize(new Dimension(100, 15));
		this.connexitySlider.setPaintLabels(true);
		this.connexitySlider.setPaintTicks(true);
		this.connexitySlider.setMajorTickSpacing(10);
		this.connexitySlider.setMinorTickSpacing(5);
		// add listener for completing the textbox
		ChangeListener connexityListener = new ChangeListener() {
			// update text field when the slider value changes
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				connexityValue.setText("" + source.getValue());
			}
		};
		this.connexitySlider.addChangeListener(connexityListener);
		
		//Generate Button
		this.generateButton = new JButton("Generate random graph");
		this.generateButton.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		this.generateButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  //Generate the graph with the properties given
		    new RandomGraph().generateGraph(Integer.parseInt(nodeNumber.getText()),(float)Integer.parseInt(connexityValue.getText()));
		    Robot robot = null;
			try {
				robot = new Robot();
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
			//simulate click on panel to trigger event to refresh the graph panel
			Point mousePos = MouseInfo.getPointerInfo().getLocation();
			robot.mouseMove(50,50);
		    robot.mousePress(InputEvent.BUTTON1_MASK);
		    robot.mouseRelease(InputEvent.BUTTON1_MASK);
		    robot.mouseMove((int)mousePos.getX(), (int)mousePos.getY());
		  }
		});
		
		// packing the panel
		this.settingsPanel.setLayout(new GridBagLayout());
		this.buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth=1;
		c.ipady = 20;
		c.ipadx = 10;
		c.insets = new Insets(0,15,0,15);
		// play button
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		this.buttonPanel.add(this.playButton, c);
		// pause button
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		this.buttonPanel.add(this.pauseButton, c);
		// stop button
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		this.buttonPanel.add(this.stopButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth=3;
		c.gridx = 0;
		c.gridy = 0;
		this.settingsPanel.add(this.buttonPanel, c);
		
		// adding the separator
		c.insets = new Insets(0,5,0,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		this.settingsPanel.add(this.separator1, c);

		// Packing the ant slider
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		this.settingsPanel.add(new JLabel("    Number of ants : "), c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		this.settingsPanel.add(this.antValue, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 4;
		this.settingsPanel.add(this.antSlider, c);

		// adding the separator
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 5;
		this.settingsPanel.add(this.separator2, c);

		// Packing the pheromone slider
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 6;
		this.settingsPanel.add(new JLabel("    Pheromone evaporation rate : "), c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 6;
		this.settingsPanel.add(this.pheromonesValue, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 8;
		this.settingsPanel.add(this.pheromonesSlider, c);

		// adding the separator
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 9;
		this.settingsPanel.add(this.separator3, c);

		//Graph Generation
		//nodes
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 11;
		this.settingsPanel.add(new JLabel("    Number of nodes : "), c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 11;
		this.settingsPanel.add(this.nodeNumber, c);
		
		//connexity slider
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 12;
		this.settingsPanel.add(new JLabel("    connexity  rate : "), c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 12;
		this.settingsPanel.add(this.connexityValue, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 14;
		this.settingsPanel.add(this.connexitySlider, c);
		
		//generate button
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth=3;
		c.gridx = 0;
		c.gridy = 15;
		this.settingsPanel.add(this.generateButton, c);

		
		return this.settingsPanel;
	}
}
