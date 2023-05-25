package de.hsrm.mi.web.projekt.ui.kategorie;

import de.hsrm.mi.web.projekt.entities.kategorie.Kategorie;
import jakarta.validation.constraints.NotEmpty;

/**
 * @author Ilja Tkatchev
 */

public class KategorieFormular {
    @NotEmpty
    private String name;

    @NotEmpty
    private String beschreibung;

    public void toKategorie(Kategorie k) {
        k.setName(name);
        k.setBeschreibung(beschreibung);
    }

    public void fromKategorie(Kategorie k) {
        this.name = k.getName();
        this.beschreibung = k.getBeschreibung();
    }

    public String getName() {
        return name;
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
}
