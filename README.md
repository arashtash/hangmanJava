# Hangman Java Game
This Java application is a graphical (and non-graphical in a separate file - but that is not discussed in this document) implementation of the classic Hangman game, where players attempt to guess a hidden word by guessing one letter at a time. The graphical game offers two modes: one with general words and another with country names.

# Features
Graphical User Interface (GUI) for an interactive gaming experience.
Two game modes: Words and Countries.
Displays the number of lives remaining and the letters guessed so far.
Shows an error message for invalid input (non-single-letter inputs).

# How to Run
1- Ensure you have Java installed on your machine. You can download it from [here](https://www.oracle.com/java/technologies/downloads/).
2- Download the HangmanUI.java file to your local machine.
3- Open a terminal or command prompt.
4- Navigate to the directory where you downloaded the HangmanUI.java file.
5- Compile the Java file using the following command:

javac HangmanUI.java

6- Run the compiled Java file using the following command:

java HangmanUI

7- The game will start, and you can choose between the Words or Countries game mode.
8- Alternatively you can run the game using and IDE like IntelliJ IDEA.

# How to Play
Once the game starts, a hidden word is displayed as a series of asterisks.
Type a letter in the input field and press the "Guess" button or hit Enter to submit your guess.
If the guessed letter is in the word, it will be revealed in the correct positions.
If the guessed letter is not in the word, you will lose a life.
The game continues until you either guess the word correctly or run out of lives.
If you guess the word correctly, a "You won!" message is displayed.
If you run out of lives, a "Game over" message is displayed, revealing the correct word.

# Notes
The word lists for the game are stored in words.txt and countries.txt files. Make sure these files are in the same directory as HangmanUI.java or update the file paths in the code accordingly.
The game is case-insensitive; it does not matter whether you enter uppercase or lowercase letters.

Enjoy the game!

# Author
Arash Tashakori


[Website and Contact information](https://arashtash.github.io/)

# MIT License

Copyright (c) 2024 arashtash - Arash Tashakori

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.






