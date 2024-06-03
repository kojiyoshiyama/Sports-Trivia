/*
 * TeamCAK TriviaMaze Game - Tests Package
 * Fall 2023
 */

package src.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.model.MCQuestion;
import src.model.TFQuestion;
import src.model.SAQuestion;
import src.model.Question;
import src.model.QuestionFactory;

/**
 * This class is the Unit testing class for QuestionFactory
 *
 * @author Calvin Beardemphl, Koji Yoshiyama, Alex Thompson
 * @version 15/12/2023
 */
class QuestionFactoryTest {

    /**
     * This is a reference to the QuestionFactory object for testing
     */
    private QuestionFactory myQf;

    /**
     * These are the set-up conditions that are met before each test is run
     */
    @BeforeEach
    void setUp() {
        myQf = new QuestionFactory();
    }

    /**
     * This tests functionality of making a MCQuestion object
     */
    @Test
    void makeQuestionMC() {
        Question newQ = myQf.makeQuestion("MC");

        assertTrue(newQ instanceof MCQuestion);
    }

    /**
     * This tests functionality of making a TFQuestion object
     */
    @Test
    void makeQuestionTF() {
        Question newQ = myQf.makeQuestion("tf");

        assertTrue(newQ instanceof TFQuestion);
    }

    /**
     * This tests functionality of making a SAQuestion object
     */
    @Test
    void makeQuestionSA() {
        Question newQ = myQf.makeQuestion("SA");

        assertTrue(newQ instanceof SAQuestion);
    }
}