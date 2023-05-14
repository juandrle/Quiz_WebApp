package de.hsrm.mi.web.projekt.entities.frage;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import validators.Verschieden;

@Entity
public class Frage implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    @NotNull
    @NotBlank
    private String kategorie;

    @NotBlank
    @Size(min = 5, max = 80)
    private String frage;

    @NotBlank
    @Size(min = 1, max = 80)
    private String richtigeAntwort;

    @NotNull
    @Min(0)
    @Max(17)
    private Integer punkte;

    @ElementCollection
    @NotEmpty(message = "{frageformular.fehler.falschantworten.leer}")
    @Verschieden(message = "{frageformular.fehler.falschantworten.duplikate}")
    private List<String> falschantworten;

    public long getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public String getFrage() {
        return frage;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

    public String getRichtigeAntwort() {
        return richtigeAntwort;
    }

    public void setRichtigeAntwort(String richtigeAntwort) {
        this.richtigeAntwort = richtigeAntwort;
    }

    public Integer getPunkte() {
        return punkte;
    }

    public void setPunkte(Integer punkte) {
        this.punkte = punkte;
    }

    public List<String> getFalschantworten() {
        return falschantworten;
    }

    public void setFalschantworten(List<String> falschantworten) {
        this.falschantworten = falschantworten;
    }
}
