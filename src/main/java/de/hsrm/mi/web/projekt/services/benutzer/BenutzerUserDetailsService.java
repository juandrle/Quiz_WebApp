package de.hsrm.mi.web.projekt.services.benutzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;

@Service
public class BenutzerUserDetailsService implements UserDetailsService{
    
    @Autowired
    private BenutzerServiceImpl benutzerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Benutzer user = benutzerService.holeBenutzerMitBenutzername(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));

            return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPasswort())
                .roles(user.getRolle())
                .build();
    }
    
}
