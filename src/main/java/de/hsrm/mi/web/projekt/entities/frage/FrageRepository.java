package de.hsrm.mi.web.projekt.entities.frage;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface FrageRepository extends JpaRepository<Frage, Long> {

    public List<Frage> findByFragetext(String fragetext);
}
