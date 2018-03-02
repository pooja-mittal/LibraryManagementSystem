package com.puja.library.view;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class AddBookPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Box mainBox, hBox1, hBox2, hBox3, hBox4, hBox5, hBox6, hBox7, hBox8;
	private JLabel jlIsbn, jlTitle, jlAuthor, jlPrice, jlFile, jlLogDog;
	private JTextField jtIsbn, jtTitle, jtAuthor, jtPrice, jtFile;
	private JButton jBrowse, jAddFile, jAddBook, jSave, jSaveAndQuit, jListAllBooks;
	private JTextArea jtaLog;
	private JScrollPane scrollPane;

	public AddBookPanel() {
		super(new FlowLayout());
		initWidgets();
		addWidgets();
		setBackground(new Color(190, 234, 248));
	}

	private void initWidgets() {
		mainBox = Box.createVerticalBox();
		hBox1 = Box.createHorizontalBox();
		hBox2 = Box.createHorizontalBox();
		hBox3 = Box.createHorizontalBox();
		hBox4 = Box.createHorizontalBox();
		hBox5 = Box.createHorizontalBox();
		hBox6 = Box.createHorizontalBox();
		hBox7 = Box.createHorizontalBox();
		hBox8 = Box.createHorizontalBox();
		jlIsbn = new JLabel("enter ISBN:       ");
		jlTitle = new JLabel("enter Title:        ");
		jlAuthor = new JLabel("enter Author:   ");
		jlPrice = new JLabel("enter Price:      ");
		jlFile = new JLabel("Add New File:   ");
		jlLogDog = new JLabel("Log Dog:     ");

		jtIsbn = new JTextField(19);
		jtTitle = new JTextField(19);
		jtAuthor = new JTextField(19);
		jtPrice = new JTextField(19);
		jtFile = new JTextField(19);
		jtFile.setText("optional");

		jtIsbn.setHorizontalAlignment(JTextField.RIGHT);
		jtTitle.setHorizontalAlignment(JTextField.RIGHT);
		jtAuthor.setHorizontalAlignment(JTextField.RIGHT);
		jtPrice.setHorizontalAlignment(JTextField.RIGHT);
		jtFile.setHorizontalAlignment(JTextField.RIGHT);

		jBrowse = new JButton("Broswe      ");
		jAddFile = new JButton("Add File to Books");
		jAddBook = new JButton("Add Book To Library ");
		jSave = new JButton("Save     ");
		jSaveAndQuit = new JButton("Save and Quit");
		jListAllBooks= new JButton("List  all books");

		jtaLog = new JTextArea(10, 24);
		jtaLog.setEditable(false);

		scrollPane = new JScrollPane(jtaLog);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

	}

	void addWidgets() {
		hBox1.add(jlIsbn);
		hBox1.add(jtIsbn);
		hBox2.add(jlTitle);
		hBox2.add(jtTitle);
		hBox3.add(jlAuthor);
		hBox3.add(jtAuthor);
		hBox4.add(jlPrice);
		hBox4.add(jtPrice);
		hBox5.add(jlFile);
		hBox5.add(jtFile);
		hBox6.add(Box.createHorizontalStrut(83));
		hBox6.add(jBrowse);
		hBox6.add(Box.createHorizontalStrut(5));
		hBox6.add(jAddFile);
		hBox7.add(jlLogDog);
		hBox7.add(Box.createHorizontalStrut(80));
		hBox7.add(jAddBook);
		hBox8.add(jListAllBooks);
		hBox8.add(Box.createHorizontalStrut(15));
		hBox8.add(jSave);
		hBox8.add(Box.createHorizontalStrut(5));
		hBox8.add(jSaveAndQuit);

		mainBox.add(hBox1);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox2);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox3);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox4);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox5);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox6);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox7);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(scrollPane);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox8);
		add(mainBox);

	}

	// jBrowse, jAddFile, jAddBook, jSave, jSaveAndQuit
	public void addActionListener(ActionListener a) {
		jBrowse.addActionListener(a);
		jAddFile.addActionListener(a);
		jAddBook.addActionListener(a);
		jSave.addActionListener(a);
		jSaveAndQuit.addActionListener(a);
		jListAllBooks.addActionListener(a);

	}

	public JButton getButtonBrowse() {
		return jBrowse;
	}

	public JButton getButtonAddFile() {
		return jAddFile;
	}

	public JButton getButtonAddBook() {
		return jAddBook;
	}

	public JButton getButtonSave() {
		return jSave;
	}

	public JButton getButtonSaveAndQuit() {
		return jSaveAndQuit;
	}
	
	public JButton getButtonListAllBooks() {
		return jListAllBooks;
	}

	// jtIsbn, jtTitle, jtAuthor, jtPrice, jtFile
	public JTextField getTextFieldIsbn() {
		return jtIsbn;
	}

	public JTextField getTextFieldTitle() {
		return jtTitle;
	}

	public JTextField getTextFieldAuthor() {
		return jtAuthor;
	}

	public JTextField getTextFieldPrice() {
		return jtPrice;
	}

	public JTextField getTextFieldFile() {
		return jtFile;
	}
	
	public JTextArea getTextAreaLog() {
		return jtaLog;
	}
}
