package de.hsrm.mi.web.projekt.entities.benutzer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BenutzerRepository extends JpaRepository<Benutzer, String> {
    boolean existsByBenutzername(String benutzername);

}
