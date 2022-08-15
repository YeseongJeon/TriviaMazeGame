package view;

import model.GenerateRandomQuestions;
import model.Maze;
import model.Question;

import java.io.*;
/*
 * The Trivia class is responsible for constructing the maze, room, question database &  GUI.
 * This allows the capabilities to incorporate beginning games, call another  game,
 * save game, load game, open menu, and get the users' decision.
 */

public class Trivia implements Serializable {

    static long serialVersionUID = 112L;
    // static Scanner scan = new Scanner(System.in);
    static int steps;

    // current location of user and ability to see next move
    public static char currPlayer(Maze maze) {
        Console.printMaze(maze);

        char playeroption = Console.getUsrDirec(maze);

        return playeroption;
    }

    public static void newGame(Maze maze) {
        steps = 0;
        while (!maze.isLastRoom() && !maze.isCurrentRoomLocked()) {
            if (steps == 15)
                break;
            if (steps == 10) {
                System.out.println("  Warning Alert  ");
                System.out.println("  Only 4 moves left  ");
                System.out.println();
            }
            int result = 0;
            char playerClick = currPlayer(maze);
            if (playerClick == 'Q')
                break;
            while (playerClick == 'M') {
                if (playerClick == 'M') {
                    if (!menu(maze)) {
                        result = 3;
                        break;
                    }
                    playerClick = currPlayer(maze);
                }
            }
            if (result == 4)
                break;
            if (!maze.isCurrentRoomDoorOpen(playerClick)) {

                GenerateRandomQuestions grq = new GenerateRandomQuestions();
                Question q = grq.createQuestion();

                System.out.println(q.getQuestion()); // prints question from table
                System.out.println(q.getChoices()); // gets the answer from table
                String playerAnswer = Console.getplayerInput();

                while (playerAnswer.equals("M") || playerAnswer.equals("?")) {
                    if (playerAnswer.equals("M")) {
                        if (menu(maze) == false) {
                            result = 4;
                            break;
                        }
                        System.out.println(maze);
                        System.out.println(q);
                        System.out.println(q.getAnswer());
                        playerAnswer = Console.getplayerInput();
                    }
                }
                if (result == 4)
                    break;
                if (playerAnswer.equals(q.getAnswer().toLowerCase())) {
                    maze.unlock(playerClick);
                    maze.move(playerClick);
                    steps++;
                } else {
                    maze.deleteCurrentRoomDoor(playerClick);
                }
            } else {
                maze.move(playerClick);
                steps++;
            }
        }
        Console.printWonOrLost(maze.isLastRoom());
    }

    // initiate the start of the game by displaying the home page
    public static void startGame() {

        Console.printFirstPage();

        final String firstPageInput = Console.inputFirstPage();

        switch (firstPageInput) {
            case "1":
                Maze maze = new Maze();
                System.out.println("New Game has been selected");
                newGame(maze);
                break;
            case "2":
                System.out.println("Loading Previous Game");
                newGame(loadGame());
                break;
            case "3":
                Console.printHelpScreen();
                startGame();
                break;
            case "4":
                break;
            default:
                System.out.println("WRONG INPUT, TRY AGAIN by entering a number between 1 and 4");
                break;
        }
    }

    public static void saveGame(Maze maze) {
        try {
            FileOutputStream outputStream = new FileOutputStream(Console.getFileName() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            out.writeObject(maze);
            out.close();
            outputStream.close();
            System.out.println("Thank You! The Game Has Been Saved!!!");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Maze loadGame() {
        Maze maze = null;
        try {
            FileInputStream grabFile = new FileInputStream(Console.getFileName() + ".ser");
            ObjectInputStream in = new ObjectInputStream(grabFile);
            maze = (Maze) in.readObject();
            in.close();
            grabFile.close();
        } catch (IOException i) {
            i.printStackTrace();
            return maze;
        } catch (ClassNotFoundException c) {
            System.out.println(" ERROR! Could Not Retrieve the Maze ");
            c.printStackTrace();
            return maze;
        }
        return maze;

    }

    public static boolean menu(Maze maze) {
        Console.printMenuScreen();
        final String menuChoice = Console.inputMenuScreen();
        switch (menuChoice) {
                case "1":
                    saveGame(maze);
                    return true;
                case "2":
                    startGame();
                    return true;
                case "3":
                    return true;
                case "4":
                    Console.printHelpScreen();
                    break;
            case "5":
                System.exit(0);
                default:
                    return false;
            }
            return true;
        }

    }

/*

Console.printFirstPage();

final String firstPageInput = Console.inputFirstPage();

        switch (firstPageInput) {
        case "1":
        Maze maze = new Maze();
        System.out.println("New Game has been selected");
        newGame(maze);
        break;
        case "2":
        System.out.println("Loading Previous Game");
        newGame(loadGame());
        break;
        case "3":
        Console.printHelpScreen();
        startGame();
        break;
        case "4":
        break;
default:
        System.out.println("WRONG INPUT, TRY AGAIN by entering a number between 1 and 4");
        break;
        }*/
