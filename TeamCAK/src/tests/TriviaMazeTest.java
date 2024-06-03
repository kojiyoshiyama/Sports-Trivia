/*
 * TeamCAK TriviaMaze Game - Tests Package
 * Fall 2023
 */

package src.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.model.TriviaMaze;
import src.view.GameBoard;
import src.view.ScoreBoard;
import src.view.TriviaBoard;
import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the tester class for the triviaMaze
 */
class TriviaMazeTest {

    /**
     * This is a reference to TriviaMaze for testing purposes
     */
    TriviaMaze myMaze;

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
        myMaze = new TriviaMaze();

        //used for testing console output
        System.setOut(new PrintStream(myOutputStreamCaptor));
    }

    /**
     * this tests to ensure that all fields are initialized properly
     */
    @Test
    void constructor_InitializeFields(){
        assertNotNull(myMaze.getTriviaHelper(), "myTriviaHelper shouldn't be null");
        assertNotNull(myMaze.getRoom(), "myRoom shouldn't be null");
        assertNotNull(myMaze.getDoor(), "myDoor shouldn't be null");
        assertNotNull(myMaze.getStatistics(), "myStatistics shouldn't be null");

        //make mySQLite public for testing
        assertNotNull(myMaze.mySQLite, "mySQLite shouldn't be null");

    }

    /**
     * This tests to make sure PCL's are added correctly to room
     */
    @Test
    void addPCLToRoom() {
        GameBoard gameBoard = new GameBoard();

        myMaze.addPCLToRoom(gameBoard);

        assertEquals(1, myMaze.getRoom().myPcs.getPropertyChangeListeners().length);
    }

    /**
     * This tests to make sure PCL's are added correctly to door
     */
    @Test
    void addPCLToDoor() {
        GameBoard gameBoard = new GameBoard();

        myMaze.addPCLToDoor(gameBoard);

        assertEquals(1, myMaze.getDoor().myPcs.getPropertyChangeListeners().length);
    }

    /**
     * This tests to make sure PCL's are added correctly to TriviaHelper
     */
    @Test
    void addPCLToTriviaHelper() {
        TriviaBoard triviaBoard = new TriviaBoard();

        myMaze.addPCLToTriviaHelper(triviaBoard);

        assertEquals(1, myMaze.getTriviaHelper().myPcs.getPropertyChangeListeners().length);
    }

    /**
     * This tests to make sure PCL's are added correctly to Statistics
     */
    @Test
    void addPCLToStatistics() {
        ScoreBoard scoreBoard = new ScoreBoard(new Timer(1000, evt -> {
        })); // do nothing, just a placeholder

        myMaze.addPCLToStatistics(scoreBoard);

        assertEquals(1, myMaze.getStatistics().myPcs.getPropertyChangeListeners().length);
    }

    /**
     * This tests to make sure nothing happens when load game is called with no saved data
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    void loadGameState_NoFile() throws IOException, ClassNotFoundException {
        // delete save file if there already is one
        TriviaMaze.loadGameState();
        assertEquals("No saved game file found", myOutputStreamCaptor.toString()
                .trim());
    }

    /**
     * This tests to make sure the game is saved properly
     *
     * @throws IOException
     */
    @Test
    void saveGameState() throws IOException {
        myMaze.saveGameState();
        assertEquals("game state saved", myOutputStreamCaptor.toString()
                .trim());
    }

    /**
     * This tests to make sure the game is loaded properly
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    void loadGameState() throws IOException, ClassNotFoundException {
        //make sure there is a save file
        myMaze.saveGameState();
        TriviaMaze.loadGameState();
        assertEquals("Game successfully loaded in", myOutputStreamCaptor.toString()
                .trim());
    }

    /**
     * This makes sure all the firePropertyChange() methods fire properly
     */
    @Test
    void fireLoadedPropertyChanges() {
        List<String> receivedEvents = new ArrayList<>();

        //make a new listener
        PropertyChangeListener listener = evt -> receivedEvents.add(evt.getPropertyName());

        // add listener to objects
        myMaze.getDoor().addPropertyChangeListener(listener);
        myMaze.getRoom().addPropertyChangeListener(listener);
        myMaze.getTriviaHelper().addPropertyChangeListener(listener);
        myMaze.getStatistics().addPropertyChangeListener(listener);

        //call method
        myMaze.fireLoadedPropertyChanges();

        assertTrue(receivedEvents.contains("doors"), "Doors should have been fired");
        assertTrue(receivedEvents.contains("gameBoard"), "GameBoard should have been fired");
        assertTrue(receivedEvents.contains("score"), "Score should have been fired");
        assertTrue(receivedEvents.contains("correct"), "Correct should have been fired");
        assertTrue(receivedEvents.contains("total"), "Total should have been fired");
        assertTrue(receivedEvents.contains("new game"), "New game should have been fired");
    }
}