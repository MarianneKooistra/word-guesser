package controller;

import java.util.Scanner;

/*
* @author Marianne Kooistra 
* A little game to guess a words, like Wordle, Lingo or Mastermind.
*/

// TODO: Work laptop needs the extension package for java.

public class wordGuesserLauncher {
    public static void main(String[] args) {
        String[] wordOptions = {
            "one",
            "two",
            "three",
            "four",
            "five"
        };

        String wordToGuess;

        int randomNumber = (int) (Math.random() * (wordOptions.length - 1));
        wordToGuess = wordOptions[randomNumber];

        Scanner keyboardScanner = new Scanner(System.in);
        String userGuess = "";
        int attempts = 0;


        System.out.println("\n**********");
        System.out.println("Your word has " + wordToGuess.length() + " characters.");
        System.out.println("Give your best guess:");
        
        while (!userGuess.equals(wordToGuess)) {
            attempts++;
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

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < userGuess.length(); i++) {
                if (userGuess.charAt(i) == wordToGuess.charAt(i)) {
                    stringBuilder.append(userGuess.charAt(i));
                }
                else {
                    stringBuilder.append("-");
                }
            }
            String correctLetters = stringBuilder.toString();
            if (correctLetters.contains("-")) {
                System.out.println(correctLetters);
            }
        }

        keyboardScanner.close();

        System.out.println("\nCongratulations! You guessed correct.\n");
        System.out.println("It took you " + attempts + ((attempts < 2) ? " attempt!" : " attempts!"));
    }
}
