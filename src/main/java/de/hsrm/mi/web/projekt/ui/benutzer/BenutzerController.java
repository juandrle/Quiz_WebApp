package de.hsrm.mi.web.projekt.ui.benutzer;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.services.benutzer.BenutzerServiceImpl;
import jakarta.validation.Valid;


@Controller
@SessionAttributes(names = { "benutzerformular", "benutzer" })
public class BenutzerController {
    Logger logger = LoggerFactory.getLogger(BenutzerController.class);
    @Autowired
    private BenutzerServiceImpl benutzerService;

    @ModelAttribute("benutzerformular")
    public void initBenutzerFormular(Model m) {
        m.addAttribute("benutzerformular", new BenutzerFormular());
    }
    @ModelAttribute("benutzer")
    public void initFrageFormular(Model m) {
        m.addAttribute("benutzer", new Benutzer());
    }

    @GetMapping("/registrieren")
    public String getRegister(Model m, Locale locale) {
        m.addAttribute("sprache", locale.getDisplayLanguage());
        return "registrieren";
    }

    @PostMapping("/registrieren")
    public String postForm(Model m,
            @ModelAttribute("benutzer") Benutzer benutzer,
            @Valid @ModelAttribute("benutzerformular") BenutzerFormular userForm,
            BindingResult formErrors) {
            if (formErrors.hasErrors()) {
            
            return "registrieren";
        }
            
            userForm.toBenutzer(benutzer);
            logger.info(benutzer.getBenutzername());
            try {

                if(benutzerService.existiertMitBenutzername(benutzer.getBenutzername()))
                throw new Exception("Benutzername " + userForm.getBenutzername() + " ist bereits vergeben");
                if(!userForm.getPasswort().equals(userForm.getPasswortwiederholung()))
                throw new Exception("Passwörter stimmen nicht überein!");

                benutzerService.speichereBenutzer(benutzer);
                m.addAttribute("benutzer", benutzer);
            } catch (Exception e) {
                m.addAttribute("info", e.getMessage());
                logger.info(e.getMessage());
                return "registrieren";
            }
    
            return "frageliste";
}
}
