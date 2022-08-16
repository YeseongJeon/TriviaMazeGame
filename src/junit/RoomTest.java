package junit;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import model.Room;
import static org.junit.Assert.assertNotEquals;

public class RoomTest {
    Room myRoom = new Room(new char[]{'S', 'E'});

    @Test
    public void statusOfDoors() {
        assertNotEquals(myRoom.statusOfDoors(), null);
    }

    @Test
    public void deleteDoor() {
        myRoom.deleteDoor('S');
        assertNotEquals(myRoom.statusOfDoors(), null);
    }

    @Test
    public void doorLocked() {
        assertNotEquals(myRoom.doorLocked(), null);
    }

    @Test
    public void openDoor() {
        assertNotEquals(myRoom.openDoor(), null);
    }

    @Test
    public void verify() {
        assertNotEquals(myRoom.verify(), null );
    }

    @Test
    public void getAvailableDoors() {
        assertNotEquals(myRoom.getAvailableDoors(), null);
    }

    @Test
    public void isOpened() {
        assertNotEquals(myRoom.isOpened(), null);
    }

    @Test
    public void testToString() {
        assertNotEquals(myRoom.toString(), null);
    }
}