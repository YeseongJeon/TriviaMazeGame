package view;

import model.GenerateRandomQuestions;
import model.Maze;
import model.Question;

import java.io.*;
public class Trivia implements Serializable {

    static long serialVersionUID = 112L;
    // static Scanner scan = new Scanner(System.in);
    static int steps;

    /**
     * This function prints the maze, and then gets the user's direction
     *
     * @param theMaze the maze object
     * @return The player's choice of direction.
     */
    public static char currentPlayer(final Maze theMaze) {
        Console.printMaze(theMaze);

        char playeroption = Console.getUsrDirec(theMaze);

        return playeroption;
    }

    /**
     * The function takes in a maze object and then it checks if the player is in the last room or if the current room is
     * locked. If the player is in the last room or the current room is locked, the function will print out a message
     * saying that the player has won or lost. If the player is not in the last room and the current room is not locked,
     * the function will then check if the player has taken 10 steps. If the player has taken 10 steps, the function will
     * print out a warning message saying that the player only has 4 moves left. If the player has not taken 10 steps, the
     * function will then check if the player has taken 15 steps. If the player has taken 15 steps, the function will break
     * out of the loop. If the player has not taken 15 steps, the function will then check if the player has clicked on the
     * mainMenu button. If the player has clicked on the mainMenu button, the function will call the mainMenu function and then check
     *
     * @param theMaze the maze object that is created in the main method.
     */
    public static void newGame(final Maze theMaze) {
        steps = 0;
        while (!theMaze.isLastRoom() && !theMaze.isCurrentRoomLocked()) {
            if (steps == 15)
                break;
            if (steps == 10) {
                System.out.println("  Warning Alert  ");
                System.out.println("  Only 4 moves left  ");
                System.out.println();
            }
            int result = 0;
            char playerClick = currentPlayer(theMaze);
            if (playerClick == 'Q')
                break;
            while (playerClick == 'M') {
                if (playerClick == 'M') {
                    if (!mainMenu(theMaze)) {
                        result = 3;
                        break;
                    }
                    playerClick = currentPlayer(theMaze);
                }
            }
            if (result == 4)
                break;
            if (!theMaze.isCurrentRoomDoorOpen(playerClick)) {

                GenerateRandomQuestions grq = new GenerateRandomQuestions();
                Question q = grq.createQuestion();

                System.out.println(q.getQuestion());
                System.out.println(q.getChoices());
                /*System.out.println(q.getAnswer());*/
                String playerAnswer = Console.getplayerInput();
                String cheat = "Cheat";


                while (playerAnswer.equals("M") || playerAnswer.equals("?")) {
                    if (playerAnswer.equals("M")) {
                        if (mainMenu(theMaze) == false) {
                            result = 4;
                            break;
                        }
                        System.out.println(theMaze);
                        System.out.println(q);
                        System.out.println(q.getAnswer());
                        playerAnswer = Console.getplayerInput();
                    }
                }
                if (result == 4)
                    break;

                if(playerAnswer.equals(cheat.toLowerCase())){
                    System.out.println(q.getAnswer());
                    theMaze.unlock(playerClick);
                    theMaze.move(playerClick);
                    steps++;
                }else if (playerAnswer.equals(q.getAnswer().toLowerCase())) {
                    theMaze.unlock(playerClick);
                    theMaze.move(playerClick);
                    steps++;
                }else {
                    theMaze.deleteCurrentRoomDoor(playerClick);
                }
            } else {
                theMaze.move(playerClick);
                steps++;
            }
        }
        Console.printWonOrLost(theMaze.isLastRoom());
    }

    /**
     * The function starts the game by printing the first page, then it takes the user's input and depending on the input,
     * it either starts a new game, loads a previous game, prints the help screen or exits the game
     */
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
                System.exit(0);
            default:
                System.out.println("WRONG INPUT, TRY AGAIN by entering a number between 1 and 4");
                break;
        }
    }

    /**
     * The function takes in a maze object and saves it to a file with the name of the file being the name of the maze
     * object
     *
     * @param theMaze The maze object that is to be saved.
     */
    public static void saveGame(final Maze theMaze) {
        try {
            FileOutputStream outputStream = new FileOutputStream(Console.getFileName() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            out.writeObject(theMaze);
            out.close();
            outputStream.close();
            System.out.println("Thank You! The Game Has Been Saved!!!");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * This function takes in a file name, and returns a maze object
     *
     * @return The maze object is being returned.
     */
    public static Maze loadGame() throws NullPointerException{
        Maze maze = null;
        File f = new File(Console.getFileName() + ".ser");
        try {
            FileInputStream grabFile = new FileInputStream(f);
            ObjectInputStream in = new ObjectInputStream(grabFile);
            maze = (Maze) in.readObject();
            in.close();
            grabFile.close();
        	
        }catch (IOException | ClassNotFoundException i) {
        	return loadGame();
        } 
        return maze;
    }

    /**
     * The main menu method takes a Maze object as an argument and returns a boolean
     *
     * @param theMaze The maze object that is being used in the game.
     * @return A boolean value.
     */
    public static boolean mainMenu(final Maze theMaze) {
        Console.printMenuScreen();
        final String menuChoice = Console.inputMenuScreen();
        switch (menuChoice) {
                case "1":
                    saveGame(theMaze);
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
