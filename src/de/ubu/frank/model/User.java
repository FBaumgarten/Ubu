package de.ubu.frank.model;

import java.util.ArrayList;

public class User {
    private String name;
    private String created;
    private ArrayList<Quiz> history;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public ArrayList<Quiz> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Quiz> history) {
        this.history = history;
    }

    public User(String name, String created, ArrayList<Quiz> history) {
        setName(name);
        setCreated(created);
        setHistory(history);
    }
}
