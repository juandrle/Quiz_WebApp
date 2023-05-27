package de.hsrm.mi.web.projekt.services.quizdienste;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import de.hsrm.mi.web.projekt.entities.frage.Frage;
import de.hsrm.mi.web.projekt.entities.kategorie.Kategorie;
import de.hsrm.mi.web.projekt.services.frage.FrageServiceImpl;
import de.hsrm.mi.web.projekt.services.kategorie.KategorieServiceImpl;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Service
public class FrageQuelleServiceTheTriviaAPI implements FrageQuelleService {

    private FrageServiceImpl frageService;
    private KategorieServiceImpl katService;
    private Validator validator;

    @Autowired
    public FrageQuelleServiceTheTriviaAPI(FrageServiceImpl frageService, KategorieServiceImpl katService,
            Validator validator) {
        this.frageService = frageService;
        this.katService = katService;
        this.validator = validator;
    }

    @Override
    public List<Frage> generiereNeueFragen(int n) {
        final int DEFAULT_PUNKTE = 1;

        Kategorie kategorie;
        Frage frage;

        Set<ConstraintViolation<Frage>> frageViolations;
        Set<ConstraintViolation<Kategorie>> katViolations;

        record TriviaDTO(String category, String correctAnswer, List<String> incorrectAnswers,
                String question) {
        }
        ;

        WebClient client = WebClient.create("https://the-trivia-api.com/api");

        List<TriviaDTO> responses = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/questions")
                        .queryParam("limit", n).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToFlux(TriviaDTO.class)
                .collectList()
                .block();

        List<Frage> triviaFragen = new ArrayList<>();

        if (responses != null) {
            for (TriviaDTO r : responses) {
                if (!frageService.existiertMitFragetext(r.question())) {

                    if (!katService.existiertMitName(r.category())) {
                        kategorie = new Kategorie();
                        kategorie.setName(r.category());
                        kategorie.setBeschreibung(r.category());
                    } else {
                        kategorie = katService.holeKategorieMitName(r.category()).get();
                    }

                    frage = new Frage();
                    frage.setFragetext(r.question);
                    frage.setRichtigeAntwort(r.correctAnswer());
                    frage.setFalschantworten(r.incorrectAnswers());
                    frage.setPunkte(DEFAULT_PUNKTE);

                    // bidirektionale Verbindung
                    frage.setKategorie(kategorie);
                    kategorie.getFragen().add(frage);

                    frageViolations = validator.validate(frage);
                    katViolations = validator.validate(kategorie);

                    if (katViolations.isEmpty() && frageViolations.isEmpty()) {
                        katService.speichereKategorie(kategorie);
                        triviaFragen.add(frage);
                    }
                }
            }
            return frageService.speichereAlleFragen(triviaFragen);
        }

        return Collections.emptyList();
    }

}
