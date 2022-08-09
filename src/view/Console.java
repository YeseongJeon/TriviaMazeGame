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
        System.out.println("'X' is for the number of room that are locked.");
        System.out.println("'O' is for the number of rooms you have unlocked.");
        System.out.println("'*' represents the room you are in.");
        System.out.println();
        System.out.println("Choose an option below: ");
        System.out.println(
                "NEW GAME (Press 1)      LOAD GAME (Press 2)      HELP SCREEN (Press 3)      QUIT (Press 4)");
    }

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
        System.out.println("Press W for moving upwards");
        System.out.println("Press A for moving to the left");
        System.out.println("Press S for moving downwards");
        System.out.println("Press D for moving to the right");
        System.out.println();

    }
    // Now I need to get user input for both the Home Screen and Menu Screen

    public static String inputFirstPage() {
        String user = sc.next();
        final Set<String> set = new HashSet<>(Arrays.asList("1", "2", "3", "4"));

        while (!set.contains(user)) {
            System.out.println("Invalid input, try again.");
            user = sc.next();
        }
        return user;

    }

    public static String inputMenuScreen() {
        String menusInput = sc.next();
        final Set<String> options = new HashSet<>(Arrays.asList("1", "2", "3", "4", "5"));
        while (!options.contains(menusInput)) {
            System.out.println("INCORRECT INPUT, Try Again");
            menusInput = sc.next();
        }
        return menusInput;
    }

    // Now lets show the current state of the maze
    public static void printMaze(final Maze ourMaze) {
        System.out.println(ourMaze);
        // tell player now where they are currently at
        System.out.println("Your current position in the maze is at" + ourMaze.getRIndex() + " row and" + ourMaze.getCindex() + " column.");

        System.out.println("Please select any door");

        System.out.println(ourMaze.getCurrAvailDoors());
    }

    public static char getUsrDirec(final Maze ourMaze) {
        final Set<Character> set = new HashSet<>();
        for (char ch : ourMaze.getCurrentAvailableDoors()) {
            set.add(ch);
        }
        set.add(MAIN_MENU_BUTTON);
        set.add(QUIT_BUTTON);

        char next = Character.toUpperCase(sc.next().charAt(0));
        while (!set.contains(next)) {
            System.out.println("Invalid input, try again!");
            next = Character.toUpperCase(sc.next().charAt(0));
        }
        return next;
    }

    public static String getplayerInput() {
        System.out.println("Your Answer:");
        final String player1 = sc.next();
        return player1.toLowerCase();
    }

    public static String getFileName() {
        System.out.println("Please enter the name of the file: ");
        final String filename = sc.next();
        return filename;
    }

    public static void printWonOrLost(final boolean mazePlayer) {
        if (mazePlayer) {
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
