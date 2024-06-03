/*
 * TeamCAK TriviaMaze Game - Tests Package
 * Fall 2023
 */

package src.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.model.MCQuestion;
import src.model.Question;

/**
 * This is the class that tests the Question class
 *
 * @author Calvin Beardemphl, Koji Yoshiyama, Alex Thompson
 * @version 15/12/2023
 */
class QuestionTest {

    /**
     * Reference to the question object for testing
     */
    Question myQ;

    /**
     * This is the set-up needed before each test
     */
    @BeforeEach
    void setUp() {
        myQ = new MCQuestion();
        myQ.getTrivia("MC");
    }

    /**
     * This is a test for the getQuestion() method
     */
    @Test
    void getQuestion() {
        assertNotNull(myQ.getQuestion());
    }

    /**
     * This is a test for the getAnswers() method
     */
    @Test
    void getAnswers() {
        assertNotNull(myQ.getAnswers());
    }

    /**
     * This is a test for the getCorrect() method
     */
    @Test
    void getCorrect() {
        assertNotNull(myQ.getCorrect());
    }

}