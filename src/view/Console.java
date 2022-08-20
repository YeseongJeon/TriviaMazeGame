package view;

import model.Maze;

import java.util.*;

/* This class will be responsible for many of our Console based interface needs
 *
 * author Liban Jama
 * Version 1.0
 */

public class Console {

    private static final Scanner sc = new Scanner(System.in);

    private static final char QUIT_BUTTON = 'Q';
    private static final char MAIN_MENU_BUTTON = 'M';


    /**
     * This function prints out the first page of the game
     */
    public static void printFirstPage() {


        //Got this Trivia Maze Ascii Art and the rest of our Ascii art from https://fsymbols.com/generators/carty/


        System.out.println("\n" +
                "██████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████\n" +
                "█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░█░░░░░░░░░░░░░░████░░░░░░██████████░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█\n" +
                "█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀░░░░░░░░░░░░░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█\n" +
                "█░░░░░░▄▀░░░░░░█░░▄▀░░░░░░░░▄▀░░███░░░░▄▀░░░░█░░▄▀░░██░░▄▀░░█░░░░▄▀░░░░█░░▄▀░░░░░░▄▀░░████░░▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░░░░░▄▀░░█░░░░░░░░░░░░▄▀▄▀░░█░░▄▀░░░░░░░░░░█\n" +
                "█████░░▄▀░░█████░░▄▀░░████░░▄▀░░█████░░▄▀░░███░░▄▀░░██░░▄▀░░███░░▄▀░░███░░▄▀░░██░░▄▀░░████░░▄▀░░░░░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░█████████░░░░▄▀░░░░█░░▄▀░░█████████\n" +
                "█████░░▄▀░░█████░░▄▀░░░░░░░░▄▀░░█████░░▄▀░░███░░▄▀░░██░░▄▀░░███░░▄▀░░███░░▄▀░░░░░░▄▀░░████░░▄▀░░██░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░███████░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█\n" +
                "█████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀▄▀░░█████░░▄▀░░███░░▄▀░░██░░▄▀░░███░░▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀░░██░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█████░░░░▄▀░░░░█████░░▄▀▄▀▄▀▄▀▄▀░░█\n" +
                "█████░░▄▀░░█████░░▄▀░░░░░░▄▀░░░░█████░░▄▀░░███░░▄▀░░██░░▄▀░░███░░▄▀░░███░░▄▀░░░░░░▄▀░░████░░▄▀░░██░░░░░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░███░░░░▄▀░░░░███████░░▄▀░░░░░░░░░░█\n" +
                "█████░░▄▀░░█████░░▄▀░░██░░▄▀░░███████░░▄▀░░███░░▄▀▄▀░░▄▀▄▀░░███░░▄▀░░███░░▄▀░░██░░▄▀░░████░░▄▀░░██████████░░▄▀░░█░░▄▀░░██░░▄▀░░█░░░░▄▀░░░░█████████░░▄▀░░█████████\n" +
                "█████░░▄▀░░█████░░▄▀░░██░░▄▀░░░░░░█░░░░▄▀░░░░█░░░░▄▀▄▀▄▀░░░░█░░░░▄▀░░░░█░░▄▀░░██░░▄▀░░████░░▄▀░░██████████░░▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀░░░░░░░░░░░░█░░▄▀░░░░░░░░░░█\n" +
                "█████░░▄▀░░█████░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀░░███░░░░▄▀░░░░███░░▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░████░░▄▀░░██████████░░▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█\n" +
                "█████░░░░░░█████░░░░░░██░░░░░░░░░░█░░░░░░░░░░█████░░░░░░█████░░░░░░░░░░█░░░░░░██░░░░░░████░░░░░░██████████░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█\n" +
                "██████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████");
        System.out.println();
        System.out.println("               QUICK INFO");
        System.out.println("Press 'M' for the Game's Main Menu.");
        System.out.println("'L' is for the number of room that are locked.");
        System.out.println("'O' is for the number of rooms you have unlocked.");
        System.out.println("'*' represents the room you are in.");
        System.out.println();
        System.out.println("Choose an option below: ");
        System.out.println(
                "NEW GAME (Press 1)      LOAD GAME (Press 2)      HELP SCREEN (Press 3)      QUIT (Press 4)");
    }

    /**
     * This function prints the main mainMenu screen
     */
    public static void printMenuScreen() {

        System.out.println("\n" +
                "█▀▄▀█ ▄▀█ █ █▄░█   █▀▄▀█ █▀▀ █▄░█ █░█\n" +
                "█░▀░█ █▀█ █ █░▀█   █░▀░█ ██▄ █░▀█ █▄█");
        System.out.println();
        System.out.println("    SAVE GAME       (Press 1)");
        System.out.println("    GO TO HOME PAGE (Press 2)");
        System.out.println("    CLOSE MAIN MENU (Press 3)");
        System.out.println("    HELP PAGE       (Press 4)");
        System.out.println("    QUIT GAME       (Press 5)");
        System.out.println("\n" +
                "█▀▄▀█ ▄▀█ █ █▄░█   █▀▄▀█ █▀▀ █▄░█ █░█\n" +
                "█░▀░█ █▀█ █ █░▀█   █░▀░█ ██▄ █░▀█ █▄█");

    }

    /**
     * This function prints out the help screen for the user to see
     */
    public static void printHelpScreen() {

        System.out.println("\n" +
                "██╗░░██╗███████╗██╗░░░░░██████╗░  ░██████╗░█████╗░██████╗░███████╗███████╗███╗░░██╗\n" +
                "██║░░██║██╔════╝██║░░░░░██╔══██╗  ██╔════╝██╔══██╗██╔══██╗██╔════╝██╔════╝████╗░██║\n" +
                "███████║█████╗░░██║░░░░░██████╔╝  ╚█████╗░██║░░╚═╝██████╔╝█████╗░░█████╗░░██╔██╗██║\n" +
                "██╔══██║██╔══╝░░██║░░░░░██╔═══╝░  ░╚═══██╗██║░░██╗██╔══██╗██╔══╝░░██╔══╝░░██║╚████║\n" +
                "██║░░██║███████╗███████╗██║░░░░░  ██████╔╝╚█████╔╝██║░░██║███████╗███████╗██║░╚███║\n" +
                "╚═╝░░╚═╝╚══════╝╚══════╝╚═╝░░░░░  ╚═════╝░░╚════╝░╚═╝░░╚═╝╚══════╝╚══════╝╚═╝░░╚══╝");
        System.out.println();
        System.out.println(" \n" +
                "▄▀█ █▄▄ █▀█ █░█ ▀█▀   ▀█▀ █▀█ █ █░█ █ ▄▀█   █▀▄▀█ ▄▀█ ▀█ █▀▀\n" +
                "█▀█ █▄█ █▄█ █▄█ ░█░   ░█░ █▀▄ █ ▀▄▀ █ █▀█   █░▀░█ █▀█ █▄ ██▄ ");
        System.out.println();
        System.out.println("\n" +
                "█▀▀█ █░░ █▀▀█ █░░█ █▀▀ █▀▀█ \n" +
                "█░░█ █░░ █▄▄█ █▄▄█ █▀▀ █▄▄▀ \n" +
                "█▀▀▀ ▀▀▀ ▀░░▀ ▄▄▄█ ▀▀▀ ▀░▀▀");
        System.out.println();
        System.out.println("In order to win you must go through each room and locate the exit");
        System.out.println("In order to exit a room and unlock it you must answer a trivia question truthfully");
        System.out.println("There are three types of questions 1) True/False 2) Short Response and lastly 3) Multiple Choice ");
        System.out.println("You will only have a number of choices before it is Game Over, so answer wisely.");
        System.out.println();
        System.out.println("\n" +
                "█▀▀ █▀▀█ █▀▀▄ ▀▀█▀▀ █▀▀█ █▀▀█ █── █▀▀ \n" +
                "█── █──█ █──█ ──█── █▄▄▀ █──█ █── ▀▀█ \n" +
                "▀▀▀ ▀▀▀▀ ▀──▀ ──▀── ▀─▀▀ ▀▀▀▀ ▀▀▀ ▀▀▀");
        System.out.println("Press N for moving to the North!");
        System.out.println("Press W for moving to the West!");
        System.out.println("Press S for moving downwards!");
        System.out.println("Press E for moving to the East!");
        System.out.println();

    }

    /**
     * This function takes in a user input and checks if it is a valid input. If it is not, it will ask the user to input
     * again
     *
     * @return A string
     */
    public static String inputFirstPage() {
        String user = sc.next();
        final Set<String> set = new HashSet<>(Arrays.asList("1", "2", "3", "4"));

        while (!set.contains(user)) {
            System.out.println("Invalid input, try again.");
            user = sc.next();
        }
        return user;

    }

    /**
     * This function takes in a user input and checks if it is one of the options in the mainMenu. If it is not, it will ask
     * the user to input again
     *
     * @return The input from the user.
     */
    public static String inputMenuScreen() {
        String menusInput = sc.next();
        final Set<String> options = new HashSet<>(Arrays.asList("1", "2", "3", "4", "5"));
        while (!options.contains(menusInput)) {
            System.out.println("INCORRECT INPUT, Try Again");
            menusInput = sc.next();
        }
        return menusInput;
    }

    /**
     * This function prints out the maze, the current position of the player, and the available doors
     *
     * @param theMaze the maze object that we are currently working with
     */
    public static void printMaze(final Maze theMaze) {
        System.out.println(theMaze);
        // tell player now where they are currently at
        System.out.println("Your current position in the maze is at row " + theMaze.getRowIndex() + " and at column " + theMaze.getColIndex());

        System.out.println("Please select any door");

        System.out.println(theMaze.getCurrentAvailableDoors());
    }

    /**
     * This function takes in a Maze object and returns a character that is either a direction, the main mainMenu button, or
     * the quit button
     *
     * @param theMaze The maze that the user is currently in.
     * @return A character
     */
    public static char getUsrDirec(final Maze theMaze) {
        final Set<Character> set = new HashSet<>();
        for (char ch : theMaze.getCurrentAvailableDoors()) {
            set.add(ch);
        }
        set.add(MAIN_MENU_BUTTON);
        set.add(QUIT_BUTTON);

        char next = Character.toUpperCase(sc.next().charAt(0));
        while (!set.contains(next)) {
            System.out.println("That Direction is now allowed!");
            next = Character.toUpperCase(sc.next().charAt(0));
        }
        return next;
    }

    /**
     * This function gets the player's input and returns it as a string
     *
     * @return The player's input.
     */
    public static String getplayerInput() {
        System.out.println("Your Answer:");
        final String player1 = sc.next();
        return player1.toLowerCase();
    }

    /**
     * This function prompts the user to enter the name of the file and returns the name of the file
     *
     * @return The name of the file.
     */
    public static String getFileName() {
        System.out.println("Please enter the name of the file: ");
        final String filename = sc.next();
        return filename;
    }


    /**
     * If the mazePlayer is true, print the ASCII art for winning, otherwise print the ASCII art for losing
     *
     * @param theMazePlayer true if the player won, false if the player lost
     */
    public static void printWonOrLost(final boolean theMazePlayer) {
        if (theMazePlayer) {
            System.out.println("\n" +
                    "██╗░░░██╗░█████╗░██╗░░░██╗  ░██╗░░░░░░░██╗░█████╗░███╗░░██╗██╗\n" +
                    "╚██╗░██╔╝██╔══██╗██║░░░██║  ░██║░░██╗░░██║██╔══██╗████╗░██║██║\n" +
                    "░╚████╔╝░██║░░██║██║░░░██║  ░╚██╗████╗██╔╝██║░░██║██╔██╗██║██║\n" +
                    "░░╚██╔╝░░██║░░██║██║░░░██║  ░░████╔═████║░██║░░██║██║╚████║╚═╝\n" +
                    "░░░██║░░░╚█████╔╝╚██████╔╝  ░░╚██╔╝░╚██╔╝░╚█████╔╝██║░╚███║██╗\n" +
                    "░░░╚═╝░░░░╚════╝░░╚═════╝░  ░░░╚═╝░░░╚═╝░░░╚════╝░╚═╝░░╚══╝╚═╝");
        } else {
            System.out.println("\n" +
                    "██╗░░░██╗░█████╗░██╗░░░██╗  ██╗░░░░░░█████╗░░██████╗████████╗██╗  \n" +
                    "╚██╗░██╔╝██╔══██╗██║░░░██║  ██║░░░░░██╔══██╗██╔════╝╚══██╔══╝██║  \n" +
                    "░╚████╔╝░██║░░██║██║░░░██║  ██║░░░░░██║░░██║╚█████╗░░░░██║░░░██║  \n" +
                    "░░╚██╔╝░░██║░░██║██║░░░██║  ██║░░░░░██║░░██║░╚═══██╗░░░██║░░░╚═╝  \n" +
                    "░░░██║░░░╚█████╔╝╚██████╔╝  ███████╗╚█████╔╝██████╔╝░░░██║░░░██╗  \n" +
                    "░░░╚═╝░░░░╚════╝░░╚═════╝░  ╚══════╝░╚════╝░╚═════╝░░░░╚═╝░░░╚═╝  ");
        }
    }
}
