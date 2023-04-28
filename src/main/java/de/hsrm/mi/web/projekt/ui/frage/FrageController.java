package de.hsrm.mi.web.projekt.ui.frage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class FrageController {
    
    @GetMapping
    public String frage_get() {
        return "fragebearbeiten";
    }
}
