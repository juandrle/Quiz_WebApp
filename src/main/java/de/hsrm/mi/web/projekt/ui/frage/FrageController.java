package de.hsrm.mi.web.projekt.ui.frage;

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

import de.hsrm.mi.web.projekt.entities.frage.Frage;
import de.hsrm.mi.web.projekt.services.frage.FrageServiceImpl;
import de.hsrm.mi.web.projekt.services.kategorie.KategorieServiceImpl;
import de.hsrm.mi.web.projekt.services.quizdienste.FrageQuelleServiceTheTriviaAPI;
import jakarta.validation.Valid;

@Controller
@SessionAttributes(names = { "frageformular", "frage" })
public class FrageController {
    private static final int MAXFALSCH = 4;
    Logger logger = LoggerFactory.getLogger(FrageController.class);

    @Autowired
    private FrageServiceImpl frageService;

    @Autowired
    private KategorieServiceImpl katService;

    @Autowired
    private FrageQuelleServiceTheTriviaAPI triviaService;

    @ModelAttribute("frageformular")
    public void initFrageFormular(Model m) {
        m.addAttribute("frageformular", new FrageFormular());
    }

    @GetMapping("/frage")
    public String getFragesammlung(Model m, Locale locale) {
        m.addAttribute("sprache", locale.getDisplayLanguage());

        m.addAttribute("frageliste", frageService.holeAlleFragen());

        return "frageliste";
    }

    @GetMapping("/frage/{fragenr}")
    public String getForm(Model m,
            @PathVariable("fragenr") int n,
            Locale locale) {

        m.addAttribute("sprache", locale.getDisplayLanguage());
        m.addAttribute("fragenr", n);
        m.addAttribute("MAXFALSCH", MAXFALSCH);

        FrageFormular frageForm = new FrageFormular();
        frageForm.setKategorien(katService.holeAlleKategorien());
        Frage frage = new Frage();

        if (n > 0) {
            frage = frageService.holeFrageMitId(n).get();
            frageForm.fromFrage(frage);
        }

        m.addAttribute("frageformular", frageForm);
        m.addAttribute("frage", frage);

        return "fragebearbeiten";
    }

    @PostMapping("/frage/{fragenr}")
    public String postForm(Model m,
            @PathVariable("fragenr") int n,
            @ModelAttribute("frage") Frage frage,
            @Valid @ModelAttribute("frageformular") FrageFormular frageForm,
            BindingResult formErrors) {

        frageForm.getFalschantworten().removeIf(s -> s.equals(""));

        String neu = frageForm.getNeueFalschantwort();
        if (neu != null && !neu.isBlank() && !frageForm.getFalschantworten().contains(neu)) {
            frageForm.getFalschantworten().add(frageForm.getNeueFalschantwort());
            frageForm.setNeueFalschantwort(null);
        }

        m.addAttribute("MAXFALSCH", MAXFALSCH);

        if (formErrors.hasErrors()) {
            return "fragebearbeiten";
        }

        // Write data from FrageFormular to Frage
        frageForm.toFrage(frage);

        try {
            // Save Frage in database and set session attribute "frage" to returned Frage
            // Reason: optimistic lock -> @Version, conflicts only handled upon occurance
            frage = frageService.speichereFrage(frage);
            m.addAttribute("frage", frage);

            return "redirect:/frage/" + frage.getId();
        } catch (RuntimeException e) {
            String errorMessage = "Failed to save Frage: ";
            m.addAttribute("info", errorMessage + e.getMessage());
            logger.info(errorMessage + e.getMessage());

            return "fragebearbeiten";
        }
    }

    @GetMapping("/frage/{fragenr}/del")
    public String deleteFrage(Model m,
            @PathVariable("fragenr") int n) {

        try {
            frageService.loescheFrageMitId(n);
            return "redirect:/frage";
        } catch (RuntimeException e) {
            String errorMessage = "Failed to delete Frage: ";
            m.addAttribute("info", errorMessage + e.getMessage());
            logger.info(errorMessage + e.getMessage());
        }
        return "frageliste";
    }

    @GetMapping("/frage/quizdienst")
    public String getNeueFragen(Model m) {
        triviaService.generiereNeueFragen(3);
        return "redirect:/frage";
    }
}
