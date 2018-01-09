package edu.uopeople.cs1103.unit5.grading;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Scanner;
import javax.swing.JFileChooser;

class spellchecker {

	static HashSet<String> dictionary = new HashSet<String>(); //dictionary of correctly spelled words
	static HashSet<String> textForChecking = new HashSet<String>(); //wordlist to check for spell errors
	/**
	 * Programs asks user to select the text file and analyzes the words and finding misspelled words
	 * For misspelled words, program will try to offer suggestions for correct words
	 * @author mz
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			dictionary = readDictionary(new File("resources/words.txt")); //Looking for dictionary in project /Dict direcctory
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary file not found. Please select the file manually.");
			try {
				dictionary = readDictionary(getInputFileNameFromUser()); //If dictionary file not found, asking user to select it manually
			} catch (FileNotFoundException e1) {
				System.out.println("File not found. Please check the path. Exiting...");// If the file not found, exiting app
				System.exit(1);
			} catch (NullPointerException e2) {
				System.out.println("Dictionary file not selected. Exiting..."); // If canceled in file selection window, app exiting.
				System.exit(1);
			} 
		}		
		System.out.println("Please select the text file to check!"); // Asking user to select file for checking
		try {
			textForChecking = readDictionary(getInputFileNameFromUser());
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Please check the path. Exiting..."); //If file not found, exiting
			System.exit(1);
		} catch (NullPointerException e) {
			System.out.println("File to check not selected. Exiting..."); //If canceled in file selector, exiting 
			System.exit(1);
		}
		for (String s : textForChecking) { //check all the words in selected file
			if (!dictionary.contains(s)) { // Checked if word is in dictionary
				if (corrections(s, dictionary).isEmpty()) {
					System.out.println(s + ": (no suggestions);"); //If the suggestion list is empty
				}
				else {
					System.out.print(s + ": "); //print the word
					TreeSet<String> correct = corrections(s, dictionary); 
					Iterator<String> iter = correct.iterator(); //create iterator
					while(iter.hasNext()) { 
						System.out.print(iter.next()); //printing out the word
						if (iter.hasNext()) { // if there is more words, printing comma
							System.out.print(", ");
						}
						else System.out.println(";"); //if this was last, printing semicolon and go to next line
					}
				}
			}
		}
	}
	/**
	 * This method reads all words from file and returns HashSet of those words
	 * @param path - filepath, to read
	 * @return - Hashset of all words from file
	 * @throws FileNotFoundException - throws exception if file is not found
	 */
	static HashSet<String> readDictionary(File path) throws FileNotFoundException {
		Scanner filein;
		filein = new Scanner(path);
		filein.useDelimiter("[^a-zA-Z]+"); //read file. All non letters are considered as delimeters
		HashSet<String> set = new HashSet<String>();
		while(filein.hasNext()) {
			String word = filein.next();
			word = word.toLowerCase(); //All words to lower case
			set.add(word); // adding to HashSet
		}
		filein.close(); //Closing scanner
		return set;
	}
	/**
     * Lets the user select an input file using a standard file
     * selection dialog box.  If the user cancels the dialog
     * without selecting a file, the return value is null.
     */
    static File getInputFileNameFromUser() {
       JFileChooser fileDialog = new JFileChooser();
       fileDialog.setDialogTitle("Select File");
       int option = fileDialog.showOpenDialog(null);
       if (option != JFileChooser.APPROVE_OPTION)
          return null;
       else
          return fileDialog.getSelectedFile();
    }
    /**
     * This method will try to find suggestions to suggest correct spellings.
     * @param badWord - word to check
     * @param dict - dictionary of usable words
     * @return - TreeSet with suggested corrections
     */
    static TreeSet<String> corrections(String badWord, HashSet<String> dict) {
    	TreeSet<String> suggestions = new TreeSet<String>(); //Initializing suggestion TreeSet
    	for (int i = 0; i < badWord.length(); i++) {
    		String s = badWord.substring(0,i)+badWord.substring(i+1); //Trying to delete character from misspelled word
    		if (dict.contains(s)) {
    			suggestions.add(s);
    		}
    		for (char ch = 'a'; ch <= 'z'; ch++) {
    			String s1 = badWord.substring(0,i)+ch+badWord.substring(i+1); //Changing any letter in word with other lettter
    			if (dict.contains(s1)) {
    				suggestions.add(s1);
    			}
    			String s2 = badWord.substring(0,i)+ch+badWord.substring(i); //Insert any letter in word
    			if (dict.contains(s2)) {
    				suggestions.add(s2);
    			}
    			String s3 = ch+badWord; //Add any letter at the beginning of word
    			if (dict.contains(s3)) {
    				suggestions.add(s3);
    			}
    			String s4 = badWord+ch; //Add any letter at the end of word
    			if (dict.contains(s4)) {
    				suggestions.add(s4);
    			}
    			String s5 = badWord.substring(0,i); //Splitting the word in two parts
    			String s6 = badWord.substring(i);
    			if (dict.contains(s5) && dict.contains(s6)) {
    				suggestions.add(s5);
    				suggestions.add(s6);
    			}
    		}
       	}
    	for (int i = 0; i < badWord.length()-1; i++) { //swapping any two characters in word
    		char[] c = badWord.toCharArray();
    		char temp = c[i];
    		c[i] = c[i+1];
    		c[i+1] = temp;
    		String cNew = new String(c);
    		if (dict.contains(cNew)) {
    			suggestions.add(cNew);
    		}
    	}
    	
    	return suggestions;
    }

}
