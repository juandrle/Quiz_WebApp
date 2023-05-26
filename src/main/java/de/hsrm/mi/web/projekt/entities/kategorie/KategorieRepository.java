package de.hsrm.mi.web.projekt.entities.kategorie;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


/**
 * @author Ilja Tkatchev
 */

public interface KategorieRepository extends JpaRepository<Kategorie, Long> {

    public List<Kategorie> findByName(String name);
}
