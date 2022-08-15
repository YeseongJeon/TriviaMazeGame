package model;

import java.io.Serializable;
import java.util.*;
/*
 * This will be an extremely Trivial part of our view.Trivia Maze Game.
 * This class will contain a various number of methods.
 * Some of them are a method to first create the maze. 
 * A method to print out that maze that we have just created.
 * A method that allows for the user to change locations. 
 * Also, a method that will determine the current state of the user. 
 * For example, if they have or have not yet completed the game. 
 * These are some of the main methods that shall be within this class.
 */


public class Maze implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * Room array
     */
    private Room[][] myCurrentRooms;

    /**
     * Position of the current row.
     */
    private int myCurrentRow;

    /**
     * position of the current column
     */
    private int myCurrentCol;

    /**
     * this 2D array will show the maze
     */
    private final char[][] myDisplayMaze;

    /**
     * Default row size for the Maze
     */
    private static final int myRows = 4;

    /**
     * Default column size for the Maze
     */
    private static final int myCol = 4;


    public Maze() {
        this(myRows, myCol);
    }

    /**
     * This is the constructor method will make the maze board by passing in the row & columns they have to be same dimensions
     * @param theRows
     * @param theCols
     */
    public Maze(final int theRows, final int theCols) {

        if (theRows < 0 || theCols < 0 || theRows != theCols) {
            throw new IllegalArgumentException("Error: The maze dimensions are supposed to be equal (e.g; 4x4");
        }
        myCurrentRooms = create(theRows, theCols);
        myCurrentRow = 0;
        myCurrentCol = 0;
        myDisplayMaze = showMaze(theRows, theCols);
    }

    /**
     * This method is responsible for maximizing the maze  depending on the current location there are different options
     * @return 2D array
     */
    private Room[][] create(final int theRow, final int theCol) {

        final Room[][] rooms = new Room[theRow][theCol];
        for (int i = 0; i < theRow; i++) {
            for (int j = 0; j < theRow; j++) {
                if (i == 0) {
                    if (j == 0) {
                        rooms[i][j] = new Room(new char[] {'S', 'D'});
                    } else if (j < theRow - 1) {
                        rooms[i][j] = new Room(new char[] { 'A', 'S', 'D' });
                    } else {
                        rooms[i][j] = new Room(new char[] { 'A', 'S' });
                    }
                } else if (j == 0 && i > 0) {
                    if (i < theRow - 1) {
                        rooms[i][j] = new Room(new char[] { 'W', 'D', 'S' });
                    } else {
                        rooms[i][j] = new Room(new char[] { 'W', 'D' });
                    }
                } else if (j > 0 && i == theRow - 1) {
                    if (j < theRow - 1) {
                        rooms[i][j] = new Room(new char[] { 'A', 'W', 'D' });
                    } else {
                        rooms[i][j] = new Room(new char[] { 'W', 'A' });
                    }
                } else if (j == theRow - 1 && (i > 0 && i < theRow - 1)) {
                    rooms[i][j] = new Room(new char[] { 'W', 'S', 'A' });
                } else {
                    rooms[i][j] = new Room(new char[] { 'W', 'S', 'D', 'A' });
                }
            }
        }
        rooms[theRow - 1][theRow - 1] = new Room(new char[] {});
        return rooms;
    }

    /**
     * This method is to show the maze after created
     *
     * @param theRow row size of the maze
     * @param theCol column size of the maze
     * @return Array to display the maze
     */
    private char[][] showMaze(int theRow, int theCol) {

        final char[][] result = new char[theRow][theCol];
        for (char[] arr : result) {
            Arrays.fill(arr, 'X');
        }
        result[0][0] = '*';
        return result;
    }


    public void move(final char theDirection) {

        char myDir = Character.toUpperCase(theDirection);
        if (myDir == 'S' && moveDown()) {
            myCurrentRow++;
        } else if (myDir == 'D' && moveRight()) {
            myCurrentCol++;
        } else if (myDir == 'A' && moveLeft()) {
            myCurrentCol--;
        } else if (myDir == 'W' && moveUp()) {
            myCurrentRow--;
        } else {
            throw new IllegalArgumentException("Error: Unable to move in this direction.");
        }
        myDisplayMaze[myCurrentRow][myCurrentCol] = '*';
    }


    public boolean moveDown() {
        return myCurrentRow + 1 < size();
    }

    public boolean moveRight() {
        return myCurrentCol + 1 < size();
    }


    public boolean moveUp() {
        return myCurrentRow - 1 > -1;
    }

    public boolean moveLeft() {
        return myCurrentCol - 1 > -1;
    }


    public void unlock(char theChar) {
        theChar = Character.toUpperCase(theChar);
        myCurrentRooms[row][col].openDoor(theChar);

        if (theChar == 'A') {
            myCurrentRooms[row][col - 1].openDoor('D');
        } else if (theChar == 'S') {
            myCurrentRooms[row + 1][col].openDoor('W');
        } else if (theChar == 'D') {
            myCurrentRooms[row][col + 1].openDoor('A');
        } else if (theChar == 'W') {
            myCurrentRooms[row - 1][col].openDoor('S');
        }
    }


    public boolean isLastRoom() {
        final int len = size();
        return row == len - 1 && col == len - 1;
    }


    public boolean isCurrentRoomLocked() {
        return myCurrentRooms[row][col].doorStatus();
    }


    public boolean isCurrentRoomDoorOpen(char ch) {
        return myCurrentRooms[row][col].unlocked(ch);
    }


    public void deleteCurrentRoomDoor(char theChar) {
        myCurrentRooms[row][col].deleteDoor(theChar);

        if (theChar == 'A') {
            myCurrentRooms[row][col - 1].deleteDoor('D');
        } else if (theChar == 'S') {
            myCurrentRooms[row + 1][col].deleteDoor('W');
        } else if (theChar == 'D') {
            myCurrentRooms[row][col + 1].deleteDoor('A');
        } else if (theChar == 'W') {
            myCurrentRooms[row - 1][col].deleteDoor('S');
        }
    }

    public int getRowIndex() {
        return myCurrentRow;
    }


    public int getColIndex() {
        return myCurrentCol;
    }

    public Set<Character> getCurrentAvailableDoors() {
        return myCurrentRooms[row][col].getAvailableDoors();
    }

    public int size() {
        return myCurrentRooms.length;
    }

    public char[][] getMyDisplayMaze() {
        return Arrays.copyOf(myDisplayMaze, myDisplayMaze.length);
    }

    public Room[][] getMaze() {
        return Arrays.copyOf(myCurrentRooms, myCurrentRooms.length);
    }

    public String toString() {
        final StringBuilder str = new StringBuilder();
        for (char[] ch : myDisplayMaze) {
            str.append(Arrays.toString(ch)).append("\n");
        }
        return str.toString();
    }
}
