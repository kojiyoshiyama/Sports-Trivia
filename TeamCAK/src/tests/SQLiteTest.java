/*
 * TeamCAK TriviaMaze Game - Tests Package
 * Fall 2023
 */

package src.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.model.SQLite;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This is a tester class for SQLite
 *
 * @author Calvin Beardemphl, Koji Yoshiyama, Alex Thompson
 * @version 15/12/2023
 */
class SQLiteTest {

    /**
     * Reference tot eh SQLite object for testing
     */
    private SQLite mySqlite;

    /**
     * This is the setup needed before each test
     */
    @BeforeEach
    void setUp() {
        mySqlite = new SQLite();
    }

    /**
     * This method tests if the instances returned from getInstance() are the same
     */
    @Test
    void getInstance_ShouldReturnSameInstance() {
        SQLite instance1 = SQLite.getInstance();
        SQLite instance2 = SQLite.getInstance();

        assertSame(instance1, instance2, "These two instances should be the same");
    }
    /**
     * This method tests if the instances returned from getInstance() are different
     */
    @Test
    void getInstance_TwoDifferentInstances_ShouldBeFalse() {
        SQLite instance1 = SQLite.getInstance();
        SQLite instance2 = new SQLite();

        assertNotSame(instance1, instance2, "These two instances should NOT be the same");
    }

    /**
     * This method tests if getMyStmt() returns the same statement
     */
    @Test
    void getMyStmt_ShouldReturnSameStatement() {
        Statement stmt = mySqlite.getMyStmt();
        Statement stmt2 = mySqlite.getMyStmt();

        assertSame(stmt, stmt2, "These two statements should be the same");
    }

    /**
     * This method tests if getMyStmt() returns different statements
     */
    @Test
    void getMyStmt_TwoDifferentStatements_ShouldBeFalse() {
        Statement stmt = mySqlite.getMyStmt();
        SQLite sqlite2 = new SQLite();
        Statement stmt2 = sqlite2.getMyStmt();

        assertNotSame(stmt, stmt2, "These two statements should NOT be the same");
    }

    /**
     * This tests if getTrivia() returns a MCQuestion when it should
     */
    @Test
    void getTrivia_ShouldReturnMCQuestion() {
        ArrayList<String> mcTrivia = mySqlite.getTrivia("MC");
        assertEquals(6, mcTrivia.size(), "Size should be 6 for MC Question");
        // 0 question, 1 answer one, 2 answer two, 3 answer three, 4 answer four, 5 correct answer
        // total size 6
    }

    /**
     * This tests if getTrivia() returns a TFQuestion when it should
     */
    @Test
    void getTrivia_ShouldReturnTFQuestion() {
        ArrayList<String> tfTrivia = mySqlite.getTrivia("TF");
        assertEquals(4, tfTrivia.size(), "Size should be 4 for TF Question");
        // 0 question, 1 answer one, 2 answer two, 3 correct answer
        // total size 4
    }

    /**
     * This tests if getTrivia() returns a SAQuestion when it should
     */
    @Test
    void getTrivia_ShouldReturnSAQuestion() {
        ArrayList<String> saTrivia = mySqlite.getTrivia("SA");
        assertEquals(2, saTrivia.size(), "Size should be 2 for TF Question");
        // 0 question, 1 correct answer
        // total size 2
    }
}