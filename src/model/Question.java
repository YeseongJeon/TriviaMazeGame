package model;
/**
 * This will take a charge of each question objects. 
 * It will generate a question object depending on what the arguments are passed in.
 * Get methods that's created here, will be allow to get the questions, answers, options in other class. 
 * 
 * @author Yeseong Jeon
 * @version 1.0 TCSS 360(Summer 2022)
 */

public class Question {
	private final String myQuestion;
	private final String myAnswer;
	private final String myChoices;
	
	/**
	 * Constructor for class Question
	 * Creates a question with the question, answer, choices
	 * 
	 * @param theQuestion a String containing the question of the question
	 * @param theAnswer a String containing the answer of the question
	 * @param theChoices a String containing the choices of the question
	 */
	public Question(final String theQuestion, final String theAnswer, final String theChoices) {
		this.myQuestion = theQuestion;
		this.myAnswer = theAnswer;
		this.myChoices = theChoices;
	}
	
	/**
	 * Returns the question from the selected Question
	 * @return String type the question
	 */
	public String getQuestion() {
		return myQuestion;
	}
	
	/**
	 * Returns the answer from the selected Question
	 * @return String type the answer
	 */
	public String getAnswer() {
		return myAnswer;
	}
	
	/**
	 * Returns the choices from the selected Question
	 * @return String type the choices
	 */
	public String getChoices() {
		return myChoices;
	}
	
	public String toString() {
		return myQuestion + "\t" + myAnswer + "\t" + myChoices;
	}
}
