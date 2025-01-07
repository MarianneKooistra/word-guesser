package controller;

import java.util.Scanner;

/*
* @author Marianne Kooistra 
* A little game to guess a words, like Wordle, Lingo or Mastermind.
*/

public class wordGuesserLauncher {
    public static void main(String[] args) {
        String wordToGuess = getWordToGuess();

        Scanner keyboardScanner = new Scanner(System.in);
        String userGuess = "";
        int attempts = 0;

        StringBuilder stringBuilderCorrectLetters = new StringBuilder();
        for (int i = 0; i < wordToGuess.length(); i++) {
            stringBuilderCorrectLetters.append("-");
        }

        System.out.println("\n**********");
        System.out.println("Your word has " + wordToGuess.length() + " characters.");
        System.out.println("Give your best guess:");
        
        while (!userGuess.equals(wordToGuess)) {
            attempts++;
            userGuess = keyboardScanner.nextLine();
            
            while (userGuess.length() != wordToGuess.length()) {
                showMessageForLengthDiscrepancy(wordToGuess, userGuess);
                userGuess = keyboardScanner.nextLine();
            }

            for (int i = 0; i < userGuess.length(); i++) {
                if (userGuess.charAt(i) == wordToGuess.charAt(i)) {
                    stringBuilderCorrectLetters.setCharAt(i, userGuess.charAt(i));
                }
            }

            String correctLetters = stringBuilderCorrectLetters.toString();
            if (correctLetters.contains("-")) {
                System.out.println(correctLetters);
            }
        }

        keyboardScanner.close();

        showFinalMessage(attempts);
    }

    private static void showFinalMessage(int attempts) {
        System.out.println("\nCongratulations! You guessed correct.\n");
        System.out.println("It took you " + attempts + ((attempts < 2) ? " attempt!" : " attempts!"));
    }

    private static void showMessageForLengthDiscrepancy(String wordToGuess, String userGuess) {
        StringBuilder stringBuilderLength = new StringBuilder();
        stringBuilderLength.append("The word is ")
                        .append(wordToGuess.length())
                        .append(" characters long, your guess was ")
                        .append(userGuess.length())
                        .append(" characters long.");
        String lengthMessage = stringBuilderLength.toString();

        System.out.println(lengthMessage);
        System.out.println("Try again: ");
    }

    private static String getWordToGuess() {
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
        return wordToGuess;
    }
}
