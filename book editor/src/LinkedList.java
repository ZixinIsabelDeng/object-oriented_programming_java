
// -----------------------------------------------------
// Assignment 4
// Part: 1,2
// Written by: Zixin Deng 40047744
// -----------------------------------------------------


import java.io.*;
import java.util.*;

/**
 * 
 * @author Zixin Deng(40047744)
 * COMP249
 * Assignment #4
 * Due Date Saturday,  17 April 2023 
 */
public class LinkedList {

	public class Node{
		
	    private Book b;
	    private Node next;
	    /**
	     * copy constructor
	     */
	    public Node(){
	    	b=null;
	    	next=null;
		 
	    }
	    /**
	     * parameterized construcctor
	     * @param b1
	     * @param n
	     */
	    public Node(Book b1, Node n){
	        b=b1;
		    next=n;
	    }
	    /**
	     * copy constructor of Node
	     * @param n
	     */
	    public Node(Node n) {
	    	b=n.b.clone();
	    	next=n.next;
	    }
	    /**
	     * Getters and setters
	     * @return
	     */
	    public Book getBook() {
	    	return b.clone();
	    }
	
	    }
	
	
	
	private Node head;
	
	public LinkedList(){
		head=null;
		
	}
	/**
	 * copy constructor for linkedlist
	 * @param lst
	 */
	public LinkedList(LinkedList lst) {
		
		if(lst.head == null)
			head = null;
		else
		{
			head = null;	
			Node t1, t2, t3;	// create 3 temporary pointers
			
			t1 = lst.head;
			t2 = t3 = null;
			
			while(t1 != null)				
{
				
				if (head == null)	// this happens only once
				{
					t2 = new Node(t1.b.clone(), null);  
						head = t2;
// Can we use t2 = new Node(t1);  for
 //  Instance as an alternative?
				}
				else 
				{
					t3 = new Node(t1.b.clone(), null);									t2.next = t3;					
					t2 = t3;												}
				t1 = t1.next;
			}
			
			t2 = t3 = null; 	// t1 is already null by now
					
		}

	}
	
	
	
	public LinkedList clone()
	{
		return new LinkedList(this);
	}

	
	
	
	
	
	/**
	 * add the book to the start, which will create a circular linkedlist, the last one always pointed towards head;
	 * @param book given
	 */
	public void addToStart(Book b) {
	     if(head==null) {
	    	 head=new Node(b,head);
	    	 head.next=head;
	     }	
	     else {
	    	 Node newNode=new Node(b,head);
	    	 Node tail=head;
	    	 while(tail.next!=head) {
	    		 tail=tail.next;
	    	 }
	    	 tail.next=newNode;
	    	 head=newNode;
	    	 
	     }
	     
	     
	     
	     
		
	
		}
		
		 
		
	
	/**
	 * When user gives an input of year, it will creates a file and stores all books with that year inside the file
	 * @param the year to create a year.file
	 */
	
	public void storeRecordsByYear(int yr) {
		Node t=head;
		
		
	    PrintWriter pw=null;
	    try {
	    	pw=new PrintWriter(new FileOutputStream(yr+".txt" ,true));
	    	
	    	do {
	    		if(t.b.getYear()==yr) {
	    		
	    		
	    		 pw.println(t.b);
	    		 t=t.next;
	    		}
	    		else t=t.next;
	    	}while(t!=head);
	    	pw.close();
	    }
	    catch(IOException e) {
	    	System.out.print("This is an IO exception");
	    }
	    
		
		
	}
	/**
	 * insert a book in front of the book with an ISBN number user input
	 * @param isbn
	 * @param b1
	 * @return
	 */
	
	public boolean insertBefore(long isbn, Book b1) {
		
		if(head==null) return false;
		else {
			Node current=head;
			do {
				if(current.b.getISBN()==isbn) {
					addToStart(b1);
					current=head;
					return true;
				}
				
				else if(current.next.b.getISBN()==isbn) {
					
					current.next=new Node(b1,current.next);
					return true;
				}
				else current=current.next;
			}while(current!=head);
            return false;
			
			
		}
		
	}
	
	/**
	 * user gives 2 values of ISBN number of two books and a new book. this method will insert the book in betweem these two books
	 * @param isbn1
	 * @param isbn2
	 * @param b   new book to insert
	 * @return
	 */
	public boolean insertBetween(long isbn1, long isbn2, Book b) {
    	
		if (head==null){
	    	
			return false;	
		}
		else if (head.next==null&&(head.b.getISBN()==isbn1||head.b.getISBN()==isbn2)){
			return false;	
		}
		else if (head.b.getISBN()==isbn1 && head.next.b.getISBN()==isbn2){
			insertBefore(isbn2,b);
			return false;	
		}
		else {
			
			Node t=head;
			do {
				if(t.b.getISBN()==isbn1&&t.next.b.getISBN()==isbn2 ){
					insertBefore(isbn2,b);
					return true;
				}
				else t=t.next;
			}while(t!=head);
			}
	        return false;	
			
		}		
		
	/**
	 * get the size of the total linkedlist
	 * @return
	 */
	public int size()
	{     
		
		int ctr = 0;
		Node temp = head;	// Point to the start of the list
		if(head==null) return 0;
		if(head.next==null) return 1;
		else {
		do
		{
			
			ctr++;
			temp = temp.next;
		}while(temp!=head);
		return ctr;
		}
	}
	
	/*
	 *display the content all all the book inside the linkedlist 
	 */
	public void displayContent() {
		
		
		if(head==null) System.out.print("NO content for display");
		else {
			Node current=head;
			do {
				System.out.println(current.getBook()+"==>");
				current=current.next;
				
				
			}
			while(current!=head);
			
			System.out.println("==>head");
			
			
			
		}
				
				
		}
	
	/***
	 * this one is similar to the delete method in linkedlist class. user gives an index. data related to that index will be deleted
	 * @param index
	 * @return
	 */
	
	public boolean delete(int index) {
		if(index==0) {
			head=head.next;
			Node t=head;
			do {
				t=t.next;
			}while(t.next!=head);
			t=head;
			return true;
		}
		if(index>size()-1||index<0) {
			return false;
		}
		else {	
			
	  int count=0;
	  Node current=head;
	 
	  for (int i = 0; i < index - 1; i++) {
          current = current.next;
      }
	  current.next = current.next.next;
	  return true;
	  
	
	}
		}
	
	/**
	 * a delete method that delete all the odd number of the repeated book
	 * @return
	 */
	
	
	public boolean delRecords() {
		int index_of_current=0;
		boolean isdelete=false;
		Node current=head;
		do {
			if(current.getBook().equals(current.next.getBook())) {
				isdelete=delete(index_of_current+1);
				current=current.next;
				index_of_current++;
			}
			else {
				current=current.next;
				index_of_current++;	
			}
		}
		while(current!=head);
		
		
			
		return isdelete;
		
	}
	
	/**
	 * a delete method that delete both odd and even number of the repeated book
	 * @return
	 */
	public boolean delConsecutiveRepeatedRecords() {
		boolean isdelete=false;
		isdelete=delRecords();
		isdelete=delRecords();
		return isdelete;
	}
	
	
	/**
	 * user gives an author name, it will generate a linkedlist that all about that author 
	 * @param aut
	 * @return
	 */
	public LinkedList extractAuthList(String aut) {
		Node t=head;
		LinkedList l=new LinkedList();
		while(t.next!=head) {
			if(t.getBook().getAuthor().equals(aut)) {
				l.addToStart(t.getBook());
				 t=t.next;
	        }
			else t=t.next;
		}
		//make it single likedlist instead of a circular one
	
		return l;
		
		
	}
	
	//check if it is possible to swap or not
	
	public boolean isswap(long isbn1, long isbn2) {
		boolean first=false,second=false;
		Node t1=head,t2=head;
		while(t1.next!=head) {
			if(t1.b.getISBN()==isbn1) {
				first=true;
				
			}
			if(t1.b.getISBN()!=isbn1) {
				t1=t1.next;
			}
			if(t2.b.getISBN()==isbn2) {
				second=true;
				
			}
			if(t2.b.getISBN()!=isbn2) {
				t2=t2.next;
		
		    }
			else {
				t1=t1.next;
				t2=t2.next;
			}
	    }
		
		return (first&&second);
		
    }
	
	/**
	 * get the index of the input books
	 * @param b1
	 * @return
	 */
	
	public int getindex(Book b1){
		
		Node t=head;
		int counter=0;
		while(t.next!=head) {
			if(t.b.equals(b1)) {
				break;
			}
			else {
				
				counter++;
				t=t.next;
			}
		}
		
		return counter;
		
	}
	/**
	 * check if two nodes for next to each other, this helps to determine which types of swap we need to choose
	 * @param a
	 * @param b1
	 * @return
	 */
	public boolean isnextto(Node a,Node b1) {
		if(a.next.b.getISBN()==b1.b.getISBN()) return true;
		else return false;
	}
	
	/**
	 * user gives ISBN number of two books, this method will swap them inside the linkedlist 
	 * @param isbn1
	 * @param isbn2
	 * @return
	 */
	
	public boolean swap(long isbn1, long isbn2) {
		
		
		if(head==null) return false;
		else if(!isswap(isbn1,isbn2)) return false;
		else {
		Node t1=head,t2=head,t3=head;;
		while(t3.next!=head) {
			
			if(t1.next.b.getISBN()!=isbn1) {
				t1=t1.next;
				t3=t3.next;
				
			}
	
			else if(t2.next.b.getISBN()!=isbn2) {
				t2=t2.next;
				t3=t3.next;
		
     		}  
			else t3=t3.next;
	
		}
		Book a=new Book(t1.next.getBook());
		Book b=new Book(t2.next.getBook());
		Long ISBN1=t1.next.next.b.getISBN();
		Long ISBN2=t2.next.next.b.getISBN();
		Long ISBN3=t1.next.b.getISBN();
		System.out.println(isnextto(t1.next,t2.next));
		if(isnextto(t1.next,t2.next)) {
			delete(getindex(t1.next.b));
			delete(getindex(t2.next.b));
			insertBefore(ISBN2,a);
			insertBefore(ISBN3,b);
			
		}
		else {
			delete(getindex(t1.next.b));
			delete(getindex(t2.next.b));
		insertBefore(ISBN1,b);

		insertBefore(ISBN2,a);
		
		}					
		return true;				
	    }	

     }
	
	/**
	 * after all adjustment, put all the book inside a file as the updated book 
	 */
	public void commit() {
		PrintWriter pw=null;
		try {
			pw=new PrintWriter(new FileOutputStream("Update_Books.txt",true));
			Node t=head;
			while(t.next!=head) {
				pw.println(t.b);
				t=t.next;
				
			}
			pw.close();
		}catch(IOException e ) {
			System.out.print("This is an IO Exception");
			
		}
		
	}








}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


