package junit;

import model.GenerateRandomQuestions;
import model.Question;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GenerateRandomQuestionsTest {

    @Test
    public void createQuestion() {
        GenerateRandomQuestions ranQ = new GenerateRandomQuestions(); //single question
        Question question = ranQ.createQuestion();
        assertEquals(true, question != null);
    }

    @Test
    public void generateQuestion() {
        GenerateRandomQuestions grqTF = new GenerateRandomQuestions(); //single question
        Question tOfQuestion = grqTF.generateQuestion("TrueOrFalse");
        assertEquals("True or False", tOfQuestion.getChoices());
        Set<String> possibleTFAnswers = new HashSet<>();
        possibleTFAnswers.add("True");
        possibleTFAnswers.add("False");
        Assert.assertTrue(possibleTFAnswers.contains(tOfQuestion.getAnswer()));

        GenerateRandomQuestions grqSA = new GenerateRandomQuestions(); //single question
        Question shortQuestion = grqSA.generateQuestion("ShortAnswer");
        assertEquals("Short Answer", shortQuestion.getChoices());

        assertNotEquals(null, shortQuestion.getAnswer());

        GenerateRandomQuestions grqMC = new GenerateRandomQuestions(); //single question
        Question multQuestion = grqMC.generateQuestion("MultipleChoice");
        assertNotEquals(null, multQuestion.getChoices());

        Set<String> possibleMultAnswers = new HashSet<>();
        possibleMultAnswers.add("1");
        possibleMultAnswers.add("2");
        possibleMultAnswers.add("3");
        possibleMultAnswers.add("4");
        Assert.assertTrue(possibleMultAnswers.contains(multQuestion.getAnswer()));
    }
}