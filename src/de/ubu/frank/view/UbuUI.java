package de.ubu.frank.view;

import de.ubu.frank.controller.UbuContoller;
import de.ubu.frank.model.Question;
import de.ubu.frank.model.Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UbuUI implements ActionListener {
    private JTextPane questionTextPane;
    private JButton prevButton;
    private JButton endButton;
    private JButton nextButton;
    private JPanel answerPanel;
    private JPanel mainPanel;
    private JLabel infoLabel;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;
    private JCheckBox checkBox5;

    private static Quiz quiz;

    public JTextPane getQuestionTextPane() {
        return questionTextPane;
    }

    public void setQuestionTextPane(JTextPane questionTextPane) {
        this.questionTextPane = questionTextPane;
    }

    public UbuUI() {
        prevButton.addActionListener(this);
        endButton.addActionListener(this);
        nextButton.addActionListener(this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ubu, das �bungstool");
        frame.setBounds(100, 100, 756, 720);
        UbuUI ubuUI = new UbuUI();
        UbuContoller ubu = new UbuContoller();

        frame.setContentPane(ubuUI.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        ubu.init();
        quiz = ubu.getQuiz();
        ubuUI.displayQuestion(quiz.getCurrentQuestion());
    }

    private void displayQuestion(Question question) {
        infoLabel.setText("Frage " + (quiz.getQuestions().indexOf(quiz.getCurrentQuestion()) + 1) + " von " + quiz.getQuestions().size());
        questionTextPane.setText(question.getQtext());
        checkBox1.setText(question.getMultiChoiceParts().get(0).getMcText());
        checkBox1.setSelected(question.getMultiChoiceParts().get(0).isMcInput());
        checkBox2.setText(question.getMultiChoiceParts().get(1).getMcText());
        checkBox2.setSelected(question.getMultiChoiceParts().get(1).isMcInput());
        checkBox3.setText(question.getMultiChoiceParts().get(2).getMcText());
        checkBox3.setSelected(question.getMultiChoiceParts().get(2).isMcInput());
        checkBox4.setText(question.getMultiChoiceParts().get(3).getMcText());
        checkBox4.setSelected(question.getMultiChoiceParts().get(3).isMcInput());
        checkBox5.setText(question.getMultiChoiceParts().get(4).getMcText());
        checkBox5.setSelected(question.getMultiChoiceParts().get(4).isMcInput());
        if (quiz.isFinished()) displayResults(question);
    }

    private void displayResults(Question question) {
        checkBox1.setBackground(question.getMultiChoiceParts().get(0).testMcInput() ? Color.GREEN : Color.RED);
        checkBox2.setBackground(question.getMultiChoiceParts().get(1).testMcInput() ? Color.GREEN : Color.RED);
        checkBox3.setBackground(question.getMultiChoiceParts().get(2).testMcInput() ? Color.GREEN : Color.RED);
        checkBox4.setBackground(question.getMultiChoiceParts().get(3).testMcInput() ? Color.GREEN : Color.RED);
        checkBox5.setBackground(question.getMultiChoiceParts().get(4).testMcInput() ? Color.GREEN : Color.RED);
    }

    private void saveInput(Question question) {
        question.getMultiChoiceParts().get(0).setMcInput(checkBox1.isSelected());
        question.getMultiChoiceParts().get(1).setMcInput(checkBox2.isSelected());
        question.getMultiChoiceParts().get(2).setMcInput(checkBox3.isSelected());
        question.getMultiChoiceParts().get(3).setMcInput(checkBox4.isSelected());
        question.getMultiChoiceParts().get(4).setMcInput(checkBox5.isSelected());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (!quiz.isFinished())
            saveInput(quiz.getCurrentQuestion());  //verhindert das bei abgaschlossenen Test weiter �nderungen gespeichert werder

        if (source.equals(nextButton)) clickNext();
        if (source.equals(prevButton)) clickPrev();
        if (source.equals(endButton)) clickEnd();

        displayQuestion(quiz.getCurrentQuestion());
    }

    private void clickEnd() {

        JOptionPane.showMessageDialog(null, quiz.quizResult());
    }

    private void clickPrev() {
        quiz.prevQuestion();
    }

    private void clickNext() {
        quiz.nextQuestion();
    }
}
