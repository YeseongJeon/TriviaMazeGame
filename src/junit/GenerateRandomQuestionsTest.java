package junit;

import model.GenerateRandomQuestions;
import model.Question;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class GenerateRandomQuestionsTest {
    GenerateRandomQuestions ranQ= new GenerateRandomQuestions();
    Question q = ranQ.createQuestion();
    @Test
    public void createQuestion() {
        assertNotEquals(GenerateRandomQuestions.createQuestion(), null);

    }
    @Test
    public void generateQuestion() {
     //   assertNotEquals(GenerateRandomQuestions.generateQuestion(), null);
    }
}