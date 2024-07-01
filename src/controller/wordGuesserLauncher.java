package controller;

import java.util.Scanner;

/*
* @author Marianne Kooistra 
* A little game to guess a words, like Wordle, Lingo or Mastermind.
*/

public class wordGuesserLauncher {
    public static void main(String[] args) {
        String wordToGuess;
        wordToGuess = "four";

        Scanner keyboardScanner = new Scanner(System.in);
        String userGuess;
        System.out.println("\n**********");
        System.out.println("Give your best guess:");

        userGuess = keyboardScanner.nextLine();
        keyboardScanner.close();

        System.out.println("The word was ... \t" + wordToGuess + "!");
        System.out.println("Your guess was ... \t" + userGuess);
    }
}