package de.hsrm.mi.web.projekt.services.quizdienste;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import de.hsrm.mi.web.projekt.entities.frage.Frage;
import de.hsrm.mi.web.projekt.entities.kategorie.Kategorie;
import de.hsrm.mi.web.projekt.services.frage.FrageServiceImpl;
import de.hsrm.mi.web.projekt.services.kategorie.KategorieServiceImpl;

@Service
public class FrageQuelleServiceTheTriviaAPI implements FrageQuelleService {
    @Autowired
    private FrageServiceImpl frageService;
    @Autowired
    private KategorieServiceImpl katService;
    @Override
    public List<Frage> generiereNeueFragen(int n) {
        record TriviaAPIResponse(String category, String id, String correctAnswer, List<String> incorrectAnswers,String question, List<String> tags, String type, String difficulty, List<String> regions, boolean isNiche) {}
        WebClient webClient = WebClient.create("https://the-trivia-api.com");
        List<TriviaAPIResponse> allResponses = webClient.get()
        .uri(UriBuilder -> UriBuilder
            .path("/api/questions")
            .queryParam("limit", n)
            .build()).accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(TriviaAPIResponse.class)
            .collectList()
            .block();
            List<Frage> fragen = new ArrayList<Frage>();
            for(TriviaAPIResponse response: allResponses){
                if (frageService.isInList(response.question)
                || response.question.length() > 80 || response.question.length() < 5) continue;
                Frage frage = new Frage();
                frage.setFalschantworten(response.incorrectAnswers);
                frage.setFragetext(response.question);
                frage.setPunkte(1);
                frage.setRichtigeAntwort(response.correctAnswer);
                
                // Still checks existing kategory manual NOT allowed needs to be changed to Repository
                if(!katService.isInList(response.category)) {
                    Kategorie kat = new Kategorie();
                    kat.setName(response.category);
                    kat.setBeschreibung(response.category);
                    frage.setKategorie(kat);
                    kat.getFragen().add(frage);
                    katService.speichereKategorie(kat);
                } else {
                    frage.setKategorie(katService.holeKategorieMitNamen(response.category));
                    katService.holeKategorieMitNamen(response.category).getFragen().add(frage);
                }
                fragen.add(frage);
            }
            return fragen;

    }

    
}
