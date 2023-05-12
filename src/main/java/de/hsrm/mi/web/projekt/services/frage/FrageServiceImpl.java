package de.hsrm.mi.web.projekt.services.frage;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.frage.Frage;
import de.hsrm.mi.web.projekt.entities.frage.FrageRepository;

@Service
public class FrageServiceImpl implements FrageService {
    private FrageRepository frageRepo;

    @Autowired 
    public FrageServiceImpl(FrageRepository frageRepo) {
        this.frageRepo = frageRepo;
    }

    @Override
    public List<Frage> holeAlleFragen() {
        return frageRepo.findAll(Sort.by("kategorie", "punkte"));
    }

    @Override
    public Optional<Frage> holeFrageMitId(long id) {
        return frageRepo.findById(id);
    }

    @Override
    public Frage speichereFrage(Frage f) {
        return frageRepo.save(f);
    }

    @Override
    public void loescheFrageMitId(long id) {
        frageRepo.deleteById(id);
    }
    
}
