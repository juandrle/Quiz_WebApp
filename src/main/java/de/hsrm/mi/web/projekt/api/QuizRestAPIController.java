package de.hsrm.mi.web.projekt.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.web.projekt.services.quiz.QuizService;

@RestController
@RequestMapping("/api/quiz")
public class QuizRestAPIController {
    QuizService quizService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllQuizes(){
        return null;
    }
    
}
