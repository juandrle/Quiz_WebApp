package de.hsrm.mi.web.projekt.ui.frage;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes(names = { "frageformular" })
public class FrageController {
    Logger logger = LoggerFactory.getLogger(FrageController.class);
    private static final int MAXFALSCH = 4;

    @ModelAttribute("frageformular")
    public void initFormular(Model m) {
        m.addAttribute("frageformular", new FrageFormular());
    }

    @GetMapping("/frage/{fragenr}")
    public String getForm(Model m, @PathVariable("fragenr") String frageNr, Locale locale) {
        m.addAttribute("sprache", locale.getDisplayLanguage());
        m.addAttribute("fragenr", frageNr);
        m.addAttribute("MAXFALSCH", MAXFALSCH);

        return "fragebearbeiten";
    }

    @PostMapping("/frage/{fragenr}")
    public String postForm(Model m, @PathVariable("fragenr") String frageNr,
            @Valid @ModelAttribute("frageformular") FrageFormular frageForm,
            BindingResult formErrors) {

        m.addAttribute("MAXFALSCH", MAXFALSCH);

        frageForm.getFalschantworten().removeIf(s -> s.equals(""));

        String neu = frageForm.getNeueFalschantwort();
        if (neu != null && !neu.isBlank() && !frageForm.getFalschantworten().contains(neu)) {
            frageForm.getFalschantworten().add(frageForm.getNeueFalschantwort());
            frageForm.setNeueFalschantwort(null);
        }

        return "fragebearbeiten";
    }
}
