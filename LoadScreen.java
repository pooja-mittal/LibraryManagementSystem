package com.puja.library.view;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class LoadScreen extends  JFrame{
	
	private static final long serialVersionUID = 1L; 	
	private JPanel panel;
	private JLabel jlmessage;
	private JButton jbNew, jbLoad, jbExit;
	
	
	public LoadScreen(String title) {
		super(title);
		initWidgets();
		addWidgets();
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(320,115);
		setResizable(false);			
	}


	private void addWidgets() {
		panel.add(jlmessage);
		panel.add(jbLoad);
		panel.add(jbNew);
		panel.add(jbExit);
		panel.setBackground(new Color(190,234,248));
		setContentPane(panel);
	}


	private void initWidgets() {
		panel=new JPanel(new FlowLayout());
		jlmessage=new JLabel("would you like to start a new library ? or load one up? ");
		jbLoad=new JButton("load saved library");
		jbNew=new JButton("start new Library");
		jbExit=new JButton("exit"); 		
	}

		public void addActionListener(ActionListener l) {
			jbNew.addActionListener(l);
			jbLoad.addActionListener(l);
			jbExit.addActionListener(l);
		}
		
		public JButton getButtonLoad() {
			return jbLoad;
		}
		
		public JButton getButtonNew() {
			return jbNew;
		}
		
		public JButton getButtonExit() {
			return jbExit;
		}
}
