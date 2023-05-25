package de.hsrm.mi.web.projekt.ui.quiz;

import java.util.Collection;
import java.util.HashSet;

import de.hsrm.mi.web.projekt.entities.frage.Frage;
import de.hsrm.mi.web.projekt.entities.quiz.Quiz;
import jakarta.validation.constraints.NotBlank;

public class QuizFormular {
    private Collection<Frage> fragen = new HashSet<>();

    @NotBlank
    private String quiztitel;

    private int anzahl;

    public void toQuiz(Quiz q) {
        // befuellt q mit Formularinhalt
        q.setQuiztitel(quiztitel);
        q.setAnzahl(fragen.size());
        q.setFragen(fragen);
        
    }
    public void fromQuiz(Quiz q) {
        // befuellt Formularinhalt aus q
        this.anzahl = q.getFragen().size();
        this.fragen = q.getFragen();
        this.quiztitel = q.getQuiztitel();
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
    
    public void setFragen(Collection<Frage> fragen) {
        this.fragen = fragen;
    }
    public Collection<Frage> getFragen() {
        return fragen;
    }
    
}
