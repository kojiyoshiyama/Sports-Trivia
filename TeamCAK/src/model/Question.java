/*
 * TeamCAK TriviaMaze Game - Model Package
 * Fall 2023
 */

package src.model;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the Question Class. This acts as an abstract parent class for MCQuestion, SAQuestion, and TFQuestion.
 *
 * @author Alex Thompson, Calvin Beardemphl, Koji Yoshiyama
 * @version 15/12/2023
 */

public abstract class Question {

    /**
     * This Question objects question.
     */
    private transient String myQuestion;

    /**
     * This Question objects answer list.
     */
    private transient List myAnswers = new ArrayList<String>();

    /**
     * This Question objects correct answer.
     */
    private transient String myCorrect;

    /**
     * This is a reference to the SQLite object.
     */
    private transient SQLite myDatabase;

    /**
     * Default constructor for Question objects.
     */
    public Question(){
        myDatabase = SQLite.getInstance();
    }

    /**
     * Used to create questions.
     * @param theType of question to be made
     */
    public void getTrivia(final String theType) {
        ArrayList trivia = myDatabase.getTrivia(theType);
        myQuestion = (String) trivia.get(0);
        myCorrect = (String) trivia.get(trivia.size() - 1);

        for(int i = 1; i < trivia.size() - 1; i++){
            myAnswers.add(trivia.get(i));
        }


    }


    /**
     * A getter method for the question.
     * @return the question
     */
    public String getQuestion(){
        return myQuestion;
    }

    /**
     * A getter method for the answer list.
     * @return the answer list
     */
    public List getAnswers(){
       return myAnswers;
    }

    /**
     * A getter method for the correct answer.
     * @return the correct answer
     */
    public String getCorrect(){
        return myCorrect;
    }


    /**
     * Abstract method used to create questions.
     */
    abstract void prepare();

}

