package de.ubu.frank.model;

import java.io.File;
import java.util.ArrayList;

public class Quiz {
    private ArrayList<Question> questions;
    private File quizFile;
    private User user;


    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public File getQuizFile() {
        return quizFile;
    }

    public void setQuizFile(File quizFile) {
        this.quizFile = quizFile;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz(ArrayList<Question> questions, File quizFile, User user) {
        setQuestions(questions);
        setQuizFile(quizFile);
        setUser(user);
    }
}
