/*
 * TeamCAK TriviaMaze Game - Tests Package
 * Fall 2023
 */

package src.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.model.TriviaHelper;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the tester for the TriviaHelper class
 *
 * @author Koji Yoshiyama, Calvin Beardemphl, Alex Thompson
 * @version 15/12/2023
 */
class TriviaHelperTest {

    /**
     * This is the reference to the TriviaHelper object for testing
     */
    TriviaHelper myTh;

    /**
     * This is the setup needed before each test case
     */
    @BeforeEach
    void setup(){
        myTh = new TriviaHelper();
    }

    /**
     * This tests to see if firePropertyChange() is working for updateQuestion()
     */
    @Test
    void updateQuestionShouldFirePropertyChange() {
        List<String> receivedEvents = new ArrayList<>();

        //make a new listener
        PropertyChangeListener listener = evt -> receivedEvents.add(evt.getPropertyName());
        //add it to TriviaHelper
        myTh.addPropertyChangeListener(listener);
        //call method
        myTh.updateQuestion();

        assertTrue(receivedEvents.contains("question"));
    }

    /**
     * this tests to make sure randomQ returns a valid type
     */
    @Test
    void randomQTypeShouldReturnValidType() {
        List<String> validTypes = new ArrayList<>();
        validTypes.add("MC");
        validTypes.add("TF");
        validTypes.add("SA");

        for (int i = 0; i < 100; i++) {
            String randomType = myTh.randomQType();
            assertTrue(validTypes.contains(randomType), "The type is incorrect");
        }

    }

    /**
     * This tests to make sure PCL's are added properly
     */
    @Test
    void addPropertyChangeListener() {
        PropertyChangeListener listener = evt -> {
        }; // do nothing, just a placeholder

        myTh.addPropertyChangeListener(listener);

        //make sure it was added
        assertEquals(1, myTh.myPcs.getPropertyChangeListeners().length);
        //make myPcs public for testing
    }

    /**
     * This tests to make sure PCL's are removed properly.
     */
    @Test
    void removePropertyChangeListener() {
        PropertyChangeListener listener = evt -> {
        }; // do nothing, just a placeholder

        myTh.addPropertyChangeListener(listener);

        //make sure it was added
        assertEquals(1, myTh.myPcs.getPropertyChangeListeners().length);

        myTh.removePropertyChangeListener(listener);

        //make sure it was deleted
        assertEquals(0, myTh.myPcs.getPropertyChangeListeners().length);
    }

}