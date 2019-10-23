package de.naumovs;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

import de.naumovs.Model.Answer;
import de.naumovs.Model.Exam;
import de.naumovs.View.JCheckBoxAnswer;

public class Controller {

	Model model;

	private boolean isStarted = false;
	private boolean isExam = false;
	private int allQuestionCount = 0;
	private int currentQuestion = 1;

	private JLabel title;
	private JTextPane question;
	private JCheckBoxAnswer[] answers = new JCheckBoxAnswer[5];
	private JButton back;
	private JButton exam;
	private JButton next;

	public Controller(Model model) {
		this.model = model;
	}

	public void init() {
		title = (JLabel) this.model.modelMap.get(Constants.TITLE);
		allQuestionCount = model.examMap.size();

		question = (JTextPane) this.model.modelMap.get(Constants.QUESTION);
		question.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!isStarted) {
					start();
					initExam(currentQuestion);
				}
			}
		});

		answers[0] = (JCheckBoxAnswer) this.model.modelMap.get(Constants.ANSWER1);
		answers[1] = (JCheckBoxAnswer) this.model.modelMap.get(Constants.ANSWER2);
		answers[2] = (JCheckBoxAnswer) this.model.modelMap.get(Constants.ANSWER3);
		answers[3] = (JCheckBoxAnswer) this.model.modelMap.get(Constants.ANSWER4);
		answers[4] = (JCheckBoxAnswer) this.model.modelMap.get(Constants.ANSWER5);
		resetAnswers();

		back = (JButton) this.model.modelMap.get(Constants.BACK);
		AbstractAction actionBack = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				backExam();
			}
		};
		back.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),
				Constants.BACK);
		back.getActionMap().put(Constants.BACK, actionBack);
		back.addActionListener(actionBack);

		exam = (JButton) this.model.modelMap.get(Constants.EXAM);
		exam.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				exam();
			}
		});

		next = (JButton) this.model.modelMap.get(Constants.NEXT);
		AbstractAction actionNext = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				nextExam();
			}
		};
		next.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),
				Constants.NEXT);
		next.getActionMap().put(Constants.NEXT, actionNext);
		next.addActionListener(actionNext);
	}

	protected void start() {
		isStarted = true;

		back.setVisible(true);
		exam.setVisible(true);
		exam.setEnabled(true);
		next.setVisible(true);
	}

	private void backExam() {
		currentQuestion--;
		currentQuestion = (currentQuestion <= 0) ? allQuestionCount : currentQuestion;
		verifyAnswers();
		initExam(currentQuestion);
	}

	private void nextExam() {
		currentQuestion++;
		currentQuestion = (currentQuestion > allQuestionCount) ? 1 : currentQuestion;
		verifyAnswers();
		initExam(currentQuestion);
	}

	private void initExam(int examNumber) {
		resetAnswers();
		Exam exam = model.examMap.get(examNumber);

		title.setText(Constants.QUESTION_COUNT + examNumber + Constants.OFF + allQuestionCount);
		question.setText(exam.question);
		initAnswers(exam.answersSet);
	}

	private void resetAnswers() {
		for (int i = 0; i < answers.length; i++) {
			resetCheckBox(answers[i]);
		}
	}

	private <E extends JCheckBox> void resetCheckBox(JCheckBox E) {
		E.setVisible(false);
		E.setSelected(false);
	}

	private void initAnswers(Set<Answer> answersSet) {

		int i = 0;
		for (Answer answer : answersSet) {
			answers[i].setAnswer(answer);
			i++;
		}
	}

	private void verifyAnswers() {
		for (int i = 0; i < answers.length; i++) {
			verify(answers[i]);
		}
	}

	private void verify(JCheckBoxAnswer answerCheckBox) {
		Answer answer = answerCheckBox.getAnswer();
		// remember user decision
		answer.isAnswerFromUserChecked = answerCheckBox.isSelected();

		if (answerCheckBox.isSelected() && answer.isCorrect) {
			answer.isUserAnswerCorrect = true;
		} else {
			answer.isUserAnswerCorrect = false;
		}
	}

	private void exam() {
		isExam = !isExam;

		if (isExam) {
			title.setText(Constants.TITLE_EXAM_END);
			verifyAnswers();

			exam.setText(Constants.EXAM_ADJUST);
			question.setText(examAnswers());
			resetAnswers();

			back.setVisible(false);
			next.setVisible(false);
		} else {
			title.setText(Constants.TITLE);

			exam.setText(Constants.EXAM_TEXT);
			initExam(currentQuestion);

			back.setVisible(true);
			next.setVisible(true);
		}
	}

	/**
	 * 
	 * @return HTML
	 */
	private String examAnswers() {
		StringBuffer sb = new StringBuffer();
		sb.append("<h3>").append(Constants.VERIFY_TITLE).append("</h3>");
		sb.append("<hr>");
		sb.append(Constants.VERIFY_QUESTIONS_COUNT).append(allQuestionCount).append("<br>");
		sb.append(Constants.VERIFY_QUESTIONS_OK_COUNT).append(examOkCount()).append("<br>");
		sb.append(Constants.VERIFY_QUESTIONS_NOT_OK_COUNT).append(examNotOkCount()).append("<br>");
		sb.append("<br>");
		sb.append(Constants.VERIFY_POINT_SUM).append(examPointSumm()).append("<br>");
		sb.append("<br>");
		sb.append("<hr>");
		sb.append("<br>");

		return sb.toString();
	}

	private int examOkCount() {
		int okCount = 0;

		Exam exam;
		for (Map.Entry<Integer, Exam> entry : model.examMap.entrySet()) {
			exam = entry.getValue();
			for (Answer answer : exam.answersSet) {
				if (answer.isCorrect && answer.isUserAnswerCorrect) {
					okCount++;
				}
			}
		}
		return okCount;
	}

	private int examNotOkCount() {
		int notOkCount = 0;

		Exam exam;
		for (Map.Entry<Integer, Exam> entry : model.examMap.entrySet()) {
			exam = entry.getValue();
			for (Answer answer : exam.answersSet) {
				if (answer.isCorrect && !answer.isUserAnswerCorrect) {
					notOkCount++;
					break;
				}
			}
		}
		return notOkCount;
	}

	private int examPointSumm() {
		// TODO Auto-generated method stub
		return 0;
	}

}
