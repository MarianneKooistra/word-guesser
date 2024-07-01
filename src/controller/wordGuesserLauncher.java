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
        while (userGuess.length() != wordToGuess.length()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder   .append("The word is ")
                            .append(wordToGuess.length())
                            .append(" characters long, your guess was ")
                            .append(userGuess.length())
                            .append(" characters long.");
            String lengthMessage = stringBuilder.toString();
            System.out.println(lengthMessage);
            System.out.println("Try again: ");
            userGuess = keyboardScanner.nextLine();
        }
        keyboardScanner.close();

        System.out.println("The word was\t ... \t" + wordToGuess + "!");
        System.out.println("Your guess was\t ... \t" + userGuess);

        if (wordToGuess.equals(userGuess)) {
            System.out.println("Congratulations! You guessed correct.\n");
        }
        else {
            System.out.println("I'm sorry, your guess was incorrect.\n");
        }
    }
}