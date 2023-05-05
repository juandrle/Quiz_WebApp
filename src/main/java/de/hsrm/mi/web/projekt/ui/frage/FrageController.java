package de.hsrm.mi.web.projekt.ui.frage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes(names = { "frageFormular" })
public class FrageController {
    Logger logger = LoggerFactory.getLogger(FrageController.class);
    final int MAXFALSCH = 4;

    @ModelAttribute("frageFormular")
    public void initFormular(Model m) {
        m.addAttribute("frageFormular", new FrageFormular());
    }

    @GetMapping("/frage/{frageNr}")
    public String getForm(Model m, @PathVariable("frageNr") String fnr) {
        m.addAttribute("frageNr", fnr);
        m.addAttribute("MAXFALSCH", MAXFALSCH);

        return "fragebearbeiten";
    }

    @PostMapping("/frage/{frageNr}")
    public String postForm(@PathVariable("frageNr") String fnr,
            @Valid @ModelAttribute("frageFormular") FrageFormular frForm, 
            BindingResult formErrors,
            @RequestParam(name = "neu", required = false) String neu) {

        frForm.getAntworten().removeIf(s -> s.equals(""));

        if (neu != null && !neu.isEmpty()) {
            frForm.getAntworten().add(neu);
        }

        return "fragebearbeiten";
    }
}
