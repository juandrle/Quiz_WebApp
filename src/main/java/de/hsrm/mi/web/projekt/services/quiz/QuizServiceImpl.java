package de.hsrm.mi.web.projekt.services.quiz;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import de.hsrm.mi.web.projekt.entities.quiz.Quiz;
import de.hsrm.mi.web.projekt.entities.quiz.QuizRepository;

@Service
public class QuizServiceImpl implements QuizService {
    Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);
    private QuizRepository quizRepo;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepo) {
        this.quizRepo = quizRepo;
    }
    @Override
    public List<Quiz> holeAlleQuizes() {
        logger.info("Hole alle Quizes.");
        return quizRepo.findAll(Sort.by("kategorie", "punkte"));
    }

    @Override
    public Optional<Quiz> holeQuizMitId(long id) {
        logger.info("Hole Quiz mit ID: " + id);

        Optional<Quiz> q = quizRepo.findById(id);

        if (q.isPresent()) {
            logger.info("Quiz mit ID " + id + " gefunden.");
        } else {
            logger.info("Quiz mit ID " + id + " nicht gefunden.");
        }
        return q;
    }

    @Override
    public Quiz speichereQuiz(Quiz q) {
        logger.info("Speichere Quiz: " + q);
        Quiz s = quizRepo.save(q);
        return s;
    }

    @Override
    public void loescheQuizMitId(long id) {
        logger.info("Lösche Quiz mit ID: " + id);
        quizRepo.deleteById(id);
    }
    
}
