/*
 * TeamCAK TriviaMaze Game - Tests Package
 * Fall 2023
 */

package src.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.model.Room;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is meant to test the Room class
 *
 * @author Calvin Beardemphl, Koji Yoshiyama, Alex Thompson
 * @version 15/12/2023
 */
class RoomTest {

    /**
     * This is a reference to the room object for testing
     */
    Room myRoom;

    /**
     * used for testing console output
     */
    private final PrintStream myStandardOut = System.out;

    /**
     * used for testing console output
     */
    private final ByteArrayOutputStream myOutputStreamCaptor = new ByteArrayOutputStream();

    /**
     * This is the setup needed before each test is run
     */
    @BeforeEach
    void setup(){
        myRoom = new Room();
        //used for testing console output
        System.setOut(new PrintStream(myOutputStreamCaptor));
    }

    /**
     * This tests to see if the constructor properly inits the fields
     */
    @Test
    void constructor_InitializeFields(){
        //make myPcs public for testing
        assertNotNull(myRoom.myPcs, "MyPcs should not be null");
        //make myNumRows public for testing
        assertEquals(5, myRoom.myNumRows, "Row should be 5");
        //make myNumCols public for testing
        assertEquals(4, myRoom.myNumCols, "Columns should be 4");
        assertEquals("0, 0", myRoom.getLocation(), "Players Location should be at 0, 0");
        assertEquals(0, myRoom.getPlayersRow(), "Players row should be 0");
        assertEquals(0, myRoom.getPlayersCol(), "Players col should be 0");

        //also make myPlayersRow, and myPlayersCol public for testing
    }

    /**
     * This test sees if the winning condition is functional
     */
    @Test
    void updatePlayersLocation_Win() {
        myRoom.updatePlayersLocation(4, 3);

        assertEquals("You Won", myOutputStreamCaptor.toString()
                .trim());
    }

    /**
     * This test ensures tha the firePropertyChange() is functional
     */
    @Test
    void updatePlayersLocationShouldFirePropertyChange() {
        List<String> receivedEvents = new ArrayList<>();

        //make a new listener
        PropertyChangeListener listener = evt -> receivedEvents.add(evt.getPropertyName());
        //add it to TriviaHelper
        myRoom.addPropertyChangeListener(listener);
        //call method
        myRoom.updatePlayersLocation(2, 1);

        assertTrue(receivedEvents.contains("gameBoard"));
    }

    /**
     * This test sees if the location is retrieved properly
     */
    @Test
    void getLocation_InitialWithDirectChanges() {
        //directly change players col and row
        // to get into else statement in method
        myRoom.myPlayersCol = 2;
        myRoom.myPlayersRow = 2;

        assertEquals("0, 0", myRoom.getLocation());
    }

    /**
     * This is another test to see if the getLocation() method is functional
     */
    @Test
    void getLocation_ElseStatement() {
        myRoom.updatePlayersLocation(4, 1);

        //directly change players col and row
        // to get into else statement in method
        myRoom.myPlayersCol = 0;
        myRoom.myPlayersRow = 0;

        assertEquals("4, 1", myRoom.getLocation());
    }

    /**
     * This method is the final test for getLocation()
     */
    @Test
    void getLocation_FinalReturnStatement(){
        myRoom.myPlayersLocation = new int[15][15];
        myRoom.myPlayersLocation[14][14] = 1;

        assertEquals("Player not found", myRoom.getLocation());

    }

    /**
     * This is a test to see if the getter method for the player row works
     */
    @Test
    void getPlayersRow() {
    }

    /**
     * This is a test to see if the getter method for the player column works
     */
    @Test
    void getPlayersCol() {
    }

    /**
     * This is a test to see if the getter method for the player's location works
     */
    @Test
    void getPlayersLocation() {
    }

    /**
     * This tests if a PCL can be properly added
     */
    @Test
    void addPropertyChangeListener() {
        PropertyChangeListener listener = evt -> {
        }; // do nothing, just a placeholder

        myRoom.addPropertyChangeListener(listener);

        //make sure it was added
        assertEquals(1, myRoom.myPcs.getPropertyChangeListeners().length);
    }

    /**
     * This tests to see if a PCL can be removed properly
     */
    @Test
    void removePropertyChangeListener() {
        PropertyChangeListener listener = evt -> {
        }; // do nothing, just a placeholder

        myRoom.addPropertyChangeListener(listener);

        //make sure it was added
        assertEquals(1, myRoom.myPcs.getPropertyChangeListeners().length);

        myRoom.removePropertyChangeListener(listener);

        //make sure it was deleted
        assertEquals(0, myRoom.myPcs.getPropertyChangeListeners().length);
    }
}