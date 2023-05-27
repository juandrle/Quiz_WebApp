
package de.hsrm.mi.web.projekt.services.kategorie;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.kategorie.Kategorie;
import de.hsrm.mi.web.projekt.entities.kategorie.KategorieRepository;

/**
 * @author Ilja Tkatchev
 */

@Service
public class KategorieServiceImpl implements KategorieService {
    Logger logger = LoggerFactory.getLogger(KategorieServiceImpl.class);
    private KategorieRepository katRepo;

    @Autowired
    public KategorieServiceImpl(KategorieRepository katRepo) {
        this.katRepo = katRepo;
    }

    @Override
    public List<Kategorie> holeAlleKategorien() {
        logger.info("Hole alle Kategorien.");
        return katRepo.findAll(Sort.by("name"));
    }

    @Override
    public Optional<Kategorie> holeKategorieMitId(long id) {
        logger.info("Hole Kategorie mit ID: " + id);

        Optional<Kategorie> k = katRepo.findById(id);

        if (k.isPresent()) {
            logger.info("Kategorie mit ID " + id + " gefunden.");
        } else {
            logger.info("Kategorie mit ID " + id + " nicht gefunden.");
        }
        return k;
    }

    @Override
    public Kategorie speichereKategorie(Kategorie f) {
        logger.info("Speichere Kategorie: " + f);
        Kategorie s = katRepo.save(f);
        return s;
    }

    @Override
    public void loescheKategorieMitId(long id) {
        logger.info("Lösche Kategorie mit ID: " + id);
        katRepo.deleteById(id);
    }

    @Override
    public boolean existiertMitName(String name) {
        if (katRepo.existsByName(name)) {
            logger.info("Kategorie mit Name " + name + " existiert bereits.");
            return true;
        }
        logger.info("Kategorie mit Name " + name + " existiert noch nicht.");
        return false;
    }

    @Override
    public Optional<Kategorie> holeKategorieMitName(String name) {
        logger.info("Hole Kategorie mit Name: " + name);

        Optional<Kategorie> k = katRepo.findByName(name);

        if (k.isPresent()) {
            logger.info("Kategorie mit Name " + name + " gefunden.");
        } else {
            logger.info("Kategorie mit Name " + name + " nicht gefunden.");
        }
        return k;
    }
}
