import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

public class HangmanUI extends JFrame {
    private int lives = 7;
    private String word;
    private String wordFoundSoFar;
    private boolean[] letterEnteredBefore = new boolean[26];
    private JLabel labelOfWord;
    private JLabel labelForLives;
    private JTextField inputField;
    private JLabel errorMessage;

    // Constructor to set up the GUI
    public HangmanUI(String filename) {
        word = getRandomWordFromFile(filename);
        wordFoundSoFar = encodeWordWithStars(word);

        setTitle("HangmanJava");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);    //Centre the window on the screen

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Adding labels to the panel for wordSoFar and Lives
        labelOfWord = new JLabel(wordFoundSoFar);
        labelOfWord.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(labelOfWord);
        labelForLives = new JLabel("Lives: " + lives);
        labelForLives.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(labelForLives);

        //Adding the field for inputs
        inputField = new JTextField();
        inputField.setMaximumSize(new Dimension(100, 30));
        inputField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(inputField);

        //Adding the error message to notify user of wrong input
        errorMessage = new JLabel("");
        errorMessage.setForeground(Color.RED);
        errorMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(errorMessage);

        //Adding submit button to the panel
        JButton submitButton = new JButton ("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        //Handling submitting action: calls handleGuess upon submitting
        submitButton.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess ();
            }
        });
        panel.add(submitButton);

        add(panel);
        setVisible(true);
    }

    // Method to handle the guessing and guess checking logic for the game
    private void handleGuess() {
        String input = inputField.getText().toLowerCase();  //Standardize to lower case
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {  //If the entered input is not a letter
            errorMessage.setText("Please enter a single letter only."); // Set up the error message
            return;
        } else {
            errorMessage.setText(""); // Clear the error message if the input is valid
        }

        char currLetter = input.charAt(0);
        int indexOfNext = currLetter - 'a';

        /*Checks if the letter exists in the word, if so reveals them in the wordFoundSoFar and sets found to
          true. Also decrements lives if the entered letter is not in the word.*/
        if (indexOfNext >= 0 && indexOfNext < letterEnteredBefore.length && !letterEnteredBefore[indexOfNext]) {
            boolean found = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == currLetter) {
                    found = true;
                    wordFoundSoFar = getWordFoundSoFar(wordFoundSoFar, currLetter, i);
                }
            }
            if (!found) {
                lives--;
            }
            letterEnteredBefore[indexOfNext] = true;
        }

        updateUI();

        if (word.equals(wordFoundSoFar)) {
            endGame("You won! The word was: " + word);
        } else if (lives <= 0) {
            endGame("Game over: The word was " + word);
        }
    }

    // Method to update the state of the current game, i.e. wordFoundSoFar and lives
    private void updateUI() {
        labelOfWord.setText(wordFoundSoFar);
        labelForLives.setText("Lives: " + lives);
        inputField.setText("");
    }

    // Method to end the game and show a message
    private void endGame(String message) {
        JOptionPane.showMessageDialog(this, message);
        System.exit(0);
    }

    //This method receives a file name consisting of a list of words and returns a random word (String) from it
    private static String getRandomWordFromFile(String fileName) {
        List<String> words = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                //Adding all the words from the file to the arrayList
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

    // Given a word, this method returns a string consisting of word.length() starts - hidden word
    private static String encodeWordWithStars(String word) {
        String wordFoundSoFar = "";
        for (int i = 0; i < word.length(); i++){
            wordFoundSoFar += "*";
        }
        return wordFoundSoFar;
    }

    //Gets the current status of the parts of the word found up until now as a String
    private static String getWordFoundSoFar(String wordFoundSoFar, char letter, int i) {
        return wordFoundSoFar.substring(0, i) + letter + wordFoundSoFar.substring(i + 1);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HangmanUI("src/main/java/words.txt");
            }
        });
    }
}
