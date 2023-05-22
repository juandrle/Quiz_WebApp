package de.hsrm.mi.web.projekt.entities.quiz;

import java.util.List;

import de.hsrm.mi.web.projekt.entities.frage.Frage;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;




@Entity
public class Quiz {

    @Id
    @GeneratedValue
   private long id; 
    @Version
   private long version;

   @ElementCollection
   @NotEmpty
   private List<Frage> fragen;

    @NotBlank
    private String thema;

    private int anzahl;

    public long getId() {
        return id;
    }
    public long getVersion() {
        return version;
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
