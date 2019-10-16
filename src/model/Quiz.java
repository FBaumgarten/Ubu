package model;

import java.io.File;
import java.util.ArrayList;

public class Quiz {
    private ArrayList<Question> questions;
    private File quizFile;
    private User user;
    private ArrayList<Answer> answers;

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

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    // einzelne Antwort nach  Fragen id liefern
    public Answer getAnswer(int id){
        Answer result = null;
        for (Answer answer:answers) {
            if (answer.getQuestionID() == id) result = answer;
        }
        return result;
    }

    public Quiz(ArrayList<Question> questions, File quizFile, User user, ArrayList<Answer> answers) {
        setQuestions(questions);
        setQuizFile(quizFile);
        setUser(user);
        setAnswers(answers);

    }
}
