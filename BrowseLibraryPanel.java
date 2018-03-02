package com.puja.library.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.puja.library.controller.LibrarySystem;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;

public class BrowseLibraryPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Box mainBox, hBox1, hBox2, hBox3, hBox4, hBox5;
	private JLabel jlBookTable, jlFileTable;
	private JButton jDeleteBook, jOpenBook, jDeleteFile, jOpenFile, jSave, jSaveAndQuit;
	private JScrollPane spBookTable, spFileTable;
	private JTable tBooks, tFile;
	private DefaultTableModel model;

	String[] bookColoumns = { "Title", "Author", "Price", "SerialNumber" };
	String[] fileColumns = { "Sounds", "Images", "Videos" };
	String[][] bookData = { { " ", " ", " " } };
	String[][] fileData = { { " ", " ", " " } };

	public BrowseLibraryPanel() {
		super(new FlowLayout());
		initWidgets();
		addWidgets();
		setBackground(new Color(194, 230, 248));
	}

	public void initWidgets() {
		mainBox = Box.createVerticalBox();
		hBox1 = Box.createHorizontalBox();
		hBox2 = Box.createHorizontalBox();
		hBox3 = Box.createHorizontalBox();
		hBox4 = Box.createHorizontalBox();
		hBox5 = Box.createHorizontalBox();

		jlBookTable = new JLabel("showing all books in library");
		jlFileTable = new JLabel("showing all files in book");

		jDeleteBook = new JButton("delete");
		jOpenBook = new JButton("Open");
		jDeleteFile = new JButton("delete");
		jOpenFile = new JButton("Open");
		jSave = new JButton("Save");
		jSaveAndQuit = new JButton("SaveandQuit");

		model = new MyTableModel(bookData, bookColoumns);
		tBooks = new JTable(model);
		tBooks.setPreferredScrollableViewportSize(new Dimension(328, 120));
		tBooks.setFillsViewportHeight(true);
		tBooks.setAutoCreateRowSorter(true);
		tBooks.getTableHeader().setReorderingAllowed(false);
		tBooks.getColumnModel().getColumn(0).setPreferredWidth(200);
		tBooks.getColumnModel().getColumn(1).setPreferredWidth(120);

		model = new MyTableModel(fileData, fileColumns);
		tFile = new JTable(model);
		tFile.setPreferredScrollableViewportSize(new Dimension(320, 80));
		tFile.setFillsViewportHeight(true);
		tFile.setAutoCreateRowSorter(true);
		tFile.getTableHeader().setReorderingAllowed(false);

		spBookTable = new JScrollPane(tBooks);
		spBookTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		spBookTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		spFileTable = new JScrollPane(tFile);
		spFileTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		spFileTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

	}

	public void addWidgets() {
		hBox1.add(jlBookTable);
		hBox1.add(Box.createHorizontalStrut(20));
		hBox1.add(jDeleteBook);
		hBox1.add(jOpenBook);
		hBox2.add(spBookTable);
		hBox3.add(jlFileTable);
		hBox3.add(Box.createHorizontalStrut(20));
		hBox3.add(jDeleteFile);
		hBox5.add(Box.createHorizontalStrut(5));
		hBox3.add(jOpenFile);
		hBox4.add(spFileTable);
		hBox5.add(Box.createHorizontalStrut(140));
		hBox5.add(jSave);
		hBox5.add(Box.createHorizontalStrut(5));
		hBox5.add(jSaveAndQuit);

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

		add(mainBox);

	}

	public void addActionListener(ActionListener a) {
		jDeleteBook.addActionListener(a);
		jOpenBook.addActionListener(a);
		jDeleteFile.addActionListener(a);
		jOpenFile.addActionListener(a);
		jSave.addActionListener(a);
		jSaveAndQuit.addActionListener(a);

	}

	// jDeleteBook, jOpenBook, jDeleteFile, jOpenFile, jSave, jSaveAndQuit
	public JButton getButtonDeleteBook() {
		return jDeleteBook;
	}

	public JButton getButtonOpenBook() {
		return jOpenBook;
	}

	public JButton getButtonDeleteFile() {
		return jDeleteFile;
	}

	public JButton getButtonOpenFile() {
		return jOpenFile;
	}

	public JButton getButtonSave() {
		return jDeleteBook;
	}

	public JButton getButtonSaveAndQuit() {
		return jDeleteBook;
	}
	
	public JTable getBookTable() {
		return tBooks;
	}
	
	public JTable getFileTable() {
		return tFile;
	}

}