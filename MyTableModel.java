package com.puja.library.view;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	public MyTableModel(String[][] data, String[] coloums) {
		super(data, coloums);
	}

	@Override
	public boolean isCellEditable(int rows, int columns) {
		return false;
	}
}
