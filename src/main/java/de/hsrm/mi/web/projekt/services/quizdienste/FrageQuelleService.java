package de.hsrm.mi.web.projekt.services.quizdienste;

import java.util.List;

import de.hsrm.mi.web.projekt.entities.frage.Frage;

public interface FrageQuelleService {
    List<Frage> generiereNeueFragen(int n);
}
