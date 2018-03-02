package com.puja.library.view;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.puja.library.controller.LibrarySystem;

public class LibraryInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTabbedPane jtb;
	private BrowseLibraryPanel blp;
	private AddBookPanel abp;
	private String filler;

	public LibraryInterface(String title) {

		super(title);

		abp = new AddBookPanel();
		blp = new BrowseLibraryPanel();
		jtb = new JTabbedPane();

		filler = "      ";
		jtb.addTab(filler + filler + " Add Book " + filler + filler, abp);
		jtb.addTab(filler + filler + " Browse Library " + filler, blp);

		add(jtb);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(335, 460);
		setResizable(false);

	}

	public AddBookPanel getAddBookPanel() {
		return abp;
	}

	public BrowseLibraryPanel getBrowseLibraryPanel() {
		return blp;
	}

	public JTabbedPane getTabbedpane() {
		return jtb;
	}

	public String getFiller() {
		return filler;
	}

	

}
