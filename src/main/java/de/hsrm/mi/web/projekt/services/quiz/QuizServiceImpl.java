package de.hsrm.mi.web.projekt.services.quiz;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.entities.quiz.Quiz;
import de.hsrm.mi.web.projekt.entities.quiz.QuizRepository;

@Service
public class QuizServiceImpl implements QuizService {
    Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);
    private QuizRepository quizRepo;

    
    public QuizServiceImpl(QuizRepository katRepo) {
        this.quizRepo = katRepo;
    }

    @Override
    public List<Quiz> holeAlleQuizzes() {
        logger.info("Hole alle Quizzes.");
        return quizRepo.findAll(Sort.by("name"));
    }

    @Override
    public Optional<Quiz> holeQuizMitId(long id) {
        logger.info("Hole Quiz mit ID: " + id);

        Optional<Quiz> f = quizRepo.findById(id);

        if (f.isPresent()) {
            logger.info("Quiz mit ID " + id + " gefunden.");
        } else {
            logger.info("Quiz mit ID " + id + " nicht gefunden.");
        }
        return f;
    }

    @Override
    public Quiz speichereQuiz(Quiz f) {
        logger.info("Speichere Quiz: " + f);
        Quiz s = quizRepo.save(f);
        return s;
    }

    @Override
    public void loescheQuizMitId(long id) {
        logger.info("Lösche Quiz mit ID: " + id);
        quizRepo.deleteById(id);
    }

}
