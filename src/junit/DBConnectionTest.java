package junit;

import model.DBConnection;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DBConnectionTest {

    @Test
    public void getInstance() {
        assertNotEquals(DBConnection.getInstance(), null);

    }

    @Test
    public void getTrueFalseQuestions() {

        assertNotEquals(DBConnection.getTrueFalseQuestions(), null);
    }

    @Test
    public void getShortAnswerQuestions() {

        assertNotEquals(DBConnection.getShortAnswerQuestions(), null);
    }

    @Test
    public void getMultQuestions() {
        assertNotEquals(DBConnection.getMultQuestions(), null);
        final int q = 6;
        assertEquals(q, DBConnection.getMultQuestions().size());
    }
}