package de.hsrm.mi.web.projekt.services.quiz;

import java.util.List;
import java.util.Optional;

import de.hsrm.mi.web.projekt.entities.quiz.Quiz;

public interface QuizService {
    List<Quiz> holeAlleQuizzes();

    public Optional<Quiz> holeQuizMitId(long id);

    Quiz speichereQuiz(Quiz f);

    void loescheQuizMitId(long id);
}
