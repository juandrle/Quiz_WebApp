package de.hsrm.mi.web.projekt.ui.quiz;

import java.util.List;

import de.hsrm.mi.web.projekt.entities.frage.Frage;
import de.hsrm.mi.web.projekt.entities.quiz.Quiz;
import jakarta.validation.constraints.NotBlank;

public class QuizFormular {
    private List<Frage> fragen;

    @NotBlank
    private String thema;

    @NotBlank
    private String quiztitel;

    private int anzahl;

    public void toQuiz(Quiz q) {
        // befuellt q mit Formularinhalt
        q.setAnzahl(anzahl);
        q.setFragen(fragen);
        q.setThema(thema);
    }
    public void fromQuiz(Quiz q) {
        // befuellt Formularinhalt aus q
        this.anzahl = q.getAnzahl();
        this.fragen = q.getFragen();
        this.thema = q.getThema();
    }

    public String getQuiztitel() {
        return quiztitel;
    }
    public void setQuiztitel(String quiztitel) {
        this.quiztitel = quiztitel;
    }
    public int getAnzahl() {
        return anzahl;
    }
    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
    public List<Frage> getFragen() {
        return fragen;
    }
    public void setFragen(List<Frage> fragen) {
        this.fragen = fragen;
    }
    public String getThema() {
        return thema;
    }
    public void setThema(String thema) {
        this.thema = thema;
    }
    
}
