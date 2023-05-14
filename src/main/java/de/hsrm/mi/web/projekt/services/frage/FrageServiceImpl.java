package de.hsrm.mi.web.projekt.services.frage;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.frage.Frage;
import de.hsrm.mi.web.projekt.entities.frage.FrageRepository;

@Service
public class FrageServiceImpl implements FrageService {
    Logger logger = LoggerFactory.getLogger(FrageServiceImpl.class);
    private FrageRepository frageRepo;

    @Autowired
    public FrageServiceImpl(FrageRepository frageRepo) {
        this.frageRepo = frageRepo;
    }

    @Override
    public List<Frage> holeAlleFragen() {
        logger.info("Hole alle Fragen.");
        return frageRepo.findAll(Sort.by("kategorie", "punkte"));
    }

    @Override
    public Optional<Frage> holeFrageMitId(long id) {
        logger.info("Hole Frage mit ID: " + id);

        Optional<Frage> f = frageRepo.findById(id);

        if (f.isPresent()) {
            logger.info("Frage mit ID " + id + " gefunden.");
        } else {
            logger.info("Frage mit ID " + id + " nicht gefunden.");
        }
        return f;
    }
    
    @Override
    public Frage speichereFrage(Frage f) {
        logger.info("Speichere Frage: " + f);
        Frage s = frageRepo.save(f);
        return s;
    }

    @Override
    public void loescheFrageMitId(long id) {
        logger.info("Lösche Frage mit ID: " + id);
        frageRepo.deleteById(id);
    }
}
