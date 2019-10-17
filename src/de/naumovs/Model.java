package de.naumovs;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import de.naumovs.View.JCheckBoxWithID;

public class Model {

	protected Map<Integer, Exam> examMap = new LinkedHashMap<Integer, Model.Exam>();
	protected Map<String, Component> modelMap = new LinkedHashMap<String, Component>();
	//
	private JLabel title;
	private JTextPane question;
	
	private JCheckBoxWithID answer1;
	private JCheckBoxWithID answer2;
	private JCheckBoxWithID answer3;
	private JCheckBoxWithID answer4;
	private JCheckBoxWithID answer5;
	
	private JButton back;
	private JButton exam;
	private JButton along;

	/**
	 * init at first start
	 */
	protected void init() {

		readQuestions();

		title = (JLabel) this.modelMap.get(Constants.TITLE);
		title.setText(Constants.TITLE_INIT);

		question = (JTextPane) this.modelMap.get(Constants.QUESTION);
		question.setText(Constants.QUESTION_INIT);

		answer1 = (JCheckBoxWithID) this.modelMap.get(Constants.ANSWER1);
		answer2 = (JCheckBoxWithID) this.modelMap.get(Constants.ANSWER2);
		answer3 = (JCheckBoxWithID) this.modelMap.get(Constants.ANSWER3);
		answer4 = (JCheckBoxWithID) this.modelMap.get(Constants.ANSWER4);
		answer5 = (JCheckBoxWithID) this.modelMap.get(Constants.ANSWER5);
		answer1.setVisible(false);
		answer2.setVisible(false);
		answer3.setVisible(false);
		answer4.setVisible(false);
		answer5.setVisible(false);

		back = (JButton) this.modelMap.get(Constants.BACK);
		back.setVisible(false);
		
		exam = (JButton) this.modelMap.get(Constants.EXAM);
		exam.setVisible(false);
		
		along = (JButton) this.modelMap.get(Constants.ALONG);
		along.setVisible(false);

	}

	private void readQuestions() {
		BufferedReader br;
		String line = null;
		try {
			br = new BufferedReader(new FileReader(Constants.FILE_QUESTIONS));
			try {
				while ((line = br.readLine()) != null) {
					String str[] = line.split(";");
										
					Exam exam = new Exam();
					Set<Answer> answerSet = new HashSet<Answer>();					
					
					for (int i = 0; i < str.length; i++) {
						switch (i) {
						case 0:
							// set id
							exam.id = Integer.parseInt(str[i]);
							break;
						case 1:
							// set question
							exam.question = str[i];
							break;
						default:
							// set answers
							Answer answer = new Answer();					
							answer.text = str[i];
							i++;
							answer.correct = Boolean.parseBoolean(str[i]);
							
							answerSet.add(answer);
							break;
						}
					}
					// set answer set before put 
					exam.answersSet = answerSet;
					examMap.put(exam.id, exam);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	class Exam {
		Integer id;
		String question;
		Set<Answer> answersSet;
	}

	class Answer {		
		boolean correct;
		String text;
	}
}
