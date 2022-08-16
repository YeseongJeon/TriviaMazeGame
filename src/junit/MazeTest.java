package junit;


import model.Maze;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;

import java.util.Random;

public class MazeTest {
    Random rand = new Random();
    int randInt = rand.nextInt(3, 7);
    Maze myMaze = new Maze(randInt, randInt);

    @Test
    public void move() {
        assertNotEquals(myMaze.move(), null);
    }

    @Test
    public void moveDown() {
        assertNotEquals(myMaze.moveDown(), null);
    }

    @Test
    public void moveRight() {
        assertNotEquals(myMaze.moveRight(), null);
    }

    @Test
    public void moveUp() {
        assertNotEquals(myMaze.moveUp(), null);
    }

    @Test
    public void moveLeft() {
        assertNotEquals(myMaze.moveLeft(), null);
    }

    @Test
    public void unlock() {
        assertNotEquals(myMaze.unlock(), null);
    }

    @Test
    public void isLastRoom() {
        assertNotEquals(myMaze.isLastRoom(), null);
    }

    @Test
    public void isCurrentRoomLocked() {
        assertNotEquals(myMaze.isCurrentRoomLocked(), null);
    }

    @Test
    public void isCurrentRoomDoorOpen() {
        assertNotEquals(myMaze.isCurrentRoomDoorOpen(), true);
    }

    @Test
    public void deleteCurrentRoomDoor() {
        assertNotEquals(myMaze.deleteCurrentRoomDoor(), null);
    }

    @Test
    public void getRowIndex() {
        assertNotEquals(myMaze.getRowIndex(), null);
    }

    @Test
    public void getColIndex() {
        assertNotEquals(myMaze.getColIndex(), null);
    }

    @Test
    public void getCurrentAvailableDoors() {
        assertNotEquals(myMaze.getCurrentAvailableDoors(), null);
    }

    @Test
    public void size() {
        assertNotEquals(myMaze.size(), null);
    }

    @Test
    public void getMyDisplayMaze() {
        assertNotEquals(myMaze.getMyDisplayMaze(), null);
    }

    @Test
    public void getMaze() {
        assertNotEquals(myMaze.getMaze(), null);
    }

    @Test
    public void testToString() {
        assertNotEquals(myMaze.toString(), null);
    }
}