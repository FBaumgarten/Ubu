package de.ubu.frank.model;

import java.util.ArrayList;

public class Question {
    private int id;
    private String qtext;
    private ArrayList<MultiChoicePart> multiChoiceParts;

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

    public ArrayList<MultiChoicePart> getMultiChoiceParts() {
        return multiChoiceParts;
    }

    public void setMultiChoiceParts(ArrayList<MultiChoicePart> multiChoiceParts) {
        this.multiChoiceParts = multiChoiceParts;
    }

    public Question(int id, String qtext, ArrayList<MultiChoicePart> multiChoiceParts) {
        setId(id);
        setQtext(qtext);
        setMultiChoiceParts(multiChoiceParts);
    }

    public Question (String csv){
        String[] split = csv.split(";");
        setId(Integer.parseInt(split[0]));
        setQtext(split[1]);
        multiChoiceParts = new ArrayList<>();
        for (int i = 2; i < split.length ; i+=2) {
            multiChoiceParts.add(new MultiChoicePart(split[i],Boolean.parseBoolean(split[i+1])));
        }
    }

    public String toCSV() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id).append(";").append(qtext);
        for (MultiChoicePart mcPart:multiChoiceParts) {
            stringBuilder.append(";").append(mcPart.toCSV());
        }
        return String.valueOf(stringBuilder);
    }
}

