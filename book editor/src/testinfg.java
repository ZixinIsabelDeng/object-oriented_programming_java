// -----------------------------------------------------
// Assignment 4
// Part: 1,2
// Written by: Zixin Deng 40047744
// -----------------------------------------------------

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * @author Zixin Deng(40047744)
 * COMP249
 * Assignment #4
 * Due Date Saturday, 17 April 2023
 */
public class testinfg {

	public static void main(String[] args) {

	filehandler f=new filehandler();
	       LinkedList bkLst=new LinkedList();
	       Book[]collection=f.getShelf();
	       ArrayList<Book> arrLst=new ArrayList<Book>();
	       
	       for(int i=0;i<collection.length;i++) {
	    	   bkLst.addToStart(collection[i]);
	       }
	       
	       
	     
	       
	     
	       
	       
	       
	       bkLst.displayContent(); 
	       System.out.println("1) Give me a year # and I would extract all records of that year and store them in a file for that year;");
	       System.out.println("2) Ask me to delete all consecutive repeated records;");
	       System.out.println("3) Give me an author name and I will create a new list with the records of this author and display them;");
	       System.out.println("4) Give me an ISBN number and a Book object, and I will insert Node with the book before the record with this ISBN;");
	       System.out.println("5) Give me 2 ISBN numbers and a Book object, and I will insert a Node between them, if I find them!");
	       System.out.println("6) Give me 2 ISBN numbers and I will swap them in the list for rearrangement of records; of course if they exist!");
	       System.out.println("7) Tell me to COMMIT! Your command is my wish. I will commit your list to a file called Updated_Books;");
	       System.out.println("8) Tell me to STOP TALKING. Remember, if you do not commit, I will not!");
	       
	       Book b=new Book("How to get a job in tech","Isabel",20000,267348329748L,"JHK",2013);
	       	       
	       while(true) {
	    	   System.out.println("which options would you like?!");
	       boolean iscommit=false;
	       Scanner kb=new Scanner(System.in);
	       int option=kb.nextInt();
	       switch(option) {
	       case 1:
	    	   System.out.println("Give me a year");
	    	   int year=kb.nextInt();
	    	   bkLst.storeRecordsByYear(year);
	    	   bkLst.displayContent();  
	    	   break;
	       case 2:  
	    	   bkLst.delConsecutiveRepeatedRecords();
	    	   bkLst.displayContent();  
	    	   break;
	       case 3:
	    	   System.out.println("Give me an author");
	    	   String a=kb.nextLine();
	    	   String author=kb.nextLine();
	    	   LinkedList authorlis=bkLst.extractAuthList(author);
	           authorlis.displayContent(); 
	    	   break;
	       case 4:
	    	   System.out.println("Give me an ISBN");
	    	   Long ISBN=kb.nextLong();
	    	   bkLst.insertBefore(ISBN, b);
	    	   bkLst.displayContent();
	    	   break;
	       case 5:
	    	   System.out.println("Give me 2 ISBN");
	    	   Long ISBN1=kb.nextLong();
	    	   Long ISBN2=kb.nextLong();
	    	   bkLst.insertBetween(ISBN1,ISBN2, b);
	    	   bkLst.displayContent();
	    	   break;
	       case 6:
	    	   System.out.println("Give me 2 ISBN");
	    	   Long ISBN3=kb.nextLong();
	    	   Long ISBN4=kb.nextLong();
	    	   bkLst.insertBetween(ISBN3,ISBN4, b);
	    	   bkLst.displayContent();
	    	   
	    	   break;
	       case 7:
	    	   
	    	   bkLst.commit();
	    	   iscommit=true;
	    	   break;
	       case 8:
	    	   if(iscommit) System.exit(0);
	    	   break;
	      
	       
	       
	       }
	       
	       }
	       

//	     
//		
		
	}
	
	


}
