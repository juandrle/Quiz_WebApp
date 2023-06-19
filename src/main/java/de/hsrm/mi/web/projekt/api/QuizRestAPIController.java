package de.hsrm.mi.web.projekt.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.web.projekt.entities.frage.Frage;
import de.hsrm.mi.web.projekt.entities.quiz.Quiz;
import de.hsrm.mi.web.projekt.services.frage.FrageServiceImpl;
import de.hsrm.mi.web.projekt.services.quiz.QuizServiceImpl;

@RestController
@RequestMapping("/api/quiz")
public class QuizRestAPIController {
    Logger logger = LoggerFactory.getLogger(QuizRestAPIController.class);

    @Autowired
    private QuizServiceImpl quizService;

    @Autowired
    private FrageServiceImpl frageService;

    private record QuizDTOshort(long id, String name, int anzahlFragen) {
    };

    private record QuizDTO(long id, String name, List<FrageDTO> fragen, int gesamtpunkte) {
    };

    private record FrageDTO(long id, String katname, String fragetext, List<String> alleAntworten, int punkte) {
    };

    private record AntwortDTO(long fid, String antwort) {
    };

    private record BeantwortetesQuizDTO(long qid, List<AntwortDTO> antworten) {
    };

    private record ErgebnisDTO(long fid, boolean richtig) {
    };

    private record QuizErgebnisDTO(long qid, List<ErgebnisDTO> ergebnisse) {
    };

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<QuizDTOshort>> getQuizzes() {
        List<Quiz> quizzes = quizService.holeAlleQuizzes();
        QuizDTOshort quizDTO;
        List<QuizDTOshort> quizzesDTO = new ArrayList<>();

        for (Quiz q : quizzes) {
            quizDTO = new QuizDTOshort(q.getId(), q.getName(), q.getAnzahlFragen());
            quizzesDTO.add(quizDTO);
        }

        // works but adds backslash to all double quotes
        /*
         * List<String> quizzesJSON = new ArrayList<>();
         * ObjectMapper objMapper = new ObjectMapper();
         * 
         * for (Quiz q : quizzes) {
         * try {
         * String stringJSON = objMapper.writeValueAsString(q);
         * quizzesJSON.add(stringJSON);
         * } catch (JsonProcessingException e) {
         * logger.info("Bei der Serialisierung von Quiz " + q +
         * " in JSON ist ein Fehler aufgetreten.");
         * e.printStackTrace();
         * }
         * }
         */
        return ResponseEntity.ok(quizzesDTO);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuizDTO> getQuizById(@PathVariable long id) {
        Optional<Quiz> quizOpt = quizService.holeQuizMitId(id);

        if (!quizOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Quiz quiz = quizOpt.get();

        FrageDTO frageDTO;
        QuizDTO quizDTO;
        List<FrageDTO> fragenDTO = new ArrayList<>();

        for (Frage f : quiz.getFragen()) {
            List<String> alleAntworten = new ArrayList<>();
            alleAntworten.add(f.getRichtigeAntwort());
            alleAntworten.addAll(f.getFalschantworten());
            Collections.shuffle(alleAntworten);

            // FetchType.LAZY bei @ManyToOne für Entity Frage, damit nicht alle "fragen" der
            // von Kategorie
            // direkt mitgeladen werden, da hier nur Name gebraucht wird
            frageDTO = new FrageDTO(f.getId(), f.getKategorie().getName(), f.getFragetext(), alleAntworten,
                    f.getPunkte());

            fragenDTO.add(frageDTO);
        }

        int gesamtpunkte = fragenDTO.stream().mapToInt(f -> f.punkte()).sum();

        quizDTO = new QuizDTO(quiz.getId(), quiz.getName(), fragenDTO, gesamtpunkte);

        return ResponseEntity.ok(quizDTO);
    }

    @PostMapping(value = "/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuizErgebnisDTO> evaluateQuiz(@RequestBody BeantwortetesQuizDTO beantwortetesQuiz) {
        Optional<Quiz> quizOpt = quizService.holeQuizMitId(beantwortetesQuiz.qid());

        if (!quizOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Quiz quiz = quizOpt.get();

        List<ErgebnisDTO> ergebnisse = new ArrayList<>();
        QuizErgebnisDTO quizErgebnis;
        boolean currRichtig;

        for (AntwortDTO antwort : beantwortetesQuiz.antworten()) {
            currRichtig = frageService.pruefeAntwort(antwort.fid(), antwort.antwort());
            ergebnisse.add(new ErgebnisDTO(antwort.fid(), currRichtig));
        }

        quizErgebnis = new QuizErgebnisDTO(quiz.getId(), ergebnisse);

        return ResponseEntity.ok(quizErgebnis);
    }
}
