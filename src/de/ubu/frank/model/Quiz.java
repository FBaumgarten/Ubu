package de.ubu.frank.model;

import java.io.File;
import java.util.ArrayList;

public class Quiz {
    private ArrayList<Question> questions;
    private File quizFile;
    private User user;
    private Question currentQuestion;


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

    public Question getQuestion(int index){
        return questions.get(index);
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void nextQuestion(){
        Question result;
        if (questions.indexOf(currentQuestion) >= questions.size()-1) result = questions.get(0);
        else result=  questions.get(questions.indexOf(currentQuestion)+1);
        setCurrentQuestion(result);
    }

    public void prevQuestion(){
        Question result;
        if (questions.indexOf(currentQuestion)== 0) result = questions.get(questions.size()-1);
        else result =  questions.get(questions.indexOf(currentQuestion)-1);
        setCurrentQuestion(result);
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public Quiz(ArrayList<Question> questions, File quizFile, User user) {
        setQuestions(questions);
        setQuizFile(quizFile);
        setUser(user);
        setCurrentQuestion(questions.get(0));
    }
}
