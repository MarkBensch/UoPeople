package edu.uopeople.cs1102;

import java.util.Scanner;

/**
 * Assignment: Programming Assignment Unit
 * Class: programming 1 - Group D
 * Created by MarkTurnTo on 2/16/17.
 */

public class FirstSubroutines {
    public static void main(String[] arguments){
        String input = getInput();
        String strip = stripped(input);
        String reverse = reverse(strip);

        System.out.println("Stripped: " + strip);
        System.out.println("Reverse: " + reverse);

        if(isPalindrome(strip, reverse)){
            System.out.println("This IS a palindrome!");

        } else {
            System.out.println("This is NOT a palindrome!");

        }
    }

    /**
     * Reverse the string given in the parameter
     * and return the reversed string
     * @param str is a string that will be reversed
     * @return the reverse of str will get returned
     */
    public static String reverse(String str){
        String reverse = "";
        int i;

        for (i = str.length() - 1; i >= 0; i--) {
            reverse = reverse + str.charAt(i);
        }
        return reverse;
    }

    /**
     * Take the string given in the parameter
     * and remove all other symbols along with
     * lowercase all letters
     * return the new stripped string
     * @param str will be stripped of all non lowercase letters
     * @return returns the str stripped of non lowercase letters
     */
    public static String stripped(String str){
        String letters = "";
        str = str.toLowerCase();
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(ch >= 'a' && ch <= 'z'){
                letters = letters + ch;
            }
        }
        return letters;
    }

    /**
     * check if parameters are a palindrome of themselves and return a boolean
     * @param str a stripped string
     * @param reversed a reverse of the stripped string
     * @return true or false depending on Palindrome
     */
    public static Boolean isPalindrome(String str, String reversed){
        return str.equals(reversed);
    }

    /**
     * get the input of the console
     * @return the input of the console
     */
    public static String getInput(){
        System.out.println("Please enter a palindrome: ");
        return new Scanner(System.in).nextLine();
    }

}
