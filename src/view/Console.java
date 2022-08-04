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

    private static final String QUIT_BUTTON = "Q";
    private static final String MAIN_MENU_BUTTON = "M";

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

    public static String inputHomeScreen() {

    }

    public static String inputMenuScreen() {

    }

    // Now lets show the current state of the maze

    public static void printMaze(final Maze ourMaze) {

    }



}
