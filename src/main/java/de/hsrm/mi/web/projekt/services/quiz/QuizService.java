package de.hsrm.mi.web.projekt.services.quiz;

import java.util.List;
import java.util.Optional;

import de.hsrm.mi.web.projekt.entities.quiz.Quiz;

public interface QuizService {
    List<Quiz> holeAlleQuizes();

    public Optional<Quiz> holeQuizMitId(long id);

    Quiz speichereQuiz(Quiz q);

    void loescheQuizMitId(long id);
}
