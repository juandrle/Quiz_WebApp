package de.hsrm.mi.web.projekt.entities.quiz;

import java.util.Collection;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import de.hsrm.mi.web.projekt.entities.frage.Frage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotEmpty;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Quiz {
    @Id
    @GeneratedValue
    private long id;

    @JsonIgnore
    @Version
    private long version;

    @NotEmpty
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany
    private Collection<Frage> fragen = new HashSet<>();

    private int anzahlFragen;

    public int getAnzahlFragen() {
        this.anzahlFragen = fragen.size();
        return anzahlFragen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Frage> getFragen() {
        return fragen;
    }

    public void setFragen(Collection<Frage> fragen) {
        this.fragen = fragen;
    }

    public long getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }
}
