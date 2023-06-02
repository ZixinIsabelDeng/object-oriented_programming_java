// -----------------------------------------------------
// Assignment 3
// Part: 1,2
// Written by: Zixin Deng 40047744
// -----------------------------------------------------





package file;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * 
 * @author Zixin Deng(40047744)
 * COMP249
 * Assignment #3
 * Due Date Saturday, 27 March 2023
 */



public class Binaryfilehandler   {
	private String[] bfile = { "Cartoons_Comics.csv.ser", "Hobbies_Collectibles.csv.ser", "Movies_TV_Books.csv.ser",
			"Music_Radio_Books.csv.ser", "Nostalgia_Eclectic_Books.csv.ser", "Old_Time_Radio_Books.csv.ser",
			"Sports_Sports_Memorabilia.csv.ser", "Trains_Planes_Automobiles.csv.ser" };

	private String[] file_to_read = { "Cartoons_Comics_Books.csv.txt", "Hobbies_Collectibles_Books.csv.txt",
			"Movies_TV.csv.txt", "Music_Radio_Books.csv.txt", "Nostalgia_Eclectic_Books.csv.txt", "Old_Time_Radio.txt",
			"Sports_Sports_Memorabilia.csv.txt", "Trains_Planes_Automobiles.csv.txt", };
   
	private Book[]shelf;
	
	
	
	private int[] records = new int[8];

	
	/**
	 * This constructor write the binary file and read the binary objects from binary file
	 */
	public Binaryfilehandler() {
		try {
			//opening all the Scanners and binary file readers and writers
			int book_total = 0;
			Scanner[] sc = new Scanner[file_to_read.length];
			ObjectOutputStream[] oos = new ObjectOutputStream[bfile.length];
			ObjectInputStream[] ois = new ObjectInputStream[bfile.length];
			for (int i = 0; i < file_to_read.length; i++) {
				sc[i] = new Scanner(new FileInputStream(file_to_read[i]));
				oos[i] = new ObjectOutputStream(new FileOutputStream(bfile[i]));
				ois[i] = new ObjectInputStream(new FileInputStream(bfile[i]));
			}

			for (int i = 0; i < file_to_read.length; i++) {
				records[i] = getfilelength(file_to_read[i]);
				book_total = book_total + records[i];
			}			
			String book_to_be, title, author, ISBN, genre;
			double price;
			int year;
			int counter = 0;
			shelf = new Book[book_total];
			StringTokenizer st;

			for (int i = 0; i < file_to_read.length; i++) {
				if (records[i] == 0) {
					oos[i].writeUTF("");
					continue;
				} else {
					Book b;

					while (sc[i].hasNextLine()) {
						book_to_be = sc[i].nextLine();
						st = new StringTokenizer(book_to_be);
						title = st.nextToken("|||");
						author = st.nextToken("|||");
						price = Double.parseDouble(st.nextToken("|||"));
						ISBN = st.nextToken("|||");
						genre = st.nextToken("|||");
						year = Integer.parseInt(st.nextToken("|||"));

						b = new Book(title, author, price, ISBN, genre, year);
						oos[i].writeObject(b);

					}

					counter = 0;

				}

			}

			Book book_read_from_bfile;
			int counter2 = 0;
			for (int i = 0; i < file_to_read.length; i++) {

				try {
					while (true) {
						book_read_from_bfile = (Book) ois[i].readObject();
						shelf[counter2] = book_read_from_bfile;
						counter2++;
					}
				} catch (ClassNotFoundException e) {
					System.out.println("Error has occurred while reading the file: " + ".");
				} catch (EOFException e) {
					System.out.println();

				}

			}

			for (int i = 0; i < sc.length; i++) {
				sc[i].close();
			}
			for (int i = 0; i < oos.length; i++) {
				oos[i].close();
			}

		} catch (FileNotFoundException e) {
			System.out.print("No such file");

		} catch (IOException e) {
			System.out.print("This is an IOException");
		
		}
	}
	
	
	
	public static int getfilelength(String filename) {

		int count;
		try {
			Path file = Paths.get(filename);
			count = (int) Files.lines(file).count();
		} catch (IOException e) {
			System.out.print("This is an IO Exception");
			return -1;
		}

		return count;
	}
/**
 * getters and setters
 * @return
 */
	public Book[] getShelf() {
		Book [] copy = new Book[shelf.length];
		for(int i=0;i<copy.length;i++) {
			copy[i]=new Book(shelf[i]);
		}
		return copy;
	}

	public void setShelf(Book[] shelf) {
		this.shelf = shelf;
	}

	public int[] getRecords() {
		
		int [] copy = new int[records.length];
		for(int i=0;i<records.length;i++) {
			copy[i]=records[i];
			}
		return copy;
	}

	public void setRecords(int[] records) {
		this.records = records;
	}

	
	
	
	
	
	
}
