package junit;

import model.Maze;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class MazeTest {

    /**
     * Should return a copy of the maze
     */
    @Test
    public void getMyDisplayMazeShouldReturnACopyOfTheMaze() {
        Maze maze = new Maze();
        char[][] mazeCopy = maze.getMyDisplayMaze();
        assertNotSame(mazeCopy, maze.getMyDisplayMaze());
    }

    /**
     * Should return the available doors in the current room
     */
    @Test
    public void getCurrentAvailableDoorsTest() {
        Maze maze = new Maze();
        assertEquals(new HashSet<>(Arrays.asList('S', 'E')), maze.getCurrentAvailableDoors());
    }

    /**
     * Should delete the door when the door is not locked
     */
    @Test
    public void deleteCurrentRoomDoorWhenTheDoorIsNotLockedTest() {
        Maze maze = new Maze();
        maze.deleteCurrentRoomDoor('S');
        assertFalse(maze.isCurrentRoomDoorOpen('S'));
    }

    /**
     * Should throw an exception when the door is locked
     */
    @Test
    public void deleteCurrentRoomDoorExceptionTest() {
        Maze maze = new Maze();
        maze.deleteCurrentRoomDoor('S');
        assertEquals(maze.getCurrentAvailableDoors().size(), 1);
    }

    /**
     * Should return true when the current row is less than size
     */
    @Test
    public void moveDownTest() {
        Maze maze = new Maze();
        maze.move('s');
        assertTrue(maze.moveDown());
    }

    /**
     * Should return false when the current row is greater than or equal to size
     */
    @Test
    public void anotherMoveDownTest() {
        Maze maze = new Maze(4, 4);
        maze.move('S');
        maze.move('S');
        maze.move('S');
        assertFalse(maze.moveDown());
    }

    /**
     * Should return a 2d array of chars with the same dimensions as the maze
     */
    @Test
    public void showMazeTest() {
        Maze maze = new Maze(4, 4);
        char[][] result = maze.showMaze(4, 4);
        assertEquals(4, result.length);
        assertEquals(4, result[0].length);
    }

    /**
     * Should return a 2d array of chars with all elements initialized to 'l'
     */
    @Test
    public void showMazeInitializedToLTest() {
        Maze maze = new Maze(4, 4);
        char[][] result = maze.showMaze(4, 4);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                Arrays.fill(result[j], 'L');
            }
        }
        assertEquals(4, result.length);
    }

    /**
     * Should throw an exception when the rows or cols are negative
     */
    @Test
    public void createTest() {
        try {
            new Maze(-1, -1);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("ERROR: MAZE DIMENSIONS ARE NOT EQUAL!!!!!", e.getMessage());
        }
    }

    /**
     * Should throw an exception when the rows and cols are not equal
     */
    @Test
    public void createNotEqualTest() {
        try {
            new Maze(3, 4);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("ERROR: MAZE DIMENSIONS ARE NOT EQUAL!!!!!", e.getMessage());
        }
    }
}