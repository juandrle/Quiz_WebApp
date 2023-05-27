package de.hsrm.mi.web.projekt.entities.kategorie;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ilja Tkatchev
 */

public interface KategorieRepository extends JpaRepository<Kategorie, Long> {
    boolean existsByName(String name);

    Optional<Kategorie> findByName(String name);
}
