package de.hsrm.mi.web.projekt.services.benutzer;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.entities.benutzer.BenutzerRepository;

@Service
public class BenutzerServiceImpl implements BenutzerService {
    Logger logger = LoggerFactory.getLogger(BenutzerServiceImpl.class);

    private BenutzerRepository benutzerRepo;

    public BenutzerServiceImpl(BenutzerRepository benutzerRepo) {
        this.benutzerRepo = benutzerRepo;
    }

    @Override
    public Optional<Benutzer> holeBenutzerMitBenutzername(String benutzername) {
        logger.info("Hole Benutzer mit benutzernamen" + benutzername);
        Optional<Benutzer> b = benutzerRepo.findById(benutzername);
        if (b.isPresent()) {
            logger.info("Benutzer mit benutzernamen " + benutzername + " gefunden.");
        } else {
            logger.info("Benutzer mit benutzernamen " + benutzername + " nicht gefunden.");
        }
        return b;

    }

    @Override
    public Benutzer speichereBenutzer(Benutzer nutzer) {
        logger.info("speichere Nutzer: " + nutzer);
        Benutzer b = benutzerRepo.save(nutzer);
        return b;
    }

    @Override
    public boolean existiertMitBenutzername(String benutzername) {
        logger.info("überprüfe ob Nutzer mit " + benutzername + "existiert");
        if (benutzerRepo.existsById(benutzername)) {
            logger.info("existiert");
            return true;
        } else {
            logger.info("existiert noch nicht");
            return false;
        }
    }

}
