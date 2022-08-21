package model;

import java.io.Serializable;
import java.util.*;
/*
 * This will contain many of the methods related to the Maze
 */


public class Maze implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Room array
     */
    private Room[][] myCurrentRooms;

    /**
     * Position of the current row and the column.
     */
    private int myCurrentRow, myCurrentCol;
    /**
     * Default sizes for row and column
     */
    private static final int myRows = 4, myCol = 4;

    /**
     * this 2D array will show the maze
     */
    private final char[][] myDisplayMaze;

    public Maze() {
        this(myRows, myCol);
    }

    /**
     * This is the constructor method will make the maze board by passing in the row & columns they have to be same dimensions
     *
     * @param theRows
     * @param theCols
     */
    public Maze(final int theRows, final int theCols) {

        if (theRows < 0 || theCols < 0 || theRows != theCols) {
            throw new IllegalArgumentException("ERROR: MAZE DIMENSIONS ARE NOT EQUAL!!!!!");
        }
        myCurrentRooms = create(theRows, theCols);
        myCurrentRow = 0;
        myCurrentCol = 0;
        myDisplayMaze = showMaze(theRows, theCols);
    }

    /**
     * This method is responsible for maximizing the maze  depending on the current location there are different options
     *
     * @return 2D array
     */
    private Room[][] create(final int theRow, final int theCol) {

        final Room[][] rooms = new Room[theRow][theCol];
        for (int i = 0; i < theRow; i++) {
            for (int j = 0; j < theRow; j++) {
                if (i == 0) {
                    if (j == 0) {
                        rooms[i][j] = new Room(new char[]{'S', 'E'});
                    } else if (j < theRow - 1) {
                        rooms[i][j] = new Room(new char[]{'W', 'S', 'E'});
                    } else {
                        rooms[i][j] = new Room(new char[]{'W', 'S'});
                    }
                } else if (j == 0 && i > 0) {
                    if (i < theRow - 1) {
                        rooms[i][j] = new Room(new char[]{'N', 'E', 'S'});
                    } else {
                        rooms[i][j] = new Room(new char[]{'N', 'E'});
                    }
                } else if (j > 0 && i == theRow - 1) {
                    if (j < theRow - 1) {
                        rooms[i][j] = new Room(new char[]{'W', 'N', 'E'});
                    } else {
                        rooms[i][j] = new Room(new char[]{'N', 'W'});
                    }
                } else if (j == theRow - 1 && (i > 0 && i < theRow - 1)) {
                    rooms[i][j] = new Room(new char[]{'N', 'S', 'W'});
                } else {
                    rooms[i][j] = new Room(new char[]{'N', 'S', 'E', 'W'});
                }
            }
        }
        rooms[theRow - 1][theRow - 1] = new Room(new char[]{});
        return rooms;
    }

    /**
     * This method is to show the maze after created
     *
     * @param theRow row size of the maze
     * @param theCol column size of the maze
     * @return Array to display the maze
     */
    public char[][] showMaze(final int theRow, final int theCol) {

        final char[][] result = new char[theRow][theCol];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                Arrays.fill(result[j], 'L');
            }
        }
        result[0][0] = '*';
        return result;
    }


    public void move(final char theDirection) {

        char myDir = Character.toUpperCase(theDirection);
        myDisplayMaze[myCurrentRow][myCurrentCol] = 'O';
        if (myDir == 'S' && moveDown()) {
            myCurrentRow++;
        } else if (myDir == 'E' && moveRight()) {
            myCurrentCol++;
        } else if (myDir == 'W' && moveLeft()) {
            myCurrentCol--;
        } else if (myDir == 'N' && moveUp()) {
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
        myCurrentRooms[myCurrentRow][myCurrentCol].openDoor(theChar);

        switch (theChar) {
            case 'W':
                myCurrentRooms[myCurrentRow][myCurrentCol - 1].openDoor('E');
                break;
            case 'S':
                myCurrentRooms[myCurrentRow + 1][myCurrentCol].openDoor('N');
                break;
            case 'E':
                myCurrentRooms[myCurrentRow][myCurrentCol + 1].openDoor('W');
                break;
            case 'N':
                myCurrentRooms[myCurrentRow - 1][myCurrentCol].openDoor('S');
                break;
            default:
                break;

        }
    }

    public boolean isLastRoom() {
        final int len = size();
        return myCurrentRow == len - 1 && myCurrentCol == len - 1;
    }


    public boolean isCurrentRoomLocked() {
        return myCurrentRooms[myCurrentRow][myCurrentCol].doorLocked();
    }


    public boolean isCurrentRoomDoorOpen(final char ch) {
        return myCurrentRooms[myCurrentRow][myCurrentCol].isOpened(ch);
    }


    public void deleteCurrentRoomDoor(final char theChar) {
        myCurrentRooms[myCurrentRow][myCurrentCol].deleteDoor(theChar);

        if (theChar == 'W') {
            myCurrentRooms[myCurrentRow][myCurrentCol - 1].deleteDoor('E');
        } else if (theChar == 'S') {
            myCurrentRooms[myCurrentRow + 1][myCurrentCol].deleteDoor('N');
        } else if (theChar == 'E') {
            myCurrentRooms[myCurrentRow][myCurrentCol + 1].deleteDoor('W');
        } else if (theChar == 'N') {
            myCurrentRooms[myCurrentRow - 1][myCurrentCol].deleteDoor('S');
        }
    }

    public int getRowIndex() {
        return myCurrentRow;
    }


    public int getColIndex() {
        return myCurrentCol;
    }

    public Set<Character> getCurrentAvailableDoors() {
        return myCurrentRooms[myCurrentRow][myCurrentCol].getAvailableDoors();
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



