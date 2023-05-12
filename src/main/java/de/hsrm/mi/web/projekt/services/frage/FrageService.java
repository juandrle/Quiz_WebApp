package de.hsrm.mi.web.projekt.services.frage;

import java.util.List;
import java.util.Optional;

import de.hsrm.mi.web.projekt.entities.frage.Frage;

public interface FrageService {
    List<Frage> holeAlleFragen();

    public Optional<Frage> holeFrageMitId(long id);

    Frage speichereFrage(Frage f);

    void loescheFrageMitId(long id);
}
