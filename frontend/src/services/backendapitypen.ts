
export interface IQuizInfo {
    id: number
    name: string
    anzahlFragen: number
}

export interface IQuiz {
    id: number
    name: string
    fragen: Array<IFrage>
    gesamtpunkte: number
}

export interface IFrage {
    id: number
    katname: string
    fragetext: string
    alleAntworten: Array<string>
    punkte: number
}

export interface IAntwort {
    fid: number,
    antwort: string
}

export interface IBeantwortetesQuiz {
    qid: number,
    antworten: Array<IAntwort>
}

export interface IErgebnis {
    fid: number,
    richtig: boolean
}

export interface IQuizErgebnis {
    qid: number,
    ergebnisse: Array<IErgebnis>
}

export interface IFrontendNachrichtEvent {
    eventTyp: string,
    eventID: number,
    operationTyp: string
}