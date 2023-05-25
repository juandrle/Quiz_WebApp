package de.hsrm.mi.web.projekt.ui.quiz;

import java.util.ArrayList;
import java.util.List;

import de.hsrm.mi.web.projekt.entities.frage.Frage;
import de.hsrm.mi.web.projekt.entities.quiz.Quiz;
import jakarta.validation.constraints.NotEmpty;

public class QuizFormular {
    @NotEmpty
    private String name;

    @NotEmpty
    private List<Frage> fragen;

    public QuizFormular() {
        this.fragen = new ArrayList<>();
    }

    public void fromQuiz(Quiz quiz) {
        this.name = quiz.getName();
        this.fragen = (List<Frage>) quiz.getFragen();
    }

    public void toQuiz(Quiz quiz) {
        quiz.setName(name);
        quiz.setFragen(fragen);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Frage> getFragen() {
        return fragen;
    }

    public void setFragen(List<Frage> fragen) {
        this.fragen = fragen;
    }

}
