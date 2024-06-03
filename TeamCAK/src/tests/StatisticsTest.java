/*
 * TeamCAK TriviaMaze Game - Tests Package
 * Fall 2023
 */

package src.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.model.Statistics;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is a tester for the statistics class
 *
 * @author Calvin Beardemphl, Koji Yoshiyama, Alex Thompson
 * @version 15/12/2023
 */
class StatisticsTest {

    /**
     * Reference to the statistics class for testing
     */
    Statistics myStats;

    /**
     * reference to a random object used for testing
     */
    Random myRand;

    /**
     * This is the setup needed before each test
     */
    @BeforeEach
    void setup(){
        myStats = new Statistics();
        myRand = new Random();
    }

    /**
     * This tests if the score is 0 when first initialized
     */
    @Test
    void getCurrentScore_InitialZero() {
        assertEquals(0, myStats.getCurrentScore(),
                "The score should be 0 at first");
    }

    /**
     * This is a tester for the score updating using a random generator
     */
    @Test
    void getCurrentScore_Random(){
        int counter = 0;
        int num;

        //randomly add values to score in Statistics
        //and to counter to make sure they are the same
        for(int i = 0; i < 20; i++){
            num = myRand.nextInt(1000);
            myStats.updateScore(num);
            counter += num;
        }

        assertEquals(counter, myStats.getCurrentScore(),
                "The current score should be the same as counter");

    }

    /**
     * This tests to see if the firePropertyChange() is working properly for score
     */
    @Test
    void updateScoreShouldFirePropertyChange() {
        List<String> receivedEvents = new ArrayList<>();

        //make a new listener
        PropertyChangeListener listener = evt -> receivedEvents.add(evt.getPropertyName());
        //add it to TriviaHelper
        myStats.addPropertyChangeListener(listener);
        //call method
        myStats.updateScore(100);

        assertTrue(receivedEvents.contains("score"));
    }

    /**
     * This tests to make sure the number of correct questions is 0 at first
     */
    @Test
    void getMyCorrectQuestions_InitialZero() {
        assertEquals(0, myStats.getMyCorrectQuestions(),
                "The number of correct questions should be 0 at first");
    }

    /**
     * This tests uses random to test if getCorrectQuestions() is functional
     */
    @Test
    void getMyCorrectQuestions_Random(){
        int num = myRand.nextInt(1000);

        //increment the number of correct questions randomly
        //i initialized outside of loop to use in assertEquals
        int i = 0;
        for(; i < num; i++){
            myStats.updateCorrectQuestions();
        }

        assertEquals(i, myStats.getMyCorrectQuestions(),
                "The number of correct questions should be the same as i");
    }

    /**
     * this tests to see if firePropertyChange() is working for # of correct questions
     */
    @Test
    void updateCorrectQuestionsShouldFirePropertyChange() {
        List<String> receivedEvents = new ArrayList<>();

        //make a new listener
        PropertyChangeListener listener = evt -> receivedEvents.add(evt.getPropertyName());
        //add it to TriviaHelper
        myStats.addPropertyChangeListener(listener);
        //call method
        myStats.updateCorrectQuestions();

        assertTrue(receivedEvents.contains("correct"));
    }

    /**
     * This tests to see if the total questions starts at 0 when first initialized
     */
    @Test
    void getMyTotalQuestions_InitialZero() {
        assertEquals(0, myStats.getMyTotalQuestions(),
                "The number of total questions should be 0 at first");
    }

    /**
     * This uses a random generator to test functionality for getTotalQuestions()
     */
    @Test
    void getMyTotalQuestions_Random(){
        int num = myRand.nextInt(1000);

        //increment the number of total questions randomly
        //i initialized outside of loop to use in assertEquals
        int i = 0;
        for(; i < num; i++){
            myStats.updateTotalQuestions();
        }

        assertEquals(i, myStats.getMyTotalQuestions(),
                "The number of Total questions should be the same as i");
    }

    /**
     * This tests if firePropertyChange() works for total questions
     */
    @Test
    void updateTotalQuestionsShouldFirePropertyChange() {
        List<String> receivedEvents = new ArrayList<>();

        //make a new listener
        PropertyChangeListener listener = evt -> receivedEvents.add(evt.getPropertyName());
        //add it to TriviaHelper
        myStats.addPropertyChangeListener(listener);
        //call method
        myStats.updateTotalQuestions();

        assertTrue(receivedEvents.contains("total"));
    }

    /**
     * This tests to see if PCL's are added correctly
     */
    @Test
    void addPropertyChangeListener() {
        PropertyChangeListener listener = evt -> {
        }; // do nothing, just a placeholder

        myStats.addPropertyChangeListener(listener);

        //make sure it was added
        assertEquals(1, myStats.myPcs.getPropertyChangeListeners().length);
        //make myPcs public for testing
    }

    /**
     * This tests to see if PCL's are removed properly
     */
    @Test
    void removePropertyChangeListener() {
        PropertyChangeListener listener = evt -> {
        }; // do nothing, just a placeholder

        myStats.addPropertyChangeListener(listener);

        //make sure it was added
        assertEquals(1, myStats.myPcs.getPropertyChangeListeners().length);

        myStats.removePropertyChangeListener(listener);

        //make sure it was deleted
        assertEquals(0, myStats.myPcs.getPropertyChangeListeners().length);
    }
}