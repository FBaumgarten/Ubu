package de.ubu.frank.controller;

import de.ubu.frank.model.Question;
import de.ubu.frank.model.User;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    public static void writeFile(ArrayList<String> stringArrayList, File filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            for (String stringToWrite : stringArrayList) {
                writer.write(stringToWrite);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readFile(File file) {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader = null;

        try {
            if (file.createNewFile())
                return lines;  //legt leere Datei an falls nicht vorhanden und liefert leeres lines Array zurück
            if (!file.canRead() || !file.isFile()) System.exit(0);

            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) try {
                reader.close();
            } catch (IOException e) {
            }
        }
        return lines;
    }

    public static ArrayList<Question> readQFile(File qfile) {
        ArrayList<String> lines;
        ArrayList<Question> questions = new ArrayList<>();
        lines = new ArrayList<>(readFile(qfile));
        for (String line : lines) {
            questions.add(new Question(line));
        }
        return questions;
    }

    public static void writeQFile(ArrayList<Question> questions, File qFile) {
        //TODO Fragenkatalog schreiben, CSV Format für Export?
    }

    public static User readUFile(File uFile) {
        return null;
        //TODO User einlesen
    }

    public static void writeUFile(User user, File uFile) {
        //TODO Userfile schreiben, serialize?
    }
}
