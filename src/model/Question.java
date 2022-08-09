
package model;
/*
 * This will also be a special class for our project. 
 * This shall contain the various questions that we will continue to ask the user as he completes the game. 
 * The questions will come from three unique tables. 
 * That are fore true/false questions, questions with numerical answers, 
 * and lastly questions that can be answered in a single word.
 */

public class Question {
	private final String myQuestion;
	private final String myAnswer;
	private final String myChoices;
	
	public Question(String question, String answer, String choices) {
		this.myQuestion = question;
		this.myAnswer = answer;
		this.myChoices = choices;
	}
	
	public String getQuestion() {
		return myQuestion;
	}
	
	public String getAnswer() {
		return myAnswer;
	}
	
	public String getChoices() {
		return myChoices;
	}
	
	public String toString() {
		return myQuestion + "\t" + myAnswer + "\t" + myChoices;
	}
}
