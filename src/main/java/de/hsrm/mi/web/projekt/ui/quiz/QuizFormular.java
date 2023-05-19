package de.hsrm.mi.web.projekt.ui.quiz;

import java.util.List;

import de.hsrm.mi.web.projekt.entities.frage.Frage;
import jakarta.validation.constraints.NotBlank;

public class QuizFormular {
    private List<Frage> fragen;

    @NotBlank
    private String thema;

    private int anzahl;

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
