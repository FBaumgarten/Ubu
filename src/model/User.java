package model;

import java.util.ArrayList;

public class User {
    private String name;
    private String created;
    private ArrayList<Result> history;

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

    public ArrayList<Result> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Result> history) {
        this.history = history;
    }

    public User(String name, String created, ArrayList<Result> history) {
        setName(name);
        setCreated(created);
        setHistory(history);
    }
}
