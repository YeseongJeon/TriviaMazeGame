package junit;

import model.GenerateRandomQuestions;
import model.Question;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class QuestionTest {

    GenerateRandomQuestions ranQ= new GenerateRandomQuestions();
    Question q = ranQ.createQuestion();
    @Test
    public void getQuestion() {

        assertNotEquals(q.getQuestion(), null);

    }

    @Test
    public void getAnswer() {
        assertNotEquals(q.getAnswer(), null);
    }

    @Test
    public void getChoices() {
        assertNotEquals(q.getAnswer(), null);
    }

    @Test
    public void testToString() {
        assertNotEquals(q.toString(), null);
    }
}