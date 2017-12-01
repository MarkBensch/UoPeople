package edu.uopeople.cs1103.unit2.grading;

public class simpleRandomSentences_1 {

	static final String[] conjunction = {"and","or","but","becuase"};
	static final String[] properNoun = {"Scooby Doo","Gabriela","Dirty Hippie","Miss America"};
	static final String[] commonNoun= {"beer","woman","fish","elephant","unicorn"};
	static final String[] determiner= {"a","the","every","some"};
	static final String[] adjective = {"big", "fat","ugly","hairy"};
	static final String[] intrisitveVerb= {"swags","jumps","sleeps","drinks"};
	static final String[] transitiveVerb= {"loves", "hates","sees","knows","looks for","finds"};


	public static void main(String[]args) {
		while(true) {
			randomSentence();
			System.out.println(".\n\n");
			try {
				Thread.sleep(3000);
			}catch (Exception E) {

			}
		}
	}

	static void randomSentence() {
		randomVerbSentence();
		randomNounSentence();
		if(Math.random()>0.75) {
			System.out.println(""+randomWord(conjunction));
			randomSentence();
		}
	}

	static void randomNounSentence() {
		if(Math.random()>.75)
			System.out.println(""+ randomWord(properNoun));
		else {
			System.out.println(""+ randomWord(determiner));
			if(Math.random()>0.5)
				System.out.println(""+randomWord(adjective)+".");
			System.out.println(""+randomWord(commonNoun));
			if(Math.random()>0.5) {
				System.out.print("who");
				randomVerbSentence();
			}
		}
	}

	static void randomVerbSentence() {
		if(Math.random()>0.75)
			System.out.println("" + randomWord(intrisitveVerb));
		else if (Math.random()>0.50) {
			System.out.print(""+ randomWord(transitiveVerb));
			randomNounSentence();
			randomVerbSentence();
		}
	}

	static String randomWord(String[]matchStringsTogether) {
		return matchStringsTogether[(int)(Math.random()*matchStringsTogether.length)];
	}
}