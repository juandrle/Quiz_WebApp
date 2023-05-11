package de.hsrm.mi.web.projekt.ui.frage;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import validators.Verschieden;

public class FrageFormular {
    private List<String> kategorien;

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

    @NotEmpty(message = "{frageformular.fehler.falschantworten.leer}")
    @Verschieden(message = "{frageformular.fehler.falschantworten.duplikate}")
    private List<String> falschantworten;

    @NotNull
    @Size(max = 80)
    private String neueFalschantwort;

    public String getNeueFalschantwort() {
        return neueFalschantwort;
    }

    public void setNeueFalschantwort(String neu) {
        this.neueFalschantwort = neu;
    }

    public List<String> getFalschantworten() {
        return falschantworten;
    }

    public FrageFormular() {
        this.kategorien = new ArrayList<>();
        this.falschantworten = new ArrayList<>();

        kategorien.add(null);
        kategorien.add("Allgemeines");
        kategorien.add("Zahlen");
        kategorien.add("Sachen");
        kategorien.add("Orte");
        kategorien.add("Ereignisse");
    }

    public List<String> getKategorien() {
        return kategorien;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String katSelected) {
        this.kategorie = katSelected;
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

    public void setRichtigeAntwort(String antwortRichtig) {
        this.richtigeAntwort = antwortRichtig;
    }

    public Integer getPunkte() {
        return punkte;
    }

    public void setPunkte(Integer punkte) {
        this.punkte = punkte;
    }
}
