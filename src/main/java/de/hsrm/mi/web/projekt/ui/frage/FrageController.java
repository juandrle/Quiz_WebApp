package de.hsrm.mi.web.projekt.ui.frage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(names = { "frageFormular" })
public class FrageController {
    Logger logger = LoggerFactory.getLogger(FrageController.class);

    @ModelAttribute("frageFormular")
    public void initFormular(Model m) {
        m.addAttribute("frageFormular", new FrageFormular());
    }

    @GetMapping("/frage/{frageNr}")
    public String getForm(Model m, @PathVariable("frageNr") String fnr) {
        m.addAttribute("frageNr", fnr);
        return "fragebearbeiten";
    }
}
