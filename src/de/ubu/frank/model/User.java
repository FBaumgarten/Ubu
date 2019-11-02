package de.ubu.frank.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class User implements Serializable{
    private String name;
    private LocalDateTime created;
    private ArrayList<Quiz> history;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public ArrayList<Quiz> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Quiz> history) {
        this.history = history;
    }

    public User(String name, LocalDateTime created, ArrayList<Quiz> history) {
        setName(name);
        setCreated(created);
        setHistory(history);
    }

    public User(){
        setName("Default");
        setCreated(LocalDateTime.now());
        setHistory(new ArrayList<Quiz>());
    }
}
