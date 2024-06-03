/*
 * TeamCAK TriviaMaze Game - Model Package
 * Fall 2023
 */

package src.model;


/**
 * This is the QuestionFactory Class. It creates objects of type Question to send over to the TriviaHelper class.
 *
 * @author Alex Thompson, Calvin Beardemphl, Koji Yoshiyama
 * @version 15/12/2023
 */
public class QuestionFactory {

    /**
     * Default Constructor for QuestionFactory
     */
    public QuestionFactory() {
    }


    /**
     * Makes a new Question object according to the type
     *
     * @param theType of Question
     * @return the new Question object
     */
    public Question makeQuestion(final String theType) {
        Question q = determineType(theType);
        q.prepare();

        return q;
    }

    /**
     * Helper method used to determine what
     * type of Question the newly created
     * Question should be.
     *
     * @param theType of Question to be made
     * @return a Question of the correct type
     */
    private Question determineType(final String theType) {
        Question q = null;

        if (theType.equalsIgnoreCase("MC")) {
            q = new MCQuestion();
        } else if (theType.equalsIgnoreCase("TF")) {
            q = new TFQuestion();
        } else if (theType.equalsIgnoreCase("SA")) {
            q = new SAQuestion();
        }
        return q;
    }


}
