package junit;

import model.Room;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RoomTest {

    /**
     * Should return a set of available doors
     */
    @Test
    public void getAvailableDoorsTest() {
        Room room = new Room(new char[]{'N', 'S', 'E', 'W'});
        Map<Character, Boolean> expected = new HashMap<>();
        expected.put('N', false);
        expected.put('S', false);
        expected.put('E', false);
        expected.put('W', false);
        assertEquals(expected, room.statusOfDoors());
    }

    /**
     * Should open the door when the door is available
     */
    @Test
    public void openDoorWhenTheDoorIsAvailable() {
        Room room = new Room(new char[]{'N', 'S', 'E', 'W'});
        room.openDoor('N');
        Map<Character, Boolean> expected = new HashMap<>();
        expected.put('N', true);
        expected.put('S', false);
        expected.put('E', false);
        expected.put('W', false);
        assertEquals(expected, room.statusOfDoors());
    }

    /**
     * Should not open the door when the door is not available
     */
    @Test
    public void openDoorWhenTheDoorIsNotAvailable() {
        Room room = new Room(new char[]{'N', 'S', 'E', 'W'});
        room.openDoor('X');
        assertEquals(4, room.statusOfDoors().size());
    }

    /**
     * Should return a map of the doors
     */
    @Test
    public void statusOfDoorsTest() {
        Room room = new Room(new char[]{'N', 'S', 'E', 'W'});
        Map<Character, Boolean> expected = new HashMap<>();
        expected.put('N', false);
        expected.put('S', false);
        expected.put('E', false);
        expected.put('W', false);
        assertEquals(expected, room.statusOfDoors());
    }

    /**
     * Should return a map with the same size as the directions
     */
    @Test
    public void buildRoomMapSameSizeaAsDirectionsTest() {
        final char[] directions = {'N', 'S', 'E', 'W'};
        final Room room = new Room(directions);
        assertEquals(4, room.statusOfDoors().size());
    }

    /**
     * Should return a map with all keys in the directions
     */
    @Test
    public void buildRoomReturnMapAllKeysTest() {
        final char[] directions = {'N', 'S', 'E', 'W'};
        final Room room = new Room(directions);
        final Map<Character, Boolean> expected = new HashMap<>();
        expected.put('N', false);
        expected.put('S', false);
        expected.put('E', false);
        expected.put('W', false);
        assertEquals(expected, room.statusOfDoors());
    }
}