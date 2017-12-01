package edu.uopeople.cs1103.unit2.grading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RecursiveSyntax {
	static final String[] conjunction = { "and", "or", "but", "because" };
	static final String[] proper_noun = { "Fred", "Jane", "Richard Nixon", "Miss America" };
	static final String[] common_noun = { "man", "woman", "fish", "elephant", "unicorn" };
	static final String[] determiner = { "a", "the", "every", "some" };
	static final String[] adjective = { "big", "tiny", "pretty", "bald" };
	static final String[] intransitive_verb = { "runs", "jumps", "talks", "sleeps" };
	static final String[] transitive_verb = { "loves", "hates", "sees", "knows", "looks for", "finds" };

	public static void main( String[] args ) {

		List<String[]> arrayList = new ArrayList<>();
		arrayList.add( conjunction );
		arrayList.add( proper_noun );
		arrayList.add( common_noun );
		arrayList.add( determiner );
		arrayList.add( adjective );
		arrayList.add( intransitive_verb );
		arrayList.add( transitive_verb );
		Random random = new Random();

		for ( String[] currentArray : arrayList ) {
			String chosenString = currentArray[ random.nextInt( currentArray.length ) ];
			System.out.println( chosenString );
		}

		while ( true ) {
			randomSentence();
			System.out.println( ".\n\n" );
			try {
				Thread.sleep( 3000 );
			} catch ( InterruptedException e ) {

			}
		}
	}

	static void randomSentence() {
		System.out.print( "this is " );
		if ( Math.random() > 0.2 )
			randomNounPhrase();
		System.out.print( "the house that Jack built" );
		if ( Math.random() > 0.75 ) {
			System.out.print( " and " );
			randomSentence();
		}
	}

	static void randomNounPhrase() {
//		int n = (int) ( Math.random() * nouns.length );
//		int v = (int) ( Math.random() * verbs.length );
//		int m = (int) ( Math.random() * modifiers.length );
//		System.out.print( "the " + nouns[ n ] );
//		if ( Math.random() > 0.75 )
//			System.out.print( " " + modifiers[ m ] );
//		System.out.print( " that " + verbs[ v ] + " " );
//		if ( Math.random() > 0.5 )
//			randomNounPhrase();
	}
}