/*
 * TeamCAK TriviaMaze Game - View Package
 * Fall 2023
 */

package src.view;

import src.model.Question;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * This is a general trivia class that will display the questions
 * and answers throughout the play of the game.
 *
 * @author Alex Thompson, Calvin Beardemphl, Koji Yoshiyama
 * @version 15/12/2023
 */
public class TriviaBoard extends JPanel implements PropertyChangeListener {

    /**
     * The current question.
     */
    private final JTextArea myQuestionLabel;

    /**
     * The first answer choice to the current question.
     */
    private final JRadioButton myA1;

    /**
     * The second answer choice to the current question.
     */
    private  final JRadioButton myA2;

    /**
     * The third answer choice to the current question.
     */
    private  final JRadioButton myA3;

    /**
     * The fourth answer choice to the current question.
     */
    private final JRadioButton myA4;

    /**
     * The result of the short answer given by the player.
     */
    private final JTextField myShortAnswer;

    /**
     * The correct answer in the list of Answers.
     */
    private String myCorrectAnswer;


    /**
     * A group of the answer buttons.
     */
    private final ButtonGroup myButtonGroup;

    /**
     * The button to enter a question.
     */
    private final JButton myEnter;

    /**
     * The button to continue the game.
     */
    private final JButton myContinueButton; // NEEDED FOR TRANSFERRING FOCUS

    /**
     * A message to display when no answers are chosen.
     */
    private final JLabel myNoAnswer;

    /**
     * A Box object used to vertically align components on the TriviaBoard.
     */
    private final Box myBox;

    /**
     * A String representing the button the player chose.
     */
    private transient String mySelectedButton;

    /**
     * An Item Listener to handle answer button clicks
     */
    private transient ItemListener myAnswerButtonClickListener;
    /**
     * An Action Listener to handle enter button clicks.
     */
    private transient ActionListener myEnterButtonClickListener;

    /**
     * An Action Listener to handle continue button clicks.
     */
    private transient ActionListener myContinueButtonClickListener;

    /**
     * Constructor for the TriviaBoard.
     */
    public TriviaBoard() {
        myQuestionLabel = new JTextArea();
        myA1 = new JRadioButton();
        myA2 = new JRadioButton();
        myA3 = new JRadioButton();
        myA4 = new JRadioButton();

        myEnter = new JButton("Enter");
        myNoAnswer = new JLabel();
        myNoAnswer.setFocusable(false);
        myButtonGroup = new ButtonGroup(); // so that only one answer can be chosen
        myBox = Box.createVerticalBox();
        myBox.setVisible(true);
        myShortAnswer = new JTextField("");

        mySelectedButton = "";
        myEnter.addActionListener(myEnterButtonClickListener);

        myContinueButton = new JButton("Continue Game");
        myContinueButton.addActionListener(myContinueButtonClickListener);

        setMyAnswerButtonClickListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent theEvt) {
                if(theEvt.getItem().equals(myA1)){
                    mySelectedButton = myA1.getText();

                } else if(theEvt.getItem().equals(myA2)){
                    mySelectedButton = myA2.getText();

                } else if(theEvt.getItem().equals(myA3)){
                    mySelectedButton = myA3.getText();

                } else if(theEvt.getItem().equals(myA4)){
                    mySelectedButton = myA4.getText();
                }

            }
        });
    }

    /**
     * Used by AnswerCheck to display the continue button.
     */
    public void displayContinue(){
        this.add(myContinueButton);
        repaint();
    }

    /**
     * Used by AnswerControls to hide the continue button.
     */
    public void hideContinue(){
        this.remove(myContinueButton);
        repaint();
    }

    /**
     * Displays the answer buttons or the short answer text field,
     * the enter button, and the no answer label when needed.
     */
    public void displayComponents(final int theSize){
        myBox.removeAll();

        if(theSize >= 2){
            myBox.add(myA1);
            myBox.add(myA2);
            if(theSize == 4) {
                myBox.add(myA3);
                myBox.add(myA4);
            }
        } else {
            myBox.add(myShortAnswer);
        }
        myBox.add(myEnter);
        myBox.add(myNoAnswer);
        this.add(myBox);
        repaint();
    }

    /**
     * Displays the current question to the GUI.
     *
     * @param theQuestion the Question object
     */
    private void initializeQuestion(final Question theQuestion) {
        myQuestionLabel.setText(theQuestion.getQuestion());
        myQuestionLabel.setBounds(10, 10, 250, 300);
        myQuestionLabel.setLineWrap(true);
        myQuestionLabel.setWrapStyleWord(true);
        myQuestionLabel.setEditable(false);
        myQuestionLabel.setFocusable(false);
        myQuestionLabel.setBackground(Color.pink);
        this.add(myQuestionLabel);

        initializeAnswerButtons(theQuestion);
    }

    /**
     * Sets up the answer buttons or the short answer text field.
     *
     * @param theQuestion the Question object
     */
    private void initializeAnswerButtons(final Question theQuestion) {

        List theAnswers = theQuestion.getAnswers();

        //makes sure the answer buttons are all
        // cleared to be reset with text
        myA1.setText("");
        myA2.setText("");
        myA3.setText("");
        myA4.setText("");

        myA1.addItemListener(myAnswerButtonClickListener);
        myA2.addItemListener(myAnswerButtonClickListener);
        myA3.addItemListener(myAnswerButtonClickListener);
        myA4.addItemListener(myAnswerButtonClickListener);

        myCorrectAnswer = theQuestion.getCorrect();

        int size = theAnswers.size();
        // if size == 0 it is a short answer question
        // if size == 2 it is true or false
        // if size == 4 it is multiple choice

        if(size > 1) {
            myA1.setText((String) theAnswers.get(0));
            myA2.setText((String) theAnswers.get(1));

            myButtonGroup.add(myA1);
            myButtonGroup.add(myA2);
        }
        if (size == 4) {
            myA3.setText((String) theAnswers.get(2));
            myA4.setText((String) theAnswers.get(3));

            myButtonGroup.add(myA3);
            myButtonGroup.add(myA4);
        } else if (size == 0) {
            myShortAnswer.setText("");
        }

        //makes sure none of the buttons are selected
        myButtonGroup.clearSelection();

        displayComponents(size);
    }

    /**
     * Getter used in AnswerControls.
     *
     * @return the String of  myNoAnswer
     */
    public String getMyNoAnswer() {
        return myNoAnswer.getText();
    }

    /**
     * Used in AnswerControls to check
     * if user got question right or wrong.
     *
     * @return the string of the correct answer
     */
    public String getMyCorrectAnswer() {
        return myCorrectAnswer;
    }

    /**
     * Used in AnswerControls to remove the answer buttons
     * from the display after the question has been answered.
     *
     * @return the box holding buttons
     */
    public Box getMyBox() {
        return myBox;
    }


    /**
     * Returns a string of the players answer
     * to a multiple choice or true or false question.
     *
     * @return the answer chosen
     */
    public String getMySelectedButton(){
        return mySelectedButton;
    }

    /**
     * Returns a string of the players answer
     * to a short answer question.
     *
     * @return the answer written
     */
    public String getMyShortAnswer(){
        return myShortAnswer.getText().trim();
    }


    /**
     * Used in AnswerControls to display if the player
     * does not choose/enter an answer.
     *
     * @param theNoAnswer the text to set to myNoAnswer
     */
    public void setMyNoAnswer(final String theNoAnswer) {
        myNoAnswer.setText(theNoAnswer);
    }

    /**
     * Used in AnswerControls to display
     * if user got question right or wrong.
     *
     * @param theQuestion the text to display
     */
    public void setMyQuestion(final String theQuestion) {
        myQuestionLabel.setText(theQuestion);
    }


    /**
     * A method to handle property changes from the model.
     *
     * @param theEvt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if("question".equals(theEvt.getPropertyName())){
            initializeQuestion((Question) theEvt.getNewValue());
        } else if("new game".equals(theEvt.getPropertyName())) {
            displayContinue();
        }
        repaint();
    }

    /**
     * Sets the ActionListener for the answer buttons.
     * The ActionListener is triggered when any answer buttons are clicked.
     *
     * @param theListener The ActionListener to be set for the answer buttons.
     * This listener will be notified when any answer buttons are clicked.
     */
    public void setMyAnswerButtonClickListener(final ItemListener theListener){
        this.myAnswerButtonClickListener = theListener;
    }

    /**
     * Sets the ActionListener for the enter button.
     * The ActionListener is triggered when the enter button is clicked.
     *
     * @param theListener The ActionListener to be set for the enter button.
     * This listener will be notified when the button is clicked.
     */
    public void setMyEnterButtonClickListener(final ActionListener theListener) {
        this.myEnterButtonClickListener = theListener;
        myEnter.addActionListener(myEnterButtonClickListener);
    }

    /**
     * Sets the ActionListener for the continue button.
     * The ActionListener is triggered when the continue button is clicked.
     *
     * @param theListener The ActionListener to be set for the continue button.
     * This listener will be notified when the button is clicked.
     */
    public void setMyContinueButtonClickListener(final ActionListener theListener) {
        this.myContinueButtonClickListener = theListener;
        myContinueButton.addActionListener(myContinueButtonClickListener);
    }
}

