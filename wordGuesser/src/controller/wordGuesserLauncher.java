package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        HashMap<String, List<String>> themeWordMap = new HashMap<>();
        File guessWordsFile = new File("resources" + File.separator + "guessable_words.txt");

        try (Scanner fileReader = new Scanner(guessWordsFile)) {
            String theme = "";
            List<String> words = new ArrayList<String>();

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                if (line.startsWith("wordTheme")) {
                    if (!theme.isEmpty()) {
                        themeWordMap.put(theme, new ArrayList<>(words));
                        words.clear();
                    }
                    theme = line.split("=")[1];
                } else {
                    words.add(line);
                }
            }
            themeWordMap.put(theme, words);

        } catch (FileNotFoundException exc) {
            System.out.println("File not found error occurred.");
        }
    
        List<String> themeOptions = new ArrayList<String>(themeWordMap.keySet());
        String gameTheme = getRandomFromList(themeOptions);

        System.out.println("Theme of you word is: " + gameTheme);

        List<String> wordOptions = themeWordMap.get(gameTheme);
        String wordToGuess = getRandomFromList(wordOptions);

        return wordToGuess;
    }

    private static String getRandomFromList(List<String> list) {
        int randomNumber = (int) (Math.random() * (list.size() - 1));
        String randomItem = list.get(randomNumber);

        return randomItem;
    }
}
