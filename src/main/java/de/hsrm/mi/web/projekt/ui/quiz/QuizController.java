package de.hsrm.mi.web.projekt.ui.quiz;

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

import de.hsrm.mi.web.projekt.entities.quiz.Quiz;
import de.hsrm.mi.web.projekt.services.frage.FrageServiceImpl;
import de.hsrm.mi.web.projekt.services.quiz.QuizServiceImpl;
import jakarta.validation.Valid;

@Controller
@SessionAttributes(names ={ "quizformular", "quiz" })
public class QuizController {
    Logger logger = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    private QuizServiceImpl quizService;

    @Autowired
    private FrageServiceImpl frageService;

    @ModelAttribute("quizformular")
    public void initQuizFormular(Model m) {
        m.addAttribute("quizformular", new QuizFormular());
    }
    @GetMapping("/quiz")
    public String getQuizsammlung(Model m, Locale locale){
        m.addAttribute("sprache", locale.getDisplayLanguage());
        
        m.addAttribute("quizliste", quizService.holeAlleQuizes());

        return "quizliste";
    }

    @GetMapping("/quiz/{quiznr}/del")
    public String getDelQuizSammlung(Model m,
        @PathVariable("quiznr") int n) {
            quizService.loescheQuizMitId(n);

            return "redirect:/quiz";
        }

    @GetMapping("/quiz/{quiznr}")
    public String getForm(Model m,
            @PathVariable("quiznr") int n,
            Locale locale) {
        m.addAttribute("frageliste", frageService.holeAlleFragen());
        m.addAttribute("sprache", locale.getDisplayLanguage());
        m.addAttribute("quiznr", n);
        
        QuizFormular quizForm = new QuizFormular();
        Quiz quiz = new Quiz();

        if(n > 0) {
            if(!quizService.holeQuizMitId(n).isPresent()){
                quiz = quizService.holeQuizMitId(n).get();
                quizForm.fromQuiz(quiz);
        } else return "redirect:/quiz";
        }

        m.addAttribute("quizformular", quizForm);
        m.addAttribute("quiz", quiz);

        return "quizbearbeiten";
    }

    @PostMapping("/quiz/{quiznr}")
    public String postForm(Model m,
            @PathVariable("quiznr") int n,
            @ModelAttribute("quiz") Quiz quiz,
            @Valid @ModelAttribute("quizformular") QuizFormular quizForm,
            BindingResult formErrors) {
        if(formErrors.hasErrors()) {
            return "quizbearbeiten";
        }
        // Write data from QuizFormular to Quiz
        quizForm.toQuiz(quiz);

        try {
            quiz = quizService.speichereQuiz(quiz);
            m.addAttribute("quiz", quiz);

            return "redirect:/quiz";
        } catch (RuntimeException e) {
            String errorMessage = "Failed to save Quiz: ";
            m.addAttribute("info", errorMessage + e.getMessage());
            logger.info(errorMessage + e.getMessage());
            return "quizbearbeiten";
        }
            
                
    }
    
}
