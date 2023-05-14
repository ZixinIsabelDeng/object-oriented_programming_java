// -----------------------------------------------------
// Assignment 4
// Part: 1,2
// Written by: Zixin Deng 40047744
// -----------------------------------------------------

import java.io.*;

import java.nio.file.*;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.ArrayList;

/**
 * 
 * @author Zixin Deng(40047744)
 * COMP249
 * Assignment #4
 * Due Date Saturday,  17 April 2023 
 */

public class filehandler {
    
       private Book[] shelf;
   
       /**
        * a default constructor which writes error book to the error file and return a book array without any error inside
        */
       public filehandler(){
    	  
    	  
	       ArrayList<Book> err=new ArrayList<Book>(1);
	       
	    	  
	    	   int count=0,count2=0;
	    	   String checking;
	    	   String[] bookString= new String[5];
	    	   int year;
	    	   int length=getfilelength("Books.txt");
	    	   String  title, author, genre;
	    	   Long ISBN;
				double price;
				
	    	   Scanner sc=null;
	    	   PrintWriter pw=null;
	    	   String[] content=new String[length];
	    	   
	    	   shelf=new Book[length-3];
	    	   Book b1;
	    	   try {
	    		   sc=new Scanner(new FileInputStream ("Books.txt"));
	    		   pw=new PrintWriter(new FileOutputStream("YearErr.txt"));
	    	   }
	    	   catch(FileNotFoundException e) {
	    		   System.out.print("File does not exist");
	    	   }
	    	 
	    	   
	    	   while(sc.hasNextLine()) {
	    		   checking=sc.nextLine();
	    		    StringTokenizer st;
	                st = new StringTokenizer(checking);
					title = st.nextToken(",");
					author = st.nextToken(",");
					price = Double.parseDouble(st.nextToken(","));
					ISBN = Long.parseLong(st.nextToken(","));
					genre = st.nextToken(",");
					year = Integer.parseInt(st.nextToken(","));
	                b1= new Book(title, author, price, ISBN, genre, year);
					if(year>2023) {
						
						
						err.add(b1);
						count2++;
					}
					
					else {
						shelf[count]=b1;
	    		        count++;
	    		        }
	    		    
	    		   
	    		   
	    		   
	    	   }
	    	   String errcontent=err.toString();
	    	   String clean=errcontent.substring(1,errcontent.length()-2);
	    	   String clean2=clean.replace(", ", "");
	    	  
	    	   pw.write(clean2);
	    	   
	    	   
	    	  sc.close();
	    	  pw.close();
	    	 // System.out.print(err.toString());
	    	  for(int i=0;i<shelf.length;i++) {
	    		 // System.out.print(shelf[i]+"\n");
	    	  }
	}
       
       
       /**
        * get the length of the file
        * @param filename
        * @return
        */
       
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
		return shelf;
	}



	public void setShelf(Book[] shelf) {
		this.shelf = shelf;
	}
       
       
}
