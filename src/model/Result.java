package model;

import java.io.File;
import java.util.ArrayList;

public class Result {
    private String date;
    private File testFile;
    private ArrayList<Answer> answers;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public File getTestFile() {
        return testFile;
    }

    public void setTestFile(File testFile) {
        this.testFile = testFile;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public Result(String date, File testFile, ArrayList<Answer> answers) {
        setDate(date);
        setTestFile(testFile);
        setAnswers(answers);
    }

    public Result(String csvString){
        String[] split = csvString.split(";");
        setDate(split[0]);
        setTestFile(new File(split[1]));
        ArrayList<Answer> ans = new ArrayList<>();
        for (int i = 2; i < split.length; i++) {
            ans.add(new Answer(split[i]));
        }
    }

    public String toCSV(){
        StringBuilder csvString;
        csvString = new StringBuilder(date + ";" + testFile.getPath());
        for (Answer answer: answers) {
            csvString.append(";").append(answer.toCSV());
        }
        return String.valueOf(csvString);
    }
}
