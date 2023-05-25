package de.hsrm.mi.web.projekt.entities.quiz;

import java.util.Collection;
import java.util.HashSet;

import de.hsrm.mi.web.projekt.entities.frage.Frage;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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

   @ManyToMany
   @NotEmpty
   private Collection<Frage> fragen = new HashSet<>();

    @NotBlank
    private String quiztitel;

    private int anzahl;

    public long getId() {
        return id;
    }
    public long getVersion() {
        return version;
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
    public Collection<Frage> getFragen() {
        return fragen;
    }
    public void setFragen(Collection<Frage> fragen) {
        this.fragen = fragen;
    }
}
