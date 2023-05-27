package de.hsrm.mi.web.projekt.services.kategorie;

import java.util.List;
import java.util.Optional;

import de.hsrm.mi.web.projekt.entities.kategorie.Kategorie;

/**
 * @author Ilja Tkatchev
 */

public interface KategorieService {
    List<Kategorie> holeAlleKategorien();

    public Optional<Kategorie> holeKategorieMitId(long id);

    Kategorie speichereKategorie(Kategorie f);

    void loescheKategorieMitId(long id);

    boolean existiertMitName(String name);

    Optional<Kategorie> holeKategorieMitName(String name);
}
