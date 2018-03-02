package com.puja.library.controller;

import com.puja.library.view.MyTableModel;
import com.puja.library.model.Book;
import com.puja.library.model.Library;
import com.puja.library.model.VIM;
import com.puja.library.view.AddBookPanel;
import com.puja.library.view.BrowseLibraryPanel;
import com.puja.library.view.LibraryInterface;
import com.puja.library.view.LoadScreen;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.util.ArrayList;
import java.util.List;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LibrarySystem implements ChangeListener, ActionListener {

	private LibraryInterface screen;
	private AddBookPanel abp;
	private BrowseLibraryPanel blp;
	private Book book;

	private JFileChooser chooser;
	private FileFilter filter, filter2;
	private int resultcode;
	private File vimFile, saveFile, libFile;
	private Library lib;
	private List<VIM> vimCache;
	private String[] validFileTypes = { ".wav", ".mp3", ".avi", ".mp4", ".png", ".jpeg" };
	// private JOptionPane dlg;

	private String fileName;
	private FileInputStream fis;
	private String validFileTypereminder;
	private FileOutputStream fos;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	private String[][] dataBook, dataFile;

	private LoadScreen ls;

	private boolean exit;

	public LibrarySystem() {

		initEventAttributes();
		screen = new LibraryInterface("Library System");
		abp = screen.getAddBookPanel();
		blp = screen.getBrowseLibraryPanel();

		screen.getTabbedpane().addChangeListener(this);
		abp.addActionListener(this);
		blp.addActionListener(this);

		// 1. choose file name to open

		ls = new LoadScreen("welcome");
		ls.addActionListener(this);
		ls.setVisible(true);
		// screen.setVisible(true);

	}

	private void initEventAttributes() {
		chooser = new JFileChooser();
		filter = new FileNameExtensionFilter("video/image/music files ", "wav", "mp3", "avi", "mp4", "png", "jpeg",
				"jpg");
		filter2 = new FileNameExtensionFilter("Library files", "ser");
		chooser.addChoosableFileFilter(filter);
		chooser.addChoosableFileFilter(filter2);

		lib = new Library();
		exit = false;
		vimFile = null;
		saveFile = null;
		vimCache = new ArrayList<VIM>();
		validFileTypereminder = "valid file type ends with .wav, .mp3, .avi, .mp4, .png, .jpeg, .jpg";

		libFile = null;
		fileName = "library";
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// when we press tab from add book to browse library
		if (screen.getTabbedpane().getSelectedIndex() == 1) {
			screen.getTabbedpane().setTitleAt(1, screen.getFiller() + screen.getFiller() + " browse library "
					+ screen.getFiller() + screen.getFiller());
			screen.setSize(360, 440);

		} else {
			screen.getTabbedpane().setTitleAt(1, screen.getFiller() + " browse library" + screen.getFiller());
			screen.setSize(320, 480);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == abp.getButtonBrowse()) {
			openChooserAndSetTheVimFile();

		} else if (e.getSource() == abp.getButtonAddFile()) {
			addVimFiletoVimCache();
		} else if (e.getSource() == abp.getButtonAddBook()) {
			addVimFilesInvimCacheToBookAndAddBookToLibrary();
			reloadDataBook();
			reloadDataFile();
		} else if (e.getSource() == abp.getButtonListAllBooks()) {
			listAllBooksInLibrary();

		} else if (e.getSource() == abp.getButtonSave()) {
			save();

		} else if (e.getSource() == abp.getButtonSaveAndQuit()) {
			saveAndQuit();
		} else if (e.getSource() == blp.getButtonDeleteBook()) {

		} else if (e.getSource() == blp.getButtonOpenBook()) {
			openBook();

		} else if (e.getSource() == blp.getButtonDeleteFile()) {

		} else if (e.getSource() == blp.getButtonOpenFile()) {
			openFile();

		} else if (e.getSource() == blp.getButtonSave()) {
			save();

		} else if (e.getSource() == blp.getButtonSaveAndQuit()) {
			saveAndQuit();
		} else if (e.getSource() == ls.getButtonLoad()) {

			reloadDataBook();
			reloadDataFile();
			loadLibrary();
			chooser.setFileFilter(filter);

		} else if (e.getSource() == ls.getButtonNew()) {
			ls.dispose();
			screen.setVisible(true);

		} else if (e.getSource() == ls.getButtonExit()) {
			System.exit(0);
		}
	}

	private void openFile() {
		int row, column;
		String fileName;
		VIM v;
		File file;

		row = ((JTable) blp.getFileTable()).getSelectedRow();
		column =  ((JTable) blp.getFileTable()).getSelectedColumn();
		fileName=((JTable) blp.getFileTable()).getValueAt(row, column).toString();
		v=book.getVIMByName(fileName);
		
		try {
			file=new File(v.getName());
			fos=new FileOutputStream(file);
			out=new ObjectOutputStream(fos);
			fos.close();
		Desktop.getDesktop().open(new File(fileName));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}

	private void openBook() {
		int row, column;
		String isbn;
		
		row = ((JTable) blp.getBookTable()).getSelectedRow();
		column = 3;
		isbn = ((JTable) blp.getBookTable()).getValueAt(row, column).toString();
		book = lib.getBookByIsbn(isbn);
		dataFile = book.toStringVectorFiles();
		reloadDataFile();

	}

	private void reloadDataFile() {
		while (((MyTableModel) blp.getFileTable().getModel()).getRowCount() > 0) {
			((MyTableModel) blp.getFileTable().getModel()).removeRow(0);
		}
		if (dataFile != null) {
			for (int i = 0; i < dataFile.length; i++) {
				((MyTableModel) blp.getFileTable().getModel()).insertRow(i, dataFile[i]);
			}
		}
	}

	private void loadLibrary() {
		chooser.setFileFilter(filter2);
		resultcode = chooser.showOpenDialog(screen);
		if (resultcode == JFileChooser.APPROVE_OPTION) {
			libFile = chooser.getSelectedFile();
			try {
				fis = new FileInputStream(libFile);
				in = new ObjectInputStream(fis);
				lib = (Library) (in.readObject());
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			ls.dispose();

			reloadDataBook();

			screen.setVisible(true);
		}

	}

	private void reloadDataBook() {
		while (((MyTableModel) blp.getBookTable().getModel()).getRowCount() > 0) {
			((MyTableModel) blp.getBookTable().getModel()).removeRow(0);
		}

		dataBook = lib.toStringVector();
		for (int i = 0; i < dataBook.length; i++) {
			((MyTableModel) blp.getBookTable().getModel()).addRow(dataBook[i]);

		}

	}

	private void saveAndQuit() {
		save();
		if (exit)
			System.exit(0);

	}

	private void save() {
		fileName = JOptionPane.showInputDialog(screen, "enter file name to save as ", "save",
				JOptionPane.INFORMATION_MESSAGE);
		if (fileName != null) {
			if (!fileName.trim().contentEquals("")) {
				fos = null;
				out = null;
				try {
					saveFile = new File(fileName + " .ser");
					if (!saveFile.exists()) {
						fos = new FileOutputStream(saveFile);
						out = new ObjectOutputStream(fos);
						out.writeObject(lib);
						fos.close();
						out.close();
						exit = true;
					} else {
						int resultCode = JOptionPane.showConfirmDialog(screen,
								"file this name already exists \n overwrite it?", "warning", JOptionPane.YES_NO_OPTION,
								JOptionPane.WARNING_MESSAGE);
						if (resultCode == JOptionPane.YES_OPTION) {
							fos = new FileOutputStream(saveFile);
							out = new ObjectOutputStream(fos);
							out.writeObject(lib);
							fos.close();
							out.close();
							exit = true;
						} else {
							abp.getTextAreaLog().append("\n save cancelled");
							exit = false;
						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				abp.getTextAreaLog().append("\n save cancelled");
				exit = false;
			}
		} else {
			abp.getTextAreaLog().append("\n save cancelled");
			exit = false;
		}
	}

	private void listAllBooksInLibrary() {
		abp.getTextAreaLog().setText("> Listing all files in library...");
		abp.getTextAreaLog().append(lib.toString());
	}

	// total no in int is 2^31-1;
	private void addVimFilesInvimCacheToBookAndAddBookToLibrary() {
		boolean IsISBNAlreadyExist = false;
		boolean AllFieldAlreFilled = false;
		int isbn = 0;
		double price = 0.0;
		Book b;

		if ((!abp.getTextFieldIsbn().getText().trim().contentEquals(""))
				&& (!abp.getTextFieldTitle().getText().trim().contentEquals(""))
				&& (!abp.getTextFieldAuthor().getText().trim().contentEquals(""))
				&& (!abp.getTextFieldPrice().getText().trim().contentEquals(""))) {
			AllFieldAlreFilled = true;

		}

		if (AllFieldAlreFilled) {
			try {
				isbn = Integer.parseInt(abp.getTextFieldIsbn().getText().trim());
				price = Double.parseDouble(abp.getTextFieldPrice().getText().trim());

				IsISBNAlreadyExist = lib.doesISBNAlreadyExist(isbn);
				if (IsISBNAlreadyExist) {
					JOptionPane.showMessageDialog(screen, isbn + " is alreeady exist \n please enter another isbn ");
				} else {
					b = new Book(isbn, abp.getTextFieldTitle().getText().trim(),
							abp.getTextFieldAuthor().getText().trim(), price);
					for (int i = 0; i < vimCache.size(); i++) {
						b.addVim(vimCache.get(i));
					}
					lib.addBook(b);
					abp.getTextFieldIsbn().setText("");
					abp.getTextFieldTitle().setText("");
					abp.getTextFieldAuthor().setText("");
					abp.getTextFieldPrice().setText("");
					abp.getTextAreaLog().append("\n> " + b.getTitle() + " has been added to the library");
					vimCache = new ArrayList<VIM>();
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(screen, " isbn or price is not a number");
			}

		} else {
			JOptionPane.showMessageDialog(screen, "please fill out all non-optional fields");
		}
	}

	private void addVimFiletoVimCache() {
		if (vimFile != null) {
			for (int i = 0; i < validFileTypes.length; i++) {
				if (abp.getTextFieldFile().getText().trim().endsWith(validFileTypes[i])) {
					byte[] data = new byte[(int) vimFile.length()];
					try {
						fis = new FileInputStream(vimFile);
						fis.read(data);
						fis.close();
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(screen, "file not found");
					} catch (IOException e) {
						JOptionPane.showMessageDialog(screen, "Error reading file");
					}

					VIM v = new VIM(abp.getTextFieldFile().getText().trim(), data);
					vimCache.add(v);
					vimFile = null;
					abp.getTextAreaLog()
							.append("\n> " + abp.getTextFieldFile().getText().trim() + " is ready to be added in book");
					abp.getTextFieldFile().setText("optional");
					return;
				}
			}
			JOptionPane.showMessageDialog(screen, "something went wrong\nplease click browser again");

		} else {
			JOptionPane.showMessageDialog(screen,
					abp.getTextFieldFile().getText().trim() + "is not valid file type \n" + validFileTypereminder);
		}

	}

	void openChooserAndSetTheVimFile() {
		resultcode = chooser.showOpenDialog(screen);
		if (resultcode == JFileChooser.APPROVE_OPTION) {
			vimFile = chooser.getSelectedFile();
			abp.getTextFieldFile().setText(vimFile.getName());
		}
	}
}
