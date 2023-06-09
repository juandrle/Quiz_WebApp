package de.hsrm.mi.web.projekt.ui.kategorie;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.hsrm.mi.web.projekt.entities.kategorie.Kategorie;
import de.hsrm.mi.web.projekt.services.kategorie.KategorieServiceImpl;
import jakarta.validation.Valid;

/**
 * @author Ilja Tkatchev
 */

@Controller
@SessionAttributes(names = { "kategorieformular", "kategorie" })
public class KategorieController {

    Logger logger = LoggerFactory.getLogger(KategorieController.class);

    @Autowired
    private KategorieServiceImpl katService;

    @ModelAttribute("kategorieformular")
    public void initKategorieFormular(Model m) {
        m.addAttribute("kategorieformular", new KategorieFormular());
    }

    @GetMapping("/kategorie/{katid}")
    public String getForm(Model m,
            @PathVariable("katid") long kategorieId,
            Locale locale) {

        m.addAttribute("sprache", locale.getDisplayLanguage());
        m.addAttribute("katid", kategorieId);

        KategorieFormular katForm = new KategorieFormular();
        Kategorie kategorie = new Kategorie();

        if (kategorieId > 0) {
            kategorie = katService.holeKategorieMitId(kategorieId).get();
            katForm.fromKategorie(kategorie);
        }

        m.addAttribute("kategorieformular", katForm);
        m.addAttribute("kategorie", kategorie);

        return "kategoriebearbeiten";
    }

    @PostMapping("/kategorie/{katid}")
    public String postForm(Model m,
            @PathVariable("katid") long kategorieId,
            @ModelAttribute("kategorie") Kategorie kategorie,
            @Valid @ModelAttribute("kategorieformular") KategorieFormular katForm,
            BindingResult formErrors) {

        if (formErrors.hasErrors()) {
            return "kategoriebearbeiten";
        }

        katForm.toKategorie(kategorie);

        try {
            kategorie = katService.speichereKategorie(kategorie);
            m.addAttribute("kategorie", kategorie);

            return "redirect:/kategorie/" + kategorie.getId();
        } catch (RuntimeException e) {
            String errorMessage = "Failed to save Kategorie: ";
            m.addAttribute("info", errorMessage + e.getMessage());
            logger.info(errorMessage + e.getMessage());

            return "kategoriebearbeiten";
        }
    }

    @GetMapping("/kategorie")
    public String getKategorien(Model m, Locale locale) {
        m.addAttribute("sprache", locale.getDisplayLanguage());
        m.addAttribute("katliste", katService.holeAlleKategorien());
        return "kategorieliste";
    }

    @GetMapping("/kategorie/{katid}/del")
    public String deleteKategorie(@PathVariable("katid") int kategorieId) {
        // Setzt in allen Fragen mit Kategorie-ID Kategorie auf null
        // Umgeht "foreign key constraint violation error"
        katService.holeKategorieMitId(kategorieId).get().getFragen().stream().forEach(f -> f.setKategorie(null));

        katService.loescheKategorieMitId(kategorieId);

        return "redirect:/kategorie";
    }
}
