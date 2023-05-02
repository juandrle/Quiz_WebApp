package de.hsrm.mi.web.projekt.ui.frage;

import java.util.ArrayList;
import java.util.List;

public class FrageFormular {
    private List<String> kategorien;
    private String katSelected;
    private String frage;
    private String richtig;
    private int punkte;

    public FrageFormular() {
        this.kategorien = new ArrayList<>();

        kategorien.add("");
        kategorien.add("Allgemeines");
        kategorien.add("Zahlen");
        kategorien.add("Sachen");
        kategorien.add("Orte");
        kategorien.add("Ereignisse");
    }

    public List<String> getKategorien() {
        return kategorien;
    }

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

    public int getPunkte() {
        return punkte;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }
}
