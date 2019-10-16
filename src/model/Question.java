package model;

import java.util.ArrayList;

public class Question {
    private int id;
    private String qtext;
    private ArrayList<SolutionPart> solution;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQtext() {
        return qtext;
    }

    public void setQtext(String qtext) {
        this.qtext = qtext;
    }

    public ArrayList<SolutionPart> getSolution() {
        return solution;
    }

    public void setSolution(ArrayList<SolutionPart> solution) {
        this.solution = solution;
    }

    public Question(int id, String qtext, ArrayList<SolutionPart>solution) {
        setId(id);
        setQtext(qtext);
        setSolution(solution);
    }

    public Question (String csv){
        String[] split = csv.split(";");
        setId(Integer.parseInt(split[0]));
        setQtext(split[1]);
        for (int i = 2; i < split.length; i+=2) {
            solution.add(new SolutionPart(split[i],Boolean.parseBoolean(split[i+1])));
        }
    }

    public String toCSV() {
        StringBuilder result = new StringBuilder(id + ";" + qtext);
        for (SolutionPart part:solution) {
            result.append(";").append(part.getSText()).append(";").append(part.isSBool());
        }
        return String.valueOf(result);
    }
}


