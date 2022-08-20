
package model;

import java.util.*;
import java.io.*;
/**
 * This class will contain all the logic towards the entrances to each room.
 * When the entrances are shot and when they can be opened.
 * When the entrance should be there and at what times that specific entrance should not be there.
 * @author Johnny Heredia III
 * @version 1.0 TCSS 360(Summer 2022)
 */
public class Room implements Serializable{
	private static final long serialVersionUID = 1l;
    /**
     * The Doors directions
     */
    private final Map<Character, Boolean> myDoors;

    /**
     * Constructor and initializer for the room
     * @param theDirections All possible direction values for the room
     */
    public Room(final char[] theDirections){
        Objects.requireNonNull(theDirections);
        myDoors = buildRoom(theDirections);
    }

    /**
     * Method to build the Map for the room
     * @param theDirections The directions of the doors
     * @return Map of the doors and if they are unlocked
     */
    private Map<Character, Boolean> buildRoom(final char[] theDirections){
        final Map<Character, Boolean> myMap = new HashMap<Character, Boolean>();
        for (final char ch : theDirections){
            myMap.put(ch, false);
        }
        return myMap;
    }

    /**
     * The current status of the doors
     * @return the doors and whether they are open or not.
     */
    public Map<Character, Boolean> statusOfDoors() {
        return myDoors;
    }

    /**
     * Method to delete a certain door
     * @param theDirection the door to be deleted
     */
    public void deleteDoor(final char theDirection){
        myDoors.remove(theDirection);
    }

    /**
     * Method will see if the door is locked
     * @return true if locked, flase if open.
     */
    public boolean doorLocked(){
        return myDoors.size() == 0;
    }

    /**
     * Method to open door
     * @param theDoor door to be opened
     */
    public void openDoor(final char theDoor){
        if(myDoors.containsKey(theDoor)){
            myDoors.put(theDoor, true);
        }
    }

    /**
     * Method to verify if there are any open doors that remain
     * @return boolean value if there is an open door.
     */
    public boolean verify(){
        boolean result = false;
        for(char theDirection : myDoors.keySet()){
            if(myDoors.get(theDirection) == true){
                result = true;
            }
        }
        return result;
    }

    /**
     * Method to retrieve any available doors
     * @return set with available doors
     */
    public Set<Character> getAvailableDoors(){
        return new HashSet<>(myDoors.keySet());
    }

    /**
     * Checks if a specific door is unlocked and open
     * @param theDirection the door to be checked
     * @return boolean value for if the door is opened
     */
    public boolean isOpened(final char theDirection){
        boolean result = false;
        if(myDoors.containsKey(theDirection)){
            result = myDoors.get(theDirection);
        }
        return result;
    }

    /**
     * Method to make room into string
     * @return String version of the room/doors
     */
    public String toString() {
        return  myDoors.toString();
    }
}
