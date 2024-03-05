/**
 * @author Arash Tashakori - arashtkr@gmail.com
 * This Java class implements the Hangman game
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

public class Hangman {
    public static void main (String [] args){
        int lives = 7;
        final String filePath = "src/main/java/words.txt";
        String word = getRandomWordFromFile(filePath);
        handleHangmanGame(lives, word);
    }

    /**
     * This list receives a file name with a list of words and returns a random word from the file. It exits if the
     * file name is not found.
     * @param fileName (String) The name of the file containing the words
     * @return (String) A random word from the file
     */
    private static String getRandomWordFromFile (String fileName){
        List<String> words = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(fileName));

            //Adding all the words from the file to the arrayList
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            System.exit(1);
        }
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    /**
     * This method handles the entire logic of the Hangman game given the hidden word and the number of lives
     * @param lives (int) Number of lives that the player begins with
     * @param word (String) The hidden word for the game
     */
    private static void handleHangmanGame(int lives, String word) {
        Scanner kb = new Scanner (System.in);
        boolean [] letterEnteredBefore = new boolean[26];  //For keeping track of whether each letter is entered or not
        String wordFoundSoFar = encodeWordWithStars(word);  //Encoding the word into all *'s e.g. "******" for "Letter"

        char currLetter = kb.next().toLowerCase().charAt(0);
        int indexOfNext = currLetter - 'a';   //Finding the index of the given letter in the array

        while (lives > 0){
            //Check if the entered letter is a letter of the alphabet
            if (indexOfNext < letterEnteredBefore.length && indexOfNext >= 0) {
                boolean found = false;

                /*Checks if the letter exists in the word, if so reveals them in the wordFoundSoFar and sets found to
                true. Also decrements lives if the entered letter is not in the word.*/
                if (!letterEnteredBefore[indexOfNext]) {
                    for (int i = 0; i < word.length(); i++) {
                        if (word.charAt(i) == currLetter) {
                            found = true;
                            wordFoundSoFar = getWordFoundSoFar(wordFoundSoFar, currLetter, i);
                        }
                    }
                    if (!found) {
                        lives--;
                    }
                }

                printCurrSituation(currLetter, lives, wordFoundSoFar, letterEnteredBefore[indexOfNext], found);
                letterEnteredBefore[indexOfNext] = true;
            }  else {  //If the entered letter is not in the alphabet
                System.out.println("The entered letter is not in the alphabet. The letter you entered was: " +
                        currLetter);
            }

            //If all letters have been found. The user wins
            if (word.equals(wordFoundSoFar)) {
                System.out.println("You won! The word was: " + word);
                break;
            } else if (lives > 0) {  //If there are still letters that are not found, get the currLetter input
                currLetter = kb.next().toLowerCase().charAt(0);
                indexOfNext = currLetter - 'a';
            }
        }

        handleGameOver(word, wordFoundSoFar);
    }

    /**
     * This method takes a string and encrypts it to all * (e.g. "word" to "****")
     * @param word (String) word to be encrypted
     * @return (String) A string of all stars with the same length as word
     */
    private static String encodeWordWithStars(String word) {
        String wordFoundSoFar = "";
        for (int i = 0; i < word.length(); i++){
            wordFoundSoFar += "*";
        }
        return wordFoundSoFar;
    }


    /**
     * Given the current state of the letters found as a string of * (for letters that haven't been found) and letters,
     * e.g. l*tt*r (for letter if e has not been found), the given letter at this instance and the index where the
     * letter is found, this method changes the * at the index that the letter was found in the input string and returns
     * it.
     * @param wordFoundSoFar (String) The current state of found and hidden letters
     * @param letter (char) Letter that was found at index i
     * @param i (int) Index where the letter was found
     * @return (String) Reveals letter found at index i
     */
    private static String getWordFoundSoFar(String wordFoundSoFar, char letter, int i) {
        return wordFoundSoFar.substring(0, i) + letter + wordFoundSoFar.substring(i + 1,
                wordFoundSoFar.length());
    }

    /**
     * This method takes the last character entered, the number of lives left, decoded word so far, whether the letter
     * has been entered before, and whether the letter is found in the word or not and prints them and says whether
     * the character was found in the word or not and prints the decoded word so far. It also checks if the character
     * was entered before and if it was, it will say that the character was entered before.
     * @param enteredLetter (char) The last character entered
     * @param lives (int) The number of lives left
     * @param wordToPrint (String) The decoded word so far
     * @param enteredBefore (boolean) Whether the letter is repetitive or not
     * @param found (boolean) Whether the letter entered was found/exists in the mystery word
     */
    private static void printCurrSituation (char enteredLetter, int lives, String wordToPrint, boolean enteredBefore,
                                            boolean found) {
        if ((!enteredBefore) && found){
            System.out.println("The letter " + enteredLetter + " is in the word - " + "Found so far: " + wordToPrint);
            System.out.println("LIVES LEFT: " + lives);
        }  else if (!enteredBefore && !found) {
            System.out.println("The letter " + enteredLetter + " is NOT in the word - " + "Found so far: " + wordToPrint);
            System.out.println("LIVES LEFT: " + lives);
        } else {
            System.out.println("The Letter " + enteredLetter + " was entered before. Please enter a new letter.");
            System.out.println("LIVES LEFT: " + lives);
        }
    }

    /**
     * This method determines whether the user has lost or not when the game is over
     * @param word (String) The original word for this game
     * @param wordFoundSoFar (String) Current status of hidden and found letters
     */
    private static void handleGameOver(String word, String wordFoundSoFar) {
        if (!word.equals(wordFoundSoFar)){
            System.out.println("Game over: The word was " + word);
        }
    }
}


