/*
 * TeamCAK TriviaMaze Game - View Package
 * Fall 2023
 */

package src.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This is a general scoreboard class that will showcase different statistics of the user
 * that will be updated throughout the play of the game.
 *
 * @author Alex Thompson, Calvin Beardemphl, Koji Yoshiyama
 * @version 15/12/2023
 */
public class ScoreBoard extends JPanel implements PropertyChangeListener{

    /**
     * The string representation of the staring point for the timer.
     */
    private static final String INITIAL_TIMER = "00:00";

    /**
     * The Jlabel for the timer.
     */
    protected static final JLabel TIMER = new JLabel(INITIAL_TIMER);

    /**
     * A JLabel for the score.
     */
    private transient JLabel myScoreLabel;

    /**
     * The score of the player.
     */
    private  JTextField myScoreField;

    /**
     * A JLabel for the number of correct questions.
     */
    private transient JLabel myCorrectLabel;

    /**
     * The number of correct questions.
     */
    private  JTextField myCorrectField;

    /**
     * This is the timer object used to create the timer for the scoreboard
     */
    private  Timer myTimer;

    /**
     * The timer of how long the game has been played.
     */
    private  JLabel myTimeLabel;

    /**
     * The total elapsed time of game play.
     */
    private int myElapsedTime;

    /**
     * The amount of minutes that have passed.
     */
    private int myMinutes;

    /**
     * The amount of seconds that have passed.
     */
    private int mySeconds;

    /**
     * Constructor for the score board.
     */
    public ScoreBoard(final Timer theTimer) {
        // Initialize the panel properties
        myScoreLabel = new JLabel("Score:");
        myScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        myScoreField = new JTextField("0");
        myScoreField.setPreferredSize(new Dimension(20, 20));
        myScoreField.setEditable(false);
        myScoreField.setFocusable(false); // NEEDED FOR TRANSFERRING FOCUS

        myTimeLabel = new JLabel("Timer:");
        myTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        myTimer = theTimer;
        myTimeLabel.setFocusable(false); // NEEDED FOR TRANSFERRING FOCUS

        myCorrectLabel = new JLabel("Correct Questions:");
        myCorrectField = new JTextField("0");
        myCorrectField.setPreferredSize(new Dimension(20, 20));
        myCorrectField.setEditable(false);
        myCorrectField.setFocusable(false); // NEEDED FOR TRANSFERRING FOCUS


        setLayout(new GridLayout(3, 2));

        add(myScoreLabel);
        add(myScoreField);
        add(myTimeLabel);
        add(TIMER);
        add(myCorrectLabel);
        add(myCorrectField);

        // the current score board look is not my favorite,
        // definitely going to be changing it in the future
    }

    /**
     * This field is here due top tight coupling with actionPerformed() this field holds timer logic.
     */
    private final Timer myTimerLogic = new Timer(1000, new ActionListener() {

        /**
         * This method is the logic behind the timer.
         *
         * @param theE the event to be processed
         */
        @Override
        public void actionPerformed(final ActionEvent theE) {
            final String numberFormat = "%02d";
            myElapsedTime += 1000;
            myMinutes = (myElapsedTime/60000) % 60;
            mySeconds = (myElapsedTime/1000) % 60;
            final String minsString = String.format(numberFormat, myMinutes);
            final String secondsString = String.format(numberFormat, mySeconds);
            TIMER.setText(minsString + ":" + secondsString);
            System.out.println("Timer Is going");
        }
    });

    /**
     * Setter method to update the score.
     * @param theScore the amount to set myScoreField to
     */
    private void setMyScoreField(final int theScore){
        myScoreField.setText(Integer.toString(theScore));
    }


    /**
     * Setter method to update the number of
     * correct questions answered.
     * @param theCorrect the amount to set myCorrectField to
     */
    private void setMyCorrectField(final int theCorrect){
        myCorrectField.setText(Integer.toString(theCorrect));
    }

    /**
     * A property change listener.
     *
     * @param theEvt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if("score".equals(theEvt.getPropertyName())) {
            setMyScoreField((int)theEvt.getNewValue());
        } else if("correct".equals(theEvt.getPropertyName())){
            setMyCorrectField((int)theEvt.getNewValue());
        } else if("total".equals(theEvt.getPropertyName())){
//            System.out.println("Total Number of Questions: " + theEvt.getNewValue());
            //^ for testing, this would really be used end of game I believe
        }
        repaint();
    }

    // add more methods to update scores, reset the scoreboard, etc. as needed
}
