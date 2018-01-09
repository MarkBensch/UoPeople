/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uopeople.cs1103.unit5.grading;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;
import javax.swing.JFileChooser;

/**
 * Th
 */
public class Dictionary {

	/**
	 * Lets the user select an input file using a standard file
	 * selection dialog box.  If the user cancels the dialog
	 * without selecting a file, the return value is null.
	 */
	static File getInputFileNameFromUser() {
		JFileChooser fileDialog = new JFileChooser();
		fileDialog.setDialogTitle( "Select File for Input" );
		int option = fileDialog.showOpenDialog( null );
		if ( option != JFileChooser.APPROVE_OPTION )
			return null;
		else

			return fileDialog.getSelectedFile();


	}

	public static void main( String[] args ) throws FileNotFoundException {

		/**
		 * This method will read the words.txt file using scanner and store then in a HashSet<String>,
		 * and will then print the total number of words in the HashSet which should be 72875.
		 */

		try {
			Scanner words = new Scanner( new File( "resources/words.txt" ) );
			HashSet<String> mydictionary = new HashSet<>(); // create new HashSet
			while ( words.hasNext() ) {
				String tk = words.next(); // reads word form file
				tk = tk.toLowerCase(); // converts word to lower case
				mydictionary.add( tk ); // add word to HashSet

			}
			System.out.println( "The number of words in the dictionary is " + mydictionary.size() );
			// prints the number of words in the set.


			Scanner userFile = new Scanner( getInputFileNameFromUser() );
			userFile.useDelimiter( "[^a-zA-Z]+" );

			while ( userFile.hasNext() ) {
				String wrd = userFile.next();
				wrd = wrd.toLowerCase();
				if ( !mydictionary.contains( wrd ) ) {

					System.out.println( "The dictionary does not contain the word " + wrd );
					System.out.println( " Suggestion: " + corrections( wrd, mydictionary ) );
				}
			}
		} catch ( IOException e ) {
			System.out.println( "File not found" );
		}
	}


	/**
	 * Stores the variations in a TreeSet, then
	 * Returns the possible variations on the misspelled word.
	 * Since the corrections are stored in a tree set, they are automatically
	 * printed out in alphabetical order with no repeats.
	 * <p>
	 * The possible corrections that the program considers are as follows:
	 * <p>
	 * Delete any one of the letters from the misspelled word.
	 * Change any letter in the misspelled word to any other letter.
	 * Insert any letter at any point in the misspelled word.
	 * Swap any two neighboring characters in the misspelled word.
	 * Insert a space at any point in the misspelled word (and check that both of the words that are produced are in the dictionary)
	 */

	static TreeSet corrections( String badWord, HashSet dictionary ) {

		TreeSet<String> tree = new TreeSet<String>();

		//Delete any one of the letters from the misspelled word, then check if exist in the dictionary.

		for ( int i = 0; i < badWord.length(); i++ ) {
			String s = badWord.substring( 0, i ) + badWord.substring( i + 1 );
			if ( dictionary.contains( s ) ) {
				tree.add( s );
			}
		}

		//Change any letter in the misspelled word to any other letter , then check if exist in the dictionary.

		for ( int i = 0; i < badWord.length(); i++ ) {
			for ( char ch = 'a'; ch <= 'z'; ch++ ) {
				String s = badWord.substring( 0, i ) + ch + badWord.substring( i + 1 );
				if ( dictionary.contains( s ) ) {
					tree.add( s );
				}
			}
		}
		//Insert any letter at any point in the misspelled word ,then check if exist in the dictionary.

		for ( int i = 0; i <= badWord.length(); i++ ) {
			for ( char ch = 'a'; ch <= 'z'; ch++ ) {
				String s = badWord.substring( 0, i ) + ch + badWord.substring( i );
				if ( dictionary.contains( s ) ) {
					tree.add( s );
				}
			}
		}

		//Swap any two neighboring characters in the misspelled word, then check if exist in the dictionary.

		for ( int i = 0; i < badWord.length() - 1; i++ ) {
			String s = badWord.substring( 0, i ) + badWord.substring( i + 1, i + 2 ) + badWord.substring( i, i + 1 ) + badWord.substring( i + 2 );
			if ( dictionary.contains( s ) ) {
				tree.add( s );
			}
		}

		//Insert a space at any point in the misspelled word (and check that
		//both of the words that are produced are in the dictionary)

		for ( int i = 1; i < badWord.length(); i++ ) {
			String stringInput = badWord.substring( 0, i ) + " " + badWord.substring( i );
			String tempString = "";

			//break a string into tokens and add it to tempWords
			StringTokenizer tempWords = new StringTokenizer( stringInput );

			//Loop over all words in tempWords.
			while ( tempWords.hasMoreTokens() ) {
				//Store each word in a temp string.
				String stringWord1 = tempWords.nextToken();
				String stringWord2 = tempWords.nextToken();
				//Check if temp words exist in dictionary otherwise break
				if ( dictionary.contains( stringWord1 ) && dictionary.contains( stringWord2 ) ) {
					tempString = stringWord1 + " " + stringWord2;
				} else
					break;
				tree.add( tempString );
			}
		}

		if ( tree.isEmpty() ) {
			tree.add( "no suggestions" );
		}
		return tree;
	}


}