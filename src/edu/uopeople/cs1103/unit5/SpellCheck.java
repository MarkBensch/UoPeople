package edu.uopeople.cs1103.unit5;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SpellCheck {
	private static String fileLocation = "resources/words.txt";
	private static Set<String> wordList = new HashSet();
	private static Set<String> SpellCheckList = new HashSet();
	public static void main(String args[]){
		createWordSet();
		System.out.println("The word list has " + wordList.size() + " words and we expect 72875");
		createSpellingSet();
		checkspelling();
	}

	static TreeSet corrections( String badWord, HashSet dictionary){
		TreeSet<String> suggestedWords = new TreeSet();
		suggestedWords.addAll( checkDelete(badWord, dictionary) );
		suggestedWords.addAll( checkChangeLetter(badWord, dictionary));
		suggestedWords.addAll( checkInsertLetter(badWord,dictionary) );
		suggestedWords.addAll( checkSwapLetter(badWord, dictionary ) );
		suggestedWords.addAll( checkInsertSpace(badWord, dictionary ) );
		return suggestedWords;
	}

	public static TreeSet checkDelete(String badWord, HashSet dictionary){
		TreeSet<String> deletedWords = new TreeSet();
		for(int pos=0; pos<= badWord.length()-1; pos++){
			StringBuilder sb = new StringBuilder(badWord);
			sb.deleteCharAt(pos);
			if(isWord(sb.toString(), dictionary)){
				deletedWords.add(sb.toString());
			}
		}
		return deletedWords;
	}

	public static TreeSet checkChangeLetter(String badWord, HashSet dictionary){
		TreeSet<String> changeWords = new TreeSet();
		for(int pos=0; pos<= badWord.length()-1; pos++){
			for (char ch = 'a'; ch <= 'z'; ch++) {
				StringBuilder newWord = new StringBuilder(badWord);
				newWord.setCharAt(pos, ch);
				if(isWord(newWord.toString(), dictionary)){
					changeWords.add(newWord.toString());
				}
			}
		}
		return changeWords;
	}

	public static TreeSet checkInsertLetter(String badWord, HashSet dictionary){
		TreeSet<String> insertWords = new TreeSet();
		for(int pos=0; pos<= badWord.length(); pos++){
			for (char ch = 'a'; ch <= 'z'; ch++) {
				StringBuilder newWord = new StringBuilder(badWord);
				newWord.insert(pos, ch);
				if(isWord(newWord.toString(), dictionary)){
					insertWords.add(newWord.toString());
				}
			}
		}
		return insertWords;
	}

	public static TreeSet checkSwapLetter(String badWord, HashSet dictionary){
		TreeSet<String> swapWords = new TreeSet();
		for(int ch=0; ch<= badWord.length()-1; ch++){
			for(int pos=0; pos<= badWord.length()-1; pos++){
				StringBuilder newWord = new StringBuilder(badWord);
				char chr = newWord.charAt( ch );
				newWord.deleteCharAt( ch );
				newWord.insert(pos, chr);
				if(isWord(newWord.toString(), dictionary)){
					swapWords.add(newWord.toString());
				}
			}
		}
		return swapWords;
	}

	public static TreeSet checkInsertSpace(String badWord, HashSet dictionary){
		TreeSet<String> insertSpace = new TreeSet();
		for(int pos=0; pos<= badWord.length(); pos++){

				StringBuilder newWord = new StringBuilder(badWord);
				newWord.insert(pos, ' ');
				String[] words = newWord.toString().split( " " );
				if(isWord(words[0], dictionary) && isWord(words[1], dictionary)){
					insertSpace.add(words[0] +" "+ words[1]);
				}

		}
		return insertSpace;
	}

	public static boolean isWord(String word, HashSet dictionary){
		return dictionary.contains( word );
	}

	public static void checkspelling(){
		for ( String word : SpellCheckList) {
			if(!wordList.contains(word)){
				//System.out.println("spelling error: " + word);
				System.out.println("incorrect Spelling: " + word + " - try these: " + corrections( word, (HashSet) wordList ).toString()) ;
			}
		}
	}

	public static void createSpellingSet(){
		Scanner filein = null;
		try {
			filein = new Scanner( getInputFileNameFromUser() );
		} catch ( FileNotFoundException e ) {
			e.printStackTrace();
		}
		filein.useDelimiter( "[^a-zA-Z]+" );
		while (filein.hasNext()) {
			String tk = filein.next();
			SpellCheckList.add(tk.toLowerCase()); // do something with the token
		}
	}

	public static void createWordSet(){
		Scanner filein = null;
		try {
			filein = new Scanner( new File( fileLocation ) );
		} catch ( FileNotFoundException e ) {
			e.printStackTrace();
		}
		while (filein.hasNext()) {
			String tk = filein.next();
			wordList.add(tk.toLowerCase()); // do something with the token
		}
	}

	/**
	 * Lets the user select an input file using a standard file
	 * selection dialog box.  If the user cancels the dialog
	 * without selecting a file, the return value is null.
	 */
	static File getInputFileNameFromUser() {
		JFileChooser fileDialog = new JFileChooser();
		fileDialog.setDialogTitle("Select File for Input");
		int option = fileDialog.showOpenDialog(null);
		if (option != JFileChooser.APPROVE_OPTION)
			return null;
		else
			return fileDialog.getSelectedFile();
	}
}
