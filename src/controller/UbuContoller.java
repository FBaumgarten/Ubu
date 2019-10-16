package controller;

import AE2.project.ubu.model.Question;
import AE2.project.ubu.model.Quiz;
import AE2.project.ubu.model.User;

import java.io.File;
import java.util.ArrayList;

public class UbuContoller {
    public static final String DEFAULT_UFILE = "default.usr";
    public static final String DEFAULT_QFILE = "default.qiz";
    private File ufile;
    private File qfile;
    private Quiz quiz;
    private User user;
    private ArrayList<Question> questionsCatalog;

    public Quiz generateQuiz (ArrayList<Question> questions){
        //TODO quiz generieren, x Fragen aus Fragenkatalog zufällig auswählen
        return null;
    }

    public static void main(String[] args) {
        UbuContoller ubu = new UbuContoller();
        ubu.init();
    }

    private void init() {
        qfile = new File(DEFAULT_QFILE);
        questionsCatalog = FileManager.readQFile(qfile);
        ufile = new File(DEFAULT_UFILE);
        user = FileManager.readUFile(ufile);


    }


}
