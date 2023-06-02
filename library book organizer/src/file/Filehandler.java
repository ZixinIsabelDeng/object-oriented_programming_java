// -----------------------------------------------------
// Assignment 3
// Part: 1,2
// Written by: Zixin Deng 40047744
// -----------------------------------------------------
package file;

import Exception.*;
import java.nio.file.*;
import java.util.*;
/**
 * 
 * @author Zixin Deng(40047744)
 * COMP249
 * Assignment #4
 * Due Date Saturday, 27 March 2023
 */
//for reading the file
//for writing the file 
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
//select qualifying document: no-empty files, no missing file, and write the missing file into the error file
public class Filehandler {
	private static String[] oldfile;
	protected static String[] newfile = { "Cartoons_Comics_Books.csv.txt", "Hobbies_Collectibles_Books.csv.txt",
			"Movies_TV.csv.txt", "Music_Radio_Books.csv.txt", "Nostalgia_Eclectic_Books.csv.txt","Old_Time_Radio.txt",
			"Sports_Sports_Memorabilia.csv.txt","Trains_Planes_Automobiles.csv.txt",
			"syntax_error_file.txt", "sematic_error_file.txt" };
	
/**
 * This constructor select the valid file for write, and write the missing file exception in syntax error 
 */
	public Filehandler() {
		Scanner sc = null;
		int num_of_file, k = 0;
		String[] validfile;
		int i = 0, z = 0;
		try {
			sc = new Scanner(new FileInputStream("part1_input_file_names.txt"));
		} catch (FileNotFoundException e) {
			System.out.print("Error! There isn't such file");
		}

		num_of_file = Integer.parseInt(sc.nextLine());
		String[] file = new String[num_of_file];
		while (sc.hasNextLine()) {

			file[i] = sc.nextLine();
			i++;
		}
		
		
	
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream("syntax_error_file.txt"), true);

			for (int j = 0; j < file.length; j++) {

				if (!validfilename(file[j])) {
					pw.println("syntax error in file: " + file[j]);
					pw.println("====================");
					pw.println("Error: missing file");
					pw.println("Record: " + "no record");
					pw.println();
				}	
			}
		
		}
		catch(IOException e) {
			System.out.print("This is an IO Exception");
		}
			
		
		
	
		for (int j = 0; j < file.length; j++) {
			if (validfilename(file[j]))
				k++;
		}
		
		validfile = new String[k];
    
		for (int j = 0; j < file.length; j++) {
			if (validfilename(file[j])) {
				validfile[z] = file[j];
				z++;
			}
		}
		
		
		
		
		
		int count = 0, f = 0;

		for (int s = 0; s < validfile.length; s++) {
			if (getfilelength(validfile[s]) != 0) {
				count++;
			}
		}
		
		String[] non_empty_file = new String[count];
		
		
		for (int s = 0; s < validfile.length; s++) {
			if (getfilelength(validfile[s]) != 0) {
				non_empty_file[f]=validfile[s];
				f++;
			}
		}
		
		oldfile=non_empty_file;
		 
		sc.close();
		pw.close();
		

	}
	
	
	
	
	
	
/**
 * 
 * @param filename the file that that user wants to know its length
 * @return return the total number of lines in a file
 */
	// check if the file is empty, if so remove
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
 * 
 * @param valid
 * @return the filename string that is not empty
 */
	public String[] notemptyfile(String[] valid) {

		int count = 0, j = 0;

		for (int i = 0; i < valid.length; i++) {
			if (getfilelength(valid[i]) != 0) {
				count++;
			}
		}
		String[] non_empty_file = new String[count];
		
		for (int i = 0; i < valid.length; i++) {
			if (getfilelength(valid[i]) != 0) {
				non_empty_file[j] = valid[i];
				j++;
			}
		}

		return non_empty_file;

	}
	
	/**
	 * getters and setters
	 * @return
	 */

	public String[] getOldfile() {
		return oldfile;
	}

	public static void setOldfile(String[] oldfile) {
		Filehandler.oldfile = oldfile;
	}

	public static String[] getNewfile() {
		return newfile;
	}

	public static void setNewfile(String[] newfile) {
		Filehandler.newfile = newfile;
	}

	public boolean validfilename(String filename) {
		try {
			File f = new File(filename);
			if (!f.exists())
				throw new MissingFileException("Error! No such file");
			;
		} catch (MissingFileException e) {
			return false;
		}

		return true;
	}
}
	