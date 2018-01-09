package edu.uopeople.cs1103.unit5;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * This is a program that read the word of a provided file and checks them against a word bank for correctness
 * <p>
 * <b>Output</b>
 * The word list has 72875 words and we expect 72875
 * incorrect Spelling: misss - try these: [miss, misses, missy, mists]
 * incorrect Spelling: spelle - try these: [spell, spelled, speller, spells]
 * incorrect Spelling: isbig - try these: [is big]
 * incorrect Spelling: tha - try these: [aha, ha, hat, ta, tea, th, th a, thad, thai, than, thar, that, thaw, the, thea, tho, thy, tia, twa]
 * incorrect Spelling: aer - try these: [a er, aery, air, apr, ar, are, aver, ear, er, era, fer, ger, her, per, rae]
 *
 * @author Mark Bensch
 */
public class SpellCheck {
	private static String fileLocation = "resources/words.txt"; // location of dictionary word list
	private static Set<String> wordList = new HashSet(); // Dictionary of correct words
	private static Set<String> SpellCheckList = new HashSet(); // list of words to spell check

	public static void main( String args[] ) {
		createWordSet();
		System.out.println( "The word list has " + wordList.size() + " words and we expect 72875" );
		createSpellingSet();
		checkspelling();
	}

	/**
	 * this method goes through several common spelling mistakes to
	 * give suggestions for the correct spelling.
	 * <b>Rules: </b>
	 * • Delete any one of the letters from the misspelled word.
	 * • Change any letter in the misspelled word to any other letter.
	 * • Insert any letter at any point in the misspelled word.
	 * • Swap any two neighboring characters in the misspelled word.
	 * • Insert a space at any point in the misspelled word
	 *
	 * @param badWord    String incorrectly spelled word
	 * @param dictionary HashSet of words to reference correctness against
	 * @return TreeSet of suggested spelling of the incorrect word
	 */
	static TreeSet corrections( String badWord, HashSet dictionary ) {
		TreeSet<String> suggestedWords = new TreeSet();
		suggestedWords.addAll( checkDelete( badWord, dictionary ) );
		suggestedWords.addAll( checkChangeLetter( badWord, dictionary ) );
		suggestedWords.addAll( checkInsertLetter( badWord, dictionary ) );
		suggestedWords.addAll( checkSwapLetter( badWord, dictionary ) );
		suggestedWords.addAll( checkInsertSpace( badWord, dictionary ) );
		return suggestedWords;
	}

	/**
	 * This method will loop through deleting charactures from the
	 * String and check it against the HashSet of known good words.
	 * Delete any one of the letters from the misspelled word.
	 * @param badWord String that contains a miss spelled word
	 * @param dictionary HashSet of known good words
	 * @return TreeSet containing possible correct spellings of the mis Spelled word
	 */
	public static TreeSet checkDelete( String badWord, HashSet dictionary ) {
		TreeSet<String> deletedWords = new TreeSet();
		for ( int pos = 0; pos <= badWord.length() - 1; pos++ ) {
			StringBuilder sb = new StringBuilder( badWord );
			sb.deleteCharAt( pos );
			if ( isWord( sb.toString(), dictionary ) ) {
				deletedWords.add( sb.toString() );
			}
		}
		return deletedWords;
	}

	/**
	 * This method will loop through and change the each letter of the String to another
	 * letter of the alphabet and check that new word against the HashSet of known good words
	 * Change any letter in the misspelled word to any other letter.
	 * @param badWord String that contains a miss spelled word
	 * @param dictionary HashSet of known good words
	 * @return TreeSet containing possible correct spellings of the mis Spelled word
	 */
	public static TreeSet checkChangeLetter( String badWord, HashSet dictionary ) {
		TreeSet<String> changeWords = new TreeSet();
		for ( int pos = 0; pos <= badWord.length() - 1; pos++ ) {
			for ( char ch = 'a'; ch <= 'z'; ch++ ) {
				StringBuilder newWord = new StringBuilder( badWord );
				newWord.setCharAt( pos, ch );
				if ( isWord( newWord.toString(), dictionary ) ) {
					changeWords.add( newWord.toString() );
				}
			}
		}
		return changeWords;
	}

	/**
	 * This method will loop through and insert a new letter into the String
	 * and check that new word against the HashSet of known good words
	 * Insert any letter at any point in the misspelled word.
	 * @param badWord String that contains a miss spelled word
	 * @param dictionary HashSet of known good words
	 * @return TreeSet containing possible correct spellings of the mis Spelled word
	 */
	public static TreeSet checkInsertLetter( String badWord, HashSet dictionary ) {
		TreeSet<String> insertWords = new TreeSet();
		for ( int pos = 0; pos <= badWord.length(); pos++ ) {
			for ( char ch = 'a'; ch <= 'z'; ch++ ) {
				StringBuilder newWord = new StringBuilder( badWord );
				newWord.insert( pos, ch );
				if ( isWord( newWord.toString(), dictionary ) ) {
					insertWords.add( newWord.toString() );
				}
			}
		}
		return insertWords;
	}
	/**
	 * This method will loop through and swap the placement of each letter
	 * and verify if the new letter placement creates a correct word from the HashSet
	 * Swap any two neighboring characters in the misspelled word.
	 * @param badWord String that contains a miss spelled word
	 * @param dictionary HashSet of known good words
	 * @return TreeSet containing possible correct spellings of the mis Spelled word
	 */
	public static TreeSet checkSwapLetter( String badWord, HashSet dictionary ) {
		TreeSet<String> swapWords = new TreeSet();
		for ( int ch = 0; ch <= badWord.length() - 1; ch++ ) {
			for ( int pos = 0; pos <= badWord.length() - 1; pos++ ) {
				StringBuilder newWord = new StringBuilder( badWord );
				char chr = newWord.charAt( ch );
				newWord.deleteCharAt( ch );
				newWord.insert( pos, chr );
				if ( isWord( newWord.toString(), dictionary ) ) {
					swapWords.add( newWord.toString() );
				}
			}
		}
		return swapWords;
	}
	/**
	 * This method will loop through and insert a blank space between each character
	 * Then checks both sides of the space to see if both are a valid word
	 * Insert a space at any point in the misspelled word
	 * @param badWord String that contains a miss spelled word
	 * @param dictionary HashSet of known good words
	 * @return TreeSet containing possible correct spellings of the mis Spelled word
	 */
	public static TreeSet checkInsertSpace( String badWord, HashSet dictionary ) {
		TreeSet<String> insertSpace = new TreeSet();
		for ( int pos = 0; pos <= badWord.length(); pos++ ) {

			StringBuilder newWord = new StringBuilder( badWord );
			newWord.insert( pos, ' ' );
			String[] words = newWord.toString().split( " " );
			if ( isWord( words[ 0 ], dictionary ) && isWord( words[ 1 ], dictionary ) ) {
				insertSpace.add( words[ 0 ] + " " + words[ 1 ] );
			}

		}
		return insertSpace;
	}

	/**
	 * Check if the word exists in the HashSet
	 *
	 * @param word String used to check if known good word
	 * @param dictionary HashSet of known good words
	 * @return boolean true if String exists in HashSet
	 */
	public static boolean isWord( String word, HashSet dictionary ) {
		return dictionary.contains( word );
	}

	/**
	 * This method loops through each word in the HashSet to check for spelling mistakes
	 * and if there is a spelling mistake prints out a list of suggestions to correct the word
	 * However, if there are mistakes and the corrections() method cant find a match
	 * the printed statement will state No Suggestions
	 */
	public static void checkspelling() {
		for ( String word : SpellCheckList ) {
			if ( !wordList.contains( word ) ) {
				TreeSet<String> errorList = corrections( word, (HashSet) wordList );
				if ( errorList.isEmpty() ) {
					System.out.println( "incorrect Spelling: " + word + " - (No Suggestions)" );
				} else {
					System.out.println( "incorrect Spelling: " + word + " - try these: " + errorList.toString() );
				}
			}
		}
	}

	/**
	 * loop through a file provided from a user interface
	 * parse out only the words and add it to the private HashSet
	 */
	public static void createSpellingSet() {
		Scanner filein = null;
		try {
			filein = new Scanner( getInputFileNameFromUser() );
		} catch ( FileNotFoundException e ) {
			e.printStackTrace();
		}
		filein.useDelimiter( "[^a-zA-Z]+" );
		while ( filein.hasNext() ) {
			String tk = filein.next();
			SpellCheckList.add( tk.toLowerCase() ); // do something with the token
		}
		filein.close();
	}

	/**
	 * read a file and add each word to a HashSet
	 * this file is the dictionary that words will be checked against for correctness
	 */
	public static void createWordSet() {
		Scanner filein = null;
		try {
			filein = new Scanner( new File( fileLocation ) );
		} catch ( FileNotFoundException e ) {
			e.printStackTrace();
		}
		while ( filein.hasNext() ) {
			String tk = filein.next();
			wordList.add( tk.toLowerCase() ); // do something with the token
		}
		filein.close();
	}

	/**
	 * Lets the user select an input file using a standard file
	 * selection dialog box.  If the user cancels the dialog
	 * without selecting a file, the return value is null.
	 * @author UofPeople
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
}
