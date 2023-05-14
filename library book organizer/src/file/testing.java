// -----------------------------------------------------
// Assignment 3
// Part: 1,2
// Written by: Zixin Deng 40047744
// -----------------------------------------------------

package file;

import java.io.*;
import java.nio.file.*;
import Exception.*;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 * 
 * @author Zixin Deng(40047744)
 * COMP249
 * Assignment #3
 * Due Date Saturday, 27 March 2023
 */
public class testing {

	public static void main(String[] args) {
        //select the qualifying books and write them into genre file and two erro files
		//do_part_one_and_two();
	    //write binary file and read object
	    Binaryfilehandler b= new Binaryfilehandler();	
	}
	
	
	
	
	/**
	 * a collection of all the methids nedded for part one and half of part two
	 */
	public static void do_part_one_and_two() {
		Filehandler f = new Filehandler();
		final String[] validfile = f.getOldfile();
		final String[] file_to_write = f.getNewfile();
		validatebookinfo(validfile, file_to_write);	
	}


	/**
	 * 
	 * @param validfile prepared file for read 
	 * @param filename2 file to write
	 */
	public static void validatebookinfo(String[] validfile, String[] filename2) {

		try {
			// create a bunch of scanner
			Scanner[] sc = new Scanner[validfile.length];
			for (int i = 0; i < validfile.length; i++) {
				sc[i] = new Scanner(new FileInputStream(validfile[i]));
			}
			// create a bunch of pw
			PrintWriter[] pw = new PrintWriter[filename2.length];

			for (int i = 0; i < filename2.length; i++) {

				pw[i] = new PrintWriter(new FileOutputStream(filename2[i]), true);

			}

			int lines = 0;
			String[] book_String;
			for (int i = 0; i < validfile.length; i++) {

				book_String = new String[filelength(validfile[i])];
				while (sc[i].hasNextLine()) {

					book_String[lines] = sc[i].nextLine();
					lines++;
				}
				lines = 0;
				// System.out.print(lines);
				for (int j = 0; j < book_String.length; j++) {
					isbookvalid(validfile[i], book_String[j], processbook(book_String[j]), pw);
				}

			}
			
			for (int i = 0; i < sc.length; i++) {
				sc[i].close();
			}
			for (int i = 0; i < pw.length; i++) {
				pw[i].close();
			}
		} catch (FileNotFoundException e) {
			System.out.print("This is an FileNotFoundException");
		}

	}

	/**
	 * 
	 * @param filename the filename that you need to do the length check
	 * @return file length
	 */
	public static int filelength(String filename) {

		int count;
		try {
			Path file = Paths.get(filename);
			count = (int) Files.lines(file).count();
		} catch (IOException e) {
			System.out.print("This is an IO Exception");
			return 0;
		}

		return count;

	}
/**
 * 
 * @param book
 * @return dealing with book start with " and return an array of each elements of the book
 */
	public static String[] bookstartwithquotation(String book) {
		String[] container;
		container = book.split("\"");
		String title = container[1];
		String String_no_title;
		String_no_title = book.substring(title.length() + 3);

		String[] book_no_title;
		book_no_title = String_no_title.split(",");
		int t = 0;
		String[] book_with_title = new String[book_no_title.length + 1];
		for (int i = 0; i < book_with_title.length; i++) {
			if (i == 0)
				book_with_title[0] = title;
			else {
				book_with_title[i] = book_no_title[t];
				t++;
			}
		}
		return book_with_title;
	}
	
	/**
	 * 
	 * @param book1
	 * @return string array that includes all the six eements of the book
	 */

	public static String[] processbook(String book1) {
		String[] book_info;
		if (book1.charAt(0) == '\"') {
			return bookstartwithquotation(book1);
		} else {
			book_info = book1.split(",");
			return book_info;
		}
	}
    
	public static boolean hasemptyfield(String[] b) {
		for(int i=0;i<b.length;i++) {
			if(b[i].isEmpty()) return true;
		}
		return false;
		
	}
	
	
	public static void isbookvalid(String fileString, String bookString, String[] book, PrintWriter[] pw) {
		try {
			if (book.length > 6)
				throw new TooManyFiedsException();
			else if (book.length < 6||hasemptyfield(book))
				throw new TooFewFieldsException();
			else if (!Pattern.matches("(CCB)|(HCB)|(MTV)|(MRB)|(NEB)|(OTR)|(SSM)|(TPA)", book[4]))
				throw new UnknownGenreException();

			else if(!hasemptyfield(book)) {

				checksemanticerror(fileString,bookString,book,pw);
			}

		} catch (TooManyFiedsException e) {
			writeTooManyFiedsException(fileString, bookString, book, pw);

		}

		catch (TooFewFieldsException e) {
			writeTooFewFieldsException(fileString, bookString, book, pw);

		} catch (UnknownGenreException e) {
			writeUnknownGenreException(fileString, bookString, book, pw);

		}

	}
/**
 * 
 * @param fileString
 * @param bookString
 * @param book
 * @param pw
 */
	public static void writeTooManyFiedsException(String fileString, String bookString, String[] book,
			PrintWriter[] pw) {
		pw[8].println("syntax error in file: " + fileString);
		pw[8].println("====================");
		pw[8].println("Error: Too many fields");
		pw[8].println("Record: " + bookString);
		pw[8].println();

	}
/**
 * content to write in the syntax error file for a specific exception
 * @param fileString
 * @param bookstring
 * @param book
 * @param pw
 */
	public static void writeTooFewFieldsException(String fileString, String bookstring, String[] book,
			PrintWriter[] pw) {
		if (book[0].isEmpty()) {
			pw[8].println("syntax error in file: " + fileString);
			pw[8].println("====================");
			pw[8].println("Error: no title");
			pw[8].println("Record: " + bookstring);
			pw[8].println();

		}
		if (book[1].isEmpty()) {
			pw[8].println("syntax error in file: " + fileString);
			pw[8].println("====================");
			pw[8].println("Error: no author");
			pw[8].println("Record: " + bookstring);
			pw[8].println();
		}
		if (book[2].isEmpty()) {
			pw[8].println("syntax error in file: " + fileString);
			pw[8].println("====================");
			pw[8].println("Error: no price");
			pw[8].println("Record: " + bookstring);
			pw[8].println();
		}
		if (book[3].isEmpty()) {
			pw[8].println("syntax error in file: " + fileString);
			pw[8].println("====================");
			pw[8].println("Error: no ISBN");
			pw[8].println("Record: " + bookstring);
			pw[8].println();
		}
		if (book[4].isEmpty()) {
			pw[8].println("syntax error in file: " + fileString);
			pw[8].println("====================");
			pw[8].println("Error: no Genre");
			pw[8].println("Record: " + bookstring);
			pw[8].println();

		}
		if (book[4].isEmpty()) {
			pw[8].println("syntax error in file: " + fileString);
			pw[8].println("====================");
			pw[8].println("Error: no Year");
			pw[8].println("Record: " + bookstring);
			pw[8].println();

		}

	}
	/**
	 * content to write in the syntax error file for a specific exception
	 * @param fileString
	 * @param bookString
	 * @param book
	 * @param pw
	 */

	public static void writeUnknownGenreException(String fileString, String bookString, String[] book,
			PrintWriter[] pw) {
		pw[8].println("syntax error in file: " + fileString);
		pw[8].println("====================");
		pw[8].println("Error: Unknown Genre");
		pw[8].println("Record: " + bookString);
		pw[8].println();

	}
/////////////////////////////
	public static void writegenre(String[] book, PrintWriter[] pw) {
		String for_input_genre = "";
		for (int i = 0; i < book.length; i++) {
			for_input_genre = for_input_genre + book[i]+"|||";
			
		}

		if (book[4].equals("CCB")) 
			pw[0].println(for_input_genre);
		else if (book[4].equals("HCB"))
			pw[1].println(for_input_genre);
		else if (book[4].equals("MTV"))
			pw[2].println(for_input_genre);
		else if (book[4].equals("MRB"))
			pw[3].println(for_input_genre);
		else if (book[4].equals("NEB"))
			pw[4].println(for_input_genre);
		else if (book[4].equals("OTR"))
			pw[5].println(for_input_genre);
		else if (book[4].equals("SSM"))
			pw[6].println(for_input_genre);
		else if (book[4].equals("TPA"))
	        pw[7].println(for_input_genre);
		
	

	}

	/**
	 * write error message to semantic errors files 
	 * @param fileString
	 * @param bookString
	 * @param book
	 * @param pw
	 */
	public static void checksemanticerror(String fileString, String bookString,String[] book,PrintWriter[] pw) {
	  
	    	try {
	    	if(!valid_price(book[2])) throw new BadPriceException();
	    	else if(book[3].length()==13&& (!validISBN13(book[3]))) {
	    		throw new BadIsbn13Exception();
	    	}
	    	   
	    	else if(book[3].length()==10&& (!validISBN10(book[3]))) {
	    			 throw new BadIsbn10Exception();
	    	}
	    	
	    	
	    	else if(!valid_year(book[5])) throw new BadYearException();
	    	else {
	    		writegenre(book, pw);
	    		
	    		}
	      }
	      catch(BadPriceException e) {
	    	  pw[9].println("semantic error in file: " + fileString);
	  		  pw[9].println("====================");
	  		  pw[9].println("Error: Bad Price");
	  		  pw[9].println("Record: " + bookString);
	  		  pw[9].println();
	    }
          catch(BadIsbn13Exception e) {
        	  pw[9].println("semantic error in file: " + fileString);
	  		  pw[9].println("====================");
	  		  pw[9].println("Error: Bad ISBN13");
	  		  pw[9].println("Record: " + bookString);
	  		  pw[9].println();
	    }
         catch(BadIsbn10Exception e) {
        	  pw[9].println("semantic error in file: " + fileString);
	  		  pw[9].println("====================");
	  		  pw[9].println("Error: ISBN10");
	  		  pw[9].println("Record: " + bookString);
	  		  pw[9].println();
	
          }
         catch(BadYearException e) {
        	 pw[9].println("semantic error in file: " + fileString);
	  		  pw[9].println("====================");
	  		  pw[9].println("Error: Bad year");
	  		  pw[9].println("Record: " + bookString);
	  		  pw[9].println();
	
          }  
	    
	
		
     }
  /**
   * 
   * @param ISBN the 10 ISBN for validity check
   * @return
   */
	public static boolean validISBN10(String ISBN) {
		boolean isvalid=true;
		if(isInteger(ISBN)==false)return false;
		int[] valid1 = new int[10];
		for (int j = 0; j < 10; j++) {
			valid1[j] = Integer.parseInt(Character.toString(ISBN.charAt(j)));
		}
		int j = 10, sum = 0;
		for (int i = 0; i < 10; i++) {
			sum += j * valid1[i];
			j--;
		}
		if (sum % 11 != 0) isvalid = false;
		
		
			return isvalid;
		}
	/**
	 * check if ISBN are all numbers
	 * @param ISBN
	 * @return
	 */
    public static boolean isInteger(String ISBN) {
         if(Pattern.matches("[0-9]+",ISBN)) return true;
         else return false;
    }
	public static boolean validISBN13(String ISBN) {
		if(isInteger(ISBN)==false)return false;
		boolean isvalid = true;
		
		int[] valid1 = new int[13];
		for (int j = 0; j < 13; j++) {
		   valid1[j] = Integer.parseInt(Character.toString(ISBN.charAt(j)));
		}
		int sum1 = 0, sum2 = 0;
		for (int i = 0; i < 13; i++) {
			if (i % 2 == 0) {
				sum1 += 3 * valid1[i];
			}
			if (i % 2 == 1) {
				sum2 += valid1[i];
			}
		}
		if ((sum1 + sum2) % 11 != 0)
			isvalid = false;
		return isvalid;
	}
	
	/**
	 * is the price valid
	 * @param item1
	 * @return
	 */

	public static boolean valid_price(String item1) {
		boolean isvalid = true;
		double num1;

		num1 = Double.parseDouble(item1);

		if (num1 < 0) {
			isvalid = false;

		}

		return isvalid;

	}

	/**
	 * Is the year valid
	 * @param item2
	 * @return
	 */
	public static boolean valid_year(String item2) {
		boolean isvalid = true;

		int num2;

		num2 = Integer.parseInt(item2);

		if (num2 > 2010 || num2 < 1995) {
			isvalid = false;

		}

		return isvalid;
	}
}