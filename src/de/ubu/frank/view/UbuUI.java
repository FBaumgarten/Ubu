package de.ubu.frank.view;

import de.ubu.frank.controller.UbuContoller;
import de.ubu.frank.model.Question;
import de.ubu.frank.model.Quiz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UbuUI {
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
    private static int currentQuestion = 0;

    public JTextPane getQuestionTextPane() {
        return questionTextPane;
    }

    public void setQuestionTextPane(JTextPane questionTextPane) {
        this.questionTextPane = questionTextPane;
    }

    public UbuUI() {
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quiz.nextQuestion();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ubu, das Übungstool");
        UbuUI ubuUI = new UbuUI();
        UbuContoller ubu = new UbuContoller();

        frame.setContentPane(ubuUI.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        ubu.init();
        quiz = ubu.getQuiz();

        displayQuestion(ubuUI, quiz.getCurrentQuestion());

    }

    private static void displayQuestion(UbuUI ubuUI, Question question) {
        ubuUI.infoLabel.setText("Frage " + (quiz.getQuestions().indexOf(quiz.getCurrentQuestion())+1) + " von " + quiz.getQuestions().size());
        ubuUI.questionTextPane.setText(question.getQtext());
        ubuUI.checkBox1.setText(question.getMultiChoiceParts().get(0).getMcText());
        ubuUI.checkBox2.setText(question.getMultiChoiceParts().get(1).getMcText());
        ubuUI.checkBox3.setText(question.getMultiChoiceParts().get(2).getMcText());
        ubuUI.checkBox4.setText(question.getMultiChoiceParts().get(3).getMcText());
        ubuUI.checkBox5.setText(question.getMultiChoiceParts().get(4).getMcText());
    }
}
