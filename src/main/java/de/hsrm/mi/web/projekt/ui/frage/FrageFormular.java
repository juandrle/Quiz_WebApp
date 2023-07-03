package de.hsrm.mi.web.projekt.ui.frage;

import java.util.ArrayList;
import java.util.List;

import de.hsrm.mi.web.projekt.entities.frage.Frage;
import de.hsrm.mi.web.projekt.entities.kategorie.Kategorie;
import de.hsrm.mi.web.projekt.validators.Verschieden;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FrageFormular {
 
    private List<Kategorie> kategorien;

    @NotNull
    private Kategorie kategorie;

    @NotBlank
    @Size(min = 5, max = 80)
    private String fragetext;

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
        this.kategorie = null;
    }

    public void toFrage(Frage f) {
        // befuellt f mit Formularinhalt
        f.setKategorie(kategorie);
        f.setFragetext(fragetext);
        f.setRichtigeAntwort(richtigeAntwort);
        f.setPunkte(punkte);
        f.setFalschantworten(falschantworten);
    }

    public void setKategorien(List<Kategorie> kategorien) {
        this.kategorien = kategorien;
    }

    public void fromFrage(Frage f) {
        // fuellt Formularinhalt aus f
        this.kategorie = f.getKategorie();
        this.fragetext = f.getFragetext();
        this.richtigeAntwort = f.getRichtigeAntwort();
        this.punkte = f.getPunkte();
        this.falschantworten = f.getFalschantworten();
    }

    public List<Kategorie> getKategorien() {
        return kategorien;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public String getFragetext() {
        return fragetext;
    }

    public void setFragetext(String frage) {
        this.fragetext = frage;
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
