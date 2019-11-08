package de.ubu.frank.controller;

import de.ubu.frank.model.Quiz;
import de.ubu.frank.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class UbuContollerTest {
    UbuContoller ubuContoller;

    @BeforeEach
    void setUp() {
        ubuContoller = new UbuContoller();
        ubuContoller.setQfile(new File(UbuContoller.DEFAULT_QFILE));
        ubuContoller.setQuestionsCatalog(FileManager.readQFile(ubuContoller.getQfile()));
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void generateQuiz() {
        ubuContoller.setUser(new User());
        assertNull(ubuContoller.generateQuiz(0),"Quiz mit Länge 0");
        assertNull(ubuContoller.generateQuiz(-10) ,"Quiz mit negativer Länge");
        assertNull(ubuContoller.generateQuiz(200), "Quiz länger als Fragenkatalog");
        assertNotNull(ubuContoller.generateQuiz(UbuContoller.DEFAULT_QLENGTH),"Default Länge");
        assertNotNull(ubuContoller.generateQuiz(ubuContoller.getQuestionsCatalog().size()),"Maximale Länge = alle Fragen aus dem Katalog");
        assertEquals(Quiz.class, ubuContoller.generateQuiz(UbuContoller.DEFAULT_QLENGTH).getClass(),"Ist das ein Quiz");
        assertFalse(ubuContoller.generateQuiz(UbuContoller.DEFAULT_QLENGTH).isFinished());

    }


    @Test
    void init() {
    }
}