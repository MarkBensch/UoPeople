package edu.uopeople.cs1103.unit2.grading;

/*

Some rules that capture the syntax of this verse:

    
<sentence> ::= <simple_sentence> [ <conjunction> <sentence> ]

<simple_sentence> ::= <noun_phrase> <verb_phrase>

<noun_phrase> ::= <proper_noun> | 
<determiner> [ <adjective> ]. <common_noun> [ who <verb_phrase> ]

<verb_phrase> ::= <intransitive_verb> | 
<transitive_verb> <noun_phrase> |
is <adjective> |
believes that <simple_sentence>

<conjunction> ::= and | or | but | because

<proper_noun> ::= Fred | Jane | Richard Nixon | Miss America

<common_noun> ::= man | woman | fish | elephant | unicorn

<determiner> ::= a | the | every | some

<adjective> ::= big | tiny | pretty | bald

<intransitive_verb> ::= runs | jumps | talks | sleeps

<transitive_verb> ::= loves | hates | sees | knows | looks for | finds
    
  This program implements these rules to generate random sentences.  All the
  verses of the rhyme can be generated, plus a lot of sentences that make no
  sense (but still follow the syntax).   Note that an optional item like
  [ <modifier> ] has a chance of being used, depending on the value of some
  randomly generated number.
  
  The program generates and outputs one random sentence every three seconds until
  it is halted (for example, by typing Control-C in the terminal window where it is
  running).
*/


public class SimpleRandomSentences {

    static final String[] conjunction = { "and", "or", "but", "because"};
                                   
    static final String[] proper_noun = { "Fred", "Jane", "Richard Nixon",
                                   "Miss America"};

    static final String[] common_noun = {"man", "woman", "fish", "elephant", "unicorn"};
    
    static final String[] determiner = {"a","the", "every", "some"};
    static final String[] adjective = {"big", "tiny", "pretty", "bald"};
    static final String[] intransitive_verb = {"runs", "jumps", "talks", "sleeps"};
    static final String[] transitive_verb = { "loves", "hates", "sees", "knows", "looks for", "finds"};
    

   public static void main(String[] args) {
      while (true) {
         randomSentence();
	     System.out.print(".\n\n");
         try {
             Thread.sleep(3000);
         }
         catch (InterruptedException e) {
         }
      }
   }

 /**
 * Generate a random sentence, following the grammar rule for a sentence.
 */  
   static void randomSentence() {
      randomSimpleSentence();
      
      if (Math.random() > 0.75) {
          int c = (int)(Math.random()*conjunction.length);
          System.out.print(" "+ conjunction[c]);
          randomSentence();
      }
   }
   
/**
 * Generate a random simple_sentence, following the grammar rule for a simple_sentence.
 */   
   static void randomSimpleSentence(){
      randomNounPhrase();
      randomVerbPhrase();
   }

/**
* Generates a random noun_phrase, following the grammar rule for a noun_phrase.
*/  
   static void randomNounPhrase() {
          int pn = (int)(Math.random()*proper_noun.length);
          int cn = (int)(Math.random()*common_noun.length);
          int adj = (int)(Math.random()*adjective.length);
          int det = (int)(Math.random()*determiner.length);
          
          if (Math.random() > 0.5){
            System.out.print(" " + proper_noun[pn]);
          }else{
              System.out.print(" "+ determiner[det]);
              if (Math.random() > 0.5){
	            System.out.print(" "+ adjective[adj]);
              }
              
              System.out.print(" "+ common_noun[cn]);
              if (Math.random() > 0.5){
	            System.out.print(" who ");
                    randomVerbPhrase();
              }
              
          }
          
   }

/**
* Generates a random verb_phrase, following the grammar rule for a verb_phrase.
*/ 
   
    static void randomVerbPhrase() {
        
        int intrv = (int)(Math.random()*intransitive_verb.length);
        int trv = (int)(Math.random()*transitive_verb.length);
        int adj = (int)(Math.random()*adjective.length);
        
        if (Math.random() > 0.2){
            System.out.print(" "+ intransitive_verb[intrv]);
        } 
        else if (Math.random() > 0.4){
            System.out.print(" " + transitive_verb[trv]);
            randomNounPhrase();
        }else if(Math.random() > 0.6){
            System.out.print(" is " + adjective[adj]);
        }else{
            System.out.print(" believes that ");
            randomSentence();
        }
        
    }

}