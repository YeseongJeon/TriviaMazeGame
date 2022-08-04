package view;
import model.*;
import java.io.Serializable;
import java.util.Scanner;
/*
 * The Trivia class is responsible for constructing the maze, room, question database &  GUI.
 * This allows the capabilities to incorporate beginning games, call another  game, 
 * save game, load game, open menu, and get the users' decision. 
 */

public class Trivia implements Serializable{

        static long serialVersionUID = 112L;
        static Scanner scan = new Scanner(System.in);
        static int steps;

        // current location of user and ablility to see next move
        public static char currPlayer(Maze maze) {

                Console.printMaze(maze);

                char playeroption = Console.getUsrDirec(maze);

                return playeroption;

        }

        public static void newGame(Maze maze) {

        }

        // initiate the start of the game by displaying the home page
        public static void startGame() {
                Console.printFirstPage();
                final String FirstPageInput = Console.inputFirstPage();

                if (FirstPageInput.equals("1")) {
                        Maze maze = new Maze();
                        System.out.println("New Game has been selected");
                        newGame(maze);
                } else if (FirstPageInput.equals("2")) {
                        System.out.println("Loading Previous Game");
                        newGame(loadGame());
                } else if (FirstPageInput.equals("3")) {
                        Console.printHelpScreen();
                        startGame();
                } else if (FirstPageInput.equals("4")) {
                } else {
                        System.out.println("Wrong input, try again by typing a digit from 1-4");
                }
        }
        public static void saveGame(Maze maze) {

        }

        public static Maze loadGame() {

        }

        public static boolean menu(Maze maze) {

        }
	
}
