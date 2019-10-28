package de.ubu.frank.controller;

import de.ubu.frank.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class UbuContoller {
    public static final String DEFAULT_UFILE = "default.usr";
    public static final String DEFAULT_QFILE = "default.qiz";
    public static final int DEFAULT_QLENGTH = 5;  //TODO testwert, später erhöhen auf 20 oder so
    private File ufile;
    private File qfile;
    private Quiz quiz;
    private User user;
    private ArrayList<Question> questionsCatalog;

    public Quiz generateQuiz(int quizLength) {
        ArrayList<Question> questions = new ArrayList<>();
        Random random = new Random();
        while (questions.size() < quizLength) {
            Question qtemp = questionsCatalog.get(random.nextInt(questionsCatalog.size()));
            if (!questions.contains(qtemp)) {
                questions.add(qtemp);
            }
        }
        return new Quiz(questions, qfile, user);
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public static void main(String[] args) {
        UbuContoller ubu = new UbuContoller();
        ubu.init();
        ubu.loop();
        ubu.shutdown();
        ubu.generateQuiz(DEFAULT_QLENGTH);
        for (Question question : ubu.quiz.getQuestions()) {
            System.out.println(question.toCSV());
        }
    }

    public void init() {
        qfile = new File(DEFAULT_QFILE);
        questionsCatalog = FileManager.readQFile(qfile);
        ufile = new File(DEFAULT_UFILE);
        user = FileManager.readUFile(ufile);
        //TODO default quiz, später entfernen wenn aufruf über UI steht
        quiz = generateQuiz(DEFAULT_QLENGTH);
    }

    private void loop() {
    }

    private void shutdown() {
        FileManager.writeUFile(user, ufile);
        // qFile write erst sinnvoll mit Editorfunktion implementierung
        //FileManager.writeQFile(questionsCatalog, qfile);
    }

}
