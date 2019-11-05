package de.ubu.frank.controller;

import de.ubu.frank.model.Question;
import de.ubu.frank.model.Quiz;
import de.ubu.frank.model.User;

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

    public File getUfile() {
        return ufile;
    }

    public void setUfile(File ufile) {
        this.ufile = ufile;
    }

    public File getQfile() {
        return qfile;
    }

    public void setQfile(File qfile) {
        this.qfile = qfile;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Question> getQuestionsCatalog() {
        return questionsCatalog;
    }

    public void setQuestionsCatalog(ArrayList<Question> questionsCatalog) {
        this.questionsCatalog = questionsCatalog;
    }

    public Quiz generateQuiz(int quizLength) {
        Quiz result = null;
        if (quizLength > 0 && quizLength <= questionsCatalog.size()) {
            ArrayList<Question> questions = new ArrayList<>();
            Random random = new Random();
            while (questions.size() < quizLength) {
                Question qtemp = questionsCatalog.get(random.nextInt(questionsCatalog.size()));
                if (!questions.contains(qtemp)) {
                    questions.add(qtemp);
                }
            }
            result = new Quiz(questions, qfile, user);
        }
        return result;
    }

    public Quiz getQuiz() {
        return quiz;
    }

//    public static void main(String[] args) {k
//        UbuContoller ubu = new UbuContoller();
//        ubu.init();
//        ubu.loop();
//        ubu.shutdown();
//        ubu.generateQuiz(DEFAULT_QLENGTH);
//        for (Question question : ubu.quiz.getQuestions()) {
//            System.out.println(question.toCSV());
//        }
//    }

    public void init() {
        questionsCatalog = FileManager.readQFile(qfile);
        user = FileManager.readUFile(ufile);
        if (user==null){
            user = new User();
        }
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

    public UbuContoller() {
        qfile = new File(DEFAULT_QFILE);
        ufile = new File(DEFAULT_UFILE);
        questionsCatalog = new ArrayList<>();
        user = null;
        quiz = null;
    }
}
