package de.hsrm.mi.web.projekt.services.benutzer;

import java.util.Optional;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;

public interface BenutzerService {

    public Optional<Benutzer> holeBenutzerMitBenutzername(String benutzername);

    Benutzer speichereBenutzer(Benutzer b);

    boolean existiertMitBenutzername(String benutzername);

}
