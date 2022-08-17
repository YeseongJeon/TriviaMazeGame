package junit;

import model.DBConnection;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DBConnectionTest {
    private static DBConnection dbQuestions;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dbQuestions = DBConnection.getInstance();
    }

    @Test
    public void getTrueFalseQuestionsTest() {
        int actualNum = dbQuestions.getTrueFalseQuestions().size();
        assertEquals(true, actualNum > 0);
        assertEquals(18, actualNum);
    }

    @Test
    public void getShortAnswerQuestionsTest() {
        int actualNum = dbQuestions.getShortAnswerQuestions().size();
        assertEquals(true, actualNum > 0);
        assertEquals(25, actualNum);

    }

    @Test
    public void getMultQuestionsTest() {
        int actualNum = dbQuestions.getMultQuestions().size();
        assertEquals(true, actualNum > 0);
        assertEquals(6, actualNum);

    }
}