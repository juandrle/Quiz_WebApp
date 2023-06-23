
export interface IQuizInfo {
    id: number
    name: string
    anzahlFragen: number
}

export interface IQuiz {
    id: number
    name: string
    fragen: readonly IFrage[]
    gesamtpunkte: number
}

export interface IFrage {
    id: number
    katname: string
    fragetext: string
    alleAntworten: readonly string[]
    punkte: number
}

export interface IAntwort {
    fid: number,
    antwort: string
}

export interface IBeantwortetesQuiz {
    qid: number,
    antworten: readonly IAntwort[]
}

export interface IErgebnis {
    fid: number,
    richtig: boolean
}

export interface IQuizErgebnis {
    qid: number,
    ergebnisse: readonly IErgebnis[]
}

export interface IFrontendNachrichtEvent {
    eventTyp: string,
    eventID: number,
    operationTyp: string
}