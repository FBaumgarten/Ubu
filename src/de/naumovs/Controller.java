package de.naumovs;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import de.naumovs.Model.Answer;
import de.naumovs.Model.Exam;
import de.naumovs.View.JCheckBoxAnswer;

public class Controller {

	Model model;
	boolean isStarted = false;
	int allQuestionCount = 0;

	private JLabel title;
	private JTextPane question;

	private JCheckBoxAnswer answer1;
	private JCheckBoxAnswer answer2;
	private JCheckBoxAnswer answer3;
	private JCheckBoxAnswer answer4;
	private JCheckBoxAnswer answer5;
	private int checkboxCount;

	private JButton back;
	private JButton exam;
	private JButton along;

	public Controller(Model model) {
		this.model = model;
	}

	public void init() {
		this.allQuestionCount = model.examMap.size();

		title = (JLabel) this.model.modelMap.get(Constants.TITLE);

		question = (JTextPane) this.model.modelMap.get(Constants.QUESTION);
		question.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!isStarted) {
					start();
				}
			}
		});

		answer1 = (JCheckBoxAnswer) this.model.modelMap.get(Constants.ANSWER1);
		answer2 = (JCheckBoxAnswer) this.model.modelMap.get(Constants.ANSWER2);
		answer3 = (JCheckBoxAnswer) this.model.modelMap.get(Constants.ANSWER3);
		answer4 = (JCheckBoxAnswer) this.model.modelMap.get(Constants.ANSWER4);
		answer5 = (JCheckBoxAnswer) this.model.modelMap.get(Constants.ANSWER5);
		resetAnswers();

		back = (JButton) this.model.modelMap.get(Constants.BACK);
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
//				back();
			}
		});
		
		
		exam = (JButton) this.model.modelMap.get(Constants.EXAM);
		
		along = (JButton) this.model.modelMap.get(Constants.ALONG);
		along.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				along();
			}
		});


	}

	protected void start() {
		isStarted = true;
		title.setText(Constants.QUESTION_COUNT + 1 + Constants.OFF + allQuestionCount);

		Exam exam = model.examMap.entrySet().stream().findFirst().get().getValue();
		question.setText(exam.question);

		initAnswers(exam.answersSet);

		back.setVisible(true);
		
		along.setVisible(true);
	}

	private void resetAnswers() {
		answer1.setVisible(false);
		answer2.setVisible(false);
		answer3.setVisible(false);
		answer4.setVisible(false);
		answer5.setVisible(false);
	}

	private void initAnswers(Set<Answer> answersSet) {
		checkboxCount = 0;
		for (Answer answer : answersSet) {
			checkboxCount++;
			switch (checkboxCount) {
			case 1:
				answer1.setAnswer(answer);
				answer1.setText(answer.text);
				answer1.setVisible(true);
				break;
			case 2:
				answer2.setAnswer(answer);
				answer2.setText(answer.text);
				answer2.setVisible(true);
				break;
			case 3:
				answer3.setAnswer(answer);
				answer3.setText(answer.text);
				answer3.setVisible(true);
				break;
			case 4:
				answer4.setAnswer(answer);
				answer4.setText(answer.text);
				answer4.setVisible(true);
				break;
			case 5:
				answer5.setAnswer(answer);
				answer5.setText(answer.text);
				answer5.setVisible(true);
				break;
			default:
				// TODO: error
				break;
			}
		}

	}

	private void along() {
		verifyAnswers();
		// go next question

	}

	private void verifyAnswers() {
		for (int i = 1; i <= checkboxCount; i++) {
			switch (i) {
			case 1:
				verify(answer1);
				break;
			case 2:
				verify(answer2);
				break;
			case 3:
				verify(answer3);
				break;
			case 4:
				verify(answer4);
				break;
			case 5:
				verify(answer5);
				break;
			default:
				// TODO: error
				break;
			}
		}

	}

	private void verify(JCheckBoxAnswer answerCheckBox) {
		if (answerCheckBox.isSelected() == answerCheckBox.getAnswer().isAnswerCorrect) {
			answerCheckBox.setBackground(Color.GREEN);
		} else{
			answerCheckBox.setBackground(Color.RED);
		}
	}

}
