package de.hsrm.mi.web.projekt.entities.kategorie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hsrm.mi.web.projekt.entities.frage.Frage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotEmpty;

/**
 * @author Ilja Tkatchev
 */

@Entity
public class Kategorie implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    @NotEmpty
    @Column(unique = true)
    private String name;

    @NotEmpty
    private String beschreibung;

    @OneToMany(mappedBy = "kategorie")
    private List<Frage> fragen = new ArrayList<>();

    private int anzahlFragen;

    public int getAnzahlFragen() {
        this.anzahlFragen = fragen.size();
        return anzahlFragen;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public List<Frage> getFragen() {
        return fragen;
    }

    public void setFragen(List<Frage> fragen) {
        this.fragen = fragen;
    }
}
