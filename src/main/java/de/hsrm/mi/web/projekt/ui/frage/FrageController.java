package de.hsrm.mi.web.projekt.ui.frage;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(names = { "formularMap" })
@RequestMapping("/frage")
public class FrageController {
    final int maxfalsch = 4;
    Logger logger = LoggerFactory.getLogger(FrageController.class);

    @ModelAttribute("formularMap")
    public Map<String, FrageFormular> initFormularMap() {
        return new HashMap<>();
    }

    @PostMapping("/{frageNr}")
    public String handleFormSubmit(
            @ModelAttribute("formularMap") Map<String, FrageFormular> formularMap,
            @PathVariable("frageNr") String fnr,
            @ModelAttribute("frageFormular") FrageFormular formular) {
        formularMap.put(fnr, formular);
        return "redirect:/frage/" + fnr;
    }

    @GetMapping("/{frageNr}")
    public String getForm(
            Model m,
            @PathVariable("frageNr") String fnr,
            @ModelAttribute("formularMap") Map<String, FrageFormular> formularMap) {
        FrageFormular formular = formularMap.get(fnr);
        if (formular == null) {
            formular = new FrageFormular();
            formular.setFrage("");
            formular.setPunkte(0);
        }
        m.addAttribute("frageNr", fnr);
        m.addAttribute("maxFalsch", Integer.toString(maxfalsch));
        m.addAttribute("frageFormular", formular);
        return "fragebearbeiten";
    }
}
