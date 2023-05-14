

public class Book {
	/**
	 * 
	 * @author Zixin Deng(40047744)
	 * COMP249
	 * Assignment #4
	 * Due Date Saturday, 27 March 2023
	 */
	
	private String title;
	private String author;
	private double price;
	private long ISBN;
	private String genre;
	private int year;
	
	
	public Book() {
		
	}
	/**
	 * copy constructor
	 * @param title
	 * @param author
	 * @param price
	 * @param iSBN2
	 * @param genre
	 * @param year
	 */
	
	public Book(String title, String author, double price, long iSBN2, String genre, int year) {
		
		this.title = title;
		this.author = author;
		this.price = price;
		this.ISBN = iSBN2;
		this.genre = genre;
		this.year = year;
	}
	
     public Book(Book b) {
    	 this.title = b.title;
 		this.author = b.author;
 		this.price =b. price;
 		this.ISBN = b.ISBN;
 		this.genre = b.genre;
 		this.year = b.year;
 		
 		
	
     
     
     }
/**
 * getters and setters
 * @return
 */

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public long getISBN() {
		return ISBN;
	}


	public void setISBN(long iSBN) {
		this.ISBN = iSBN;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}

	
	public Book clone() {
		return new Book(this);
	}
/**
 * to string method
 */
	@Override
	public String toString() {
		return title + "," + author + ","  + price +","  + ISBN + ","+genre+
				 "," +year ;
	}
     
	
	/**
	 * check equality
	 */
	@Override
	public boolean equals(Object o) {
		if(o==null||o.getClass()!=this.getClass()) {
			return false;
		}
		else {
			Book b=(Book)o;
			return(title.equals(b.getTitle()) &&  author.equals(b.getAuthor()) &&  ISBN==b.getISBN()   &&  genre.equals(b.getGenre())    &&    
					price==b.getPrice()&&year==b.getYear());
		}
		
		
		
	}





}
