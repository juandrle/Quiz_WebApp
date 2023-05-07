package de.hsrm.mi.web.projekt.ui.frage;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import validators.Verschieden;
@Validated
public class FrageFormular {
    private List<String> kategorien;
    
    @NotBlank
    private String katSelected;
    @NotBlank
    @Size(min = 5, max = 80)
    private String frage;
    @NotBlank
    @Size(min = 1, max = 80)
    private String richtig;
    @Min(0)
    @Max(18)
    @NotNull
    private Integer punkte;
    @Valid
    @Verschieden
    private List<String> antworten;
    @NotBlank
    private String neu;

    public List<String> getAntworten() {
        return antworten;
    }

    public String getNeu() {
        return neu;
    }
    public void setNeu(String neu) {
        this.neu = neu;
    }

    public FrageFormular() {
        this.kategorien = new ArrayList<>();
        this.antworten = new ArrayList<>();

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
    @NotNull
    public String getKatSelected() {
        return katSelected;
    }

    public void setKatSelected(String katSelected) {
        this.katSelected = katSelected;
    }

    public String getFrage() {
        return frage;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

    public String getRichtig() {
        return richtig;
    }

    public void setRichtig(String antwortRichtig) {
        this.richtig = antwortRichtig;
    }

    public Integer getPunkte() {
        return punkte;
    }

    public void setPunkte(Integer punkte) {
        this.punkte = punkte;
    }
}
