/*
 * TeamCAK TriviaMaze Game - Tests Package
 * Fall 2023
 */

package src.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.model.Door;
import src.model.Room;
import src.model.TriviaHelper;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This class Unit tests all parts of the door class
 *
 * @author Calvin Beardemphl, Koji Yoshiyama, Alex Thompson
 * @version 15/12/2023
 */
class DoorTest {

    /**
     * Reference to the door object for tests
     */
    private Door myDoor;

    /**
     * Reference to the room object for door tests
     */
    private Room myRoom;

    /**
     * Reference to TriviaHelper for door tests
     */
    private TriviaHelper myTriviaHelper;

    /**
     * Used for testing console output
     */
    private final PrintStream myStandardOut = System.out;

    /**
     * used for testing console output
     */
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * This method sets up the proper things before each test is done
     */
    @BeforeEach
    void setUp() {
        myRoom = new Room();
        myTriviaHelper = new TriviaHelper();
        myDoor = new Door(myRoom, myTriviaHelper);

        //used for testing console output
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * This tests if the constructor properly inits the fields
     */
    @Test
    void constructor_InitializeFields(){
        assertNotNull(myDoor.getDoors(), "myDoors shouldn't be null");
        assertNotNull(myDoor.getDirection(), "myDirection shouldn't be null");
        //make myPcs public for testing
        assertNotNull(myDoor.myPcs, "mPcs shouldn't be null");
    }

    /**
     * This tests if the doors lock when player goes up
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    void updateDoors_ShouldLockDoorUP() throws IOException, ClassNotFoundException {
        // Setup initial conditions
        myRoom.updatePlayersLocation(1, 1);
        myDoor.doorLockedOrUnlocked("up"); // Set the direction to "up"

        //lock door above
        myDoor.updateDoors();
        //make sure right door is locked
        assertEquals(1, myDoor.getDoors()[1][1], "Door [1][1] should be locked");
    }

    /**
     * This tests if the doors lock when player goes dowm
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    void updateDoors_ShouldLockDoorDOWN() throws IOException, ClassNotFoundException {
        // Setup initial conditions
        myRoom.updatePlayersLocation(2, 3);
        myDoor.doorLockedOrUnlocked("down"); // Set the direction to "down"

        //lock door below
        myDoor.updateDoors();
        //make sure right door is locked
        assertEquals(1, myDoor.getDoors()[5][3], "Door [5][3] should be locked");
    }

    /**
     * This tests if the doors lock when player goes left
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    void updateDoors_ShouldLockDoorLEFT() throws IOException, ClassNotFoundException {
        // Setup initial conditions
        myRoom.updatePlayersLocation(3, 1);
        myDoor.doorLockedOrUnlocked("left"); // Set the direction to "left"

        //lock door to the left
        myDoor.updateDoors();
        //make sure right door is locked
        assertEquals(1, myDoor.getDoors()[6][0], "Door [6][0] should be locked");
    }

    /**
     * This tests if the doors lock when player goes right
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    void updateDoors_ShouldLockDoorRIGHT() throws IOException, ClassNotFoundException {
        // Setup initial conditions
        myRoom.updatePlayersLocation(1, 2);
        myDoor.doorLockedOrUnlocked("right"); // Set the direction to "right"

        //lock door to the right
        myDoor.updateDoors();
        //make sure right door is locked
        assertEquals(1, myDoor.getDoors()[2][2], "Door [2][2] should be locked");
    }

    /**
     * This test checks to see if the property changes are being fired correctly
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    void updateDoors_ShouldFirePropertyChange() throws IOException, ClassNotFoundException {
        List<String> receivedEvents = new ArrayList<>();

        //make a new listener
        PropertyChangeListener listener = evt -> receivedEvents.add(evt.getPropertyName());
        //add it to TriviaHelper
        myDoor.addPropertyChangeListener(listener);

        myDoor.doorLockedOrUnlocked("right"); // Set the direction to "right"
        //call method
        myDoor.updateDoors();

        assertTrue(receivedEvents.contains("doors"));

    }

    /**
     * This tests checks if the locked or unlocked method works
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    void doorLockedOrUnlocked_ShouldPrintToConsole() throws IOException, ClassNotFoundException {
        // Setup initial conditions
        myRoom.updatePlayersLocation(1, 3);
        myDoor.doorLockedOrUnlocked("left"); // Set the direction to "left"
        myDoor.updateDoors(); //lock door to the left

        myDoor.doorLockedOrUnlocked("left"); // try to move left again

        assertEquals("Cannot pass through a locked door", outputStreamCaptor.toString()
                .trim());

    }
//
//    @Test
//    void doorLockedOrUnlocked_PlayerAtTopRowTryingToMoveUp() throws IOException, ClassNotFoundException {
//        // Setup initial conditions
//        room.updatePlayersLocation(0, 1);
//        door.doorLockedOrUnlocked("up"); // try to move "up"
//
//        assertEquals("Cannot pass through a locked door", outputStreamCaptor.toString()
//                .trim());
//
//    }
//
//    @Test
//    void doorLockedOrUnlocked_PlayerAtBottomRowTryingToMoveDown() throws IOException, ClassNotFoundException {
//        // Setup initial conditions
//        room.updatePlayersLocation(4, 2);
//        door.doorLockedOrUnlocked("down"); // try to move "down"
//
//        assertEquals("Cannot pass through a locked door", outputStreamCaptor.toString()
//                .trim());
//
//    }
//
//    @Test
//    void doorLockedOrUnlocked_PlayerAtLeftMostColTryingToMoveLeft() throws IOException, ClassNotFoundException {
//        // Setup initial conditions
//        room.updatePlayersLocation(2, 0);
//        door.doorLockedOrUnlocked("left"); // try to move "left"
//
//        assertEquals("Cannot pass through a locked door", outputStreamCaptor.toString()
//                .trim());
//
//    }
//
//    @Test
//    void doorLockedOrUnlocked_PlayerAtRightMostColTryingToMoveRight() throws IOException, ClassNotFoundException {
//        // Setup initial conditions
//        room.updatePlayersLocation(2, 0);
//        door.doorLockedOrUnlocked("right"); // try to move "right"
//
//        assertEquals("Cannot pass through a locked door", outputStreamCaptor.toString()
//                .trim());
//
//    }

    /**
     * This tests to see if losing scenario is functional
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    void youLose_ShouldPrintToConsole() throws IOException, ClassNotFoundException {
        myRoom.updatePlayersLocation(1, 1);

        // lock all surrounding doors
        myDoor.doorLockedOrUnlocked("up"); // Set the direction to "up"
        myDoor.updateDoors(); //lock door above
        myDoor.doorLockedOrUnlocked("down"); // Set the direction to "down"
        myDoor.updateDoors(); //lock door below
        myDoor.doorLockedOrUnlocked("left"); // Set the direction to "left"
        myDoor.updateDoors(); //lock door to the left right
        myDoor.doorLockedOrUnlocked("right"); // Set the direction to "right"
        myDoor.updateDoors(); //lock door to the right


        assertEquals("You Lost", outputStreamCaptor.toString()
                .trim());
    }

    /**
     * This tests if PCL are added correctly
     */
    @Test
    void addPropertyChangeListener() {
        PropertyChangeListener listener = evt -> {
        }; // do nothing, just a placeholder

        myDoor.addPropertyChangeListener(listener);

        //make sure it was added
        assertEquals(1, myDoor.myPcs.getPropertyChangeListeners().length);
    }

    /**
     * This tests if PCL's are able being removed properly
     */
    @Test
    void removePropertyChangeListener() {
        PropertyChangeListener listener = evt -> {
        }; // do nothing, just a placeholder

        myDoor.addPropertyChangeListener(listener);

        //make sure it was added
        assertEquals(1, myDoor.myPcs.getPropertyChangeListeners().length);

        myDoor.removePropertyChangeListener(listener);

        //make sure it was deleted
        assertEquals(0, myDoor.myPcs.getPropertyChangeListeners().length);
    }
}