package de.hsrm.mi.web.projekt.ui.frage;

import java.util.ArrayList;
import java.util.List;

import de.hsrm.mi.web.projekt.entities.frage.Frage;
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

    public FrageFormular() {
        this.kategorien = new ArrayList<>();
        this.falschantworten = new ArrayList<>();
        this.neueFalschantwort = null;

        kategorien.add(null);
        kategorien.add("Allgemeines");
        kategorien.add("Zahlen");
        kategorien.add("Sachen");
        kategorien.add("Orte");
        kategorien.add("Ereignisse");
    }

    public void toFrage(Frage f) {
        // befuellt f mit Formularinhalt
        f.setKategorie(kategorie);
        f.setFrage(frage);
        f.setRichtigeAntwort(richtigeAntwort);
        f.setPunkte(punkte);
        f.setFalschantworten(falschantworten);
    }

    public void fromFrage(Frage f) {
        // fuellt Formularinhalt aus f
        this.kategorie = f.getKategorie();
        this.frage = f.getFrage();
        this.richtigeAntwort = f.getRichtigeAntwort();
        this.punkte = f.getPunkte();
        this.falschantworten = f.getFalschantworten();
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

    public String getNeueFalschantwort() {
        return neueFalschantwort;
    }

    public void setNeueFalschantwort(String neu) {
        this.neueFalschantwort = neu;
    }

    public List<String> getFalschantworten() {
        return falschantworten;
    }
}
