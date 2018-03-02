package com.puja.library.model;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
public class Library implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Book> collection;
	
	public Library() {
		collection=new ArrayList<Book>();
	}
	
	public void addBook(Book book) {
		collection.add(book);
	}
	
	public String toString() {
		String total="\n\n";
		 /*for(int i=0; i<collection.size(); i++) {
			Book b=collection.get(i);
			total=total+b.toString();
		} */
		
		Iterator<Book> itr=collection.iterator();
		while(itr.hasNext()) {
			Book b=(Book)itr.next();
			total=total+b.toString();
		}
		return total;
	}

	public boolean doesISBNAlreadyExist(int isbn) {
		Iterator<Book> itr=collection.iterator();
		while(itr.hasNext()) {
		if(itr.next().getIsbn()==isbn) {
			return true;
		}
		}
		return false;
	}
	
	public String[][] toStringVector() {
		String[][] total=new String[collection.size()][5];
		for(int i=0; i<collection.size(); i++) {
			total[i][0]=collection.get(i).getTitle();
			total[i][1]=collection.get(i).getAuthor();
			total[i][2]=collection.get(i).getPrice();
			total[i][3]=collection.get(i).getISBN();
			
		}
		return total;
	}

	public Book getBookByIsbn(String isbn) {
		for(int i=0; i<collection.size(); i++) {
			if(collection.get(i).getISBN().contentEquals(isbn))
				return collection.get(i);
		}
		return null;
	}
}

